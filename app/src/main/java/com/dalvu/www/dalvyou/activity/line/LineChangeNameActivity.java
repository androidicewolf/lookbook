package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

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
    private String lineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_change_name);
        ButterKnife.bind(this);
        lineName = getIntent().getStringExtra("linename");
        initData();
    }

    private void initData() {
        lineNameEt.setText(lineName);
    }

    @OnClick({R.id.iv_go_back, R.id.line_changename_save_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.line_changename_save_tv:

                break;
        }
    }
}
