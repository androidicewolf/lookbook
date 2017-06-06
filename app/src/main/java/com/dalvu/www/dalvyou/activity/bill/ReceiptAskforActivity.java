package com.dalvu.www.dalvyou.activity.bill;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ReceiptAskforActivity extends BaseNoTitleActivity {

    //发票快递
    private final int EXPRESS = 1;
    //发票自取
    private final int BYMYSELF = 2;
    //运费到付
    private final int GETTEDPAY = 3;
    //运费现付
    private final int NOWPAY = 4;
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
    private StateView activity_stateview;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private Unbinder unbinder;
    //获取发票的方式
    private int getMode = EXPRESS;
    //邮寄发票运费的付款方式
    private int payMode = GETTEDPAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
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


                activity_stateview.showNormal();
            }
        };
    }

    @OnClick({R.id.bill_receiptaskfor_receipttype_ll, R.id.bill_receiptaskfor_express, R.id.bill_receiptaskfor_bymyself, R.id.bill_receiptaskfor_gettedpay, R.id.bill_receiptaskfor_nowpay, R.id.bill_receiptaskfor_conmmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bill_receiptaskfor_receipttype_ll:
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
                String companyName = billReceiptaskforCompanyname.getText().toString();
                String receiptType = billReceiptaskforReceipttype.getText().toString();
                String companyNumber = billReceiptaskforCompanynumber.getText().toString();
                String money = billReceiptaskforInputmoney.getText().toString();
                if (companyName.isEmpty()) {
                    Toast.makeText(this, "发票抬头不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (receiptType.isEmpty()) {
                        Toast.makeText(this, "发票项目不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if (companyNumber.isEmpty()) {
                            Toast.makeText(this, "纳税人识别号不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            if (money.isEmpty()) {
                                Toast.makeText(this, "发票金额不能为空", Toast.LENGTH_SHORT).show();
                            } else {
                                switch (getMode) {
                                    case 1:
                                        //发快递
                                        String linkmanAddress = billReceiptaskforInputaddress.getText().toString();
                                        String linkmanName = billReceiptaskforInputname.getText().toString();
                                        String linkmanTel = billReceiptaskforInputtel.getText().toString();
                                        if (linkmanAddress.isEmpty()) {
                                            Toast.makeText(this, "收件人地址不能为空", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (linkmanName.isEmpty()) {
                                                Toast.makeText(this, "联系人不能为空", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (linkmanTel.isEmpty()) {
                                                    Toast.makeText(this, "联系电话不能为空", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    //向服务器发送数据

                                                }
                                            }
                                        }
                                        break;
                                    case 2:
                                        //发票自取

                                        break;
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
