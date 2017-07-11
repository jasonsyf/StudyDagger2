package com.jason.studydagger2.util.toast;


import android.support.design.widget.Snackbar;
import android.view.View;

import com.jason.studydagger2.app.MyApplication;

/**
 * Created by 太能 on 2016/11/10.
 */
public class ToastUtil {
    private static ToastCreate mToastCreate = new ToastCreate();

    public static void showCenterText(String text) {
        mToastCreate.getTextCenterToast(text).show();
    }

    public static void showCenterText(int textId) {
        if (textId < 0) {
            return;
        }
        mToastCreate.getTextCenterToast(textId).show();
    }

    public static void showCenterImg(String text, boolean isSuccess) {
        mToastCreate.getImgCenterToast(text, isSuccess).show();
    }

    public static void showCenterImg(int textId, boolean isSuccess) {
        mToastCreate.getImgCenterToast(textId, isSuccess).show();
    }

    public static void showCenterText(int textIdFirst, Throwable e) {
        String errorMsg = "";
        if (e != null) {
            errorMsg = e.getMessage();
        }
        showCenterText(textIdFirst,errorMsg);
    }

    public static void showCenterText(int textIdFirst, String second) {
        String text = MyApplication.getInstance().getResources().getString(textIdFirst);
        mToastCreate.getTextBottomToast(String.format("%s%s", text, second)).show();
    }

    public static void showBottom(int textId) {
        if (textId <0) {
            return;
        }
        mToastCreate.getTextBottomToast(textId).show();
    }

    public static void showBottom(String str) {
        mToastCreate.getTextBottomToast(str).show();
    }

    public static void showBottom(int textIdFirst, int textIdSecond) {
        String text = MyApplication.getInstance().getResources().getString(textIdFirst);
        String textSecond = "";
        if (textIdSecond >0) {
            textSecond = MyApplication.getInstance().getResources().getString(textIdSecond);
        }
        showBottom(text,textSecond);
    }

    public static void showBottom(String textFirst, String textSecond) {
        mToastCreate.getTextBottomToast(String.format("%s%s", textFirst, textSecond)).show();
    }

    public static void showBottomText(int textIdFirst, Throwable e) {
        String errorMsg = "";
        if (e != null) {
            errorMsg = e.getMessage();
        }
        showBottomText(textIdFirst,errorMsg);
    }

    public static void showBottomText(int textIdFirst, String msg) {
        String text = "";
        if (textIdFirst > 0) {
            text = MyApplication.getInstance().getResources().getString(textIdFirst);
        }
        mToastCreate.getTextBottomToast(String.format("%s%s", text, msg)).show();
    }

    public static void showSnackBarLong(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackBarShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
