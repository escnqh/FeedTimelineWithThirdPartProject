package com.meitu.qihangni.feedtimelinewiththirdpartproject.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author nqh 2018/7/16
 */
public abstract class BasePresenter<V> {
    protected Reference<V> mViewRef;

    //与View建立关系
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    //获取View
    protected V getView() {
        return mViewRef.get();
    }

    //判断是否与View建立了关系
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    //与View解除关系
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
