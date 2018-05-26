package com.example.a8540w.shopppingmall.shoppingcart.adapter;

import android.app.usage.ConfigurationStats;
import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.home.bean.GoodsBean;
import com.example.a8540w.shopppingmall.shoppingcart.utils.CartStorage;
import com.example.a8540w.shopppingmall.shoppingcart.view.AddSubView;
import com.example.a8540w.shopppingmall.utils.Constants;

import java.util.List;

/**
 * Created by 8540W on 2018/3/27.
 * 适配器构造方法
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>{


    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox cbAll;

    public ShoppingCartAdapter(Context mContext, List<GoodsBean> goodsBeansList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodsBeansList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        //完成状态下的删除
        this.cbAll = cbAll;
        showTotalPrice();
        //设置点击事件
        setListener();
        //校验是否全选
        checkAll();

    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //1.根据位置找到对应的Bean对象
                GoodsBean goodsBean = datas.get(position);
                //2.设置取反
                goodsBean.setSelected(!goodsBean.isSelected());
                //3.刷新状态
                notifyItemChanged(position);
                //4.重新计算总价格
                showTotalPrice();
            }
        });
        //CheckBox设置点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1.得到状态
                boolean isCheck = checkboxAll.isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(isCheck);
                // 3.设置价格
                showTotalPrice();

            }
        });
        //cbALL设置点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1.得到状态
                boolean isCheck = cbAll.isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(isCheck);

            }
        });
    }

    /*
    设置全选和非全选
     */
    public void checkAll_none(boolean isCheck)
    {
        if(datas != null && datas.size()>0 )
        {
            for(int i = 0;i<datas.size();i++)
            {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyDataSetChanged();
            }
        }


    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:"+getTotalPrice());
    }
    /*
        计算总价格总计
     */
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if(datas != null && datas.size() > 0)
        {
           for(int i = 0;i<datas.size();i++)
           {
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isSelected())
                {
                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber() )* Double.valueOf(goodsBean.getCover_price());
                }
           }

        }
        return totalPrice;
    }

    public void deleteData() {
        if(datas != null && datas.size() > 0)
        {
            for(int i = 0;i<datas.size() ;i++)
            {
                //删除选中的
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isSelected())
                {
                    //内存中移除
                    datas.remove(goodsBean);
                    //列表中移除
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;

                }

            }

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;



        public ViewHolder(View itemView)
        {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView)itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            ddSubView = (AddSubView) itemView.findViewById(R.id.ddSubView);
            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(onItemClickListener != null)
                   {
                       onItemClickListener.onItemClick(getLayoutPosition());
                   }
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //1.根据位置得到相应的Bean对象
        final GoodsBean goodsBean = datas.get(position);
        //2.设置数据
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥"+goodsBean.getCover_price());
        holder.ddSubView.setValue(goodsBean.getNumber());
        holder.ddSubView.setMinValue(1);
        holder.ddSubView.setMaxValue(8);
        //设置商品数量变化的监听
        holder.ddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void OnNumberChange(int value) {
                //1.当前列表内存中
                goodsBean.setNumber(value);
                //2.本地要更新
                CartStorage.getInstance().updateData(goodsBean);
                //3.刷新适配器
                notifyItemChanged(position);
                //4.校验是否全选
                checkAll();
                // 5.再次计算总价格
                showTotalPrice();
            }
        });

    }

    public void checkAll()
    {
        if(datas != null && datas.size()>0 )
        {
            int number = 0;
            for(int i = 0;i<datas.size();i++)
            {
                GoodsBean goodsBean = datas.get(i);
                if(!goodsBean.isSelected())
                {
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }else
                {
                    //选中的
                    number++;
                }

            }
            if(number == datas.size())
            {
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }

        }
        else
        {

            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /*
    点击Item监听者
     */
    public interface OnItemClickListener
    {
        public void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    /*
    设置item的监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
