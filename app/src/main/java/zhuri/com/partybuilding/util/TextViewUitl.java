package zhuri.com.partybuilding.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/12
 * 描述: 处理TextView
 */

public class TextViewUitl {

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
     * 改变某些字段的大小
     *
     * @param text
     * @param change
     * @param size
     * @param textView
     */
    public static void toStringChangeSize(String text, String change, int size, TextView textView) {
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
                            new TextAppearanceSpan(null, 0, SizeUtils.dip2px(size), null, null),
                            temp,
                            temp + change.length(),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 设置指定位置文字的颜色

                    textView.setText(style);
                }
            }
        }
    }

    /***
     * 改变某些字段的大小和颜色
     *
     * @param text
     * @param change
     * @param size
     * @param textView
     */
    public static void toStringChangeSizeAndColor(String text, String change, int size, String color, TextView textView) {
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
                            new TextAppearanceSpan(null, 0, SizeUtils.dip2px(size), null, null),
                            temp,
                            temp + change.length(),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 设置指定位置文字的大小
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

}
