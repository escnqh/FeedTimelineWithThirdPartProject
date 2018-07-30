package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkMethod;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.BaseService;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.FeedService;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkClient;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.web.HttpService;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author nqh 2018/7/26
 */
public class RetrofitMethod<T> implements NetworkContract.NetworkMethod {
    private NetworkContract.ResponseMethod responseMethod;

    private Retrofit mRetrofit;
    private BaseService mHttpService;


    public RetrofitMethod(Map<String, String> map) {
        try {
            mHttpService = mRetrofit.create(BaseService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mHttpService.get(map);
    }

    @Override
    public void execute(NetworkClient.RequestBuilder requestBuilder, OkHttpClient okHttpClient, NetworkContract.ResponseMethod responseMethod) {
        this.responseMethod = responseMethod;
        mRetrofit.newBuilder()
                .client(okHttpClient)
                .baseUrl(requestBuilder.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }

    @Override
    public void cancel() {

    }
}
