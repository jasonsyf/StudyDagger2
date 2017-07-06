package com.jason.studydagger2;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.studydagger2.dagger.DaggerUserComponent;
import com.taobao.sophix.SophixManager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    @Inject
    ApiService apiService;

    private static final int REQUEST_ORIGINAL = 2;
    @BindView(R.id.query_patch_btn)
    Button mQueryPatchBtn;
    @BindView(R.id.text)
    TextView mText;
    private static String TAG = "rxPermissions";
    @BindView(R.id.image_view)
    ImageView mImageView;
    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerUserComponent.create().inject(this);
        apiService.register();
        mText.setText("热更新之后啊");
        //获取SD卡的路径
        sdPath = Environment.getExternalStorageDirectory().getPath();
        picPath = sdPath + "/" + "temp.png";
        Log.e("sdPath1", sdPath);
    }

    @OnClick(R.id.query_patch_btn)
    public void onViewClicked() {
        SophixManager.getInstance().queryAndLoadNewPatch();
        Toast.makeText(this, "热更新了呢呢呢", Toast.LENGTH_SHORT).show();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                // 用户已经同意该权限
                                Toast.makeText(MainActivity.this, "用户已经同意该权限", Toast.LENGTH_SHORT).show();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                Toast.makeText(MainActivity.this, "用户拒绝了该权限", Toast.LENGTH_SHORT).show();
                            } else {
                                // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
                                Toast.makeText(MainActivity.this, "权限被拒绝，请在设置里面开启相应权限，若无相应权限会影响使用", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (permission.granted) {
                                // 用户已经同意该权限
                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                Uri uri = Uri.fromFile(new File(picPath));
                                //为拍摄的图片指定一个存储的路径
                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                startActivityForResult(intent2, REQUEST_ORIGINAL);
                                Toast.makeText(MainActivity.this, "用户已经同意该权限", Toast.LENGTH_SHORT).show();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                Toast.makeText(MainActivity.this, "用户拒绝了该权限", Toast.LENGTH_SHORT).show();
                            } else {
                                // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
                                Toast.makeText(MainActivity.this, "权限被拒绝，请在设置里面开启相应权限，若无相应权限会影响使用", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                });

    }

    /**
     * 返回应用时回调方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (resultCode == REQUEST_ORIGINAL) {//对应第二种方法
                /**
                 * 这种方法是通过内存卡的路径进行读取图片，所以的到的图片是拍摄的原图
                 */
                FileInputStream fis = null;
                try {
                    Log.e("sdPath2", picPath);
                    //把图片转化为字节流
                    fis = new FileInputStream(picPath);
                    //把流转化图片
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    mImageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();//关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

