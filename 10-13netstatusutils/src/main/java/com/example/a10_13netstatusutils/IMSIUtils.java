package com.example.a10_13netstatusutils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by wuqi on 2017/10/13.
 */

public class IMSIUtils {

    private static String TAG = "IMSIUtils";
    /**
     * IMSI:国际移动用户识别码
     * IMSI由MCC、MNC、MSIN组成
     * MCC为移动国家号码，由3位数字组成， * 唯一地识别移动客户所属的国家，我国为460；
     * MNC为网络id，由2位数字组成， * 用于识别移动客户所归属的移动网络，中国移动为00，中国联通为01,中国电信为03；
     * MSIN为移动客户识别码，采用等长11位数字构成
     */
    public static String getImsi(Context context) {
        Log.i(TAG,"getImsi ");
        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String subscriberId = telManager.getSubscriberId();//卡未准备好，直接返回null 46001
        String simOperator = telManager.getSimOperator();//卡未准备好，返回 "" 不是返回null 460016862312195
        Log.i(TAG,"subscriberId = "+subscriberId);
        return simOperator;
    }
}
