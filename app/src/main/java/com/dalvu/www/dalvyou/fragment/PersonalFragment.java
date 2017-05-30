package com.dalvu.www.dalvyou.fragment;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.netUtils.StateView;

/**展示个人中心的内容
 * Created by user on 2017/5/9.
 */

public class PersonalFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stateview;
    }

    @Override
    protected void initView() {
        StateView fragment_stateview = (StateView) view.findViewById(R.id.fragment_stateview);
        fragment_stateview.addNormal(R.layout.personal_fragment);
        fragment_stateview.showNormal();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void update() {

    }
}
