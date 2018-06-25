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
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
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

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述: 活动社区
 */

public class ActivitiesOneFragment extends BaseRecyclerFragment {

    private int page;

    private ActivitiesAdapter adapter;
    private List<ActivitiesItemBean> itemList;


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
        adapter = new ActivitiesAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();

    }

    public void getdata() {

        for (int i = 0; i < 10; i++) {
            itemList.add(new ActivitiesItemBean(
                    i + ""
                    , "http://img1.dzwww.com:8080/tupian/20170728/9/12094154024698648245.jpg"
                    , "为喜迎十九大，山塘片区新庄联合市职工文体协会结合建党96周年等，举办猜谜解题受党教、沙画创作学党史、以画会友庆党建、漫画诙谐做党人、舞动新庄歌唱党等丰富多彩系列活动，通过丰富多彩的活动，为党的生日献一份礼，抒发社区群众永远跟党走的心声。 "
                    , i + ""
                    , "辽宁省沈阳市和平区"
                    , "1000"
                    , "99999999"
                    , "2018-7-1", "2018-6-5"
                    , ((int) (Math.random() * 4)) + "", i % 2 + ""));
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

        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.ACTIVITIES_COMMUNTIY, new OkHttpUtil.ResultCallback<BaseEntity<CommunityEntity>>() {
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
                    itemList.add(new ActivitiesItemBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getImageurl(),
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getIstop(),
                            response.getData().getInfo().get(i).getAddress(),
                            response.getData().getInfo().get(i).getIlike(),
                            response.getData().getInfo().get(i).getAmount(),
                            response.getData().getInfo().get(i).getEtime(),
                            response.getData().getInfo().get(i).getStime(),
                            response.getData().getInfo().get(i).getStatus(),
                            response.getData().getInfo().get(i).getPurview()));
                }
                adapter.setDataList(itemList);
            }
        }, map, "", page);
    }

}
