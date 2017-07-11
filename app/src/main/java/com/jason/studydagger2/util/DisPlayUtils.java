package com.jason.studydagger2.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by 太能 on 2016/11/10.
 */
public class DisPlayUtils {
    private static float mScale = 0.0f; // 密度
    private static int mDpi = 0; // dpi
    private static float mFontScale = 0.0f;
    private static int mWidthPixels = 0;
    private static int mHeightPixels = 0;

    public static void init(Context context) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();// 初始化一个结构
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaysMetrics);// 对该结构赋值
        mWidthPixels = displaysMetrics.widthPixels;
        mHeightPixels = displaysMetrics.heightPixels;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        mScale = dm.density;
        mDpi = dm.densityDpi;
        mFontScale = dm.scaledDensity;

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {

        return (int) (dpValue * mScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {

        return (int) (pxValue / mScale + 0.5f);
    }

    /**
     * 得到的屏幕的宽度
     */
    public static int getWidthPx() {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸

        return mWidthPixels;
    }

    /**
     * 得到的屏幕的高度
     */
    public static int getHeightPx() {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸

        return mHeightPixels;
    }

    /**
     * 得到屏幕的dpi
     */
    public static int getDensityDpi() {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸

        return mDpi;
    }

    /**
     * 返回状态栏/通知栏的高度
     */
    public static int getStatusHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    public static int px2sp(float pxValue) {

        return (int) (pxValue / mFontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     */
    public static int sp2px(float spValue) {

        return (int) (spValue * mFontScale + 0.5f);
    }
}
