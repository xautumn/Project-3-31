package com.example.edittextbug.titlebar.titlebarview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by wq on 2017/1/19.
 * 透明状态栏效果工具类
 */

public class TransparentStatusBarUtil {
    private Activity activity;

    public TransparentStatusBarUtil() {
    }

    public static void initToFlyme(Activity activity) {
        if (isFlyme()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.parseColor("#4d000000"));
            }
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 4.4以上机型状态栏半透明
     * @param activity
     */
    public static void init(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //状态栏变半透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            /*WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                    | localLayoutParams.flags);
            activity.getWindow().setAttributes(localLayoutParams);*/
        }
        /*SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 20%透明度，保持对魅族、乐视等无半透明状态栏效果机型的兼容
        tintManager.setTintColor(Color.parseColor("#33000000"));*/
    }

    private static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    public static boolean isMoreAPI19() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return true;
        }
        return false;
    }

    public static int getStatusBarPxHeight(Context context) {
        int statusHeight;
        int resourceID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        statusHeight = context.getResources().getDimensionPixelOffset(resourceID);
        return statusHeight;
    }
}
