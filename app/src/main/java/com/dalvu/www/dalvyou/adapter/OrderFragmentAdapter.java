package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.order.LineOrderDetailActivity;
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
        holder.addClickListener(new LineOrderdetailItemListener(position));
    }

    @Override
    public int getItemCount() {
//        return items.size();
        return 8;
    }

    private class LineOrderdetailItemListener implements View.OnClickListener {
        private int position;

        private LineOrderdetailItemListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LineOrderDetailActivity.class);
            context.startActivity(intent);
        }
    }
}
