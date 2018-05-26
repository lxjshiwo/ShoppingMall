package com.example.a8540w.shopppingmall.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.a8540w.shopppingmall.base.BaseFragment;


/**
 * Created by 8540W on 2018/3/21.
 */

public class TypeFragment extends BaseFragment{
    private TextView textView;
    private static final String TAG = TypeFragment.class.getSimpleName();


    @Override
    public View initView() {
        Log.e(TAG,"分类页面的Fragment的UI被创建");
        textView = new TextView(mContext);
        textView.setText("我是分类页面内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"分类页面的Fragement的数据被初始化了");
        textView.setText("我是分类页面内容");
    }
}
