package com.dalvu.www.dalvyou.activity.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.BillItemXRecyclerItemAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.BillRecordDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

public class BillItemActivity extends BaseNoTitleActivity {
    //页面标题
    private String title;
    //页面布局文件的地址
    private int layoutId;
    private int position;
    private MyCallBack callBack;
    private StateView activity_stateview;
    private XRecyclerView bill_item_activity_xrecyclerview;
    private String url;
    private int userId;
    private String user_token;

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
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    BillRecordDataBean billRecordDataBean = MyApplication.getGson().fromJson(json, BillRecordDataBean.class);
                    if (billRecordDataBean.status.equals("00000")) {
                        //返回数据正确，进行解析
                        if (billRecordDataBean.list != null && billRecordDataBean.list.size() > 0) {
                            bill_item_activity_xrecyclerview.setAdapter(new BillItemXRecyclerItemAdapter(BillItemActivity.this,
                                    billRecordDataBean.list, position));
                            activity_stateview.showNormal();
                        } else {
                            activity_stateview.showEmpty();
                        }
                    } else {
                        Toast.makeText(BillItemActivity.this, billRecordDataBean.msg, Toast.LENGTH_SHORT).show();
                        activity_stateview.showError();
                    }
                }
            };
        }
        switch (position) {
            case 0:
                url = "Api/agencyFinance/accountTransaction";
                break;
            case 2:
                url = "Api/agencyFinance/topupList";
                break;
            case 4:
                url = "Api/agencyFinance/withdrawList";
                break;
            case 6:
                url = "Api/agencyFinance/invoiceList";
                break;
            case 8:
                url = "Api/agencyFinance/contractList";
                break;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(userId));
        map.put("sign_token", user_token);
        map.put("page", "1");//分页数据在下拉刷新时传具体参数
        NetUtils.callNet(10, CustomValue.SERVER + url, map, callBack);
    }
}
