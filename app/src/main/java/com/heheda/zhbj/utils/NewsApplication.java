package com.heheda.zhbj.utils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by cmm on 2016/12/26.
 */

public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.setDebug(true);
        x.Ext.init(this);

    }
}
