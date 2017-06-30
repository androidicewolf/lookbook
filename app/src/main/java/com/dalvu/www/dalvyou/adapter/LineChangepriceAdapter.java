package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.MyApplication;
import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.bean.LineChangePriceDataBean;
import com.dalvu.www.dalvyou.bean.ServerFeedback;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.tools.AppUserDate;
import com.dalvu.www.dalvyou.tools.CustomValue;

import java.util.HashMap;
import java.util.List;

/**
 * 线路改价页面ListView的适配器
 * Created by user on 2017/5/31.
 */

public class LineChangepriceAdapter extends BaseAdapter {
    private final int MAN = 0;
    private final int CHILDREN = 1;
    private final int DIFFERENCE = 2;
    private Context context;
    private List<LineChangePriceDataBean.ListBean> items;
    private SparseArray<String> editTextArray;

    //改动价格时的服务地址
    private String url = "Api/agency/changePriceHandle";

    public LineChangepriceAdapter(Context context, List<LineChangePriceDataBean.ListBean> items) {
        this.context = context;
        this.items = items;
        editTextArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewBox viewBox;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.line_changeprice_item, null);
            viewBox = new ViewBox();
            viewBox.groupdate = (TextView) convertView.findViewById(R.id.line_changeprice_item_date);
            viewBox.line_changeprice_item_manprice = (TextView) convertView.findViewById(R.id.line_changeprice_item_manprice);
            viewBox.line_changeprice_item_childrenprice = (TextView) convertView.findViewById(R.id.line_changeprice_item_childrenprice);
            viewBox.line_changeprice_item_difference = (TextView) convertView.findViewById(R.id.line_changeprice_item_difference);
            viewBox.line_changeprice_item_inputmanprice = (EditText) convertView.findViewById(R.id.line_changeprice_item_inputmanprice);
            viewBox.line_changeprice_item_inputchildrenprice = (EditText) convertView.findViewById(R.id.line_changeprice_item_inputchildrenprice);
            viewBox.line_changeprice_item_inputdifferenceprice = (EditText) convertView.findViewById(R.id.line_changeprice_item_inputdifferenceprice);
            viewBox.line_changeprice_item_saveprice = (TextView) convertView.findViewById(R.id.line_changeprice_item_saveprice);
            convertView.setTag(viewBox);
        } else {
            viewBox = (ViewBox) convertView.getTag();
        }
        //在这里给items设置数据
        viewBox.groupdate.setText(items.get(position).start_time);
        viewBox.line_changeprice_item_manprice.setText("￥" + Integer.valueOf(items.get(position).price_adult_agency) / 100 + " / ￥" + Integer.valueOf(items.get(position).price_adult_list) / 100);
        viewBox.line_changeprice_item_childrenprice.setText("￥" + Integer.valueOf(items.get(position).price_child_agency) / 100 + " / ￥" + Integer.valueOf(items.get(position).price_child_list) / 100);
        viewBox.line_changeprice_item_difference.setText("￥" + Integer.valueOf(items.get(position).price_hotel_agency) / 100 + " / ￥" + Integer.valueOf(items.get(position).price_hotel_list) / 100);
        if (items.get(position).user_defined_adult_list == null) {
            editTextArray.put(position * 3 + MAN, "" + Integer.valueOf(items.get(position).price_adult_agency) / 100);
            editTextArray.put(position * 3 + CHILDREN, "" + Integer.valueOf(items.get(position).price_child_agency) / 100);
            editTextArray.put(position * 3 + DIFFERENCE, "" + Integer.valueOf(items.get(position).price_hotel_agency) / 100);
        } else {
            editTextArray.put(position * 3 + MAN, "" + Integer.valueOf(items.get(position).user_defined_adult_list) / 100);
            editTextArray.put(position * 3 + CHILDREN, "" + Integer.valueOf(items.get(position).user_defined_child_list) / 100);
            editTextArray.put(position * 3 + DIFFERENCE, "" + Integer.valueOf(items.get(position).user_defined_hotel_list) / 100);
        }

        saveText(viewBox.line_changeprice_item_inputmanprice, position, MAN);
        saveText(viewBox.line_changeprice_item_inputchildrenprice, position, CHILDREN);
        saveText(viewBox.line_changeprice_item_inputdifferenceprice, position, DIFFERENCE);
        viewBox.line_changeprice_item_saveprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String man = editTextArray.get(position * 3 + MAN);
                String children = editTextArray.get(position * 3 + CHILDREN);
                String difference = editTextArray.get(position * 3 + DIFFERENCE);

                HashMap<String, String> map = new HashMap<>();
                map.put("uid", "" + AppUserDate.getUserId());
                map.put("sign_token", AppUserDate.getUserToken());
                map.put("sku_id", items.get(position).id);
                map.put("tour_id", items.get(position).tour_id);
                map.put("adult", man);
                map.put("child", children);
                map.put("hotel", difference);
                Log.e("call", "成人价：==" + man + "***儿童价：==" + children + "***单房差：==" + difference);
                NetUtils.callNet(1, CustomValue.SERVER + url, map, new MyCallBack() {
                    @Override
                    public void onStart(int what) {

                    }

                    @Override
                    public void onSucceed(int what, String json) {
                        ServerFeedback serverFeedback = MyApplication.getGson().fromJson(json, ServerFeedback.class);
                        if (serverFeedback.status.equals("00000")) {
                            Toast.makeText(context, "修改价格保存成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "服务器繁忙，价格修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFinish(int what) {
                    }

                    @Override
                    public void onFailed(int what, int code) {
                        Toast.makeText(context, "连接服务器失败，请检查网络后重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return convertView;
    }

    private void saveText(EditText editText, final int position, final int sort) {
        if (editText.getTag() instanceof TextWatcher) {
            editText.removeTextChangedListener((TextWatcher) editText.getTag());
        }
        editText.setText(editTextArray.get(position * 3 + sort));
        Log.e("call", "文字监听被调用，输入框的内容改变了:=" + editText.getText().toString() + "=");
        TextWatcher textWatch = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    editTextArray.put(position * 3 + sort, "");
                } else {
                    editTextArray.put(position * 3 + sort, s.toString());
                }
            }
        };
        editText.addTextChangedListener(textWatch);
        editText.setTag(textWatch);
    }

    private class ViewBox {
        TextView groupdate;
        TextView line_changeprice_item_manprice;
        TextView line_changeprice_item_childrenprice;
        TextView line_changeprice_item_difference;
        EditText line_changeprice_item_inputmanprice;
        EditText line_changeprice_item_inputchildrenprice;
        EditText line_changeprice_item_inputdifferenceprice;
        TextView line_changeprice_item_saveprice;
    }
}
