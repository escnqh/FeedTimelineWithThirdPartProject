package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author nqh 2018/7/27
 */
public interface BaseService<T> {
    @GET()
    Call<T> get(@QueryMap Map<String, String> paramsMap);
}
