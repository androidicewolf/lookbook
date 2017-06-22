package com.dalvu.www.dalvyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.UserLoginBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.NumberUtils;
import com.dalvu.www.dalvyou.tools.SharedPreferencesTool;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseNoTitleActivity {

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
    private String uri = "login/agencyIndex";

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
                finish();
                break;
            case R.id.login_tv_enroll:
                //注册
                Intent intent = new Intent(this, AdviserEnrollActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn_login:
                //登录
                String tel = loginEtName.getText().toString();
                if (tel.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(loginEtName);//获取焦点
                } else {
                    if (!NumberUtils.isMobileNo(tel)) {
                        Toast.makeText(this, "您的手机号格式不正确", Toast.LENGTH_SHORT).show();
                        getFocusable(loginEtName);
                    } else {
                        String password = loginEtPassword.getText().toString();
                        if (password.isEmpty()) {
                            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                            getFocusable(loginEtPassword);
                        } else {
                            //请求服务器
                            MyCallBack callBack = new MyCallBack() {
                                @Override
                                public void onStart(int what) {
                                    //显示加载中的动图

                                }

                                @Override
                                public void onSucceed(int what, String json) {
                                    UserLoginBean userLoginBean = new Gson().fromJson(json, UserLoginBean.class);
                                    if (userLoginBean.status.equals("00000")) {

                                        //登录成功，将数据存进本地
                                        SharedPreferencesTool.saveBoolean(LoginActivity.this, CustomValue.ISFIRST, false);
                                        SharedPreferencesTool.saveString(LoginActivity.this, CustomValue.UID, userLoginBean.uid);
                                        SharedPreferencesTool.saveString(LoginActivity.this, CustomValue.TYPE, userLoginBean.user_type);
                                        SharedPreferencesTool.saveString(LoginActivity.this, CustomValue.TOKEN, userLoginBean.sign_token);
                                        Log.e("call", "登录成功存储到本地的user_type=========" + userLoginBean.sign_token);

                                        //将用户的基本信息存进内存中
                                        AppUserDate.setUserId(Integer.valueOf(userLoginBean.uid));
                                        AppUserDate.setUserType(Integer.valueOf(userLoginBean.user_type));
                                        AppUserDate.setUserToken(userLoginBean.sign_token);

                                        //进入主界面，主界面的启动模式是singTask
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, userLoginBean.msg, Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailed(int what, int code) {
                                    Toast.makeText(LoginActivity.this, "网路异常，请稍后再试", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFinish(int what) {
                                    //隐藏加载中的动图
                                }
                            };
                            Map<String, String> map = new HashMap<>();
                            map.put("login_name", tel);
                            map.put("login_pwd", password);
                            NetUtils.callNet(0, CustomValue.SERVER + uri, map, callBack);
                        }
                    }
                }
                break;
            case R.id.login_tv_forgetpassword:
                //忘记密码
                break;
        }
    }

    /**
     * EditText获取焦点
     *
     * @param et 想要获取焦点的控件
     */
    private void getFocusable(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
    }
}
