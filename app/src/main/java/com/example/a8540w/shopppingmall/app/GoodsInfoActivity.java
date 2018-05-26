package com.example.a8540w.shopppingmall.app;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a8540w.shopppingmall.R;
import com.example.a8540w.shopppingmall.home.adapter.HomeFragmentAdapter;
import com.example.a8540w.shopppingmall.home.bean.GoodsBean;
import com.example.a8540w.shopppingmall.home.fragment.HomeFragment;
import com.example.a8540w.shopppingmall.shoppingcart.utils.CartStorage;
import com.example.a8540w.shopppingmall.utils.Constants;


public class GoodsInfoActivity extends Activity implements View.OnClickListener{


    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private GoodsBean goodsBean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-03-24 14:21:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {


        tv_more_share = (TextView)findViewById(R.id.tv_more_share);
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);
        btn_more = (Button) findViewById(R.id.btn_more);





        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );

        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener( this );
        tvGoodInfoCollection.setOnClickListener( this );
        tvGoodInfoCart.setOnClickListener( this );


        //设置点击事件
        tv_more_share.setOnClickListener( this );
        tv_more_home.setOnClickListener( this );
        tv_more_search.setOnClickListener( this );
    }


    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-03-24 14:21:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            //返回按键
            finish();
        } else if ( v == ibGoodInfoMore ) {
            //更多
            Toast.makeText(GoodsInfoActivity.this,"more",Toast.LENGTH_SHORT).show();

        } else if ( v == btnGoodInfoAddcart ) {
            CartStorage.getInstance().addData(goodsBean);
            Toast.makeText(GoodsInfoActivity.this,"添加到购物车成功",Toast.LENGTH_SHORT).show();
        } else if(v == tvGoodInfoCallcenter)
        {
            Toast.makeText(GoodsInfoActivity.this,"客户中心",Toast.LENGTH_SHORT).show();
        }else if(v == tvGoodInfoCollection)
        {
            Toast.makeText(GoodsInfoActivity.this,"收藏",Toast.LENGTH_SHORT).show();
        }else if(v == tvGoodInfoCart )
        {
            Toast.makeText(GoodsInfoActivity.this,"购物车",Toast.LENGTH_SHORT).show();
        }else if(v == tv_more_share )
        {
            Toast.makeText(GoodsInfoActivity.this,"分享",Toast.LENGTH_SHORT).show();
        }else if(v == tv_more_search )
        {
            Toast.makeText(GoodsInfoActivity.this,"搜索",Toast.LENGTH_SHORT).show();
        }else if(v == tv_more_home )
        {
            Toast.makeText(GoodsInfoActivity.this,"主页面",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        //接收数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeFragmentAdapter.GOODS_BEAN);
        if(goodsBean != null )
        {
//           Toast.makeText(this,"bean"+goodsBean.getCover_price(),Toast.LENGTH_SHORT).show();
            setDataForView(goodsBean);
        }

    }

    /*
    设置数据
     */
    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(ivGoodInfoImage);
        //设置文本
        tvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        tvGoodInfoPrice.setText("￥"+goodsBean.getCover_price());
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if(product_id != null)
        {
            wbGoodInfoMore.loadUrl("https://baidu.com");
            //设置支持javaScript
            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true);//设置是否支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true);//设置支持javaScript
            //优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wbGoodInfoMore.setWebViewClient(new WebViewClient(){

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去webView打开，false则调用浏览器或者三方浏览器
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }
            });

        }
    }

}
