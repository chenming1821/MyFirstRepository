package com.heheda.zhbj.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.heheda.zhbj.R;
import com.heheda.zhbj.fragment.ContentFragment;
import com.heheda.zhbj.fragment.LeftMenuFragment;
import com.heheda.zhbj.utils.DensityUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";

    public static final String LEFTMENU_TAG = "leftmenu_tag";

    static android.support.v4.app.FragmentManager fm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //

        setBehindContentView(R.layout.activity_leftmenu);


        SlidingMenu slidingMenu = getSlidingMenu();

        //设置右侧菜单
        //slidingMenu.setSecondaryMenu(R.layout.activity_rigthmenu);

        //设置显示模式
        slidingMenu.setMode(SlidingMenu.LEFT); //设置为左侧

        //设置滑动模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置主页占据的宽度

        slidingMenu.setBehindOffset(DensityUtils.dip2px(this, 200));

        fm = getSupportFragmentManager();

        initFragment();

    }



    private void initFragment() {


        FragmentTransaction ft = fm.beginTransaction(); //开启事务

        ft.replace(R.id.activity_main, new ContentFragment(), MAIN_CONTENT_TAG);
        ft.replace(R.id.leftmenu, new LeftMenuFragment(), LEFTMENU_TAG);


        ft.commit();

    }

    public static LeftMenuFragment getleftMenuFragment() {

        return (LeftMenuFragment)fm.findFragmentByTag(LEFTMENU_TAG);
    }

    public static ContentFragment getContentFragment() {

        return (ContentFragment)fm.findFragmentByTag(MAIN_CONTENT_TAG);
    }


}
