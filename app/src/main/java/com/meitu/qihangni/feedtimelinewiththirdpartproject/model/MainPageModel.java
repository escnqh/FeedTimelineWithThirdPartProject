package com.meitu.qihangni.feedtimelinewiththirdpartproject.model;

import com.google.gson.reflect.TypeToken;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.MainPageContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkMethod.DownloadMethod;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.ResponseMethod.JsonResponse;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkClient;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.NetworkContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        NetworkClient.RequestBuilder requestBuilder = new NetworkClient.RequestBuilder()
                .url("http://preapi.meipai.com/hot/feed_timeline.json?page=2");
        Map<String, String> map = new HashMap<>();
        NetworkClient.getInstance(requestBuilder, new DownloadMethod(null)).execute(new JsonResponse<>(new NetworkContract.NetworkCallback<List<PageContentBean>>() {
            @Override
            public void onSucceed(List<PageContentBean> pageContentBeans) {
                mListener.onSeccess(pageContentBeans);
            }

            @Override
            public void onProcess(long total, long process) {
            }

            @Override
            public void onComplete(String filename) {
            }

            @Override
            public void onFailed(String errorMsg) {
                mListener.onFailed(0, errorMsg);
            }
        }, new TypeToken<List<PageContentBean>>() {
        }.getType()));
    }
}
