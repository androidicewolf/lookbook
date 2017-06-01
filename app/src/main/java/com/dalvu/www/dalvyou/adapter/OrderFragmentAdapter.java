package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.OrderFragmentItemBox;

import java.util.ArrayList;

/**
 * 订单页面里xrecyclerview的适配器
 * Created by wolf on 17.6.2.
 */

public class OrderFragmentAdapter extends RecyclerView.Adapter<OrderFragmentItemBox> {
    private Context context;
    private ArrayList items;

    public OrderFragmentAdapter(Context context, ArrayList items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public OrderFragmentItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderFragmentItemBox itemBox = new OrderFragmentItemBox(LayoutInflater.from(context)
                .inflate(R.layout.order_fragment_xrecycleritem, parent, false));
        return itemBox;
    }

    @Override
    public void onBindViewHolder(OrderFragmentItemBox holder, int position) {
//        holder.order_fragment_item_iv.setImageResource();
    }

    @Override
    public int getItemCount() {
//        return items.size();
        return 8;
    }
}
