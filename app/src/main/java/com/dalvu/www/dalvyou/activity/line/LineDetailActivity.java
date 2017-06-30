package com.dalvu.www.dalvyou.activity.line;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LinePagerAdapter;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LineDetailDatabean;
import com.dalvu.www.dalvyou.customView.ListenedScrollView;
import com.dalvu.www.dalvyou.customView.OnScrollListener;
import com.dalvu.www.dalvyou.fragment.LinedetailCostFragment;
import com.dalvu.www.dalvyou.fragment.LinedetailDescriptionFragment;
import com.dalvu.www.dalvyou.fragment.LinedetailNoticeFragment;
import com.dalvu.www.dalvyou.fragment.LinedetailPlanFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.DensityUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 展示线路详情的界面
 */
public class LineDetailActivity extends BaseNoTitleActivity {

    //线路详情页viewpager点击跳转的地址
    String uri = "";
    //线路详情页顾问请求的地址
    String uriAdLineDetail = "Api/agency/details";
    //线路详情页未登录请求的地址
    String uriLineDetail = "Api/index/details";

    //请求网络使用的回调
    MyCallBack callBack;
    @BindView(R.id.line_consult_ll)
    LinearLayout lineConsultLl;
    @BindView(R.id.line_btn_destine)
    Button lineBtnDestine;
    @BindView(R.id.line_ll_do)
    LinearLayout lineLlDo;
    @BindView(R.id.linedetail_viewpager)
    ViewPager linedetailViewpager;
    @BindView(R.id.linedetail_viewper_cursor_guidedot)
    LinearLayout linedetailViewperCursorGuidedot;
    @BindView(R.id.linedetail_viewper_cursor_reddot)
    View linedetailViewperCursorReddot;
    @BindView(R.id.linedetail_name_tv)
    TextView linedetailNameTv;
    @BindView(R.id.linedetail_btn_changename_iv)
    ImageView linedetailBtnChangenameIv;
    @BindView(R.id.linedetail_btn_changename_tv)
    TextView linedetailBtnChangenameTv;
    @BindView(R.id.linedetail_ll_changename)
    LinearLayout linedetailLlChangename;
    @BindView(R.id.linedetail_number)
    TextView linedetailNumber;
    @BindView(R.id.line_contacts)
    TextView lineContacts;
    @BindView(R.id.linedetail_supplier)
    TextView linedetailSupplier;
    @BindView(R.id.line_price)
    TextView linePrice;
    @BindView(R.id.linedetail_tv_changeprice)
    TextView linedetailTvChangeprice;
    @BindView(R.id.line_gocity)
    TextView lineGocity;
    @BindView(R.id.line_govehicle)
    TextView lineGovehicle;
    @BindView(R.id.line_days)
    TextView lineDays;
    @BindView(R.id.line_gotraffic)
    TextView lineGotraffic;
    @BindView(R.id.line_destination)
    TextView lineDestination;
    @BindView(R.id.linedetail_tabLayout)
    TabLayout linedetailTabLayout;
    @BindView(R.id.linedetail_fragment)
    FrameLayout linedetailFragment;
    @BindView(R.id.linedetail_btn_recommend)
    LinearLayout linedetailBtnRecommend;
    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.linedetail_listenedscrollview)
    ListenedScrollView linedetailListenedscrollview;
    @BindView(R.id.line_detail_title_rl)
    RelativeLayout lineDetailTitleRl;
    @BindView(R.id.linedetail_viewpager_cursor_rl)
    RelativeLayout linedetailViewpagerCursorRl;
    @BindView(R.id.line_detail_title)
    TextView lineDetailTitle;
    @BindView(R.id.linedetail_groupdate_ll)
    LinearLayout linedetailGroupdateLl;

    //是否是输入框的一个状态
    private boolean isNameInput;
    //保存线路详情里ViewPager的子条目
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private StateView lineDetailStateview;
    private Unbinder unbinder;
    private TabLayout.OnTabSelectedListener tabSelectedListener;
    private SparseArray<BaseFragment> fragments;
    private Handler handler;
    private LineDetailDatabean lineDetailDatabean;
    private String lineid;
    private int user_id;
    private int user_type;
    private String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notoolbar_stateview);
        //从意图中获取线路的id
        lineid = getIntent().getStringExtra("id");
        Log.e("call", "线路的ID:" + lineid);
        //从内存中获取uid，token和type
        user_id = AppUserDate.getUserId();
        user_type = AppUserDate.getUserType();
        user_token = AppUserDate.getUserToken();

        lineDetailStateview = (StateView) findViewById(R.id.line_detail_stateview);
        lineDetailStateview.addNormal(R.layout.activity_line_detail);
        unbinder = ButterKnife.bind(this, lineDetailStateview.normal);
        isNameInput = false;
        initView();
        initData();
    }

    private void initView() {
        ivGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linedetailListenedscrollview.setOnScrollListener(new OnScrollListener() {
            int[] screenSize = MyApplication.getMyApplication().getScreenSize();
            float totalDistance = screenSize[1] / 5;

            @Override
            public void onBottomArrived() {
            }

            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                if (y >= totalDistance) {
                    if (lineDetailTitle.getVisibility() != View.VISIBLE) {
                        lineDetailTitle.setVisibility(View.VISIBLE);
                        ivGoBack.setImageResource(R.mipmap.arrowhead_up);
                        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 255);
                        valueAnimator.setDuration(500);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                Integer alpha = (Integer) animation.getAnimatedValue();
                                lineDetailTitleRl.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                            }
                        });
                        valueAnimator.start();
                    }
                } else {
                    if (lineDetailTitle.getVisibility() == View.VISIBLE) {
                        lineDetailTitle.setVisibility(View.GONE);
                        ivGoBack.setImageResource(R.mipmap.return_details);
                        lineDetailTitleRl.setBackgroundColor(Color.argb(0, 255, 255, 255));
                        ValueAnimator valueAnimator = ValueAnimator.ofInt(255, 0);
                        valueAnimator.setDuration(500);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                Integer alpha = (Integer) animation.getAnimatedValue();
                                lineDetailTitleRl.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                            }
                        });
                        valueAnimator.start();
                    }
                }
            }
        });
    }

    //初始化数据
    private void initData() {
        TabLayout.Tab tab1 = linedetailTabLayout.newTab().setTag("1").setText("行程安排");
        linedetailTabLayout.addTab(tab1);
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("2").setText("产品亮点"));
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("3").setText("费用说明"));
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("4").setText("注意事项"));
        initFragments();
        if (tabSelectedListener == null) {
            initTabSelectedListener();
        }
        linedetailTabLayout.addOnTabSelectedListener(tabSelectedListener);
        tabSelectedListener.onTabSelected(tab1);
        requestNetCall();
    }

    //根据图片的张数创建灰色小圆点
    private void creatGuideDot(List<String> list) {
        for (String url : list) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_guide_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dip2px(this, 8), DensityUtils.dip2px(this, 8));
            params.rightMargin = 15;
            view.setLayoutParams(params);
            linedetailViewperCursorGuidedot.addView(view);
        }
    }

    private void requestNetCall() {
        if (callBack == null) {
            callBack = new MyCallBack(lineDetailStateview) {
                @Override
                public void onStart(int what) {
                    lineDetailStateview.showLoading();
                }

                @Override
                public void onSucceed(int what, String json) {
                    switch (what) {
                        /**基本信息**/
                        case CustomValue.LINEDETAILBASE:
                            Log.e("call", "线路详情页的数据json：======" + json);
                            lineDetailDatabean = MyApplication.getGson().fromJson(json, LineDetailDatabean.class);

                            if (lineDetailDatabean.status.equals("00000")) {
                                //设置数据
                                lineDetailTitle.setText(lineDetailDatabean.list.name);
                                linedetailNameTv.setText(lineDetailDatabean.list.name);
                                linedetailNumber.setText("代码：" + lineDetailDatabean.list.id);
                                lineContacts.setText("联系人：" + lineDetailDatabean.list.contact_person);
                                linedetailSupplier.setText("供应商：" + lineDetailDatabean.list.provider_name);
                                linePrice.setText(String.valueOf(Float.valueOf(lineDetailDatabean.list.min_price) / 100));
                                lineGocity.setText(lineDetailDatabean.list.departure);
                                lineDestination.setText(lineDetailDatabean.list.destinations);
                                lineGovehicle.setText(lineDetailDatabean.list.traffic_go);
                                lineGotraffic.setText(lineDetailDatabean.list.traffic_back);
                                lineDays.setText(lineDetailDatabean.list.totalDay + "天");

                                //代码创建灰色小圆点
                                creatGuideDot(lineDetailDatabean.picArr);
                                initViewPager(lineDetailDatabean.picArr);
                                Log.e("call", "从服务器获取的图片集合的大小：=====" + lineDetailDatabean.picArr.size());
                                lineDetailStateview.showNormal();


                            } else {
                                Toast.makeText(LineDetailActivity.this, lineDetailDatabean.msg, Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }

                @Override
                public void onFailed(int what, int code) {
                    if (what == CustomValue.LINEDETAILBASE) {
                        lineDetailStateview.showError();
                    }
                    if (code == -1) {
                        Toast.makeText(LineDetailActivity.this, "无法链接到服务器", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LineDetailActivity.this, "错误代码" + code, Toast.LENGTH_SHORT).show();
                    }
                }
            };
        }
        requestServer();

    }

    private void requestServer() {
        Map<String, String> map = new HashMap<>();
        map.put("id", lineid);
        Log.e("call", "用户ID=" + user_id + "****用户type=" + user_type + "****用户token=" + user_token);
        if (user_id == 0 || user_token.isEmpty()) {
            //未登录，发送线路基本信息的网络请求
            NetUtils.callNet(CustomValue.LINEDETAILBASE, CustomValue.SERVER + uriLineDetail, map, callBack);
        } else {
            switch (user_type) {
                case 4:
                    //顾问已登录，发送线路基本信息的网络请求
                    map.put("uid", "" + user_id);
                    map.put("sign_token", user_token);
                    NetUtils.callNet(CustomValue.LINEDETAILBASE, CustomValue.SERVER + uriAdLineDetail, map, callBack);
                    break;
                case 5:
                    //游客已登录，发送线路基本信息的网络请求
                    map.put("uid", "" + user_id);
                    map.put("sign_token", user_token);
                    NetUtils.callNet(CustomValue.LINEDETAILBASE, CustomValue.SERVER + uriLineDetail, map, callBack);
                    break;
                default:
                    //未登录，发送线路基本信息的网络请求
                    NetUtils.callNet(CustomValue.LINEDETAILBASE, CustomValue.SERVER + uriLineDetail, map, callBack);
                    break;
            }
        }
    }

    //初始化Fragment的容器
    private void initFragments() {
        fragments = new SparseArray<>();
        fragments.put(0, new LinedetailPlanFragment(lineid));
        fragments.put(1, new LinedetailDescriptionFragment(lineid));
        fragments.put(2, new LinedetailCostFragment(lineid));
        fragments.put(3, new LinedetailNoticeFragment(lineid));
    }

    //初始化tablayout的标签选中监听
    private void initTabSelectedListener() {
        tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (fragments != null) {
                    Fragment fragment = fragments.get(position);
                    if (fragment.isAdded()) {
                        transaction.show(fragment);
                    } else {
                        transaction.remove(fragment).add(R.id.linedetail_fragment, fragment);
                    }
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (fragments != null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    Fragment fragment = fragments.get(position);
                    transaction.hide(fragment);
                    transaction.commit();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    private void initViewPager(List<String> itemsList) {
        if (itemsList.size() == 0) {
            ImageView imageView = new ImageView(this);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(R.mipmap.linelist_null);
            imageViews.add(imageView);
        } else {
            for (String url : itemsList) {
                ImageView imageView = new ImageView(this);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(this).load(url).into(imageView);
                imageViews.add(imageView);
            }
        }
        Log.e("call", "线路图片集合的大小:=====" + imageViews.size());


        //适配器中传入的是一个imageview对象的集合，后期再改为在适配器中加载图片

        //判断是否有一个图片，如果只有一个不显示圆点，不发消息轮播
        if (imageViews == null || imageViews.size() <= 1) {
            linedetailViewpagerCursorRl.setVisibility(View.GONE);
        } else {
            linedetailViewpagerCursorRl.setVisibility(View.VISIBLE);
            //设置自动轮播
            if (handler == null) {
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        int itemPosition = linedetailViewpager.getCurrentItem();
                        if (itemPosition == imageViews.size() - 1) {
                            itemPosition = 0;
                        } else {
                            itemPosition++;
                        }
                        linedetailViewpager.setCurrentItem(itemPosition, true);
                        handler.sendEmptyMessageDelayed(0, 3000);
                    }
                };
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
        linedetailViewpager.setAdapter(new LinePagerAdapter(this, imageViews, handler, lineid));
        //viewpager的切换监听
        linedetailViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动时调用
                linedetailViewperCursorReddot.setTranslationX((positionOffset + position)
                        * (15 + DensityUtils.dip2px(LineDetailActivity.this, 8)));
            }

            @Override
            public void onPageSelected(int position) {
                //选中时调用

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //

            }
        });


    }

    @OnClick({R.id.linedetail_tv_changeprice, R.id.line_consult_ll, R.id.line_btn_destine, R.id.linedetail_groupdate_ll,
            R.id.linedetail_ll_changename})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.linedetail_ll_changename:
                //修改标题
                intent = new Intent(this, LineChangeNameActivity.class);
                if (lineDetailDatabean.agencyTourTitle == null) {
                    //传递原标题
                    intent.putExtra("linename", lineDetailDatabean.list.name);
                } else {
                    if (lineDetailDatabean.agencyTourTitle.title.isEmpty()) {
                        //传递原标题
                        intent.putExtra("linename", lineDetailDatabean.list.name);
                    } else {
                        //传递修改的标题
                        intent.putExtra("linename", lineDetailDatabean.agencyTourTitle.title);
                    }
                }
                //传线路的ID
                intent.putExtra("linetitleid", lineDetailDatabean.list.id);
                startActivity(intent);
                break;

            case R.id.linedetail_tv_changeprice:
                //跳转改价页面
                intent = new Intent(this, LineChangepriceActivity.class);
                intent.putExtra("linetitleid", lineDetailDatabean.list.id);
                startActivity(intent);
                break;
            case R.id.line_consult_ll:
                break;

            case R.id.linedetail_groupdate_ll:
                //跳转团期页面（打开一个新的界面）
                intent = new Intent(this, LineGroupDateActivity.class);
                intent.putExtra("groupdate", (Serializable) lineDetailDatabean.tourSkuDate);
                startActivity(intent);
                break;

            case R.id.line_btn_destine:
                intent = new Intent(this, LineDestineActivity.class);
                intent.putExtra("line_id", lineDetailDatabean.list.id);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStop() {
        if (handler != null) {
            handler.removeMessages(0);
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
