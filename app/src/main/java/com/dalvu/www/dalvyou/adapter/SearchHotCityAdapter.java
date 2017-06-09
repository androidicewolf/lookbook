package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 搜索界面热门城市GridView的适配器
 * Created by user on 2017/6/9.
 */

public class SearchHotCityAdapter extends BaseAdapter {
    private Context context;
    private String[] citys;

    public SearchHotCityAdapter(Context context, String[] citys) {
        this.context = context;
        this.citys = citys;
    }

    @Override
    public int getCount() {
        return citys.length;
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_hotcitys_recycleritem, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.activity_search_hotcity_item);
        tv.setText(citys[position]);
        return view;
    }
}
