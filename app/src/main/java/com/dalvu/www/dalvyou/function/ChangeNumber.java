package com.dalvu.www.dalvyou.function;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

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
     */
    public static void addNumber(View clickView, final TextView changeView, final int number, final int color) {
        changeView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (Integer.valueOf(s.toString()) == number) {
                    changeView.setBackgroundColor(color);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Integer.valueOf(s.toString()) == number) {
                    changeView.setBackgroundColor(color);
                }
            }
        });
        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberStr = changeView.getText().toString();
                if (numberStr.isEmpty()) {
                    Integer number = Integer.valueOf(numberStr);
                    number++;
                    changeView.setText(number);
                }
            }
        });
    }

    /**
     * 点击按钮，数值减1
     *
     * @param clickView  点击的控件
     * @param changeView 改变的文本控件
     */
    public static void subNumber(View clickView, final TextView changeView, final int number, final int color) {
        changeView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (Integer.valueOf(s.toString()) == number) {
                    changeView.setBackgroundColor(color);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Integer.valueOf(s.toString()) == number) {
                    changeView.setBackgroundColor(color);
                }
            }
        });
        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberStr = changeView.getText().toString();
                if (numberStr.isEmpty()) {
                    Integer number = Integer.valueOf(numberStr);
                    number--;
                    changeView.setText(number);
                }
            }
        });
    }
}
