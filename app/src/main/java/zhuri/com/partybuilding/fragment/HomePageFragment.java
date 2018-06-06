package zhuri.com.partybuilding.fragment;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
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
import zhuri.com.partybuilding.activity.ExaminationActivity;
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
 * 首页
 */

public class HomePageFragment extends BaseFragment {
    //轮播图
    // @BindView(R.id.fra_home_bro)
    private BroadcastView bView;
    //轮播文字
    //  @BindView(R.id.fra_home_marqueeview)
    private MarqueeView marqueeview;
    //社区活动
    private TextView communityAactivities;
    //微志愿
    private TextView vVolunteer;
    //微心愿
    private TextView vWish;
    //在线答题
    private TextView onlineAnswer;
    //两学一做
    private TextView twoOne;
    //十九大精神
    private TextView nineteen;
    //党务工作指导
    private TextView workGuidance;
    //更多
    private TextView more;


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;


    private HomePageAdapter adapter;
    private List<HomePageItemBean> itemList;

    private List<String> imageResIds;
    private List<String> contentDescs;
    private List<String> info;

    @Override
    public int getLayoutId() {
        return R.layout.fra_homepage;
    }

    @Override
    public void initView() {

        //设置标题
        getTitleView().setData(getResources().getString(R.string.app_name), 0, R.drawable.dian_dian_dian, null, R.drawable.plus, null, null);
        init();
    }

    @Override
    public void refreshData() {
        //在线答题
        onlineAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExaminationActivity.class));
            }
        });
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
        View exHeader = View.inflate(getActivity(), R.layout.head_homepage, null);
        bView = exHeader.findViewById(R.id.fra_home_bro);
        marqueeview = exHeader.findViewById(R.id.fra_home_marqueeview);
        communityAactivities = exHeader.findViewById(R.id.fra_home_community_activities);
        vVolunteer = exHeader.findViewById(R.id.fra_home_v_volunteer);
        vWish = exHeader.findViewById(R.id.fra_home_v_wish);
        onlineAnswer = exHeader.findViewById(R.id.fra_home_online_answer);
        twoOne = exHeader.findViewById(R.id.fra_home_two_one);
        nineteen = exHeader.findViewById(R.id.fra_home_nineteen);
        workGuidance = exHeader.findViewById(R.id.fra_home_work_guidance);
        more = exHeader.findViewById(R.id.fra_home_more);


        //添加位置固定的头部
        // refreshLayout.addFixedExHeader(exHeader);

        //添加位置跟listview滑动的头部
        // recycler.addHeaderView(exHeader);

        refreshLayout.setOverScrollRefreshShow(false);

        //设置不允许上拉
        refreshLayout.setEnableLoadmore(false);

        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshLayout.setFloatRefresh(true);

        //listview 效果
        LinearLayoutManager lmr = new LinearLayoutManager(getActivity());
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recycler.setLayoutManager(lmr);
       recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
        adapter = new HomePageAdapter(getActivity());
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

        //轮播文字点击
        marqueeview.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getActivity(), String.valueOf(marqueeview.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
                Log.e("eeeeee", "滚动:" + textView.getText().toString());
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

//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                Log.e("eeeee", "ListView" + "上拉");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.finishLoadmore();
//                    }
//                }, 1000);
//
//
//            }

        });
//        //进入到界面的时候主动调用下刷新  下拉
//        refreshLayout.startRefresh();
//        //进入到界面的时候主动调用下刷新  加载更多
//        refreshLayout.startLoadMore();
    }

    //数据
    public void getdata() {
        //轮播图
        imageResIds = new ArrayList<>();
        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925682&di=d12775547359b0c44d837f51f02e6518&imgtype=0&src=http%3A%2F%2Fu5.qiyipic.com%2Fimage%2F20170815%2F9c%2F5d%2Fuv_3066296689_m_601_480_270.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=51e62c9a9f63768cbb089f90668a17a2&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201601%2F06%2F20160106173647_yiXdf.jpeg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=f92d19cc97cb850b93d622c248a9327a&imgtype=0&src=http%3A%2F%2Fi2.qhmsg.com%2Ft01e909ef83b1591352.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=ae1442d7c0da3969ab98dfcdf8bf2a31&imgtype=0&src=http%3A%2F%2Fp2.qhmsg.com%2Ft01fcf832a92d4bd986.jpg");

        contentDescs = new ArrayList<>();
        contentDescs.add("巩俐不低俗，我就不能低俗");
        contentDescs.add("扑树又回来啦！再唱经典老歌引万人大合唱");
        contentDescs.add("揭秘北京电影如何升级");
        contentDescs.add("乐视网TV版大派送");
        contentDescs.add("热血屌丝的反杀");
        bView.text = "A";
        bView.setData(imageResIds, contentDescs);

        //轮播文字
        info = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            info.add("这里是热门头条" + i);
        }

        marqueeview.startWithList(info);

        //item数据
        itemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
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
    public void onStart() {
        super.onStart();
        marqueeview.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeview.stopFlipping();
    }

    @Override
    public void onPause() {
        super.onPause();
        bView.setRunning(false);
    }


}
