package com.jason.studydagger2.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseActivity;
import com.jason.studydagger2.base.BasePresenter;
import com.jason.studydagger2.base.contract.main.WelcomeContract;
import com.jason.studydagger2.mvpmodel.bean.WelcomeBean;
import com.jason.studydagger2.mvppresenter.WelcomePresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView mIvWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView mTvWelcomeAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.with(WelcomeActivity.this).load("http://p2.zhimg.com/10/7b/107bb4894b46d75a892da6fa80ef504a.jpg").crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvWelcomeBg);
                mIvWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
                mTvWelcomeAuthor.setText("欢迎欢迎，热烈欢迎！");
            }
        });
        Timer timer = new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        timer.schedule(timerTask,2200);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(mIvWelcomeBg);
        super.onDestroy();
    }

    @Override
    protected void initInject() {
         getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }


    @Override
    public void showContent(WelcomeBean welcomeBean) {

    }

    @Override
    public void jumpToMain() {

    }
}
