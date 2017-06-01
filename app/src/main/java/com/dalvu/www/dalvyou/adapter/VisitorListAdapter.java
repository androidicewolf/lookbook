package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.VisitorListItemBox;

import java.util.ArrayList;

/**
 * 我的直客列表中xrecyclerview的适配器
 * Created by user on 2017/6/1.
 */

public class VisitorListAdapter extends RecyclerView.Adapter<VisitorListItemBox> {
    private Context context;
    private ArrayList items;
    private VisitorListItemBox itemBox;

    public VisitorListAdapter(Context context, ArrayList items) {
        Log.e("call", "适配器被创建");
        this.context = context;
        this.items = items;
    }

    @Override
    public VisitorListItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("call", "VisitorListItemBox被创建");
        itemBox = new VisitorListItemBox(LayoutInflater.from(context).inflate(
                R.layout.visitor_list_xrecycleritem, parent, false));
        return itemBox;
    }

    @Override
    public void onBindViewHolder(VisitorListItemBox holder, int position) {
        Log.e("call", "VisitorListItemBox被赋值");
//        holder.visitorlist_item_icon.setImageResource();
        holder.visitorlist_item_name.setText("迪卡普里奥莱昂纳多");
        holder.visitorlist_item_tel.setText("110");
        holder.visitorlist_item_binddate.setText("公元前224年");
    }

    @Override
    public int getItemCount() {
        Log.e("call", "有10个条目");
//        return items.size();
        return 10;
    }
}
