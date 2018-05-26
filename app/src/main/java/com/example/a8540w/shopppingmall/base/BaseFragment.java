package com.example.a8540w.shopppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 8540W on 2018/3/21.
 * 基类Fragment
 * 软件分包:
 * 首页HomeFragment
 * 分类TypeFragment
 * 发现:CommunityFragment
 * 购物车:ShoppingCartFragment
 * 用户中心:UserFragment
 * 等都继承该类
 */


public abstract class BaseFragment extends Fragment{

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /*
    当Activity被创建的时候回调这个方法
    抽象类:由子类实现,实现不同效果
     */
    public abstract View initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /*
    当子类需要联网请求数据时候可以重写该方法,在该方法中联网请求
     */
    public void initData()
    {

    }
}
