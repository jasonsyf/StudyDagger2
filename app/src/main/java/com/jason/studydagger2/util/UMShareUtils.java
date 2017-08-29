package com.jason.studydagger2.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by 太能 on 2016/12/16.
 */
public  class UMShareUtils {

    public ShareAction mShareAction;

    public UMShareUtils(Activity activity,
                        Context context,
                        int icon,
                        String url,
                        String text) {
        showShareActionWithDisPlay(activity,context,icon,url,text);
    }

    public UMShareUtils(Activity activity,
                        Context context,
                        int icon,
                        String url,
                        String text,
                        UMShareListener umShareListener) {
        showShareActionWithDisPlayLoadImage(activity,context,icon,url,text,umShareListener);
    }


    //需要下载的图片
    public void showShareActionWithDisPlayLoadImage(Activity activity,
                                                    final Context context,
                                                    int icon,
                                                    String url,
//                                                    String title,
                                                    String text,
                                                    UMShareListener umShareListener){
        //image是分享出来的图片
        UMImage image = new UMImage(context, icon);
        mShareAction = new ShareAction(activity)
                .withMedia(image)
                .withMedia(new UMWeb(url))
                .withText(text)
                .setDisplayList(SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.WEIXIN_FAVORITE,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
    }


    //分享链接 有标题 有内容
    public  void showShareActionWithDisPlay(Activity activity,
                                            final Context context,
                                            int icon,
                                            String url,
                                            String text) {
        //image是分享出来的图片
        UMImage image = new UMImage(context, icon);
        mShareAction = new ShareAction(activity)
                .withMedia(image)
                .withMedia(new UMWeb(url))
                .withText(text)
                .setDisplayList(SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.QQ,
                SHARE_MEDIA.QZONE,
                SHARE_MEDIA.SINA)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(context,"分享成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(context,"分享失败，请检查网络连接。",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                       // Toast.makeText(context,"分享取消",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void openShareDialog(){
        if (mShareAction!=null){
            mShareAction.open();
        }
    }

}