package com.dalvu.www.dalvyou.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.line.LineDetailActivity;
import com.dalvu.www.dalvyou.adapter.ViewHolder.HomeFragmentItemBox;

import java.util.ArrayList;

/**首页整体的适配器
 * Created by user on 2017/5/16.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentItemBox>{
    private ArrayList items;
    private Context context;
    private HomeFragmentItemBox viewBox;

    public HomeFragmentAdapter(Context context, ArrayList items){
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
        holder.addOnClickListener(lickListener);
    }

    @Override
    public int getItemCount() {
        return 9;
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
            intent.putExtra("Url", "网址");
            context.startActivity(intent);
        }
    }
}
