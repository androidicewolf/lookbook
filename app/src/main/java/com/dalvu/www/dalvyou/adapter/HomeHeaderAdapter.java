package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.line.LineAssortmentActivity;
import com.dalvu.www.dalvyou.adapter.ViewHolder.HeaderViewBox;

import java.util.TreeMap;

/**首页中XRecyclerView的头条目里的recyclerview的适配器
 * Created by user on 2017/5/16.
 */

public class HomeHeaderAdapter extends RecyclerView.Adapter<HeaderViewBox> {
    private Context context;
    private TreeMap<String, Integer> modules;
    public HomeHeaderAdapter(Context context, TreeMap<String, Integer> modules){
        this.context = context;
        this.modules = modules;
    }
    @Override
    public HeaderViewBox onCreateViewHolder(ViewGroup parent, int viewType) {
        HeaderViewBox headerViewBox = new HeaderViewBox(LayoutInflater.from(context).inflate(R.layout.home_header_module,
                parent, false));
        return headerViewBox;
    }

    @Override
    public void onBindViewHolder(HeaderViewBox holder, int position) {
        holder.textView.setText("国内游");
        holder.imageView.setImageResource(modules.get("国内游"));
        holder.imageView.setOnClickListener(new MyOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    private class MyOnClickListener implements View.OnClickListener{
        private int position;

        private MyOnClickListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LineAssortmentActivity.class);
            intent.putExtra("title", "国内游");
            context.startActivity(intent);
        }
    }
}
