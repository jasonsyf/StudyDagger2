package com.jason.studydagger2.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseActivity;
import com.jason.studydagger2.widget.BottomNavigationViewHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static String TAG = "rxPermissions";
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;
//    private String sdPath;//SD卡的路径
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
    }

    @Override
    protected void setListener() {
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Snackbar.make(navigation, "this is a navigationBar", Snackbar.LENGTH_SHORT).setAction("wozhidaole", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "hahaha", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;
                    case R.id.navigation_dashboard:
                        break;
                    case R.id.navigation_notifications:
                        break;
                    case R.id.navigation_notification:
                        Snackbar.make(navigation, "我是底部导航栏", Snackbar.LENGTH_SHORT).setAction("耶耶", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "嘿嘿嘿", Toast.LENGTH_SHORT).show();
                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
                        break;
                    case R.id.navigation_dashboard1:
                        Snackbar.make(navigation, "我是底部导航栏", Snackbar.LENGTH_SHORT).setAction("草", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void setData() {

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

