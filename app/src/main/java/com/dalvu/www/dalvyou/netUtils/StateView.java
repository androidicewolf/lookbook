package com.dalvu.www.dalvyou.netUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

import butterknife.BindView;

/**
 * 显示网路状态的类
 * Created by user on 2017/5/11.
 */

public class StateView extends FrameLayout {
    public View state_Load;
    public View state_Error;
    public View state_Empty;
    public TextView state_Text;
    public View normal;
    //存描述当前状态的文字
    private String[] stateTexts = {"加载中......", "网络不给力啊", "这里没有数据了"};

    public StateView(Context context) {
        this(context, null);
    }

    public StateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.stateview, null);
        state_Load = view.findViewById(R.id.state_load);
        state_Error = view.findViewById(R.id.state_error);
        state_Empty = view.findViewById(R.id.state_empty);
        state_Text = (TextView) view.findViewById(R.id.state_text);
        this.addView(view);
    }

    private void reset() {
        state_Load.setVisibility(GONE);
        state_Error.setVisibility(GONE);
        state_Empty.setVisibility(GONE);
        if (normal != null) {
            normal.setVisibility(View.GONE);
        }

    }

    /**
     * 显示正在加载中
     **/
    public void showLoading() {
        reset();
        state_Load.setVisibility(VISIBLE);
        state_Text.setVisibility(VISIBLE);
        state_Text.setText(stateTexts[0]);
    }

    /**
     * 显示网络不可用
     **/
    public void showError() {
        reset();
        state_Error.setVisibility(VISIBLE);
        state_Text.setVisibility(VISIBLE);
        state_Text.setText(stateTexts[1]);
    }

    /**
     * 显示空数据
     **/
    public void showEmpty() {
        reset();
        state_Empty.setVisibility(VISIBLE);
        state_Text.setVisibility(VISIBLE);
        state_Text.setText(stateTexts[2]);
    }

    /**添加展示数据的view
     *
     * @param normalViewlayout
     */
    public void addNormal(Object normalViewlayout){
        if(normal == null){
            Log.e("call", "StateView中的normal是null");
            if (normalViewlayout instanceof Integer) {
                normal = LayoutInflater.from(getContext()).inflate((Integer) normalViewlayout, this, false);
            } else {
                normal = (View) normalViewlayout;
            }
            this.addView(normal);
        }
    }

    /**
     * 显示正常的数据，输入的参数可以是xml文件地址，也可以是一个View对象
     */
    public void showNormal() {
        reset();
        state_Text.setVisibility(GONE);
        normal.setVisibility(VISIBLE);
    }
}
