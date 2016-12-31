package com.heheda.zhbj.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


import com.heheda.zhbj.R;
import com.heheda.zhbj.activity.MainActivity;
import com.heheda.zhbj.base.BaseFragment;
import com.heheda.zhbj.base.BasePager;
import com.heheda.zhbj.pager.GovaffairPager;
import com.heheda.zhbj.pager.HomePager;
import com.heheda.zhbj.pager.NewsCenterPager;
import com.heheda.zhbj.pager.SettingPager;
import com.heheda.zhbj.pager.SmartServicePager;
import com.heheda.zhbj.view.noScroolViewpager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by cmm on 2016/12/19.
 */

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.viewpager)
    private noScroolViewpager viewpager;

    @ViewInject(R.id.radio_group_bottom)
    private RadioGroup rdgroup;

    private ArrayList<BasePager> pagerslist;

    @Override
    public View initView() {     //baseFragment 的oncreatview()方法中调用了initview

        View view = View.inflate(ctx, R.layout.content_fargment, null);

//        viewpager= (ViewPager) view.findViewById(R.id.viewpager);
//        rdgroup= (RadioGroup) view.findViewById(R.id.radio_group_bottom);


        x.view().inject(ContentFragment.this, view);


        return view;
    }

    @Override
    public void initData() {
        super.initData();

        pagerslist = new ArrayList<>();

        pagerslist.add(new HomePager(ctx));
        pagerslist.add(new NewsCenterPager(ctx));
        pagerslist.add(new SmartServicePager(ctx));

        pagerslist.add(new GovaffairPager(ctx));
        pagerslist.add(new SettingPager(ctx));

        //默认是选中主页面、并初始化数据
        rdgroup.check(R.id.radiobtn_home);
        pagerslist.get(0).initData();
        isEnambleSlidingMenu(SlidingMenu.TOUCHMODE_NONE);

        viewpager.setAdapter(new mypagerAdapter());


        //viewpager设置 页面改变的监听
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                pagerslist.get(position).initData(); //被选中的页面才去请求数据

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rdgroup.setOnCheckedChangeListener(new myOncheckedChangedListener());

    }


    public NewsCenterPager getNewsCenterPager() {

        return (NewsCenterPager)pagerslist.get(1);
    }


    class myOncheckedChangedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {

                case R.id.radiobtn_home:
                    //viewpager.setCurrentItem(0,false);

                    viewpager.setCurrentItem(0);
                    isEnambleSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.radiobtn_news:
//                    viewpager.setCurrentItem(1,false);
                    viewpager.setCurrentItem(1);
                    isEnambleSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.radiobtn_smart:
//                    viewpager.setCurrentItem(2,false);
                    viewpager.setCurrentItem(2);
                    isEnambleSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.radiobtn_govern:
//                    viewpager.setCurrentItem(3,false);
                    viewpager.setCurrentItem(3);
                    isEnambleSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.radiobtn_settings:
//                    viewpager.setCurrentItem(4,false);
                    viewpager.setCurrentItem(4);
                    isEnambleSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }

        }
    }

    private void isEnambleSlidingMenu(int touchmode) {
        MainActivity mainActivity = (MainActivity) ContentFragment.this.ctx;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmode);
    }

    class mypagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return pagerslist.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            BasePager currentpager = pagerslist.get(position);

            //获得子页面的一个整个的布局
            View rootview = currentpager.rootview;  //调用了子pager的initView方法

            //数据解析
            //currentpager.initData();


            container.addView(rootview);

            return rootview;


        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);

            container.removeView((View) object);
        }


    }


}
