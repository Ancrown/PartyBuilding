package zhuri.com.partybuilding.activity;

import android.os.Handler;
import android.util.Log;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.StudyAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
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
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/20
 * 描述: 十九大 两学一做 党务工作
 */

public class StudyActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {
    private String cid;

    private int page;
    private StudyAdapter adapter;
    private List<StudyBean> itemList;

    private String title;

    @Override
    public void initView() {
        super.initView();
        setupListView();
    }

    private void setupListView() {
        cid = getIntent().getStringExtra("cid");

        switch (cid) {
            case "0":
                title = "十九大精神";
                break;
            case "1":
                title = "两学一做";
                break;
            case "2":
                title = "党务工作";
                break;
        }
        getTitleView().setData(title, 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new StudyAdapter(this);
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
                    "遵义会议与红军长征、信仰的力量、红军悍将钟赤",
                    "遵义会议，是中国共产党历史上一个生死攸关的转折点。这次会议确立了毛泽东同志在党和红军中的领导地位，结束了王明“左”倾教条主义在党内的统治，从而使党领导的民主革命和革命战争转危为安，转败为胜，大大加快了我国革命胜利发展的进程。 ",
                    "2018-6-155",
                    "30",
                    "20",
                    "" + i % 2,
                    "100", i % 2 + ""));
        }
        adapter.setDataList(itemList);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("cid", "");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(this).doPostList(AddressRequest.STUDY, new OkHttpUtil.ResultCallback<BaseEntity<StudyEntity>>() {
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

    @Override
    protected void initData() {

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