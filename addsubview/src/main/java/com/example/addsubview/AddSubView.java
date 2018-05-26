package com.example.addsubview;

import android.content.Context;
import android.media.MediaMetadata;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 8540W on 2018/3/25.
 * 自定义增加删除按钮
 */

public class AddSubView extends LinearLayout implements View.OnClickListener{
    private final Context mContext;
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;
    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //把布局文件 实例化并加载到类中间
        View.inflate(context,R.layout.add_sub_view,this);
        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_value = (TextView) findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_add://加
                addNumber();
                break;
            case R.id.iv_sub://减
                subNumber();
                break;


        }

    }

    /*
        减
     */
    private void subNumber() {
        if(value > minValue)
        {
            value --;
        }
        setValue(value);

        if(onNumberChangeListener != null)
        {
            onNumberChangeListener.OnNumberChange(value);
        }
    }

    /*
    加
     */
    private void addNumber() {

        if(value < maxValue)
        {
            value ++;
        }
        setValue(value);
        if(onNumberChangeListener != null)
        {
            onNumberChangeListener.OnNumberChange(value);
        }
    }


    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getValue() {
        String valueStr =  tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr))
        {
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value + "");
    }

    /*
        当数量发生变化时回调用
     */
    public interface OnNumberChangeListener
    {
        /*
        当数据发生变化时候回调
         */
        public void OnNumberChange(int value);

    }

    private OnNumberChangeListener onNumberChangeListener;
    /*
    设置数量变化的监听
     */
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener)
    {
        this.onNumberChangeListener = onNumberChangeListener;

    }
}

