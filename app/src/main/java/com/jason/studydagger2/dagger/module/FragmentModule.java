package com.jason.studydagger2.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.jason.studydagger2.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jason_Sunyf on 2017/9/12 0012.
 * Email： jason_sunyf@163.com
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
