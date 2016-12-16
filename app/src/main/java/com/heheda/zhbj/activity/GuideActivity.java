package com.heheda.zhbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.heheda.zhbj.R;
import com.heheda.zhbj.utils.CacheUtils;
import com.heheda.zhbj.utils.DensityUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager vp;

    private LinearLayout ll;
    private ImageView iv_point;

    private Button btn;

    private ArrayList<ImageView> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ll = (LinearLayout) findViewById(R.id.ll_radiogroup_parent);

        btn = (Button) findViewById(R.id.btn_start_main);
        iv_point = (ImageView) findViewById(R.id.iv_point);
        int[] arrs = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

        for (int i = 0; i < arrs.length; i++) {
            ImageView iv = new ImageView(this);


            iv.setBackgroundResource(arrs[i]);

            al.add(iv);
            ImageView piont_nomal = new ImageView(this);
            piont_nomal.setBackgroundResource(R.drawable.point_nomal);

            int width = DensityUtils.dip2px(this, 10f);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            }

            piont_nomal.setLayoutParams(params);

            ll.addView(piont_nomal);
        }

        //点击进入main activity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CacheUtils.SaveSharedPreference(GuideActivity.this, SplashActivity.START_MAIN, true);

                //
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vp = (ViewPager) findViewById(R.id.activity_viewpager);

        //viewpager设置适配器 填充数据
        vp.setAdapter(new mypagerAdpter());

        //通过 视图树观察者 添加一个全局的布局监听
        iv_point.getViewTreeObserver().addOnGlobalLayoutListener(new myOnGlobalLayoutListener());

        // 给viewpager添加页面改变的监听
        vp.addOnPageChangeListener(new myOnPageChangeListener());
    }

    // 相邻的两个灰色点直接的距离
    int pointBetween;


    /**
     *
     */
    private class myOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        protected void finalize() throws Throwable {

        }



        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            int left = (int) (position * pointBetween + positionOffset * pointBetween);


            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_point.getLayoutParams();

            params.leftMargin = left;
            iv_point.setLayoutParams(params);

        }

        @Override
        public void onPageSelected(int position) {

            if (position == al.size() - 1) {
                btn.setVisibility(View.VISIBLE);

            } else {
                btn.setVisibility(View.GONE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class myOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {

            //移除监听 节省资源 提高效率
            iv_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            // 获得两个相邻灰色指示点 的距离
            pointBetween = ll.getChildAt(1).getLeft() - ll.getChildAt(0).getLeft();


        }
    }

    private class mypagerAdpter extends PagerAdapter {

        @Override
        public int getCount() {
            return al.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = al.get(position);

            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


}
