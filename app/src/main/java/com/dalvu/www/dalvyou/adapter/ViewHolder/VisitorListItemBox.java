package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 我的直客列表里xrecyclerview的ViewHolder
 * Created by user on 2017/6/1.
 */

public class VisitorListItemBox extends RecyclerView.ViewHolder {
    public ImageView visitorlist_item_icon;
    public TextView visitorlist_item_name;
    public TextView visitorlist_item_tel;
    public TextView visitorlist_item_binddate;

    public VisitorListItemBox(View itemView) {
        super(itemView);
        visitorlist_item_icon = (ImageView) itemView.findViewById(R.id.visitorlist_item_icon);
        visitorlist_item_name = (TextView) itemView.findViewById(R.id.visitorlist_item_name);
        visitorlist_item_tel = (TextView) itemView.findViewById(R.id.visitorlist_item_tel);
        visitorlist_item_binddate = (TextView) itemView.findViewById(R.id.visitorlist_item_binddate);
    }
}
