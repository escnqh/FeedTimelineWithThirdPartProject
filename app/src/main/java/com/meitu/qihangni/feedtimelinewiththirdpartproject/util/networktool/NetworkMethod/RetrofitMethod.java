package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkMethod;

import android.util.Log;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.BaseService;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkClient;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author nqh 2018/7/26
 */
public class RetrofitMethod implements NetworkContract.NetworkMethod {
    private NetworkContract.ResponseMethod responseMethod;

    private Retrofit mRetrofit;
    private BaseService mHttpService;


    public RetrofitMethod() {
    }

    @Override
    public void execute(NetworkClient.RequestBuilder requestBuilder, OkHttpClient okHttpClient, NetworkContract.ResponseMethod responseMethod) {
        this.responseMethod = responseMethod;
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(requestBuilder.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mHttpService = mRetrofit.create(BaseService.class);
        if (requestBuilder.getHttpMethod() == NetworkContract.HttpMethod.POST) {
            if (null != requestBuilder.getRequestBody()) {
                mHttpService.post(requestBuilder.getUrl(), requestBuilder.getRequestBody());
            }
            if (null != requestBuilder.getParams()) {
                mHttpService.post(requestBuilder.getUrl(), requestBuilder.getParams());
            }
        } else {
            mHttpService.get(requestBuilder.getUrl(), requestBuilder.getParams(), requestBuilder.getHeaders());
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
    }
}
