package com.jason.studydagger2.dagger.module;

import com.jason.studydagger2.app.MyApplication;
import com.jason.studydagger2.mvpmodel.http.HttpHelper;
import com.jason.studydagger2.mvpmodel.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */
@Module
public class MyApplicationModule {
    private final MyApplication mMyApplication;

    public MyApplicationModule(MyApplication myApplication) {
        mMyApplication = myApplication;
    }

    @Provides
    @Singleton
    MyApplication provideApplicationContext() {
        return mMyApplication;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }
}
