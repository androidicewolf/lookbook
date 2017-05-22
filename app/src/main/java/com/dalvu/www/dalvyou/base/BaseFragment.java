package com.dalvu.www.dalvyou.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.netUtils.StateView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**抽取的父类Fragment
 * Created by user on 2017/5/9.
 */

public abstract class BaseFragment extends Fragment {
    public Activity activity;
    public View view;
    public Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        if(view == null){
            view = inflater.inflate(getLayoutId(), container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    /**让子类去实现返回要加载的布局文件的Id**/
    protected abstract int getLayoutId();

    /**让子类去实现具体的加载界面的操作**/
    protected abstract void initView();

    /**让子类去实现具体的加载数据的操作**/
    protected abstract void initData();

    /**让子类去实现具体的刷新数据的操作**/
    public abstract void update();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
