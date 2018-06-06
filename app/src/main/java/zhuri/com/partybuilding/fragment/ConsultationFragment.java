package zhuri.com.partybuilding.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.FragmentAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.fragment.consultation.TabLayoutOneFragment;
import zhuri.com.partybuilding.fragment.consultation.TabLayoutThreeFragment;
import zhuri.com.partybuilding.fragment.consultation.TabLayoutTwoFragment;

/**
 * 咨询
 */

public class ConsultationFragment extends BaseFragment {


    private List<Fragment> fList;
    private List<String> tList;
    private FragmentAdapter adapter;


    @BindView(R.id.consultation_tal)
    TabLayout mTabLayout;
    @BindView(R.id.consultation_viewpager)
    ViewPager mViewPager;


    @Override
    public int getLayoutId() {
        return R.layout.fra_consultation;
    }

    @Override
    public void initView() {
        getTitleView().setData(getResources().getString(R.string.pb_consultation), 0, 0, null, 0, null, null);
        initViewPager();
//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab == mTabLayout.getTabAt(0)) {
//                    mTabLayout.getTabAt(0).setText("· "+tList.get(0));
//                    mViewPager.setCurrentItem(0);
//                } else if (tab == mTabLayout.getTabAt(1)) {
//                    mTabLayout.getTabAt(1).setText("· "+tList.get(1));
//                    mViewPager.setCurrentItem(1);
//                } else if (tab == mTabLayout.getTabAt(2)) {
//                    mTabLayout.getTabAt(2).setText("· "+tList.get(2));
//                    mViewPager.setCurrentItem(2);
//                }
//
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                if (tab == mTabLayout.getTabAt(0)) {
//                    mTabLayout.getTabAt(0).setText(tList.get(0));
//                    mViewPager.setCurrentItem(0);
//                } else if (tab == mTabLayout.getTabAt(1)) {
//                    mTabLayout.getTabAt(1).setText(tList.get(1));
//                    mViewPager.setCurrentItem(1);
//                } else if (tab == mTabLayout.getTabAt(2)) {
//                    mTabLayout.getTabAt(2).setText(tList.get(2));
//                    mViewPager.setCurrentItem(2);
//                }
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public void refreshData() {

    }

    public void initViewPager() {

        fList = new ArrayList<>();
        // 装填
        fList.add(new TabLayoutOneFragment());
        fList.add(new TabLayoutThreeFragment());
        fList.add(new TabLayoutTwoFragment());

        tList = new ArrayList<>();
        tList.add("党建要闻");
        tList.add("不忘初心");
        tList.add("项目为王");

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
