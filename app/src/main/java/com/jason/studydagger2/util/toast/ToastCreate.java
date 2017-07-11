package com.jason.studydagger2.util.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.studydagger2.R;
import com.jason.studydagger2.app.MyApplication;


/**
 * Created by 太能 on 2016/11/10.
 */
public class ToastCreate {
    private Toast mBottomToast;
    private Toast mCenterToast;
    private Toast mImgToast;

    private TextView mBottomTv;
    private TextView mCenterTv;
    private TextView mImgTv;


    /**
     * @param str 需要显示的字符串
     * @return mCenterToast
     */
    public Toast getTextCenterToast(String str) {

        initCenterTextToast().setText(str);
        return mCenterToast;
    }

    /**
     * @param strId 需要显示的字符串的resId
     * @return mCenterToast
     */
    public Toast getTextCenterToast(int strId) {
        initCenterTextToast().setText(strId);
        return mCenterToast;
    }

    /**
     * 带成功失败图片的方法
     *
     * @param strId                   需要显示的字符串的resId
     * @param isSuccess：true显示√，失败显示×
     * @return mImgToast
     */
    public Toast getImgCenterToast(int strId, boolean isSuccess) {
        initImgToast(isSuccess).setText(strId);
        return mImgToast;
    }

    /**
     * 带成功失败图片的方法
     *
     * @param strId                   需要显示的字符串的resId
     * @param isSuccess：true显示√，失败显示×
     * @return mImgToast
     */
    public Toast getImgCenterToast(String strId, boolean isSuccess) {

        initImgToast(isSuccess).setText(strId);
        return mImgToast;
    }

    public Toast getTextBottomToast(String str) {
        initBottomTextToast().setText(str);
        return mBottomToast;
    }


    /**
     * @param strId : 字符串id
     * @return mBottomToast
     */
    public Toast getTextBottomToast(int strId) {
        initBottomTextToast().setText(strId);
        return mBottomToast;
    }


    private TextView initCenterTextToast() {

        if (mCenterToast != null) {
            return mCenterTv;
        }

        Context context = MyApplication.getInstance();
        mCenterToast = new Toast(context);
        mCenterToast.setDuration(Toast.LENGTH_SHORT);
        mCenterToast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.center_toast_layout, null);
        mCenterTv = (TextView) view.findViewById(R.id.center_toast_text);
        mCenterToast.setView(view);
        return mCenterTv;
    }

    private TextView initImgToast(boolean isSuccess) {
        if (mImgToast != null) {
            return mImgTv;
        }

        Context context = MyApplication.getInstance();
        mImgToast = new Toast(context);
        mImgToast.setDuration(Toast.LENGTH_SHORT);
        mImgToast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.center_toast_img_layout, null);
        mImgTv = (TextView) view.findViewById(R.id.center_toast_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.center_toast_img);
        if (isSuccess) {
            imageView.setImageResource(R.mipmap.success);
        } else {
            imageView.setImageResource(R.mipmap.fail);
        }
        mImgToast.setView(view);
        return mImgTv;
    }


    private TextView initBottomTextToast() {
        if (mBottomToast != null) {
            return mBottomTv;
        }

        Context context =MyApplication.getInstance();
        mBottomToast = new Toast(context);
        mBottomToast.setDuration(Toast.LENGTH_SHORT);
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_toast_layout, null);
        mBottomTv = (TextView) view.findViewById(R.id.bottom_toast_text);
        mBottomToast.setView(view);
        mBottomToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        return mBottomTv;
    }
}
