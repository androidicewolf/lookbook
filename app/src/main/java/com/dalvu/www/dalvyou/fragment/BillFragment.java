package com.dalvu.www.dalvyou.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.BillFragmentAdapter;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.BillFragmentDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.StrToNumUtils;

import java.util.HashMap;

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
    private String url = "Api/agencyFinance/index";
    private int userId;
    private String user_token;

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
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    BillFragmentDataBean billFragmentDataBean = MyApplication.getGson().fromJson(json, BillFragmentDataBean.class);
                    //解析数据
                    if (billFragmentDataBean.status.equals("00000")) {
                        Log.e("call", "**" + billFragmentDataBean.agencyInfo.account_balance + "**" + billFragmentDataBean.agencyInfo.freezeMoney + "**" + billFragmentDataBean.agencyInfo.availableBalance);
                        billFragmentTotalsum.setText("" + StrToNumUtils.str2Str(billFragmentDataBean.agencyInfo.account_balance) + "");
                        billFragmentFreezeasset.setText("" + StrToNumUtils.str2Str(billFragmentDataBean.agencyInfo.freezeMoney) + "");
                        billFragmentUsableasset.setText("" + StrToNumUtils.str2Str(billFragmentDataBean.agencyInfo.availableBalance) + "");
                    } else {
                        Toast.makeText(activity, billFragmentDataBean.msg, Toast.LENGTH_SHORT).show();
                        fragment_stateview.showError();
                    }
                    billFragmentRecyclerview.setAdapter(new BillFragmentAdapter(activity));
                    fragment_stateview.showNormal();
                }
            };
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(userId));
        map.put("sign_token", user_token);
        NetUtils.callNet(9, CustomValue.SERVER + url, map, callBack);
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
