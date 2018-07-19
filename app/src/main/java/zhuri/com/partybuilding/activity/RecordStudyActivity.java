package zhuri.com.partybuilding.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ExaminationAdapter;
import zhuri.com.partybuilding.adapter.RecordStudyAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.bean.RecordStudyBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/30
 * 描述:
 */

public class RecordStudyActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {

    ImageView myImg;

    TextView myName;
    TextView myPosition;
    TextView myLabel;
    TextView myAccumulative;
    RelativeLayout myRlInfo;
    private RecordStudyAdapter adapter;
    private List<RecordStudyBean> list;

    private int page;

    //分类 0 代考 1 自考
    private String cid = "1";

    @Override
    protected void initView() {
        super.initView();
        getTitleView().setData("学习记录", 0, R.drawable.back, null, 0, null, this);
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new RecordStudyAdapter(this);
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
        //名字
        myName.setText(StaticVariables.getUserName());
        //头像
        GlideUtils.LoadCircleImage(this, StaticVariables.getUserHeadImg() + "", myImg);

        myLabel.setText("优秀党员");

        myPosition.setText(StaticVariables.getdName());

        myAccumulative.setText("已加入党" + TimeUtil.differentDays(StaticVariables.getJOINTIME())
                + "天    累计学习" + StaticVariables.getStudyCount() + "次");

        list.add(new RecordStudyBean("798",
                "1",
                "聚焦新时代 解读十九大 《新时代面对面》出版发行",
                "2018-06-27 11:07:08"));
        list.add(new RecordStudyBean("798",
                "1",
                "[青海新闻联播]中国科协科学家宣讲党的十九大精神巡回报告青海站活动举行",
                "2018-07-08 19:30:00"));
        list.add(new RecordStudyBean("798",
                "2",
                "党代会各代表团认真审议党委、纪委工作报告",
                "2018-07-09 16:48:08"));
        list.add(new RecordStudyBean("798",
                "2",
                "中共同济大学第十一次代表大会闭幕,新一届党委领导集体产生",
                "2018-07-09 21:47:08"));
        list.add(new RecordStudyBean("798",
                "0",
                "市人社局召开庆祝建党97周年表彰大会暨党建工作报告会",
                "2018-07-05 07:45:00"));
        list.add(new RecordStudyBean("798",
                "0",
                "山东大学就第十四次党代会报告征求意见",
                "2018-06-29 12:51:00"));
        list.add(new RecordStudyBean("798",
                "2",
                "首份央企五年党建报告发布 教育基地为何选在这4家?",
                "2018-06-29 00:00:00"));
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


