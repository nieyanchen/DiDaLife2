package com.example.didalife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;


        public class SplashActivity extends AppCompatActivity {

            protected Handler handler = new Handler ();
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                SysApplication.getInstance().addActivity(this);
                super.onCreate (savedInstanceState);
                setContentView (R.layout.activity_splash);
                handler.postDelayed (new Runnable () {    //匿名内部类  创建线程
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this,MenuPageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity (intent);      //界面转跳
                    }
                },3000);         //第二个参数是停留的时间
            }
        }
