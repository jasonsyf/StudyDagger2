package com.jason.studydagger2.dagger;

import com.jason.studydagger2.MainActivity;
import com.jason.studydagger2.dagger.UserModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/7/5.
 */
@Component(modules = {UserModule.class})
public interface UserComponent {
    //当前只能写MainActivity，不能写Activity，要不然会出现空指针。
    void inject(MainActivity activity);
}
