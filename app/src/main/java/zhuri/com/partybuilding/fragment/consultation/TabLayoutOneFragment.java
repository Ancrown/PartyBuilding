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

import com.squareup.okhttp.Request;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.ConsultationEntity;
import zhuri.com.partybuilding.entity.HomeEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.twinklingrefreshlayout.header.progresslayout.ProgressLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.bannerview.BannerItem;
import zhuri.com.partybuilding.view.bannerview.BannerView;
import zhuri.com.partybuilding.view.bannerview.BannerViewFactory;

/**
 * 党建要闻
 */

public class TabLayoutOneFragment extends BaseRecyclerFragment {

    private BannerView bView;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;

    //轮播
    private BannerItem bannerItem;
    private List<BannerItem> brolist=new ArrayList<>();
    private BannerViewFactory bannerViewFactory = new BannerViewFactory();


    private HomePageAdapter adapter;
    private List<HomePageItemBean> itemList;


    @Override
    public int getLayoutId() {
        return R.layout.base_fresh_recy;
    }

    @Override
    public void initView() {
        super.initView();
        setupListView();
    }

    @Override
    public void refreshData() {
        //轮播图点击
        bannerViewFactory.setOnClikImg(new BannerViewFactory.OnClikImg() {
            @Override
            public void onItemImg(int i) {
                Log.e("eeeee", "position:" + brolist.get(i).getId());
            }
        });


        //下拉上拉
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = 1;
                getEntity("Refresh");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                page++;
                getEntity("LoadMore");


            }


        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void setupListView() {

        ProgressLayout headerView = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(headerView);
        View exHeader = View.inflate(getActivity(), R.layout.consultation_head, null);
        bView = exHeader.findViewById(R.id.fra_consultation_bro);


        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new HomePageAdapter(true, getActivity(),"新闻要闻");
        recycler.setAdapter(adapter);
        adapter.setHeadHolder(exHeader);
        //初始化数据对象
        itemList = new ArrayList<>();
        // getdata();
        getEntity(null);

    }

    //数据
    public void getdata() {
        brolist = new ArrayList<>();
        bannerItem = new BannerItem();
        bannerItem.setId("101");
        bannerItem.setTitle("独家视频：新时代来啦！");
        bannerItem.setImage("http://cpc.people.com.cn/NMediaFile/2017/1113/MAIN201711131714403760613030737.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("102");
        bannerItem.setTitle("九个字带您感知十九大报告的民生温度");
        bannerItem.setImage("http://cpc.people.com.cn/NMediaFile/2017/1218/MAIN201712181515344682939898196.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("103");
        bannerItem.setTitle("十九大报告的十个为什么");
        bannerItem.setImage("http://cpc.people.com.cn/NMediaFile/2018/0124/MAIN201801240917552212259573131.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("104");
        bannerItem.setTitle("河北宣讲十九大：燕赵大地起春雷 高质发展显活力");
        bannerItem.setImage("http://cpc.people.com.cn/NMediaFile/2018/0209/MAIN201802091111178353313115393.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("104");
        bannerItem.setTitle("十九大专题报道");
        bannerItem.setImage("http://cpc.people.com.cn/NMediaFile/2017/1120/MAIN201711201041470238663765644.jpg");
        brolist.add(bannerItem);

        bView.setViewFactory(bannerViewFactory);
        bView.setDataList(brolist);
        bView.start();




        //item数据
        itemList = new ArrayList<>();

        itemList.add(new HomePageItemBean(1 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0614/MAIN201806141508000274770352147.png"
                , "", "中华龙舟如何奋楫争先？习近平这些话要牢记。"
                , ""
                , "1530068828", "11", "56", 0 % 2 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://cpc.people.com.cn/NMediaFile/2018/0212/MAIN201802120901276824665637665.jpg"
                , "党中央", "党的十八以来,以习近平同志为核心的党中央聚焦作风建设,以“永远在路上”的执着把全面从严治党引向深入,开创全面从严治党新局面。"
                , "2017年12月13日，中共中央总书记、国家主席、中央军委主席习近平到第71集团军视察。这是习近平同“王杰班”战士合影。"
                , "1530068828", "60", "52", 1 % 2 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://app2.sznews.com/shenzhen/Public/Attachment/2018/06/17/20180617085001_264.jpg"
                , "", "“党的作风就是党的形象,关系人心向背,关系党的生死存亡。我们党作为一个在中国长期执政的马克思主义政党,对作风问题任何时候都不能掉以轻心。"
                , ""
                , "1530068828,", "5", "23", 2 % 2 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://dingyue.nosdn.127.net/4yFUrG5HJZtSFmSUBmYV63UjrGijaY7lXcxilWpFMK==X1529370288898transferflag.png"
                , "", "要让文物说话，让历史说话，让文化说话。要加强文物保护和利用，加强历史研究和传承，使中华优秀传统文化不断发扬光大。"
                , ""
                , "1530068828", "56", "899", 3 % 2 + ""));

        itemList.add(new HomePageItemBean(1 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0614/MAIN201806141508000274770352147.png"
                , "", "为庆祝人民日报创刊70周年，人民网·中国共产党新闻网依托“人民党建云”平台，从即日起开展“我和党报党网有个约会”在线征集活动。"
                , ""
                , "1530068828", "5", "100", 1 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0615/MAIN201806150944175481595913948.jpg"
                , "", "几千年奴隶社会和封建社会史、几百年资本主义发展史，写满了极少数人为满足自身利益而残酷压迫绝大多数人的斑斑血泪，正所谓“一篇读罢头飞雪”。"
                , ""
                , "1530068828", "80", "86", 1 + ""));

        adapter.setDataList(itemList);

    }

    //页码
    private int page = 1;

    //网络数据
    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("cid", "15");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.HOME, new OkHttpUtil.ResultCallback<BaseEntity<ConsultationEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<ConsultationEntity> response) {
                endRefresh(gesture);

                if (page <= 1) {
                    brolist.clear();
                    for (int i = 0; i < response.getData().getSlide().size(); i++) {
                        bannerItem = new BannerItem(
                                response.getData().getSlide().get(i).getImageurl(),
                                response.getData().getSlide().get(i).getTitle(),
                                response.getData().getSlide().get(i).getId());
                        brolist.add(bannerItem);
                    }
                    bView.setViewFactory(bannerViewFactory);
                    bView.setDataList(brolist);
                    bView.start();

                }


                //item数据

                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    itemList.add(new HomePageItemBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getImageurl(),
                            "",
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getAddtime(),
                            response.getData().getInfo().get(i).getIlike(),
                            response.getData().getInfo().get(i).getHits(),
                            response.getData().getInfo().get(i).getPurview()));
                }
                adapter.setDataList(itemList);


            }
        }, map, "加载中", page);

    }


}











