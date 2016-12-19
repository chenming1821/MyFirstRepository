package com.heheda.zhbj.activity;

import android.os.Bundle;

import com.heheda.zhbj.R;
import com.heheda.zhbj.utils.DensityUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //

        setBehindContentView(R.layout.activity_leftmenu);


        SlidingMenu slidingMenu = getSlidingMenu();

        //设置右侧菜单
        slidingMenu.setSecondaryMenu(R.layout.activity_rigthmenu);

        //设置显示模式
        slidingMenu.setMode(SlidingMenu.LEFT); //设置为左侧

        //设置滑动模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置主页占据的宽度

        slidingMenu.setBehindOffset(DensityUtils.dip2px(this,200));
    }
}
