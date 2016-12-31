package com.heheda.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by cmm on 2016/12/28.
 * 屏蔽掉viewpager的左右的滑动
 *
 */

public class noScroolViewpager extends ViewPager {

    //private static final int DEFAULT_OFFSCREEN_PAGES = 0;

    public noScroolViewpager(Context context) {
        super(context);
    }

    public noScroolViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return true;//消费掉触摸事件， 不响应
    }
}


