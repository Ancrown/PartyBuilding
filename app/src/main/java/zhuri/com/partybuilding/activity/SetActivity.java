package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.dialog.UpgradeDialog;
import zhuri.com.partybuilding.dialog.load.TipLoadDialog;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.CacheDataManager;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.permission.PermissionManager;
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
    @BindView(R.id.my_set_examine_label)
    TextView mySetExamineLabel;

    //服务器apk path,这里放了百度云盘的apk 作为测试
    String apkPath = "http://issuecdn.baidupcs.com/issue/netdisk/apk/BaiduNetdisk_7.15.1.apk";

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("设置", 0, R.drawable.back, null, 0, null, this);
        mySetExamineLabel.setVisibility(View.VISIBLE);

        mySetVersionText.setText("当前版本：v" + ToolUtils.getVersion(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mySetClearText.setText("清除缓存：" + CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                if (PermissionManager.permissionApplication(this, PermissionManager.Write(), PermissionManager.PERMISSION)) {
                    updateAPP();
                }
                break;
            case R.id.my_set_sign_out:
                finishAllActivity();
                SharedPreferencesUtils.clear(this, StaticVariables.USER_ID);
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    public void updateAPP() {
        UpgradeDialog uDialog = new UpgradeDialog(this, R.style.custom_dialog);
        uDialog.setTitleString("沈阳党建 v1.0.2 来袭");
        uDialog.setContentString(AppUtils.getString(R.string.updatecontent));
        uDialog.serverVersionName("1.0.2");
        uDialog.setApkPath(apkPath);
        uDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0) return;
        int count = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                count++;
            }
        }

        if (count == grantResults.length) {
            if (requestCode == PermissionManager.PERMISSION) {
                updateAPP();
            }
        } else {
            if (requestCode == PermissionManager.PERMISSION) {
                PermissionManager.showDialog(this,
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'储存空间 )");
            }
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
        public void handleMessage(Message msg) {
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
