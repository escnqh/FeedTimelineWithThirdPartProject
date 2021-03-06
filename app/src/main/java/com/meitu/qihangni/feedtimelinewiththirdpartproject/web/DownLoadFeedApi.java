package com.meitu.qihangni.feedtimelinewiththirdpartproject.web;

import retrofit2.Retrofit;

/**
 * @author nqh 2018/7/16
 */
public class DownLoadFeedApi extends WebApi {

    String mUrl = "http://preapi.meipai.com/hot/";

    Retrofit mRetrofit = getApi(mUrl);

    @Override
    public <T> T getService() {
        return (T) mRetrofit.create(DownLoadFeedService.class);
    }
}
