package com.meitu.qihangni.feedtimelinewiththirdpartproject.contract;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.base.BaseContract;

import java.util.List;

/**
 * @author nqh 2018/7/16
 */
public class MainPageContract extends BaseContract {
    public interface Model {
        void loadPageContent(int pageid);
    }

    public interface View<T> {
        void responsePageContent(List<T> list);
    }

    public interface Presenter {
        void requestPageContent(int pageid);
    }

    public interface InteractionListener<T> {
        void onSeccess(T t);

        void onFailed(int errCode, String errMsg);
    }

}
