package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;

import java.util.ArrayList;

/**
 * 线路改价页面ListView的适配器
 * Created by user on 2017/5/31.
 */

public class LineChangepriceAdapter extends BaseAdapter {
    private final int MAN = 0;
    private final int CHILDREN = 1;
    private final int DIFFERENCE = 2;
    private Context context;
    private ArrayList items;
    private SparseArray<String> editTextArray;

    public LineChangepriceAdapter(Context context, ArrayList items) {
        this.context = context;
        this.items = items;
        editTextArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
//        return items.size();
        return 5;
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
        ViewBox viewBox;
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
        saveText(viewBox.line_changeprice_item_inputmanprice, position, MAN);
        saveText(viewBox.line_changeprice_item_inputchildrenprice, position, CHILDREN);
        saveText(viewBox.line_changeprice_item_inputdifferenceprice, position, DIFFERENCE);
        viewBox.line_changeprice_item_saveprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = "";
                String man = editTextArray.get(position * 3 + MAN);
                String children = editTextArray.get(position * 3 + CHILDREN);
                String difference = editTextArray.get(position * 3 + DIFFERENCE);
                NetUtils.callNet(1, "", new MyCallBack() {
                    @Override
                    public void onStart(int what) {
                    }

                    @Override
                    public void onSucceed(int what, String json) {
                        Toast.makeText(context, "修改价格保存成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish(int what) {
                        Toast.makeText(context, "网络异常，修改价格失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(int what, int code) {
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
