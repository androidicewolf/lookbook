package com.dalvu.www.dalvyou.activity.personaldata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.StateView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PersonalReviseDataActivity extends BaseNoTitleActivity {

    @BindView(R.id.personal_revisedata_name_ed)
    EditText personalRevisedataNameEd;
    @BindView(R.id.personal_revisedata_nickname_ed)
    EditText personalRevisedataNicknameEd;
    @BindView(R.id.personal_revisedata_sex_tv)
    TextView personalRevisedataSexTv;
    @BindView(R.id.personal_revisedata_sex_rl)
    RelativeLayout personalRevisedataSexRl;
    @BindView(R.id.personal_revisedata_age_tv)
    TextView personalRevisedataAgeTv;
    @BindView(R.id.personal_revisedata_age_rl)
    RelativeLayout personalRevisedataAgeRl;
    @BindView(R.id.personal_revisedata_workyear_tv)
    TextView personalRevisedataWorkyearTv;
    @BindView(R.id.personal_revisedata_workyear_rl)
    RelativeLayout personalRevisedataWorkyearRl;
    @BindView(R.id.personal_revisedata_label_ed)
    EditText personalRevisedataLabelEd;
    @BindView(R.id.personal_revisedata_tel_ed)
    EditText personalRevisedataTelEd;
    @BindView(R.id.personal_revisedata_email_ed)
    EditText personalRevisedataEmailEd;
    @BindView(R.id.personal_revisedata_experience_ed)
    EditText personalRevisedataExperienceEd;
    @BindView(R.id.personal_revisedata_revisepassword_rl)
    RelativeLayout personalRevisedataRevisepasswordRl;
    private StateView activity_stateview;
    private ImageView iv_go_back;
    private TextView tv_dalv_title;
    private ArrayList<String> sex = new ArrayList<>();
    private ArrayList<String> workyears = new ArrayList<>();
    private TextView personal_save_tv;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_stateview);
        initView();
        initData();
    }

    private void initView() {
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        personal_save_tv = (TextView) findViewById(R.id.personal_save_tv);
        activity_stateview.addNormal(R.layout.activity_personal_revise_data);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
    }

    private void initData() {
        sex.add("男");
        sex.add("女");
        for (int i = 1; i <= 20; i++) {
            workyears.add(i + "");
        }
        tv_dalv_title.setText("修改个人资料");
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        personal_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断各个项目的内容有没有发生变化，如果发生变化，向服务器提交，如果没有变化不提交

                //保存数据，向服务器提交申请

            }
        });


    }

    @OnClick({R.id.personal_revisedata_sex_rl, R.id.personal_revisedata_age_rl, R.id.personal_revisedata_workyear_rl, R.id.personal_revisedata_revisepassword_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_revisedata_sex_rl:
                personalRevisedataSexRl.setOnClickListener(new View.OnClickListener() {
                    OptionsPickerView pvOptions;

                    @Override
                    public void onClick(View v) {
                        if (pvOptions == null) {
                            pvOptions = new OptionsPickerView.Builder(PersonalReviseDataActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    personalRevisedataSexTv.setText(sex.get(options1));
                                }
                            }).build();
                            pvOptions.setPicker(sex);
                        }
                        pvOptions.show();
                    }
                });
                break;
            case R.id.personal_revisedata_age_rl:

                Calendar startDate = Calendar.getInstance();
                startDate.set(2013, 1, 1);
                Calendar endDate = Calendar.getInstance();
                endDate.set(2020, 1, 1);
                TimePickerView pvTime = new TimePickerView.Builder(PersonalReviseDataActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        personalRevisedataAgeTv.setText(sdf.format(date));
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setRangDate(startDate, endDate)//起始终止年月日设定
                        .setLabel("年", "月", "日", null, null, null)
                        .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.show();
                break;
            case R.id.personal_revisedata_workyear_rl:
                personalRevisedataWorkyearRl.setOnClickListener(new View.OnClickListener() {
                    OptionsPickerView pvOptions;

                    @Override
                    public void onClick(View v) {
                        if (pvOptions == null) {
                            pvOptions = new OptionsPickerView.Builder(PersonalReviseDataActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {

                                }
                            }).build();
                            pvOptions.setNPicker(workyears, null, null);
                        }
                        pvOptions.show();
                    }
                });

                break;
            case R.id.personal_revisedata_revisepassword_rl:
                Intent intent = new Intent(this, PersonalRevisePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
