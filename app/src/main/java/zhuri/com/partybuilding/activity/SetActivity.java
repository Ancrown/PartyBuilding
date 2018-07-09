package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.dialog.load.TipLoadDialog;
import zhuri.com.partybuilding.util.CacheDataManager;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/7
 * 描述:
 */

public class SetActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.my_set_password)
    RelativeLayout mySetPassword;
    @BindView(R.id.my_set_clear)
    RelativeLayout mySetClear;
    @BindView(R.id.my_set_clear_text)
    TextView mySetClearText;
    @BindView(R.id.my_set_version_text)
    TextView mySetVersionText;
    @BindView(R.id.my_set_version)
    RelativeLayout mySetVersion;
    @BindView(R.id.my_set_examine_text)
    TextView mySetExamineText;
    @BindView(R.id.my_set_examine)
    RelativeLayout mySetExamine;
    @BindView(R.id.my_set_sign_out)
    TextView mySetSignOut;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("设置", 0, R.drawable.back, null, 0, null, this);

        try {
            mySetClearText.setText("清除缓存：" + CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mySetVersionText.setText("当前版本："+ToolUtils.getVersion(this));
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_set;
    }


    @OnClick({R.id.my_set_password, R.id.my_set_clear, R.id.my_set_version, R.id.my_set_examine, R.id.my_set_sign_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_set_password:
                break;
            case R.id.my_set_clear:
                try {
                    if (CacheDataManager.getTotalCacheSize(this).equals("0.0KB")) {
                        ToolUtils.showToast(this, "已经清除到最小了");
                    } else {
                        dialog = new TipLoadDialog(SetActivity.this).setNoShadowTheme().setMsgAndType("正在清理", TipLoadDialog.ICON_TYPE_LOADING2);
                        dialog.show();
                        //清除缓存
                        new Thread(new clearCache()).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.my_set_version:
                break;
            case R.id.my_set_examine:
                break;
            case R.id.my_set_sign_out:
                finishAllActivity();
                SharedPreferencesUtils.clear(this, StaticVariables.USER_ID);
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }

    //加载框
    public static TipLoadDialog dialog;

    class clearCache implements Runnable {
        @Override
        public void run() {
            try {

                CacheDataManager.clearAllCache(SetActivity.this);
                Thread.sleep(3000);
                if (CacheDataManager.getTotalCacheSize(SetActivity.this).startsWith("0")) {
                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    dialog.dismiss();
                    ToolUtils.showToast(SetActivity.this, "清理完成");
                    try {
                        mySetClearText.setText("清除缓存：" + CacheDataManager.getTotalCacheSize(SetActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };
}
