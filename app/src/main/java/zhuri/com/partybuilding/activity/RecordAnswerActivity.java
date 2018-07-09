package zhuri.com.partybuilding.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ExaminationAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/30
 * 描述: 考试记录
 */

public class RecordAnswerActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {

    ImageView myImg;

    TextView myName;
    TextView myPosition;
    TextView myLabel;
    TextView myAccumulative;
    RelativeLayout myRlInfo;
    private ExaminationAdapter adapter;
    private List<ExaminationBean> list;

    private int page;

    //分类 0 代考 1 自考
    private String cid = "1";

    @Override
    protected void initView() {
        super.initView();
        getTitleView().setData("考试记录", 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ExaminationAdapter(this);
        recyclerView.setAdapter(adapter);
        View headView = View.inflate(this, R.layout.head_my_info, null);
        myImg = headView.findViewById(R.id.my_img);
        myName = headView.findViewById(R.id.my_name);
        myPosition = headView.findViewById(R.id.my_position);
        myLabel = headView.findViewById(R.id.my_label);
        myAccumulative = headView.findViewById(R.id.my_accumulative);
        myRlInfo = headView.findViewById(R.id.my_rl_info);
        adapter.setHeadHolder(headView);
        list = new ArrayList<>();
        getdata();
    }

    @Override
    protected void initData() {
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
//        params.setMargins(0, SizeUtils.dip2px(1), 0, 0);
//        recyclerView.setLayoutParams(params);




    }

    //  随机 1~4 ((int) ((Math.random() * 4) + 1) + "")
    public void getdata() {
        for (int i = 0; i < 5; i++) {
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
                    i % 2 + "","")
            );
        }
        adapter.setDataList(list);
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
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }


}
