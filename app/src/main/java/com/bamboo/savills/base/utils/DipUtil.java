package com.bamboo.savills.base.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DipUtil {

    public static int getScreenWidth(Activity activity) {
        int fullWidth = getDisplayMetrics(activity).widthPixels;
        return fullWidth;
    }

    public static int getScreenHeight(Activity activity) {
        int heightPixels = getDisplayMetrics(activity).heightPixels;
        return heightPixels;
    }

    public static int calcFromDip(Activity activity, int number) {
        float f = getDisplayMetrics(activity).density;
        return (int) (number * f + 0.5);
    }

    static DisplayMetrics sDisplay = null;

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        if (sDisplay == null) {
            sDisplay = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay()
                    .getMetrics(sDisplay);
        }
        return sDisplay;
    }

    public static float getDensity(Activity activity) {
        float density = getDisplayMetrics(activity).density;
        // int densityDpi = getDisplayMetrics(activity_editpic).densityDpi;
        return density;
    }
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸不变
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}