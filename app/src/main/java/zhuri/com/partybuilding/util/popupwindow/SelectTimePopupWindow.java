package zhuri.com.partybuilding.util.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.date.PickerView;
import zhuri.com.partybuilding.util.date.SelectDateDialog;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/30
 * 描述:
 */

public class SelectTimePopupWindow extends PopupWindow {
    private Context context;
    private View mView;

    private TextView title;
    private TextView confirm;
    private TextView cancel;

    private LinearLayout yearLL;
    private LinearLayout monthLL;
    private LinearLayout dayLL;

    private PickerView year;
    private PickerView month;
    private PickerView day;

    private View bg;

    private String titleStr;
    private String y;
    private String m;
    private String d;
    private List<String> yearList;
    private List<String> monthList;
    private List<String> dayList;
    private String[] time;

    //是否显示 日
    private boolean showDay = true;


    public SelectTimePopupWindow(Context context, String titleStr) {
        super(context);
        this.context = context;
        this.titleStr = titleStr;
        init();
    }

    public SelectTimePopupWindow(Context context, String titleStr, boolean showDay) {
        super(context);
        this.context = context;
        this.titleStr = titleStr;
        this.showDay = showDay;
        init();
    }


    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_select_time, null);
        year = (PickerView) mView.findViewById(R.id.time_year);
        month = (PickerView) mView.findViewById(R.id.time_month);
        day = (PickerView) mView.findViewById(R.id.time_day);
        yearLL = (LinearLayout) mView.findViewById(R.id.time_year_ll);
        monthLL = (LinearLayout) mView.findViewById(R.id.time_month_ll);
        dayLL = (LinearLayout) mView.findViewById(R.id.time_day_ll);
        bg = mView.findViewById(R.id.pop_view);

        title = (TextView) mView.findViewById(R.id.tv_title);
        confirm = (TextView) mView.findViewById(R.id.tv_confirm);
        cancel = (TextView) mView.findViewById(R.id.tv_cancel);

        if (showDay) {
            dayLL.setVisibility(View.VISIBLE);
        } else {
            dayLL.setVisibility(View.GONE);
        }


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        /**
         * 获取系统时间作为当前时间
         */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String current = df.format(new Date());
        time = current.split("-");
        y = time[0];
        m = time[1];
        d = time[2];

//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(dm.widthPixels - SizeUtils.dip2px(25), ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.topMargin = SizeUtils.dip2px(18);
//        title.setLayoutParams(lp);

        yearList = new ArrayList<String>();
        monthList = new ArrayList<String>();
        dayList = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            yearList.add(String.valueOf((1900 + i)));
        }
        year.setData(yearList);
        //初始化pickerView为当前时间
        year.setSelected(Integer.parseInt(y) - 1900);
        setPickerData(monthList, 12, month, Integer.parseInt(m));
        setPickerData(dayList, getDaysByYearMonth(y, m), day, Integer.parseInt(d));
        title.setText(titleStr);

        year.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                y = text;
                m = "1";
                d = "1";
                setPickerData(monthList, 12, month, Integer.parseInt(m));
                setPickerData(dayList, getDaysByYearMonth(y, m), day, Integer.parseInt(d));
            }
        });
        month.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                m = text;
                d = "1";
                setPickerData(dayList, getDaysByYearMonth(y, m), day, Integer.parseInt(d));
            }
        });
        day.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                d = text;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.confirm(y, m.length() == 1 ? "0" + m : m, d.length() == 1 ? "0" + d : d, isLessThanCurrentTime());
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    dismiss();
            }
        });

        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.pop_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(AppUtils.getColor(R.color.translate));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 是否小于当前时间
     *
     * @return 小于返回true 等于或大于返回false
     */
    private boolean isLessThanCurrentTime() {
        if (Integer.parseInt(y) < Integer.parseInt(time[0])) {
            return true;
        } else if (Integer.parseInt(y) > Integer.parseInt(time[0])) {
            return false;
        } else if (Integer.parseInt(m) < Integer.parseInt(time[1])) {
            return true;
        } else if (Integer.parseInt(m) > Integer.parseInt(time[1])) {
            return false;
        } else if (Integer.parseInt(d) < Integer.parseInt(time[2])) {
            return true;
        } else {
            return false;
        }
    }


    private void setPickerData(List<String> data, int max, PickerView view, int select) {
        data.clear();
        for (int i = 1; i <= max; i++) {
            String d = String.valueOf(i == max ? max : i % max);
            Log.e("---------------", "==" + d);
            data.add(d.length() == 0 ? "0" + d : d);
        }
        if (select == 1) {
            //将最后一位数据放到list的第一位
            String first = data.get(max - 1);
            data.remove(max - 1);
            data.add(0, first);
            view.setData(data);
            view.setSelected(select);
        } else if (select == max) {
            //将第一位数据放到list的最后一位
            String end = data.get(0);
            data.remove(0);
            data.add(end);
            view.setData(data);
            view.setSelected(select - 2);
        } else {
            view.setData(data);
            view.setSelected(select - 1);
        }
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public int getDaysByYearMonth(String year, String month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, Integer.parseInt(year));
        a.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    private OnSelectTimeListener listener;

    public void setOnSelectTimeListener(OnSelectTimeListener listener) {
        this.listener = listener;
    }

    public interface OnSelectTimeListener {


        /**
         * @param year  年
         * @param month 月
         * @param day   日
         * @param past  和当前时间对比，时间是否已经过去，过去为 true
         */
        void confirm(String year, String month, String day, boolean past);
    }
}
