package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.HomeFragmentAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class LineListActivity extends BaseNoTitleActivity {

    private StateView activity_stateview;
    private MyCallBack callBack;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private String title;
    private View headerView;
    private TextView line_list_headtv;
    private XRecyclerView xRecyclerView;
    private ArrayList items = new ArrayList();
    private HomeFragmentAdapter lineListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        initView();
        initData();
    }

    private void initView() {
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        xRecyclerView = new XRecyclerView(this);
        xRecyclerView.setBackgroundColor(Color.WHITE);
        xRecyclerView.setLayoutParams(layoutParams);
        xRecyclerView.setLayoutManager(layoutManager);
        headerView = View.inflate(this, R.layout.line_list_headview, null);
        line_list_headtv = (TextView) headerView.findViewById(R.id.line_list_headtv);
        xRecyclerView.addHeaderView(headerView);
        activity_stateview.addNormal(xRecyclerView);
    }

    private void initData() {
        tv_dalv_title.setText(title);
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        line_list_headtv.setText(title + "推荐");

        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据

                    if (lineListAdapter == null) {
                        lineListAdapter = new HomeFragmentAdapter(LineListActivity.this, items);
                        xRecyclerView.setAdapter(lineListAdapter);
                    } else {
                        lineListAdapter.notifyDataSetChanged();
                    }
                    activity_stateview.showNormal();
                }
            };
        }

        NetUtils.callNet(17, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }
}
