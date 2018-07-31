package com.meitu.qihangni.feedtimelinewiththirdpartproject.model;

import android.content.Context;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.DownloadContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkClient;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkMethod.DownloadMethod;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.ResponseMethod.DownloadResponse;

/**
 * @author nqh 2018/7/30
 */
public class DownloadModel implements DownloadContract.Model {
    private DownloadContract.InteractionListener mListener;
    private NetworkContract.NetworkMethod mDownloadMethod;
    private NetworkContract.ResponseMethod mDownloadResponseMethod;
    NetworkClient.RequestBuilder mRequestBuilder;

    public DownloadModel(DownloadContract.InteractionListener listener, Context context) {
        this.mListener = listener;
        this.mDownloadMethod = new DownloadMethod(context);
        mDownloadResponseMethod = new DownloadResponse(new NetworkContract.NetworkCallback() {
            @Override
            public void onSucceed(Object o) {
            }

            @Override
            public void onProcess(long total, long process) {
                mListener.onProcess(process, total);
            }

            @Override
            public void onComplete(String filename) {
                mListener.onComplete(filename);
            }

            @Override
            public void onFailed(String errorMsg) {
                mListener.onFailed(errorMsg);
            }
        });
    }

    @Override
    public void download() {
        mRequestBuilder = new NetworkClient.RequestBuilder()
                .url("https://github.com/escnqh/ExpandableTextView-View/archive/master.zip");
        NetworkClient.getInstance(mRequestBuilder, mDownloadMethod).execute(mDownloadResponseMethod);
    }

    @Override
    public void pause() {
        NetworkClient.getInstance(mRequestBuilder, mDownloadMethod).pause();
    }

    @Override
    public void restart() {
        NetworkClient.getInstance(mRequestBuilder, mDownloadMethod).restart();
    }
}
