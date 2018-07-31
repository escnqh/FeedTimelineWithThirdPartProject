package com.meitu.qihangni.feedtimelinewiththirdpartproject.contract;

/**
 * @author nqh 2018/7/30
 */
public class DownloadContract {
    public interface Model {
        void download();

        void pause();

        void restart();
    }

    public interface View {
        void onProcess(long process, long total);

        void onComplete(String filename);

        void onFailed(String errorMsg);
    }

    public interface Presenter {
        void requestDownload();

        void requesrContinue();

        void requestPause();
    }

    public interface InteractionListener {
        void onProcess(long process, long total);

        void onFailed(String errorMsg);

        void onComplete(String filename);
    }
}
