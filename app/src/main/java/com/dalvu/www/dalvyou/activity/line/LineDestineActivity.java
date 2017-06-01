package com.dalvu.www.dalvyou.activity.line;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.function.ChangeNumber;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LineDestineActivity extends BaseNoTitleActivity {

    @BindView(R.id.line_destine_tv)
    TextView lineDestineTv;
    @BindView(R.id.line_destine_totalprice)
    TextView lineDestineTotalprice;
    @BindView(R.id.line_destine_commit)
    Button lineDestineCommit;
    @BindView(R.id.line_destine_rl)
    RelativeLayout lineDestineRl;
    @BindView(R.id.linedetail_name)
    TextView linedetailName;
    @BindView(R.id.line_price_man)
    TextView linePriceMan;
    @BindView(R.id.line_price_children)
    TextView linePriceChildren;
    @BindView(R.id.line_destine_date)
    TextView lineDestineDate;
    @BindView(R.id.line_destine_man_sub)
    ImageView lineDestineManSub;
    @BindView(R.id.line_destine_mannumber)
    TextView lineDestineMannumber;
    @BindView(R.id.line_destine_man_add)
    ImageView lineDestineManAdd;
    @BindView(R.id.line_destine_children_sub)
    ImageView lineDestineChildrenSub;
    @BindView(R.id.line_destine_childrennumber)
    TextView lineDestineChildrennumber;
    @BindView(R.id.line_destine_children_add)
    ImageView lineDestineChildrenAdd;
    @BindView(R.id.line_destine_difference_sub)
    ImageView lineDestineDifferenceSub;
    @BindView(R.id.line_destine_differencenumber)
    TextView lineDestineDifferencenumber;
    @BindView(R.id.line_destine_difference_add)
    ImageView lineDestineDifferenceAdd;
    @BindView(R.id.line_destine_linkman_name)
    EditText lineDestineLinkmanName;
    @BindView(R.id.line_destine_linkman_phone)
    EditText lineDestineLinkmanPhone;
    @BindView(R.id.line_destine_remarks)
    EditText lineDestineRemarks;
    private TextView tv_dalv_title;
    private StateView activity_stateview;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        initView();
        initData();
    }

    private void initView() {
        ImageView iv_go_back = (ImageView) findViewById(R.id.iv_go_back);
        iv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_dalv_title = (TextView) findViewById(R.id.tv_dalv_title);
        activity_stateview = (StateView) findViewById(R.id.activity_stateview);
        activity_stateview.addNormal(R.layout.line_detail_destine);
        unbinder = ButterKnife.bind(this, activity_stateview.normal);
    }

    private void initData() {
        tv_dalv_title.setText("填写与提交");
        ChangeNumber.subNumber(this, lineDestineManSub, lineDestineMannumber, 0);
        ChangeNumber.addNumber(this, lineDestineManAdd, lineDestineMannumber, 0);
        lineDestineMannumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = s.toString();
                int manNumber = Integer.valueOf(number);
                Log.e("call", "-----------------当前数字：" + manNumber);
            }
        });
    }

    @OnClick({R.id.line_destine_man_sub, R.id.line_destine_man_add, R.id.line_destine_children_sub, R.id.line_destine_children_add, R.id.line_destine_difference_sub, R.id.line_destine_difference_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_destine_man_sub:
                Log.e("call", "成人数减少");
                Log.e("call", "lineDestineMannumber的地址" + lineDestineMannumber.toString());
                break;
            case R.id.line_destine_man_add:
                Log.e("call", "成人数增加");
                Log.e("call", "lineDestineMannumber的地址" + lineDestineMannumber.toString());
                break;
            case R.id.line_destine_children_sub:
                Log.e("call", "儿童数减少");
                break;
            case R.id.line_destine_children_add:
                Log.e("call", "儿童数增加");
                break;
            case R.id.line_destine_difference_sub:
                Log.e("call", "单房差数减少");
                break;
            case R.id.line_destine_difference_add:
                Log.e("call", "单房差数增加");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
