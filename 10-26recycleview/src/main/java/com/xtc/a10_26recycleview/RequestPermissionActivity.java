package com.xtc.a10_26recycleview;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.graphics.Interpolator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;


/**
 * 自定义列表样式,recycleview
 */
public class RequestPermissionActivity extends AppCompatActivity {

    private View viewById;
    private TranslateAnimation translateAnimation;

    @RequiresApi(api = Build.VERSION_CODES.M)//只是去除警告，不能解决在不同版本上运行的问题
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv);

        Log.i("wq","onCreate");
        /*ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        ob*/
        translateAnimation = new TranslateAnimation(0, 0, 0, 200);
        translateAnimation.setDuration(1000);
        translateAnimation.setInterpolator(new TestInter());


        //new AccelerateDecelerateInterpolator();
        requestPermission();
    }

    public void test(View v) {
        viewById.startAnimation(translateAnimation);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermission() {
        Log.i("wq","requestPermission");
        //android 6.0以上需要动态申请权限
        String []permissions = {Manifest.permission.ACCESS_WIFI_STATE};
        requestPermissions(permissions,200);
    }

    /**
     * 请求权限后回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("wq","onRequestPermissionsResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
