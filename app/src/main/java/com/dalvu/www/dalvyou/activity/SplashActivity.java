package com.dalvu.www.dalvyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.StatusActivity;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.SharedPreferencesTool;

public class SplashActivity extends Activity {

    private ImageView splash_image;
    private Button splash_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();

    }
    /**加载数据**/
    private void initData() {

    }
    /**初始化界面**/
    private void initView() {
        splash_image = (ImageView) findViewById(R.id.splash_image);
        splash_image.setImageResource(R.mipmap.screenshot);
        splash_go = (Button) findViewById(R.id.splash_go);
    }

    public void goInto(View view){
        SharedPreferencesTool.saveBoolean(this, CustomValue.isFirst, false);
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
    }
}
