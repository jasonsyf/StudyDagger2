package com.jason.studydagger2.mvpmodel.http;

import android.net.Uri;

import com.jason.studydagger2.mvpmodel.bean.WelcomeBean;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Part;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public interface HttpHelper {
    Flowable<WelcomeBean> fetchWelcomeInfo(String res);

    Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWxNewsLList(int num, int page);

    Flowable<WxNewsResponse<List<WxNewsBean>>> fetchWechatSearchListInfo(int num, int page, String word);

    Flowable<WxNewsResponse<ResponseBody>> upFile(RequestBody description,
                                  MultipartBody.Part file);
}
