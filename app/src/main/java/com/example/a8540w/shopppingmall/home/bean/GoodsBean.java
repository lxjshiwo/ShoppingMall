package com.example.a8540w.shopppingmall.home.bean;

import java.io.Serializable;

/**
 * Created by 8540W on 2018/3/24.
 * 商品对象
 */

public class GoodsBean implements Serializable {
    //价格
    private String cover_price;
    //图片
    private String figure;
    //名称
    private String name;
    //产品ID
    private String product_id;

    //数量
    private int number = 1;


    /*
    是否被选中
     */
    private boolean isSelected = true;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getCover_price() {
        return cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public String getName() {
        return name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isSelected=" + isSelected +
                '}';
    }
}
