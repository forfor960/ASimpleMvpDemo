package com.yingzi.common.mvp.model;

import android.widget.Toast;

import com.yingzi.common.MyApplication;
import com.yingzi.common.RetrofitUtil;
import com.yingzi.common.model.DataModel;
import com.yingzi.common.mvp.IDataRequestListener;

import rx.Subscriber;

/**
 * Created by yingzi on 2017/5/7.
 */
public class DataGetModel {
    public void loadData(String cid, String page, final IDataRequestListener.CallBackListener callBackListener) {
        RetrofitUtil.getInstance().getData(cid, page, "20", new Subscriber<DataModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callBackListener.failed();
                Toast.makeText(MyApplication.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(DataModel dataModel) {
                callBackListener.loadSuccess(dataModel);
            }
        });
    }
}
