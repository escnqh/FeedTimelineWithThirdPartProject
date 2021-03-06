package com.meitu.qihangni.feedtimelinewiththirdpartproject.web;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author nqh 2018/7/16
 */
public interface DownLoadFeedService {
    @GET("feed_timeline.json")
    Observable<List<PageContentBean>> getState(
            @Query("page") int pageid
    );
}
