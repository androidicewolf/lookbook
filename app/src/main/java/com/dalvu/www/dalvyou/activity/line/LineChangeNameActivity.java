package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LineChangeNameActivity extends AppCompatActivity {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.tv_dalv_title)
    TextView tvDalvTitle;
    @BindView(R.id.line_changename_save_tv)
    TextView lineChangenameSaveTv;
    @BindView(R.id.line_name_et)
    EditText lineNameEt;
    @BindView(R.id.line_changename_loading)
    ImageView lineChangenameLoading;
    @BindView(R.id.line_changename_state)
    LinearLayout lineChangenameState;
    private String lineName;
    private int user_id;
    private String user_token;
    private String lineTitleId;
    private String url = "Api/agency/changeTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_change_name);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        lineName = intent.getStringExtra("linename");
        lineTitleId = intent.getStringExtra("linetitleid");
        initData();
    }

    private void initData() {
        user_id = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        Glide.with(LineChangeNameActivity.this).load(R.drawable.before_ask_loading).into(lineChangenameLoading);
        lineNameEt.setText(lineName);
    }

    @OnClick({R.id.iv_go_back, R.id.line_changename_save_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.line_changename_save_tv:
                //保存修改的标题
                String lineChangeName = lineNameEt.getText().toString();
                if (lineChangeName.equals(lineName)) {
                    Toast.makeText(this, "您未做任何修改", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    //向服务器发送新修改的标题
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", "" + user_id);
                    map.put("sign_token", user_token);
                    map.put("shareTitle", lineChangeName);
                    map.put("tour_id", lineTitleId);
                    NetUtils.callNet(24, CustomValue.SERVER + url, map, new MyCallBack() {
                        @Override
                        public void onStart(int what) {
                            lineChangenameState.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailed(int what, int code) {
                            lineChangenameState.setVisibility(View.GONE);
                            Toast.makeText(LineChangeNameActivity.this, "无法连接到服务器，请检查您的网络", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucceed(int what, String json) {
                            ServerFeedback serverFeedback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                            lineChangenameState.setVisibility(View.GONE);
                            if (serverFeedback.status.equals("00000")) {
                                Toast.makeText(LineChangeNameActivity.this, "标题修改成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(LineChangeNameActivity.this, "标题修改失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
        }
    }
}
