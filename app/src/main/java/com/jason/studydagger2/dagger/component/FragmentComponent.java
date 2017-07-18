package com.jason.studydagger2.dagger.component;

import android.app.Activity;
import com.jason.studydagger2.dagger.module.FragmentModule;
import com.jason.studydagger2.dagger.scope.FragmentScope;
import com.jason.studydagger2.ui.wxnews.fragment.WxNewsFragment;

import dagger.Component;

/**
 * Created by jason_sunyf on 2017/7/12.
 * Email:yufeng.sun@21wendao.cn
 */
@FragmentScope
@Component(dependencies = MyApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(WxNewsFragment wxNewsFragment);

}
