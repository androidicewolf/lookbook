package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LineChangepriceAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LineChangePriceDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;

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
    private MyCallBack callBack;
    private Unbinder unbinder;
    private String url = "Api/agency/changePrice";
    private int user_id;
    private String user_token;
    private String linetitleid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        user_id = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        linetitleid = getIntent().getStringExtra("linetitleid");
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
                    Log.e("call", "线路改价页面返回的服务器数据：=======" + json);
                    LineChangePriceDataBean lineChangePriceDataBean = MyApplication.getGson().fromJson(json, LineChangePriceDataBean.class);
                    if (lineChangePriceDataBean.status.equals("00000")) {
                        Glide.with(LineChangepriceActivity.this).load(lineChangePriceDataBean.tour_list.cover_pic).into(linedetailChangepricePic);
                        linedetailChangepriceName.setText(lineChangePriceDataBean.tour_list.name);
                        linedetailChangepricePrice.setText("" + Float.valueOf(lineChangePriceDataBean.tour_list.min_price) / 100);
                        linedetailChangepriceModule.setText(lineChangePriceDataBean.tour_list.lineTypeName);

                        line_changeprice_listview.setAdapter(new LineChangepriceAdapter(LineChangepriceActivity.this, lineChangePriceDataBean.list));
                        activity_stateview.showNormal();
                    } else {
                        Toast.makeText(LineChangepriceActivity.this, lineChangePriceDataBean.msg, Toast.LENGTH_SHORT).show();
                        activity_stateview.showError();
                    }
                }
            };
        }
        Log.e("call", "开始请求网络");
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "" + user_id);
        map.put("sign_token", user_token);
        map.put("id", linetitleid);
        NetUtils.callNet(25, CustomValue.SERVER + url, map, callBack);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
