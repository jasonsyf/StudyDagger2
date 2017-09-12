package com.jason.studydagger2.dagger.module;

import com.jason.studydagger2.app.MyApplication;
import com.jason.studydagger2.mvpmodel.http.HttpHelper;
import com.jason.studydagger2.mvpmodel.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jason_Sunyf on 2017/9/12 0012.
 * Email： jason_sunyf@163.com
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
