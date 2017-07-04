package com.jason.studydagger2.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;
import com.tbruyelle.rxpermissions2.RxPermissions;


public class InitializeService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        initBugly();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InitializeService.this, "初始化完成", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    private void initBugly() {
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        Toast.makeText(context, packageName+"initBugly", Toast.LENGTH_SHORT).show();
    }
}
