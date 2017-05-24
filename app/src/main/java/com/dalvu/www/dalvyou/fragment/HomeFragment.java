package com.dalvu.www.dalvyou.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
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

/**
 * 首页界面
 * Created by user on 2017/5/9.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_stateview)
    StateView home_Stateview;
    //保存首页模块分类的集合
    private ArrayList homemodules = new ArrayList();
    //保存首页线路条目的集合
    private ArrayList homeItems = new ArrayList();
    private View headerView;
    private HomeFragmentAdapter homeFragmentAdapter;
    private HomeHeaderAdapter homeHeaderAdapter;
    private MyCallBack callBack;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void update() {
        requestServer();
    }

    private void requestServer() {
        if(callBack == null){
            callBack = new MyCallBack(home_Stateview) {

                boolean isShow = false;
                private XRecyclerView xRecyclerView;

                @Override
                public void onStart(int what) {
                    if (home_Stateview.state_Load.getVisibility() == View.GONE) {
                        home_Stateview.showLoading();
                    }
                }

                @Override
                public void onSucceed(int what, String json) {
                    Log.e("call", json);
                    switch (what) {
                        case CustomValue.HOMECOLUMN:
                            HomeFragmentModuleDataBean homeFragmentModuleDataBean = new Gson().fromJson(json, HomeFragmentModuleDataBean.class);
                            break;
                        case CustomValue.HOMELINE:
                            break;
                    }
                    home_Stateview.addNormal(R.layout.item_xrecyclerview);
                    if(xRecyclerView == null){
                        Log.e("call", "xRecyclerView是null");
                        xRecyclerView = (XRecyclerView) home_Stateview.normal.findViewById(R.id.xrecyclerview);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
                        xRecyclerView.setLayoutManager(gridLayoutManager);
                        if (headerView == null) {
                            Log.e("call", "headerView是null");
                            initHeader();
                            xRecyclerView.addHeaderView(headerView);
                        }
                        if(homeFragmentAdapter == null){
                            homeFragmentAdapter = new HomeFragmentAdapter(activity, homeItems);
                            xRecyclerView.setAdapter(homeFragmentAdapter);
                        }
                    }else{
                        //刷新适配器
                        homeHeaderAdapter.notifyDataSetChanged();
                        homeFragmentAdapter.notifyDataSetChanged();
                    }
                    if(isShow){
                        home_Stateview.showNormal();
                        isShow = false;
                    }else{
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
        //保存模块图片地址的集合
        TreeMap<String, Integer> modules = new TreeMap<>();
        modules.put("国内游", R.mipmap.domestic_travel);
        modules.put("出境游", R.mipmap.overseas_travel);
        modules.put("周边游", R.mipmap.travel_around);
        modules.put("自由行", R.mipmap.free_exercise);
        modules.put("保险/签证", R.mipmap.insurance_visa);
        modules.put("机票", R.mipmap.air_ticket);
        modules.put("门票", R.mipmap.ticket);
        modules.put("特价", R.mipmap.special_offer);
        headerView = LayoutInflater.from(activity).inflate(R.layout.home_fragmen_recyclerviewhead, null, false);
        RecyclerView home_module = (RecyclerView) headerView.findViewById(R.id.home_module);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
        home_module.setLayoutManager(gridLayoutManager);
        homeHeaderAdapter = new HomeHeaderAdapter(activity, modules);
        home_module.setAdapter(homeHeaderAdapter);
    }
}
