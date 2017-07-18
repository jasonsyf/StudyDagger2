package com.jason.studydagger2.mvppresenter;

import com.jason.studydagger2.base.RxPresenter;
import com.jason.studydagger2.base.contract.main.WelcomeContract;
import com.jason.studydagger2.mvpmodel.bean.WelcomeBean;
import com.jason.studydagger2.mvpmodel.http.RetrofitHelper;
import com.jason.studydagger2.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by jason_sunyf on 2017/7/17.
 * Email:yufeng.sun@21wendao.cn
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {
    private static final String RES = "1080*1076";
    private static final int COUNT_DOWN_TIME = 2200;
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public WelcomePresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getWelcomeData() {
          addSubscribe(mRetrofitHelper.fetchWelcomeInfo(RES)
          .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
          .subscribe(new Consumer<WelcomeBean>() {
              @Override
              public void accept(@NonNull WelcomeBean welcomeBean) throws Exception {
                  mView.showContent(welcomeBean);
                  startCountDown();
              }
          }, new Consumer<Throwable>() {
              @Override
              public void accept(@NonNull Throwable throwable) throws Exception {
                  mView.jumpToMain();
              }
          }));
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
        .compose(RxUtil.<Long>rxSchedulerHelper())
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                mView.jumpToMain();
            }
        }));
    }
}
