package com.heheda.zhbj.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.heheda.zhbj.R;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager vp;

    private ArrayList<ImageView>  al=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);


        int [] arrs ={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

        for (int i=0;i<arrs.length;i++){
          ImageView iv=new ImageView(this);

            iv.setBackgroundResource(arrs[i]);

            al.add(iv);

        }

       vp =(ViewPager) findViewById(R.id.activity_viewpager);

        vp.setAdapter(new mypagerAdpter());
    }


    private class  mypagerAdpter extends PagerAdapter{

        @Override
        public int getCount() {
            return al.size();
        }



        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = al.get(position);

            container.addView(imageView);

            return  imageView;
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
