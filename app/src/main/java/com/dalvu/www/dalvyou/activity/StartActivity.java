package com.dalvu.www.dalvyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.SharedPreferencesTool;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        update();

    }

    private void update() {
        if(true){
            final boolean isFirst = SharedPreferencesTool.getBoolean(this, CustomValue.isFirst, false);
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Intent intent = null;
                    if(isFirst){
                        intent = new Intent(getApplicationContext(), SplashActivity.class);
                        startActivity(intent);
                    }else{
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            };
            handler.sendEmptyMessageDelayed(0,3000);
        }
    }
}
