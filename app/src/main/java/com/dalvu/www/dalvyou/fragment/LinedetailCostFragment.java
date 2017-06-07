package com.dalvu.www.dalvyou.fragment;

import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

/**
 * 线路详情页费用说明
 * Created by user on 2017/6/7.
 */

public class LinedetailCostFragment extends BaseFragment {

    private StateView fragment_stateview;
    private MyCallBack callBack;
    private TextView linecost_includecost;
    private TextView linecost_uncludecost;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.line_cost);
        linecost_includecost = (TextView) fragment_stateview.normal.findViewById(R.id.linecost_includecost);
        linecost_uncludecost = (TextView) fragment_stateview.normal.findViewById(R.id.linecost_uncludecost);
    }

    @Override
    protected void initData() {
        //解析数据
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据

                    //设置文本
                    fragment_stateview.showNormal();
                }
            };
        }
        NetUtils.callNet(CustomValue.LINECOST, CustomValue.SERVER + "/index.php/Api/index/details", callBack);

    }

    @Override
    public void update() {

    }
}
