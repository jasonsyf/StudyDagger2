package com.jason.studydagger2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jason.studydagger2.BuildConfig;
import com.jason.studydagger2.util.logger.LogLevel;
import com.jason.studydagger2.util.logger.Logger;
import com.jason.studydagger2.util.toast.ToastLoading;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class BaseActivity extends FragmentActivity {
    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }
    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
        setListener();
        setData();
    }

    protected void initView() {
    }

    protected void setListener() {

    }

    protected void setData() {
    }
    //隐藏键盘
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN && getCurrentFocus() != null) {
            mInputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public void showAlertWithMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private ToastLoading mAppLoading;

    public void showLoading() {
        if (mAppLoading == null) {
            mAppLoading = new ToastLoading(this);
        }
        mAppLoading.open();
    }

    public void showLoading(int textResId) {
        if (mAppLoading == null) {
            mAppLoading = new ToastLoading(this);
        }
        mAppLoading.open(textResId);
    }

    public void hiddenLoading() {
        if (mAppLoading != null) {
            mAppLoading.close();
        }
    }
}
