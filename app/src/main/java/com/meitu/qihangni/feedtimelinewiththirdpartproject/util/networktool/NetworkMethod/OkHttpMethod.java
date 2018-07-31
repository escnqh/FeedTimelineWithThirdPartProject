package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkMethod;

import android.support.annotation.NonNull;
import android.text.style.URLSpan;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkClient;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author nqh 2018/7/26
 */
public class OkHttpMethod implements NetworkContract.NetworkMethod {
    private NetworkContract.ResponseMethod mResponseMethod;
    private Call call;

    @Override
    public void execute(NetworkClient.RequestBuilder requestBuilder, OkHttpClient okHttpClient, NetworkContract.ResponseMethod responseMethod) {
        this.mResponseMethod = responseMethod;
        if (requestBuilder.getHttpMethod().equals(NetworkContract.HttpMethod.GET)) {
            Request request = new Request.Builder()
                    .url(requestBuilder.getUrl())
                    .get()
                    .build();
            call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    mResponseMethod.onFailed(e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    mResponseMethod.onSucceed(response);
                }
            });
        } else if (requestBuilder.getHttpMethod().equals(NetworkContract.HttpMethod.POST)) {
            RequestBody requestBody = requestBuilder.getRequestBody();
            if (requestBody == null) {
                mResponseMethod.onFailed("requestbody is null,please use GET");
                return;
            }
            Request request = new Request.Builder()
                    .url(requestBuilder.getUrl())
                    .post(requestBody)
                    .build();
            call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    mResponseMethod.onFailed(e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    mResponseMethod.onSucceed(response);
                }
            });
        }

    }

    @Override
    public void restart() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }
}
