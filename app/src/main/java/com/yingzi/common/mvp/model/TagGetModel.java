package com.yingzi.common.mvp.model;

import com.yingzi.common.RetrofitUtil;
import com.yingzi.common.model.TagModel;
import com.yingzi.common.mvp.IDataRequestListener;

import rx.Subscriber;

/**
 * Created by yingzi on 2017/5/20.
 */

public class TagGetModel {
    public void loadData(final IDataRequestListener.CallBackListener callBackListener) {
        RetrofitUtil.getInstance().getTagData(new Subscriber<TagModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callBackListener.failed();
            }

            @Override
            public void onNext(TagModel tagModel) {
                callBackListener.loadSuccess(tagModel);
            }
        });
    }
}
