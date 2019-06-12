package com.example.didalife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PageActivity extends AppCompatActivity {

    List<Fragment>fragments;
    TabLayout tabLayout;
    ViewPager viewPager;
    String[]title={"A","B","C","D"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_page);
            tabLayout = findViewById(R.id.tablayout);
            viewPager = findViewById(R.id.viewpager);
            fragments= new ArrayList<>();
            fragments.add(new CostFragment());
            fragments.add(new WeatherFragment());
            fragments.add(new NewsFragment());
            fragments.add(new UserFragment());
            adapter pageAdapter = new adapter(getSupportFragmentManager(),fragments);
            viewPager.setAdapter(pageAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    private class  adapter extends FragmentPagerAdapter {

       private List<Fragment>list;

        public adapter(FragmentManager fm,List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) { ;
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
