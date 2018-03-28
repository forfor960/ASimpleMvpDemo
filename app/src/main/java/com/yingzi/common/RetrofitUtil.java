package com.yingzi.common;

import com.yingzi.common.model.DataModel;
import com.yingzi.common.model.TagModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yingzi on 2017/5/26.
 */

public class RetrofitUtil {
    public static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private HttpService mHttpService;

    private static RetrofitUtil mInstance;

    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mHttpService = mRetrofit.create(HttpService.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    /**
     * 列表获取
     *
     * @param cid
     * @param page
     * @param count
     * @param subscriber
     */
    public void getData(String cid, String page, String count, Subscriber<DataModel> subscriber) {
        mHttpService.getDatas(cid, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 标签页面
     *
     * @param subscriber
     */
    public void getTagData(Subscriber<TagModel> subscriber) {
        mHttpService.getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
