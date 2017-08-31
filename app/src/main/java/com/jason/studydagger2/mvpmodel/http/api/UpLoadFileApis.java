package com.jason.studydagger2.mvpmodel.http.api;

import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Jason_Sunyf on 2017/8/31 0031.
 * Email： jason_sunyf@163.com
 */

public interface UpLoadFileApis {
    @Multipart
    @POST("upload")
    Flowable<WxNewsResponse<ResponseBody>> upload(@Part("description") RequestBody description,
                                                 @Part MultipartBody.Part file);

//    链接：http://www.jianshu.com/p/7053a651979a

}

