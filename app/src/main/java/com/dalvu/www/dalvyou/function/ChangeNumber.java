package com.dalvu.www.dalvyou.function;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.tools.DensityUtils;

/**
 * 点击按钮，改变控件中文字和背景色的功能实现类
 * Created by wolf on 17.5.29.
 */

public class ChangeNumber {
    /**
     * 点击按钮，数值加1
     *
     * @param clickView  点击的控件
     * @param changeView 改变的文本控件
     * @param number      匹配的数字
     */
    public static void addNumber(final Context context, View clickView, final TextView changeView, final int number) {
        changeView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("call", "------文字改变前");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("call", "------文字改变后");
                if (Integer.valueOf(s.toString()) == number) {

                    changeView.setTextColor(Color.BLACK);
                    changeView.setBackgroundResource(R.drawable.all_nocorner_edging_style);
                    changeView.setPadding(0, DensityUtils.dip2px(context, 5), 0, DensityUtils.dip2px(context, 5));
                } else {
                    changeView.setTextColor(Color.WHITE);
                    changeView.setBackgroundResource(R.color.dalvbule);
//                        changeView.setPadding(0,DensityUtils.dip2px(context, 5),0,DensityUtils.dip2px(context, 5));
                }
            }
        });
        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("call", "加号“+”点击事件执行了");
                String numberStr = changeView.getText().toString();
                if (!numberStr.isEmpty()) {
                    int number = Integer.valueOf(numberStr);
                    number++;
                    changeView.setText(String.valueOf(number));
                    Log.e("call", "changeView控件的地址" + changeView.toString());
                }
            }
        });
        changeView.postInvalidate();
    }

    /**
     * 点击按钮，数值减1
     *
     * @param clickView  点击的控件
     * @param changeView 改变的文本控件
     * @param number      匹配的数字
     */
    public static void subNumber(final Context context, View clickView, final TextView changeView, final int number) {
        changeView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("call", "------文字改变前");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("call", "------文字改变后");
                if (Integer.valueOf(s.toString()) == number) {
                    changeView.setTextColor(Color.BLACK);
                    changeView.setBackgroundResource(R.drawable.all_nocorner_edging_style);
                    changeView.setPadding(0, DensityUtils.dip2px(context, 5), 0, DensityUtils.dip2px(context, 5));
                } else {
                    changeView.setTextColor(Color.WHITE);
                    changeView.setBackgroundResource(R.color.dalvbule);
//                        changeView.setPadding(0, DensityUtils.dip2px(context, 5),0,DensityUtils.dip2px(context, 5));
                }
            }
        });
        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("call", "减号“-”点击事件执行了");
                String numberStr = changeView.getText().toString();
                if (!numberStr.isEmpty()) {
                    int number = Integer.valueOf(numberStr);
                    if (number != 0) {
                        number--;
                        changeView.setText(String.valueOf(number));
                        Log.e("call", "changeView控件的地址" + changeView.toString());
                    }
                }
            }
        });
    }
}
