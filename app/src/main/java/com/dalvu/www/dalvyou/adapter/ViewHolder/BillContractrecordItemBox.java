package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 合同记录页面xrecyclerview的ViewHolder
 * Created by user on 2017/6/7.
 */

public class BillContractrecordItemBox extends RecyclerView.ViewHolder {
    public TextView contractrecord_xrecycleritem_date;
    public TextView contractrecord_xrecycleritem_passstate;
    public TextView contractrecord_xrecycleritem_failcause;
    public TextView contractrecord_xrecycleritem_internalnumber;
    public TextView contractrecord_xrecycleritem_overseasnumber;
    public TextView contractrecord_xrecycleritem_entrustnumber;
    public TextView contractrecord_xrecycleritem_businessnumber;
    public LinearLayout contractrecord_xrecycleritem_failcause_ll;

    public BillContractrecordItemBox(View itemView) {
        super(itemView);
        contractrecord_xrecycleritem_date = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_date);
        contractrecord_xrecycleritem_passstate = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_passstate);
        contractrecord_xrecycleritem_failcause = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_failcause);
        contractrecord_xrecycleritem_internalnumber = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_internalnumber);
        contractrecord_xrecycleritem_overseasnumber = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_overseasnumber);
        contractrecord_xrecycleritem_entrustnumber = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_entrustnumber);
        contractrecord_xrecycleritem_businessnumber = (TextView) itemView.findViewById(R.id.contractrecord_xrecycleritem_businessnumber);
        contractrecord_xrecycleritem_failcause_ll = (LinearLayout) itemView.findViewById(R.id.contractrecord_xrecycleritem_failcause_ll);
    }
}
