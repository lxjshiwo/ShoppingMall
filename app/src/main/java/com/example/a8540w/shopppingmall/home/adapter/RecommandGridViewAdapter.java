package com.example.a8540w.shopppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.home.bean.ResultBeanData;
import com.example.a8540w.shopppingmall.utils.Constants;

import java.util.List;

/**
 * Created by 8540W on 2018/3/23.
 */

public class RecommandGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.RecommendInfoBean> data;

    public RecommandGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.data = recommend_info;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommand = (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }
        else
        {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到相应数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = data.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + recommendInfoBean.getFigure()).into(viewHolder.iv_recommand);
        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("￥"+recommendInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder
    {
        ImageView iv_recommand;
        TextView tv_name;
        TextView tv_price;
    }
}
