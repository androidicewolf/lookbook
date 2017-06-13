package com.dalvu.www.dalvyou.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.tools.CustomValue;

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
                    enrollSelectorcityTvLl.setVisibility(View.GONE);
                    enrollSelectorcityEtLl.setVisibility(View.VISIBLE);
                    enrollSelectorcityInput.requestFocus();
                }
            }
        });
    }

    private void initData() {

    }

    @OnClick({R.id.login_iv_back, R.id.enroll_selectorcity_tv_ll, R.id.enroll_btn_getprovingnumber, R.id.enroll_btn_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv_back:
                finish();
                break;
            case R.id.enroll_selectorcity_tv_ll:
                CustomValue.CITYS.add("其他");
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(AdviserEnrollActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        enrollTvSelectorcity.setText(CustomValue.CITYS.get(options1));
                    }
                }).build();
                pvOptions.setNPicker(CustomValue.CITYS, null, null);
                pvOptions.show();
                break;
            case R.id.enroll_btn_getprovingnumber:
                //获取验证码

                break;
            case R.id.enroll_btn_enroll:

                break;
        }
    }
}
