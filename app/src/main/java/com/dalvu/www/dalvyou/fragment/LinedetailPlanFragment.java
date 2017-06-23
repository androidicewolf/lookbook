package com.dalvu.www.dalvyou.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.ArrayList;

/**
 * 线路详情页行程安排
 * Created by user on 2017/6/7.
 */

public class LinedetailPlanFragment extends BaseFragment {

    private StateView fragment_stateview;
    private LinearLayout line_plan_ll;
    private ArrayList items;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.line_plan);
        line_plan_ll = (LinearLayout) fragment_stateview.normal.findViewById(R.id.line_plan_ll);
    }

    @Override
    protected void initData() {
        MyCallBack callBack = new MyCallBack(fragment_stateview) {
            @Override
            public void onSucceed(int what, String json) {
                //解析数据

                for (int i = 0; i < 4; i++) {
                    View view = LayoutInflater.from(activity).inflate(R.layout.line_plan_item, null, true);
                    //找到控件设置数据

                    line_plan_ll.addView(view);
                }
                fragment_stateview.showNormal();
            }
        };
        NetUtils.callNet(CustomValue.LINEPLAN, CustomValue.SERVER + "/index.php/Api/index/details", callBack);
    }

    @Override
    public void update() {

    }
}
