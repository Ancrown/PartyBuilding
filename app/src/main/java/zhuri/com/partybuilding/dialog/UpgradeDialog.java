package zhuri.com.partybuilding.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.interf.Callback;
import zhuri.com.partybuilding.util.updateapp.DownloadAppUtils;
import zhuri.com.partybuilding.util.ToolUtils;


/**
 * 创建人: Administrator
 * 创建时间: 2018/7/10
 * 描述: 更新APP
 */

public class UpgradeDialog extends Dialog {
    //更新的版本
    private String serverVersionName;
    private String titleString;
    private String contentString;
    private String apkPath;
    public static boolean showNotification = true;//是否显示下载进度到通知栏，默认为true
    public static boolean needFitAndroidN = true; //提供给 整个工程不需要适配到7.0的项目 置为false

    private TextView up;
    private TextView title;
    private TextView content;


    public UpgradeDialog serverVersionName(String serverVersionName) {
        this.serverVersionName = serverVersionName;
        return this;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public String getTitleString() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public UpgradeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);


    }

    public UpgradeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_upgrade);
        up = (TextView) findViewById(R.id.upgrade_up);
        title = (TextView) findViewById(R.id.upgrade_title);
        content = (TextView) findViewById(R.id.upgrade_content);
        title.setText(titleString);
        content.setText(contentString);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (PermissionManager.permissionApplication(activity, PermissionManager.Write(), PermissionManager.PERMISSION)) {
//                    updateApp();
//                }
                updateApp();
            }
        });
    }


    /**
     * 0代表相等，1代表version1大于version2，-1代表version1小于version2
     */
    private void updateApp() {

        switch (ToolUtils.compareVersion(serverVersionName, ToolUtils.getVersion(getContext()))) {
            case 0:
                ToolUtils.showToast(getContext(), "当前已是最新版本");
                break;
            case 1:
                if (ToolUtils.isWifiConnected(getContext())) {
                    DownloadAppUtils.download(getContext(), apkPath, serverVersionName);
                    dismiss();
                } else {
                    new ConfirmDialog(getContext(), new Callback() {
                        @Override
                        public void callback(int position) {
                            dismiss();
                            if (position == 1) {
                                DownloadAppUtils.download(getContext(), apkPath, serverVersionName);
                            } else {
                            }
                        }
                    }).setContent("目前手机不是WiFi状态\n确认是否继续下载更新？").show();
                }

                break;
            case -1:
                ToolUtils.showToast(getContext(), "当前已是最新版本");
                break;
        }

    }
}
