package com.dalvu.www.dalvyou.fragment;

import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

/**
 * 线路详情页注意事项
 * Created by user on 2017/6/7.
 */

public class LinedetailNoticeFragment extends BaseFragment {

    private StateView fragment_stateview;
    private MyCallBack callBack;
    private TextView linenotice_notice;

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

                    //设置数据
                    fragment_stateview.showNormal();
                }
            };
        }
        NetUtils.callNet(CustomValue.LINENOTICE, CustomValue.SERVER + "/index.php/Api/index/details", callBack);
    }

    @Override
    public void update() {

    }
}
