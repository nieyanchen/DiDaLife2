package com.example.didalife;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static android.support.constraint.Constraints.TAG;


public class CostFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    private List<CostBean> mCostBeanList;
    private  DatabaseHelper mDatabaseHelper;
    private CostListAdapter madapter;
    private static final int msgKey1 = 1;
    private TextView mTime;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.frame_cost, container, false);
        //显示标题栏
        toolbar=view1.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        CostFragment.this.setSupportActionBar(toolbar);

        mTime = view1.findViewById(R.id.mytime);
        new TimeThread().start();

        mDatabaseHelper = new DatabaseHelper(getActivity()); //fragment 中用getActivity()代替this
        mCostBeanList = new ArrayList<>();
        final ListView costList =(ListView)view1.findViewById(R.id.lv_main);
        initCostDate();
        madapter = new CostListAdapter(getActivity(), mCostBeanList);
        costList.setAdapter(madapter);
        costList.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.i(TAG, "onItemLongClick: 长按列表项position"+position);
                //删除操作
                //listItem.remove(position);
                //listItemAdapter.notifyDataSetChanged();
                //构造对话框，确认操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("请确认是否删除当前数据");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: 对话框事件处理");
                        mCostBeanList.remove(position);

                        madapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("否", null);
                builder.create().show();
                Log.i(TAG, "onItemLongClick: size= "+mCostBeanList.size());
                return true;
            }
        });




        TextView textView = view1.findViewById(R.id.imageView3);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ChartsActivity.class);
                i.putExtra("cost_list",(Serializable) mCostBeanList);
                startActivity(i);
            }
        });

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
                builder.setTitle("添加账单");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
                builder.setNegativeButton("取消",null);
                builder.create().show();
            }
        });
                return view1;

    }



    private void initCostDate() {
        //  mDatabaseHelper.deleteAllData();
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
    public class TimeThread extends Thread {
        @Override
        public void run () {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(true);
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    mTime.setText(getTime());
                    break;
                default:
                    break;
            }
        }
    };
    //获得当前年月日时分秒星期
    public String getTime(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String mSecond = String.valueOf(c.get(Calendar.SECOND));//秒

        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        return mYear + "年" + mMonth + "月" + mDay+"日"+"  "+"星期"+mWay+"  "+mHour+":"+mMinute+":"+mSecond;
    }

}
