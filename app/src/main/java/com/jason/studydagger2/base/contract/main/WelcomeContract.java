package com.jason.studydagger2.base.contract.main;

import android.view.View;

import com.jason.studydagger2.base.BasePresenter;
import com.jason.studydagger2.base.BaseView;
import com.jason.studydagger2.mvpmodel.bean.WelcomeBean;

/**
 * Created by jason_sunyf on 2017/7/17.
 * Email:yufeng.sun@21wendao.cn
 */

public interface WelcomeContract {
    interface View extends BaseView {
        void showContent(WelcomeBean welcomeBean);

        void jumpToMain();
    }

    interface Presenter extends BasePresenter<View> {
        void getWelcomeData();
    }
}
