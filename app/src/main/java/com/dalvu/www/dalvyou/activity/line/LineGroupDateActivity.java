package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LineGroupDateAdapter;
import com.dalvu.www.dalvyou.base.BaseFragment;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LineDetailDatabean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineGroupDateActivity extends BaseNoTitleActivity {

    @BindView(R.id.line_groupdate_tablayout)
    TabLayout lineGroupdateTablayout;
    @BindView(R.id.line_groupdate_recyclerview)
    RecyclerView lineGroupdateRecyclerview;
    private String[] prices = {"3999", "4799", "2998", "1999"};
    private SparseArray<BaseFragment> fragments;
    private List<LineDetailDatabean.TourSkuDateBean> groudDates;
    private TreeSet<String> monthsSet = new TreeSet<>();
    private Date date;
    private String[] dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_group_date);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //从上一个界面把团期信息传过来
        groudDates = (List<LineDetailDatabean.TourSkuDateBean>) intent.getSerializableExtra("groupdate");
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        getMonthSet();


        for (String month : monthsSet) {
            TabLayout.Tab tab = lineGroupdateTablayout.newTab();
            tab.setCustomView(R.layout.line_groupdate_tab);
            View view = tab.getCustomView();
            TextView line_groupdate_tab_month = (TextView) view.findViewById(R.id.line_groupdate_tab_month);
//            TextView line_groupdate_tab_price = (TextView) view.findViewById(R.id.line_groupdate_tab_price);
            dates = month.split("-");
            line_groupdate_tab_month.setText(Integer.valueOf(dates[1]) + "月");
//            line_groupdate_tab_price.setText("￥" + prices[i] + "起");
            lineGroupdateTablayout.addTab(tab);
        }
        TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
        lineGroupdateTablayout.addOnTabSelectedListener(tabSelectedListener);
        GridLayoutManager manager = new GridLayoutManager(this, 7);
        lineGroupdateRecyclerview.setAdapter(new LineGroupDateAdapter(this));
        lineGroupdateRecyclerview.setLayoutManager(manager);
    }

    private void getMonthSet() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String[] dates = null;
        //初始化传递过来的团期集合，将需要的数据放入对应的集合中
        for (LineDetailDatabean.TourSkuDateBean groupDate : groudDates) {
            //取出年月份，放入一个TreeSet集合中，可以实现去重和排序
            String start_time = groupDate.start_time;
            try {
                date = dateFormat.parse(start_time);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int day = calendar.get(Calendar.DAY_OF_WEEK);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dates = start_time.split("-");
            monthsSet.add(dates[0] + "-" + dates[1] + "01");
        }
    }


}
