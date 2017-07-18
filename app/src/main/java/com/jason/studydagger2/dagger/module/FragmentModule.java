package com.jason.studydagger2.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.jason.studydagger2.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
