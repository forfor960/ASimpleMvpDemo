package com.yingzi.common.mvp;

/**
 * Created by yingzi on 2017/5/20.
 */

public interface IDataRequestListener {
    interface CallBackListener {
        void loadSuccess(Object object);

        void failed();
    }
}
