package com.dalvu.www.dalvyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dalvu.www.dalvyou.R;


public class StatusActivity extends Activity {

    private Button tourist;
    private Button agency;
    private Button provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        initView();
        initData();

    }
    /**加载数据**/
    private void initData() {

    }
    /**初始化界面**/
    private void initView() {
        tourist = (Button) findViewById(R.id.status_btn_tourist);
        agency = (Button) findViewById(R.id.status_btn_agency);
        provider = (Button) findViewById(R.id.status_btn_provider);
        MyOnClickListener myOnClick = new MyOnClickListener();
        tourist.setOnClickListener(myOnClick);
        agency.setOnClickListener(myOnClick);
        provider.setOnClickListener(myOnClick);
    }

    private class MyOnClickListener implements View.OnClickListener{
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.status_btn_tourist :
                    startActivity(intent);
                    break;
                case R.id.status_btn_agency :
                    startActivity(intent);
                    break;
                case R.id.status_btn_provider :
                    startActivity(intent);
                    break;
            }
        }
    }
}
