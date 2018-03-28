package com.yingzi.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by yingzi on 2017/5/22.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private static Context context;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
