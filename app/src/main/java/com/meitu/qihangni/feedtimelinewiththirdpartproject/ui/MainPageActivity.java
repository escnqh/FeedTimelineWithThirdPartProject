package com.meitu.qihangni.feedtimelinewiththirdpartproject.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.R;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.adapter.MainPageContentAdapter;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.base.BaseActivity;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.MainPageContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.presenter.MainPagePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageActivity extends BaseActivity<MainPageContract.View<PageContentBean>, MainPagePresenter> implements MainPageContract.View<PageContentBean>, MainPageContract.Presenter {
    private final String TAG = this.getClass().getName();
    @BindView(R.id.recyclerview_mainpage)
    RecyclerView mRecyclerviewMainpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        ButterKnife.bind(this);
        this.requestPageContent(2);
    }

    @Override
    protected MainPagePresenter createPresenter() {
        return new MainPagePresenter(this);
    }

    @Override
    public void responsePageContent(List<PageContentBean> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerviewMainpage.setLayoutManager(linearLayoutManager);
        MainPageContentAdapter mainPageContentAdapter = new MainPageContentAdapter(list, this);
        mRecyclerviewMainpage.setAdapter(mainPageContentAdapter);
    }

    @Override
    public void requestPageContent(int pageid) {
        mPresenter.requestPageContent(pageid);
    }
}
