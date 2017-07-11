package com.jason.studydagger2.base.contract;

import com.jason.studydagger2.base.BasePresenter;
import com.jason.studydagger2.base.BaseView;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;

import java.util.List;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public interface WxNewsContract {
    interface View extends BaseView {
        void showContent(List<WxNewsBean> wxNewsBeanList);

        void showMoreContent(List<WxNewsBean> wxNewsBeanList);
    }

    interface Presenter extends BasePresenter<View> {
        void getWxNewsData();

        void getMoreWxNewsData();
    }

}
