package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.SupplierQueryItemBox;

import java.util.ArrayList;

/**
 * 供应商查询里的xrecyclerview的适配器
 * Created by user on 2017/5/31.
 */

public class SupplierQueryAdapter extends RecyclerView.Adapter<SupplierQueryItemBox> {
    private Context context;
    private ArrayList items;

    public SupplierQueryAdapter(Context context, ArrayList items) {
        this.context = context;
    }

    @Override
    public SupplierQueryItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        SupplierQueryItemBox itemBox = new SupplierQueryItemBox(LayoutInflater.from(context)
                .inflate(R.layout.supplier_xrecyclerview, parent, false));
        return itemBox;
    }

    @Override
    public void onBindViewHolder(SupplierQueryItemBox holder, int position) {
        holder.supplier_query_item_name.setText("昵称");
        holder.supplier_query_item_companyname.setText("公司");
        holder.supplier_query_item_businesslinkman.setText("顾问名字");
        holder.supplier_query_item_termini.setText("目标国家");
        holder.supplier_query_item_linkmantel.setText("电话号码");
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
