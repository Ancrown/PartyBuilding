package zhuri.com.partybuilding.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import zhuri.com.partybuilding.base.BaseApplication;

/**
 * 工具类
 */

public class ToolUtils {

    private static BaseApplication application;

    static {
        application = BaseApplication.getInstance();
    }

    public static Toast mToast;

    /**
     * 无延时的Toast
     *
     * @param mContext
     * @param msg
     */
    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 关闭软键盘
     */
    public static void closeKeyboard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取屏幕高
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * 获取屏幕宽
     */
    public static int getScreenWith(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /***
     * 改变某些字段的颜色
     *
     * @param text
     * @param change
     * @param color
     * @param textView
     */
    public static void toStringChangeColor(String text, String change, String color, TextView textView) {
        if (change != null) {
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            text += " ";
            if (text.indexOf(change) != -1) {
                String[] s = text.split(change);
                int temp = 0;
                for (int i = 0; i < s.length; i++) {
                    if (i != s.length - 1) {
                        if (i == 0) {
                            temp += s[i].length();
                        } else {
                            temp += s[i].length() + change.length();
                        }
                    }
                    style.setSpan(
                            new ForegroundColorSpan(Color.parseColor(color)),
                            temp,
                            temp + change.length(),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 设置指定位置文字的颜色
                    textView.setText(style);
                }
            }
        }
    }

    /***
     * 改变某些文字的颜色
     *
     * @param text
     * @param change
     * @param color
     * @param textView
     */
    public static void toStringChangeColor(String text, String[] change, String color, TextView textView) {
        if (change != null) {
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            text += " ";
            for (int j = 0; j < change.length; j++) {
                if (change.length != -1) {
                    String[] s = text.split(change[j]);
                    int temp = 0;
                    for (int i = 0; i < s.length; i++) {
                        if (i != s.length - 1) {
                            if (i == 0) {
                                temp += s[i].length();
                            } else {
                                temp += s[i].length() + change[j].length();
                            }
                        }
                        style.setSpan(
                                new ForegroundColorSpan(Color.parseColor(color)),
                                temp,
                                temp + change[j].length(),
                                Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 设置指定位置文字的颜色
                        textView.setText(style);
                    }
                }
            }

        }
    }


    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     */
    public static void fitsSystemWindows(Activity activity) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            //布局预留状态栏高度的 padding
            parentView.setFitsSystemWindows(true);
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }
}
