package com.meitu.qihangni.feedtimelinewiththirdpartproject.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.R;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.adapter.MainPageContentAdapter;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.base.BaseActivity;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.MainPageContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.presenter.MainPagePresenter;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends BaseActivity<MainPageContract.View<PageContentBean>, MainPagePresenter> implements MainPageContract.View<PageContentBean>, MainPageContract.Presenter {
    private final String TAG = this.getClass().getName();
    private List<PageContentBean> mPageContentList = new ArrayList<>();
    private MainPageContentAdapter mMainPageContentAdapter;
    private RecyclerView mRecyclerviewMainpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        mRecyclerviewMainpage = findViewById(R.id.recyclerview_mainpage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerviewMainpage.setLayoutManager(linearLayoutManager);
        mMainPageContentAdapter = new MainPageContentAdapter(mPageContentList, this);
        mRecyclerviewMainpage.setAdapter(mMainPageContentAdapter);
        this.requestPageContent(2);
    }

    @Override
    protected MainPagePresenter createPresenter() {
        return new MainPagePresenter(this);
    }

    @Override
    public void responsePageContent(List<PageContentBean> list) {
        mPageContentList.clear();
        mPageContentList.addAll(list);
        try {
            mMainPageContentAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestPageContent(int pageid) {
        mPresenter.requestPageContent(pageid);
    }
}
