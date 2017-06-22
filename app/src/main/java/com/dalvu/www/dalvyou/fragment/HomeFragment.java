package com.dalvu.www.dalvyou.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.SearchActivity;
import com.dalvu.www.dalvyou.adapter.HomeFragmentAdapter;
import com.dalvu.www.dalvyou.adapter.HomeHeaderAdapter;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.HomeFragmentLineDataBean;
import com.dalvu.www.dalvyou.bean.HomeFragmentModuleDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.home_fragment_head_rl)
    RelativeLayout homeFragmentHeadRl;
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
    //从内存中获取的用户类型
    private int user_type;
    //从内存中获取的uid
    private int user_id;
    //从内存中获取的token
    private String user_token;

    //根据sort字段存放模块的顺序
    private SparseArray<HomeFragmentModuleDataBean.ColumnListBean> columnsort;
    //保存首页线路条目的集合
    private ArrayList<HomeFragmentLineDataBean.ListBean> homeItems = new ArrayList<>();

    private View headerView;
    private HomeFragmentAdapter homeFragmentAdapter;
    private HomeHeaderAdapter homeHeaderAdapter;
    private MyCallBack callBack;
    private StateView home_Stateview;
    private XRecyclerView xRecyclerView;
    private HashMap<String, Integer> modules;
    private RecyclerView home_module;
    private RelativeLayout home_fragment_toolbar;
    //屏幕滑动的总距离
    private int distanceY;
    //当前的透明度
    private int alpha;
    //城市的集合
    private ArrayList<String> citys;
    private String city = "北京市";
    //未登录模块
    private String urlColumn = "Api/index/indexMod";
    //未登录线路列表
    private String urlLine = "Api/index/indexLineList";
    //顾问登录模块
    private String urlAdColumn = "Api/index/agencyIndexMod";
    //顾问登录线路列表
    private String urlAdLine = "Api/index/agencyIndexLinelist";
    //游客登录模块
    private String urlViColumn = "TouristApi/TouristIndex/agencyIndexMod";
    //游客登录线路列表
    private String urlViLine = "TouristApi/TouristIndex/agencyIndexLinelist";
    //用户头布局信息的bean类
    private HomeFragmentModuleDataBean homeFragmentModuleDataBean;
    //用户线路列表信息的bean类
    private HomeFragmentLineDataBean homeFragmentLineDataBean;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        user_type = AppUserDate.getUserType();
        user_id = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
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
        requestServer(city);
    }

    @Override
    public void update() {
        NetUtils.callNet(1, "", callBack);

    }

    private void requestServer(String city) {
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
                            homeFragmentModuleDataBean = new Gson().fromJson(json, HomeFragmentModuleDataBean.class);
                            break;
                        case CustomValue.HOMELINE:
                            homeFragmentLineDataBean = new Gson().fromJson(json, HomeFragmentLineDataBean.class);
                            break;
                    }
                    if ((homeFragmentModuleDataBean.status.equals("00000")) || (homeFragmentLineDataBean.status.equals("00000"))) {
                        if (isShow) {
                            if (homeFragmentModuleDataBean.status.equals("00000") && (homeFragmentLineDataBean.status.equals("00000"))) {
//                                isShow = false;
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
//                                            homeItems = homeFragmentLineDataBean.list;
                                            if (homeFragmentLineDataBean.list != null && homeFragmentLineDataBean.list.size() != 0) {
                                                homeFragmentAdapter = new HomeFragmentAdapter(activity, homeFragmentLineDataBean.list);
                                                xRecyclerView.setAdapter(homeFragmentAdapter);
                                            }
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
                                home_Stateview.showNormal();
                                isShow = false;
//                                if (isShow) {
//                                    home_Stateview.showNormal();
//                                    isShow = false;
//                                } else {
//                                    isShow = true;
//                                }
                            }
                        } else {
                            isShow = true;
                        }
                    } else {
                        if (home_Stateview.state_Error.getVisibility() == View.GONE) {
                            Log.e("call", "服务器响应码不是00000时，执行这个方方法，显示错误的界面，展示数据");
                            home_Stateview.showError();
                            Toast.makeText(activity, "服务器繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                        }
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
        if (user_id == 0) {
            NetUtils.callNet(CustomValue.HOMECOLUMN, CustomValue.SERVER + urlColumn, callBack);
            NetUtils.callNet(CustomValue.HOMELINE, CustomValue.SERVER + urlLine, callBack);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put(CustomValue.UID, "" + user_id);
            map.put(CustomValue.TOKEN, user_token);
            if (user_type == 4) {
                NetUtils.callNet(CustomValue.HOMECOLUMN, CustomValue.SERVER + urlAdColumn, map, callBack);
                NetUtils.callNet(CustomValue.HOMELINE, CustomValue.SERVER + urlAdLine, map, callBack);
            } else {
                NetUtils.callNet(CustomValue.HOMECOLUMN, CustomValue.SERVER + urlViColumn, map, callBack);
                NetUtils.callNet(CustomValue.HOMELINE, CustomValue.SERVER + urlViLine, map, callBack);
            }
        }
    }

    private void initHeader() {
        if (columnsort == null) {
            columnsort = new SparseArray<>();
        } else {
            columnsort.clear();
        }
        for (HomeFragmentModuleDataBean.ColumnListBean columnListBean : homeFragmentModuleDataBean.columnList) {
            columnsort.put(Integer.valueOf(columnListBean.sort) - 1, columnListBean);
        }
        if (modules == null) {
            //保存模块图片地址的集合
            modules = new HashMap<>();
            modules.put("国内游", R.mipmap.domestic_travel);
            modules.put("出境游", R.mipmap.outbound_tourism);
            modules.put("周边游", R.mipmap.surrounding_tourism);
            modules.put("自由行", R.mipmap.free_walker);
            modules.put("机票", R.mipmap.passenger_ticket);
            modules.put("特价", R.mipmap.special_offe);
            modules.put("门票", R.mipmap.entrance_ticket);
            modules.put("保险/签证", R.mipmap.insuran_visa);
        }
        if (headerView != null) {
            unbinder = ButterKnife.bind(this, headerView);
        }
        if (user_id == 0) {
            homeFragmentHeadRl.setVisibility(View.GONE);
            homeFragmentHeadAdviserll.setVisibility(View.GONE);
            homeFragmentHeadInlogin.setVisibility(View.VISIBLE);
        } else {
            if (user_type == 4) {
                homeFragmentHeadRl.setVisibility(View.VISIBLE);
                homeFragmentHeadAdviserll.setVisibility(View.GONE);
                homeFragmentHeadInlogin.setVisibility(View.GONE);
                homeFragmentHeadAdviser.setVisibility(View.VISIBLE);
//                homeFragmentHeadScore.setVisibility(View.VISIBLE);
                //显示头像
                Glide.with(activity).load(homeFragmentModuleDataBean.agencyInfo.head_pic).into(homeFragmentHeadIcon);
                //显示名称
                homeFragmentHeadName.setText(homeFragmentModuleDataBean.agencyInfo.name);
//                //显示积分
//                homeFragmentHeadScore.setText();
            } else {
                homeFragmentHeadRl.setVisibility(View.VISIBLE);
                homeFragmentHeadAdviserll.setVisibility(View.VISIBLE);
                homeFragmentHeadInlogin.setVisibility(View.GONE);
                homeFragmentHeadAdviser.setVisibility(View.GONE);
                //显示头像
                Glide.with(activity).load(homeFragmentModuleDataBean.touristInfo.head_img).into(homeFragmentHeadIcon);
                //显示名称
                homeFragmentHeadName.setText(homeFragmentModuleDataBean.touristInfo.name);
//                //显示积分
//                homeFragmentHeadScore.setText("积分：1");
                //我的顾问的头像
                Glide.with(activity).load(homeFragmentModuleDataBean.agencyInfo.head_pic).into(homeFragmentHeadAdvisericon);

                //我的顾问的名字
                homeFragmentHeadAdvisername.setText(homeFragmentModuleDataBean.agencyInfo.name);
            }
        }

        if (home_module == null) {
            home_module = (RecyclerView) headerView.findViewById(R.id.home_module);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
            home_module.setLayoutManager(gridLayoutManager);
        }
        homeHeaderAdapter = new HomeHeaderAdapter(activity, modules, columnsort);
        home_module.setAdapter(homeHeaderAdapter);
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    private class HomeOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_selector_city:
                    if (citys == null) {
                        citys = new ArrayList<>();
                        citys.add("北京市");
                        citys.add("天津市");
                        citys.add("河北省");
                        citys.add("唐山市");
                        citys.add("其他");
                    }

                    //选择城市，刷新首页
                    OptionsPickerView pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            city = citys.get(options1);
                            Log.e("call", city);
                            update();
                        }
                    }).build();
                    pvOptions.setNPicker(citys, null, null);
                    pvOptions.show();
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
