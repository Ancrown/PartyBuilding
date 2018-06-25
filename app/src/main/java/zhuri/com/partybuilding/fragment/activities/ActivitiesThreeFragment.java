package zhuri.com.partybuilding.fragment.activities;


import android.os.Handler;
import android.util.Log;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ActivitiesAdapter;
import zhuri.com.partybuilding.adapter.VWishAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.bean.VWishBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.activities.VVolunteerEntity;
import zhuri.com.partybuilding.entity.activities.VWish;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述: 微心愿
 */

public class ActivitiesThreeFragment extends BaseRecyclerFragment {

    private int page;

    private VWishAdapter adapter;
    private List<VWishBean> itemList;


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

    private void setupListView() {
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new VWishAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();

    }

    public void getdata() {

        for (int i = 0; i < 10; i++) {
            itemList.add(new VWishBean(
                    i + "",
                    "微心愿|“点亮微心愿，争做圆梦人”",
                    i % 2 + "",
                    "微心愿”是在微博上新近兴起的一个网络热词，是指借助微博平台发表自己的个人心愿，用户可以通过微博发布个人愿望，也可以认领其他人发布的愿望，发起人与认领人可以借助微博平台相互沟通，最终完成用户的微心愿。",
                    i % 2 + "",
                    "2018-6-9 10:20",
                    "90",
                    i % 2 + ""));
            Log.e("eeeeee", "TYPE    " + itemList.get(i).getType());
        }
        adapter.setDataList(itemList);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TOKEN, ""));
        map.put("keyword", "");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.ACTIVITIES_V_WISH, new OkHttpUtil.ResultCallback<BaseEntity<VWish>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<VWish> response) {
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
                            response.getData().getInfo().get(i).getAddtime(),
                            response.getData().getInfo().get(i).getIntegral(),
                            response.getData().getInfo().get(i).getPurview()));
                }
                adapter.setDataList(itemList);
            }
        }, map, "", page);
    }

}
