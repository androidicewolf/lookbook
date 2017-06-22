package com.dalvu.www.dalvyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusActivity extends BaseNoTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getMyApplication().addActivity(this);
        setContentView(R.layout.activity_status);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    /**
     * 加载数据
     **/
    private void initData() {
        //
    }

    /**
     * 初始化界面
     **/
    private void initView() {
        //
    }

    @OnClick({R.id.status_back, R.id.status_tv_adviser, R.id.status_tv_visitor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.status_back:
                finish();
                break;
            case R.id.status_tv_adviser:
                Intent intentAd = new Intent(this, LoginActivity.class);
                startActivity(intentAd);
                break;
            case R.id.status_tv_visitor:
                Intent intentVi = new Intent(this, VisitorEnrollActivity.class);
                startActivity(intentVi);
                break;
        }
    }
}
