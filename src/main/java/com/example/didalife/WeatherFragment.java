package com.example.didalife;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


public class WeatherFragment extends Fragment {
    TextInputEditText textInputEditText;
    Button button;
    WebView webView;
    Toolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.frame_weather, container, false);

        //显示标题栏
        toolbar=view1.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        WeatherFragment.this.setSupportActionBar(toolbar);
        
       textInputEditText =view1.findViewById(R.id.textinput);
       button = view1.findViewById(R.id.button5);
       webView = view1.findViewById(R.id.webview);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               webView.loadUrl(textInputEditText.getText().toString());
           }
       });
        return view1;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
