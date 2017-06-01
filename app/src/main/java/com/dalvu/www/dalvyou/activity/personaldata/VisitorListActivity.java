package com.dalvu.www.dalvyou.activity.personaldata;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.VisitorListAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VisitorListActivity extends BaseNoTitleActivity {

    @BindView(R.id.my_visitor_number)
    TextView myVisitorNumber;
    @BindView(R.id.visitor_list_xrecyclerview)
    XRecyclerView visitorListXrecyclerview;
    private StateView activity_stateview;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private Unbinder unbinder;
    private ArrayList items = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        initView();
        initData();
    }

    private void initView() {
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        activity_stateview.addNormal(R.layout.activity_visitor_list);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        visitorListXrecyclerview.setLayoutManager(layoutManager);
    }

    private void initData() {
        tv_dalv_title.setText("直客列表");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MyCallBack callBack = new MyCallBack(activity_stateview) {
            @Override
            public void onSucceed(int what, String json) {
                //解析服务器的数据

                myVisitorNumber.setText("100");
                visitorListXrecyclerview.setAdapter(new VisitorListAdapter(VisitorListActivity.this, items));
                activity_stateview.showNormal();
            }
        };
        NetUtils.callNet(6, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
