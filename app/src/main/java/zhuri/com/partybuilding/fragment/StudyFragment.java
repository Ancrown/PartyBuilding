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
import zhuri.com.partybuilding.fragment.study.StudyFourFragment;
import zhuri.com.partybuilding.fragment.study.StudyOneFragment;
import zhuri.com.partybuilding.fragment.study.StudyThreeFragment;
import zhuri.com.partybuilding.fragment.study.StudyTwoFragment;
import zhuri.com.partybuilding.util.AppUtils;

/**
 * 学习
 */

public class StudyFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fra_study;
    }

    @Override
    public void initView() {
        getTitleView().setData(getResources().getString(R.string.study), 0, 0, null, 0, null, null);
        initViewPager();
    }

    @Override
    public void refreshData() {

    }

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
        fList.add(new StudyOneFragment());
        fList.add(new StudyTwoFragment());
        fList.add(new StudyThreeFragment());
        fList.add(new StudyFourFragment());

        tList = new ArrayList<>();
        tList.add(AppUtils.getString(R.string.nineteen));
        tList.add(AppUtils.getString(R.string.two_one));
        tList.add("党务工作");
        tList.add("考试系统");

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



