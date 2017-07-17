package com.example.edittextbug.titlebar.titlebarview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.example.edittextbug.LogUtil;


/**
 * Created by wq on 2017/3/2.
 * 解决在设置全屏时，adjustResize无法生效
 * 软键盘出现时，布局无法被顶上去
 */

public class TitleBarUtil {

    private final View decorView;
    private OnInputSoftListener onInputSoftListener;

    public static TitleBarUtil assistActivity (Activity activity) {
       return new TitleBarUtil(activity);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private Activity activity;

    private TitleBarUtil(Activity activity) {
        this.activity = activity;
        decorView = activity.getWindow().getDecorView();

        //decorView布局
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        //获取当前界面的根布局
        mChildOfContent = content.getChildAt(0);
        //构造器中，宽高还为赋值，监听赋值回调(设置windowSoftInputMode监听会失效)
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                //宽高赋值后（layout）或view的宽高变化（例如键盘弹出）时,会回调
                possiblyResizeChildOfContent();
            }
        });
        //当前界面根布局的layoutparam，目的告诉 父decorView自己的宽高和位置
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    /**
     * 动态设置decorview的高度，contentView宽高自动变化
     */
    private void possiblyResizeChildOfContent() {
        int height = decorView.getHeight();
        LogUtil.i("decorView="+height);
        int mHeight = ScreenUtil.getStatusBarPxHeight(activity);
        int statusHeight = 0;
        int screenHeight = 0;
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4以前状态栏会空出
            statusHeight = ScreenUtil.getStatusBarPxHeight(activity);
            screenHeight = mHeight;
        }
        int usableHeightNow = computeUsableHeight(mHeight);
        if (usableHeightNow != usableHeightPrevious) {
            //得到decorView的高度
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            LogUtil.i("srceenHeight ="+screenHeight);
            LogUtil.i("statusHeight ="+statusHeight);
            LogUtil.i("usableHeightNow ="+usableHeightNow);
            LogUtil.i("usableHeightSansKeyboard ="+usableHeightSansKeyboard);
            //得到键盘+状态栏高度
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard/4)) {
                //键盘弹出，屏幕高度-键盘高度(4.4以上)
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference+statusHeight;
                if (onInputSoftListener != null) {
                    onInputSoftListener.onIsShow(true);
                }
            } else {
                //键盘消失,占满整个屏幕（4.4以上）
                if (onInputSoftListener != null) {
                    onInputSoftListener.onIsShow(false);
                }
                frameLayoutParams.height = usableHeightNow+statusHeight;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight(int number) {
        Rect r = new Rect();
        //窗口可视区域的大小，
        decorView.getWindowVisibleDisplayFrame(r);//当为半透明时，三星机型会直接返回全屏高度，而其它手机会返回除状态栏的高度
        LogUtil.i("rTop ="+r.top);//三星无透明 为75 有为0  其它为状态栏高度
        if (r.top == 0) {
            return (r.bottom-number);
        }
        LogUtil.i("computeUsableHeight ="+(r.bottom - r.top));
        return (r.bottom - r.top);
    }

    /**
     * 键盘消失/出现接口
     * @param listener
     */
    public void setInputSoftListener(OnInputSoftListener listener) {
        this.onInputSoftListener = listener;
    }
    public interface OnInputSoftListener{
        void onIsShow(boolean flag);
    }
}
