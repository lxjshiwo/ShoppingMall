package com.example.a8540w.shopppingmall.app;

import android.app.Application;
import android.content.Context;


import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 8540W on 2018/3/22.
 * 整个软件
 */

public class MyApplication extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        /*
        初始化OkHttpUtils
         */
        initOkhttpClient();


    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                 .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                 .readTimeout(10000L, TimeUnit.MILLISECONDS)
                 //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
