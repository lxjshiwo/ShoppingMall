package com.example.a8540w.shopppingmall.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.base.BaseFragment;
import com.example.a8540w.shopppingmall.home.bean.GoodsBean;
import com.example.a8540w.shopppingmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.example.a8540w.shopppingmall.shoppingcart.utils.CartStorage;

import java.util.List;


/**
 * Created by 8540W on 2018/3/21.
 */

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private TextView tv_shopcart_edit;
    private CheckBox cbAll;

    private ShoppingCartAdapter adapter;


    //编辑状态与完成状态改变
    private static final int ACTION_EDIT = 1;
    private static final int ACTION_COMPLETE = 2;














    @Override
    public View initView() {
        Log.e(TAG,"购物页面的Fragment的UI被创建");
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        llDelete = (LinearLayout)view.findViewById( R.id.ll_delete );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );
        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );
        tv_shopcart_edit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        cbAll = (CheckBox)view.findViewById(R.id.cb_all);


        ll_empty_shopcart = (LinearLayout) view.findViewById(R.id.ll_empty_shopcart);

        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );


        initListener();

        return view;
    }

    private void initListener() {
        //设置默认的编辑状态
        tv_shopcart_edit.setTag(ACTION_EDIT);
        tv_shopcart_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if(action == ACTION_EDIT)
                {
                    //切换为完成状态
                    showDelete();
                }
                else
                {
                    //切换为编辑状态
                    hideDelete();

                }

            }
        });
    }

    private void hideDelete()
    {
        //1.设置状态和文本-完成
        tv_shopcart_edit.setTag(ACTION_EDIT);
        tv_shopcart_edit.setText("完成");
        //2.变成非勾选
        if(adapter != null)
        {
            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
        //3.删除视图显示
        llDelete.setVisibility(View.GONE);
        //4.结算师视图隐藏
        llCheckAll.setVisibility(View.VISIBLE);
    }

    private void showDelete()
    {
        //1.设置状态和文本- 编辑
        tv_shopcart_edit.setTag(ACTION_COMPLETE);
        tv_shopcart_edit.setText("编辑");
        //2.变成非勾选
        if(adapter != null)
        {
            adapter.checkAll_none(false);
            adapter.checkAll();
        }
        //3.删除视图隐藏
        llDelete.setVisibility(View.VISIBLE);
        //4.结算师视图显示
        llCheckAll.setVisibility(View.GONE);

    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"购物页面的Fragement的数据被初始化了");

        showData();

    }

    /*
    数据的展现
     */
    private void showData() {
        List<GoodsBean> goodsBeansList = CartStorage.getInstance().getAllData();
        if(goodsBeansList != null && goodsBeansList.size() >0)
        {
            //有数据
            //吧当没有数据显示的布局隐藏
            tv_shopcart_edit.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.VISIBLE);
            ll_empty_shopcart.setVisibility(View.GONE);
            //设置适配器
            adapter = new ShoppingCartAdapter(mContext,goodsBeansList,tvShopcartTotal,checkboxAll,cbAll);
            recyclerview.setAdapter(adapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }

        else
        {
            //没有数据显示数据为空的布局
            emptyShoppingCart();

        }
    }

    private void emptyShoppingCart() {
        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tv_shopcart_edit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
            //删除选中的
            adapter.deleteData();
            //校验状态
            adapter.checkAll();
            //数据大小为0
            if(adapter.getItemCount() == 0)
            {
                emptyShoppingCart();
            }
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }
}
