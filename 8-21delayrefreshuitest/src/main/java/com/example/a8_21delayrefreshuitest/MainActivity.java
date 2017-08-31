package com.example.a8_21delayrefreshuitest;

import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //整个页面最底层DecorView填充完毕之后，就可以开始去填充MainActivity的布局了，
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                // 填充布局
                //viewStub.inflate();
                // 2秒后移除Splash
                //mHandler.postDelayed(new DelayRunnable(MainActivity.this, splashFragment), 2000);
            }
        });

        Log.i(TAG,"onResume ");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG,"onAttachedToWindow ");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                iv.post(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageResource(R.drawable.ic_launcher);
                        //Log.i("MainActivity",Thread.currentThread()+"");
                    }
                });
                //Log.i("MainActivity",Thread.currentThread()+"");

            }
        };

        Timer timer = new Timer();
        //timer.schedule(timerTask,3000);


    }

    //1.直接原来的位置异步加载
    //2、挪到windowFocus再异步加载好，回到UI线程（可以异步的太少，这个方案不太好，异步回来还是得消耗UI线程）
    //2
    //onWindowFocusChanged  Activity用户可见的标志
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);//会立即调用ActivityManager: [AppLaunch] Displayed Displayed
        //可见的时候调用，在onAttachedToWindow之后，每次可见都会调用onWindowFocusChanged
        //打印diaplay 说明onWindowFocusChanged开始绘制第一帧
        Log.i(TAG,"onWindowFocusChanged ");
        reportFullyDrawn();
        try {
            Thread.currentThread().sleep(4000);//这里让主线程sleep，界面ui依然加载不出来
            Log.i("MainActivity",Thread.currentThread()+"-onWindowFocusChanged--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
