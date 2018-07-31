package com.meitu.qihangni.feedtimelinewiththirdpartproject.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.base.BasePresenter;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.DownloadContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.model.DownloadModel;

/**
 * @author nqh 2018/7/30
 */
public class DownloadPresenter extends BasePresenter<DownloadContract.View> implements DownloadContract.InteractionListener, DownloadContract.Presenter {

    private DownloadContract.View mView;
    private DownloadContract.Model mModel;

    public DownloadPresenter(DownloadContract.View view, @NonNull Context context) {
        this.mView = view;
        this.mModel = new DownloadModel(this, context);
    }

    @Override
    public void requestDownload() {
        mModel.download();
    }

    @Override
    public void requesrContinue() {
        mModel.restart();
    }

    @Override
    public void requestPause() {
        mModel.pause();
    }

    @Override
    public void onProcess(long process, long total) {
        mView.onProcess(process, total);
    }

    @Override
    public void onFailed(String errorMsg) {
        mView.onFailed(errorMsg);
    }

    @Override
    public void onComplete(String filename) {
        mView.onComplete(filename);
    }
}
