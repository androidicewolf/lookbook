package com.dalvu.www.dalvyou.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.fragment.BillFragment;
import com.dalvu.www.dalvyou.fragment.HomeFragment;
import com.dalvu.www.dalvyou.fragment.OrderFragment;
import com.dalvu.www.dalvyou.fragment.PersonalFragment;
import com.dalvu.www.dalvyou.tools.AppUserDate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_bottombar)
    BottomNavigationBar main_bottombar;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    private SparseArray<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        int userType = AppUserDate.getUserType();
        initFragment(userType);
        initBottomBar(userType);
        initData();
    }

    private void initFragment(int type) {
        if (fragments == null) {
            fragments = new SparseArray<>();
        } else {
            fragments.clear();
        }
        fragments.append(0, new HomeFragment());
        fragments.append(1, new OrderFragment());
        switch (type) {
            case 0:
                fragments.append(2, new BaseFragment() {
                    @Override
                    protected int getLayoutId() {
                        return R.layout.main_unlogin;
                    }

                    @Override
                    protected void initView() {

                    }

                    @Override
                    protected void initData() {

                    }

                    @Override
                    public void update() {

                    }
                });
            case 1:
                fragments.append(2, new PersonalFragment());
                break;
            case 2:
                fragments.append(2, new BillFragment());
                fragments.append(3, new PersonalFragment());
                break;
        }
    }

    private void initToolBar() {
        View view = LayoutInflater.from(this).inflate(R.layout.main_toolbar_view, mainToolbar, false);
        mainToolbar.addView(view);
    }

    private void initBottomBar(int type) {
        MyOnTabSelectedListener onTabSelectedListener = new MyOnTabSelectedListener(type);
        main_bottombar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        main_bottombar.setMode(BottomNavigationBar.MODE_FIXED);
        main_bottombar
                .addItem(new BottomNavigationItem(R.mipmap.home_highlight, R.string.home)
                        .setActiveColor(R.color.selector)
                        .setInactiveIconResource(R.mipmap.home))
                .addItem(new BottomNavigationItem(R.mipmap.order_highlight, R.string.order)
                        .setActiveColor(R.color.selector)
                        .setInactiveIconResource(R.mipmap.order));
        switch (type) {
            case 0:
            case 1:
                main_bottombar
                        .addItem(new BottomNavigationItem(R.mipmap.my_highligh, R.string.personal)
                                .setActiveColor(R.color.selector)
                                .setInactiveIconResource(R.mipmap.my));
                break;
            case 2:
                main_bottombar
                        .addItem(new BottomNavigationItem(R.mipmap.financial_highlight, R.string.bill)
                                .setActiveColor(R.color.selector)
                                .setInactiveIconResource(R.mipmap.financial))
                        .addItem(new BottomNavigationItem(R.mipmap.my_highligh, R.string.personal)
                                .setActiveColor(R.color.selector)
                                .setInactiveIconResource(R.mipmap.my));
                break;
        }

        main_bottombar.setFirstSelectedPosition(0).initialise();
        main_bottombar.setTabSelectedListener(onTabSelectedListener);
        onTabSelectedListener.onTabSelected(0);
    }

    private void initData() {

    }

    private class MyOnTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {
        private int type;

        public MyOnTabSelectedListener(int type) {
            this.type = type;
        }
        @Override
        public void onTabSelected(int position) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (fragments != null) {
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    transaction.show(fragment);
                } else {
                    transaction.remove(fragment).add(R.id.main_frame, fragment);
                }
            }
            transaction.commit();
            if (position == 2) {
                if (type == 0) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }

        @Override
        public void onTabUnselected(int position) {
            Log.e("call", "onTabUnselected被执行++++++++++++++" + position);
            if (fragments != null) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = fragments.get(position);
                transaction.hide(fragment);
                transaction.commit();
            }
        }

        @Override
        public void onTabReselected(int position) {
            Log.e("call", "onTabReselected被执行++++++++++++");
            if (fragments != null) {
                fragments.get(position).update();
            }
        }
    }
}
