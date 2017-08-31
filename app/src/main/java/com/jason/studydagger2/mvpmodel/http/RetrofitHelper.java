package com.jason.studydagger2.mvpmodel.http;

import android.net.Uri;

import com.jason.studydagger2.app.Constants;
import com.jason.studydagger2.mvpmodel.bean.WelcomeBean;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.mvpmodel.http.api.UpLoadFileApis;
import com.jason.studydagger2.mvpmodel.http.api.WxApis;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class RetrofitHelper implements HttpHelper {
    private WxApis mWxApisService;
    private UpLoadFileApis mUpLoadFileApis;
    @Inject
    public RetrofitHelper(WxApis wxApisService,UpLoadFileApis upLoadFileApis) {
      this.mWxApisService = wxApisService;
        this.mUpLoadFileApis=upLoadFileApis;

    }

    @Override
    public Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWxNewsLList(int num, int page) {
        return mWxApisService.getWxNewsList(Constants.KEY_API, num, page);
    }

    @Override
    public Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWechatSearchListInfo(int num, int page, String word) {
        return mWxApisService.getWXHotSearch(Constants.KEY_API,num, page, word);
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mWxApisService.getWelcomeInfo(res);
    }


    @Override
    public Flowable<WxNewsResponse<ResponseBody>> upFile(RequestBody description, MultipartBody.Part file) {
        return mUpLoadFileApis.upload(description, file);
    }


}
