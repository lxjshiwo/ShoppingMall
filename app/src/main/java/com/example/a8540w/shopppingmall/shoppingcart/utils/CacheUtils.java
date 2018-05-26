package com.example.a8540w.shopppingmall.shoppingcart.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 8540W on 2018/3/24.
 * 缓存工具
 */

public class CacheUtils {
    /*
    得到保存的String类型的数据
     */
    public static String getString(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /*
    保存数据
    context 上下文
    key 键
    value 值
     */
    public static void saveString(Context context, String key, String value)
    {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
