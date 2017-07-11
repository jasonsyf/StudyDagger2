package com.jason.studydagger2.mvppresenter;


import com.jason.studydagger2.app.Constants;
import com.jason.studydagger2.base.RxPresenter;
import com.jason.studydagger2.base.contract.WxNewsContract;
import com.jason.studydagger2.easylibrary.CommonSubscriber;
import com.jason.studydagger2.easylibrary.RxBus;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.mvpmodel.http.RetrofitHelper;
import com.jason.studydagger2.mvpmodel.http.event.SearchEvent;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;
import com.jason.studydagger2.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class WxNewsPresenter extends RxPresenter<WxNewsContract.View> implements WxNewsContract.Presenter {

    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WxNewsPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void attachView(WxNewsContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    @Override
    public void detachView() {
        super.detachView();
        unSubscribe();
    }

    @Override
    public void getWxNewsData() {
        queryStr = null;
        currentPage = 1;
        addSubscribe(mRetrofitHelper.fetchWxNewsLList(NUM_OF_PAGE, currentPage)
                .compose(RxUtil.<WxNewsResponse<List<WxNewsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxNewsBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WxNewsBean>>(mView){
                    @Override
                    public void onNext(List<WxNewsBean> wxNewsBeanList) {
                        mView.showContent(wxNewsBeanList);
                    }
                }));

    }

    @Override
    public void getMoreWxNewsData() {
        Flowable<WxNewsResponse<List<WxNewsBean>>> wxNewsResponseFlowable;
        if (queryStr != null) {
            wxNewsResponseFlowable = mRetrofitHelper.fetchWechatSearchListInfo(NUM_OF_PAGE, ++currentPage, queryStr);
        } else {
            wxNewsResponseFlowable = mRetrofitHelper.fetchWxNewsLList(NUM_OF_PAGE, ++currentPage);
        }
        addSubscribe(wxNewsResponseFlowable
                .compose(RxUtil.<WxNewsResponse<List<WxNewsBean>>>rxSchedulerHelper())
        .compose(RxUtil.<List<WxNewsBean>>handleWXResult())
        .subscribeWith(new CommonSubscriber<List<WxNewsBean>>(mView, "没有更多数据了") {
            @Override
            public void onNext(List<WxNewsBean> wxNewsBeanList) {
                mView.showMoreContent(wxNewsBeanList);
            }
        }));
    }
    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(SearchEvent.class)
                .compose(RxUtil.<SearchEvent>rxSchedulerHelper())
                .filter(new Predicate<SearchEvent>() {
                    @Override
                    public boolean test(@NonNull SearchEvent searchEvent) throws Exception {
                        return searchEvent.getType() == Constants.TYPE_WECHAT;
                    }
                })
                .map(new Function<SearchEvent, String>() {
                    @Override
                    public String apply(SearchEvent searchEvent) {
                        return searchEvent.getQuery();
                    }
                })
                .subscribeWith(new CommonSubscriber<String>(mView, "搜索失败ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(String s) {
                        queryStr = s;
                        getSearchWechatData(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
                    }
                })
        );
    }
    private void getSearchWechatData(String query) {
        currentPage = 1;
        addSubscribe(mRetrofitHelper.fetchWechatSearchListInfo(NUM_OF_PAGE,currentPage,query)
                .compose(RxUtil.<WxNewsResponse<List<WxNewsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxNewsBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WxNewsBean>>(mView) {
                    @Override
                    public void onNext(List<WxNewsBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                })
        );
    }
}
