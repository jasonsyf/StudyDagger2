package com.jason.studydagger2.mvpmodel.http;

import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public interface HttpHelper {
    Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWxNewsLList(int num, int page);

    Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWechatSearchListInfo(int num, int page, String word);
}
