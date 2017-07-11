package com.jason.studydagger2.base;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public interface BaseView {

    void showErrorMsg(String msg);

    void showLoading(String msg);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
