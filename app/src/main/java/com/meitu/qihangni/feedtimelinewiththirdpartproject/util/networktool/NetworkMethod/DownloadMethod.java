package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkMethod;

import android.app.Application;
import android.content.Context;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile.DownloadInfo;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile.DownloadObserver;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile.IOUtil;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkClient;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author nqh 2018/7/30
 */
public class DownloadMethod implements NetworkContract.DownloadMethod {
    private OkHttpClient mOkHttpClient;
    private NetworkClient.RequestBuilder mRequestBuilder;
    private NetworkContract.DownloadResponseMethod mResponseMethod;
    private HashMap<String, Call> downCalls;
    private Context mContext;

    public DownloadMethod(Context appContext) {
        if (appContext instanceof Application) {
            mContext = appContext;
        } else {
            mContext = appContext.getApplicationContext();
        }
    }


    @Override
    public void execute(NetworkClient.RequestBuilder requestBuilder, OkHttpClient okHttpClient, NetworkContract.ResponseMethod responseMethod) {
        this.downCalls = new HashMap<>();
        this.mOkHttpClient = okHttpClient;
        this.mRequestBuilder = requestBuilder;
        this.mResponseMethod = (NetworkContract.DownloadResponseMethod) responseMethod;
        Observable.just(mRequestBuilder.getUrl())
                .filter(s -> !downCalls.containsKey(s))//call的map已经有了,就证明正在下载,则这次不下载
                .flatMap(s -> Observable.just(createDownloadInfo(s)))
                .map(this::getRealFileName)//检测本地文件夹,生成新的文件名
                .flatMap(downloadInfo -> Observable.create(new DownloadSubscribe(downloadInfo)))//下载
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调
                .subscribeOn(Schedulers.io())//在子线程执行
                .subscribe(new DownloadObserver() {//添加观察者
                    @Override
                    public void onNext(DownloadInfo downloadInfo) {
                        super.onNext(downloadInfo);
                        mResponseMethod.onProcess(downloadInfo.getTotal(), downloadInfo.getProgress());
                    }

                    @Override
                    public void onComplete() {
                        mResponseMethod.onComplete(mDownloadInfo.getFileName());
                    }
                });
    }

    @Override
    public void pause() {
        Call call = downCalls.get(mRequestBuilder.getUrl());
        if (call != null) {
            call.cancel();
        }
        downCalls.remove(mRequestBuilder.getUrl());
    }

    @Override
    public void cancel() {
        //todo 删除已下载文件的操作
    }


    private DownloadInfo createDownloadInfo(String url) {
        DownloadInfo downloadInfo = new DownloadInfo(url);
        long contentLength = getContentLength(url);
        downloadInfo.setTotal(contentLength);
        String filename = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFileName(filename);
        return downloadInfo;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String fileName = downloadInfo.getFileName();
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        File file = new File(mContext.getFilesDir(), fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (downloadLength >= contentLength) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(mContext.getFilesDir(), fileNameOther);
            file = newFile;
            downloadLength = newFile.length();
            i++;
        }
        //设置改变过的文件名/大小
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }


    /**
     * @param downloadUrl 目标链接
     * @return 下载长度
     */
    private long getContentLength(String downloadUrl) {
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (null != response && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }

    public class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
        private DownloadInfo mDownloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.mDownloadInfo = downloadInfo;
        }

        @Override
        public void subscribe(ObservableEmitter<DownloadInfo> emitter) throws Exception {
            String url = mDownloadInfo.getUrl();
            long downloadLength = mDownloadInfo.getProgress();
            long contentLength = mDownloadInfo.getTotal();
            emitter.onNext(mDownloadInfo);
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                    .url(url)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            downCalls.put(url, call);
            Response response = call.execute();
            File file = new File(mContext.getFilesDir(), mDownloadInfo.getFileName());
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                inputStream = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                byte[] buffer = new byte[2048];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downloadLength += len;
                    mDownloadInfo.setProgress(downloadLength);
                    emitter.onNext(mDownloadInfo);
                }
                fileOutputStream.flush();
                downCalls.remove(url);
            } finally {
                IOUtil.closeAll(inputStream, fileOutputStream);
            }
            emitter.onComplete();
        }
    }
}
