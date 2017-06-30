package com.dalvu.www.dalvyou.fragment;

import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.bean.LineNoticeDataBean;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;

/**
 * 线路详情页注意事项
 * Created by user on 2017/6/7.
 */

public class LinedetailNoticeFragment extends BaseFragment {

    private StateView fragment_stateview;
    private MyCallBack callBack;
    private String id;
    private String url = "Api/index/detailsNotice";
    private TextView linenotice_notice;

    public LinedetailNoticeFragment(String id) {
        this.id = id;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.line_notice);
        linenotice_notice = (TextView) fragment_stateview.normal.findViewById(R.id.linenotice_notice);
    }

    @Override
    protected void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(fragment_stateview) {
                @Override
                public void onSucceed(int what, String json) {
                    //解析数据
                    LineNoticeDataBean lineNoticeDataBean = MyApplication.getGson().fromJson(json, LineNoticeDataBean.class);
                    if (lineNoticeDataBean.status.equals("00000")) {
                        linenotice_notice.setText(Html.fromHtml(lineNoticeDataBean.list.notice));
                        fragment_stateview.showNormal();
                    } else {
                        Toast.makeText(activity, lineNoticeDataBean.msg, Toast.LENGTH_SHORT).show();
                        fragment_stateview.showError();
                    }
                }
            };
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        NetUtils.callNet(CustomValue.LINENOTICE, CustomValue.SERVER + url, map, callBack);
    }

    @Override
    public void update() {

    }
}
