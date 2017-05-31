package com.dalvu.www.dalvyou.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;

/**
 * 供应商查询页面里xrecyclerview的ViewBox
 * Created by user on 2017/5/31.
 */

public class SupplierQueryItemBox extends RecyclerView.ViewHolder {
    public TextView supplier_query_item_name;
    public TextView supplier_query_item_companyname;
    public TextView supplier_query_item_businesslinkman;
    public TextView supplier_query_item_termini;
    public TextView supplier_query_item_linkmantel;

    public SupplierQueryItemBox(View itemView) {
        super(itemView);
        supplier_query_item_name = (TextView) itemView.findViewById(R.id.supplier_query_item_name);
        supplier_query_item_companyname = (TextView) itemView.findViewById(R.id.supplier_query_item_companyname);
        supplier_query_item_businesslinkman = (TextView) itemView.findViewById(R.id.supplier_query_item_businesslinkman);
        supplier_query_item_termini = (TextView) itemView.findViewById(R.id.supplier_query_item_termini);
        supplier_query_item_linkmantel = (TextView) itemView.findViewById(R.id.supplier_query_item_linkmantel);
    }

}
