package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 搜索界面里热门城市的ViewHolder
 * Created by user on 2017/6/8.
 */

public class SearchCityItemBox extends RecyclerView.ViewHolder {
    public TextView activity_search_hotcity_item;

    public SearchCityItemBox(View itemView) {
        super(itemView);
        activity_search_hotcity_item = (TextView) itemView.findViewById(R.id.activity_search_hotcity_item);
    }
}
