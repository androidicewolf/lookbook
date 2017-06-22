package com.dalvu.www.dalvyou.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.fragment.BillFragment;
import com.dalvu.www.dalvyou.fragment.HomeFragment;
import com.dalvu.www.dalvyou.fragment.OrderFragment;
import com.dalvu.www.dalvyou.fragment.PersonalFragment;
import com.dalvu.www.dalvyou.tools.AppUserDate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseNoTitleActivity {
    @BindView(R.id.main_bottombar)
    BottomNavigationBar main_bottombar;
    int userId;
    private SparseArray<BaseFragment> fragments;
    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userType = AppUserDate.getUserType();
        userId = AppUserDate.getUserId();
        Log.e("call", "从本地取出来的user_type========" + userType);
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
            case 5:
                Log.e("call", "type为" + type + "，没有登陆");
                fragments.append(2, new PersonalFragment());
                break;
            case 4:
                Log.e("call", "type为2，顾问登陆");
                fragments.append(2, new BillFragment());
                fragments.append(3, new PersonalFragment());
                break;
        }
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
            case 5:
                main_bottombar
                        .addItem(new BottomNavigationItem(R.mipmap.my_highligh, R.string.personal)
                                .setActiveColor(R.color.selector)
                                .setInactiveIconResource(R.mipmap.my));
                break;
            case 4:
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        Log.e("call", "-------------------------onKeyDown被执行");
        MyApplication.getMyApplication().closeActivity();
//            return false;
//        }else {
        return super.onKeyDown(keyCode, event);
//        }
    }

    private class MyOnTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {
        private int type;

        private MyOnTabSelectedListener(int type) {
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
            Log.e("call", "添加成功");
            transaction.commit();
            if (userId == 0) {
                switch (type) {
                    case 4:
                        if (position == 3) {
                            Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                            startActivityForResult(intent, 1);
                        }
                        break;
                    case 0:
                    case 5:

                        break;
                }
                if (type == 0) {
                    Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                    startActivity(intent);
                }
            }
        }

        @Override
        public void onTabUnselected(int position) {
            Log.e("call", "onTabUnselected被执行++++++++++++++" + position);
            if (fragments != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment fragment = fragments.get(position);
                transaction.hide(fragment);
                transaction.commit();
            }
        }

        @Override
        public void onTabReselected(int position) {
            Log.e("call", "onTabReselected被执行++++++++++++");
            if (position == 2) {
                if (userId == 0) {
                    Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                    startActivity(intent);
                }
            } else {
                if (fragments != null) {
                    fragments.get(position).update();
                }
            }
        }
    }
}
