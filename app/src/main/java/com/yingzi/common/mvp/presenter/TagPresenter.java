package com.yingzi.common.mvp.presenter;

import android.content.Context;

import com.yingzi.common.model.TagModel;
import com.yingzi.common.mvp.IDataRequestListener;
import com.yingzi.common.mvp.contract.TagContract;
import com.yingzi.common.mvp.model.TagGetModel;

/**
 * Created by yingzi on 2017/5/20.
 */

public class TagPresenter implements TagContract.Presenter {
    private TagGetModel tagModel;
    private TagContract.View mView;
    private Context mContext;

    public TagPresenter(Context context) {
        this.tagModel = new TagGetModel();
        this.mView = (TagContract.View) context;
        this.mContext = context;

    }


    @Override
    public void loadData() {
        tagModel.loadData(new IDataRequestListener.CallBackListener() {
            @Override
            public void loadSuccess(Object object) {
                mView.showloadData((TagModel) object);
            }

            @Override
            public void failed() {
                mView.showloadData(null);

            }
        });
    }

    public void cancalView() {
        if (mView != null) {
            mView = null;
        }
    }
}
