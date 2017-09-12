package com.jason.studydagger2.dagger.module;

import android.app.Activity;


import com.jason.studydagger2.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jason_Sunyf on 2017/9/12 0012.
 * Email： jason_sunyf@163.com
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
