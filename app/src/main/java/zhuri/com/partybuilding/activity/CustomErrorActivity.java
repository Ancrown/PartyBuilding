package zhuri.com.partybuilding.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;


/**
 * 创建人: Administrator
 * 创建时间: 2018/7/2
 * 描述: 程序崩溃后显示的崩溃页面
 */

public class CustomErrorActivity extends BaseActivity {
    @BindView(R.id.error_text)
    TextView errorText;
    @BindView(R.id.restart)
    TextView restart;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.error_edit_text)
    EditText errorEditText;

    CaocConfig config;//配置对象


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("崩溃", 0, 0, null, 0, null, null);

        //可以获取到的四个信息:
        String stackString = CustomActivityOnCrash.getStackTraceFromIntent(getIntent());//将堆栈跟踪作为字符串获取。
        String logString = CustomActivityOnCrash.getActivityLogFromIntent(getIntent()); //获取错误报告的Log信息
        String allString = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());// 获取所有的信息
        config = CustomActivityOnCrash.getConfigFromIntent(getIntent());//获得配置信息,比如设置的程序崩溃显示的页面和重新启动显示的页面等等信息

        errorText.setText("*********************\n" + allString + "\n*********************");


    }

    @Override
    protected int getLayout() {
        return R.layout.aty_error;
    }


    @OnClick({R.id.restart, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restart:
                CustomActivityOnCrash.restartApplication(CustomErrorActivity.this, config);
                break;
            case R.id.close:
                CustomActivityOnCrash.closeApplication(CustomErrorActivity.this, config);
                break;
        }
    }

}
