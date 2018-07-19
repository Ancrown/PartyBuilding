package zhuri.com.partybuilding.activity;


import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseFragmentActivity;
import zhuri.com.partybuilding.fragment.ActivitiesFragment;
import zhuri.com.partybuilding.fragment.ConsultationFragment;
import zhuri.com.partybuilding.fragment.HomePageFragment;
import zhuri.com.partybuilding.fragment.MyFragment;
import zhuri.com.partybuilding.fragment.StudyFragment;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.permission.PermissionManager;


/**
 * Author  ：liu
 * Function: 导航
 */
public class NavigationActivity extends
        BaseFragmentActivity implements
        RadioGroup.OnCheckedChangeListener {


    //定位返回requestCode
    private final int LOCATION_PERMISSION = 2000;

    @BindView(R.id.navigation_hall)
    RadioButton navigationHall;
    @BindView(R.id.navigation_consultation)
    RadioButton navigationConsultation;
    @BindView(R.id.navigation_activities)
    RadioButton navigationActivities;
    @BindView(R.id.navigation_study)
    RadioButton navigationStudy;
    @BindView(R.id.navigation_my)
    RadioButton navigationMy;
    @BindView(R.id.navigation_rg)
    RadioGroup navigationRg;


    private Fragment content;
    /**
     * 首页
     */
    private HomePageFragment homePage;
    /**
     * 咨询
     */
    private ConsultationFragment consultation;
    /**
     * 活动
     */
    private ActivitiesFragment activities;
    /**
     * 学习
     */
    private StudyFragment study;
    /**
     * 我的
     */
    private MyFragment my;

    private int currentTabIndex;


    @Override
    protected void initData() {
    }


    @Override
    protected void initView() {

        homePage = new HomePageFragment();
        consultation = new ConsultationFragment();
        activities = new ActivitiesFragment();
        study = new StudyFragment();
        my = new MyFragment();


        replaceFragment(content, homePage);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作。
        Log.e("权限返回", requestCode + "");
        if (grantResults.length == 0) return;
        int count = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                count++;
            }
        }
        if (count == grantResults.length) {
            //确认权限
            if (requestCode == LOCATION_PERMISSION) {


            }
        } else {
            //没有确认
            if (requestCode == LOCATION_PERMISSION) {

            }
        }
    }

    @Override
    protected void initListener() {
        navigationRg.setOnCheckedChangeListener(this);

    }


    @Override
    protected int getLayout() {
        return R.layout.aty_navigation;
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.navigation_hall:
                currentTabIndex = 0;
                replaceFragment(content, homePage);
                break;
            case R.id.navigation_consultation:
                currentTabIndex = 1;
                replaceFragment(content, consultation);
                break;
            case R.id.navigation_activities:
                currentTabIndex = 2;
                replaceFragment(content, activities);
                break;
            case R.id.navigation_study:
                currentTabIndex = 3;
                replaceFragment(content, study);
                break;
            case R.id.navigation_my:
                currentTabIndex = 4;
                replaceFragment(content, my);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ToolUtils.isNotificationEnabled(NavigationActivity.this)) {
            PermissionManager.showDialog(NavigationActivity.this,
                    "通知管理权限被禁止，通知功能无法正常使用。是否开启该权限？(步骤：通知管理->'勾选'允许通知)");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 隐藏fragment和添加fragment
     *
     * @param from
     * @param to
     */
    private void replaceFragment(Fragment from, Fragment to) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (from == null) {
            ft.add(R.id.navigation_fl, to);
            ft.commit();
            content = to;
            return;
        }
        if (to.isHidden()) {
            ft.hide(from).show(to);
        } else {
            ft.hide(from).add(R.id.navigation_fl, to);
        }
        ft.commit();
        content = to;
    }

}
