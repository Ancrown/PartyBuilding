package zhuri.com.partybuilding.fragment.study;

import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.study.StudyOneAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.study.StudyOneBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.study.StudyEntity;
import zhuri.com.partybuilding.entity.study.StudyOneEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:十九大报告
 */

public class StudyOneFragment extends BaseRecyclerFragment {

    private int page=1;
    private StudyOneAdapter adapter;
    private List<StudyOneBean> itemList;

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

    }

    private void setupListView() {


        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new StudyOneAdapter(getActivity(), "0");
        recyclerView.setAdapter(adapter);
        itemList = new ArrayList<>();
        //  getdata();
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

    //数据
    public void getdata() {
        //item数据
        for (int i = 0; i < 10; i++) {
            itemList.add(new StudyOneBean(i + "",
                    "遵义会议与红军长征、信仰的力量、红军悍将钟赤",
                    "http://cms-bucket.nosdn.127.net/catchpic/3/3a/3ae4ccf442bc72757f4b2d47b9fcb511.jpg",
                    "遵义会议，是中国共产党历史上一个生死攸关的转折点。这次会议确立了毛泽东同志在党和红军中的领导地位，结束了王明“左”倾教条主义在党内的统治，从而使党领导的民主革命和革命战争转危为安，转败为胜，大大加快了我国革命胜利发展的进程。 ",
                    (int) (Math.random() * 2) + "",
                    (int) (Math.random() * 2) + "",
                    (int) (Math.random() * 100) + "",
                    "0"));
        }
        adapter.setDataList(itemList);
    }


    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("cid", "9");
        map.put("page", page == 0 ? 1 : page);

        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.STUDY_ONE, new OkHttpUtil.ResultCallback<BaseEntity<StudyOneEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<StudyOneEntity> response) {
                endRefresh(gesture);
                if (page <= 1) {
                    itemList.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    itemList.add(new StudyOneBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getImageurl(),
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getIsstudy(),
                            response.getData().getInfo().get(i).getIsvideo(),
                            response.getData().getInfo().get(i).getAmount(),
                            response.getData().getInfo().get(i).getPurview()));
                }
                adapter.setDataList(itemList);

            }
        }, map, "加载中", page);
    }

}