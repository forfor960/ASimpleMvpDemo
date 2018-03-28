package com.yingzi.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yingzi.common.R;

/**
 * Created by yingzi on 2017/5/19.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        initViews();
        initDatas();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
    }

    public void startActivityDefault(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
    }

    public void finishDefault() {
        super.finish();
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initDatas();

    @Override
    protected void onPause() {
        super.onPause();
    }
}
