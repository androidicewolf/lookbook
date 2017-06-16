package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.LineGroupDateItemBox;
import com.dalvu.www.dalvyou.bean.LineDetailDatabean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 线路团期的recyclerview的适配器
 * Created by user on 2017/6/15.
 */

public class LineGroupDateAdapter extends RecyclerView.Adapter<LineGroupDateItemBox> {
    private Context context;
    private String month;
    private HashMap<String, LineDetailDatabean.TourSkuDateBean> groupDateMap;
    //当月最大天数
    private int maxDate;
    //当月第一天周几
    private int fristDateWeek;
    //拼接当前的日期：2017-06-05
    private String dayStr;

    public LineGroupDateAdapter(Context context, String month, HashMap<String, LineDetailDatabean.TourSkuDateBean> groupDateMap) {
        this.context = context;
        this.month = month;
        this.groupDateMap = groupDateMap;
        Log.e("call", "--------传入适配器里的月份：" + month);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(month + "-01");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            fristDateWeek = calendar.get(Calendar.DAY_OF_WEEK);
            Log.e("call", "-------------当月第一天的周:" + fristDateWeek);
            calendar.roll(Calendar.DATE, -1);
            maxDate = calendar.get(Calendar.DATE);
            Log.e("call", "-------------当月最多的天数:" + maxDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LineGroupDateItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new LineGroupDateItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.line_groupdate_unitem, parent, false));
        }
        if (viewType == 1) {
            return new LineGroupDateItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.line_groupdate_initem, parent, false));
        }
        if (viewType == -1) {
            return new LineGroupDateItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.line_groupdate_unitem, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(LineGroupDateItemBox holder, int position) {
        int viewType = holder.getItemViewType();
        int dayInt = position - fristDateWeek + 2;
        if (viewType == 1) {
            String key;
            if (dayInt < 10) {
                key = month + "-0" + dayInt;
            } else {
                key = month + "-" + dayInt;
            }
            LineDetailDatabean.TourSkuDateBean groupDate = groupDateMap.get(key);
            holder.groupdate_item_day.setText("" + dayInt + "");
            holder.groupdate_itme_manprice.setText("￥" + Integer.valueOf(groupDate.price_adult_list) * 1.0f / 100 + "");
        }
        if (viewType == 0) {
            holder.groupdate_item_day.setText("" + dayInt + "");
        }
    }

    @Override
    public int getItemCount() {
        return maxDate + fristDateWeek - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < fristDateWeek - 1) {
            return -1;
        }
        int dayInt = position - fristDateWeek + 2;
        if (dayInt < 10) {
            dayStr = month + "-0" + dayInt;
        } else {
            dayStr = month + "-" + dayInt;
        }
        if (groupDateMap.containsKey(dayStr)) {
            return 1;
        }
        return 0;
    }
}
