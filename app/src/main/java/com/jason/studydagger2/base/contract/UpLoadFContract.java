package com.jason.studydagger2.base.contract;

import android.net.Uri;

import com.jason.studydagger2.base.BasePresenter;
import com.jason.studydagger2.base.BaseView;

/**
 * Created by Jason_Sunyf on 2017/8/31 0031.
 * Email： jason_sunyf@163.com
 */

public interface UpLoadFContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        void getUpLoadFileData(Uri fileUri);
    }
}
