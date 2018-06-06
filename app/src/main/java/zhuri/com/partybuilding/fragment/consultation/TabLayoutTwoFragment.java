package zhuri.com.partybuilding.fragment.consultation;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.twinklingrefreshlayout.header.progresslayout.ProgressLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.view.BroadcastView;

/**
 * 党建要闻
 */

public class TabLayoutTwoFragment extends BaseFragment {

    //  @BindView(R.id.fra_consultation_one_bro)
    BroadcastView bView;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;
    private List<String> imageResIds;
    private List<String> contentDescs;

    private HomePageAdapter adapter;
    private List<HomePageItemBean> itemList;


    @Override
    public int getLayoutId() {
        return R.layout.fra_one;
    }

    @Override
    public void initView() {

        init();
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void init() {
        setupListView();
    }

    private void setupListView() {

        ProgressLayout headerView = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(headerView);
        View exHeader = View.inflate(getActivity(), R.layout.consultation_head, null);
        bView = exHeader.findViewById(R.id.fra_consultation_bro);


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
        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px( 5)));
        adapter = new HomePageAdapter(true,getActivity());
        recycler.setAdapter(adapter);
        adapter.setHeadHolder(exHeader);
        getdata();

        //轮播图点击
        bView.setOnImgClick(new BroadcastView.OnImgClick() {
            @Override
            public void onItme(String id) {
                Log.e("eeeee", "position:" + id);
            }
        });


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
        //轮播图
        imageResIds = new ArrayList<>();
        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=f92d19cc97cb850b93d622c248a9327a&imgtype=0&src=http%3A%2F%2Fi2.qhmsg.com%2Ft01e909ef83b1591352.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=ae1442d7c0da3969ab98dfcdf8bf2a31&imgtype=0&src=http%3A%2F%2Fp2.qhmsg.com%2Ft01fcf832a92d4bd986.jpg");

        contentDescs = new ArrayList<>();
        contentDescs.add("巩俐不低俗，我就不能低俗");
        contentDescs.add("乐视网TV版大派送");
        contentDescs.add("热血屌丝的反杀");
        bView.text = "A";
        bView.setData(imageResIds, contentDescs);


        //item数据
        itemList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            itemList.add(new HomePageItemBean(i + ""
                    , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_" +
                    "10000&sec=1527142406419&di=bcdf4024750e8647e876f6df31334935&imgt" +
                    "ype=0&src=http%3A%2F%2Fjiweixin168.com%2FUploads%2F2015%2F12%2F17%" +
                    "2F56725e9c1ebac.png"
                    , "党中央", "习近平书记说:XXXXXXX"
                    , "2017年12月13日，中共中央总书记、国家主席、中央军委主席习近平到第71集团军视察。这是习近平同“王杰班”战士合影。"
                    , "2018-5-24 11:27", "100", "999+"));
        }
        adapter.setDataList(itemList);

    }

    @Override
    public void onPause() {
        super.onPause();
        bView.setRunning(false);
    }

}
