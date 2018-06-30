package zhuri.com.partybuilding.util.popupwindow;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.util.popupwindow.photoview.NewsModifyAvatarPopupWindow;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/29
 * 描述: 相机
 */

public class CameraUtil {
    private static Activity activity;

    //弹出新的popupWindow
    //selectModel =  -1剪切  0 单选  1-9 多选
    public static void getNewsPopupWindow( Activity activity, int selectModel) {
        CameraUtil.activity = activity;
        NewsModifyAvatarPopupWindow modifyAvatarPopupWindow = new NewsModifyAvatarPopupWindow(activity, 1, selectModel);
//        ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
//        modifyAvatarPopupWindow.setSelectModel(selectModel);
//        modifyAvatarPopupWindow.setBackgroundDrawable(colorDrawable);
//        modifyAvatarPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        modifyAvatarPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        modifyAvatarPopupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
//      //  modifyAvatarPopupWindow.setAnimationStyle(R.style.anim_menu_bottombar); //设置动画
    }

}
