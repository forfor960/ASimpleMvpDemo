package com.yingzi.common.mvp.contract;

import com.yingzi.common.model.DataModel;
import com.yingzi.common.mvp.BasePresenter;
import com.yingzi.common.mvp.BaseView;

/**
 * Created by yingzi on 2017/5/7.
 */
public interface DataContract {
    interface View extends BaseView {
        void showDataView(int type, DataModel dataModel);
    }

    interface Presenter extends BasePresenter {
        void loadData(int type, String cid, String page);
    }
}
