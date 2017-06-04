package com.dalvu.www.dalvyou.fragment;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**资金页面
 * Created by user on 2017/5/9.
 */

public class BillFragment extends BaseFragment {

    private Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        StateView fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.bill_fragment);
        unbinder = ButterKnife.bind(this, fragment_stateview.normal);
        fragment_stateview.showNormal();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void update() {

    }
}
