package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.LinePictureItemBox;

import java.util.ArrayList;

/**
 * 线路图片库里recyclerview的适配器
 * Created by user on 2017/5/23.
 */

public class LinePictureAdapter extends RecyclerView.Adapter<LinePictureItemBox> {
    private Context context;
    private ArrayList<String> arrayList;

    public LinePictureAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.arrayList = list;
    }

    @Override
    public LinePictureItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        LinePictureItemBox itemBox = new LinePictureItemBox(LayoutInflater.from(context)
                .inflate(R.layout.line_picture_recycleritem, parent, false));
        return itemBox;
    }

    @Override
    public void onBindViewHolder(LinePictureItemBox holder, int position) {
        holder.textView.setText("我是标题");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
