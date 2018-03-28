package com.yingzi.common;


import com.yingzi.common.model.DataModel;
import com.yingzi.common.model.TagModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yingzi on 2017/5/1.
 */
public interface HttpService {
    @GET("category/query?key=1b9803a23ca71")
    Observable<TagModel> getTags();

    @GET("search?key=1b9803a23ca71")
    Observable<DataModel> getDatas(@Query("cid") String cid, @Query("page") String page, @Query("size") String size);
}
