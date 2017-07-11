package com.jason.studydagger2.base;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public interface BasePresenter <T extends BaseView>{

    void attachView(T view);

    void detachView();
}

