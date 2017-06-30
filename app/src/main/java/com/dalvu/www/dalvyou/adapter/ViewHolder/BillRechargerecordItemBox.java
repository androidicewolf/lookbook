package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 充值记录页面xrecyclerview的ViewHolder
 * Created by user on 2017/6/5.
 */

public class BillRechargerecordItemBox extends RecyclerView.ViewHolder {
    public TextView rechargerecord_xrecycleritem_date;
    public TextView rechargerecord_xrecycleritem_passstate;
    public LinearLayout rechargerecord_xrecycleritem_failcause_ll;
    public TextView rechargerecord_xrecycleritem_failcause;
    public TextView rechargerecord_xrecycleritem_rechargemoney;
    public TextView rechargerecord_xrecycleritem_poundage;
    public TextView rechargerecord_xrecycleritem_actualmoney;
    public TextView rechargerecord_xrecycleritem_companyname;
    public TextView rechargerecord_xrecycleritem_businessnumber;

    public BillRechargerecordItemBox(View itemView) {
        super(itemView);
        rechargerecord_xrecycleritem_date = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_date);
        rechargerecord_xrecycleritem_passstate = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_passstate);
        rechargerecord_xrecycleritem_failcause_ll = (LinearLayout) itemView.findViewById(R.id.rechargerecord_xrecycleritem_failcause_ll);
        rechargerecord_xrecycleritem_failcause = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_failcause);
        rechargerecord_xrecycleritem_rechargemoney = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_rechargemoney);
        rechargerecord_xrecycleritem_poundage = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_poundage);
        rechargerecord_xrecycleritem_actualmoney = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_actualmoney);
        rechargerecord_xrecycleritem_companyname = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_companyname);
        rechargerecord_xrecycleritem_businessnumber = (TextView) itemView.findViewById(R.id.rechargerecord_xrecycleritem_businessnumber);
    }
}
