package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.SearchCityItemBox;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.CustomValue;

/**
 * 搜索页面显示热门城市的recyclerview的适配器
 * Created by user on 2017/6/8.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchCityItemBox> {
    private Context context;
    private String[] citys;
    private MyCallBack callBack;

    public SearchRecyclerViewAdapter(Context context, String[] citys, MyCallBack callBack) {
        this.context = context;
        this.citys = citys;
        this.callBack = callBack;
    }

    @Override
    public SearchCityItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchCityItemBox(LayoutInflater.from(context)
                .inflate(R.layout.search_hotcitys_recycleritem, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchCityItemBox holder, final int position) {
        holder.activity_search_hotcity_item.setText(citys[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用搜索接口，传输数组citys的文字
                NetUtils.callNet(14, CustomValue.SERVER + "/index.php/Api/index/indexMod", callBack);
            }
        });
    }

    @Override
    public int getItemCount() {
        return citys.length;
    }
}
