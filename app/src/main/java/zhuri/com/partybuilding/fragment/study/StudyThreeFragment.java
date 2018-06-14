package zhuri.com.partybuilding.fragment.study;

import android.os.Handler;
import android.util.Log;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.StudyAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.StudyBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.StudyEntity;
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
 * 描述:党务工作
 */

public class StudyThreeFragment extends BaseRecyclerFragment {

    private int page;
    private StudyAdapter adapter;
    private List<StudyBean> itemList;

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

    }

    private void setupListView() {


        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
        adapter = new StudyAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();


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

    //数据
    public void getdata() {
        //item数据
        for (int i = 0; i < 5; i++) {
            itemList.add(new StudyBean(i + "",
                    "咖喱牛唠嗑呢来拿来呢来看你可怜你看了那看来你",
                    "爱看美女反馈滥发开朗了半年可填报情况不太认可清洁不发动机卡布法规尽快把看个几把开个本会计报告卡不到关键看吧again",
                    "2018-6-5 15:48",
                    "9991",
                    "20",
                    "" + i % 2,
                    "100", i % 2 + ""));
        }
        adapter.setDataList(itemList);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TOKEN, ""));
        map.put("cid", "");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.STUDY, new OkHttpUtil.ResultCallback<BaseEntity<StudyEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<StudyEntity> response) {
                endRefresh(gesture);
                if (page <= 1) {
                    itemList.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    itemList.add(new StudyBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getAddtime(),
                            response.getData().getInfo().get(i).getAmount(),
                            response.getData().getInfo().get(i).getIlike(),
                            "",
                            response.getData().getInfo().get(i).getHits(),
                            response.getData().getInfo().get(i).getPurview()));
                }
                adapter.setDataList(itemList);

            }
        }, map, "加载中", page);
    }

}