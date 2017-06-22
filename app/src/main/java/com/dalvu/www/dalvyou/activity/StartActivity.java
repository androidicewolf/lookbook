package com.dalvu.www.dalvyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.bean.UserInfoBean;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.SharedPreferencesTool;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class StartActivity extends Activity {
    //顾问的接口
    private String uriAd = "Api/agencyPersonal/index";
    //C客户的接口
    private String uriVi = "TouristApi/TouristPersonal/index";
    private boolean isGoin;
    private boolean isFirst;
    private Handler handler;
    private Request<String> request;

    //界面结束时想要执行的意图
    private Intent nextIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //从本地获取是否是第一次登陆
        isFirst = SharedPreferencesTool.getBoolean(this, CustomValue.ISFIRST, true);
        login();
    }

    @Override
    protected void onStart() {
        update();
        super.onStart();
    }

    private void login() {
        //从本地取出账号的token
        final String uid = SharedPreferencesTool.getString(this, CustomValue.UID, null);
        String token = SharedPreferencesTool.getString(this, CustomValue.TOKEN, null);
        String user_type = SharedPreferencesTool.getString(this, CustomValue.TYPE, "0");
        Log.e("call", "从本地取出来的用户token======" + token);
        //如果三者都不为空，请求网络登录，如果有空数据则直接进入默认首页
        if (uid != null && token != null && user_type != null) {
            if (!uid.isEmpty() && !token.isEmpty() && !user_type.isEmpty()) {
                Log.e("call", "-----------从本地文件获取数据");
                OnResponseListener<String> responseListener = new OnResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        Log.e("call", "网路请求发送出去");
                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        Log.e("call", "解析获取的数据");
                        switch (what) {
                            case 0:
                                //顾问
                                String jsonAd = response.get();
                                UserInfoBean userInfoBeanAd = new Gson().fromJson(jsonAd, UserInfoBean.class);
                                Log.e("call", "------------返回码" + userInfoBeanAd.status);
                                if (userInfoBeanAd.status.equals("00005")) {
                                    Toast.makeText(StartActivity.this, "服务器繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (userInfoBeanAd.status.equals("00000")) {
                                        Log.e("call", "进入到了成功的界面中");
                                        AppUserDate.setUserId(Integer.valueOf(uid));
                                        AppUserDate.setUserType(4);
                                        AppUserDate.setUserToken(userInfoBeanAd.sign_token);
                                        nextIntent = new Intent(StartActivity.this, MainActivity.class);
                                    } else {
                                        //登录失败，进入选择身份页面
                                        Log.e("call", "身份验证失败，进入选择身份页面进行登录");
                                        nextIntent = new Intent(StartActivity.this, StatusActivity.class);
                                    }
                                    startNext();
                                }

                                break;
                            case 1:
                                //C客户
                                String jsonVi = response.get();
                                UserInfoBean userInfoBeanVi = new Gson().fromJson(jsonVi, UserInfoBean.class);
                                Log.e("call", "------------返回码" + userInfoBeanVi.status);
                                if (userInfoBeanVi.status.equals("00005")) {
                                    Toast.makeText(StartActivity.this, "服务器繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (userInfoBeanVi.status.equals("00000")) {
                                        Log.e("call", "进入到了成功的界面中");
                                        AppUserDate.setUserId(Integer.valueOf(uid));
                                        AppUserDate.setUserType(5);
                                        AppUserDate.setUserToken(userInfoBeanVi.sign_token);
                                        nextIntent = new Intent(StartActivity.this, MainActivity.class);
                                    } else {
                                        //登录失败，进入选择身份页面
                                        Log.e("call", "身份验证失败，进入选择身份页面进行登录");
                                        nextIntent = new Intent(StartActivity.this, StatusActivity.class);
                                    }
                                    startNext();
                                }
                                break;
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        isGoin = true;
                        Toast.makeText(StartActivity.this, "您的网络连接出现异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish(int what) {
                        Log.e("call", "网络请求已经结束");
                    }
                };
                if (user_type.equals("4")) {
                    request = NoHttp.createStringRequest(CustomValue.SERVER + uriAd, RequestMethod.POST);
                    request.add("uid", uid);
                    request.add("sign_token", token);
                    MyApplication.getRequestQueue().add(0, request, responseListener);
                } else if (user_type.equals("5")) {
                    Log.e("call", "进入了C客户的网络请求");
                    request = NoHttp.createStringRequest(CustomValue.SERVER + uriVi, RequestMethod.POST);
                    request.add("uid", uid);
                    request.add("sign_token", token);
                    MyApplication.getRequestQueue().add(1, request, responseListener);
                } else {
                    Log.e("call", "取到了不认识的user_type，既不是4也不是5");
                    isGoin = true;
                }
            }
        } else {
            Log.e("call", "-----------没有从本地文件获取数据");
            isGoin = true;
        }
    }

    private synchronized void startNext() {
        Log.e("call", "-----------开始下一步跳转" + isGoin);
        if (isGoin) {
            if (isFirst) {
                nextIntent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(nextIntent);
            } else {
                startActivity(nextIntent);
            }
            finish();
        } else {
            isGoin = true;
        }
    }

    private void update() {
        Log.e("call", "界面开始读秒");
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    startNext();
                }
            };
        }
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    protected void onStop() {
        Log.e("call", "取消读秒");
        if (handler != null) {
            handler.removeMessages(0);

        }
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        if (request != null) {
            request.cancel();
        }
        super.onDestroy();
    }
}
