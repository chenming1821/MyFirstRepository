package com.heheda.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by cmm on 2016/12/15.
 */

public class CacheUtils {


    public static Boolean getIsfristStartMain(Context ctx, String key) {

        SharedPreferences sp = ctx.getSharedPreferences("zhbj", Context.MODE_PRIVATE);


        return sp.getBoolean(key, false);

    }


    public static void SaveStartMain(Context ctx, String key) {

        SharedPreferences sp = ctx.getSharedPreferences("zhbj", Context.MODE_PRIVATE);


        sp.edit().putBoolean(key, true).commit();

    }


}
