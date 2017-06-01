package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示线路图片的界面
 */
public class LineDetailPictureActivity extends BaseNoTitleActivity {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.tv_dalv_title)
    TextView tvDalvTitle;
    @BindView(R.id.activity_stateview)
    StateView activityStateview;
    private String uri = "";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(manager);
        activityStateview.addNormal(recyclerView);
    }

    private void initData() {
        ivGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvDalvTitle.setText("目的地相册");
        requestNet();
    }

    private void requestNet() {

        NetUtils.callNet(1, CustomValue.SERVER + uri, new MyCallBack(activityStateview) {
            @Override
            public void onSucceed(int what, String json) {
                Log.e("call", "^^^^^^^^^^^^^" + json);
//                recyclerView.setAdapter(new LinePictureAdapter(this, ));
                activityStateview.showNormal();
            }
        });
    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {
    }
}
