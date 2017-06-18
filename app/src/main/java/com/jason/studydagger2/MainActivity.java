package com.jason.studydagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.query_patch_btn)
    Button mQueryPatchBtn;
    @BindView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mText.setText("热更新之后啊");
    }

    @OnClick(R.id.query_patch_btn)
    public void onViewClicked() {
        SophixManager.getInstance().queryAndLoadNewPatch();
        Toast.makeText(this, "热更新了呢呢呢", Toast.LENGTH_SHORT).show();
    }
}
