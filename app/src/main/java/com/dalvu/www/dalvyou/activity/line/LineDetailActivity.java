package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LinePagerAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LineDetailDatabean;
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
    @BindView(R.id.line_detail_title)
    TextView lineDetailTitle;

    //是否是输入框的一个状态
    private boolean isNameInput;
    //保存线路详情里ViewPager的子条目
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private StateView lineDetailStateview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notoolbar_stateview);
        lineDetailStateview = (StateView) findViewById(R.id.line_detail_stateview);
        lineDetailStateview.addNormal(R.layout.activity_line_detail);
        ButterKnife.bind(this, lineDetailStateview.normal);
        isNameInput = false;
        initData();
        initTextView();
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
        requestNetCall();
    }

    //根据图片的张数创建灰色小圆点
    private void creatGuideDot(List list) {
        for (int i = 0; i < list.size(); i++) {
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
                        /**行程安排**/
                        case CustomValue.LINEDESCRIPTION:
                            break;
                        /**产品亮点**/
                        case CustomValue.LINEDPLAN:
                            break;
                        /**费用说明**/
                        case CustomValue.LINECOST:
                            break;
                        /**注意事项**/
                        case CustomValue.LINENOTICE:
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

    private void initViewPager(List<String> itemsList) {
        Log.e("call", "----------------" + itemsList);
        for (String uri : itemsList) {
            ImageView imageView = new ImageView(this);
            imageViews.add(imageView);
        }

        linedetailViewpager.setAdapter(new LinePagerAdapter(this, imageViews));
        //viewpager的切换监听
        linedetailViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动时调用
                linedetailViewperCursorReddot.setTranslationX((positionOffsetPixels + position) * 30);
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
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("1"));
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("2"));
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("3"));
        linedetailTabLayout.addTab(linedetailTabLayout.newTab().setTag("4"));
    }

    @OnClick({R.id.linedetail_tv_changeprice, R.id.line_consult_ll, R.id.line_btn_destine})
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
            case R.id.line_btn_destine:
                intent = new Intent(this, LineDestineActivity.class);
                startActivity(intent);
                break;
        }
    }
}
