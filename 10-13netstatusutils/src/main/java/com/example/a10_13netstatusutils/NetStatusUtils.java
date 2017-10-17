package com.example.a10_13netstatusutils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wuqi on 2017/10/11.
 */

public class NetStatusUtils {

    private static String TAG = "NetStatusUtils";




    //判断手机是否能正常上网 进行ping操作， 即使判断手机连接了wifi了（热点没有上网），手机还是不能上网
    //测试ping 一定要加联网权限android.permission.INTERNET 否则使用时不会报错 但一直显示连接不上
    //ping
    public static boolean ping(String hostIp, int pingCount) {
        String result = null;
        try {
            String ip = "www.baidu.com";
            Process p = Runtime.getRuntime().exec("ping -c 2 " + ip);//ping次数
            // 读取ping的内容，可不加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            Log.i(TAG, "result content : " + stringBuffer.toString());
            // PING的状态
            int status = p.waitFor();
            Log.i(TAG, "status = " + status);
            if (status == 0) {
                result = "successful";
                return true;
            } else {
                result = "failed cannot reach the IP address";
            }
        } catch (IOException e) {
            result = "failed IOException";
        } catch (InterruptedException e) {
            result = "failed InterruptedException";
        } finally {
            Log.i(TAG, "result = " + result);
        }
        return false;
    }


    public static boolean ping(String host, int pingCount, String s) {
        String result = null;
        try {
            //ping次数1 后面一定要加空格
            Process p = Runtime.getRuntime().exec("ping -c1 " + host);//ping次数
            // PING的状态
            int status = p.waitFor();
            Log.i(TAG,"status = " + status);
            if (status == 0) {
                result = "successful";
                return true;
            } else {
                result = "failed cannot reach the IP address";
            }

        } catch (IOException e) {
            result = "failed IOException";
        } catch (InterruptedException e) {
            result = "failed InterruptedException";
        } finally {
            Log.i(TAG,"result = " + result);
        }
        return false;
    }
}
