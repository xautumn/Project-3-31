package com.example.edittextbug.titlebar.titlebarview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edittextbug.LogUtil;
import com.example.edittextbug.R;

/**
 * Created by wq on 2016/11/15.
 */

public class TitleBarView extends RelativeLayout {

    private Context context;
    private String titleText;   //标题文字
    private float titleTextSize;    //标题文字大小
    private int titleTextColor;   //标题文字颜色
    private String leftText;     //左侧按钮文字
    private float leftTextSize;  //左侧文字大小
    private int leftTextColor;//左文字颜色
    private String rightText;    //右侧按钮文字
    private float rightTextSize; //右侧文字大小
    private int rightTextColor;//右文字颜色
    private Drawable leftIvDrawable; //左侧按钮背景
    private Drawable rightIvDrawable;    //右侧按钮背景
    private ImageView leftImageView; //左侧按钮控件
    private ImageView rightImageView;    //右侧按钮控件
    private TextView titleTextView;   //标题文本控件
    private RelativeLayout.LayoutParams contentParams;  //左侧控件布局参数

    private TextView leftTextView;//左tv控件
    private TextView rightTextView;//右tv控件
    private int splitColor;//标题栏分割线的底色
    private Drawable titleBarViewBackground;//标题栏整体的底色
    private boolean isNeedSplitLine;//分割线有无得开关
    private View rlInflate;//引入的布局
    private View spliteView;
    private View viewStaus;
    private Drawable statusDrawable;
    private boolean isVersion19;//版本号判断
    private int statusHeight;
    private RelativeLayout rlTitleBarviewContent;
    private View viewSplitLine;


    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        //全部在三参中执行，最大限度防止crash
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        toTransparent();
        //找到自定义的属性数组
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        initAttr(ta);
        initView();
        initData();
    }

    public void setRightTextClickable(boolean clickable) {
        rightTextView.setClickable(clickable);
    }

    /**
     * 得到属性数组中指定的属性值
     *
     * @param ta 属性数组
     */

    private void initAttr(TypedArray ta) {
        /*拿取特定的属性值*/
        titleBarViewBackground = ta.getDrawable(R.styleable.TitleBarView_titleBarViewBackground);
        //1.标题栏的属性：文字，字体，颜色（默认为黑，所以有底色的需要自行设置颜色）
        titleText = ta.getString(R.styleable.TitleBarView_titleText);
        titleTextSize = ta.getDimension(R.styleable.TitleBarView_titleTextSize, getResources().getDimension(R.dimen.titlebar_text_size_l8));
        titleTextColor = ta.getColor(R.styleable.TitleBarView_titleTextColor, getResources().getColor(R.color.titlebar_3333333));
        //2.左的属性：分两种根据调用者来显示是imageButton或textView

        //2.1左为image时：获取图片即可
        leftIvDrawable = ta.getDrawable(R.styleable.TitleBarView_leftImageViewDrawable);
        //test

        //2.2左为textView时，获取文字，字体，颜色（默认为黑色）
        leftText = ta.getString(R.styleable.TitleBarView_leftText);
        leftTextSize = ta.getDimension(R.styleable.TitleBarView_leftTextSize, getResources().getDimension(R.dimen.titlebar_text_size_l6));
        leftTextColor = ta.getColor(R.styleable.TitleBarView_leftTextColor, getResources().getColor(R.color.titlebar_3333333));

        //3.右的属性:分为两种imageButton和textView

        //3.1位textView，获取文字，字体，颜色（默认为黑色）
        rightText = ta.getString(R.styleable.TitleBarView_rightText);
        rightTextSize = ta.getDimension(R.styleable.TitleBarView_rightTextSize, getResources().getDimension(R.dimen.titlebar_text_size_l6));
        rightTextColor = ta.getColor(R.styleable.TitleBarView_rightTextColor, getResources().getColor(R.color.titlebar_3333333));

        //3.2为imageButton，获取图片
        rightIvDrawable = ta.getDrawable(R.styleable.TitleBarView_rightImageViewDrawable);
        //4.是否需要分割线（适合子啊父容器中设置了背景色，底部出现不想要的分割线的情况）
        isNeedSplitLine = ta.getBoolean(R.styleable.TitleBarView_isNeedSplitLine, true);
        //5.状态栏填充部分的背景色
        statusDrawable = ta.getDrawable(R.styleable.TitleBarView_statusDrawable);

        //test
        //资源回收
        ta.recycle();
    }

    private void toTransparent() {
        if (context instanceof Activity) {
            LogUtil.i("可以强转--成功");
            TransparentStatusBarUtil.init((Activity) context);
        } else {
            LogUtil.i("可以强转--失败");
        }
    }

    /**
     * 优化默认是控件都加载出来，默认都为gone
     * 使用者设置了属性才显示为visible，且有优先级左：iv优先，右iv优先
     */
    private void initView() {

        rlInflate = inflate(context, R.layout.titlebar_view, null);
        rlTitleBarviewContent = (RelativeLayout) rlInflate.findViewById(R.id.rl_titlebarview_content);
        viewStaus = rlInflate.findViewById(R.id.view_status);
        viewSplitLine = rlInflate.findViewById(R.id.view_titleBar_splitLine);
        titleTextView = (TextView) rlInflate.findViewById(R.id.tv_titleBar_title);
        leftTextView = (TextView) rlInflate.findViewById(R.id.tv_titleBarView_left);
        leftImageView = (ImageView) rlInflate.findViewById(R.id.iv_titleBarView_left);
        rightTextView = (TextView) rlInflate.findViewById(R.id.tv_titleBarView_right);
        rightImageView = (ImageView) rlInflate.findViewById(R.id.iv_titleBarView_right);
        spliteView = rlInflate.findViewById(R.id.view_titleBar_splitLine);


        setViewAttr(titleTextView, titleTextColor, titleTextSize);
        setViewAttr(leftTextView, leftTextColor, leftTextSize);
        setViewAttr(rightTextView, rightTextColor, rightTextSize);

        contentParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(rlInflate);
    }

    /**
     * 根据属性设置view的数据
     */
    private void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            isVersion19 = true;
            ViewGroup.LayoutParams layoutParams = viewStaus.getLayoutParams();
            statusHeight = TransparentStatusBarUtil.getStatusBarPxHeight(context);
            layoutParams.height = statusHeight;
            viewStaus.setVisibility(VISIBLE);
        }
        if (titleBarViewBackground == null) {
            //this.setBackgroundColor(Color.WHITE);
        } else {
            rlInflate.setBackgroundDrawable(titleBarViewBackground);
            LogUtil.i("设置了导航栏背景");
        }
        //没有背景色，且父控件也没有设置整体背景色，导航栏默认为白色，否则为透明，使用父的背景
        if (titleBarViewBackground == null && isNeedSplitLine) {
            setBackgroundColor(Color.WHITE);
        } else {
            LogUtil.i("父设置了全局背景");
        }

        //二.根据调用者的属性值设置view的参数
        //1.设置标题文本
        if (titleText != null) {
            LogUtil.i("center is textView");
            titleTextView.setVisibility(VISIBLE);
            titleTextView.setText(titleText);
        }

        //2左属性
        //2.1为imageView(im)
        if (leftIvDrawable != null) {
            LogUtil.i("left is imageView");
            //设置控件的id值
            leftImageView.setVisibility(VISIBLE);
            leftImageView.setImageDrawable(leftIvDrawable);
            //因图片本身较小，设置padding增加用户点击图片响应的范围，又不用设置控件具体的宽高

        }
        //2.2位textview
        if (leftText != null) {
            LogUtil.i("left is textView");
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);


        }
        //在同时设置可iv和tv的情况下，优先显示iv的back按钮
        if (leftIvDrawable != null && leftText != null) {
            leftTextView.setVisibility(GONE);
            leftImageView.setVisibility(VISIBLE);
        }

        //3右属性
        //3.1为imageButton
        if (rightIvDrawable != null) {
            LogUtil.i("right is imageView");
            rightImageView.setVisibility(VISIBLE);
            rightImageView.setImageDrawable(rightIvDrawable);

        }
        //3.2为textview时
        if (rightText != null) {
            LogUtil.i("right is textView");
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);

        }
        //在同时设置可iv和tv的情况下，优先显示iv
        if (rightIvDrawable != null && rightText != null) {
            rightTextView.setVisibility(GONE);
            rightImageView.setVisibility(VISIBLE);
        }
        //4分割线
        //有底色的是没有分割线的
        if (titleBarViewBackground == null && isNeedSplitLine) {
            LogUtil.i("buttom have splite");
            //分割线
            spliteView.setVisibility(VISIBLE);
        }
        if (statusDrawable != null) {
            viewStaus.setBackgroundDrawable(statusDrawable);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            if (isVersion19) {
                height = getResources().getDimensionPixelOffset(R.dimen.titlebar_height) + statusHeight;
                LogUtil.i("onMeasure phone is over 19");
            } else {
                height = getResources().getDimensionPixelOffset(R.dimen.titlebar_height);
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            LogUtil.i("at_most");
        } else if (mode == MeasureSpec.EXACTLY) {
            if (isVersion19) {
                height = height + statusHeight;
                LogUtil.i("onMeasure phone is over 19");
            }
            //动态修改自定义
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            LogUtil.i("exactly");
            LogUtil.i("heightMeasureSpec-statusHeight=" + statusHeight + "-height=" + height);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 设置控件属性
     */
    private void setViewAttr(TextView textView, int color, float size) {
        textView.setTextColor(color);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * 设置左点击事件接口
     *
     * @param listener 接口实现
     */
    public void setLeftOnClickListener(OnClickListener listener) {
        if (leftTextView != null) {
            //此时设置callback内部类得到的才不是null对象
            leftTextView.setOnClickListener(listener);
        }
        if (leftImageView != null) {
            leftImageView.setOnClickListener(listener);
        }
    }

    /**
     * 设置右点击事件接口
     *
     * @param listener 接口实现
     */
    public void setRightOnClickListener(OnClickListener listener) {
        if (rightTextView != null) {
            //此时设置callback内部类得到的才不是null对象
            rightTextView.setOnClickListener(listener);
        }
        if (rightImageView != null) {
            rightImageView.setOnClickListener(listener);
        }
    }

    /**
     * 标题栏标题需要动态确定的
     *
     * @param s 内容
     */
    public void setTitleBarViewTitle(CharSequence s) {
        if (titleTextView != null) {
            titleTextView.setVisibility(VISIBLE);
            titleTextView.setText(s);
        }
    }

    /**
     * 提供给右textview内容需要动态变化的
     *
     * @param s 内容
     */
    public void setRightTextViewText(CharSequence s) {
        if (rightTextView != null) {
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(s);
        }
    }

    /**
     * 提供给右textview内容需要动态变化的
     *
     * @param s 内容
     */
    public void setLeftTextViewText(CharSequence s) {
        if (leftTextView != null) {
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(s);
        }
    }

    /**
     * 设置左为TextView时控件的可见性
     *
     * @param flag true:visible，false:gone
     */
    public void setLeftTvVisibleOrInvisible(boolean flag) {
        if (leftTextView != null) {
            if (flag) {
                leftTextView.setVisibility(VISIBLE);
            } else {
                leftTextView.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置左为imageView时控件的可见性
     *
     * @param flag true:visible，false:gone
     */
    public void setLeftIvVisibleOrInvisible(boolean flag) {
        if (leftImageView != null) {
            if (flag) {
                leftImageView.setVisibility(VISIBLE);
            } else {
                leftImageView.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置右为textview时控件的可见性
     *
     * @param flag true:visible，false:gone
     */
    public void setRightTvVisibleOrInvisible(boolean flag) {
        if (rightTextView != null) {
            if (flag) {
                rightTextView.setVisibility(VISIBLE);
            } else {
                rightTextView.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置右为imageView时控件的可见性
     *
     * @param flag true:visible，false:gone
     */
    public void setRightIvVisibleOrInvisible(boolean flag) {
        if (rightImageView != null) {
            if (flag) {
                rightImageView.setVisibility(VISIBLE);
            } else {
                rightImageView.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置左textview文本颜色
     *
     * @param color 颜色数值
     */
    public void setLeftTvTextColor(int color) {
        if (leftTextView != null) {
            leftTextView.setTextColor(color);
        }
    }

    /**
     * 设置右textview文本颜色
     *
     * @param color 颜色
     */
    public void setRightTvTextColor(int color) {
        if (rightTextView != null) {
            rightTextView.setTextColor(color);
        }
    }

    /**
     * true:visible
     * false:gone
     */
    public void setTitleBarViewContentVisibleOrInvisible(boolean flag) {
        if (flag) {
            rlTitleBarviewContent.setVisibility(VISIBLE);
            viewSplitLine.setVisibility(VISIBLE);
        } else {
            rlTitleBarviewContent.setVisibility(GONE);
            viewSplitLine.setVisibility(GONE);
        }
    }
}
