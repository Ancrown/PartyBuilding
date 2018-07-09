package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ExaminationAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.ExaminationEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.AppUtils;
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


    @BindView(R.id.aty_examination_daikao)
    TextView atyExaminationDaikao;
    @BindView(R.id.aty_examination_yikao)
    TextView atyExaminationYikao;


    private ExaminationAdapter adapter;
    private List<ExaminationBean> list;

    private int page;

    //分类 0 代考 1 自考
    private String cid = "0";

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_examination;
    }

    @Override
    protected void initView() {
        super.initView();

        //设置标题
        getTitleView().setData(getResources().getString(R.string.online_answer), 0, R.drawable.back, null, 0, null, this);


        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ExaminationAdapter(this);
        recyclerView.setAdapter(adapter);
        list = new ArrayList<>();
        // getdata();
        getEntity(null);
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

    //  随机 1~4 ((int) ((Math.random() * 4) + 1) + "")
    public void getdata() {
        for (int i = 0; i < (cid.equals("0") ? 5 : 2); i++) {
            list.add(new ExaminationBean(i + "",
                    "201" + i + "党校考试题库大全",
                    "201" + i + "年党纪法规知识考试题库(更新) - 全市党纪法规知识考试题库(共 365 题) ",
                    "1" + i,
                    "100",
                    "60",
                    "2018-6-9",
                    "2018-10-1",
                    !cid.equals("0") ? "9" + i : "",
                    cid.equals("0") ? "0" : "1",
                    "0",
                    i % 2 + "",
                    "  ")
            );
        }
        adapter.setDataList(list);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("keyword", "");
        map.put("page", page == 0 ? 1 : page);
        map.put("cid", cid);

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
                    list.add(new ExaminationBean(
                            response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getAmount(),
                            response.getData().getInfo().get(i).getScore(),
                            response.getData().getInfo().get(i).getTimes(),
                            response.getData().getInfo().get(i).getStime(),
                            response.getData().getInfo().get(i).getEtime(),
                            response.getData().getInfo().get(i).getScore(),
                            cid,
                            "1",
                            response.getData().getInfo().get(i).getStatus(),
                            response.getData().getInfo().get(i).getAddtime()

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


    @OnClick({R.id.aty_examination_daikao, R.id.aty_examination_yikao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_examination_daikao:
                atyExaminationDaikao.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_semicircle_left_orange_5));
                atyExaminationYikao.setBackground(null);
                cid = "0";
                page = 1;
                list.clear();
                //  getdata();
                getEntity(null);
                break;
            case R.id.aty_examination_yikao:
                atyExaminationYikao.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_semicircle_right_orange_5));
                atyExaminationDaikao.setBackground(null);
                cid = "1";
                page = 1;
                list.clear();
                // getdata();
                getEntity(null);
                break;
        }
    }


}
