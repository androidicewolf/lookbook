package com.dalvu.www.dalvyou.activity.bill;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.GetCashAskforDataBean;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.customView.CustomDialog;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.StrToNumUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提现申请的界面
 */
public class GetCashAskforActivity extends BaseNoTitleActivity {

    @BindView(R.id.bill_getcashaskfor_accountnumber)
    TextView billGetcashaskforAccountnumber;
    @BindView(R.id.bill_getcashaskfor_totalasset)
    TextView billGetcashaskforTotalasset;
    @BindView(R.id.bill_getcashaskfor_usableasset)
    TextView billGetcashaskforUsableasset;
    @BindView(R.id.bill_getcashaskfor_inputmoney)
    EditText billGetcashaskforInputmoney;
    @BindView(R.id.bill_getcashaskfor_btn_conmmit)
    Button billGetcashaskforBtnConmmit;
    private StateView activity_stateview;
    private Unbinder unbinder;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private String url = "Api/agencyFinance/applyWithdraw";
    private String commitUrl = "Api/agencyFinance/applyWithdrawHandle";
    //账户状态
    private String account_state;
    private String user_token;
    private int userId;
    private double usable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_get_cash_askfor);
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        initView();
        initData();
    }

    private void initView() {
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);

    }

    private void initData() {
        tv_dalv_title.setText("提现申请");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MyCallBack callBack = new MyCallBack(activity_stateview) {
            @Override
            public void onSucceed(int what, String json) {
                //解析数据
                GetCashAskforDataBean getCashAskforDataBean = MyApplication.getGson().fromJson(json, GetCashAskforDataBean.class);
                if (getCashAskforDataBean.status.equals("00000")) {
                    billGetcashaskforTotalasset.setText("" + StrToNumUtils.str2Str(getCashAskforDataBean.agencyInfo.account_balance) + "");
                    usable = Double.valueOf(getCashAskforDataBean.agencyInfo.availableBalance);
                    billGetcashaskforUsableasset.setText("" + StrToNumUtils.str2Str(getCashAskforDataBean.agencyInfo.availableBalance) + "");
                    account_state = getCashAskforDataBean.agencyInfo.account_state;
                    activity_stateview.showNormal();
                } else {
                    Toast.makeText(GetCashAskforActivity.this, getCashAskforDataBean.msg, Toast.LENGTH_SHORT).show();
                    activity_stateview.showError();
                }
            }
        };
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "" + userId);
        map.put("sign_token", user_token);
        NetUtils.callNet(12, CustomValue.SERVER + url, map, callBack);
    }

    @OnClick(R.id.bill_getcashaskfor_btn_conmmit)
    public void onViewClicked() {
        //先判断是不是冻结账户
        if (account_state.equals("1")) {
            //再判断输入的金额是否大于当前余额
            String str = billGetcashaskforInputmoney.getText().toString();
            if (str.isEmpty()) {
                Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
            } else {
                final int money = Integer.valueOf(str);
                if (money <= 0) {
                    Toast.makeText(this, "对不起提现金额必须大于0", Toast.LENGTH_SHORT).show();
                } else {
                    if (money > usable) {
                        Toast.makeText(this, "对不起，您输入的提现金额超过当前的账户余额", Toast.LENGTH_SHORT).show();
                    } else {
                        //确认请求弹窗

                        CustomDialog.Builder builder = new CustomDialog.Builder(this);
                        builder.setMessage("是否提交提现申请？");
                        //取消按钮
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //确认按钮
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //向服务器发请求
                                HashMap<String, String> map = new HashMap<>();
                                map.put("uid", String.valueOf(userId));
                                map.put("sign_token", user_token);
                                map.put("amount", String.valueOf(money));
                                map.put("code", "400");
                                NetUtils.callNet(37, commitUrl, map, new MyCallBack() {
                                    @Override
                                    public void onStart(int what) {
                                        //显示加载中图片
                                    }

                                    @Override
                                    public void onSucceed(int what, String json) {
                                        ServerFeedback serverFeeback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                                        if (serverFeeback.status.equals("00000")) {
                                            Toast.makeText(GetCashAskforActivity.this, "申请提交成功", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(GetCashAskforActivity.this, BillItemActivity.class);
                                            intent.putExtra("title", "提现记录");
                                            intent.putExtra("layoutId", 0);
                                            intent.putExtra("position", 4);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(GetCashAskforActivity.this, serverFeeback.msg, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailed(int what, int code) {
                                        Toast.makeText(GetCashAskforActivity.this, "连接服务器失败，请检查网络后再试", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                }
            }
        } else {
            Toast.makeText(this, "对不起，您的账户已经被冻结，请联系客服", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}