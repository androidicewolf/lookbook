package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillBusinessrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillContractrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillGetcashrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillReceiptrecordItemBox;
import com.dalvu.www.dalvyou.adapter.ViewHolder.BillRechargerecordItemBox;
import com.dalvu.www.dalvyou.bean.BillRecordDataBean;
import com.dalvu.www.dalvyou.tools.StrToNumUtils;

import java.util.List;

/**
 * 财务子界面中所有列表页面的通用适配器
 * Created by user on 2017/6/5.
 */

public class BillItemXRecyclerItemAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BillRecordDataBean.ListBean> items;
    private int itemType;
    private String[] stateRecharge = {"未审核", "审核通过", "审核失败", "", "待支付", "已支付"};
    private String[] stateGetcash = {"未审核", "以确认，待财务处理", "审核失败", "处理完成"};
    private String[] stateReceipt = {"未审核", "审核通过", "审核失败"};
    private String[] stateContract = {"未审核", "审核通过", "审核失败"};

    public BillItemXRecyclerItemAdapter(Context context, List<BillRecordDataBean.ListBean> items, int itemType) {
        this.context = context;
        this.items = items;
        this.itemType = itemType;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                //交易记录
                return new BillBusinessrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_businessrecord_xrecycleritem, parent, false));
            case 2:
                //充值记录
                return new BillRechargerecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_rechargerecord_xrecycleritem, parent, false));
            case 4:
                //提现记录
                return new BillGetcashrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_getcashrecord_xrecycleritem, parent, false));
            case 6:
                //发票记录
                return new BillReceiptrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_receiptrecord_xrecycleritem, parent, false));
            case 8:
                //合同记录
                return new BillContractrecordItemBox(LayoutInflater.from(context)
                        .inflate(R.layout.bill_item_contractrecord_xrecycleritem, parent, false));
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (itemType) {
            case 0:
                //交易记录
                BillBusinessrecordItemBox itemBoxBusiness = (BillBusinessrecordItemBox) holder;
                itemBoxBusiness.businessrecord_xrecycleritem_state.setText(items.get(position).action);
                itemBoxBusiness.businessrecord_xrecycleritem_date.setText(items.get(position).create_time);
                itemBoxBusiness.businessrecord_xrecycleritem_businessmoney.setText("￥ " + StrToNumUtils.str2Str(items.get(position).amount) + "");
                itemBoxBusiness.businessrecord_xrecycleritem_accountremainingsum.setText("￥ " + StrToNumUtils.str2Str(items.get(position).balance) + "");
                itemBoxBusiness.businessrecord_xrecycleritem_businessnumber.setText(items.get(position).sn);

                break;
            case 2:
                //充值记录
                BillRechargerecordItemBox itemBoxRecharge = (BillRechargerecordItemBox) holder;
                itemBoxRecharge.rechargerecord_xrecycleritem_date.setText(items.get(position).create_time);
                if (!items.get(position).state.equals("2")) {
                    itemBoxRecharge.rechargerecord_xrecycleritem_passstate.setTextColor(ContextCompat.getColor(context, R.color.dalvred));
                    if (items.get(position).state.equals("3")) {
                        itemBoxRecharge.rechargerecord_xrecycleritem_failcause_ll.setVisibility(View.VISIBLE);
                        itemBoxRecharge.rechargerecord_xrecycleritem_failcause.setText(items.get(position).memo);
                    }
                }
                itemBoxRecharge.rechargerecord_xrecycleritem_passstate.setText(stateRecharge[Integer.valueOf(items.get(position).state) - 1]);
                itemBoxRecharge.rechargerecord_xrecycleritem_rechargemoney.setText("￥ " + StrToNumUtils.str2Str(items.get(position).amount) + "");
                itemBoxRecharge.rechargerecord_xrecycleritem_poundage.setText("" + Integer.valueOf(items.get(position).commission_rate) / 1000 + "%");
                itemBoxRecharge.rechargerecord_xrecycleritem_actualmoney.setText("￥ " + StrToNumUtils.str2Str(items.get(position).actualPrice) + "");
                itemBoxRecharge.rechargerecord_xrecycleritem_companyname.setText("" + items.get(position).payer + "");
                itemBoxRecharge.rechargerecord_xrecycleritem_businessnumber.setText(items.get(position).account);

                break;
            case 4:
                //提现记录
                BillGetcashrecordItemBox itemBoxGetcash = (BillGetcashrecordItemBox) holder;
                itemBoxGetcash.getcashrecord_xrecycleritem_date.setText(items.get(position).create_time);
                if (!items.get(position).state.equals("4")) {
                    itemBoxGetcash.getcashrecord_xrecycleritem_passstate.setTextColor(ContextCompat.getColor(context, R.color.dalvred));
                    if (items.get(position).state.equals("3")) {
                        itemBoxGetcash.getcashrecord_xrecycleritem_failcause_ll.setVisibility(View.VISIBLE);
                        itemBoxGetcash.getcashrecord_xrecycleritem_failcause.setText(items.get(position).memo);
                    }
                }
                itemBoxGetcash.getcashrecord_xrecycleritem_passstate.setText(stateGetcash[Integer.valueOf(items.get(position).state) - 1]);
                itemBoxGetcash.getcashrecord_xrecycleritem_getcashmoney.setText("" + StrToNumUtils.str2Str(items.get(position).amount) + "");
                itemBoxGetcash.getcashrecord_xrecycleritem_businessnumber.setText(items.get(position).sn);
                break;
            case 6:
                //发票记录
                BillReceiptrecordItemBox itemBoxReceipt = (BillReceiptrecordItemBox) holder;
                itemBoxReceipt.receiptrecord_xrecycleritem_date.setText(items.get(position).create_time);
                if (!items.get(position).state.equals("2")) {
                    itemBoxReceipt.receiptrecord_xrecycleritem_passstate.setTextColor(ContextCompat.getColor(context, R.color.dalvred));
                    if (items.get(position).state.equals("3")) {
                        itemBoxReceipt.receiptrecord_xrecycleritem_failcause_ll.setVisibility(View.VISIBLE);
                        itemBoxReceipt.receiptrecord_xrecycleritem_failcause.setText(items.get(position).memo);
                    }
                }
                itemBoxReceipt.receiptrecord_xrecycleritem_passstate.setText(stateReceipt[Integer.valueOf(items.get(position).state) - 1]);
                itemBoxReceipt.receiptrecord_xrecycleritem_companyname.setText(items.get(position).title);
                itemBoxReceipt.receiptrecord_xrecycleritem_receipttype.setText(items.get(position).detail);
                if (!items.get(position).detail_comm.isEmpty()) {
                    itemBoxReceipt.receiptrecord_xrecycleritem_remarks.setText(items.get(position).detail_comm);
                }
                itemBoxReceipt.receiptrecord_xrecycleritem_receiptmoney.setText("￥ " + StrToNumUtils.str2Double(items.get(position).amount) + "");
                itemBoxReceipt.receiptrecord_xrecycleritem_businessnumber.setText(items.get(position).sn);

                break;
            case 8:
                //合同记录
                BillContractrecordItemBox itemBoxContract = (BillContractrecordItemBox) holder;
                itemBoxContract.contractrecord_xrecycleritem_date.setText(items.get(position).create_time);
                if (!items.get(position).state.equals("2")) {
                    itemBoxContract.contractrecord_xrecycleritem_passstate.setTextColor(ContextCompat.getColor(context, R.color.dalvred));
                    if (items.get(position).state.equals("3")) {
                        itemBoxContract.contractrecord_xrecycleritem_failcause_ll.setVisibility(View.VISIBLE);
                        itemBoxContract.contractrecord_xrecycleritem_failcause.setText(items.get(position).memo);
                    }
                }
                itemBoxContract.contractrecord_xrecycleritem_passstate.setText(stateContract[Integer.valueOf(items.get(position).state) - 1]);
                itemBoxContract.contractrecord_xrecycleritem_internalnumber.setText("" + Integer.valueOf(items.get(position).inland_count) + " 份");
                itemBoxContract.contractrecord_xrecycleritem_overseasnumber.setText("" + Integer.valueOf(items.get(position).outbound_count) + " 份");
                itemBoxContract.contractrecord_xrecycleritem_entrustnumber.setText("" + Integer.valueOf(items.get(position).peritem_count) + " 份");
                itemBoxContract.contractrecord_xrecycleritem_businessnumber.setText(items.get(position).sn);
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
