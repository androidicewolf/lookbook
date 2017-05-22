package com.dalvu.www.dalvyou.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.fragment.BillFragment;
import com.dalvu.www.dalvyou.fragment.HomeFragment;
import com.dalvu.www.dalvyou.fragment.OrderFragment;
import com.dalvu.www.dalvyou.fragment.PersonalFragment;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.dalvu.www.dalvyou.tools.CustomValue;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_bottombar)
    BottomNavigationBar main_bottombar;
    private SparseArray<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initFragment();
        initBottomBar();
        initData();
    }

    private void initFragment() {
        fragments = new SparseArray<>();
        fragments.append(0, new HomeFragment());
        fragments.append(1, new OrderFragment());
        fragments.append(2, new BillFragment());
        fragments.append(3, new PersonalFragment());
    }

    private void initToolBar() {

    }

    private void initBottomBar() {
        MyOnTabSelectedListener onTabSelectedListener = new MyOnTabSelectedListener();
        main_bottombar.addItem(new BottomNavigationItem(R.mipmap.home, R.string.home)
                .setInActiveColor(R.color.selector).setActiveColor(R.color.inselector))
                .addItem(new BottomNavigationItem(R.mipmap.my_order, R.string.order)
                        .setInActiveColor(R.color.selector).setActiveColor(R.color.inselector))
                .addItem(new BottomNavigationItem(R.mipmap.finance, R.string.bill)
                        .setInActiveColor(R.color.selector).setActiveColor(R.color.inselector))
                .addItem(new BottomNavigationItem(R.mipmap.my, R.string.personal)
                        .setInActiveColor(R.color.selector).setActiveColor(R.color.inselector))
                .setFirstSelectedPosition(0)
                .initialise();
        main_bottombar.setTabSelectedListener(onTabSelectedListener);
        onTabSelectedListener.onTabSelected(0);
    }

    private class MyOnTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {
        @Override
        public void onTabSelected(int position) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Fragment fragment = fragments.get(position);
            if(fragments != null){
                if(fragment.isAdded()){
                    transaction.show(fragment);
                }else{
                    transaction.remove(fragment).add(R.id.main_frame, fragment);
                }
            }
            transaction.commit();
        }

        @Override
        public void onTabUnselected(int position) {
            if (fragments != null) {
                android.app.FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = fragments.get(position);
                transaction.hide(fragment);
                transaction.commit();
            }
        }

        @Override
        public void onTabReselected(int position) {
            Log.e("call", "onTabReselected被执行++++++++++++");
            if(fragments != null){
                fragments.get(position).update();
            }
        }
    }

    private void initData() {

    }

}
