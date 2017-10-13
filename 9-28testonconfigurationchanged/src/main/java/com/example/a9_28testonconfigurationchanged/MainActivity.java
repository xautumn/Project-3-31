package com.example.a9_28testonconfigurationchanged;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private String TAG = "wq,-页面1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        TextView viewById = (TextView) findViewById(R.id.tv1);
        viewById.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        boolean connectToNet = isConnectToNet(this);
        Log.i(TAG, "connectToNet = " + connectToNet);
        boolean noSim = isNoSIM(this);
        Log.i(TAG, "noSim = " + noSim);
        boolean mobileDataEnabled = getMobileDataEnabled(this);
        Log.i(TAG, "mobileDataEnabled = " + mobileDataEnabled);

        boolean wifiNet = isWifiNet(this);
        Log.i(TAG, "wifiNet = " + wifiNet);

        boolean wiFiActive = isWiFiActive(this);
        Log.i(TAG, "wiFiActive = " + wiFiActive);
        boolean ping = NetStatusUtils.ping("www.baidu.com",1);
        Log.i(TAG, "ping = " + ping);
    }


    /**
     * 只判断手机的数据流量开关是否打开
     *
     * @param context
     * @return true:数据开关打开 false：数据开关关闭
     */
    public static boolean getMobileDataEnabled(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        Method method = null;
        try {
            method = mTelephonyManager.getClass().getMethod("getDataEnabled");
            return (boolean) method.invoke(mTelephonyManager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 手机sim卡是否可用
     * @param context
     * @return true 不可用
     */
    public static boolean isNoSIM(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telephonyManager.getSimState();
        boolean isOut = (simState == TelephonyManager.SIM_STATE_ABSENT || simState == 6);
        return isOut;
    }

    /**
     * wifi开关是否打开
     * @param context
     * @return
     */
    public static boolean isWiFiActive(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * 是否连上wifi热点
     * 不判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isWifiNet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        }

        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info == null) {
            return false;
        }


        for (int i = 0; i < info.length; i++) {
            if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                return true;
            }
        }

        return false;
    }

    //连接wifi，即使没有网络也会返回true
    //数据流量开关关闭时，返回false
    public static boolean isConnectToNet(Context context) {
        if (context == null) { //如果context为空则放弃判断
            return true;
        }
        ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWorkInfo = cwjManager.getActiveNetworkInfo();
        if (netWorkInfo == null) {
            return false;
        }

        return netWorkInfo.isConnected();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    public void jump(View v) {
        Log.i(TAG, "jump");
        startActivity(new Intent(this,Main2Activity.class));
        //startActivity(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));
    }
}
