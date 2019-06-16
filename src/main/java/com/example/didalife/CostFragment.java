package com.example.didalife;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class CostFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    private List<CostBean> mCostBeanList;
    private  DatabaseHelper mDatabaseHelper;
    private CostListAdapter madapter;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.frame_cost, container, false);
        //显示标题栏
        toolbar=view1.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        CostFragment.this.setSupportActionBar(toolbar);

        mDatabaseHelper = new DatabaseHelper(getActivity()); //fragment 中用getActivity()代替this
        mCostBeanList = new ArrayList<>();
        ListView costList =(ListView)view1.findViewById(R.id.lv_main);
        initCostDate();
        madapter = new CostListAdapter(getActivity(), mCostBeanList);
        costList.setAdapter(madapter);

        floatingActionButton= view1.findViewById(R.id.float_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View viewDialog = inflater.inflate(R.layout.new_cost_data,null);
                final EditText title = (EditText)viewDialog.findViewById(R.id.et_cot_title);
                final EditText money = (EditText)viewDialog.findViewById(R.id.et_cot_money);
                final DatePicker date = viewDialog.findViewById(R.id.dp_cost_date);
                builder.setView(viewDialog);
                builder.setTitle("New Cost");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CostBean costBean = new CostBean();
                        costBean.costTitle = title.getText().toString();
                        costBean.costMoney = money.getText().toString();
                        costBean.costDate = date.getYear() + "-" + (date.getMonth()+ 1)+ "-" +date.getDayOfMonth();
                         mDatabaseHelper.insertCost(costBean);
                         mCostBeanList.add(costBean);
                         madapter.notifyDataSetChanged(); //刷新操作
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
            }
        });
                return view1;

    }


    private void initCostDate() {
       mDatabaseHelper.deleteAllData();
      /*  for (int i=0; i<2;i++) {
            CostBean costBean = new CostBean();
            costBean.costTitle = i+"dddd" ;
            costBean.costDate = "12-12";
            costBean.costMoney = "20";
            mDatabaseHelper.insertCost(costBean);
        }*/
        Cursor cursor = mDatabaseHelper.getAllCostData();
        if (cursor !=null){
            while (cursor.moveToNext()){
                CostBean costBean = new CostBean();
                costBean.costTitle = cursor.getString(cursor.getColumnIndex("cost_title"));
                costBean.costDate = cursor.getString(cursor.getColumnIndex("cost_date"));
                costBean.costMoney = cursor.getString(cursor.getColumnIndex("cost_money"));
                mCostBeanList.add(costBean);
            }
            cursor.close();
        }
    }


    private void setSupportActionBar(Toolbar toolbar) {
    }


}
