package zhuri.com.partybuilding.fragment.activities;

import android.os.Handler;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.ActivitiesAdapter;
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

public class RecordActivitiesOneFragment extends BaseRecyclerFragment {


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
        adapter = new RecordActivitiesAdapter(getActivity(), "2");
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();
        // getEntity(null);
    }

    public void getdata() {

        itemList.clear();

        itemList.add(new RecordActivitiesItemBean("526",
                "渝北紫福路社区开展打击非法集资传活动",
                "2018-07-15 15:00:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("526",
                "社区携手学校办暑期“道德银行”",
                "2018-07-10 13:00:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("526",
                "厦门市大嶝司法所开展送法进社区活动",
                "2018-07-10 08:00:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("526",
                "陕西省宝鸡市凤翔县人民检察院开展法制进社区活动",
                "2018-07-11 14:10:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("526",
                "工农兵社区活动中心管理员 做“鸡毛蒜皮”寻常事",
                "2018-07-10 16:31:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("526",
                "安平社区开展安全生产知识竞赛活动",
                "2018-07-11 07:00:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("526",
                "奉贤区开展抵制“克隆出租车”宣传入社区活动",
                "2018-07-10 11:54:00",
                "1"));
        itemList.add(new RecordActivitiesItemBean("526",
                "复星保德信人寿开展“7.8保险公众宣传日”进社区活动",
                "2018-07-11 15:21:10",
                "1"));

        adapter.setDataList(itemList);
    }
}
