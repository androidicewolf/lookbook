package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.MyAdviserItemBox;

/**
 * 我的顾问列表界面中Xrecyclerview的适配器
 * Created by user on 2017/6/16.
 */

public class MyAdviserAdapter extends RecyclerView.Adapter<MyAdviserItemBox> {
    private Context context;

    public MyAdviserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyAdviserItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdviserItemBox(LayoutInflater.from(context)
                .inflate(R.layout.myadviser_xrecycleritem, parent, false));
    }

    @Override
    public void onBindViewHolder(MyAdviserItemBox holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
