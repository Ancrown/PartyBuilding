package zhuri.com.partybuilding.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.FragmentAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.base.BaseFragmentActivity;
import zhuri.com.partybuilding.fragment.activities.ActivitiesOneFragment;
import zhuri.com.partybuilding.fragment.activities.ActivitiesThreeFragment;
import zhuri.com.partybuilding.fragment.activities.ActivitiesTwoFragment;
import zhuri.com.partybuilding.fragment.activities.RecordActivitiesOneFragment;
import zhuri.com.partybuilding.fragment.activities.RecordActivitiesThreeFragment;
import zhuri.com.partybuilding.fragment.activities.RecordActivitiesTwoFragment;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/9
 * 描述: 活动记录
 */

public class RecordActivitiesActivity extends BaseFragmentActivity implements TranslucentActionBar.ActionBarClickListener {
    private List<Fragment> fList;
    private List<String> tList;
    private FragmentAdapter adapter;


    @BindView(R.id.consultation_tal)
    TabLayout mTabLayout;
    @BindView(R.id.consultation_viewpager)
    ViewPager mViewPager;


    public void initViewPager() {

        fList = new ArrayList<>();
        // 装填
        fList.add(new RecordActivitiesOneFragment());
        fList.add(new RecordActivitiesTwoFragment());
        fList.add(new RecordActivitiesThreeFragment());

        tList = new ArrayList<>();
        tList.add(AppUtils.getString(R.string.community_activities));
        tList.add(AppUtils.getString(R.string.v_volunteer));
        tList.add(AppUtils.getString(R.string.v_wish));

        // 给ViewPager设置适配器
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setfList(fList);
        adapter.settList(tList);
        mViewPager.setAdapter(adapter);

        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("活动记录", 0, R.drawable.back, null, 0, null, this);
        initViewPager();
    }

    @Override
    protected int getLayout() {
        return R.layout.fra_activities;
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
