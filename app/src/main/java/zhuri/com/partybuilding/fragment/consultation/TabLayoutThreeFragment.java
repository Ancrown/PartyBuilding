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

public class TabLayoutThreeFragment extends BaseRecyclerFragment {

    //  @BindView(R.id.fra_consultation_one_bro)
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
        adapter = new HomePageAdapter(true, getActivity(),"项目为王");
        recycler.setAdapter(adapter);
        adapter.setHeadHolder(exHeader);
        //初始化数据对象
        itemList = new ArrayList<>();
     //   getdata();

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
                , "http://www.people.com.cn/mediafile/pic/20180615/45/8657476590562130661.jpg"
                , "", "核心阅读习近平总书记指出：“人民是历史的创造者，群众是真正的英雄。人民群众是我们力量的源泉。”党同人民群众联系问题是关系党生死存亡的问题。坚持以人民为中心的发展思想，就是坚持人民主体地位，践行全心全意为人民服务的根本宗旨，把党的群众路线贯彻到治国理政全部…"
                , ""
                , "1530068828", "11", "999+", 0 + ""));
        itemList.add(new HomePageItemBean(2 + ""
                , "http://cpc.people.com.cn/NMediaFile/2018/0615/MAIN201806150846227533060354023.jpg"
                , "", "　人民网北京6月14日电 （记者赵成）国家主席习近平14日在人民大会堂会见美国国务卿蓬佩奥。"
                , ""
                , "1530068828", "999+", "999+", 1 + ""));

        itemList.add(new HomePageItemBean(3 + ""
                , "http://cpc.people.com.cn/NMediaFile/2018/0212/MAIN201802120901276824665637665.jpg"
                , "", "不忘初心，枝叶关情。是什么样的成长经历，在总书记心中烙下深深的“人民情怀”？"
                , ""
                , "1530068828", "45", "999+", 0 + ""));

        itemList.add(new HomePageItemBean(4 + ""
                , "http://paper.people.com.cn/rmrb/res/1/20171026/1508964076506_1.jpg"
                , "", "习近平：新时代要有新气象更要有新作为中国人民生活一定会一年更比一年好"
                , ""
                , "1530068828", "56", "999+", 1 + ""));

        itemList.add(new HomePageItemBean(5 + ""
                , "http://rencai.people.com.cn/NMediaFile/2017/0828/MAIN201708281519208668866763469.gif"
                , "", "新中国成立以来，从钱学森到杨振宁，从施一公到黄大年，一批又一批海归人才学成归来，书写着科技报国的壮丽篇章，为祖国的建设与发展作出了卓越贡献。人民网·中国人才网邀请罗克佳华工业有限公司董事长李玮、电子科技大学计算机科学与工程学院院长戴元顺、南京大学现代工程与应用科学学院教授朱嘉，就“千人计划”专家谈归国创业故事与网友在线交流，欢迎收看。"
                , ""
                , "1530068828", "66", "999+", 0 + ""));


        adapter.setDataList(itemList);

    }

    //页码
    private int page = 1;

    //网络数据
    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TOKEN, ""));
        map.put("cid", "16");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.CONSULTATION, new OkHttpUtil.ResultCallback<BaseEntity<ConsultationEntity>>() {
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
