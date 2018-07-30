package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author nqh 2018/7/30
 */
public class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
    private DownloadInfo mDownloadInfo;
    private OkHttpClient mOkHttpClient;

    public DownloadSubscribe(DownloadInfo downloadInfo, OkHttpClient okHttpClient) {
        this.mDownloadInfo = downloadInfo;
        this.mOkHttpClient = okHttpClient;
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
    }
}
