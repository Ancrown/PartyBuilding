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
import zhuri.com.partybuilding.bean.BroadcastBean;
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
import zhuri.com.partybuilding.view.BroadcastView;

/**
 * 党建要闻
 */

public class TabLayoutTwoFragment extends BaseRecyclerFragment {

    //  @BindView(R.id.fra_consultation_one_bro)
    BroadcastView bView;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;

    private BroadcastBean broadcastBean;
    private List<String> broId;
    private List<String> imageResIds;
    private List<String> contentDescs;

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
                        page = 1;
                        // getEntity("Refresh");
                        endRefresh("Refresh");
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "上拉");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        // getEntity("Loadmore");
                        endRefresh("Loadmore");
                    }
                }, 1000);


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
        getdata();


    }

    //数据
    public void getdata() {
        //轮播图
        imageResIds = new ArrayList<>();
        imageResIds.add("http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180619345889972995.jpg");
        imageResIds.add("http://images1.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180615370080057216.jpg");
        imageResIds.add("http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180618396478717942.jpg");
        imageResIds.add("http://images1.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180618393023283314.jpg");
        imageResIds.add("http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180614376614466052.jpg");

        contentDescs = new ArrayList<>();
        contentDescs.add("走访《习近平谈治国理政》英文版翻译团队");
        contentDescs.add("习近平在山东考察");
        contentDescs.add("统帅关怀励兵心 矢志强军谋打赢");
        contentDescs.add("北京：追求精彩非凡卓越 全力推进冬奥筹办");
        contentDescs.add("山东：打造乡村振兴的“齐鲁样板”");


        broadcastBean = new BroadcastBean(broId, imageResIds, contentDescs);
        bView.setData(broadcastBean);


        //item数据
        itemList = new ArrayList<>();

        itemList.add(new HomePageItemBean(1 + ""
                , "http://www.people.com.cn/mediafile/pic/20180615/81/14729970956243590109.jpg"
                , "党中央", "福建省东山县陈城镇澳角村村民自力更生，艰苦奋斗，用智慧和双手实现了由传统落后的渔村到现代美丽渔村的华丽蝶变。新华社发"
                , ""
                , "2018-6-15", "25", "53", 0 + ""));

        itemList.add(new HomePageItemBean(2 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0613/MAIN201806130921070354054484766.jpg"
                , "", "让贫困群众稳定脱贫、逐步致富，为贫困地区找到更多脱贫致富的光源，是扶贫干部新时代的历史使命"
                , ""
                , "2018-6-16", "11", "20", 1 + ""));

        itemList.add(new HomePageItemBean(3 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0508/MAIN201805080818049056278193206.jpg"
                , "", "福建省东山县陈城镇澳角村村民自力更生，艰苦奋斗，用智慧和双手实现了由传统落后的渔村到现代美丽渔村的华丽蝶变。新华社发"
                , ""
                , "2018-6-15", "25", "53", 0 + ""));

        itemList.add(new HomePageItemBean(4 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0507/MAIN201805071649000374359575469.jpg"
                , "党中央", "人民网·中国共产党新闻网推出升级版“人民党建云”平台"
                , ""
                , "2018-6-15", "25", "53", 1 + ""));

        itemList.add(new HomePageItemBean(5 + ""
                , "http://dangjian.people.com.cn/NMediaFile/2018/0508/MAIN201805081627000118959442100.jpg"
                , "", "中国共产党新闻网北京5月8日电 党的十九大胜利召开以来，全党全国全军把学习贯彻党的十九大精神作为当前和今后一个时期的首要政治任务，及时部署、有序组织开展学习宣传贯彻党的十九大精神各项工作。"
                , ""
                , "2018-5-9", "55", "999+", 0 + ""));

        adapter.setDataList(itemList);

    }

    //页码
    private int page = 0;

    //网络数据
    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TOKEN, ""));
        map.put("cid", "");
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
                    broId = new ArrayList<>();
                    imageResIds = new ArrayList<>();
                    contentDescs = new ArrayList<>();
                    for (int i = 0; i < response.getData().getSlide().size(); i++) {
                        broId.add(response.getData().getSlide().get(i).getId());
                        imageResIds.add(response.getData().getSlide().get(i).getImageurl());
                        contentDescs.add(response.getData().getSlide().get(i).getTitle());
                    }
                    broadcastBean = new BroadcastBean(broId, imageResIds, contentDescs);
                    bView.setData(broadcastBean);

                    itemList.clear();
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
