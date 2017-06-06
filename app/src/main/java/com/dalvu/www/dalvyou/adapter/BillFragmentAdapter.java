package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.activity.bill.BillItemActivity;
import com.dalvu.www.dalvyou.activity.bill.ContractAskforActivity;
import com.dalvu.www.dalvyou.activity.bill.GetCashAskforActivity;
import com.dalvu.www.dalvyou.activity.bill.ReceiptAskforActivity;
import com.dalvu.www.dalvyou.activity.bill.RechargeAskforActivity;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillFragmentRecyclerItemBox;

/**
 * 财产页面的recyclerview的适配器
 * Created by user on 2017/6/5.
 */

public class BillFragmentAdapter extends RecyclerView.Adapter<BillFragmentRecyclerItemBox> {
    private Context context;
    private int[] icos = {R.mipmap.transaction_record, R.mipmap.recharge_application, R.mipmap.recharge_record,
            R.mipmap.present_application, R.mipmap.cash_register, R.mipmap.invoice_application,
            R.mipmap.invoice_record, R.mipmap.contract_application, R.mipmap.contract_record};
    private String[] name = {"交易记录", "充值申请", "充值申请记录", "提现申请", "提现记录", "发票申请",
            "发票记录", "合同申请", "合同记录"};

    public BillFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BillFragmentRecyclerItemBox onCreateViewHolder(ViewGroup parent, int viewType) {
        BillFragmentRecyclerItemBox itemBox = null;
        if (viewType == 0) {
            itemBox = new BillFragmentRecyclerItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.bill_fragment_recycleritem, parent, false));
        } else if (viewType == 1) {
            itemBox = new BillFragmentRecyclerItemBox(LayoutInflater.from(context)
                    .inflate(R.layout.bill_fragment_recyclernullitem, parent, false));
        }
        return itemBox;
    }

    @Override
    public void onBindViewHolder(BillFragmentRecyclerItemBox holder, final int position) {
        if (holder.getItemViewType() == 0) {
            if (icos[position] != 0) {
                holder.item_iv.setImageResource(icos[position]);
            }
            if (!name[position].isEmpty()) {
                holder.item_tv.setText(name[position]);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                Intent intent;

                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 1:
                            intent = new Intent(context, RechargeAskforActivity.class);
                            break;
                        case 3:
                            intent = new Intent(context, GetCashAskforActivity.class);
                            break;
                        case 5:
                            intent = new Intent(context, ReceiptAskforActivity.class);
                            break;
                        case 7:
                            intent = new Intent(context, ContractAskforActivity.class);
                            break;
                        default:
                            intent = new Intent(context, BillItemActivity.class);
                            intent.putExtra("title", name[position]);
                            intent.putExtra("layoutId", 0);
                            intent.putExtra("position", position);
                            break;
                    }
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= 8) {
            return 0;
        }
        return 1;
    }
}
