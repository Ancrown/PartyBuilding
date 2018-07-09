package zhuri.com.partybuilding.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/4
 * 描述: 功能
 */

public class Function {

    /**
     * 点赞
     *
     * @param context
     * @param textView
     * @param pid
     * @param types    types：1-资讯，2-社区活动，3-微志愿，4-微心愿，5-学习，6-考试
     * @param context
     * @param textView
     */


    public static void fabulous(Boolean isLogin, final Context context, final TextView textView, String pid, String types) {

        if (isLogin) {
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {


            final Drawable fabulousNo = AppUtils.getDrawable(R.drawable.fabulous);
            final Drawable fabulousYes = AppUtils.getDrawable(R.drawable.fabulous_yes);

            fabulousNo.setBounds(0, 0, fabulousNo.getMinimumWidth(), fabulousNo.getMinimumHeight());
            fabulousYes.setBounds(0, 0, fabulousYes.getMinimumWidth(), fabulousYes.getMinimumHeight());

            Map map = new HashMap();
            map.put("uid", SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, ""));
            map.put("token", SharedPreferencesUtils.getParam(context, StaticVariables.TOKEN, ""));
            map.put("pid", pid);
            map.put("types", types);
            OkHttpUtil.getInstance(context).doPost(AddressRequest.FABULOUS, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(BaseEntity<String> response) {

                    if (response.getData().equals("0")) {
                        textView.setCompoundDrawables(fabulousYes, null, null, null);

                        textView.setText((Integer.parseInt(textView.getText().toString()) + 1) + "");
                    } else {
                        textView.setCompoundDrawables(fabulousNo, null, null, null);
                        textView.setText((Integer.parseInt(textView.getText().toString()) - 1) + "");
                    }

                }
            }, map);
        }
    }

    /**
     * 转发
     *
     * @param isLogin
     * @param context
     * @param textView
     * @param pid
     * @param types
     */
    public static void forward(Boolean isLogin, final Context context, final TextView textView, String pid, String types) {
        if (isLogin) {
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {
            Map map = new HashMap();
            map.put("uid", SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, ""));
            map.put("token", SharedPreferencesUtils.getParam(context, StaticVariables.TOKEN, ""));
            map.put("pid", pid);
            map.put("types", types);
            OkHttpUtil.getInstance(context).doPost(AddressRequest.FORWARD, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(BaseEntity<String> response) {
                    textView.setText((Integer.parseInt(textView.getText().toString()) + 1) + "");

                }
            }, map);

        }

    }

    /**
     * 活动签到
     *
     * @param isLogin
     * @param context
     * @param id
     */
    public static void signIn(Boolean isLogin, final Context context, String url, String id) {

        if (isLogin) {
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {
            Map map = new HashMap();
            map.put("uid", SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, ""));
            map.put("token", SharedPreferencesUtils.getParam(context, StaticVariables.TOKEN, ""));
            map.put("id", id);

            OkHttpUtil.getInstance(context).doPost(url, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(BaseEntity<String> response) {

                    ToolUtils.showToast(context, response.getMsg());

                }
            }, map);

        }
    }
}
