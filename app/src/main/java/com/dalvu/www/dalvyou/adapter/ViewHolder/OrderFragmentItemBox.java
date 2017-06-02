package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 订单页面里xrecyclerview的ViewHolder
 * Created by wolf on 17.6.2.
 */

public class OrderFragmentItemBox extends RecyclerView.ViewHolder {
    public ImageView order_fragment_item_iv;
    public TextView order_fragment_item_title;
    public TextView order_fragment_item_date;
    public TextView order_fragment_item_price;
    public TextView order_fragment_item_state;

    public OrderFragmentItemBox(View itemView) {
        super(itemView);
        order_fragment_item_iv = (ImageView) itemView.findViewById(R.id.order_fragment_item_iv);
        order_fragment_item_title = (TextView) itemView.findViewById(R.id.order_fragment_item_title);
        order_fragment_item_date = (TextView) itemView.findViewById(R.id.order_fragment_item_date);
        order_fragment_item_price = (TextView) itemView.findViewById(R.id.order_fragment_item_price);
        order_fragment_item_state = (TextView) itemView.findViewById(R.id.order_fragment_item_state);
    }

    public void addClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }
}
