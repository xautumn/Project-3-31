package com.example.a10_13netstatusutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 *  1.测试读出sim卡信息imsi
 *  2.网络状态wifi 4g 3g
 *  3.ping连接外网
 *  4.
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String imsi = IMSIUtils.getImsi(this);
        Log.i("MainActivity","imsi = "+imsi);
    }

    public void test(View v) {
        String imsi = IMSIUtils.getImsi(this);
        if (imsi == null) {
            Log.i("MainActivity","imsi = null");
        }
        Log.i("MainActivity","imsi = "+imsi);
    }
}
