package com.heheda.zhbj.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.heheda.zhbj.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by cmm on 2016/12/27.
 */

public class BasePager {


    public final Context context;

    //每个子页面
    public View  rootview;

    @ViewInject(R.id.btn_togle_slidingmenu)
    public ImageButton ibtn;

    @ViewInject(R.id.tv_title)
    public TextView tv_title;

    @ViewInject(R.id.fr_content)
    public FrameLayout fl_content;


    public BasePager(Context context){

        this.context=context;

        rootview = initview();
    }


    public View initview(){

        View  view=View.inflate(context, R.layout.base_pager,null);
        x.view().inject(BasePager.this,view);


        return view;

    }

    public void initData(){

    }

}
