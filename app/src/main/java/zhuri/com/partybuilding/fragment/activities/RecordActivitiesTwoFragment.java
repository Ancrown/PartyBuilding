package zhuri.com.partybuilding.fragment.activities;

import android.os.Handler;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.RecordActivitiesAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.RecordActivitiesItemBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/9
 * 描述:
 */

public class RecordActivitiesTwoFragment extends BaseRecyclerFragment {



    private int page = 1;

    private RecordActivitiesAdapter adapter;
    private List<RecordActivitiesItemBean> itemList;


    @Override
    public int getLayoutId() {
        return R.layout.base_fresh_recy;
    }


    @Override
    public void initView() {
        super.initView();
        setupListView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setMargins(0, SizeUtils.dip2px(2), 0, 0);
        recyclerView.setLayoutParams(params);
    }

    @Override
    public void refreshData() {

        //下拉上拉
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        endRefresh("Refresh");
                        getdata();

                    }
                }, 2000);

            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        endRefresh("LoadMore");
                        getdata();

                    }
                }, 2000);

            }

        });
    }

    private void setupListView() {
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new RecordActivitiesAdapter(getActivity(), "3");
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();
        // getEntity(null);
    }

    public void getdata() {

        itemList.clear();
        itemList.add(new RecordActivitiesItemBean("877",
                "“文明乘车 从我做起” 真情巴士开展排队乘车日主题活动",
                "2018-07-12 15:00:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("877",
                "三八南社区开展微志愿服务活动",
                "2018-06-19 08:00:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("877",
                "实施绿色生态教育 提升文明校园内涵",
                "2018-07-05 08:58:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("877",
                "喜迎端午节 沈河区三八南社区开展微志愿服务活动",
                "2018-06-15 16:00:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("877",
                "桐梓县2018年“贵州生态日”系列志愿者服务暨助推脱贫攻坚“三治”活动动员大会召开 ",
                "2018-06-12 13:06:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("877",
                "志愿有微爱 上合再添彩 央企青年志愿服务获外国友人点赞",
                "2018-06-08 10:10:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("877",
                "关于面向各高校征集西部计划和研究生支教团“志愿文学”作品的启事",
                "2018-07-13 11:13:50",
                "1"));
        itemList.add(new RecordActivitiesItemBean("877",
                "皇姑区中小学生志愿申领“创城暑假作业”",
                "2018-07-12 08:00:00",
                "0"));
        adapter.setDataList(itemList);
    }
}
