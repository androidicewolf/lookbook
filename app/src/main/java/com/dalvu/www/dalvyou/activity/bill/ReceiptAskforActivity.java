package com.dalvu.www.dalvyou.activity.bill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.ReceiptAskforDataBean;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.StrToNumUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 发票申请的界面
 */
public class ReceiptAskforActivity extends BaseNoTitleActivity {

    //发票快递
    private final int EXPRESS = 2;
    //发票自取
    private final int BYMYSELF = 1;
    //运费到付
    private final int GETTEDPAY = 3;
    //运费现付
    private final int NOWPAY = 4;
    @BindView(R.id.bill_receiptaskfor_freight)
    TextView billReceiptaskforFreight;
    @BindView(R.id.bill_receiptaskfor_linkremarks)
    EditText billReceiptaskforLinkremarks;
    @BindView(R.id.bill_receiptaskfor_companyname)
    EditText billReceiptaskforCompanyname;
    @BindView(R.id.bill_receiptaskfor_receipttype)
    TextView billReceiptaskforReceipttype;
    @BindView(R.id.bill_receiptaskfor_receipttype_ll)
    LinearLayout billReceiptaskforReceipttypeLl;
    @BindView(R.id.bill_receiptaskfor_companynumber)
    EditText billReceiptaskforCompanynumber;
    @BindView(R.id.bill_receiptaskfor_inputremarks)
    EditText billReceiptaskforInputremarks;
    @BindView(R.id.bill_receiptaskfor_usablelimit)
    TextView billReceiptaskforUsablelimit;
    @BindView(R.id.bill_receiptaskfor_inputmoney)
    EditText billReceiptaskforInputmoney;
    @BindView(R.id.bill_receiptaskfor_express_iv)
    ImageView billReceiptaskforExpressIv;
    @BindView(R.id.bill_receiptaskfor_express)
    LinearLayout billReceiptaskforExpress;
    @BindView(R.id.bill_receiptaskfor_bymyself_iv)
    ImageView billReceiptaskforBymyselfIv;
    @BindView(R.id.bill_receiptaskfor_bymyself)
    LinearLayout billReceiptaskforBymyself;
    @BindView(R.id.bill_receiptaskfor_dalvinfo_ll)
    LinearLayout billReceiptaskforDalvinfoLl;
    @BindView(R.id.bill_receiptaskfor_gettedpay_iv)
    ImageView billReceiptaskforGettedpayIv;
    @BindView(R.id.bill_receiptaskfor_gettedpay)
    LinearLayout billReceiptaskforGettedpay;
    @BindView(R.id.bill_receiptaskfor_nowpay_iv)
    ImageView billReceiptaskforNowpayIv;
    @BindView(R.id.bill_receiptaskfor_nowpay)
    LinearLayout billReceiptaskforNowpay;
    @BindView(R.id.bill_receiptaskfor_inputaddress)
    EditText billReceiptaskforInputaddress;
    @BindView(R.id.bill_receiptaskfor_inputname)
    EditText billReceiptaskforInputname;
    @BindView(R.id.bill_receiptaskfor_inputtel)
    EditText billReceiptaskforInputtel;
    @BindView(R.id.bill_receiptaskfor_express_ll)
    LinearLayout billReceiptaskforExpressLl;
    @BindView(R.id.bill_receiptaskfor_conmmit)
    Button billReceiptaskforConmmit;
    @BindView(R.id.bill_receiptaskfor_dalvcompanyname)
    TextView billReceiptaskforDalvcompanyname;
    @BindView(R.id.bill_receiptaskfor_dalvtel)
    TextView billReceiptaskforDalvtel;
    private String url = "Api/agencyFinance/applyInvoice";
    private String commitUrl = "Api/agencyFinance/applyInvoiceHandle";
    private StateView activity_stateview;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private Unbinder unbinder;
    //获取发票的方式
    private int getMode = EXPRESS;
    //邮寄发票运费的付款方式
    private int payMode = GETTEDPAY;
    private int userId;
    private String user_token;
    private ArrayList<String> detailList = new ArrayList<>();
    //提交给服务器的参数
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
        activity_stateview.addNormal(R.layout.activity_receipt_askfor);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
    }

    private void initData() {
        tv_dalv_title.setText("发票申请");
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
                ReceiptAskforDataBean receiptAskforDataBean = MyApplication.getGson().fromJson(json, ReceiptAskforDataBean.class);
                if (receiptAskforDataBean.status.equals("00000")) {
                    billReceiptaskforUsablelimit.setText("￥" + StrToNumUtils.str2Str(receiptAskforDataBean.lastTotal) + "元");
                    detailList.clear();
                    for (ReceiptAskforDataBean.InvoiceTypeListBean invoiceTypeListBean : receiptAskforDataBean.invoiceTypeList) {
                        detailList.add(invoiceTypeListBean.detail);
                    }
                    billReceiptaskforDalvcompanyname.setText("公司地址： " + receiptAskforDataBean.operatorInfo.address + "");
                    billReceiptaskforDalvtel.setText("联系电话： " + receiptAskforDataBean.operatorInfo.tel + "");
                    billReceiptaskforFreight.setText("(支付" + Integer.valueOf(receiptAskforDataBean.operatorInfo.express_fee) / 100 + "元快递费)");
                    activity_stateview.showNormal();
                } else {
                    Toast.makeText(ReceiptAskforActivity.this, receiptAskforDataBean.msg, Toast.LENGTH_SHORT).show();
                    activity_stateview.showError();
                }
            }
        };
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(userId));
        map.put("sign_token", user_token);
        NetUtils.callNet(38, CustomValue.SERVER + url, map, callBack);
    }

    @OnClick({R.id.bill_receiptaskfor_receipttype_ll, R.id.bill_receiptaskfor_express, R.id.bill_receiptaskfor_bymyself, R.id.bill_receiptaskfor_gettedpay, R.id.bill_receiptaskfor_nowpay, R.id.bill_receiptaskfor_conmmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.bill_receiptaskfor_receipttype_ll:
                //隐藏输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive() && getCurrentFocus() != null) {
                    if (getCurrentFocus().getWindowToken() != null) {
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }

                //选择发票项目
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(ReceiptAskforActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        billReceiptaskforReceipttype.setText(detailList.get(options1));
                    }
                }).build();
                pvOptions.setNPicker(detailList, null, null);
                pvOptions.show();
                break;

            case R.id.bill_receiptaskfor_express:
                billReceiptaskforBymyselfIv.setImageResource(R.mipmap.unclickbutton);
                billReceiptaskforExpressIv.setImageResource(R.mipmap.click_button);
                getMode = EXPRESS;
                billReceiptaskforExpressLl.setVisibility(View.VISIBLE);
                billReceiptaskforDalvinfoLl.setVisibility(View.GONE);
                break;
            case R.id.bill_receiptaskfor_bymyself:
                billReceiptaskforExpressIv.setImageResource(R.mipmap.unclickbutton);
                billReceiptaskforBymyselfIv.setImageResource(R.mipmap.click_button);
                getMode = BYMYSELF;
                billReceiptaskforExpressLl.setVisibility(View.GONE);
                billReceiptaskforDalvinfoLl.setVisibility(View.VISIBLE);
                break;
            case R.id.bill_receiptaskfor_gettedpay:
                billReceiptaskforNowpayIv.setImageResource(R.mipmap.unclickbutton);
                billReceiptaskforGettedpayIv.setImageResource(R.mipmap.click_button);
                payMode = GETTEDPAY;
                break;
            case R.id.bill_receiptaskfor_nowpay:
                billReceiptaskforGettedpayIv.setImageResource(R.mipmap.unclickbutton);
                billReceiptaskforNowpayIv.setImageResource(R.mipmap.click_button);
                payMode = NOWPAY;
                break;
            case R.id.bill_receiptaskfor_conmmit:
                commitMap.clear();
                String companyName = billReceiptaskforCompanyname.getText().toString();
                final String receiptType = billReceiptaskforReceipttype.getText().toString();
                String companyNumber = billReceiptaskforCompanynumber.getText().toString();
                String money = billReceiptaskforInputmoney.getText().toString();
                if (companyName.isEmpty()) {
                    Toast.makeText(this, "发票抬头不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(billReceiptaskforCompanyname);
                } else {
                    if (receiptType.isEmpty()) {
                        Toast.makeText(this, "发票项目不能为空", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        if (companyNumber.isEmpty()) {
                            Toast.makeText(this, "纳税人识别号不能为空", Toast.LENGTH_SHORT).show();
                            getFocusable(billReceiptaskforCompanynumber);
                        } else {
                            if (money.isEmpty()) {
                                Toast.makeText(this, "发票金额不能为空", Toast.LENGTH_SHORT).show();
                                getFocusable(billReceiptaskforInputmoney);
                            } else {
                                switch (getMode) {
                                    case EXPRESS:
                                        //发快递
                                        String linkmanAddress = billReceiptaskforInputaddress.getText().toString();
                                        String linkmanName = billReceiptaskforInputname.getText().toString();
                                        String linkmanTel = billReceiptaskforInputtel.getText().toString();
                                        if (linkmanAddress.isEmpty()) {
                                            Toast.makeText(this, "收件人地址不能为空", Toast.LENGTH_SHORT).show();
                                            getFocusable(billReceiptaskforInputaddress);
                                            break;
                                        } else {
                                            if (linkmanName.isEmpty()) {
                                                Toast.makeText(this, "联系人不能为空", Toast.LENGTH_SHORT).show();
                                                getFocusable(billReceiptaskforInputname);
                                                break;
                                            } else {
                                                if (linkmanTel.isEmpty()) {
                                                    Toast.makeText(this, "联系电话不能为空", Toast.LENGTH_SHORT).show();
                                                    getFocusable(billReceiptaskforInputtel);
                                                    break;
                                                } else {
                                                    //向服务器发送数据
                                                    String linkRemarks = billReceiptaskforLinkremarks.getText().toString();
                                                    commitMap.put("express_fee", String.valueOf(payMode));
                                                    commitMap.put("addr", linkmanAddress);
                                                    commitMap.put("name", linkmanName);
                                                    commitMap.put("phone", linkmanTel);
                                                    commitMap.put("remark", linkRemarks);
                                                    commitMap.put("code", "400");
                                                }
                                            }
                                        }
                                        Log.e("call", "收件人是空的，执行的顺序；**************");
                                    case BYMYSELF:
                                        //发票自取
                                        String receiptRemarks = billReceiptaskforInputremarks.getText().toString();
                                        commitMap.put("request_method", String.valueOf(getMode));
                                        commitMap.put("uid", "" + userId);
                                        commitMap.put("sign_token", user_token);
                                        commitMap.put("title", companyName);
                                        commitMap.put("tax_number", companyNumber);
                                        commitMap.put("detail", receiptType);
                                        commitMap.put("detail_comm", receiptRemarks);
                                        commitMap.put("amount", money);
                                        Log.e("call", "发票申请向服务器提交的参数：======" + commitMap.toString());
                                        NetUtils.callNet(39, CustomValue.SERVER + commitUrl, commitMap, new MyCallBack() {
                                            @Override
                                            public void onStart(int what) {
                                                //显示加载中的图片
                                            }

                                            @Override
                                            public void onFailed(int what, int code) {
                                                Toast.makeText(ReceiptAskforActivity.this, "连接服务器失败，请检查网络后重试", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onSucceed(int what, String json) {
                                                //解析数据
                                                ServerFeedback serverFeedback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                                                if (serverFeedback.status.equals("00000")) {
                                                    //数据正常
                                                    Toast.makeText(ReceiptAskforActivity.this, "申请提交成功", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ReceiptAskforActivity.this, BillItemActivity.class);
                                                    intent.putExtra("title", "发票记录");
                                                    intent.putExtra("layoutId", 0);
                                                    intent.putExtra("position", 6);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Toast.makeText(ReceiptAskforActivity.this, serverFeedback.msg, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        break;
                                }
                            }
                        }
                    }
                }
                break;
        }
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
