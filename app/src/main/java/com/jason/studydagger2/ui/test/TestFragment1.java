package com.jason.studydagger2.ui.test;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseFragment;
import com.jason.studydagger2.util.UMShareUtils;
import com.jason.studydagger2.util.toast.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment1 extends BaseFragment {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.qq_share)
    Button mQqShare;
    @BindView(R.id.qqZone_share)
    Button mQqZoneShare;
    @BindView(R.id.wx_share)
    Button mWxShare;
    @BindView(R.id.wx_circle_share)
    Button mWxCircleShare;
    @BindView(R.id.weibo_share)
    Button mWeiboShare;
    @BindView(R.id.all_share)
    Button mAllShare;
    Unbinder unbinder;
    @BindView(R.id.qq_login)
    Button mQqLogin;
    @BindView(R.id.wx_login)
    Button mWxLogin;
    @BindView(R.id.weibo_login)
    Button mWeiboLogin;

    public TestFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showErrorMsg(String msg) {

    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            ToastUtil.showBottom("分享成功");
//            if (platform.toString().equals("QQ")){
//                ToastUtil.showToast("手机QQ分享成功");
//            }else if (platform.toString().equals("QZONE")){
//                ToastUtil.showToast("QQ空间分享成功");
//            }else if (platform.toString().equals("WEIXIN")){
//                ToastUtil.showToast("微信好友分享成功");
//            }else if (platform.toString().equals("WEIXIN_CIRCLE")){
//                ToastUtil.showToast("微信朋友圈分享成功");
//            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showBottom("分享失败");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showBottom("分享取消");
        }
    };

    @OnClick({R.id.qq_share, R.id.qqZone_share, R.id.wx_share, R.id.wx_circle_share, R.id.weibo_share, R.id.all_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qq_share:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QQ)
                        .withText("我是分享的qq")
                        .withMedia(new UMImage(mContext, R.mipmap.ic_launcher))
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.qqZone_share:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QZONE)
                        .withText("我是分享的QQ空间")
                        .withMedia(new UMImage(mContext, R.mipmap.ic_launcher))
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.wx_share:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .withText("我是分享的微信")
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.wx_circle_share:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withText("我是分享的朋友圈")
                        .setCallback(umShareListener)
                        .share();


                break;
            case R.id.weibo_share:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.SINA)
                        .withText("我是分享的微博")
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.all_share:

                //没有图片的头像
                UMShareUtils shareUtils =
                        new UMShareUtils(getActivity(),
                                getContext(),
                                R.mipmap.ic_launcher,
                                "http://www.baidu.com",
                                "我是text", umShareListener);
                shareUtils.openShareDialog();
//                new ShareAction(getActivity())
//                        .withText("分享到以下平台：")
//                        .withMedia(new UMImage(mContext, R.mipmap.ic_launcher))
//                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,
//                                SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN_CIRCLE)
//                        .setCallback(umShareListener)
//                        .open();
                break;
        }
    }

    @OnClick({R.id.qq_login, R.id.wx_login, R.id.weibo_login})
    public void onViewClicked1(View view) {
        switch (view.getId()) {
            case R.id.qq_login:
                authorization(SHARE_MEDIA.QQ);
                break;
            case R.id.wx_login:
                authorization(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.weibo_login:
                authorization(SHARE_MEDIA.SINA);
                break;
        }
    }

    //授权
    private void authorization(SHARE_MEDIA share_media) {
        UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d(TAG, "onStart " + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.d(TAG, "onComplete " + "授权完成");

                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");

                Toast.makeText(getActivity(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();

                //拿到信息去请求登录接口。。。
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.d(TAG, "onError " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.d(TAG, "onCancel " + "授权取消");
            }
        });
    }

}
