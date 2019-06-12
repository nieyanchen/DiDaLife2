package com.example.didalife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NewsFragment extends Fragment {
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_news, container, false);
        //显示标题栏
        toolbar=view.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        NewsFragment.this.setSupportActionBar(toolbar);
        return view;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
