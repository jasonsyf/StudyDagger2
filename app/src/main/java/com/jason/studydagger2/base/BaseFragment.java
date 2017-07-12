package com.jason.studydagger2.base;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.jason.studydagger2.BuildConfig;
import com.jason.studydagger2.app.MyApplication;
import com.jason.studydagger2.dagger.component.DaggerFragmentComponent;
import com.jason.studydagger2.dagger.component.FragmentComponent;
import com.jason.studydagger2.dagger.module.FragmentModule;
import com.jason.studydagger2.util.logger.LogLevel;
import com.jason.studydagger2.util.logger.Logger;
import javax.inject.Inject;


/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .myApplicationComponent(MyApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    protected InputMethodManager inputMethodManager;
    protected Bundle mArguments;
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
        initInject();

        super.onViewCreated(view, savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    protected abstract void initInject();
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
    }

}
