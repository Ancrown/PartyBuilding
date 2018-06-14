package zhuri.com.partybuilding.activity;

import android.os.Handler;
import android.util.Log;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ExaminationAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.ExaminationEntity;
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
 * 创建时间: 2018/6/6
 * 描述: 考试列表
 */

public class ExaminationActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {


    private ExaminationAdapter adapter;
    private List<ExaminationBean> list;

    private int page;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        super.initView();
        //设置标题
        getTitleView().setData(getResources().getString(R.string.online_answer), 0, 0, null, 0, null, this);


        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
        adapter = new ExaminationAdapter(this);
        recyclerView.setAdapter(adapter);
        list = new ArrayList<>();
        getdata();

        //下拉上拉
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "下拉");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "上拉");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);


            }

        });

    }

    //  随机 1~4 ((int) ((Math.random() * 4) + 1) + "")
    public void getdata() {
        for (int i = 0; i < 100; i++) {
            list.add(new ExaminationBean(i + "",
                    "昂阿鲁卡你个浪了康看了那看来你卡了",
                    "" + i,
                    "昂昂看了那个拉卡不能十分关键看了不可添加标签卡价格八九十可拉倒吧公交卡了不过啊那个坎",
                    ((int) ((Math.random() * 4)) + ""), "2018-6-9", "2018-7-9", i + "" + i,
                    "90", i % 2 + "", i + "" + i, "100")
            );
            Log.e("eeeee", "TYPE " + list.get(i).getType());
        }
        adapter.setDataList(list);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("keyword", "");
        map.put("page", page == 0 ? 1 : page);


        OkHttpUtil.getInstance(this).doPostList(AddressRequest.EXAMINATION_LIST, new OkHttpUtil.ResultCallback<BaseEntity<ExaminationEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<ExaminationEntity> response) {
                endRefresh(gesture);
                if (page <= 1) {
                    list.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    list.add(new ExaminationBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            "",
                            response.getData().getInfo().get(i).getDemo(),

                            response.getData().getInfo().get(i).getStatus(),
                            response.getData().getInfo().get(i).getStime(),
                            response.getData().getInfo().get(i).getEtime(),
                            response.getData().getInfo().get(i).getTimes(),
                            response.getData().getInfo().get(i).getIntegral(),
                            response.getData().getInfo().get(i).getPurview(),
                            response.getData().getInfo().get(i).getAmount(),
                            response.getData().getInfo().get(i).getScore()

                    ));
                }
                adapter.setDataList(list);

            }
        }, map, "加载中", page);
    }


    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
