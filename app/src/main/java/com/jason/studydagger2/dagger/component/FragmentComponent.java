package com.jason.studydagger2.dagger.component;

import android.app.Activity;

import com.jason.studydagger2.dagger.module.FragmentModule;
import com.jason.studydagger2.dagger.module.MyApplicationModule;
import com.jason.studydagger2.dagger.scope.FragmentScope;
import com.jason.studydagger2.ui.WxNews.fragment.WxNewsFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = MyApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(WxNewsFragment wxNewsFragment);

}
