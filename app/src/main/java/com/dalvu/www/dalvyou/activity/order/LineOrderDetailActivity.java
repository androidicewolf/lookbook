package com.dalvu.www.dalvyou.activity.order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LineOrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.line_orderdetail_item_title)
    TextView lineOrderdetailItemTitle;
    @BindView(R.id.line_orderdetail_item_states)
    TextView lineOrderdetailItemStates;
    @BindView(R.id.orderdetail_item_pic)
    ImageView orderdetailItemPic;
    @BindView(R.id.orderdetail_item_mannumber)
    TextView orderdetailItemMannumber;
    @BindView(R.id.orderdetail_item_childrennumber)
    TextView orderdetailItemChildrennumber;
    @BindView(R.id.orderdetail_item_date)
    TextView orderdetailItemDate;
    @BindView(R.id.orderdetail_item_defaultprice)
    TextView orderdetailItemDefaultprice;
    @BindView(R.id.orderdetail_item_adjustprice)
    TextView orderdetailItemAdjustprice;
    @BindView(R.id.orderdetail_item_finalprice)
    TextView orderdetailItemFinalprice;
    @BindView(R.id.orderdetail_item_remark)
    TextView orderdetailItemRemark;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private StateView activity_stateview;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        initView();
        initData();
    }

    private void initView() {
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        activity_stateview.addNormal(R.layout.line_orderdetail_item);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);

    }

    private void initData() {
        tv_dalv_title.setText("订单详情");
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

                lineOrderdetailItemTitle.setText("火星一年游");
                activity_stateview.showNormal();
            }
        };
        NetUtils.callNet(8, CustomValue.SERVER + "/index.php/Api/index/details", callBack);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
