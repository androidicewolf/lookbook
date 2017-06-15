package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 线路团期里recyclerview的Viewholder
 * Created by user on 2017/6/15.
 */

public class LineGroupDateItemBox extends RecyclerView.ViewHolder {
    public TextView groupdate_item_day;
    public TextView groupdate_itme_childrenprice;
    public TextView groupdate_itme_manprice;

    public LineGroupDateItemBox(View itemView) {
        super(itemView);
        groupdate_item_day = (TextView) itemView.findViewById(R.id.groupdate_item_day);
        groupdate_itme_childrenprice = (TextView) itemView.findViewById(R.id.groupdate_itme_childrenprice);
        groupdate_itme_manprice = (TextView) itemView.findViewById(R.id.groupdate_itme_manprice);
    }
}
