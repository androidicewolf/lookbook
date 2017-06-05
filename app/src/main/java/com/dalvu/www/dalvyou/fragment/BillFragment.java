package com.dalvu.www.dalvyou.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.BillFragmentAdapter;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 资金页面
 * Created by user on 2017/5/9.
 */

public class BillFragment extends BaseFragment {

    @BindView(R.id.bill_fragment_totalsum)
    TextView billFragmentTotalsum;
    @BindView(R.id.bill_fragment_freezeasset)
    TextView billFragmentFreezeasset;
    @BindView(R.id.bill_fragment_usableasset)
    TextView billFragmentUsableasset;
    @BindView(R.id.bill_fragment_recyclerview)
    RecyclerView billFragmentRecyclerview;
    private Unbinder unbinder;
    private StateView fragment_stateview;
    private MyCallBack callBack;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.bill_fragment);
        unbinder = ButterKnife.bind(this, fragment_stateview.normal);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
        billFragmentRecyclerview.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据

                    billFragmentRecyclerview.setAdapter(new BillFragmentAdapter(activity));
                    fragment_stateview.showNormal();
                }
            };
        }
        NetUtils.callNet(9, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }

    @Override
    public void update() {

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
