package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.widget.ImageView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineDestineActivity extends BaseNoTitleActivity {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.line_stateview)
    StateView lineStateview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_stateview);
        ButterKnife.bind(this);
        lineStateview.addNormal(R.layout.line_detail_destine);
    }
}
