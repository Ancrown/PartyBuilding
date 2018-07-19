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

public class RecordActivitiesThreeFragment extends BaseRecyclerFragment {


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
        adapter = new RecordActivitiesAdapter(getActivity(), "4");
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();
        // getEntity(null);
    }

    public void getdata() {

        itemList.clear();

        itemList.add(new RecordActivitiesItemBean("874",
                "一个微心愿，一颗爱的种子",
                "2016-07-12 06:10:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("874",
                "点亮微心愿 倾听民声“六个到一线”",
                "2016-07-12 13:33:19",
                "0"));
        itemList.add(new RecordActivitiesItemBean("874",
                "聚力“微心愿” 圆梦志愿行",
                "2016-07-12 06:10:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("874",
                "一个微心愿，一颗爱的种子",
                "2016-07-12 17:56:00",
                "0"));
        itemList.add(new RecordActivitiesItemBean("874",
                "张家港经开区国土分局结对微项目认领微心愿",
                "2016-07-12 17:07:10",
                "0"));
        itemList.add(new RecordActivitiesItemBean("874",
                "好心人帮17位贫困环卫工人实现微心愿 他们感恩同时说出共同心愿：被尊重",
                "2016-01-19 19:25:40",
                "1"));


        adapter.setDataList(itemList);
    }
}
