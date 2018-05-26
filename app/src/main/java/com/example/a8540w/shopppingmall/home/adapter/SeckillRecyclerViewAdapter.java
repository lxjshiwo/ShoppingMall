package com.example.a8540w.shopppingmall.home.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.home.bean.ResultBeanData;
import com.example.a8540w.shopppingmall.utils.Constants;

import java.util.List;

/**
 * Created by 8540W on 2018/3/23.
 * 秒杀的适配器
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext,R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //1.根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
        //2.绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = (ImageView)itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price= (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext,"秒杀="+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                    if(onSeckRecyclerView != null)
                    {
                        onSeckRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });

        }



    }
    /*
    监听器
     */
    public interface OnSeckRecyclerView
    {
       /*
        当某条被点击的时候回调
        */
        public void onItemClick(int position);

    }

    private OnSeckRecyclerView onSeckRecyclerView;
    /*
    设置监听
     */
    public void setOnSeckRecyclerView(OnSeckRecyclerView onSeckRecyclerView)
    {
        this.onSeckRecyclerView = onSeckRecyclerView;
    }

}
