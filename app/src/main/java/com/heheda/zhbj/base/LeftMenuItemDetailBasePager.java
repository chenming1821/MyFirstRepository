package com.heheda.zhbj.base;

import android.content.Context;
import android.view.View;

/**
 * Created by cmm on 2016/12/30.
 */

public abstract class LeftMenuItemDetailBasePager {

    public final Context context;
    public   View rootview;

    public  LeftMenuItemDetailBasePager(Context context){

        this.context=context;

        rootview=initView();
    }

    public abstract View initView();


    public  void initData(){

    }

}
