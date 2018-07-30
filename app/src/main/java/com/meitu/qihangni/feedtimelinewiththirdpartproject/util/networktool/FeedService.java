package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author nqh 2018/7/27
 */
public interface FeedService extends BaseService<List<PageContentBean>> {
    @GET("feed_timeline.json")
    Call<List<PageContentBean>> get(
            @QueryMap Map<String, String> paramsMap
    );
}
