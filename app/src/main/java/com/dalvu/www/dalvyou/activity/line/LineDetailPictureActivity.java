package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 展示线路图片的界面
 */
public class LineDetailPictureActivity extends AppCompatActivity {
    @BindView(R.id.line_stateview)
    StateView lineStateview;
    private String uri = "";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_stateview);
        ButterKnife.bind(this);
        initView();
        initData();


    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(manager);
        lineStateview.addNormal(recyclerView);
    }

    private void initData() {
        requestNet();
    }

    private void requestNet() {

        NetUtils.callNet(1, CustomValue.SERVER + uri, new MyCallBack(lineStateview) {
            @Override
            public void onSucceed(int what, String json) {
                Log.e("call", "^^^^^^^^^^^^^" + json);
//                recyclerView.setAdapter(new LinePictureAdapter(this, ));
                lineStateview.showNormal();
            }
        });
    }
}
