package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillBusinessrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillContractrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillGetcashrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillReceiptrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillRechargerecordItemBox;

import java.util.ArrayList;

/**
 * 财务子界面中所有列表页面的通用适配器
 * Created by user on 2017/6/5.
 */

public class BillItemXRecyclerItemAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList items;
    private int itemType;

    public BillItemXRecyclerItemAdapter(Context context, ArrayList items, int itemType) {
        this.context = context;
        this.items = items;
        this.itemType = itemType;
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                //交易记录
                BillBusinessrecordItemBox businessItemBox = new BillBusinessrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_businessrecord_xrecycleritem, parent, false));
                return businessItemBox;
            case 2:
                //充值记录
                BillRechargerecordItemBox rechargeItemBox = new BillRechargerecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_rechargerecord_xrecycleritem, parent, false));
                return rechargeItemBox;
            case 4:
                //提现记录
                BillGetcashrecordItemBox getcashItemBox = new BillGetcashrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_getcashrecord_xrecycleritem, parent, false));
                return getcashItemBox;
            case 6:
                //发票记录
                BillReceiptrecordItemBox receiptItemBox = new BillReceiptrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_receiptrecord_xrecycleritem, parent, false));
                return receiptItemBox;
            case 8:
                //合同记录
                BillContractrecordItemBox contractItemBox = new BillContractrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_contractrecord_xrecycleritem, parent, false));
                return contractItemBox;
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (itemType) {
            case 0:
                BillBusinessrecordItemBox itemBox = (BillBusinessrecordItemBox) holder;
                itemBox.businessrecord_xrecycleritem_state.setText("交易类型");
                itemBox.businessrecord_xrecycleritem_date.setText("2017-09-22 12:50:59");
                itemBox.businessrecord_xrecycleritem_businessmoney.setText("-1");
                itemBox.businessrecord_xrecycleritem_accountremainingsum.setText("10");
                itemBox.businessrecord_xrecycleritem_businessnumber.setText("18845806607676496");
                break;
            case 2:

                break;
            case 4:
                break;
            case 6:
                break;
            case 8:
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemType;
    }
}
