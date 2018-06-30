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
import zhuri.com.partybuilding.entity.activities.VVolunteerEntity;
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
 * 描述:
 */

public class ActivitiesTwoActivity extends  BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {
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
        getTitleView().setData("微志愿", 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ActivitiesAdapter(this,"1");
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();

    }
    public void getdata() {

        itemList.add(new ActivitiesItemBean(
                "6",
                "遂宁市职校领导到成都党建特色学校考察党建活动室建设。",
                "http://www.eol.cn/sichuan/sc_school/201806/W020180625385433733263.jpg#http://www.eol.cn/sichuan/sc_school/201806/W020180625385433746659.jpg",
                "川师大实验外国语学校",
                "2018-06-25 08:52:57",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
        itemList.add(new ActivitiesItemBean(
                "7",
                "80后女博士为党建活动注入新活力——记中科新松有限公司党总支书记许楠。",
                "http://www.pudong.gov.cn/shpd/UploadFile/e24c44b7-11e4-47b4-97fe-ecd8f226fcc1/20180626133443674.jpg",
                "上海浦东",
                "2018-06-26 09:15:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
        itemList.add(new ActivitiesItemBean(
                "8",
                "党建丨陕西送变电:知行合一 擎旗聚魂再登高。",
                "http://www.cpnn.com.cn/zdzgtt/201806/W020180605341637718747.jpg",
                "上海浦东",
                "2018-06-26 10:00:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
        itemList.add(new ActivitiesItemBean(
                "9",
                "中牟县雁鸣湖镇开展迎“七一”系列党建活动。",
                "http://gov.hnr.cn/xzzm/20180626/385892/res/1meybGpX.jpg",
                "中牟县雁鸣湖镇",
                "2018-06-26 00:00:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
        itemList.add(new ActivitiesItemBean(
                "10",
                "雄安铁塔联合三家运营商开展迎七一联合党建活动。",
                "http://p0.ifengimg.com/pmop/2018/0621/5CAE3CC0F3E46044A479931789E7349C5E57710F_size67_w580_h386.jpeg",
                "雄安",
                "2018-06-21 23:59:43",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
        itemList.add(new ActivitiesItemBean(
                "11",
                "开展“不忘初心 扶贫帮困“迎“七一”党建主题党日活动。",
                "http://upload.0745news.cn/2018/0626/1530018116881.jpg#http://upload.0745news.cn/2018/0626/1530018115367.jpg#http://upload.0745news.cn/2018/0626/1530018116501.jpg",
                "羊角坪村",
                "2018-06-26 22:53:00",
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

        OkHttpUtil.getInstance(this).doPostList(AddressRequest.ACTIVITIES_V_VOLUNTEER, new OkHttpUtil.ResultCallback<BaseEntity<VVolunteerEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<VVolunteerEntity> response) {
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endRefresh("Refresh");
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                page++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endRefresh("LoadMore");

                    }
                }, 1000);


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
