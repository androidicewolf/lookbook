package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.line.LineListActivity;
import com.dalvu.www.dalvyou.adapter.ViewHolder.HomeFragmentItemBox;

import java.util.ArrayList;

/**
 * 线路分类页面里xrecyclerview的适配器
 * Created by user on 2017/6/9.
 */

public class LineAssortmentAdapter extends RecyclerView.Adapter<HomeFragmentItemBox> {
    private Context context;
    private ArrayList items;
    private String title;

    public LineAssortmentAdapter(Context context, ArrayList items, String title) {
        this.context = context;
        this.items = items;
        this.title = title;
    }

    @Override
    public HomeFragmentItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeFragmentItemBox(LayoutInflater.from(context)
                .inflate(R.layout.home_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeFragmentItemBox holder, int position) {
        holder.itemView.setOnClickListener(new LineSearchItemOnLickListener(position));
        //设置数据
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    private class LineSearchItemOnLickListener implements View.OnClickListener {
        private int position;

        private LineSearchItemOnLickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LineListActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("Url", "网址");
            context.startActivity(intent);
        }
    }
}
