package com.heheda.zhbj.newsdetail;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.heheda.zhbj.base.LeftMenuItemDetailBasePager;

/**
 * Created by cmm on 2016/12/30.
 */

public class NewsDetailpager extends LeftMenuItemDetailBasePager {

    private TextView textView;

    public NewsDetailpager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        //模拟数据
        textView = new TextView(context);

        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("新闻详情界面");

    }
}
