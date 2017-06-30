package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.LinePictureItemBox;
import com.dalvu.www.dalvyou.bean.LinePictureDataBean;

import java.util.List;

/**
 * 线路图片库里recyclerview的适配器
 * Created by user on 2017/5/23.
 */

public class LinePictureAdapter extends RecyclerView.Adapter<LinePictureItemBox> {
    private Context context;
    private List<LinePictureDataBean.PicArrBean> items;

    public LinePictureAdapter(Context context, List<LinePictureDataBean.PicArrBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public LinePictureItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        LinePictureItemBox itemBox = new LinePictureItemBox(LayoutInflater.from(context)
                .inflate(R.layout.line_picture_recycleritem, parent, false));
        return itemBox;
    }

    @Override
    public void onBindViewHolder(LinePictureItemBox holder, int position) {
        Glide.with(context).load(items.get(position).img).into(holder.imageView);
        holder.textView.setText(items.get(position).name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
