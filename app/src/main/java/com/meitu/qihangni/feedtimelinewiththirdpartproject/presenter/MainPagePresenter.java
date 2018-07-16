package com.meitu.qihangni.feedtimelinewiththirdpartproject.presenter;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.base.BasePresenter;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.bean.PageContentBean;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.MainPageContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.model.MainPageModel;

import java.util.List;

/**
 * @author nqh 2018/7/16
 */
public class MainPagePresenter extends BasePresenter<MainPageContract.View<PageContentBean>> implements MainPageContract.InteractionListener<List<PageContentBean>>, MainPageContract.Presenter {
    private MainPageContract.View<PageContentBean> mView;
    private MainPageContract.Model mModel;

    public MainPagePresenter(MainPageContract.View<PageContentBean> view) {
        this.mView = view;
        this.mModel = new MainPageModel(this);
    }

    @Override
    public void requestPageContent(int pageid) {
        mModel.loadPageContent(pageid);
    }

    @Override
    public void onSeccess(List<PageContentBean> pageContentBeans) {
        mView.responsePageContent(pageContentBeans);
    }

    @Override
    public void onFailed(int errCode, String errMsg) {

    }
}
