package com.dalvu.www.dalvyou.activity.personaldata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyVisitorActivity extends BaseNoTitleActivity {

    @BindView(R.id.my_visitor_brkerage)
    TextView myVisitorBrkerage;
    @BindView(R.id.my_visitor_list)
    LinearLayout myVisitorList;
    @BindView(R.id.visitor_airticket_order)
    LinearLayout visitorAirticketOrder;
    @BindView(R.id.visitor_discuss)
    LinearLayout visitorDiscuss;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private Unbinder unbinder;
    private StateView activity_stateview;
    private MyCallBack callBack;
    private String url = "Api/agencyPersonal/index";
    private int userId;
    private String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_my_visitor);
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        initView();
        initData();
    }

    private void initView() {
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);


    }

    private void initData() {
        tv_dalv_title.setText("我的直客");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //请求网络，设置数据

    }

    @OnClick({R.id.my_visitor_list, R.id.visitor_airticket_order, R.id.visitor_discuss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_visitor_list:
                Intent intent = new Intent(this, VisitorListActivity.class);
                startActivity(intent);
                break;
            case R.id.visitor_airticket_order:

                break;
            case R.id.visitor_discuss:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
