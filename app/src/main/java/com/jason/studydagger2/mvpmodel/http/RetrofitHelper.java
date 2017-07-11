package com.jason.studydagger2.mvpmodel.http;

import com.jason.studydagger2.app.Constants;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.mvpmodel.http.api.WxApis;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class RetrofitHelper implements HttpHelper {
    private WxApis mWxApisService;
    @Inject
    public RetrofitHelper(WxApis wxApisService) {
      this.mWxApisService = wxApisService;
    }

    @Override
    public Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWxNewsLList(int num, int page) {
        return mWxApisService.getWxNewsList(Constants.KEY_API, num, page);
    }

    @Override
    public Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWechatSearchListInfo(int num, int page, String word) {
        return mWxApisService.getWXHotSearch(Constants.KEY_API,num, page, word);
    }

}
