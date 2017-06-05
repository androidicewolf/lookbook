package com.dalvu.www.dalvyou.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 财务里线下充值申请的fragment
 * Created by user on 2017/6/5.
 */

public class BillRechargeAskforUnlineFragment extends BaseFragment {
    @BindView(R.id.bill_rechargeaskfor_unline_cash_iv)
    ImageView billRechargeaskforUnlineCashIv;
    @BindView(R.id.bill_rechargeaskfor_unline_cash)
    LinearLayout billRechargeaskforUnlineCash;
    @BindView(R.id.bill_rechargeaskfor_unline_swingcard_iv)
    ImageView billRechargeaskforUnlineSwingcardIv;
    @BindView(R.id.bill_rechargeaskfor_unline_swingcard)
    LinearLayout billRechargeaskforUnlineSwingcard;
    @BindView(R.id.bill_rechargeaskfor_unline_cheque_iv)
    ImageView billRechargeaskforUnlineChequeIv;
    @BindView(R.id.bill_rechargeaskfor_unline_cheque)
    LinearLayout billRechargeaskforUnlineCheque;
    @BindView(R.id.bill_rechargeaskfor_unline_bank_iv)
    ImageView billRechargeaskforUnlineBankIv;
    @BindView(R.id.bill_rechargeaskfor_unline_bank)
    LinearLayout billRechargeaskforUnlineBank;
    @BindView(R.id.bill_rechargeaskfor_unline_bankinfo_ll)
    LinearLayout billRechargeaskforUnlineBankinfoLl;
    private Unbinder unbinder;
    private ArrayList items;

    public BillRechargeAskforUnlineFragment(ArrayList items) {
        this.items = items;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bill_rechargeaskfor_unline_fragment;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        Log.e("call", "--------------------------线下充值的fragment被创建");
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(activity).inflate(R.layout.bill_rechargeaskfor_unline_bankitem, null, false);
            //找到控件，设置账户信息和开户行
            billRechargeaskforUnlineBankinfoLl.addView(view);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick({R.id.bill_rechargeaskfor_unline_cash, R.id.bill_rechargeaskfor_unline_swingcard, R.id.bill_rechargeaskfor_unline_cheque, R.id.bill_rechargeaskfor_unline_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bill_rechargeaskfor_unline_cash:
                reset();
                billRechargeaskforUnlineCashIv.setImageResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bill_rechargeaskfor_unline_swingcard:
                reset();
                billRechargeaskforUnlineSwingcardIv.setImageResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bill_rechargeaskfor_unline_cheque:
                reset();
                billRechargeaskforUnlineChequeIv.setImageResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bill_rechargeaskfor_unline_bank:
                reset();
                billRechargeaskforUnlineBankIv.setImageResource(R.mipmap.ic_launcher_round);
                break;
        }
    }

    public void reset() {
        billRechargeaskforUnlineCashIv.setImageResource(R.mipmap.ic_launcher);
        billRechargeaskforUnlineSwingcardIv.setImageResource(R.mipmap.ic_launcher);
        billRechargeaskforUnlineChequeIv.setImageResource(R.mipmap.ic_launcher);
        billRechargeaskforUnlineBankIv.setImageResource(R.mipmap.ic_launcher);
    }
}
