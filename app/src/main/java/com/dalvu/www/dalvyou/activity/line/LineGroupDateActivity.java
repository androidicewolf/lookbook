package com.dalvu.www.dalvyou.activity.line;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.LineGroupDateAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.bean.LineDetailDatabean;
import com.dalvu.www.dalvyou.function.CustomGridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineGroupDateActivity extends BaseNoTitleActivity {

    @BindView(R.id.line_groupdate_tablayout)
    TabLayout lineGroupdateTablayout;
    @BindView(R.id.line_groupdate_recyclerview)
    RecyclerView lineGroupdateRecyclerview;
    @BindView(R.id.line_groupdate_ll)
    LinearLayout lineGroupdateLl;
    private List<LineDetailDatabean.TourSkuDateBean> groudDates;
    private TreeSet<String> monthsSet = new TreeSet<>();
    //    private Date date;
    private HashMap<String, LineDetailDatabean.TourSkuDateBean> groupDateMap;
    private HashMap<String, Integer> minPrice;
    //    private DateFormat dateFormat;

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
        lineGroupdateLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (groupDateMap == null) {
            groupDateMap = new HashMap<>();
            for (LineDetailDatabean.TourSkuDateBean groupDate : groudDates) {
                //取出年月份，放入一个TreeSet集合中，可以实现去重和排序
                String start_time = groupDate.start_time;
                groupDateMap.put(start_time, groupDate);
            }
        }
        getAdapterSet();
        String[] dates;
        int index = 0;
        final String[] months = new String[monthsSet.size()];
        for (String month : monthsSet) {
            months[index] = month;
            TabLayout.Tab tab = lineGroupdateTablayout.newTab();
            tab.setCustomView(R.layout.line_groupdate_tab);
            View view = tab.getCustomView();
            TextView line_groupdate_tab_month = (TextView) view.findViewById(R.id.line_groupdate_tab_month);
            TextView line_groupdate_tab_price = (TextView) view.findViewById(R.id.line_groupdate_tab_price);
            dates = month.split("-");
            line_groupdate_tab_month.setText(Integer.valueOf(dates[1]) + "月");
            line_groupdate_tab_price.setText("￥" + minPrice.get(month) * 1f / 100 + "起");
            lineGroupdateTablayout.addTab(tab);
            index++;
        }
        final ArrayList<LineGroupDateAdapter> list = new ArrayList<>();
        for (int i = 0; i < months.length; i++) {
            list.add(new LineGroupDateAdapter(this, months[i], groupDateMap));
        }
        TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                lineGroupdateRecyclerview.setAdapter(list.get(position));
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
        CustomGridLayoutManager manager = new CustomGridLayoutManager(this, 7);
        manager.setScrollEnabled(false);
        lineGroupdateRecyclerview.setAdapter(new LineGroupDateAdapter(this, months[0], groupDateMap));
        lineGroupdateRecyclerview.setLayoutManager(manager);
    }

    private void getAdapterSet() {
//        if(dateFormat == null){
//            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        }
        if (minPrice == null) {
            minPrice = new HashMap<>();
//        Date date = null;
            String[] dateStr;
            int price;
            int priceMin = 0;
            //初始化传递过来的团期集合，将需要的数据放入对应的集合中
            for (LineDetailDatabean.TourSkuDateBean groupDate : groudDates) {
                //取出年月份，放入一个TreeSet集合中，可以实现去重和排序
                String start_time = groupDate.start_time;

//            try {
//                date = dateFormat.parse(start_time);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(date);
//                int day = calendar.get(Calendar.DAY_OF_WEEK);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
                dateStr = start_time.split("-");
                monthsSet.add(dateStr[0] + "-" + dateStr[1]);


                //取出销售价格，放入集合中用于在月份下面显示多少多少元起
                price = Integer.valueOf(groupDate.price_adult_list);
                boolean isbe = minPrice.containsKey(dateStr[0] + "-" + dateStr[1]);
                if (isbe) {
                    priceMin = minPrice.get(dateStr[0] + "-" + dateStr[1]);
                    Log.e("call", "-----------------price存在:price=" + priceMin);
                    if (price < priceMin) {
                        minPrice.put(dateStr[0] + "-" + dateStr[1], price);
                    }
                } else {
                    Log.e("call", dateStr[0] + "-" + dateStr[1] + "======" + priceMin);
                    minPrice.put(dateStr[0] + "-" + dateStr[1], price);
                }
            }
            //循环结束以后得到三个集合，一个是存月份的，一个是存月份对应的下面最低价的，一个是存日期对应的线路价格对象的
        }
    }
}
