package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineDestineActivity extends AppCompatActivity {

    @BindView(R.id.line_toolbar)
    Toolbar lineToolbar;
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
