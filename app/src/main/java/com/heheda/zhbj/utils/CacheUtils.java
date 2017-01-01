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


    public static void SaveSharedPreference(Context ctx, String key ,Boolean values) {

        SharedPreferences sp = ctx.getSharedPreferences("zhbj", Context.MODE_PRIVATE);

        sp.edit().putBoolean(key, values).commit();

    }


    public static String getNewsCenterCatche(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("zhbj", Context.MODE_PRIVATE);


        return sp.getString(key, "");
    }

    public static void saveNewsCache(Context context, String key, String result) {
        SharedPreferences sp = context.getSharedPreferences("zhbj", Context.MODE_PRIVATE);

        sp.edit().putString(key, result).commit();
    }
}
