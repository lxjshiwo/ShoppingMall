package com.example.a8540w.shopppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.a8540w.shopppingmall.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒钟进入发现页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程执行
                //启动发现页面
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();


            }
        },2000);
    }
}
