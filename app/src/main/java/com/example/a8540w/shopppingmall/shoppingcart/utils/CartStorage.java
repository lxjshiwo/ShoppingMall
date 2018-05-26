package com.example.a8540w.shopppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.a8540w.shopppingmall.app.MyApplication;
import com.example.a8540w.shopppingmall.home.bean.GoodsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8540W on 2018/3/24.
 * 单例模式
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage instance ;
    private final Context mContext;
    //SparseArray的性能优于hashMap
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context)
    {
        this.mContext = context;
        //把之前存储的数据读取
        sparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /*
    从本地读取的数据加入到SparseArray中
     */
    private void listToSparseArray() {
        List<GoodsBean> goodsBeansList = getAllData();
        //把List数据转换成SparseArray
        for (int i =0 ; i < goodsBeansList.size();i++)
        {
            GoodsBean goodsBean = goodsBeansList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }

    /*
    获取本地所有的数据
     */
    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeansList = new ArrayList<>();
        //1.从本地获取
        String json = CacheUtils.getString(mContext, JSON_CART);
        //2.使用Gson转换成列表
        //判断不为空
        if(!TextUtils.isEmpty(json))
        {
            //直接将String类型转换为List
            goodsBeansList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        //3.
        return goodsBeansList;
    }


    /*
    得到购物车实例
     */
    public static CartStorage getInstance()
    {
        if(instance == null)
        {
            instance = new CartStorage(MyApplication.getContext());
        }

        return instance;
    }


    /*
    添加数据
     */
    public void addData(GoodsBean goodsBean)
    {
        //1.添加到内存中SparseArray
        //如果当前数据已经存在，就修改成number递增
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(tempData != null)
        {
            //内存中存在该条数据
            tempData.setNumber(tempData.getNumber()+1);

        }else
        {
            tempData = goodsBean;
            tempData.setNumber(1);
        }
        //同步到内存中
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),tempData);

        //2.同步到本地
        saveLocal();

    }


    /*
    删除数据
    */
    public void deleteData(GoodsBean goodsBean)
    {
        //1.内存中删除
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        //2.把内存的保持到本地
        saveLocal();
    }


    /*
    修改数据
    * */
    public void updateData(GoodsBean goodsBean)
    {
        //1.内存中更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        //2.把内存的同步到本地
        saveLocal();
    }



    /*
    保持数据到本地
     */
    private void saveLocal()
    {
        //1.SparseArray转换成List
        List<GoodsBean> goodsBeansList = sparseToList();
        //2.使用Gsonj把列表转换成String类型
        String json = new Gson().toJson(goodsBeansList);
        //3.把String数据保存
        CacheUtils.saveString(mContext,JSON_CART,json);
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeansList = new ArrayList<>();
        for(int i =0 ;i<sparseArray.size();i++)
        {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeansList.add(goodsBean);
        }
        return goodsBeansList;
    }
}
