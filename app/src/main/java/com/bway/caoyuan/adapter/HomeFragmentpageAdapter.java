package com.bway.caoyuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentpageAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    public HomeFragmentpageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;

    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
}