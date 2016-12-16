package com.heheda.zhbj.utils;

import android.content.Context;

/**
 * Created by cmm on 2016/12/16.
 */

public class DensityUtils {

    /**
     * dp 转换为 像素
     *
     * @return
     */
    public static int dip2px(Context context, float dpvalue) {


        float density = context.getResources().getDisplayMetrics().density;//像素密度


        return (int)(dpvalue*density+0.5f);

    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
