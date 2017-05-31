package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LineChangepriceAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 修改线路价格的界面
 */
public class LineChangepriceActivity extends BaseNoTitleActivity {

    @BindView(R.id.linedetail_changeprice_pic)
    ImageView linedetailChangepricePic;
    @BindView(R.id.linedetail_changeprice_name)
    TextView linedetailChangepriceName;
    @BindView(R.id.linedetail_changeprice_price)
    TextView linedetailChangepricePrice;
    @BindView(R.id.linedetail_changeprice_module)
    TextView linedetailChangepriceModule;
    private StateView activity_stateview;
    private ListView line_changeprice_listview;
    private View view;
    private String uri = CustomValue.SERVER;
    private MyCallBack callBack;
    private Unbinder unbinder;
    private ArrayList items = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        initView();
        initData();
    }

    private void initView() {
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        TextView tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        ImageView iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        tv_dalv_title.setText("线路改价");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activity_stateview.addNormal(R.layout.activity_line_changeprice);
        line_changeprice_listview = (ListView) activity_stateview.normal.findViewById(R.id.line_changeprice_listview);
        view = LayoutInflater.from(this).inflate(R.layout.activity_line_changeprice_header, null, false);
        line_changeprice_listview.addHeaderView(view);
        unbinder = ButterKnife.bind(this, view);
    }

    private void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    Log.e("call", json);
                    linedetailChangepricePic.setImageResource(R.drawable.icon);
                    linedetailChangepriceName.setText("家里蹲五日游");
                    linedetailChangepricePrice.setText("20000");
                    linedetailChangepriceModule.setText("国内游");
                    line_changeprice_listview.setAdapter(new LineChangepriceAdapter(LineChangepriceActivity.this, items));
                    activity_stateview.showNormal();
                }
            };
        }
        Log.e("call", "开始请求网络");
        NetUtils.callNet(3, uri + "/index.php/Api/index/indexMod", callBack);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
