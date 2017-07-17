package com.example.edittextbug.titlebar.titlebarview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.edittextbug.LogUtil;

/**
 * 手机屏幕工具类
 * <p>
 * Created by lhd on 2015/9/21.
 */
public class ScreenUtil {

    /**
     * 获取屏幕宽度(像素px)
     *
     * @return
     */
    public static int getPxWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        metric = context.getResources().getDisplayMetrics();
        return metric.widthPixels;
    }

    /**
     * 获取屏幕高度(像素px)
     *
     * @return
     */
    public static int getPxHeight(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        metric = context.getResources().getDisplayMetrics();
        return metric.heightPixels;
    }

    /**
     * 获取状态栏高度(像素px)
     *
     * @param activity
     *
     * @return
     */
    public static int getStatusBarPxHeight(Activity activity) {
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);

        int height = localRect.top;
        if (height != 0) {
            return height;
        }

        try {
            Class<?> localClass = Class.forName("com.android.internal.R$dimen");
            Object localObject = localClass.newInstance();
            int i5 = Integer.parseInt(localClass.getField("status_bar_height")
                    .get(localObject)
                    .toString());

            height = activity.getResources().getDimensionPixelSize(i5);

        } catch (ClassNotFoundException e) {
            LogUtil.e(e);
        } catch (NoSuchFieldException e) {
            LogUtil.e(e);
        } catch (InstantiationException e) {
            LogUtil.e(e);
        } catch (IllegalAccessException e) {
            LogUtil.e(e);
        }

        return height;
    }

    /**
     * 设置全屏
     */
    public static void setScreenFull(Activity context, boolean isFull) {
        if (isFull) {
            context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }
}
