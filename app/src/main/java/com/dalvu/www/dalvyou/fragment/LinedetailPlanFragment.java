package com.dalvu.www.dalvyou.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.LinePlanDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;
import java.util.List;

/**
 * 线路详情页行程安排
 * Created by user on 2017/6/7.
 */

public class LinedetailPlanFragment extends BaseFragment {

    private StateView fragment_stateview;
    private LinearLayout line_plan_ll;
    private List<LinePlanDataBean.TourDescriptionBean> items;
    private String url = "Api/index/detailsScheduling";
    private String id;

    public LinedetailPlanFragment(String id) {
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.line_plan);
        line_plan_ll = (LinearLayout) fragment_stateview.normal.findViewById(R.id.line_plan_ll);
    }

    @Override
    protected void initData() {
        MyCallBack callBack = new MyCallBack(fragment_stateview) {
            @Override
            public void onSucceed(int what, String json) {
                //解析数据
                //加载线路的行程安排
                LinePlanDataBean linePlanDataBean = MyApplication.getGson().fromJson(json, LinePlanDataBean.class);
                if (linePlanDataBean.status.equals("00000")) {
                    items = linePlanDataBean.tour_description;
                    //正常解析
                    if (items.size() > 0) {
                        for (int i = 0; i < items.size(); i++) {
                            View view = LayoutInflater.from(activity).inflate(R.layout.line_plan_item, null, true);
                            //找到控件设置数据
                            LinePlanDataBean.TourDescriptionBean plan = items.get(i);
                            TextView date = (TextView) view.findViewById(R.id.lineplan_item_date);
                            date.setText("第" + (i + 1) + "天");
                            TextView planinfo = (TextView) view.findViewById(R.id.lineplan_item_planinfo);
                            planinfo.setText(plan.description);
                            TextView plan_dinner = (TextView) view.findViewById(R.id.lineplan_item_dinner);
                            plan_dinner.setText(plan.dining);
                            TextView plan_hotel = (TextView) view.findViewById(R.id.lineplan_item_hotel);
                            plan_hotel.setText(plan.hotel);
                            line_plan_ll.addView(view);
                        }
                    }
                    fragment_stateview.showNormal();
                } else {
                    Toast.makeText(activity, linePlanDataBean.msg, Toast.LENGTH_SHORT).show();
                    fragment_stateview.showError();
                }
            }
        };
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        NetUtils.callNet(CustomValue.LINEPLAN, CustomValue.SERVER + url, map, callBack);
    }

    @Override
    public void update() {

    }
}
