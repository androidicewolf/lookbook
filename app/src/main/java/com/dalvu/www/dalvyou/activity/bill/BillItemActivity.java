package com.dalvu.www.dalvyou.activity.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.BillItemXRecyclerItemAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class BillItemActivity extends BaseNoTitleActivity {
    //页面标题
    private String title;
    //页面布局文件的地址
    private int layoutId;
    private int position;
    private MyCallBack callBack;
    private StateView activity_stateview;
    private XRecyclerView bill_item_activity_xrecyclerview;
    private ArrayList items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        layoutId = intent.getIntExtra("layoutId", 0);
        position = intent.getIntExtra("position", 0);
        Log.e("call", "从上一个界面传过来的布局文件的ID是：" + layoutId);
        setContentView(R.layout.activity_stateview);
        initView();
        initData();
    }

    private void initView() {
        TextView tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        tv_dalv_title.setText(title);
        ImageView iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        if (layoutId != 0) {
            activity_stateview.addNormal(layoutId);
        } else {
            activity_stateview.addNormal(R.layout.bill_item_activity_xrecyclerview);
            bill_item_activity_xrecyclerview = (XRecyclerView) activity_stateview.normal.findViewById(R.id.bill_item_activity_xrecyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
            bill_item_activity_xrecyclerview.setLayoutManager(layoutManager);
        }
    }

    private void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据

                    bill_item_activity_xrecyclerview.setAdapter(new BillItemXRecyclerItemAdapter(BillItemActivity.this, items, position));
                    activity_stateview.showNormal();
                }
            };
        }
        NetUtils.callNet(10, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }
}
