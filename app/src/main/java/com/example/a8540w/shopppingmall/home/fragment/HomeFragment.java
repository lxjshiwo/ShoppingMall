package com.example.a8540w.shopppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.base.BaseFragment;
import com.example.a8540w.shopppingmall.home.adapter.HomeFragmentAdapter;
import com.example.a8540w.shopppingmall.home.bean.ResultBeanData;
import com.example.a8540w.shopppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * Created by 8540W on 2018/3/21.
 */

public class HomeFragment extends BaseFragment{
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    /*
    返回数据
     */
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;

    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被创建");
        View view = View.inflate(mContext,R.layout.fragment_home,null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        //设置点击事件
        initListener();

        return view;
    }

    private void initListener() {
        //设置置顶监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶端
                rvHome.scrollToPosition(0);
            }
        });
        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
            }
        });
        //消息监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"进入消息中心",Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页面的Fragement的数据被初始化了");
        //联网请求数据
        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    /*
                    当请求是失败时执行
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"请求失败:"+e.getMessage());

                    }

                    /*
                    当联网成功时候回调
                    response 请求成功的数据
                    id 区分http和okhttp
                     */
                    @Override
                    public void onResponse(String response, int id) {
//                        Log.e(TAG,"请求成功:"+response);
                        //解析数据
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
//        Log.e(TAG,"解析成功"+resultBean.getHot_info().get(0).getName());
        if(resultBean != null)
        {
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
            //设置布局管理者
            GridLayoutManager manager = new GridLayoutManager(mContext,1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3)
                    {
                        //当滑动位置小于3的时候
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }
                    else
                    {
                        //当滑动位置超过3的时候
                        //显示
                        ib_top.setVisibility(View.VISIBLE);

                    }
                    //该方法只能返回1
                    return 1;
                }
            });
            rvHome.setLayoutManager(manager);
        }
        else
        {
            //没有数据

        }

    }
}
