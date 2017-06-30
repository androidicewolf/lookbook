package com.dalvu.www.dalvyou.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.AdEnrollBean;
import com.dalvu.www.dalvyou.bean.EnrollGetNumberBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.NumberUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdviserEnrollActivity extends BaseNoTitleActivity {

    @BindView(R.id.login_iv_back)
    ImageView loginIvBack;
    @BindView(R.id.enroll_ed_name)
    EditText enrollEdName;
    @BindView(R.id.enroll_tv_selectorcity)
    TextView enrollTvSelectorcity;
    @BindView(R.id.enroll_selectorcity_tv_ll)
    LinearLayout enrollSelectorcityTvLl;
    @BindView(R.id.enroll_ed_inputtel)
    EditText enrollEdInputtel;
    @BindView(R.id.enroll_btn_getprovingnumber)
    TextView enrollBtnGetprovingnumber;
    @BindView(R.id.enroll_ed_inputprovingnumber)
    EditText enrollEdInputprovingnumber;
    @BindView(R.id.enroll_ed_inputpassword)
    EditText enrollEdInputpassword;
    @BindView(R.id.enroll_ed_confirmpassword)
    EditText enrollEdConfirmpassword;
    @BindView(R.id.enroll_ed_occupation)
    EditText enrollEdOccupation;
    @BindView(R.id.enroll_btn_enroll)
    Button enrollBtnEnroll;
    @BindView(R.id.enroll_selectorcity_input)
    EditText enrollSelectorcityInput;
    @BindView(R.id.enroll_selectorcity_et_ll)
    LinearLayout enrollSelectorcityEtLl;
    @BindView(R.id.enroll_btn_getprovingnumber_ll)
    LinearLayout enrollBtnGetprovingnumberLl;
    private CountDownTimer countDownTimer;
    //自定义网络回调
    private MyCallBack callBack;
    private String tel;
    private String name;
    private String city;
    private String cityNumber = "1";
    private String provingNumber;
    private String password;
    private String confirmPassword;
    private String occupation;
    //获取验证码的接口
    private String urlGetNumber = "Api/login/agencyVerificationCode";
    //顾问注册接口
    private String urlEnroll = "Api/login/agencyRegister";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adviser_enroll);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        enrollTvSelectorcity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("其他")) {
                    cityNumber = 1 + "";
                    enrollSelectorcityTvLl.setVisibility(View.GONE);
                    enrollSelectorcityEtLl.setVisibility(View.VISIBLE);
                    enrollSelectorcityInput.requestFocus();
                }
            }
        });
    }

    private void initData() {
        CustomValue.CITYS.add("其他");
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
                                    enrollBtnGetprovingnumber.setTextColor(ContextCompat.getColor(AdviserEnrollActivity.this, R.color.dalvbule));
                                    enrollBtnGetprovingnumber.setText("重新获取验证码");
                                }
                            };
                        }
                        countDownTimer.start();
                        break;
                    case 20:
                        //加载中的动画开始执行
                        break;
                }
            }

            @Override
            public void onSucceed(int what, String json) {
                //解析数据
                switch (what) {
                    case 19:
                        EnrollGetNumberBean enrollGetNumberBean = MyApplication.getGson().fromJson(json, EnrollGetNumberBean.class);
                        Log.e("call", "发送验证码请求成功，响应码=======" + enrollGetNumberBean.status);
                        if (!enrollGetNumberBean.status.equals("0000")) {
                            Toast.makeText(AdviserEnrollActivity.this, enrollGetNumberBean.msg, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 20:
                        //注册成功与否
                        AdEnrollBean adEnrollBean = MyApplication.getGson().fromJson(json, AdEnrollBean.class);
                        if (adEnrollBean.status.equals("00000")) {
                            //注册成功，跳转页面
                            Toast.makeText(AdviserEnrollActivity.this, "注册成功，请登陆", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AdviserEnrollActivity.this, adEnrollBean.msg, Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
            }

            @Override
            public void onFailed(int what, int code) {
                switch (what) {
                    case 19:
                        Toast.makeText(AdviserEnrollActivity.this, "网络异常，获取验证码失败，请稍后再试。", Toast.LENGTH_SHORT).show();
                        countDownTimer.cancel();
                        countDownTimer.onFinish();
                        break;
                    case 20:
                        Toast.makeText(AdviserEnrollActivity.this, "网络异常，注册失败，请检查网络后重试！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFinish(int what) {
                //加载中的动画隐藏
            }
        };
    }

    @OnClick({R.id.login_iv_back, R.id.enroll_selectorcity_tv_ll, R.id.enroll_btn_getprovingnumber, R.id.enroll_btn_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv_back:
                finish();
                break;
            case R.id.enroll_selectorcity_tv_ll:
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(AdviserEnrollActivity.this,
                        new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                enrollTvSelectorcity.setText(CustomValue.CITYS.get(options1));
                                cityNumber = options1 + 1 + "";
                            }
                        }).build();
                pvOptions.setNPicker(CustomValue.CITYS, null, null);
                pvOptions.show();
                break;
            case R.id.enroll_btn_getprovingnumber:
                //获取验证码
                tel = enrollEdInputtel.getText().toString();
                if (tel.isEmpty()) {
                    Toast.makeText(this, "输入的手机号不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(enrollEdInputtel);//获取焦点
                } else {
                    //判断手机号是否合法
                    if (NumberUtils.isMobileNo(tel)) {

                        //请求服务器发送验证码
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
                //提交注册信息
                name = enrollEdName.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(enrollEdName);//获取焦点
                } else {
                    city = "";
                    if (enrollSelectorcityTvLl.getVisibility() == View.VISIBLE) {
                        city = enrollTvSelectorcity.getText().toString();
                    } else if (enrollSelectorcityEtLl.getVisibility() == View.VISIBLE) {
                        city = enrollSelectorcityInput.getText().toString();
                    }
                    tel = enrollEdInputtel.getText().toString();
                    if (tel.isEmpty()) {
                        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                        getFocusable(enrollEdInputtel);
                    } else {
                        if (!NumberUtils.isMobileNo(tel)) {
                            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                            getFocusable(enrollEdInputtel);
                        } else {
                            provingNumber = enrollEdInputprovingnumber.getText().toString();
                            if (provingNumber.isEmpty()) {
                                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                                getFocusable(enrollEdInputprovingnumber);
                            } else {
                                password = enrollEdInputpassword.getText().toString();
                                if (password.isEmpty()) {
                                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                                    getFocusable(enrollEdInputpassword);
                                } else {
                                    if (!NumberUtils.isPassword(password)) {
                                        Toast.makeText(this, "您输入的密码格式不正确", Toast.LENGTH_SHORT).show();
                                        getFocusable(enrollEdInputpassword);
                                    } else {
                                        confirmPassword = enrollEdConfirmpassword.getText().toString();
                                        if (confirmPassword.equals(password)) {
                                            occupation = enrollEdOccupation.getText().toString();
                                            Log.e("call", "--------注册条件判断全部通过");

                                            //添加参数
                                            Map<String, String> map = new HashMap<>();
                                            map.put("name", name);
                                            map.put("province ", cityNumber);
                                            map.put("thecity", city);
                                            map.put("phone", tel);
                                            map.put("vercode", provingNumber);
                                            map.put("password", password);
                                            map.put("vocation", occupation);
                                            //请求服务器，传递参数
                                            NetUtils.callNet(20, CustomValue.SERVER + urlEnroll, map, callBack);

                                        } else {
                                            Toast.makeText(this, "您两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                                            getFocusable(enrollEdConfirmpassword);
                                        }
                                    }
                                }
                            }
                        }
                    }
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
