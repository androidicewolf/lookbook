package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**首页条目的Box
 * Created by user on 2017/5/16.
 */
public class HomeFragmentItemBox extends RecyclerView.ViewHolder{
    public ImageView home_item_image;
    public TextView home_item_name;
    public TextView home_item_gosite;
    public TextView home_item_price;
    public HomeFragmentItemBox(View itemView) {
        super(itemView);
        home_item_image = (ImageView) itemView.findViewById(R.id.home_item_image);
        home_item_name = (TextView) itemView.findViewById(R.id.home_item_name);
        home_item_gosite = (TextView) itemView.findViewById(R.id.home_item_gosite);
        home_item_price = (TextView) itemView.findViewById(R.id.home_item_price);

    }
}
