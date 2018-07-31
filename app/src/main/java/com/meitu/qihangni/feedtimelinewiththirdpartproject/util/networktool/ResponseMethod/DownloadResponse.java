package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.ResponseMethod;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author nqh 2018/7/30
 */
public class DownloadResponse implements NetworkContract.ResponseMethod {
    private NetworkContract.NetworkCallback callback;

    public DownloadResponse(NetworkContract.NetworkCallback callback) {
        this.callback = callback;
    }


    @Override
    public void onSucceed(Response response) throws IOException {
    }

    @Override
    public void onFailed(String errorMsg) {
        callback.onFailed(errorMsg);
    }

    @Override
    public void onProcess(long total, long process) {
        callback.onProcess(total,process);
    }

    @Override
    public void onComplete(String filename) {
        callback.onComplete(filename);
    }
}
