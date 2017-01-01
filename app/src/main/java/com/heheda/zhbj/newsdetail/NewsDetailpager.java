package com.heheda.zhbj.newsdetail;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.heheda.zhbj.R;
import com.heheda.zhbj.base.LeftMenuItemDetailBasePager;
import com.heheda.zhbj.domain.NewsCenterBean;
import com.heheda.zhbj.newsdetail.newsitempager.newsDetailItemPager;
import com.heheda.zhbj.utils.LogUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmm on 2016/12/30.
 */

public class NewsDetailpager extends LeftMenuItemDetailBasePager {

    @ViewInject(R.id.viewpager_itemnews)
    private ViewPager viewpage;


    private List<NewsCenterBean.newsData.DataChildren> children;

    private List<newsDetailItemPager> itempagers;

    public NewsDetailpager(Context context, NewsCenterBean.newsData data) {

        super(context);


        itempagers = new ArrayList<>();
        children = data.getChildren();
        LogUtil.e("长度为" + children.size() + "---------------");


    }

    @Override
    public View initView() {
        //模拟数据

        View view = View.inflate(context, R.layout.newsdetail_itempager, null);
        x.view().inject(this, view);


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        if (children != null && children.size() > 0) {
            itempagers = new ArrayList<>();
            for (int i = 0; i < children.size(); i++) {

                itempagers.add(new newsDetailItemPager(context, children.get(i)));
            }

        }

        viewpage.setAdapter(new mypageradapeter());
    }

    class mypageradapeter extends PagerAdapter {

        @Override
        public int getCount() {
            return itempagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            newsDetailItemPager currrentItempager = itempagers.get(position);
            View view = currrentItempager.initView();
            currrentItempager.initData();
            container.addView(view);
            return view;


        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
