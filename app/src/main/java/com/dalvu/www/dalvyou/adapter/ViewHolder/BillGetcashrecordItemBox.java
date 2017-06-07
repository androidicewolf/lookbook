package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 提现记录页面xrecyclerview的ViewHolder
 * Created by user on 2017/6/7.
 */

public class BillGetcashrecordItemBox extends RecyclerView.ViewHolder {
    public TextView getcashrecord_xrecycleritem_date;
    public TextView getcashrecord_xrecycleritem_passstate;
    public TextView getcashrecord_xrecycleritem_passingstate;
    public TextView getcashrecord_xrecycleritem_getcashmoney;
    public TextView getcashrecord_xrecycleritem_businessnumber;

    public BillGetcashrecordItemBox(View itemView) {
        super(itemView);
        getcashrecord_xrecycleritem_date = (TextView) itemView.findViewById(R.id.getcashrecord_xrecycleritem_date);
        getcashrecord_xrecycleritem_passstate = (TextView) itemView.findViewById(R.id.getcashrecord_xrecycleritem_passstate);
        getcashrecord_xrecycleritem_passingstate = (TextView) itemView.findViewById(R.id.getcashrecord_xrecycleritem_passingstate);
        getcashrecord_xrecycleritem_getcashmoney = (TextView) itemView.findViewById(R.id.getcashrecord_xrecycleritem_getcashmoney);
        getcashrecord_xrecycleritem_businessnumber = (TextView) itemView.findViewById(R.id.getcashrecord_xrecycleritem_businessnumber);
    }
}
