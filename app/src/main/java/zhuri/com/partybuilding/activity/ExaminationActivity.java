package zhuri.com.partybuilding.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ExaminationAdapter;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.Examinationbean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class ExaminationActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;

    private ExaminationAdapter adapter;
    private List<Examinationbean> list;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        //设置标题
        getTitleView().setData(getResources().getString(R.string.online_answer), 0, 0, null, 0, null, this);

        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshLayout.setFloatRefresh(true);

        //listview 效果
        LinearLayoutManager lmr = new LinearLayoutManager(this);
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recycler.setLayoutManager(lmr);
        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
        adapter = new ExaminationAdapter(this);
        recycler.setAdapter(adapter);

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
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Examinationbean(i + "",
                    "昂阿鲁卡你个浪了康看了那看来你卡了",
                    "" + i,
                    "昂昂看了那个拉卡不能十分关键看了不可添加标签卡价格八九十可拉倒吧公交卡了不过啊那个坎",
                    "2018-6-6 14:28",
                    ((int) ((Math.random() * 4)) + ""),
                    "2018-6-10"));
            Log.e("eeeee", "TYPE " + list.get(i).getType());
        }
        adapter.setDataList(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_examination;
    }


    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
