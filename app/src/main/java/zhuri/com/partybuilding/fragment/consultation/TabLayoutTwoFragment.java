package zhuri.com.partybuilding.fragment.consultation;


import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.ConsultationEntity;
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

public class TabLayoutTwoFragment extends BaseRecyclerFragment {

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
        adapter = new HomePageAdapter(true, getActivity());
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
        bannerItem.setTitle("推进人力资源服务业创新发展 深圳（南山）人力资源服务产业联盟成立");
        bannerItem.setImage("http://www.nsxf.cn/upload/2018/0606/cc5372c0e4e04055976885d4946dd7c8.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("102");
        bannerItem.setTitle("南山区开展“不忘初心、牢记使命”主题微型马拉松活动");
        bannerItem.setImage("http://www.nsxf.cn/upload/2018/0605/de79d1ac2d4343c29d590f623a5cbd79.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("103");
        bannerItem.setTitle("南山区召开“双百”人才座谈会");
        bannerItem.setImage("http://www.nsxf.cn/upload/2018/0605/92be28d16e4f46d88938f189c3681d5a.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("104");
        bannerItem.setTitle("南山举行首届“企业红色运动会” 520名党员用行动向党“表白”");
        bannerItem.setImage("http://www.nsxf.cn/upload/2018/0521/d845ed9eeb0d4672b8e62674ebf9fadc.jpg");
        brolist.add(bannerItem);

        bannerItem = new BannerItem();
        bannerItem.setId("104");
        bannerItem.setTitle("组织部长面对面");
        bannerItem.setImage("http://dangjian.people.com.cn/NMediaFile/2016/0630/MAIN201606301910107798646305239.jpg");
        brolist.add(bannerItem);

        bView.setViewFactory(bannerViewFactory);
        bView.setDataList(brolist);
        bView.start();




        //item数据
        itemList = new ArrayList<>();

        itemList.add(new HomePageItemBean(1 + ""
                , "http://www.people.com.cn/mediafile/pic/20180615/81/14729970956243590109.jpg"
                , "党中央", "福建省东山县陈城镇澳角村村民自力更生，艰苦奋斗，用智慧和双手实现了由传统落后的渔村到现代美丽渔村的华丽蝶变。新华社发"
                , ""
                , "1530068828", "25", "53", 0 + ""));

        itemList.add(new HomePageItemBean(2 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0613/MAIN201806130921070354054484766.jpg"
                , "", "让贫困群众稳定脱贫、逐步致富，为贫困地区找到更多脱贫致富的光源，是扶贫干部新时代的历史使命"
                , ""
                , "1530068828", "11", "20", 1 + ""));

        itemList.add(new HomePageItemBean(3 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0508/MAIN201805080818049056278193206.jpg"
                , "", "福建省东山县陈城镇澳角村村民自力更生，艰苦奋斗，用智慧和双手实现了由传统落后的渔村到现代美丽渔村的华丽蝶变。新华社发"
                , ""
                , "1530068828", "25", "53", 0 + ""));

        itemList.add(new HomePageItemBean(4 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0507/MAIN201805071649000374359575469.jpg"
                , "党中央", "人民网·中国共产党新闻网推出升级版“人民党建云”平台"
                , ""
                , "1530068828", "25", "53", 1 + ""));

        itemList.add(new HomePageItemBean(5 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0508/MAIN201805081627000118959442100.jpg"
                , "", "中国共产党新闻网北京5月8日电 党的十九大胜利召开以来，全党全国全军把学习贯彻党的十九大精神作为当前和今后一个时期的首要政治任务，及时部署、有序组织开展学习宣传贯彻党的十九大精神各项工作。"
                , ""
                , "1530068828", "55", "999+", 0 + ""));

        adapter.setDataList(itemList);

    }

    //页码
    private int page = 1;

    //网络数据
    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TOKEN, ""));
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
