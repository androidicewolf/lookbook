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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.DensityUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 展示线路详情的界面
 */
public class LineDetailActivity extends BaseNoTitleActivity {

    //线路详情页viewpager点击跳转的地址
    String uri = "www.baidu.com";
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
    @BindView(R.id.linedetail_name_et)
    EditText linedetailNameEt;
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
    ListenedScrollView linedetailGroupdateLl;

    //是否是输入框的一个状态
    private boolean isNameInput;
    //保存线路详情里ViewPager的子条目
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private StateView lineDetailStateview;
    private Unbinder unbinder;
    private TabLayout.OnTabSelectedListener tabSelectedListener;
    private SparseArray<BaseFragment> fragments;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notoolbar_stateview);
        lineDetailStateview = (StateView) findViewById(R.id.line_detail_stateview);
        lineDetailStateview.addNormal(R.layout.activity_line_detail);
        unbinder = ButterKnife.bind(this, lineDetailStateview.normal);
        isNameInput = false;
        initView();
        initData();
        initTextView();
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
                Log.e("call", "输出y===============" + y);
                Log.e("call", "输出oldY===============" + oldY);
                if (y >= totalDistance) {
                    if (lineDetailTitle.getVisibility() != View.VISIBLE) {
                        lineDetailTitle.setVisibility(View.VISIBLE);
                        ivGoBack.setImageResource(R.mipmap.arrowhead_up);
                        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 255);
                        valueAnimator.setDuration(1000);
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
                        valueAnimator.setDuration(1000);
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

    private void initTextView() {
        if (!isNameInput) {
            changeToShow();
        } else {
            changeToInput();
        }
    }

    private void changeToShow() {
//        linedetailLlChangename.setText("更改分享标题");
        linedetailNameTv.setVisibility(View.VISIBLE);
        linedetailNameEt.setVisibility(View.GONE);
    }

    private void changeToInput() {
//        linedetailLlChangename.setText("保存");
        linedetailNameTv.setVisibility(View.GONE);
        linedetailNameEt.setVisibility(View.VISIBLE);
    }

//    @OnClick({R.id.linedetail_ll_changename, R.id.linedetail_btn_changeprice, R.id.linedetail_btn_recommend, R.id.line_btn_consult, R.id.line_btn_destine})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.linedetail_ll_changename:
//                if (isNameInput) {
//                    String line_name = linedetailNameEt.getText().toString();
//                    //请求网络，发送自定义标题，成功或者失败Toast提示
//
//                    changeToShow();
//                    isNameInput = false;
//                } else {
//                    linedetailNameEt.setText("我的自定义标题");
//                    changeToInput();
//                    isNameInput = true;
//                }
//                break;
//            case R.id.linedetail_btn_changeprice:

//
//                break;
//            case R.id.linedetail_btn_recommend:
//                //请求网络，发送当前线路设为推荐线路
//
//                break;
//            case R.id.line_btn_consult:
//                //发起意图打电话
//                Intent callIntent = new Intent();
//
//                break;
//            case R.id.line_btn_destine:
//                //跳转支付页面
//
//                break;
//        }
//    }

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
    private void creatGuideDot(List list) {
        for (int i = 0; i < 4; i++) {
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
                    if (lineDetailStateview.state_Load.getVisibility() == View.GONE) {
                        lineDetailStateview.showLoading();
                    }
                }

                @Override
                public void onSucceed(int what, String json) {
                    switch (what) {
                        /**基本信息**/
                        case CustomValue.LINEDETAILBASE:
                            Log.e("call", "线路的基本信息=====" + json);
                            LineDetailDatabean lineDetailDatabean = new Gson().fromJson(json, LineDetailDatabean.class);
                            //代码创建灰色小圆点
                            creatGuideDot(lineDetailDatabean.picArr);
                            initViewPager(lineDetailDatabean.picArr);
                            lineDetailStateview.showNormal();
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

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("id", 4005);
        //发送线路基本信息的网络请求
        NetUtils.callNet(CustomValue.LINEDETAILBASE, CustomValue.SERVER + "/index.php/Api/index/details", treeMap, callBack);

    }

    //初始化Fragment的容器
    private void initFragments() {
        fragments = new SparseArray<>();
        fragments.put(0, new LinedetailPlanFragment());
        fragments.put(1, new LinedetailDescriptionFragment());
        fragments.put(2, new LinedetailCostFragment());
        fragments.put(3, new LinedetailNoticeFragment());
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
        Log.e("call", "----------------" + itemsList);
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageViews.add(imageView);
        }


        //适配器中传入的是一个imageview对象的集合，后期再改为在适配器中加载图片

        //判断是否有一个图片，如果只有一个不显示圆点，不发消息轮播
        if (imageViews == null || imageViews.size() == 1) {
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
        linedetailViewpager.setAdapter(new LinePagerAdapter(this, imageViews, handler));
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

    @OnClick({R.id.linedetail_tv_changeprice, R.id.line_consult_ll, R.id.line_btn_destine, R.id.linedetail_groupdata_ll})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.linedetail_tv_changeprice:
                //跳转改价页面
                intent = new Intent(this, LineChangepriceActivity.class);
                startActivity(intent);
                break;
            case R.id.line_consult_ll:
                break;
            case R.id.linedetail_groupdate_ll:
                //跳转团期页面（打开一个新的界面）
                intent = new Intent(this, LineGroupDateActivity.class);
                startActivity(intent);
                break;
            case R.id.line_btn_destine:
                intent = new Intent(this, LineDestineActivity.class);
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
