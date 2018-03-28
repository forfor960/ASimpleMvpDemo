package com.yingzi.common.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yingzi.common.widget.DividerItemDecoration;
import com.yingzi.common.R;
import com.yingzi.common.activity.NewsDetailActivity;
import com.yingzi.common.base.BaseFragment;
import com.yingzi.common.model.DataModel;
import com.yingzi.common.mvp.contract.DataContract;
import com.yingzi.common.mvp.presenter.DataPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingzi on 2017/5/19.
 */

public class ShowFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, DataContract.View {
    public static final String ARG_PAGE = "ARG_PAGE";
    private String cid;
    private RecyclerView mRecyclerview;
    private LinearLayoutManager layoutManager;
    private BaseQuickAdapter mAdapter;
    protected List<DataModel.Detail> mEntites = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DataPresenter mDataPresenter;
    private String page = "1";
    private boolean firstLoad = false;

    private final static int TYPE_PULLREFRESH = 1;
    private final static int TYPE_UPLOADREFRESH = 2;

    public ShowFragment() {
        firstLoad = true;
    }

    public static ShowFragment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, page);
        ShowFragment pageFragment = new ShowFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cid = getArguments().getString(ARG_PAGE);
        mDataPresenter = new DataPresenter(this, getActivity());
    }

    @Override
    protected void loadData() {
        if (!isPrepared || !isVisible || !firstLoad) {
            return;
        }
        firstLoad = false;
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        onRefresh();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        initView(view);
        isPrepared = true;
        loadData();
        return view;
    }

    private void initView(View view) {
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mRecyclerview.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_item_line));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BaseQuickAdapter<DataModel.Detail, BaseViewHolder>(R.layout.fragment_item, mEntites) {
            @Override
            protected void convert(BaseViewHolder helper, DataModel.Detail item) {
                helper.setText(R.id.title_tv, item.getTitle());
                helper.setText(R.id.pubtime_tv, item.getPubTime());
            }
        };
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, mEntites.get(position).getSourceUrl());
                intent.putExtra(NewsDetailActivity.TITLE, mEntites.get(position).getTitle());
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, R.drawable.recycler_item_line));
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mDataPresenter.loadData(TYPE_UPLOADREFRESH, cid, page);
            }
        });
    }

    @Override
    public void onRefresh() {
        page = "1";
        mDataPresenter.loadData(TYPE_PULLREFRESH, cid, page);
    }

    @Override
    public void showDataView(int type, DataModel dataModel) {
        if (dataModel != null) {
            page = String.valueOf(Integer.parseInt(dataModel.getResult().getCurPage()) + 1);
            if (type == TYPE_PULLREFRESH) {
                mEntites.clear();
                mEntites.addAll(dataModel.getResult().getList());
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            } else if (type == TYPE_UPLOADREFRESH) {
                if (dataModel.getResult().getList().size() > 0) {
                    mEntites.addAll(dataModel.getResult().getList());
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        } else {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDataPresenter.cancalView();
    }
}
