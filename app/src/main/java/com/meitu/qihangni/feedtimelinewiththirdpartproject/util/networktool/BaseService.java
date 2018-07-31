package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author nqh 2018/7/27
 */
public interface BaseService {

    /**
     * get请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    @GET
    Call<Response> get(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    /**
     * post表单发送键值对
     *
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<Response> post(@Url String url, @FieldMap Map<String, String> params);

    /**
     * post发送json/xml
     *
     * @param url
     * @param object
     * @return
     */
    @POST
    Call<Response> post(@Url String url, @Body Object object);

    /**
     * post发送json/xml
     *
     * @param url
     * @param requestBody
     * @return
     */
    @POST
    Call<Response> post(@Url String url, @Body RequestBody requestBody);

    /**
     * get下载文件
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    @Streaming
    @GET
    Call<Response> download(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    /**
     * post上传文件
     *
     * @param url
     * @param params
     * @param requestBody
     * @return
     */
    @Multipart
    @POST
    Call<Response> upload(@Url String url, @PartMap Map<String, String> params, @Part MultipartBody.Part requestBody);
}
