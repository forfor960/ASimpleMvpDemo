package com.yingzi.common.mvp.presenter;

import android.content.Context;

import com.yingzi.common.model.DataModel;
import com.yingzi.common.mvp.IDataRequestListener;
import com.yingzi.common.mvp.contract.DataContract;
import com.yingzi.common.mvp.model.DataGetModel;

/**
 * Created by yingzi on 2017/5/7.
 */
public class DataPresenter implements DataContract.Presenter {
    private DataGetModel mDataGetModel;
    private DataContract.View mView;
    private Context mContext;

    public DataPresenter(DataContract.View view, Context context) {
        mDataGetModel = new DataGetModel();
        this.mView = view;
        this.mContext = context;
    }


    @Override
    public void loadData(final int type, String cid, String page) {
        mDataGetModel.loadData(cid, page, new IDataRequestListener.CallBackListener() {
            @Override
            public void loadSuccess(Object object) {
                mView.showDataView(type,(DataModel) object);
            }

            @Override
            public void failed() {
                mView.showDataView(type,null);
            }
        });
    }

    public void cancalView() {
        if (mView != null) {
            mView = null;
        }
    }
}
