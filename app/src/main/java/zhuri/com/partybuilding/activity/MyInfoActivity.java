package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.date.SelectDateDialog;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;
import zhuri.com.partybuilding.util.permission.PermissionManager;
import zhuri.com.partybuilding.util.popupwindow.CameraUtil;
import zhuri.com.partybuilding.util.popupwindow.photoview.photopicker.PhotoPickerActivity;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/28
 * 描述:
 */

public class MyInfoActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.my_info_img)
    ImageView myInfoImg;
    @BindView(R.id.my_info_name_edit)
    EditText myInfoNameEdit;
    @BindView(R.id.my_info_age_textview)
    TextView myInfoAgeTextview;
    @BindView(R.id.my_info_sex_textview)
    TextView myInfoSexTextview;
    @BindView(R.id.my_info_phone_textview)
    EditText myInfoPhoneTextview;
    @BindView(R.id.my_info_phone_rl)
    RelativeLayout myInfoPhoneRl;
    @BindView(R.id.my_info_birthday_textview)
    TextView myInfoBirthdayTextview;
    @BindView(R.id.my_info_party_textview)
    TextView myInfoPartyTextview;
    @BindView(R.id.my_info_party_rl)
    RelativeLayout myInfoPartyRl;
    @BindView(R.id.my_info_preservation)
    TextView myInfoPreservation;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("个人信息", 0, R.drawable.back, null, 0, null, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_my_info;
    }

    @OnClick({R.id.my_info_img, R.id.my_info_name_rl, R.id.my_info_sex_rl, R.id.my_info_phone_rl, R.id.my_info_birthday_rl, R.id.my_info_party_rl, R.id.my_info_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_info_img:
                if (PermissionManager.permissionApplication(this, PermissionManager.ImgPermission(), 1000)) {
                    CameraUtil.getNewsPopupWindow( this, -1);
                }
                break;
            case R.id.my_info_name_rl:
                break;
            case R.id.my_info_sex_rl:
                break;
            case R.id.my_info_phone_rl:
                break;
            case R.id.my_info_birthday_rl:
                SelectDateDialog dialog = new SelectDateDialog(this, R.style.custom_dialog);
                dialog.setTitleStr("生日");
                dialog.show();
                dialog.setOnSelectTimeListener(new SelectDateDialog.OnSelectTimeListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String year, String month, String day, boolean past) {
                        if (past) {
                            myInfoBirthdayTextview.setText(year + "-" + month + "-" + day);

                            try {
                                Log.e("eeeee", "age: " + TimeUtil.getAge(TimeUtil.parse(year + "-" + month + "-" + day)));
                                myInfoAgeTextview.setText(TimeUtil.getAge(TimeUtil.parse(year + "-" + month + "-" + day)) + "");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        } else {
                            ToolUtils.showToast(MyInfoActivity.this, "您选择的有误！");
                        }

                    }
                });
                break;
            case R.id.my_info_party_rl:
                SelectDateDialog dialog1 = new SelectDateDialog(this, R.style.custom_dialog);
                dialog1.setTitleStr("入党时间");
                dialog1.show();
                dialog1.setOnSelectTimeListener(new SelectDateDialog.OnSelectTimeListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm(String year, String month, String day, boolean past) {
                        if (past) {
                            myInfoPartyTextview.setText(year + "-" + month + "-" + day);

                        } else {
                            ToolUtils.showToast(MyInfoActivity.this, "您选择的有误！");
                        }

                    }
                });
                break;
            case R.id.my_info_preservation:
                break;
        }
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
            if (requestCode == 1000) {
                CameraUtil.getNewsPopupWindow( this, -1);
            }
        } else {
            if (requestCode == 1000) {
                PermissionManager.showDialog(this,
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'储存空间，相机)");
            }
        }
    }

    /**
     * 回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ssssssssssss", requestCode + "  +  " + resultCode);

        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                GlideUtils.LoadCircleImage(this, data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0), myInfoImg);
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
}
