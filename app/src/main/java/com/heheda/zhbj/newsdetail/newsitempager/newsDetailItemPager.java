package com.heheda.zhbj.newsdetail.newsitempager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.heheda.zhbj.base.LeftMenuItemDetailBasePager;
import com.heheda.zhbj.domain.NewsCenterBean;

/**
 * Created by cmm on 2017/1/1.
 */

public class newsDetailItemPager extends LeftMenuItemDetailBasePager {
    private TextView textView;

    NewsCenterBean.newsData.DataChildren child;

    public newsDetailItemPager(Context context,NewsCenterBean.newsData.DataChildren child) {
        super(context);
        this.child=child;
    }

    @Override
    public View initView() {
        //模拟数据
        textView = new TextView(context);

        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(18);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();


        textView.setText(child.getTitle());

    }
}
