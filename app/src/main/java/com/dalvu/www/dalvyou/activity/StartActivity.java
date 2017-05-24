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
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.SharedPreferencesTool;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class StartActivity extends Activity {
    private String uri = "/";
    private boolean isGoin;
    private int statusType = 0;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        login();
        update();
    }

    private void login() {
        //从本地取出账号密码
        String name = SharedPreferencesTool.getString(this, CustomValue.NAME, null);
        String password = SharedPreferencesTool.getString(this, CustomValue.PASSWORD, null);
        //如果两者都不为空，请求网络登录，如果有空数据则直接进入默认首页
        if (name != null && password != null) {
            Request<String> request = NoHttp.createStringRequest(CustomValue.SERVER + uri, RequestMethod.POST);
            request.add("", name);
            request.add("", password);
            MyApplication.getRequestQueue().add(0, request, new OnResponseListener<String>() {
                @Override
                public void onStart(int what) {
                    Log.e("call", "网路请求发送出去");
                }

                @Override
                public void onSucceed(int what, Response<String> response) {
                    Log.e("call", "解析获取的数据");
                    String json = response.get();
                    statusType = 0;
                    startNext(statusType);
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
            });
        } else {
            isGoin = true;
        }
    }

    private void startNext(int type) {
        if (isGoin) {
            Intent intent;
            if (isFirst) {
                intent = new Intent(getApplicationContext(), SplashActivity.class);
                intent.putExtra(CustomValue.STATUSTYPE, type);
                startActivity(intent);
            } else {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(CustomValue.STATUSTYPE, type);
                startActivity(intent);
            }
            finish();
        } else {
            isGoin = true;
        }
    }

    private void update() {
        Log.e("call", "界面开始读秒");
        isFirst = SharedPreferencesTool.getBoolean(this, CustomValue.ISFIRST, false);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                startNext(statusType);
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
