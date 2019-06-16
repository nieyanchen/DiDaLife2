package com.example.didalife;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        NavigationView navigationView;
    public Context mContext;
    private List<CostBean> mCostBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navi1);
        navigationView.setItemIconTintList(null);
        mCostBeanList = new ArrayList<>();
        ListView costList =findViewById(R.id.lv_main);
        initCostDate();
        costList.setAdapter(new CostListAdapter(this,mCostBeanList));
    }

    private void initCostDate() {
        for (int i=0; i<6;i++) {
            CostBean costBean = new CostBean();
            costBean.costTitle = "dddd";
            costBean.costDate = "12-12";
            costBean.costMoney = "20";
            mCostBeanList.add(costBean);
        }
    }
}
