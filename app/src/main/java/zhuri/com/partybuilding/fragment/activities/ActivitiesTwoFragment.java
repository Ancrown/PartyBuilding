package zhuri.com.partybuilding.fragment.activities;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ActivitiesAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述: 微志愿
 */

public class ActivitiesTwoFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;

    private ActivitiesAdapter adapter;
    private List<ActivitiesItemBean> itemList;

    @Override
    public int getLayoutId() {
        return R.layout.fra_activities_one;
    }

    @Override
    public void initView() {
        setupListView();
    }

    @Override
    public void refreshData() {

    }

    private void setupListView() {

        // ProgressLayout headerView = new ProgressLayout(getActivity());
        //   refreshLayout.setHeaderView(headerView);
//        View exHeader = View.inflate(getActivity(), R.layout.consultation_head, null);
//        bView = exHeader.findViewById(R.id.fra_consultation_bro);


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
        adapter = new ActivitiesAdapter(getActivity());
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

    public void getdata() {
        itemList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            itemList.add(new ActivitiesItemBean(
                    i + ""
                    , "https://gss0.bdstatic.com/70cFfyinKgQIm2_p8IuM_a/daf/pic/item/30adcbef76094b36ccec2775afcc7cd98d109d2f.jpg"
                    , "间隔我哦阿尔及哦啊好你欧尼奥尔太糊弄 "
                    , i + ""
                    , "辽宁省沈阳市和平区"
                    , "1000"
                    , "99999999"
                    , "2018-6-5~2018-7-1"
                    , (i % 2 == 0 ? 0 + "." + ((int) (Math.random() * 10) % 2) : 1) + ""));
            Log.e("eeeeee", "TYPE    " + itemList.get(i).getType());
        }
        adapter.setDataList(itemList);
    }

}
