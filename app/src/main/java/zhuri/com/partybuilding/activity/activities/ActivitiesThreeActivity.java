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
import zhuri.com.partybuilding.adapter.VWishAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.VWishBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.activities.VWishEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/20
 * 描述:
 */

public class ActivitiesThreeActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {
    private int page;

    private VWishAdapter adapter;
    private List<VWishBean> itemList;


    @Override
    public void initView() {
        super.initView();
        setupListView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setMargins(0, SizeUtils.dip2px(2), 0, 0);
        recyclerView.setLayoutParams(params);
    }

    @Override
    public void initData() {

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

    private void setupListView() {
        getTitleView().setData("微心愿", 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new VWishAdapter(this);
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        //  getdata();
        getEntity(null);
    }

    public void getdata() {
        itemList.add(new VWishBean(
                "0",
                "微心愿|“点亮微心愿，争做圆梦人”",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-06-09",
                "2018-10-10",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "南湖社区"));
        itemList.add(new VWishBean(
                "1",
                "【基层党建】扬州市邗江区杨寿镇点亮“微心愿” 服务大民生",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-06-22",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "扬州市邗江区杨寿镇"));

        itemList.add(new VWishBean(
                "2",
                "秦都党建创新路 全心全意为人民",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-06-25",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "秦都区委"));

        itemList.add(new VWishBean(
                "3",
                "党建微帮站解民“微心愿”",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2017-12-15",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "太原市老军营街道辖区"));

        itemList.add(new VWishBean(
                "4",
                "莲花社区：党建领航——“圆梦微心愿送书助成长”",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-04-27",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "莲花社区"));

        itemList.add(new VWishBean(
                "5",
                "党员认领微心愿 长沙岳麓区创新党建工作",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2017-09-30",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "长沙岳麓区"));
        itemList.add(new VWishBean(
                "6",
                "占勇来到永修调研企业党建及开展“圆梦微心愿”活动",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-02-05",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "赣江新区党工委"));
        itemList.add(new VWishBean(
                "7",
                "党建“微心愿”墙助群众圆梦",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2017-08-16",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "德源街道党工委"));
//        for (int i = 0; i < 10; i++) {
//            itemList.add(new VWishBean(
//                    i + "",
//                    "微心愿|“点亮微心愿，争做圆梦人”",
//                    i % 2 + "",
//                    "微心愿”是在微博上新近兴起的一个网络热词，是指借助微博平台发表自己的个人心愿，用户可以通过微博发布个人愿望，也可以认领其他人发布的愿望，发起人与认领人可以借助微博平台相互沟通，最终完成用户的微心愿。",
//                    i % 2 + "",
//                    "2018-6-9", "2018-10-10",
//                    "90",
//                    i % 2 + "", "5", "XXXXXXXXXXXX"));
//            Log.e("eeeeee", "TYPE    " + itemList.get(i).getType());
//        }
        adapter.setDataList(itemList);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("keyword", "");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(this).doPostList(AddressRequest.ACTIVITIES_V_WISH, new OkHttpUtil.ResultCallback<BaseEntity<VWishEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<VWishEntity> response) {
                endRefresh(gesture);
                if (page <= 1) {
                    itemList.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    itemList.add(new VWishBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            "",
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getStatus(),
                            response.getData().getInfo().get(i).getStime(),
                            response.getData().getInfo().get(i).getEtime(),
                            "",
                            response.getData().getInfo().get(i).getPurview(),
                            response.getData().getInfo().get(i).getSignup(),
                            response.getData().getInfo().get(i).getDname()));
                }
                adapter.setDataList(itemList);


            }
        }, map, "", page);
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
