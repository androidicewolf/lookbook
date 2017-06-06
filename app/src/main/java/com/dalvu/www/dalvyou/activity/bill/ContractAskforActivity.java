package com.dalvu.www.dalvyou.activity.bill;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.function.ChangeNumber;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContractAskforActivity extends BaseNoTitleActivity {

    //发票快递
    private final int EXPRESS = 1;
    //发票自取
    private final int BYMYSELF = 2;
    //运费到付
    private final int GETTEDPAY = 3;
    //运费现付
    private final int NOWPAY = 4;
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

        MyCallBack callBack = new MyCallBack(activity_stateview) {
            @Override
            public void onSucceed(int what, String json) {
                //解析数据

                activity_stateview.showNormal();
            }
        };

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
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
