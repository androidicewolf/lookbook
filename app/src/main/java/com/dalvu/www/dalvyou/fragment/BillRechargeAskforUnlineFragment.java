package com.dalvu.www.dalvyou.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.bill.BillItemActivity;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.DensityUtils;

import java.util.ArrayList;
import java.util.HashMap;

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
    @BindView(R.id.bill_rechargeaskfor_et_inputremitter)
    EditText billRechargeaskforEtInputremitter;
    @BindView(R.id.bill_rechargeaskfor_unline_remitter_ll)
    LinearLayout billRechargeaskforUnlineRemitterLl;
    @BindView(R.id.bill_rechargeaskfor_unline_et_inputmoney)
    EditText billRechargeaskforUnlineEtInputmoney;
    private Unbinder unbinder;
    private ArrayList<String> items;
    private int number = 1;
    private String url = "Api/agencyFinance/applyTopupHandle";
    private int userId;
    private String user_token;
    private String remitter;
    private String money;

    public BillRechargeAskforUnlineFragment(ArrayList<String> items) {
        this.items = items;
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
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
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size() / 3; i++) {
                View view = LayoutInflater.from(activity).inflate(R.layout.bill_rechargeaskfor_unline_bankitem, null, false);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, DensityUtils.dip2px(activity, 9));
                view.setLayoutParams(params);
                //找到控件，设置账户信息和开户行
                TextView unline_bankitem_name = (TextView) view.findViewById(R.id.unline_bankitem_name);
                unline_bankitem_name.setText(items.get(i * 3));
                TextView unline_bankitem_number = (TextView) view.findViewById(R.id.unline_bankitem_number);
                unline_bankitem_number.setText(items.get(i * 3 + 1));
                TextView unline_bankitem_openingbank = (TextView) view.findViewById(R.id.unline_bankitem_openingbank);
                unline_bankitem_openingbank.setText(items.get(i * 3 + 2));
                billRechargeaskforUnlineBankinfoLl.addView(view);
            }
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

    @OnClick({R.id.bill_rechargeaskfor_unline_cash, R.id.bill_rechargeaskfor_unline_swingcard, R.id.bill_rechargeaskfor_unline_cheque, R.id.bill_rechargeaskfor_unline_bank, R.id.bill_rechargeaskfor_unline_btn_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bill_rechargeaskfor_unline_cash:
                billRechargeaskforUnlineRemitterLl.setVisibility(View.GONE);
                reset();
                billRechargeaskforUnlineCashIv.setImageResource(R.mipmap.click_button);
                number = 1;
                break;
            case R.id.bill_rechargeaskfor_unline_swingcard:
                billRechargeaskforUnlineRemitterLl.setVisibility(View.GONE);
                reset();
                billRechargeaskforUnlineSwingcardIv.setImageResource(R.mipmap.click_button);
                number = 2;
                break;
            case R.id.bill_rechargeaskfor_unline_cheque:
                billRechargeaskforUnlineRemitterLl.setVisibility(View.VISIBLE);
                reset();
                billRechargeaskforUnlineChequeIv.setImageResource(R.mipmap.click_button);
                number = 3;
                break;
            case R.id.bill_rechargeaskfor_unline_bank:
                reset();
                billRechargeaskforUnlineRemitterLl.setVisibility(View.VISIBLE);
                billRechargeaskforUnlineBankinfoLl.setVisibility(View.VISIBLE);
                billRechargeaskforUnlineBankIv.setImageResource(R.mipmap.click_button);
                number = 4;
                break;
            case R.id.bill_rechargeaskfor_unline_btn_comment:
                //提交按钮
                //获取输入的汇款方
                remitter = billRechargeaskforEtInputremitter.getText().toString();
                money = billRechargeaskforUnlineEtInputmoney.getText().toString();
                if (number == 4) {
                    if (remitter.isEmpty()) {
                        Toast.makeText(activity, "汇款方名称不能为空", Toast.LENGTH_SHORT).show();
                    } else if (money.isEmpty()) {
                        Toast.makeText(activity, "金额不能为空", Toast.LENGTH_SHORT).show();
                    } else if (Integer.valueOf(money) <= 0) {
                        Toast.makeText(activity, "请输入大于0的金额数", Toast.LENGTH_SHORT).show();
                    } else {
                        requestServer();
                    }
                } else {
                    if (money.isEmpty()) {
                        Toast.makeText(activity, "金额不能为空", Toast.LENGTH_SHORT).show();
                    } else if (Integer.valueOf(money) <= 0) {
                        Toast.makeText(activity, "请输入大于0的金额数", Toast.LENGTH_SHORT).show();
                    } else {
                        requestServer();
                    }
                }
                break;
        }
    }

    private void requestServer() {
        //请求服务器
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "" + userId);
        map.put("sign_token", user_token);
        map.put("topup_type", "" + number);
        map.put("payer", remitter);
        map.put("amount", money);
        NetUtils.callNet(33, CustomValue.SERVER + url, map, new MyCallBack() {
            @Override
            public void onStart(int what) {
                //显示加载中的图片
            }

            @Override
            public void onSucceed(int what, String json) {
                ServerFeedback serverFeedback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                if (serverFeedback.status.equals("00000")) {
                    Toast.makeText(activity, "申请提交成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, BillItemActivity.class);
                    intent.putExtra("title", "充值记录");
                    intent.putExtra("layoutId", 0);
                    intent.putExtra("position", 2);
                    startActivity(intent);
                    activity.finish();
                } else {
                    Toast.makeText(activity, serverFeedback.msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, int code) {
                //请求网络出错
            }
        });
    }

    public void reset() {
        billRechargeaskforUnlineCashIv.setImageResource(R.mipmap.unclickbutton);
        billRechargeaskforUnlineSwingcardIv.setImageResource(R.mipmap.unclickbutton);
        billRechargeaskforUnlineChequeIv.setImageResource(R.mipmap.unclickbutton);
        billRechargeaskforUnlineBankIv.setImageResource(R.mipmap.unclickbutton);
        billRechargeaskforUnlineBankinfoLl.setVisibility(View.GONE);
    }
}
