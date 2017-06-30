package com.dalvu.www.dalvyou.fragment;

import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.LineDescriptionDateBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.DensityUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 线路详情页产品亮点
 * Created by user on 2017/6/7.
 */

public class LinedetailDescriptionFragment extends BaseFragment {

    private StateView fragment_stateview;
    private LinearLayout line_description_ll;
    private ArrayList<String> items = new ArrayList<>();
    private MyCallBack callBack;
    private String url = "Api/index/detailsEdge";
    private String id;

    public LinedetailDescriptionFragment(String id) {
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.line_description);
        line_description_ll = (LinearLayout) fragment_stateview.normal.findViewById(R.id.line_description_ll);
    }

    @Override
    protected void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    Log.e("call", "产品亮点fragment=======" + json);
                    //解析数据
                    LineDescriptionDateBean lineDescriptionDateBean = MyApplication.getGson().fromJson(json, LineDescriptionDateBean.class);
                    if (lineDescriptionDateBean.status.equals("00000")) {
                        for (int i = 0; i < 1; i++) {
                            LinearLayout linearLayout = new LinearLayout(activity);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(0, 0, 0, DensityUtils.dip2px(activity, 20));
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                            ImageView imageView = new ImageView(activity);
                            imageView.setImageResource(R.mipmap.brightened_dot);
                            linearLayout.addView(imageView);
                            TextView textView = new TextView(activity);
                            textView.setTextSize(14);
                            textView.setTextColor(Color.parseColor("#3F3F3F"));
                            textView.setText(Html.fromHtml(lineDescriptionDateBean.list.description));
                            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                            textLayoutParams.setMargins(DensityUtils.dip2px(activity, 5), 0, 0, 0);
                            textView.setLayoutParams(textLayoutParams);
                            linearLayout.addView(textView);
                            line_description_ll.addView(linearLayout);
                        }
                        fragment_stateview.showNormal();
                    } else {
                        Toast.makeText(activity, lineDescriptionDateBean.msg, Toast.LENGTH_SHORT).show();
                        fragment_stateview.showError();
                    }
                }
            };
            HashMap<String, String> map = new HashMap<>();
            map.put("id", id);
            NetUtils.callNet(CustomValue.LINEDETAILBASE, CustomValue.SERVER + url, map, callBack);
        }
    }

    @Override
    public void update() {

    }
}
