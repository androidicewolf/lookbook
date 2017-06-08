package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.SearchCityItemBox;

/**
 * 搜索页面显示热门城市的recyclerview的适配器
 * Created by user on 2017/6/8.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchCityItemBox> {
    private Context context;
    private String[] citys;

    public SearchRecyclerViewAdapter(Context context, String[] citys) {
        this.context = context;
        this.citys = citys;
    }

    @Override
    public SearchCityItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchCityItemBox(LayoutInflater.from(context)
                .inflate(R.layout.search_hotcitys_recycleritem, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchCityItemBox holder, int position) {
        holder.activity_search_hotcity_item.setText(citys[position]);
    }

    @Override
    public int getItemCount() {
        return citys.length;
    }
}
