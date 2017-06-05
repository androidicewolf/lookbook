package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 财产页面中recyclerview的ViewHolder
 * Created by user on 2017/6/5.
 */

public class BillFragmentRecyclerItemBox extends RecyclerView.ViewHolder {
    public ImageView item_iv;
    public TextView item_tv;

    public BillFragmentRecyclerItemBox(View itemView) {
        super(itemView);
        item_iv = (ImageView) itemView.findViewById(R.id.bill_fragment_recycleritem_iv);
        item_tv = (TextView) itemView.findViewById(R.id.bill_fragment_recycleritem_tv);
    }
}
