package com.jason.studydagger2.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseActivity;
import com.jason.studydagger2.base.BaseFragment;
import com.jason.studydagger2.base.BasePresenter;
import com.jason.studydagger2.base.BaseView;
import com.jason.studydagger2.ui.WxNews.fragment.WxNewsFragment;
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

public class MainActivity extends BaseActivity  {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;
    private MenuItem menuItem;
    private WxNewsFragment mWxNewsFragment;
    private TestFragment1 mTestFragment1;
    private TestFragment2 mTestFragment2;
    private TestFragment3 mTestFragment3;
    private TestFragment4 mTestFragment4;
    private List<BaseFragment> mFragments = new ArrayList<>();
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        //获取SD卡的路径
//        sdPath = Environment.getExternalStorageDirectory().getPath();
//        picPath = sdPath + "/" + "temp.png";
//        Log.e("sdPath1", sdPath);
        setListener();
    }


    protected void setListener() {
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
        mMainViewpager.setAdapter(mAdapter);
    }

//
//    @OnClick(R.id.query_patch_btn)
//    public void onViewClicked() {
//        SophixManager.getInstance().queryAndLoadNewPatch();
//        Toast.makeText(this, "热更新了呢呢呢", Toast.LENGTH_SHORT).show();
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                            if (permission.granted) {
//                                // 用户已经同意该权限
//                                Toast.makeText(MainActivity.this, "用户已经同意该权限", Toast.LENGTH_SHORT).show();
//                            } else if (permission.shouldShowRequestPermissionRationale) {
//                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                                Toast.makeText(MainActivity.this, "用户拒绝了该权限", Toast.LENGTH_SHORT).show();
//                            } else {
//                                // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
//                                Toast.makeText(MainActivity.this, "权限被拒绝，请在设置里面开启相应权限，若无相应权限会影响使用", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            if (permission.granted) {
//                                // 用户已经同意该权限
//                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                Uri uri = Uri.fromFile(new File(picPath));
//                                //为拍摄的图片指定一个存储的路径
//                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                                startActivityForResult(intent2, REQUEST_ORIGINAL);
//                                Toast.makeText(MainActivity.this, "用户已经同意该权限", Toast.LENGTH_SHORT).show();
//                            } else if (permission.shouldShowRequestPermissionRationale) {
//                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                                Toast.makeText(MainActivity.this, "用户拒绝了该权限", Toast.LENGTH_SHORT).show();
//                            } else {
//                                // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
//                                Toast.makeText(MainActivity.this, "权限被拒绝，请在设置里面开启相应权限，若无相应权限会影响使用", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//
//                    }
//                });
//
//    }

    /**
     * 返回应用时回调方法
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            if (resultCode == REQUEST_ORIGINAL) {//对应第二种方法
//                /**
//                 * 这种方法是通过内存卡的路径进行读取图片，所以的到的图片是拍摄的原图
//                 */
//                FileInputStream fis = null;
//                try {
//                    Log.e("sdPath2", picPath);
//                    //把图片转化为字节流
//                    fis = new FileInputStream(picPath);
//                    //把流转化图片
//                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
//                    mImageView.setImageBitmap(bitmap);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        fis.close();//关闭流
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
}

