package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LinePictureAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LinePictureDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;

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
    //线路ID
    private String id;
    private String url = "Api/agency/morePics";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
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
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        NetUtils.callNet(1, CustomValue.SERVER + url, map, new MyCallBack(activityStateview) {
            @Override
            public void onSucceed(int what, String json) {
                Log.e("call", "^^^^^^^^^^^^^" + json);
                //解析数据
                LinePictureDataBean linePictureDataBean = MyApplication.getGson().fromJson(json, LinePictureDataBean.class);
                if (linePictureDataBean.status.equals("00000")) {
                    if (linePictureDataBean.picArr != null && linePictureDataBean.picArr.size() > 0) {
                        recyclerView.setAdapter(new LinePictureAdapter(LineDetailPictureActivity.this, linePictureDataBean.picArr));
                        activityStateview.showNormal();
                    } else {
                        activityStateview.showEmpty();
                    }
                } else {
                    Toast.makeText(LineDetailPictureActivity.this, linePictureDataBean.msg, Toast.LENGTH_SHORT).show();
                    activityStateview.showError();
                }
            }
        });
    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {
    }
}
