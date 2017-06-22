package com.dalvu.www.dalvyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dalvu.www.dalvyou.R;

public class SplashActivity extends Activity {

    private ImageView splash_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();

    }
    /**加载数据**/
    private void initData() {
        //
    }
    /**初始化界面**/
    private void initView() {
        splash_image = (ImageView) findViewById(R.id.splash_image);
        splash_image.setImageResource(R.mipmap.screenshot);
    }

    public void goInto(View view){
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
        finish();
    }
}
