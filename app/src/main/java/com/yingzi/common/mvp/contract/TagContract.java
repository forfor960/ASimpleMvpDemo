package com.yingzi.common.mvp.contract;

import com.yingzi.common.model.TagModel;
import com.yingzi.common.mvp.BasePresenter;
import com.yingzi.common.mvp.BaseView;

/**
 * Created by yingzi on 2017/5/20.
 */

public interface TagContract {
    interface View extends BaseView {
        void showloadData(TagModel tagModel);

    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
