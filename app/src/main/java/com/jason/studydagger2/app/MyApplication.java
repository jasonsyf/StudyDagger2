package com.jason.studydagger2.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.jason.studydagger2.dagger.component.DaggerMyApplicationComponent;
import com.jason.studydagger2.dagger.component.MyApplicationComponent;
import com.jason.studydagger2.dagger.module.HttpModule;
import com.jason.studydagger2.dagger.module.MyApplicationModule;
import com.jason.studydagger2.service.InitializeService;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class MyApplication extends Application {
    private Set<Activity> allActivities;
    public static MyApplicationComponent appComponent;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    private static MyApplication instance;
    public static synchronized MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SophixManager.getInstance().setContext(this)
                .setAppVersion("1.1.0")
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
        //初始化屏幕宽高
        getScreenSize();
        //在子线程中完成其他初始化
        InitializeService.start(this);
        UMShareAPI.get(this);
        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106306325", "RfJraplKOwKgyn8E");
        PlatformConfig.setSinaWeibo("1239979837", "e4239b342a820f223795d58390a83b66", "http://sns.whalecloud.com");
    }



    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static MyApplicationComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerMyApplicationComponent.builder()
                    .myApplicationModule(new MyApplicationModule(getInstance()))
                    .httpModule(new HttpModule())
            .build();
        }
        return appComponent;
    }
}
