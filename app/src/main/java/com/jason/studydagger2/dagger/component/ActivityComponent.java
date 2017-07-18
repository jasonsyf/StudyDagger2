package com.jason.studydagger2.dagger.component;

import android.app.Activity;

import com.jason.studydagger2.dagger.module.ActivityModule;
import com.jason.studydagger2.dagger.scope.ActivityScope;
import com.jason.studydagger2.ui.main.activity.WelcomeActivity;

import dagger.Component;

/**
 * Created by jason_sunyf on 2017/7/17.
 * Email:yufeng.sun@21wendao.cn
 */
@ActivityScope
@Component(dependencies = MyApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(WelcomeActivity welcomeActivity);
}
