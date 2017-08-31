package com.jason.studydagger2.util;



import com.jason.studydagger2.mvpmodel.http.exception.ApiException;
import com.jason.studydagger2.mvpmodel.http.response.WxNewsResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<WxNewsResponse<T>, T> handleWXResult() {   //compose判断结果
        return new FlowableTransformer<WxNewsResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<WxNewsResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<WxNewsResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(WxNewsResponse<T> tWxNewsResponse) {
                        if(tWxNewsResponse.getCode() == 200) {
                            return createData(tWxNewsResponse.getNewslist());
                        } else {
                            return Flowable.error(new ApiException(tWxNewsResponse.getMsg(), tWxNewsResponse.getCode()));
                        }
                    }
                });
            }
        };
    }


    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
