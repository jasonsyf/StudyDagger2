package com.jason.studydagger2.util.toast;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseActivity;
import com.jason.studydagger2.util.DisPlayUtils;


/**
 * Created by 太能 on 2016/11/10.
 */
public class ToastLoading {
    protected TextView mTextView;
    protected ImageView mLoadingImg;
    protected Animation mAnimation;
    protected View mLoadingLayout;
    /**
     * 点击loading的任何位置关闭loading
     */
    protected boolean mOutsideTouchCancelable = true;

    public ToastLoading(BaseActivity activity) {
        initView(activity);
    }

    public ToastLoading(BaseActivity activity, boolean outsideTouchCancelable) {
        this.mOutsideTouchCancelable = outsideTouchCancelable;
        initView(activity);
    }


    protected void initView(BaseActivity activity) {
        initLayout(activity);
        mTextView = (TextView) mLoadingLayout.findViewById(R.id.loading_operate_text);
        mLoadingImg = (ImageView) mLoadingLayout.findViewById(R.id.loading_operate_img);
        mAnimation = AnimationUtils.loadAnimation(activity, R.anim.roatate);
        setListener();
    }

    private void initLayout(Activity activity) {
        Window window = activity.getWindow();
        Rect frame = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;//拿到状态栏的高度
        FrameLayout decorView = (FrameLayout) window.getDecorView();//DecorView 是整个视图的根View，它是一个FrameLayout,相关链接：http://www.nowamagic.net/academy/detail/50160216
        mLoadingLayout = LayoutInflater.from(activity).inflate(R.layout.loading_operate, decorView, false);
        mLoadingLayout.setVisibility(View.GONE);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mLoadingLayout.getLayoutParams();
        initHeight(statusBarHeight,params);
        decorView.addView(mLoadingLayout);
    }

    protected void initHeight(int statusBarHeight,FrameLayout.LayoutParams params) {
        params.topMargin = DisPlayUtils.dip2px(45f) + statusBarHeight; //loadingLayout 距离手机屏幕顶部的距离 = headView + statusBar 的高度之和，这样 headView 上面的按钮就可以点击了
        mLoadingLayout.setLayoutParams(params);
    }

    protected void setListener() {
        mLoadingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOutsideTouchCancelable) {
                    mLoadingLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    /**设置loading的文字
     * @param text ：loading的文字
     */
    public void setLoadingText(String text) {
        mTextView.setText(text);
    }

    /**设置loading的文字
     * @param textResId ： 文字的资源id
     */
    public void setLoadingText(int textResId) {
        mTextView.setText(textResId);
    }

    /**
     * 打开loading
     */
    public void open() {
        if (isOpen()) {
            return;
        }
        mLoadingLayout.setVisibility(View.VISIBLE);
        showLoadingAnim(true);
    }

    /**打开loading
     * @param text ：显示的loading的文字
     */
    public void open(String text) {
        open();
        setLoadingText(text);
    }

    /**打开loading
     * @param textResId ：显示的loading的文字的资源id
     */
    public void open(int textResId) {
        open();
        setLoadingText(textResId);
    }

    /**
     * 关闭loading
     */
    public void close() {
        mLoadingLayout.setVisibility(View.GONE);
        showLoadingAnim(false);
    }


    private boolean isOpen() {
        return mLoadingLayout.getVisibility() == View.VISIBLE;
    }


    protected void showLoadingAnim(boolean show){
        if (show) {
            mLoadingImg.setVisibility(View.VISIBLE);
            mLoadingImg.startAnimation(mAnimation);
        } else {
            mLoadingImg.setVisibility(View.INVISIBLE);
            mLoadingImg.clearAnimation();
        }
    }
}
