package com.dalvu.www.dalvyou.activity.personaldata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalProposeActivity extends BaseNoTitleActivity {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.tv_dalv_title)
    TextView tvDalvTitle;
    @BindView(R.id.personal_setting_propose_et)
    EditText personalSettingProposeEt;
    @BindView(R.id.personal_setting_propose_commit)
    Button personalSettingProposeCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_propose);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tvDalvTitle.setText("意见反馈");
    }

    @OnClick({R.id.iv_go_back, R.id.personal_setting_propose_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.personal_setting_propose_commit:
                String propose = personalSettingProposeEt.getText().toString();
                //向服务器提交建议

                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
