package com.yingzi.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yingzi.common.fragment.ShowFragment;
import com.yingzi.common.model.TagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingzi on 2017/5/19.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<TagModel.Result> tags;

    public List<TagModel.Result> getTags() {
        return tags;
    }

    public void setTags(List<TagModel.Result> tags) {
        this.tags = tags;

    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<TagModel.Result> tags) {
        super(fm);
        if (tags != null && tags.size() > 0) {
            this.tags = tags;
        } else {
            this.tags = new ArrayList<>();

        }
    }

    @Override
    public Fragment getItem(int position) {
        return ShowFragment.newInstance(tags.get(position).getCid());
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tags.get(position).getName();
    }
}
