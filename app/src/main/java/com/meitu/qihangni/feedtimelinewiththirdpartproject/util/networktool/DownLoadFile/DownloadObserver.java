package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author nqh 2018/7/30
 */
public abstract class DownloadObserver implements Observer<DownloadInfo> {
    protected Disposable mDisposable;
    protected DownloadInfo mDownloadInfo;

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(DownloadInfo downloadInfo) {
        this.mDownloadInfo = downloadInfo;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
