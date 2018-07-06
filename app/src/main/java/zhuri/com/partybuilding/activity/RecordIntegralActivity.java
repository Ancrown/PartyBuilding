package zhuri.com.partybuilding.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.RecordIntegralAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.RecordIntegralBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.date.SelectDateDialog;
import zhuri.com.partybuilding.util.popupwindow.SelectTimePopupWindow;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/29
 * 描述: 积分记录
 */

public class RecordIntegralActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {

    //  @BindView(R.id.record_integral_name)
    TextView recordIntegralName;
    //   @BindView(R.id.record_integral_position)
    TextView recordIntegralPosition;
    //   @BindView(R.id.record_integral_img)
    ImageView recordIntegralImg;
    //    @BindView(R.id.record_integral_integral)
    TextView recordIntegralIntegral;

    RelativeLayout recordIntegralIntegralRL;
    //   @BindView(R.id.record_integral_ranking)
    TextView recordIntegralRanking;
    //   @BindView(R.id.record_integral_time)
    TextView recordIntegralTime;

    private String integralTime;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshLayout;

    private RecordIntegralBean bean;
    private List<RecordIntegralBean> list;

    private RecordIntegralAdapter adapter;

    public LinearLayoutManager lmr;

    private int page;

    private SelectTimePopupWindow selectTimePopupWindow;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
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

        recordIntegralTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimePopupWindow = new SelectTimePopupWindow(RecordIntegralActivity.this, "选择日期", false);

                selectTimePopupWindow.setOnSelectTimeListener(new SelectTimePopupWindow.OnSelectTimeListener() {
                    @Override
                    public void confirm(String year, String month, String day, boolean past) {

                        if (past) {
                            recordIntegralTime.setText(year + "-" + month);
                            integralTime = year + "-" + month;
                        } else {
                            ToolUtils.showToast(RecordIntegralActivity.this, "您选择的有误！");
                        }
                        selectTimePopupWindow.dismiss();
                    }
                });
            }
        });


    }


    @Override
    protected void initView() {
        getTitleView().setData("积分记录", 0, R.drawable.back, null, 0, null, this);
        View view = View.inflate(this, R.layout.head_record_integral, null);
        recordIntegralName = view.findViewById(R.id.record_integral_name);
        recordIntegralPosition = view.findViewById(R.id.record_integral_position);
        recordIntegralImg = view.findViewById(R.id.record_integral_img);
        recordIntegralIntegral = view.findViewById(R.id.record_integral_integral);
        recordIntegralIntegralRL = view.findViewById(R.id.record_integral_time_rl);
        recordIntegralRanking = view.findViewById(R.id.record_integral_ranking);
        recordIntegralTime = view.findViewById(R.id.record_integral_time);

      //  refreshLayout.addFixedExHeader(view);

        //是否允许越界时显示刷新控件（setOverScrollTopShow,setOverScrollBottomShow统一设置方法）
        refreshLayout.setOverScrollRefreshShow(false);
        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshLayout.setFloatRefresh(true);
        //禁止下拉
    //    refreshLayout.setEnableRefresh(false);
        //listview 效果
        lmr = new LinearLayoutManager(this);
        //设置为垂直布局，这也是默认的
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(lmr);

        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new RecordIntegralAdapter(this);

        recyclerView.setAdapter(adapter);


        adapter.setHeadHolder(view);
        list = new ArrayList<>();
        getdata();
    }

    private void getdata() {
        for (int i = 0; i < 10; i++) {
            bean = new RecordIntegralBean(i + "",
                    "及那附近慷慨激昂而其他其他气温气温人情味",
                    "2018-6-28",
                    (int) (Math.random() * 3) + "",
                    (int) (Math.random() * 3) + "",
                    i + "" + i);
            list.add(bean);
        }
        adapter.setDataList(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_record_integral;
    }

    //根据手势 结束下拉上拉
    public void endRefresh(String gesture) {
        switch (gesture) {
            case "":
                break;
            case "Refresh":
                refreshLayout.finishRefreshing();
                break;
            case "Loadmore":
                refreshLayout.finishLoadmore();
                break;
        }
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
