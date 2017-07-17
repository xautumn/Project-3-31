package com.example.edittextbug;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wuqi on 2017/6/15.
 */

public class LogUtil {

    public static final String LOCAL_LOG_PRINTER_PROPERTY = "com.xtc.watch.logger.printer";
    public static final String LOCAL_LOG_SAVE_PROPERTY = "com.xtc.watch.logger.save";

    public LogUtil() {
    }

    public static void v(String message) {
        Log.v("", message);
    }

    public static void d(String message) {
        Log.d("", message);
    }

    public static void i(String message) {
        Log.i("---", message);
    }

    public static void w(String message) {
        Log.w("", message);
    }

    public static void e(String message) {
        Log.e("", message);
    }

    public static void wtf(String message) {
        Log.wtf("", message);
    }

    public static void e(Throwable throwable) {
        Log.e("", "", throwable);
    }

    public static void v(String tag, String message) {
        Log.v(tag, message);
    }

    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    public static void w(String tag, String message) {
        Log.w(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    public static void wtf(String tag, String message) {
        Log.wtf(tag, message);
    }

    public static void w(String tag, Throwable throwable) {
        Log.w(tag, "", throwable);
    }

    public static void e(String tag, Throwable throwable) {
        Log.e(tag, "", throwable);
    }

    public static void v(String tag, String message, Throwable throwable) {
        Log.v(tag, message, throwable);
    }

    public static void d(String tag, String message, Throwable throwable) {
        Log.d(tag, message, throwable);
    }

    public static void i(String tag, String message, Throwable throwable) {
        Log.i(tag, message, throwable);
    }

    public static void w(String tag, String message, Throwable throwable) {
        Log.w(tag, message, throwable);
    }

    public static void e(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }

    public static void wtf(String tag, String message, Throwable throwable) {
        Log.wtf(tag, message, throwable);
    }

    public static String getSystemProperty(String propertyName) {
        String property = "";

        try {
            Class e = Class.forName("android.os.SystemProperties");
            Method get = e.getMethod("get", new Class[]{String.class, String.class});
            property = (String)get.invoke(e, new Object[]{propertyName, ""});
        } catch (NoSuchMethodException var4) {
            e((Throwable)var4);
        } catch (InvocationTargetException var5) {
            e((Throwable)var5);
        } catch (ClassNotFoundException var6) {
            e((Throwable)var6);
        } catch (IllegalAccessException var7) {
            e((Throwable)var7);
        }

        return property;
    }

    public static void setSystemProperty(String propertyName, boolean value) {
        try {
            Class e = Class.forName("android.os.SystemProperties");
            Method set = e.getMethod("set", new Class[]{String.class, String.class});
            set.invoke(e, new Object[]{propertyName, String.valueOf(value)});
            d(propertyName + "：" + value);
        } catch (NoSuchMethodException var4) {
            e((Throwable)var4);
        } catch (InvocationTargetException var5) {
            e((Throwable)var5);
        } catch (ClassNotFoundException var6) {
            e((Throwable)var6);
        } catch (IllegalAccessException var7) {
            e((Throwable)var7);
        }

    }
}
