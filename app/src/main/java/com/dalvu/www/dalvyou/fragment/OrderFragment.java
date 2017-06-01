package com.dalvu.www.dalvyou.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**订单页面
 * Created by user on 2017/5/9.
 */

public class OrderFragment extends BaseFragment {

    private StateView fragment_stateview;
    private XRecyclerView order_fragment_xrecyclerview;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.order_fragment);
        order_fragment_xrecyclerview = (XRecyclerView) fragment_stateview.normal.findViewById(R.id.order_fragment_xrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        order_fragment_xrecyclerview.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        MyCallBack callBack = new MyCallBack(fragment_stateview) {
            @Override
            public void onSucceed(int what, String json) {
                //解析数据


            }
        };
        NetUtils.callNet(7, "", callBack);
    }

    @Override
    public void update() {

    }
}
