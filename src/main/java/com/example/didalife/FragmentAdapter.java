package com.example.didalife;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public abstract class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public FragmentAdapter(FragmentManager fm, FragmentPagerAdapter adapter) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}


