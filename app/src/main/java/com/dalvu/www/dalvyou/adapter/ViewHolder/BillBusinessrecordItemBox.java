package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 交易记录页面xrecyclerview的ViewHolder
 * Created by user on 2017/6/5.
 */

public class BillBusinessrecordItemBox extends RecyclerView.ViewHolder {
    public TextView businessrecord_xrecycleritem_state;
    public TextView businessrecord_xrecycleritem_date;
    public TextView businessrecord_xrecycleritem_businessmoney;
    public TextView businessrecord_xrecycleritem_accountremainingsum;
    public TextView businessrecord_xrecycleritem_businessnumber;

    public BillBusinessrecordItemBox(View itemView) {
        super(itemView);
        businessrecord_xrecycleritem_state = (TextView) itemView.findViewById(R.id.businessrecord_xrecycleritem_state);
        businessrecord_xrecycleritem_date = (TextView) itemView.findViewById(R.id.businessrecord_xrecycleritem_date);
        businessrecord_xrecycleritem_businessmoney = (TextView) itemView.findViewById(R.id.businessrecord_xrecycleritem_businessmoney);
        businessrecord_xrecycleritem_accountremainingsum = (TextView) itemView.findViewById(R.id.businessrecord_xrecycleritem_accountremainingsum);
        businessrecord_xrecycleritem_businessnumber = (TextView) itemView.findViewById(R.id.businessrecord_xrecycleritem_businessnumber);
    }
}
