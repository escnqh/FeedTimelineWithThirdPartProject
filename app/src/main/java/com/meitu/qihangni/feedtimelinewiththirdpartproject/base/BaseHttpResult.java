package com.meitu.qihangni.feedtimelinewiththirdpartproject.base;

/**
 * @author nqh 2018/7/25
 */
public class BaseHttpResult<T> {
    private int mStatus;
    private String mMsg;
    private T mResultBody;

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public T getmResultBody() {
        return mResultBody;
    }

    public void setmResultBody(T mResultBody) {
        this.mResultBody = mResultBody;
    }
}
