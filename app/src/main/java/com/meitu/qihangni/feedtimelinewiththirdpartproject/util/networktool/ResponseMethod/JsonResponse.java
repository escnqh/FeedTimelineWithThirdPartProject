package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.ResponseMethod;

import com.google.gson.Gson;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Json解析方式应遵守协议{@link com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract.ResponseMethod}
 * 并且通过用户实现的{@link com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract.NetworkCallback}返回
 *
 * @author nqh 2018/7/27
 */
public class JsonResponse<T> implements NetworkContract.ResponseMethod {
    private final NetworkContract.NetworkCallback<T> callback;
    private final Type dataType;

    public JsonResponse(NetworkContract.NetworkCallback<T> callback, Type dataType) {
        this.callback = callback;
        this.dataType = dataType;
    }

    @Override
    public void onSucceed(Response response) throws IOException {
        if (response.body() != null) {
            String jsonString = response.body().string();
            Gson gson = new Gson();
            T t = gson.fromJson(jsonString, dataType);
            callback.onSucceed(t);
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
