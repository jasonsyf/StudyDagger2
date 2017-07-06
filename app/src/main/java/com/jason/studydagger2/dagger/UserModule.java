package com.jason.studydagger2.dagger;

import com.jason.studydagger2.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/7/5.
 */
@Module
public class UserModule {
    @Provides
    public ApiService provideApiService() {
        return new ApiService();
    }
}
