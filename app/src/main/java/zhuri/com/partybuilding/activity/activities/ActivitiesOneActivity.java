package zhuri.com.partybuilding.activity.activities;

import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ActivitiesAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.activities.CommunityEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/20
 * 描述:活动社区
 */

public class ActivitiesOneActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {
    private int page;

    private ActivitiesAdapter adapter;
    private List<ActivitiesItemBean> itemList;

    @Override
    public void initView() {
        super.initView();
        setupListView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setMargins(0, SizeUtils.dip2px(2), 0, 0);
        recyclerView.setLayoutParams(params);
    }

    private void setupListView() {
        getTitleView().setData("社区活动", 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ActivitiesAdapter(this, "2");
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        // getdata();
        getEntity(null);
    }

    public void getdata() {

        itemList.add(new ActivitiesItemBean(
                "0",
                "郑州市洛阳商会开展迎七一“三新”党建活动。",
                "http://img3.utuku.china.com/600x0/henan/20180626/780e3df8-073b-48b0-b2b9-c95ef4238a45.jpg",
                "河南郑州市洛阳",
                "2018-06-27 08:52:57",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "1",
                "为深入学习宣传贯彻党的十九大精神，推到广大党员群众学习新思想、掌握新本领、创造新业绩，郑州市洛阳商会党总支积极响应洛阳市委组织部号召，建设新时代“三新”讲习所。",
                "http://img3.utuku.china.com/600x0/henan/20180626/780e3df8-073b-48b0-b2b9-c95ef4238a45.jpg",
                "辽宁沈阳平和平区",
                "2018-06-25 09:52:57",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "2",
                "上海这个新建白领中心与全国首批开发区为邻， 让党建活动告别“土味”。",
                "https://images.shobserver.com/news/690_390/2018/6/26/1e270dfd-644c-4af3-9892-7f4ab43244d5.jpg#https://images.shobserver.com/news/news/2018/6/26/11a6eaf3-5bb8-4f2a-be91-9326f81ecab9.jpg#https://images.shobserver.com/news/news/2018/6/26/3f2228c2-2689-4474-a02b-661c0e758d54.jpg",
                "上海虹桥白领中心位于红宝石路500号",
                "2018-06-26 16:38:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "2",
                "广德法院：组织开展迎“七一”主题党建活动。",
                "http://n.sinaimg.cn/news/transform/117/w550h367/20180625/V-Ph-hencxtt8177470.jpg",
                "黄山市徽州区岩寺新四军军部旧址",
                "2018-06-26 16:38:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
        itemList.add(new ActivitiesItemBean(
                "3",
                "咸阳市秦都区委宣传部组织的城市基层党建媒体主题采访活动正式启动。",
                "http://img3.myhsw.cn/2018-06-26/dqap77dd.jpg",
                "咸阳市秦都区委宣传部",
                "2018-06-26 17:27:59",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "4",
                "纪念建党97周年 呼兰分局 “党建活动周”系列活动进行时。",
                "http://t11.baidu.com/it/u=583052248,3786309501&fm=173&app=25&f=JPEG#http://t12.baidu.com/it/u=2350380131,965275826&fm=173&app=25&f=JPEG",
                "重温",
                "2018-06-26 21:51:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "5",
                "济南地铁区域党建主题活动举行 确保R1线元旦通车。",
                "http://t10.baidu.com/it/u=1817647791,846802102&fm=173&app=25&f=JPEG",
                "山东济南",
                "2018-06-26 19:41:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
//        for (int i = 0; i < 50; i++) {
//
//            String img = "http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180626386413961154.jpg#";
//
//
//            for (int j = 0; j < ((int) (Math.random() * 3)); j++) {
//                img = img + "http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180626386413961154.jpg#";
//            }
//            itemList.add(new ActivitiesItemBean(
//                    i + "",
//                    "为喜迎十九大，山塘片区新庄联合市职工文体协会结合建党96周年等，举办猜谜解题受党教、沙画创作学党史、以画会友庆党建、漫画诙谐做党人、舞动新庄歌唱党等丰富多彩系列活动，通过丰富多彩的活动，为党的生日献一份礼，抒发社区群众永远跟党走的心声。 ",
//                    img,
//                    "辽宁省沈阳市和平区",
//                    "2018-7-1",
//                    i % 2 + "",
//                    ((int) (Math.random() * 3)) + "",
//                    ((int) (Math.random() * 2)) + ""));
//        }
        adapter.setDataList(itemList);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("keyword", "");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(this).doPostList(AddressRequest.ACTIVITIES_COMMUNTIY, new OkHttpUtil.ResultCallback<BaseEntity<CommunityEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<CommunityEntity> response) {
                endRefresh(gesture);

                if (page <= 1) {
                    itemList.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    itemList.add(new ActivitiesItemBean(
                            response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getImageurl(),
                            response.getData().getInfo().get(i).getAddress(),
                            response.getData().getInfo().get(i).getAddtime(),
                            response.getData().getInfo().get(i).getPurview(),
                            response.getData().getInfo().get(i).getFlag(),
                            response.getData().getInfo().get(i).getStatus()
                    ));
                }
                adapter.setDataList(itemList);
            }
        }, map, "", page);
    }

    @Override
    protected void initData() {
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
    protected void initListener() {

    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
