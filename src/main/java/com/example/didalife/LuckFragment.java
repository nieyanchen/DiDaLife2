package com.example.didalife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class LuckFragment extends Fragment {

    private luck mLuckyPan;
    private ImageView mStartBtn;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.frame_luck, container, false);
        //显示标题栏
        toolbar=view4.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        LuckFragment.this.setSupportActionBar(toolbar);

        mLuckyPan = view4.findViewById(R.id.id_luckyPan);
        mStartBtn = view4.findViewById(R.id.id_start_btn);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPan.isStart()){
                    mLuckyPan.luckyStart();
                    mStartBtn.setImageResource(R.drawable.zhen);
                }else{
                    if (!mLuckyPan.isShouldEnd()){
                        mLuckyPan.luckyEnd();
                        mStartBtn.setImageResource(R.drawable.zhen);
                }
                }
            }
        });

        return view4;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
