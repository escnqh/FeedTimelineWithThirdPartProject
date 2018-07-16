package com.meitu.qihangni.feedtimelinewiththirdpartproject.model;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.MainPageContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.webapi.DownLoadFeedApi;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.webservice.DownLoadFeedService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author nqh 2018/7/16
 */
public class MainPageModel implements MainPageContract.Model {

    private List<PageContentBean> mPageContentList = new ArrayList<>();
    private MainPageContract.InteractionListener<List<PageContentBean>> mListener;

    public MainPageModel(MainPageContract.InteractionListener<List<PageContentBean>> listener) {
        this.mListener = listener;
    }

    @Override
    public void loadPageContent(int pageid) {
        mPageContentList.clear();
        DownLoadFeedApi downLoadFeedApi = new DownLoadFeedApi();
        DownLoadFeedService downLoadFeedService = downLoadFeedApi.getService();
        Call<List<PageContentBean>> call_downloadmaopage = downLoadFeedService.getState(pageid);
        call_downloadmaopage.enqueue(new Callback<List<PageContentBean>>() {
            @Override
            public void onResponse(Call<List<PageContentBean>> call, Response<List<PageContentBean>> response) {
                if (null != response) {
                    mListener.onSeccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PageContentBean>> call, Throwable t) {

            }
        });
    }
}
