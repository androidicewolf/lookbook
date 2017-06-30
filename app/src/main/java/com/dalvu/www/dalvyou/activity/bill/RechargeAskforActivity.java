package com.dalvu.www.dalvyou.activity.bill;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.RechargeAskforDataBean;
import com.dalvu.www.dalvyou.fragment.BillRechargeAskforOnlineFragment;
import com.dalvu.www.dalvyou.fragment.BillRechargeAskforUnlineFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 充值申请页面
 **/
public class RechargeAskforActivity extends BaseNoTitleActivity {

    @BindView(R.id.bill_rechargeaskfor_tablayout)
    TabLayout billRechargeaskforTablayout;
    @BindView(R.id.bill_rechargeaskfor_framelayout)
    FrameLayout billRechargeaskforFramelayout;
    private TextView tv_dalv_title;

    private String url = "Api/agencyFinance/applyTopup";
    private ImageView iv_go_back;
    private Unbinder unbinder;
    private MyCallBack callBack;
    private StateView activity_stateview;
    private TabLayout.OnTabSelectedListener tabSelectedListener;
    private SparseArray<BaseFragment> fragments;
    private ArrayList<String> banks = new ArrayList<>();
    private TabLayout.Tab tab1;
    private int userId;
    private String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        initView();
        initData();
    }

    private void initView() {
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_recharge_askfor);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        tab1 = billRechargeaskforTablayout.newTab().setText("线下充值");
        TabLayout.Tab tab2 = billRechargeaskforTablayout.newTab().setText("线上充值");
        billRechargeaskforTablayout.addTab(tab1);
        billRechargeaskforTablayout.addTab(tab2);
    }

    private void initData() {
        tv_dalv_title.setText("充值申请");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据
                    RechargeAskforDataBean rechargeAskforDataBean = MyApplication.getGson().fromJson(json, RechargeAskforDataBean.class);
                    if (rechargeAskforDataBean.status.equals("00000")) {
                        if (!rechargeAskforDataBean.operatorInfo.topupInfo.isEmpty()) {
                            subString(rechargeAskforDataBean.operatorInfo.topupInfo);
                            initFragment();
                        }
                        if (tabSelectedListener == null) {
                            tabSelectedListener = new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    int position = tab.getPosition();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    if (fragments != null) {
                                        Fragment fragment = fragments.get(position);
                                        if (fragment.isAdded()) {
                                            transaction.show(fragment);
                                        } else {
                                            transaction.remove(fragment).add(R.id.bill_rechargeaskfor_framelayout, fragment);
                                        }
                                    }
                                    transaction.commit();
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {
                                    int position = tab.getPosition();
                                    if (fragments != null) {
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        Fragment fragment = fragments.get(position);
                                        transaction.hide(fragment);
                                        transaction.commit();
                                    }
                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            };
                            billRechargeaskforTablayout.addOnTabSelectedListener(tabSelectedListener);
                            tabSelectedListener.onTabSelected(tab1);
                        }
                        activity_stateview.showNormal();
                    } else {
                        Toast.makeText(RechargeAskforActivity.this, rechargeAskforDataBean.msg, Toast.LENGTH_SHORT).show();
                        activity_stateview.showError();
                    }
                }
            };
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", "" + userId);
            map.put("sign_token", user_token);
            NetUtils.callNet(10, CustomValue.SERVER + url, map, callBack);
        }
    }

    private void subString(String jsonStr) {
        banks.clear();
        String[] jsonArray = jsonStr.split("<br />");
        String[] strArray;
        for (String str : jsonArray) {
            if (!str.isEmpty()) {
                strArray = str.split("：");
                banks.add(strArray[1]);
            }
        }
    }

    private void initFragment() {
        if (fragments == null) {
            fragments = new SparseArray<>();
            fragments.put(0, new BillRechargeAskforUnlineFragment(banks));
            fragments.put(1, new BillRechargeAskforOnlineFragment());
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
