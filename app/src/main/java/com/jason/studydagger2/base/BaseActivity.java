package com.jason.studydagger2.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jason.studydagger2.BuildConfig;
import com.jason.studydagger2.app.MyApplication;
import com.jason.studydagger2.dagger.component.ActivityComponent;
import com.jason.studydagger2.dagger.component.DaggerActivityComponent;
import com.jason.studydagger2.dagger.module.ActivityModule;
import com.jason.studydagger2.util.logger.LogLevel;
import com.jason.studydagger2.util.logger.Logger;
import com.jason.studydagger2.util.toast.ToastUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity implements BaseView {

    private InputMethodManager mInputMethodManager;
    protected Activity mContext;
    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .myApplicationComponent(MyApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        MyApplication.getInstance().addActivity(this);
        initEventAndData();
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

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }

    //隐藏键盘
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN && getCurrentFocus() != null) {
            mInputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.showSnackBarShort(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();

    protected abstract void initEventAndData();
}
