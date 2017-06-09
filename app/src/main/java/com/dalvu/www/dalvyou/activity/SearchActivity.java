package com.dalvu.www.dalvyou.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.HomeFragmentAdapter;
import com.dalvu.www.dalvyou.adapter.SearchHotCityAdapter;
import com.dalvu.www.dalvyou.adapter.SearchRecyclerViewAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.DensityUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchActivity extends BaseNoTitleActivity {
    //这个界面的热门城市以后需要用成第三方的流式布局
    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.activity_search_ed)
    EditText activitySearchEd;
    @BindView(R.id.activity_search_close)
    ImageView activitySearchClose;
    @BindView(R.id.activity_search_iv)
    ImageView activitySearchIv;
    @BindView(R.id.search_stateview)
    StateView searchStateview;
    @BindView(R.id.search_recyclerview)
    RecyclerView searchRecyclerview;
    @BindView(R.id.activity_search_hotcity_ll)
    LinearLayout activitySearchHotcityLl;
    @BindView(R.id.search_gridview)
    GridView searchGridview;
    private Unbinder unbinder;
    private MyCallBack callBack;
    private String[] citys = {"北京", "天津", "河北", "唐山"};
    private XRecyclerView xRecyclerView;
    private ArrayList lineitems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        unbinder = ButterKnife.bind(this);
        initData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        xRecyclerView = new XRecyclerView(this);
        xRecyclerView.setLayoutParams(layoutParams);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setPadding(DensityUtils.dip2px(this, 14), DensityUtils.dip2px(this, 13), DensityUtils.dip2px(this, 14), 0);
        searchStateview.addNormal(xRecyclerView);
    }

    private void initData() {
        activitySearchEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("call", "-----------文字改变后");
                if (s.toString().isEmpty()) {
                    activitySearchClose.setVisibility(View.GONE);
                } else {
                    activitySearchClose.setVisibility(View.VISIBLE);
                }
            }
        });
        searchGridview.setAdapter(new SearchHotCityAdapter(this, citys));
        GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
        searchRecyclerview.setLayoutManager(layoutManager);
        if (callBack == null) {
            callBack = new MyCallBack(searchStateview) {
                @Override
                public void onStart(int what) {
                    activitySearchHotcityLl.setVisibility(View.GONE);
                    searchStateview.setVisibility(View.VISIBLE);
                    super.onStart(what);
                }

                @Override
                public void onSucceed(int what, String json) {
                    Log.e("call", "创建的网络回调中onSucceed被执行");
                    //解析数据

                    xRecyclerView.setAdapter(new HomeFragmentAdapter(SearchActivity.this, lineitems));
                    searchStateview.showNormal();
                }
            };
        }
        searchRecyclerview.setAdapter(new SearchRecyclerViewAdapter(this, citys, callBack));
    }

    @OnClick({R.id.iv_go_back, R.id.activity_search_close, R.id.activity_search_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.activity_search_close:
                activitySearchEd.setText("");
                break;
            case R.id.activity_search_iv:
                String content = activitySearchEd.getText().toString();
                //请求网络
                NetUtils.callNet(14, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
