package com.dalvu.www.dalvyou.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.AdviserEnrollActivity;
import com.dalvu.www.dalvyou.activity.personaldata.MyAdviserActivity;
import com.dalvu.www.dalvyou.activity.personaldata.PersonalReviseDataActivity;
import com.dalvu.www.dalvyou.activity.personaldata.PersonalSettingActivity;
import com.dalvu.www.dalvyou.activity.personaldata.SupplierQueryActivity;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.PersonalFragmentDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 展示个人中心的内容
 * Created by user on 2017/5/9.
 */

public class PersonalFragment extends BaseFragment {

    @BindView(R.id.personal_fragment_icon)
    RoundedImageView personalFragmentIcon;
    @BindView(R.id.personal_fragment_name)
    TextView personalFragmentName;
    @BindView(R.id.personal_fragment_tel)
    TextView personalFragmentTel;
    @BindView(R.id.personal_fragment_revisedata)
    LinearLayout personalFragmentRevisedata;
    @BindView(R.id.personal_fragment_myrecommend)
    LinearLayout personalFragmentMyrecommend;
    @BindView(R.id.personal_fragment_tv_mystatus)
    TextView personalFragmentTvMystatus;
    @BindView(R.id.personal_fragment_mystatus)
    LinearLayout personalFragmentMystatus;
    @BindView(R.id.personal_fragment_feedback)
    LinearLayout personalFragmentFeedback;
    @BindView(R.id.personal_fragment_vendor_inquiries)
    LinearLayout personalFragmentVendorInquiries;
    @BindView(R.id.personal_fragment_line_inquiry)
    LinearLayout personalFragmentLineInquiry;
    Unbinder unbinder;
    private MyCallBack callBack;
    private String url = "Api/agencyPersonal/index";
    private int userId;
    private String user_token;
    private StateView fragment_stateview;
    private int userType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.personal_fragment);
        userType = AppUserDate.getUserType();
        userId = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
        unbinder = ButterKnife.bind(this, fragment_stateview.normal);
        fragment_stateview.showNormal();
    }

    @Override
    protected void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据
                    PersonalFragmentDataBean personalFragmentDataBean = MyApplication.getGson()
                            .fromJson(json, PersonalFragmentDataBean.class);
                    if (personalFragmentDataBean.status.equals("00000")) {

                        fragment_stateview.showNormal();
                    } else {
                        Toast.makeText(activity, personalFragmentDataBean.msg, Toast.LENGTH_SHORT).show();
                        fragment_stateview.showError();
                    }
                }

            };
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(userId));
        map.put("sign_token", user_token);
        NetUtils.callNet(5, CustomValue.SERVER + url, map, callBack);
    }

    @Override
    public void update() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.personal_fragment_icon, R.id.personal_fragment_revisedata, R.id.personal_fragment_mystatus, R.id.personal_fragment_feedback, R.id.personal_fragment_vendor_inquiries, R.id.personal_fragment_line_inquiry, R.id.personal_fragment_setting})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.personal_fragment_icon:
                break;
            case R.id.personal_fragment_revisedata:
                intent = new Intent(activity, PersonalReviseDataActivity.class);
                //传递用户类型
//                intent.putExtra()
                startActivity(intent);
                break;
            case R.id.personal_fragment_mystatus:
                if (userType == 4) {
                    intent = new Intent(activity, MyAdviserActivity.class);
//                    intent = new Intent(activity, MyVisitorActivity.class);
                    startActivity(intent);
                } else {
                    //如果是游客进入我的顾问页面
                }
                break;
            case R.id.personal_fragment_feedback:
                break;
            case R.id.personal_fragment_vendor_inquiries:
                //供应商查询
                intent = new Intent(activity, SupplierQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_fragment_line_inquiry:
                //线路询价
                //暂时为了看注册，写成了跳转顾问注册页面
                intent = new Intent(activity, AdviserEnrollActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_fragment_setting:
                //通用设置
                intent = new Intent(activity, PersonalSettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
