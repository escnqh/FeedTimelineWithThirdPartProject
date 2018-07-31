package com.meitu.qihangni.feedtimelinewiththirdpartproject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.R;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.base.BaseActivity;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.contract.DownloadContract;
import com.meitu.qihangni.feedtimelinewiththirdpartproject.presenter.DownloadPresenter;

/**
 * @author nqh 2018/7/30
 */
public class DownloadActivity extends BaseActivity<DownloadContract.View, DownloadPresenter> implements DownloadContract.View, DownloadContract.Presenter {
    private ProgressBar mProgressBar;
    private Button mButtonStart;
    private Button mButtonStop;
    private int mStartTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        mProgressBar = findViewById(R.id.progressbar_1);
        mButtonStart = findViewById(R.id.btn_start);
        mButtonStop = findViewById(R.id.btn_stop);
        mButtonStart.setOnClickListener(v -> {
            if (mStartTime == 0) {
                requestDownload();
                mStartTime++;
            } else {
                requesrContinue();
            }
        });
        mButtonStop.setOnClickListener(v -> requestPause());
    }

    @Override
    protected DownloadPresenter createPresenter() {
        return new DownloadPresenter(this, this);
    }

    @Override
    public void onProcess(long process, long total) {
        mProgressBar.setMax((int) total);
        mProgressBar.setProgress((int) process);
    }

    @Override
    public void onComplete(String filename) {
        Toast.makeText(this, filename + " complete!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestDownload() {
        mPresenter.requestDownload();
    }

    @Override
    public void requesrContinue() {
        mPresenter.requesrContinue();
    }

    @Override
    public void requestPause() {
        mPresenter.requestPause();
    }
}
