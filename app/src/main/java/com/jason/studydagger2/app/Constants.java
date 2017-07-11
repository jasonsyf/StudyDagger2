package com.jason.studydagger2.app;

import android.app.Application;
import android.os.Environment;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {

    //================= TYPE ====================
    public static final int TYPE_WECHAT = 1;
    //================= KEY ====================
    //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请
    public static final String KEY_API = "f5ac5c7f469b309c6cb1d61f975218f6";

    //================= PATH ====================
    public static final String PATH_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "jason" + File.separator + "StudyDagger2";
    //================= PREFERENCE ====================

    //================= INTENT ====================

}
