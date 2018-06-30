package zhuri.com.partybuilding.util.date;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zhuri.com.partybuilding.R;


/**
 * Function:
 */
public class SelectDateDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "SelectDateDialog";
    private TextView title;
    private PickerView year;
    private PickerView month;
    private PickerView day;
    private TextView confirm;
    private TextView cancel;
    private String titleStr;
    private String y;
    private String m;
    private String d;
    private List<String> yearList;
    private List<String> monthList;
    private List<String> dayList;
    private String[] time;

    public SelectDateDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_date);
        //      setCanceledOnTouchOutside(false);
        year = (PickerView) findViewById(R.id.time_year);
        month = (PickerView) findViewById(R.id.time_month);
        day = (PickerView) findViewById(R.id.time_day);
        title = (TextView) findViewById(R.id.time_title);
        confirm = (TextView) findViewById(R.id.confirm);
        cancel = (TextView) findViewById(R.id.cancel);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        /**
         * 获取系统时间作为当前时间
         */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String current = df.format(new Date());
        Log.e(TAG, current);
        time = current.split("-");
        y = time[0];
        m = time[1];
        d = time[2];

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dm.widthPixels - dpTopx(25), ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = dpTopx(18);
        title.setLayoutParams(lp);
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

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (listener == null) return;
        switch (v.getId()) {
            case R.id.confirm:
                if (listener != null) {
                    listener.confirm(y, m.length() == 1 ? "0" + m : m, d.length() == 1 ? "0" + d : d, isLessThanCurrentTime());

                }
                break;
            case R.id.cancel:
                if (listener != null) {
                    listener.cancel();
                }

                break;
        }
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

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public int dpTopx(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取当月的天数
     */
    public int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
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

    /**
     * 根据日期 找到对应日期的 星期
     */
    public String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }

    private OnSelectTimeListener listener;

    public void setOnSelectTimeListener(OnSelectTimeListener listener) {
        this.listener = listener;
    }

    public interface OnSelectTimeListener {
        void cancel();

        /**
         * @param year  年
         * @param month 月
         * @param day   日
         * @param past  和当前时间对比，时间是否已经过去，过去为 true
         */
        void confirm(String year, String month, String day, boolean past);
    }
}
