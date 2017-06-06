package com.dalvu.www.dalvyou.activity.bill;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

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
    private int usable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_get_cash_askfor);
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
                usable = 2000;

                activity_stateview.showNormal();
            }
        };

        NetUtils.callNet(12, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }

    @OnClick(R.id.bill_getcashaskfor_btn_conmmit)
    public void onViewClicked() {
        //先判断输入的金额是否大于当前余额
        String str = billGetcashaskforInputmoney.getText().toString();
        if (str.isEmpty()) {
            Toast.makeText(this, "请先输入转出金额", Toast.LENGTH_SHORT).show();
        } else {
            int money = Integer.valueOf(str);
            if (money <= 0) {
                Toast.makeText(this, "对不起提现金额必须大于0", Toast.LENGTH_SHORT).show();
            } else {
                if (money > usable) {
                    Toast.makeText(this, "对不起，您当前的账户余额不足", Toast.LENGTH_SHORT).show();
                } else {
                    //确认请求弹窗

                    //向服务器发请求

                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
