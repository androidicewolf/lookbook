package com.dalvu.www.dalvyou.activity.personaldata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.customView.CustomDialog;
import com.dalvu.www.dalvyou.tools.CleanBufferMemoryUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalSettingActivity extends BaseNoTitleActivity {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.tv_dalv_title)
    TextView tvDalvTitle;
    @BindView(R.id.personal_setting_propose)
    LinearLayout personalSettingPropose;
    @BindView(R.id.personal_setting_aboutdalv)
    LinearLayout personalSettingAboutdalv;
    @BindView(R.id.personal_setting_cleanbuffer)
    LinearLayout personalSettingCleanbuffer;
    @BindView(R.id.personal_setting_quit)
    TextView personalSettingQuit;
    @BindView(R.id.personal_setting_cleanbuffer_size)
    TextView personalSettingCleanbufferSize;
    private Handler handler;
    private String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
    }

    private void initData() {
        tvDalvTitle.setText("通用设置");
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            personalSettingCleanbufferSize.setText(CleanBufferMemoryUtils.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            personalSettingCleanbufferSize.setText("0K");
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_go_back, R.id.personal_setting_propose, R.id.personal_setting_aboutdalv, R.id.personal_setting_cleanbuffer, R.id.personal_setting_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.personal_setting_propose:
                //意见建议
                Intent intent = new Intent(this, PersonalProposeActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_setting_aboutdalv:
                //关于大旅

                break;
            case R.id.personal_setting_cleanbuffer:

                //清除缓存
                try {
                    CleanBufferMemoryUtils.clearAllCache(getApplicationContext());
                    size = CleanBufferMemoryUtils.getTotalCacheSize(getApplicationContext());
                } catch (Exception e) {
                    Toast.makeText(this, "计算缓存出现异常", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                personalSettingCleanbufferSize.setText("清理中......");
                if (handler == null) {
                    handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            personalSettingCleanbufferSize.setText(size);
                            Toast.makeText(getApplicationContext(), "缓存已经清理完成", Toast.LENGTH_SHORT).show();
                        }
                    };
                }
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                break;
            case R.id.personal_setting_quit:
                //退出登录的操作
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setMessage("是否要退出当前账号");
                //取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //确认按钮
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PersonalSettingActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
        }
    }

    @Override
    protected void onStop() {
        if (handler != null) {
            handler.removeMessages(0);
        }
        super.onStop();
    }
}
