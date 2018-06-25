package zhuri.com.partybuilding.activity.activities;

import android.os.Handler;
import android.util.Log;

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
    }
    private void setupListView() {
        getTitleView().setData("微志愿", 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ActivitiesAdapter(this);
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();

    }
    public void getdata() {

        for (int i = 0; i < 10; i++) {
            itemList.add(new ActivitiesItemBean(
                    i + ""
                    , "http://www.shmh.gov.cn/Plugins/ueditor_release-ueditor1_4_3_1-utf8-net/net/upload/image/20171213/6364877839491409896842106914ffcdaf.jpg"
                    , "“党员’微’志愿点亮’大’党建”华漕微自治•红色微志愿版块正式上线启动。"
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
