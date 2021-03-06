package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.SearchActivity;
import com.dalvu.www.dalvyou.adapter.LineAssortmentAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**展示线路分类的界面
 *
 */
public class LineAssortmentActivity extends BaseNoTitleActivity {

    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private StateView activity_stateview;
    private String title;
    private XRecyclerView xRecyclerView;
    private View headerView;
    private ImageView line_assortment_headiv;
    private TextView line_assortment_headtv;
    private LinearLayout line_assortment_headsearch_ll;
    private MyCallBack callBack;
    private ArrayList items;
    private LineAssortmentAdapter lineAssortmentAdapter;

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
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        xRecyclerView = new XRecyclerView(this);
        xRecyclerView.setBackgroundColor(0xFFF1F1F1);
        xRecyclerView.setLayoutParams(layoutParams);
        xRecyclerView.setLayoutManager(layoutManager);
        activity_stateview.addNormal(xRecyclerView);
        initHeader();
    }

    private void initData() {
        tv_dalv_title.setText(title);
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据

                    if (lineAssortmentAdapter == null) {
                        xRecyclerView.addHeaderView(headerView);
                        lineAssortmentAdapter = new LineAssortmentAdapter(LineAssortmentActivity.this, items, title);
                        xRecyclerView.setAdapter(lineAssortmentAdapter);
                    } else {
                        lineAssortmentAdapter.notifyDataSetChanged();
                    }
                    //给头布局设置参数
//                    line_assortment_headiv.setImageResource();

                    activity_stateview.showNormal();
                }
            };
        }

        NetUtils.callNet(16, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }

    private void initHeader() {
        headerView = View.inflate(this, R.layout.activity_line_assortment, null);
        line_assortment_headiv = (ImageView) headerView.findViewById(R.id.line_assortment_headiv);
        line_assortment_headtv = (TextView) headerView.findViewById(R.id.line_assortment_headtv);
        line_assortment_headsearch_ll = (LinearLayout) headerView.findViewById(R.id.line_assortment_headsearch_ll);
        line_assortment_headsearch_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LineAssortmentActivity.this, SearchActivity.class);
                intent.putExtra("isShow", false);
                startActivity(intent);
            }
        });
        setHeaderData();
    }

    private void setHeaderData() {
        line_assortment_headtv.setText(title + "推荐");
    }
}
