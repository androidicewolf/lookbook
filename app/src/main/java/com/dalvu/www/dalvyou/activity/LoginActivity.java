package com.dalvu.www.dalvyou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dalvu.www.dalvyou.R;

public class LoginActivity extends AppCompatActivity {

    private ImageView login_portrait;
    private EditText login_et_name;
    private EditText login_et_password;
    private Button login_btn_login;
    private Button login_btn_enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }
    /**加载数据**/
    private void initData() {
//        login_portrait.setImageResource();
    }

    /**初始化界面**/
    private void initView() {
        login_portrait = (ImageView) findViewById(R.id.login_portrait);
        login_et_name = (EditText) findViewById(R.id.login_et_name);
        login_et_password = (EditText) findViewById(R.id.login_et_password);
        login_btn_login = (Button) findViewById(R.id.login_btn_login);
        login_btn_enroll = (Button) findViewById(R.id.login_btn_enroll);
    }
}
