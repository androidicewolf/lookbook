package com.dalvu.www.dalvyou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseNoTitleActivity {

    @BindView(R.id.login_iv_back)
    ImageView loginIvBack;
    @BindView(R.id.login_tv_enroll)
    TextView loginTvEnroll;
    @BindView(R.id.login_et_name)
    EditText loginEtName;
    @BindView(R.id.login_et_password)
    EditText loginEtPassword;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_tv_forgetpassword)
    TextView loginTvForgetpassword;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_tv_dalv)
    TextView loginTvDalv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 加载数据
     **/
    private void initData() {

    }

    /**
     * 初始化界面
     **/
    private void initView() {

    }

    @OnClick({R.id.login_iv_back, R.id.login_tv_enroll, R.id.login_btn_login, R.id.login_tv_forgetpassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv_back:
                //返回按钮
                break;
            case R.id.login_tv_enroll:
                //注册

                break;
            case R.id.login_btn_login:
                //登录

                break;
            case R.id.login_tv_forgetpassword:
                //忘记密码
                break;
        }
    }
}
