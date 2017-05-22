package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**首页头布局中的recyclerview的ViewHolder
 * Created by user on 2017/5/16.
 */

public class HeaderViewBox extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    public HeaderViewBox(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.home_header_image);
        textView = (TextView) itemView.findViewById(R.id.home_header_text);
    }
}
