package com.jason.studydagger2.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jason.studydagger2.BuildConfig;
import com.jason.studydagger2.R;
import com.jason.studydagger2.util.logger.LogLevel;
import com.jason.studydagger2.util.logger.Logger;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class BaseFragment extends Fragment {
    protected InputMethodManager inputMethodManager;
    protected BaseActivity mBaseActivity;
    protected Bundle mArguments;
    private Window mWindow;
    protected Handler mHandler =
            new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArguments = savedInstanceState;
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }

    //topPadding
    //app_title_bar
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        mBaseActivity = (BaseActivity) getActivity();
        initView(view);
        setListener();
        setData();
    }

    protected void initView(View view) {
        mWindow = getActivity().getWindow();
        int fragmentBg = getResources().getColor(R.color.white);
        view.setBackgroundColor(fragmentBg);
        view.setClickable(true);
    }

    protected void setListener() {
    }

    protected void setData() {
    }

    //隐藏键盘
    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    /**
     * 返回键处理
     *
     * @return
     */
    public boolean onBackPressed() {
        hiddenLoading();
        return false;
    }

    public void popSelf() {
        popBackStack();
        // hideSoftKeyboard();
    }

    private void popBackStack() {
        try {
            if (isDetached() || isRemoving() || getFragmentManager() == null) {
                return;
            }
            if (isResumed()) {
                getFragmentManager().popBackStackImmediate();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hiddenLoading();
    }

    //======================================子类常用方法处理===================================
    public void showLoading() {
        mBaseActivity.showLoading();
    }

    public void showLoading(int textResId) {
        mBaseActivity.showLoading(textResId);
    }

    public void hiddenLoading() {
        if (mBaseActivity != null) {
            mBaseActivity.hiddenLoading();
        }
    }

    public void setWindowBgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mWindow.setAttributes(lp);
    }

}
