package com.example.a8540w.shopppingmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.base.BaseFragment;
import com.example.a8540w.shopppingmall.community.fragment.CommunityFragment;
import com.example.a8540w.shopppingmall.home.fragment.HomeFragment;
import com.example.a8540w.shopppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.example.a8540w.shopppingmall.type.fragment.TypeFragment;
import com.example.a8540w.shopppingmall.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    /*
    装多个Fragment的实例集合
     */
    private ArrayList<BaseFragment> fragments;

    private int position = 0;
    /*
        缓存Fragment 或者上次显示的Fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        /*
        初始化Fragment
         */
        initFragment();
        //设置RadioGroup监听
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_type://分类
                        position = 1;
                        break;
                    case R.id.rb_community://社区
                        position = 2;
                        break;
                    case R.id.rb_cart://购物车
                        position = 3;
                        break;
                    case R.id.rb_user://用户中心
                        position = 4;
                        break;

                    default:
                        position = 0;
                        break;

                }

                //根据位置取得不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                /*
                第一参数:上次显示的Fragment,
                第二参数:当前正要显示的Fragment,
                 */
                switchFragment(tempFragment,baseFragment);

            }
        });
        rgMain.check(R.id.rb_home);
    }


    private void initFragment()
    {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position)
    {
       if(fragments != null && fragments.size() >0)
       {
           BaseFragment baseFragment = fragments.get(position);
           return baseFragment;
       }
       return null;
    }

    /*
        切换Fragment
     */
    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment)
    {
        if(tempFragment != nextFragment)
        {
            tempFragment = nextFragment;
            if(nextFragment != null)
            {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded())
                {
                    //隐藏当前Fragment
                    if(fromFragment != null)
                    {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }
                else
                {
                    //隐藏当前fragment
                    if(fromFragment != null)
                    {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }

            }

        }

    }

}
