package zhuri.com.partybuilding.view.gradualchange;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.fragment.HomePageFragment;


/**
 * 支持渐变的 actionBar
 * Created by 晖仔(Milo) on 2016/12/28.
 * email:303767416@qq.com
 */

public final class TranslucentActionBar extends LinearLayout {

    private View layRoot;
    private View vStatusBar;
    public View layLeft;
    public View layRight;
    public TextView tvTitle;
    private TextView tvLeft;
    private TextView tvRight;
    private ImageView iconLeft;
    private ImageView iconRight;


    public TranslucentActionBar(Context context) {
        this(context, null);
    }

    public TranslucentActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TranslucentActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setOrientation(HORIZONTAL);
        View contentView = inflate(getContext(), R.layout.actionbar_trans, this);
        layRoot = contentView.findViewById(R.id.lay_transroot);
        vStatusBar = contentView.findViewById(R.id.v_statusbar);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_actionbar_title);
        layLeft = findViewById(R.id.lay_actionbar_left);
        layRight = findViewById(R.id.lay_actionbar_right);
        tvLeft = (TextView) contentView.findViewById(R.id.tv_actionbar_left);
        tvRight = (TextView) contentView.findViewById(R.id.tv_actionbar_right);
        iconLeft = contentView.findViewById(R.id.iv_actionbar_left);
        iconRight = contentView.findViewById(R.id.v_actionbar_right);
    }

    /**
     * 设置状态栏高度
     *
     * @param statusBarHeight
     */
    public void setStatusBarHeight(int statusBarHeight) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 以上
            Log.e("eeeeee版本", "111");
            ViewGroup.LayoutParams params = vStatusBar.getLayoutParams();
            params.height = statusBarHeight;
            vStatusBar.setLayoutParams(params);
        } else {
            //5.0 以下
            Log.e("eeeeee版本", "222");
        }

        Log.e("eeeeee版本", "" + Build.VERSION.SDK_INT);


    }

    /**
     * 设置是否需要渐变
     */
    public void setNeedTranslucent() {
        setNeedTranslucent(true, false);
    }

    /**
     * 设置是否需要渐变,并且隐藏标题
     *
     * @param translucent
     */
    public void setNeedTranslucent(boolean translucent, boolean titleInitVisibile) {
        if (translucent) {
            layRoot.setBackgroundDrawable(null);
        }
        if (!titleInitVisibile) {
            tvTitle.setVisibility(View.GONE);
            layLeft.setVisibility(View.GONE);
            layRight.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            layLeft.setVisibility(View.VISIBLE);
            layRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题
     *
     * @param strTitle
     */
    public void setTitle(String strTitle) {
        if (!TextUtils.isEmpty(strTitle)) {
            tvTitle.setText(strTitle);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置数据
     *
     * @param strTitle
     * @param resIdLeft
     * @param strLeft
     * @param resIdRight
     * @param strRight
     * @param listener
     */
    public void setData(String strTitle, int titleColor, int resIdLeft, String strLeft, int resIdRight, String strRight, final ActionBarClickListener listener) {
        if (!TextUtils.isEmpty(strTitle)) {
            tvTitle.setText(strTitle);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        if (titleColor != 0) {
            tvTitle.setTextColor(getResources().getColor(titleColor));
        }
        if (!TextUtils.isEmpty(strLeft)) {
            tvLeft.setText(strLeft);
            tvLeft.setVisibility(View.VISIBLE);
        } else {
            tvLeft.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(strRight)) {
            tvRight.setText(strRight);
            tvRight.setVisibility(View.VISIBLE);
        } else {
            tvRight.setVisibility(View.GONE);
        }

        if (resIdLeft == 0) {
            iconLeft.setVisibility(View.GONE);
        } else {
            iconLeft.setImageDrawable(getResources().getDrawable(resIdLeft));
            iconLeft.setVisibility(View.VISIBLE);
        }

        if (resIdRight == 0) {
            iconRight.setVisibility(View.GONE);
        } else {
            iconRight.setImageDrawable(getResources().getDrawable(resIdRight));
            iconRight.setVisibility(View.VISIBLE);
        }

        if (listener != null) {

            layLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLeftClick();
                }
            });
            layRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRightClick();
                }
            });
        }
    }


    public interface ActionBarClickListener {

        void onLeftClick();

        void onRightClick();

    }

}
