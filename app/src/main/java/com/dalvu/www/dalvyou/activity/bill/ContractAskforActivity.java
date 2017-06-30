package com.dalvu.www.dalvyou.activity.bill;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.ContractAskforDataBean;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.function.ChangeNumber;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContractAskforActivity extends BaseNoTitleActivity {

    //发票自取
    private final int BYMYSELF = 1;
    //发票快递
    private final int EXPRESS = 2;
    //运费到付
    private final int GETTEDPAY = 3;
    //运费现付
    private final int NOWPAY = 4;
    @BindView(R.id.bill_contractaskfor_internal_univalent)
    TextView billContractaskforInternalUnivalent;
    @BindView(R.id.bill_contractaskfor_overseas_univalent)
    TextView billContractaskforOverseasUnivalent;
    @BindView(R.id.bill_contractaskfor_entrust_univalent)
    TextView billContractaskforEntrustUnivalent;
    @BindView(R.id.bill_contractaskfor_internal_sub)
    ImageView billContractaskforInternalSub;
    @BindView(R.id.bill_contractaskfor_internal_number)
    TextView billContractaskforInternalNumber;
    @BindView(R.id.bill_contractaskfor_internal_add)
    ImageView billContractaskforInternalAdd;
    @BindView(R.id.bill_contractaskfor_overseas_sub)
    ImageView billContractaskforOverseasSub;
    @BindView(R.id.bill_contractaskfor_overseas_number)
    TextView billContractaskforOverseasNumber;
    @BindView(R.id.bill_contractaskfor_overseas_add)
    ImageView billContractaskforOverseasAdd;
    @BindView(R.id.bill_contractaskfor_entrust_sub)
    ImageView billContractaskforEntrustSub;
    @BindView(R.id.bill_contractaskfor_entrust_number)
    TextView billContractaskforEntrustNumber;
    @BindView(R.id.bill_contractaskfor_entrust_add)
    ImageView billContractaskforEntrustAdd;
    @BindView(R.id.bill_contractaskfor_express_iv)
    ImageView billContractaskforExpressIv;
    @BindView(R.id.bill_contractaskfor_express)
    LinearLayout billContractaskforExpress;
    @BindView(R.id.bill_contractaskfor_bymyself_iv)
    ImageView billContractaskforBymyselfIv;
    @BindView(R.id.bill_contractaskfor_bymyself)
    LinearLayout billContractaskforBymyself;
    @BindView(R.id.bill_contractaskfor_dalvcompanyname)
    TextView billContractaskforDalvcompanyname;
    @BindView(R.id.bill_contractaskfor_dalvtel)
    TextView billContractaskforDalvtel;
    @BindView(R.id.bill_contractaskfor_dalvinfo_ll)
    LinearLayout billContractaskforDalvinfoLl;
    @BindView(R.id.bill_contractaskfor_gettedpay_iv)
    ImageView billContractaskforGettedpayIv;
    @BindView(R.id.bill_contractaskfor_gettedpay)
    LinearLayout billContractaskforGettedpay;
    @BindView(R.id.bill_contractaskfor_nowpay_iv)
    ImageView billContractaskforNowpayIv;
    @BindView(R.id.bill_contractaskfor_nowpay)
    LinearLayout billContractaskforNowpay;
    @BindView(R.id.bill_contractaskfor_inputaddress)
    EditText billContractaskforInputaddress;
    @BindView(R.id.bill_contractaskfor_inputname)
    EditText billContractaskforInputname;
    @BindView(R.id.bill_contractaskfor_inputtel)
    EditText billContractaskforInputtel;
    @BindView(R.id.bill_contractaskfor_express_ll)
    LinearLayout billContractaskforExpressLl;
    @BindView(R.id.bill_contractaskfor_conmmit)
    Button billContractaskforConmmit;
    private String url = "Api/agencyFinance/applyContract";
    private String commitUrl = "Api/agencyFinance/applyContractHandle";
    //获取发票的方式
    private int getMode = EXPRESS;
    //邮寄发票运费的付款方式
    private int payMode = GETTEDPAY;
    private StateView activity_stateview;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;

    private Unbinder unbinder;
    private MyCallBack callBack;
    private int userId;
    private String user_token;
    private HashMap<String, String> commitMap = new HashMap<>();

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
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_contract_askfor);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
    }

    private void initData() {
        tv_dalv_title.setText("合同申请");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initCallBack();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(userId));
        map.put("sign_token", user_token);
        NetUtils.callNet(40, CustomValue.SERVER + url, map, callBack);


        ChangeNumber.addNumber(this, billContractaskforInternalAdd, billContractaskforInternalNumber, 0);
        ChangeNumber.subNumber(this, billContractaskforInternalSub, billContractaskforInternalNumber, 0);
        ChangeNumber.addNumber(this, billContractaskforOverseasAdd, billContractaskforOverseasNumber, 0);
        ChangeNumber.subNumber(this, billContractaskforOverseasSub, billContractaskforOverseasNumber, 0);
        ChangeNumber.addNumber(this, billContractaskforEntrustAdd, billContractaskforEntrustNumber, 0);
        ChangeNumber.subNumber(this, billContractaskforEntrustSub, billContractaskforEntrustNumber, 0);
    }

    @OnClick({R.id.bill_contractaskfor_express, R.id.bill_contractaskfor_bymyself, R.id.bill_contractaskfor_gettedpay, R.id.bill_contractaskfor_nowpay, R.id.bill_contractaskfor_conmmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bill_contractaskfor_express:
                billContractaskforBymyselfIv.setImageResource(R.mipmap.unclickbutton);
                billContractaskforExpressIv.setImageResource(R.mipmap.click_button);
                getMode = EXPRESS;
                billContractaskforExpressLl.setVisibility(View.VISIBLE);
                billContractaskforDalvinfoLl.setVisibility(View.GONE);
                break;
            case R.id.bill_contractaskfor_bymyself:
                billContractaskforExpressIv.setImageResource(R.mipmap.unclickbutton);
                billContractaskforBymyselfIv.setImageResource(R.mipmap.click_button);
                getMode = BYMYSELF;
                billContractaskforExpressLl.setVisibility(View.GONE);
                billContractaskforDalvinfoLl.setVisibility(View.VISIBLE);
                break;
            case R.id.bill_contractaskfor_gettedpay:
                billContractaskforNowpayIv.setImageResource(R.mipmap.unclickbutton);
                billContractaskforGettedpayIv.setImageResource(R.mipmap.click_button);
                payMode = GETTEDPAY;
                break;
            case R.id.bill_contractaskfor_nowpay:
                billContractaskforGettedpayIv.setImageResource(R.mipmap.unclickbutton);
                billContractaskforNowpayIv.setImageResource(R.mipmap.click_button);
                payMode = NOWPAY;
                break;
            case R.id.bill_contractaskfor_conmmit:
                commitMap.clear();
                //判断数据是否合法，向服务器提交
                int internalNumber = Integer.valueOf(billContractaskforInternalNumber.getText().toString());
                int overseasNumber = Integer.valueOf(billContractaskforOverseasNumber.getText().toString());
                int entrustNumber = Integer.valueOf(billContractaskforEntrustNumber.getText().toString());
                if (internalNumber == 0 && overseasNumber == 0 && entrustNumber == 0) {
                    Toast.makeText(this, "至少申请一份合同", Toast.LENGTH_SHORT).show();
                } else {
                    switch (getMode) {
                        case EXPRESS:
                            String inputAddress = billContractaskforInputaddress.getText().toString();
                            String inputName = billContractaskforInputname.getText().toString();
                            String inputTel = billContractaskforInputtel.getText().toString();
                            if (inputAddress.isEmpty()) {
                                Toast.makeText(this, "收件地址不能为空", Toast.LENGTH_SHORT).show();
                                getFocusable(billContractaskforInputaddress);
                                break;
                            } else if (inputName.isEmpty()) {
                                Toast.makeText(this, "收件人姓名不能为空", Toast.LENGTH_SHORT).show();
                                getFocusable(billContractaskforInputname);
                                break;
                            } else if (inputTel.isEmpty()) {
                                Toast.makeText(this, "收件人电话不能为空", Toast.LENGTH_SHORT).show();
                                getFocusable(billContractaskforInputtel);
                                break;
                            }
                            commitMap.put("express_fee", String.valueOf(payMode));
                            commitMap.put("addr", inputAddress);
                            commitMap.put("name", inputName);
                            commitMap.put("phone", inputTel);

                        case BYMYSELF:
                            commitMap.put("uid", String.valueOf(userId));
                            commitMap.put("sign_token", user_token);
                            commitMap.put("request_method", String.valueOf(getMode));
                            commitMap.put("inland", String.valueOf(internalNumber));
                            commitMap.put("outbound", String.valueOf(overseasNumber));
                            commitMap.put("peritem", String.valueOf(entrustNumber));
                            commitMap.put("code", "400");
                            Log.e("call", "合同申请页向服务器提交的数据：=====" + commitMap.toString());
                            //请求服务器
                            NetUtils.callNet(41, CustomValue.SERVER + commitUrl, commitMap, callBack);
                            break;
                    }
                }
                break;
        }
    }

    private void initCallBack() {
        callBack = new MyCallBack(activity_stateview) {
            @Override
            public void onStart(int what) {
                switch (what) {
                    case 40:
                        activity_stateview.showLoading();
                        break;
                    case 41:
                        //显示加载中的图片
                        break;
                }
            }

            @Override
            public void onSucceed(int what, String json) {
                //解析数据
                switch (what) {
                    case 40:
                        ContractAskforDataBean contractAskforDataBean = MyApplication.getGson().fromJson(json, ContractAskforDataBean.class);
                        if (contractAskforDataBean.status.equals("00000")) {
                            billContractaskforInternalUnivalent.setText("每份" + Float.valueOf(contractAskforDataBean.contractFeeInfo.contract_fee) / 100 + "元");
                            billContractaskforOverseasUnivalent.setText("每份" + Float.valueOf(contractAskforDataBean.contractFeeInfo.contract_fee) / 100 + "元");
                            billContractaskforEntrustUnivalent.setText("每份" + Float.valueOf(contractAskforDataBean.contractFeeInfo.contract_fee) / 100 + "元");
                            billContractaskforDalvcompanyname.setText("公司地址： " + contractAskforDataBean.contractFeeInfo.address + "");
                            billContractaskforDalvtel.setText("联系电话： " + contractAskforDataBean.contractFeeInfo.tel + "");
                            activity_stateview.showNormal();
                        } else {
                            Toast.makeText(ContractAskforActivity.this, contractAskforDataBean.msg, Toast.LENGTH_SHORT).show();
                            activity_stateview.showError();
                        }
                        break;
                    case 41:
                        //关闭加载中的动画

                        ServerFeedback serverFeedback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                        if (serverFeedback.status.equals("00000")) {
                            Toast.makeText(ContractAskforActivity.this, "申请提交成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ContractAskforActivity.this, BillItemActivity.class);
                            intent.putExtra("title", "合同记录");
                            intent.putExtra("layoutId", 0);
                            intent.putExtra("position", 8);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ContractAskforActivity.this, serverFeedback.msg, Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onFailed(int what, int code) {
                Toast.makeText(ContractAskforActivity.this, "无法连接服务器，请检查网络后重试", Toast.LENGTH_SHORT).show();
                switch (what) {
                    case 40:
                        activity_stateview.showError();
                        break;
                    case 41:
                        //关闭加载中动画

                        break;
                }
            }
        };
    }

    private void getFocusable(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
