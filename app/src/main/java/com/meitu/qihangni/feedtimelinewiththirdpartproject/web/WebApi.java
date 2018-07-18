package com.meitu.qihangni.feedtimelinewiththirdpartproject.web;

import com.google.gson.GsonBuilder;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.IntegerGsonDeserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 需要设置类型拦截器{@link IntegerGsonDeserializer}来将后端返回int类型的""字段转换为-1，方便处理
 *
 * @author nqh 2018/7/16
 */
public abstract class WebApi {
    Retrofit getApi(String url) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(int.class, new IntegerGsonDeserializer());
        gsonBuilder.registerTypeAdapter(Integer.class, new IntegerGsonDeserializer());
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();
    }

    public abstract <T> T getService();
}
