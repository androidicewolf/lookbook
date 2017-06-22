package com.dalvu.www.dalvyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.EnrollGetNumberBean;
import com.dalvu.www.dalvyou.bean.UserLoginBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.NumberUtils;
import com.dalvu.www.dalvyou.tools.SharedPreferencesTool;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitorEnrollActivity extends BaseNoTitleActivity {

    @BindView(R.id.enroll_ed_inputtel)
    EditText enrollEdInputtel;
    @BindView(R.id.enroll_btn_getprovingnumber)
    TextView enrollBtnGetprovingnumber;
    @BindView(R.id.enroll_btn_getprovingnumber_ll)
    LinearLayout enrollBtnGetprovingnumberLl;
    @BindView(R.id.enroll_ed_inputprovingnumber)
    EditText enrollEdInputprovingnumber;
    @BindView(R.id.enroll_ed_advisertel)
    EditText enrollEdAdvisertel;
    @BindView(R.id.enroll_btn_enroll)
    Button enrollBtnEnroll;
    private CountDownTimer countDownTimer;
    private MyCallBack callBack;
    //电话号码
    private String tel;
    //获取验证码的接口
    private String urlGetNumber = "TouristApi/TouristLogin/touristVerificationCode";
    //C客户登录的接口
    private String urlEnroll = "TouristApi/TouristLogin/touristRegister";
    //是否绑定顾问
    private boolean isBindAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_enroll);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        callBack = new MyCallBack() {
            @Override
            public void onStart(int what) {
                switch (what) {
                    case 19:
                        if (countDownTimer == null) {
                            countDownTimer = new CountDownTimer(61000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int sec = (int) ((millisUntilFinished / 1000) - 1);
                                    if (sec >= 0) {
                                        enrollBtnGetprovingnumber.setText("" + sec + "s后重新获取");
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    enrollBtnGetprovingnumberLl.setBackgroundResource(R.drawable.enroll_btn_style);
                                    enrollBtnGetprovingnumber.setTextColor(ContextCompat.getColor(VisitorEnrollActivity.this, R.color.dalvbule));
                                    enrollBtnGetprovingnumber.setText("重新获取验证码");
                                }
                            };
                        }
                        countDownTimer.start();
                        break;
                    case 20:
                        //加载中的动图显示出来
                        break;
                }
            }

            @Override
            public void onSucceed(int what, String json) {
                //解析数据
                switch (what) {
                    case 19:
                        //从返回的数据中取出绑定顾问的手机号，判断手机号是或否为空，不为空，就把输入
                        // 顾问手机号的输入框设置为不可输入
                        EnrollGetNumberBean enrollGetNumberBean = new Gson().fromJson(json, EnrollGetNumberBean.class);
                        if (enrollGetNumberBean.status.equals("0000")) {
                            isBindAd = enrollGetNumberBean.binding_state.equals("1");
                            if (isBindAd) {
                                enrollEdAdvisertel.setHint("您已经绑定了顾问，登陆后可以查看");
                                enrollEdAdvisertel.setEnabled(false);
                            }
                        } else {
                            Toast.makeText(VisitorEnrollActivity.this, enrollGetNumberBean.msg, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 20:
                        UserLoginBean userLoginBean = new Gson().fromJson(json, UserLoginBean.class);
                        if (userLoginBean.status.equals("00000")) {
                            //登录成功，将数据存进本地
                            SharedPreferencesTool.saveBoolean(VisitorEnrollActivity.this, CustomValue.ISFIRST, false);
                            SharedPreferencesTool.saveString(VisitorEnrollActivity.this, CustomValue.UID, userLoginBean.uid);
                            SharedPreferencesTool.saveString(VisitorEnrollActivity.this, CustomValue.TYPE, userLoginBean.user_type);
                            SharedPreferencesTool.saveString(VisitorEnrollActivity.this, CustomValue.TOKEN, userLoginBean.sign_token);

                            //将用户的基本信息存进内存中
                            AppUserDate.setUserId(Integer.valueOf(userLoginBean.uid));
                            AppUserDate.setUserType(Integer.valueOf(userLoginBean.user_type));
                            AppUserDate.setUserToken(userLoginBean.sign_token);

                            //进入主界面，主界面的启动模式是singTask
                            Intent intent = new Intent(VisitorEnrollActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        } else {
                            Toast.makeText(VisitorEnrollActivity.this, userLoginBean.msg, Toast.LENGTH_SHORT).show();
                        }

                }
            }

            @Override
            public void onFailed(int what, int code) {
                switch (what) {
                    case 19:
                        Toast.makeText(VisitorEnrollActivity.this, "网络异常，获取验证码失败，请稍后再试。", Toast.LENGTH_SHORT).show();
                        countDownTimer.cancel();
                        countDownTimer.onFinish();
                        break;
                    case 20:
                        Toast.makeText(VisitorEnrollActivity.this, "网络异常，注册失败，请检查网络后重试！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFinish(int what) {
                //加载中的图片隐藏
            }
        };
    }

    @OnClick({R.id.login_iv_back, R.id.enroll_btn_getprovingnumber, R.id.enroll_btn_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.login_iv_back:
                finish();
                break;

            case R.id.enroll_btn_getprovingnumber:
                tel = enrollEdInputtel.getText().toString();
                if (tel.isEmpty()) {
                    Toast.makeText(this, "输入的手机号不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(enrollEdInputtel);//获取焦点
                } else {
                    //判断手机号是否合法
                    if (NumberUtils.isMobileNo(tel)) {

                        NetUtils.callNet(19, CustomValue.SERVER + urlGetNumber, callBack);

                        enrollBtnGetprovingnumberLl.setBackgroundResource(R.drawable.send_provingnumber_style);
                        enrollBtnGetprovingnumber.setTextColor(0xFF8F9090);
                    } else {
                        Toast.makeText(this, "您的手机号格式不正确", Toast.LENGTH_SHORT).show();
                        getFocusable(enrollEdInputtel);//获取焦点
                    }
                }
                break;

            case R.id.enroll_btn_enroll:
                //提交注册
                tel = enrollEdInputtel.getText().toString();
                String provingNumber = enrollEdInputprovingnumber.getText().toString();
//                if (name.isEmpty()) {
//                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
//                    getFocusable(enrollEdName);//获取焦点
//                } else {
//
//                }
                if (tel.isEmpty()) {
                    Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(enrollEdInputtel);//获取焦点
                } else if (NumberUtils.isMobileNo(tel)) {
                    if (provingNumber.isEmpty()) {
                        Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                        getFocusable(enrollEdInputprovingnumber);//获取焦点
                    } else {
                        String occupationTel = enrollEdAdvisertel.getText().toString();
                        if (!isBindAd) {
                            //如果没有绑定顾问
                            if (!occupationTel.isEmpty()) {
                                if (NumberUtils.isMobileNo(occupationTel)) {

                                    Log.e("call", "------------------没绑定顾问，全部通过，发起请求");
                                    Map<String, String> map = new HashMap<>();
                                    map.put("phone", tel);
                                    map.put("vercode", provingNumber);
                                    map.put("theAgencyPhone", occupationTel);
                                    //发起请求
                                    NetUtils.callNet(20, CustomValue.SERVER + urlEnroll, map, callBack);

                                } else {
                                    Toast.makeText(this, "顾问的手机号格式不正确", Toast.LENGTH_SHORT).show();
                                    getFocusable(enrollEdAdvisertel);//获取焦点
                                }
                            }
                        } else {
                            Log.e("call", "------------------绑定了顾问，全部通过，发起请求");
                            //如果已经绑定了顾问
                            Map<String, String> map = new HashMap<>();
                            map.put("phone", tel);
                            map.put("vercode", provingNumber);
                            map.put("theAgencyPhone", occupationTel);
                            //发起请求
                            NetUtils.callNet(20, CustomValue.SERVER + urlEnroll, map, callBack);

                        }
                    }
                } else {
                    Toast.makeText(this, "您的手机号格式不正确", Toast.LENGTH_SHORT).show();
                    getFocusable(enrollEdInputtel);//获取焦点
                }
                break;
        }
    }

    /**
     * EditText获取焦点
     *
     * @param et 想要获取焦点的控件
     */
    private void getFocusable(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
    }
}
