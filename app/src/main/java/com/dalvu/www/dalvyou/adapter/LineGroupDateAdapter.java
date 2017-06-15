package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.LineGroupDateItemBox;

/**
 * 线路团期的recyclerview的适配器
 * Created by user on 2017/6/15.
 */

public class LineGroupDateAdapter extends RecyclerView.Adapter<LineGroupDateItemBox> {
    private Context context;

    public LineGroupDateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LineGroupDateItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            LineGroupDateItemBox lineGroupDateInitemBox = new LineGroupDateItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.line_groupdate_initem, parent, false));
            return lineGroupDateInitemBox;
        } else {
            LineGroupDateItemBox lineGroupDateUnitemBox = new LineGroupDateItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.line_groupdate_unitem, parent, false));

            return lineGroupDateUnitemBox;
        }
    }

    @Override
    public void onBindViewHolder(LineGroupDateItemBox holder, int position) {
        int viewType = holder.getItemViewType();
        if (viewType == 1) {
            holder.groupdate_itme_manprice.setText("399");
            holder.groupdate_itme_childrenprice.setText("2999");
        }
        holder.groupdate_item_day.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return 31;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 9 == 1) {
            return 1;
        }
        return 0;
    }
}
