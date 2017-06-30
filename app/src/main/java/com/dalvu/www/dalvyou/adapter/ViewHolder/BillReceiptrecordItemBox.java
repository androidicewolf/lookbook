package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 发票记录页面xrecyclerview的ViewHolder
 * Created by user on 2017/6/7.
 */

public class BillReceiptrecordItemBox extends RecyclerView.ViewHolder {
    public TextView receiptrecord_xrecycleritem_date;
    public TextView receiptrecord_xrecycleritem_passstate;
    public LinearLayout receiptrecord_xrecycleritem_failcause_ll;
    public TextView receiptrecord_xrecycleritem_failcause;
    public TextView receiptrecord_xrecycleritem_companyname;
    public TextView receiptrecord_xrecycleritem_receipttype;
    public TextView receiptrecord_xrecycleritem_remarks;
    public TextView receiptrecord_xrecycleritem_receiptmoney;
    public TextView receiptrecord_xrecycleritem_businessnumber;

    public BillReceiptrecordItemBox(View itemView) {
        super(itemView);
        receiptrecord_xrecycleritem_date = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_date);
        receiptrecord_xrecycleritem_passstate = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_passstate);
        receiptrecord_xrecycleritem_failcause_ll = (LinearLayout) itemView.findViewById(R.id.receiptrecord_xrecycleritem_failcause_ll);
        receiptrecord_xrecycleritem_failcause = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_failcause);
        receiptrecord_xrecycleritem_companyname = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_companyname);
        receiptrecord_xrecycleritem_receipttype = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_receipttype);
        receiptrecord_xrecycleritem_remarks = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_remarks);
        receiptrecord_xrecycleritem_receiptmoney = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_receiptmoney);
        receiptrecord_xrecycleritem_businessnumber = (TextView) itemView.findViewById(R.id.receiptrecord_xrecycleritem_businessnumber);
    }
}
