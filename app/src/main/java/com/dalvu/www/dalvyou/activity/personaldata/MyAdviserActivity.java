package com.dalvu.www.dalvyou.activity.personaldata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.MyAdviserAdapter;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyAdviserActivity extends AppCompatActivity {

    @BindView(R.id.myadviser_search_ed)
    EditText myadviserSearchEd;
    @BindView(R.id.myadviser_search_iv)
    ImageView myadviserSearchIv;
    @BindView(R.id.myadviser_search_xrecyclerview)
    XRecyclerView myadviserSearchXrecyclerview;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private StateView activity_stateview;
    private MyCallBack callBack;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        initView();
        initData();
    }

    private void initView() {
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_my_adviser);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        myadviserSearchXrecyclerview.setAdapter(new MyAdviserAdapter(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myadviserSearchXrecyclerview.setLayoutManager(layoutManager);

    }

    private void initData() {
        tv_dalv_title.setText("查找顾问");
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

                    activity_stateview.showNormal();
                }
            };
        }
        NetUtils.callNet(21, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
