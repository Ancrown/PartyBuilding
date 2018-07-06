package zhuri.com.partybuilding.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
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


    /**
     * 设置状态栏高度
     *
     * @param statusBarHeight
     */
    public static void setStatusBarHeight(int statusBarHeight,View view) {

        //如果sdk版本大于4.4则设置状态栏透明化 会导致首页状态栏减少
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //19 以上
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = statusBarHeight;
            view.setLayoutParams(params);
        } else {
            //19 以下
            Log.e("eeeeee版本", "222");
        }

        Log.e("eeeeee版本", "" + Build.VERSION.SDK_INT + " " + Build.VERSION_CODES.KITKAT);




    }
}
