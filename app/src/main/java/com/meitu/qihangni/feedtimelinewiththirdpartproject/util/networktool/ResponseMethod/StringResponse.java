package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.ResponseMethod;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author nqh 2018/7/31
 */
public class StringResponse implements NetworkContract.ResponseMethod {
    private final NetworkContract.NetworkCallback<String> callback;

    public StringResponse(NetworkContract.NetworkCallback<String> callback) {
        this.callback = callback;
    }

    @Override
    public void onSucceed(Response response) throws IOException {
        if (null != response.body()) {
            String responseString = response.body().string();
            callback.onSucceed(responseString);
        } else {
            callback.onFailed("response.body is null!");
        }
    }

    @Override
    public void onFailed(String errorMsg) {
        callback.onFailed(errorMsg);
    }

    @Override
    public void onProcess(long total, long process) {
    }

    @Override
    public void onComplete(String filename) {
    }
}
