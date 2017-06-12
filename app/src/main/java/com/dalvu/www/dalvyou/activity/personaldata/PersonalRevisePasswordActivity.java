package com.dalvu.www.dalvyou.activity.personaldata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonalRevisePasswordActivity extends AppCompatActivity {

    @BindView(R.id.personal_revise_password_old_ed)
    EditText personalRevisePasswordOldEd;
    @BindView(R.id.personal_revise_password_new_ed)
    EditText personalRevisePasswordNewEd;
    @BindView(R.id.personal_revise_password_newagain_ed)
    EditText personalRevisePasswordNewagainEd;
    private StateView activity_stateview;
    private TextView tv_dalv_title;
    private ImageView iv_go_back;
    private TextView personal_save_tv;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_stateview);
        initView();
        initData();
    }

    private void initView() {
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.activity_personal_revise_password);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        personal_save_tv = (TextView) findViewById(R.id.personal_save_tv);
        activity_stateview.showNormal();
    }

    private void initData() {
        tv_dalv_title.setText("修改密码");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        personal_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //验证密码是否合法，提交服务器

            }
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
