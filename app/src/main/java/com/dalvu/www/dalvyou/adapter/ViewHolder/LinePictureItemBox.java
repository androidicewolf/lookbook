package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 线路图片库里recyclerview的适配器的Box
 * Created by user on 2017/5/23.
 */

public class LinePictureItemBox extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    public LinePictureItemBox(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.line_picture_item_image);
        textView = (TextView) itemView.findViewById(R.id.line_picture_item_text);
    }
}
