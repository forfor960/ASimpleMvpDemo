package com.yingzi.common.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.yingzi.common.R;

/**
 * Created by yingzi on 2017/5/22.
 */

public abstract class BaseFragment extends RxFragment {
    protected FragmentActivity mActivity;
    protected boolean isVisible;
    public boolean isPrepared = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
    }

    public void startActivityDefault(Intent intent) {
        super.startActivity(intent);
    }

    protected void onVisible() {
        loadData();
    }

    protected abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }
}
