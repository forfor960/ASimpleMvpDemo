package com.yingzi.common;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yingzi.common.adapter.MyFragmentPagerAdapter;
import com.yingzi.common.base.BaseActivity;
import com.yingzi.common.model.TagModel;
import com.yingzi.common.mvp.contract.TagContract;
import com.yingzi.common.mvp.presenter.TagPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements TagContract.View {
    private MyFragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewpager;
    private TabLayout mTablayout;
    private TagPresenter mTagPresenter;
    private List<TagModel.Result> tags = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), tags);
        mViewpager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTagPresenter = new TagPresenter(this);
    }

    @Override
    protected void initDatas() {
        mProgressDialog = ProgressDialog.show(mContext, getResources().getString(R.string.app_name), "加载中，请稍后……");
        mTagPresenter.loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showloadData(TagModel tagModel) {
        mProgressDialog.dismiss();
        if (tagModel == null || tagModel.getResult().size() == 0) {
            Toast.makeText(this, "服务器连接错误，稍后再试......", Toast.LENGTH_SHORT).show();
        } else {
            mPagerAdapter.setTags(tagModel.getResult());
        }
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(mContext, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finishDefault();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTagPresenter.cancalView();
    }
}
