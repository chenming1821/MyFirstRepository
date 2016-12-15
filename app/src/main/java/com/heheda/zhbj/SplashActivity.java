package com.heheda.zhbj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class SplashActivity extends Activity {


    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rl  =(RelativeLayout) findViewById(R.id.activity_splash);


        RotateAnimation ra=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,

                0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        ra.setDuration(500);
        ra.setFillAfter(true);


        AlphaAnimation aa=new AlphaAnimation(0,1);
        aa.setDuration(500);
        aa.setFillAfter(true);

        ScaleAnimation sa=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        sa.setDuration(500);
        sa.setFillAfter(true);

        AnimationSet as=new AnimationSet(false);

        as.addAnimation(aa);
        as.addAnimation(sa);
        as.addAnimation(ra);


        as.setDuration(2000);
        rl.setAnimation(as);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Toast.makeText(SplashActivity.this,"hehda",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
