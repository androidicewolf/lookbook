package com.dalvu.www.dalvyou.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.line.LineDetailActivity;
import com.dalvu.www.dalvyou.adapter.ViewHolder.HomeFragmentItemBox;
import com.dalvu.www.dalvyou.bean.HomeFragmentLineDataBean;

import java.util.List;

/**首页整体的适配器
 * Created by user on 2017/5/16.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentItemBox>{
    private List<HomeFragmentLineDataBean.ListBean> items;
    private Context context;
    private HomeFragmentItemBox viewBox;

    public HomeFragmentAdapter(Context context, List<HomeFragmentLineDataBean.ListBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public HomeFragmentItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        viewBox = new HomeFragmentItemBox(LayoutInflater.from(context)
                .inflate(R.layout.home_fragment_item, parent, false));
        return viewBox;
    }

    @Override
    public void onBindViewHolder(HomeFragmentItemBox holder, int position) {
        HomeItemOnLickListener lickListener = new HomeItemOnLickListener(position);
        holder.itemView.setOnClickListener(lickListener);
        //设置数据
        holder.home_item_name.setText(items.get(position).name);
        holder.home_item_gosite.setText(items.get(position).departure);
        holder.home_item_price.setText(String.valueOf(Float.valueOf(items.get(position).min_price) / 100));
        Glide.with(context).load(items.get(position).cover_pic).into(holder.home_item_image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    private class HomeItemOnLickListener implements View.OnClickListener {
        private int position;

        private HomeItemOnLickListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            Log.e("call", "---------------------点击条目的索引是：" + position);
            Intent intent = new Intent(context, LineDetailActivity.class);
            intent.putExtra("id", items.get(position).id);
            context.startActivity(intent);
        }
    }
}
