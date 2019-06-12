package com.example.didalife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View btn){
        openConfig();
    }

    private void openConfig(){
        Intent config = new Intent (this,Login2Activity.class);

        startActivityForResult(config,1);
    }
}
