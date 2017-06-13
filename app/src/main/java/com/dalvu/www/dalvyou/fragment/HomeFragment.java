package com.dalvu.www.dalvyou.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.SearchActivity;
import com.dalvu.www.dalvyou.adapter.HomeFragmentAdapter;
import com.dalvu.www.dalvyou.adapter.HomeHeaderAdapter;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.HomeFragmentModuleDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.dalvu.www.dalvyou.R.id.home_fragment_search_ll;

/**
 * 首页界面
 * Created by user on 2017/5/9.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_fragment_head_icon)
    ImageView homeFragmentHeadIcon;
    @BindView(R.id.home_fragment_head_name)
    TextView homeFragmentHeadName;
    @BindView(R.id.home_fragment_head_adviser)
    TextView homeFragmentHeadAdviser;
    @BindView(R.id.home_fragment_head_ll)
    LinearLayout homeFragmentHeadLl;
    @BindView(R.id.home_fragment_head_score)
    TextView homeFragmentHeadScore;
    @BindView(R.id.home_fragment_head_advisericon)
    ImageView homeFragmentHeadAdvisericon;
    @BindView(R.id.home_fragment_head_advisername)
    TextView homeFragmentHeadAdvisername;
    @BindView(R.id.home_fragment_head_adviserll)
    LinearLayout homeFragmentHeadAdviserll;
    @BindView(R.id.home_fragment_head_inlogin)
    LinearLayout homeFragmentHeadInlogin;
    private Unbinder unbinder;
    //保存首页模块分类的集合
    private ArrayList homemodules = new ArrayList();
    //保存首页线路条目的集合
    private ArrayList homeItems = new ArrayList();
    private View headerView;
    private HomeFragmentAdapter homeFragmentAdapter;
    private HomeHeaderAdapter homeHeaderAdapter;
    private MyCallBack callBack;
    private StateView home_Stateview;
    private XRecyclerView xRecyclerView;
    private TreeMap<String, Integer> modules;
    private RecyclerView home_module;
    private RelativeLayout home_fragment_toolbar;
    //屏幕滑动的总距离
    private int distanceY;
    //当前的透明度
    private int alpha;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        home_Stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        home_Stateview.addNormal(R.layout.home_fragment_xrecyclerview);
        xRecyclerView = (XRecyclerView) home_Stateview.normal.findViewById(R.id.home_fragment_xrecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        headerView = LayoutInflater.from(activity).inflate(R.layout.home_fragmen_recyclerviewhead, null, false);
        home_fragment_toolbar = (RelativeLayout) home_Stateview.findViewById(R.id.home_fragment_toolbar);
        ImageView home_selector_city = (ImageView) home_Stateview.normal.findViewById(R.id.home_selector_city);
        LinearLayout home_fragment_search_ll = (LinearLayout) home_Stateview.normal.findViewById(R.id.home_fragment_search_ll);
        HomeOnClickListener listener = new HomeOnClickListener();
        home_selector_city.setOnClickListener(listener);
        home_fragment_search_ll.setOnClickListener(listener);
    }

    @Override
    protected void initData() {
        xRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int[] screenSize = MyApplication.getMyApplication().getScreenSize();
            float totalDistance = screenSize[1] / 3;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                distanceY += dy;
                if (distanceY <= 0) {
                    alpha = 0;
                } else if (distanceY >= totalDistance) {
                    alpha = 230;
                } else {
                    alpha = (int) (distanceY * 1.0f / totalDistance * 230);
                }
                home_fragment_toolbar.setBackgroundColor(Color.argb(alpha, 86, 109, 248));
            }
        });
        requestServer();
    }

    @Override
    public void update() {
        requestServer();
    }

    private void requestServer() {
        if (callBack == null) {
            callBack = new MyCallBack(home_Stateview) {

                boolean isShow = false;

                @Override
                public void onStart(int what) {
                    if (home_Stateview.state_Load.getVisibility() == View.GONE) {
                        home_Stateview.showLoading();
                    }
                }

                @Override
                public void onSucceed(int what, String json) {
                    switch (what) {
                        case CustomValue.HOMECOLUMN:
                            HomeFragmentModuleDataBean homeFragmentModuleDataBean = new Gson().fromJson(json, HomeFragmentModuleDataBean.class);
                            break;
                        case CustomValue.HOMELINE:
                            break;
                    }
                    if (xRecyclerView != null) {
                        Log.e("call", "xRecyclerView不是null");
                        if (homeHeaderAdapter == null || homeFragmentAdapter == null) {
                            if (homeHeaderAdapter == null) {
                                Log.e("call", "headerView是null");
                                initHeader();
                                xRecyclerView.addHeaderView(headerView);
                            }
                            if (homeFragmentAdapter == null) {
                                Log.e("call", "homeFragmentAdapter是null");
                                homeFragmentAdapter = new HomeFragmentAdapter(activity, homeItems);
                                xRecyclerView.setAdapter(homeFragmentAdapter);
                            }
                        } else {
                            //刷新适配器
                            switch (what) {
                                case CustomValue.HOMECOLUMN:
                                    Log.e("call", "Head适配器刷新");
                                    homeHeaderAdapter.notifyDataSetChanged();
                                    break;
                                case CustomValue.HOMELINE:
                                    Log.e("call", "Item适配器刷新");
                                    homeFragmentAdapter.notifyDataSetChanged();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    if (isShow) {
                        home_Stateview.showNormal();
                        isShow = false;
                    } else {
                        isShow = true;
                    }
                }

                @Override
                public void onFailed(int what, int code) {
                    if (what == CustomValue.HOMECOLUMN) {
                        home_Stateview.showError();
                    }
                    if (code == -1) {
                        Toast.makeText(activity, "无法链接到服务器", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, "错误代码" + code, Toast.LENGTH_SHORT).show();
                    }
                }
            };
        }
        NetUtils.callNet(CustomValue.HOMECOLUMN, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
        NetUtils.callNet(CustomValue.HOMELINE, CustomValue.SERVER + "/index.php/Api/index/indexLineList", callBack);
    }

    private void initHeader() {
        if (modules == null) {
            //保存模块图片地址的集合
            modules = new TreeMap<>();
            modules.put("国内游", R.mipmap.domestic_travel);
            modules.put("出境游", R.mipmap.outbound_tourism);
            modules.put("周边游", R.mipmap.surrounding_tourism);
            modules.put("自由行", R.mipmap.free_walker);
            modules.put("机票", R.mipmap.passenger_ticket);
            modules.put("特价", R.mipmap.special_offe);
            modules.put("门票", R.mipmap.entrance_ticket);
            modules.put("签证保险", R.mipmap.insurance_visa);
        }
        if (headerView != null) {
            unbinder = ButterKnife.bind(this, headerView);
        }
        //显示头像
//        homeFragmentHeadIcon.set
        //显示名称
        homeFragmentHeadName.setText("我是名字");
        //显示身份
        homeFragmentHeadAdviser.setText("身份");
        //显示积分
        homeFragmentHeadScore.setText("积分：1");
        //显示我的顾问
        homeFragmentHeadAdviserll.setVisibility(View.VISIBLE);
        //我的顾问的头像
//        homeFragmentHeadAdvisericon.set
        //我的顾问的名字
        homeFragmentHeadAdvisername.setText("张三");
        //显示登陆按钮
        homeFragmentHeadInlogin.setVisibility(View.GONE);
        if (home_module == null) {
            home_module = (RecyclerView) headerView.findViewById(R.id.home_module);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
            home_module.setLayoutManager(gridLayoutManager);
        }
        homeHeaderAdapter = new HomeHeaderAdapter(activity, modules);
        home_module.setAdapter(homeHeaderAdapter);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private class HomeOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_selector_city:
                    //选择城市，刷新首页

                    break;
                case home_fragment_search_ll:
                    Intent intent = new Intent(activity, SearchActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }
}
