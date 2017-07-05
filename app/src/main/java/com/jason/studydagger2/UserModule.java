package com.jason.studydagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/7/5.
 */
@Module
public class UserModule {
    @Provides
    ApiService provideApiService() {
        return new ApiService();
    }
}
