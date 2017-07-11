package com.jason.studydagger2.dagger.component;

import com.jason.studydagger2.app.MyApplication;
import com.jason.studydagger2.dagger.module.HttpModule;
import com.jason.studydagger2.dagger.module.MyApplicationModule;
import com.jason.studydagger2.mvpmodel.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */
@Singleton
@Component(modules = {MyApplicationModule.class, HttpModule.class})
public interface MyApplicationComponent {
    // 提供App的Context
    MyApplication getContext();
   //提供http的帮助类
    RetrofitHelper retrofitHelper();
}
