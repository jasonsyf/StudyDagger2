package com.jason.studydagger2.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseActivity;
import com.jason.studydagger2.base.BaseFragment;
import com.jason.studydagger2.ui.wxnews.fragment.WxNewsFragment;
import com.jason.studydagger2.ui.main.adapter.ViewPagerAdapter;
import com.jason.studydagger2.ui.test.TestFragment1;
import com.jason.studydagger2.ui.test.TestFragment2;
import com.jason.studydagger2.ui.test.TestFragment3;
import com.jason.studydagger2.ui.test.TestFragment4;
import com.jason.studydagger2.widget.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;
    private MenuItem menuItem;
    WxNewsFragment mWxNewsFragment;
    TestFragment1 mTestFragment1;
    TestFragment2 mTestFragment2;
    TestFragment3 mTestFragment3;
    TestFragment4 mTestFragment4;
    List<BaseFragment> mFragments = new ArrayList<>();
    //    private String sdPath;//SD卡的路径

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mMainViewpager.setCurrentItem(0);
                    break;
                case R.id.navigation_dashboard:
                    mMainViewpager.setCurrentItem(1);
                    break;
                case R.id.navigation_notifications:
                    mMainViewpager.setCurrentItem(2);
                    break;
                case R.id.navigation_notification:
                    mMainViewpager.setCurrentItem(3);
                    break;
                case R.id.navigation_dashboard1:
                    mMainViewpager.setCurrentItem(4);
                    break;
            }
            return false;
        }
    };


//    private String picPath;//图片存储路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
//        //获取SD卡的路径
    }

    @Override
    protected void initInject() {
    }

    @Override
    protected void initEventAndData() {
        setViewpager();
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mMainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setViewpager() {
        mWxNewsFragment = new WxNewsFragment();
        mTestFragment1 = new TestFragment1();
        mTestFragment2 = new TestFragment2();
        mTestFragment3 = new TestFragment3();
        mTestFragment4 = new TestFragment4();
        mFragments.add(mWxNewsFragment);
        mFragments.add(mTestFragment1);
        mFragments.add(mTestFragment2);
        mFragments.add(mTestFragment3);
        mFragments.add(mTestFragment4);
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        if (mMainViewpager != null) {
            mMainViewpager.setAdapter(mAdapter);
        }
    }
}

