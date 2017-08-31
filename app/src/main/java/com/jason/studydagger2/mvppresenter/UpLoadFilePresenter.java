package com.jason.studydagger2.mvppresenter;

import android.net.Uri;
import android.util.Log;

import com.jason.studydagger2.base.RxPresenter;
import com.jason.studydagger2.base.contract.UpLoadFContract;
import com.jason.studydagger2.easylibrary.CommonSubscriber;
import com.jason.studydagger2.mvpmodel.http.RetrofitHelper;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;
import com.jason.studydagger2.util.RxUtil;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jason_Sunyf on 2017/8/31 0031.
 * Email： jason_sunyf@163.com
 */

public class UpLoadFilePresenter extends RxPresenter<UpLoadFContract.View> implements UpLoadFContract.Presenter {
//    http://www.jianshu.com/p/acfefb0a204f   上传图片文件测试demo
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public UpLoadFilePresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getUpLoadFileData(Uri fileUri) {
        File file = new File(File.pathSeparator);
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        // finally, execute the request
        Flowable<WxNewsResponse<ResponseBody>> responseBodyFlowable = mRetrofitHelper.upFile(description, body);
        addSubscribe(responseBodyFlowable.compose(RxUtil.<WxNewsResponse<ResponseBody>>rxSchedulerHelper())
        .compose(RxUtil.<ResponseBody>handleWXResult())
        .subscribeWith(new CommonSubscriber<ResponseBody>(mView) {
            @Override
            public void onNext(ResponseBody responseBody) {

            }
        }));
    }
}
