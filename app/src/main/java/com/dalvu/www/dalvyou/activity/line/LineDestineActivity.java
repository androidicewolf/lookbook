package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.MainActivity;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LineDestineDataBean;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.function.ChangeNumber;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;

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
    @BindView(R.id.line_destine_date_ll)
    LinearLayout lineDestineDateLl;
    private TextView tv_dalv_title;
    private StateView activity_stateview;
    private Unbinder unbinder;
    private MyCallBack callBack;
    private int user_id;
    private String user_token;
    private String line_id;
    //加载页面数据
    private String url = "Api/agency/orderInfo";
    //提交数据的地址
    private String commitUrl = "Api/agency/orderInfoHandle";
    private LineDestineDataBean lineDestineDataBean;
    private String manPrice;
    private String childrenPrice;
    private String differencePrice;
    private float manprice;
    private float childrenprice;
    private float differenceprice;
    private float totalPrice;
    private ArrayList<String> dateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateview);
        line_id = getIntent().getStringExtra("line_id");
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

        user_id = AppUserDate.getUserId();
        user_token = AppUserDate.getUserToken();
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
                //内容改变时，同步调整最下面的总价
                manprice = Float.valueOf(manPrice) / 100 * manNumber;
                Log.e("call", "成人：====" + manprice);
                totalPrice = manprice + childrenprice + differenceprice;
                lineDestineTotalprice.setText(String.valueOf(totalPrice));
            }
        });
        ChangeNumber.subNumber(this, lineDestineChildrenSub, lineDestineChildrennumber, 0);
        ChangeNumber.addNumber(this, lineDestineChildrenAdd, lineDestineChildrennumber, 0);
        lineDestineChildrennumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = s.toString();
                int childrenNumber = Integer.valueOf(number);
                Log.e("call", "-----------------当前数字：" + childrenNumber);
                childrenprice = Float.valueOf(childrenPrice) / 100 * childrenNumber;
                Log.e("call", "直接转化成浮点型是否有问题：====" + childrenprice);
                totalPrice = manprice + childrenprice + differenceprice;
                lineDestineTotalprice.setText(String.valueOf(totalPrice));
            }
        });
        ChangeNumber.subNumber(this, lineDestineDifferenceSub, lineDestineDifferencenumber, 0);
        ChangeNumber.addNumber(this, lineDestineDifferenceAdd, lineDestineDifferencenumber, 0);
        lineDestineDifferencenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = s.toString();
                int differenceNumber = Integer.valueOf(number);
                Log.e("call", "-----------------当前数字：" + differenceNumber);
                differenceprice = Float.valueOf(differencePrice) / 100 * differenceNumber;
                Log.e("call", "单房差：====" + differenceprice);
                totalPrice = manprice + childrenprice + differenceprice;
                lineDestineTotalprice.setText(String.valueOf(totalPrice));
            }
        });

        if (callBack == null) {
            callBack = new MyCallBack(activity_stateview) {

                @Override
                public void onStart(int what) {
                    super.onStart(what);
                }

                @Override
                public void onSucceed(int what, String json) {
                    lineDestineDataBean = MyApplication.getGson().fromJson(json, LineDestineDataBean.class);
                    if (lineDestineDataBean.status.equals("00000")) {
                        if (!lineDestineDataBean.list.name.isEmpty()) {
                            manPrice = lineDestineDataBean.list.price_adult_agency;
                            childrenPrice = lineDestineDataBean.list.price_child_agency;
                            differencePrice = lineDestineDataBean.list.price_hotel_agency;
                            linedetailName.setText(lineDestineDataBean.list.name);
                            linePriceMan.setText(String.valueOf(Float.valueOf(manPrice) / 100));
                            linePriceChildren.setText(String.valueOf(Float.valueOf(childrenPrice) / 100));
                            if (lineDestineDataBean.tour_date.size() == 0) {
                                lineDestineDateLl.setClickable(false);
                                lineDestineDate.setText("暂无团期");
                            } else {
                                dateList.clear();
                                for (LineDestineDataBean.TourDateBean date : lineDestineDataBean.tour_date) {
                                    dateList.add(date.start_time);
                                }
                            }
                        }
                        activity_stateview.showNormal();
                    } else {
                        Toast.makeText(LineDestineActivity.this, lineDestineDataBean.msg, Toast.LENGTH_SHORT).show();
                        activity_stateview.showError();
                    }
                }
            };
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "" + user_id);
        map.put("sign_token", user_token);
        map.put("tour_id", line_id);
        NetUtils.callNet(26, CustomValue.SERVER + url, map, callBack);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.line_destine_commit, R.id.line_destine_date_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_destine_commit:
                if (lineDestineDataBean.tour_date.size() == 0) {
                    Toast.makeText(this, "此线路暂无团期，无法预订", Toast.LENGTH_SHORT).show();
                    break;
                }
                //判断输入的联系人和手机号是否为空
                String linkmanName = lineDestineLinkmanName.getText().toString();
                String linkmanPhone = lineDestineLinkmanPhone.getText().toString();

                if (lineDestineMannumber.getText().toString().equals("0")) {
                    Toast.makeText(this, "成人数不能为0，请重新选择", Toast.LENGTH_SHORT).show();
                } else if (linkmanName.isEmpty()) {
                    Toast.makeText(this, "联系人姓名不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(lineDestineLinkmanName);
                } else if (linkmanPhone.isEmpty()) {
                    Toast.makeText(this, "联系人手机号不能为空", Toast.LENGTH_SHORT).show();
                    getFocusable(lineDestineLinkmanPhone);
                } else if (!NumberUtils.isMobileNo(linkmanPhone)) {
                    Toast.makeText(this, "请输入正确的联系人手机号", Toast.LENGTH_SHORT).show();
                    getFocusable(lineDestineLinkmanPhone);
                } else {
                    String remarks = lineDestineRemarks.getText().toString();

                    //向服务器提交数据
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", "" + user_id);
                    map.put("sign_token", user_token);
                    map.put("id", line_id);
                    map.put("start_time", lineDestineDate.getText().toString());
                    map.put("client_adult_count", lineDestineMannumber.getText().toString());
                    map.put("client_child_count", lineDestineChildrennumber.getText().toString());
                    map.put("hotel_count", lineDestineDifferencenumber.getText().toString());
                    map.put("contact", linkmanName);
                    map.put("contact_phone", linkmanPhone);
                    map.put("memo", remarks);

                    NetUtils.callNet(27, CustomValue.SERVER + commitUrl, map, new MyCallBack() {
                        @Override
                        public void onStart(int what) {

                        }

                        @Override
                        public void onFailed(int what, int code) {
                            Toast.makeText(LineDestineActivity.this, "连接服务器失败，请检查网络", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucceed(int what, String json) {
                            ServerFeedback serverFeedback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                            if (serverFeedback.status.equals("00000")) {
                                //提交成功，跳转页面
                                Intent intent = new Intent(LineDestineActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                //提交失败，提示原因
                                Toast.makeText(LineDestineActivity.this, serverFeedback.msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.line_destine_date_ll:
                //选择团期
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        lineDestineDate.setText(dateList.get(options1));
                    }
                }).build();
                pvOptions.setNPicker(dateList, null, null);
                pvOptions.show();
                break;
        }
    }

    private void getFocusable(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
    }
}
