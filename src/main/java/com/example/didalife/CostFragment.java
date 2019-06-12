package com.example.didalife;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CostFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    Toolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.frame_cost, container, false);
        //显示标题栏
        toolbar=view1.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");

        CostFragment.this.setSupportActionBar(toolbar);


        floatingActionButton= view1.findViewById(R.id.float_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AddCostActivity.class);
                startActivity(i);
            }
        });
                return view1;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
