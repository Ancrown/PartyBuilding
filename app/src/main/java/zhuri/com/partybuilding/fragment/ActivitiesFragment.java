package zhuri.com.partybuilding.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.FragmentAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.fragment.activities.ActivitiesOneFragment;
import zhuri.com.partybuilding.fragment.activities.ActivitiesThreeFragment;
import zhuri.com.partybuilding.fragment.activities.ActivitiesTwoFragment;
import zhuri.com.partybuilding.fragment.consultation.TabLayoutOneFragment;
import zhuri.com.partybuilding.fragment.consultation.TabLayoutThreeFragment;
import zhuri.com.partybuilding.fragment.consultation.TabLayoutTwoFragment;
import zhuri.com.partybuilding.util.AppUtils;

/**
 * Created by Administrator on 2018/5/17.
 */

public class ActivitiesFragment extends BaseFragment {
    private List<Fragment> fList;
    private List<String> tList;
    private FragmentAdapter adapter;


    @BindView(R.id.consultation_tal)
    TabLayout mTabLayout;
    @BindView(R.id.consultation_viewpager)
    ViewPager mViewPager;


    @Override
    public int getLayoutId() {
        return R.layout.fra_activities;
    }

    @Override
    public void initView() {
        getTitleView().setData(getResources().getString(R.string.activities), 0, 0, null, 0, null, null);
        initViewPager();
    }

    @Override
    public void refreshData() {

    }

    public void initViewPager() {

        fList = new ArrayList<>();
        // 装填
        fList.add(new ActivitiesOneFragment());
        fList.add(new ActivitiesTwoFragment());
        fList.add(new ActivitiesThreeFragment());

        tList = new ArrayList<>();
        tList.add(AppUtils.getString(R.string.community_activities));
        tList.add(AppUtils.getString(R.string.v_volunteer));
        tList.add(AppUtils.getString(R.string.v_wish));

        // 给ViewPager设置适配器
        adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.setfList(fList);
        adapter.settList(tList);
        mViewPager.setAdapter(adapter);

        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);

    }

}


