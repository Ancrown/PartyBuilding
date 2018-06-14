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

public class TabLayoutThreeFragment extends BaseRecyclerFragment {

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


        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
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

        broadcastBean = new BroadcastBean(broId, imageResIds, contentDescs);
        bView.setData(broadcastBean);


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
                    , "2018-5-24 11:27", "100", "999+", i % 2 + ""));
        }
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

    @Override
    public void onPause() {
        super.onPause();
        bView.setRunning(false);
    }

}
