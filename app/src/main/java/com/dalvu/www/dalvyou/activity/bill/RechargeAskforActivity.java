package com.dalvu.www.dalvyou.activity.bill;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.fragment.BillRechargeAskforOnlineFragment;
import com.dalvu.www.dalvyou.fragment.BillRechargeAskforUnlineFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 充值申请页面
 **/
public class RechargeAskforActivity extends BaseNoTitleActivity {

    @BindView(R.id.bill_rechargeaskfor_tablayout)
    TabLayout billRechargeaskforTablayout;
    @BindView(R.id.bill_rechargeaskfor_framelayout)
    FrameLayout billRechargeaskforFramelayout;
    @BindView(R.id.bill_rechargeaskfor_et_inputremitter)
    EditText billRechargeaskforEtInputremitter;
    @BindView(R.id.bill_rechargeaskfor_unline_remitter_ll)
    LinearLayout billRechargeaskforUnlineRemitterLl;
    @BindView(R.id.bill_rechargeaskfor_et_inputmoney)
    EditText billRechargeaskforEtInputmoney;
    @BindView(R.id.bill_rechargeaskfor_btn_comment)
    Button billRechargeaskforBtnComment;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private Unbinder unbinder;
    private MyCallBack callBack;
    private StateView activity_stateview;
    private TabLayout.OnTabSelectedListener tabSelectedListener;
    private SparseArray<BaseFragment> fragments;
    private ArrayList banks = new ArrayList();
    private TabLayout.Tab tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
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
        initFragment();
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
                }
            };
            NetUtils.callNet(10, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
        }
    }

    private void initFragment() {
        if (fragments == null) {
            fragments = new SparseArray<>();
            fragments.put(0, new BillRechargeAskforUnlineFragment(banks));
            fragments.put(1, new BillRechargeAskforOnlineFragment());
        }
    }

    @OnClick(R.id.bill_rechargeaskfor_btn_comment)
    public void onViewClicked() {
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
