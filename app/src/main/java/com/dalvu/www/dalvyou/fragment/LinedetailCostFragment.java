package com.dalvu.www.dalvyou.fragment;

import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.LineCostDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;

/**
 * 线路详情页费用说明
 * Created by user on 2017/6/7.
 */

public class LinedetailCostFragment extends BaseFragment {

    private StateView fragment_stateview;
    private MyCallBack callBack;
    private String id;
    private String url = "Api/index/detailsCostExplain";
    private TextView linecost_includecost;
    private TextView linecost_uncludecost;

    public LinedetailCostFragment(String id) {
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.line_cost);
        linecost_includecost = (TextView) fragment_stateview.normal.findViewById(R.id.linecost_includecost);
        linecost_uncludecost = (TextView) fragment_stateview.normal.findViewById(R.id.linecost_uncludecost);
    }

    @Override
    protected void initData() {
        //解析数据
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    LineCostDataBean lineCostDataBean = MyApplication.getGson().fromJson(json, LineCostDataBean.class);
                    //解析数据
                    if (lineCostDataBean.status.equals("00000")) {
                        Log.e("call", "费用说明fragment：=======" + json);
                        if (lineCostDataBean.list != null) {
                            linecost_includecost.setText(Html.fromHtml(lineCostDataBean.list.fee_include));
                            linecost_uncludecost.setText(Html.fromHtml(lineCostDataBean.list.fee_exclude));
                        }
                        //显示文本
                        fragment_stateview.showNormal();
                    } else {
                        Toast.makeText(activity, lineCostDataBean.msg, Toast.LENGTH_SHORT).show();
                        fragment_stateview.showError();
                    }
                }
            };
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        NetUtils.callNet(CustomValue.LINECOST, CustomValue.SERVER + url, map, callBack);
    }

    @Override
    public void update() {

    }
}
