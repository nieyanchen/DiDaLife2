package com.example.didalife;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class UserFragment extends Fragment {

    TextInputEditText textInputEditText;
    Button button;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.frame_user, container, false);
        //显示标题栏
        toolbar=view4.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        UserFragment.this.setSupportActionBar(toolbar);
        textInputEditText =view4.findViewById(R.id.textinput);
        button = view4.findViewById(R.id.button5);

        return view4;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
