package zhuri.com.partybuilding.fragment.study;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.adapter.StudyAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.bean.StudyBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.twinklingrefreshlayout.header.progresslayout.ProgressLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.view.BroadcastView;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:十九大报告
 */

public class StudyOneFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;

    private StudyAdapter adapter;
    private List<StudyBean> itemList;

    @Override
    public int getLayoutId() {
        return R.layout.fra_study_one;
    }

    @Override
    public void initView() {
        setupListView();
    }

    @Override
    public void refreshData() {

    }

    private void setupListView() {


        //添加位置固定的头部
        // refreshLayout.addFixedExHeader(exHeader);

        //添加位置跟listview滑动的头部
        // recycler.addHeaderView(exHeader);

        refreshLayout.setOverScrollRefreshShow(false);

        //设置不允许上拉
        //refreshLayout.setEnableLoadmore(false);

        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshLayout.setFloatRefresh(true);

        //listview 效果
        LinearLayoutManager lmr = new LinearLayoutManager(getActivity());
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recycler.setLayoutManager(lmr);
        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
        adapter = new StudyAdapter(getActivity());
        recycler.setAdapter(adapter);

        getdata();


        //下拉上拉
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "下拉");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "上拉");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);


            }

        });

    }

    //数据
    public void getdata() {


        //item数据
        itemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            itemList.add(new StudyBean(i + "",
                    "咖喱牛唠嗑呢来拿来呢来看你可怜你看了那看来你",
                    "爱看美女反馈滥发开朗了半年可填报情况不太认可清洁不发动机卡布法规尽快把看个几把开个本会计报告卡不到关键看吧again",
                    "2018-6-5 15:48",
                    "9991",
                    "20",
                    "" + i % 2,
                    "100"));
        }
        adapter.setDataList(itemList);
    }
}