package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile;

/**
 * @author nqh 2018/7/30
 */
public class DownloadInfo {
    public static final long TOTAL_ERROR = -1;
    private String mUrl;
    private String mFilename;
    private long mTotal;
    private long mProgress;

    public DownloadInfo(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getFileName() {
        return mFilename;
    }

    public void setFileName(String mFilename) {
        this.mFilename = mFilename;
    }

    public long getTotal() {
        return mTotal;
    }

    public void setTotal(long mTotal) {
        this.mTotal = mTotal;
    }

    public long getProgress() {
        return mProgress;
    }

    public void setProgress(long mProgress) {
        this.mProgress = mProgress;
    }
}
