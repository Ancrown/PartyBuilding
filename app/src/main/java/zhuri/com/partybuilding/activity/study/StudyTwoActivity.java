package zhuri.com.partybuilding.activity.study;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.study.StudyTwoAdapter;
import zhuri.com.partybuilding.adapter.study.StudyTwoSubitemAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.study.StudyTwoBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.study.StudyOneEntity;
import zhuri.com.partybuilding.entity.study.StudyTwoEntity;
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
 * 创建时间: 2018/7/7
 * 描述: 两学一座
 */

public class StudyTwoActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.study_two_subitem)
    RecyclerView studyTwoSubitem;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;


    private StudyTwoBean bean;
    private List<StudyTwoBean> itemList;
    private List<StudyTwoBean.SubitemBean> subitemList;
    private StudyTwoSubitemAdapter subitemAdapter;

    private StudyTwoAdapter adapter;

    //   private int page = 1;


    private LinearLayoutManager lmr;
    private LinearLayoutManager lmr1;

    //当前页的id
    private String indexId;
    //当前页
    private int index;


    @Override
    public void initView() {
        getTitleView().setData("两学一做", 0, R.drawable.back, null, 0, null, this);


        //是否允许越界时显示刷新控件（setOverScrollTopShow,setOverScrollBottomShow统一设置方法）
        refreshlayout.setOverScrollRefreshShow(false);
        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshlayout.setFloatRefresh(true);


        lmr1 = new LinearLayoutManager(this) {

        };
        //设置为垂直布局，这也是默认的
        lmr1.setOrientation(OrientationHelper.VERTICAL);

        //listview 效果
        lmr = new LinearLayoutManager(this);
        //设置为垂直布局，这也是默认的
        lmr.setOrientation(OrientationHelper.VERTICAL);

        //设置布局管理器
        studyTwoSubitem.setLayoutManager(lmr1);
        recycler.setLayoutManager(lmr);

        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));

        subitemAdapter = new StudyTwoSubitemAdapter(this);
        adapter = new StudyTwoAdapter(this, "1");

        studyTwoSubitem.setAdapter(subitemAdapter);
        recycler.setAdapter(adapter);
        itemList = new ArrayList<>();
        //  getdata();

        getEntity();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        subitemAdapter.setOnClickItem(new StudyTwoSubitemAdapter.OnClickItem() {
            @Override
            public void onItem(int i) {
                for (int z = 0; z < itemList.size(); z++) {
                    if (z == i) {
                        itemList.get(z).setType("0");
                        subitemList = itemList.get(z).getSubitem();
                        indexId = itemList.get(z).getSubitemid();
                        index = z;
                        // page = 1;
                    } else {
                        itemList.get(z).setType("1");
                    }
                }
                subitemAdapter.setDataList(itemList);
                adapter.setDataList(subitemList);
            }
        });
        //下拉上拉
        refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                //  Log.e("eeeee", "ListView" + "下拉");
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        itemList.get(index).page = 1;
//                        // getEntity("Refresh");
//                        endRefresh("Refresh");
//                    }
//                }, 1000);

                itemList.get(index).page = 1;
                getEntityFL("Refresh", indexId);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                Log.e("eeeee", "ListView" + "上拉");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        // getEntity("Loadmore");
//                        endRefresh("Loadmore");
//                    }
//                }, 1000);
                itemList.get(index).page++;
                getEntityFL("LoadMore", indexId);

            }

        });
    }


    @Override
    protected int getLayout() {
        return R.layout.aty_study_two;
    }


    public void getdata() {
        for (int i = 0; i < 3; i++) {
            subitemList = new ArrayList<>();
            for (int j = 0; j < i + 3; j++) {
                StudyTwoBean.SubitemBean itemBean = new StudyTwoBean.SubitemBean();
                itemBean.setId(j + "" + j);
                //  itemBean.setImageurl("http://jjxmt.cn/d/file/p/2018-06-26/ec063c9665dce18d33d25ef349661388.jpg");
                itemBean.setTitle("九江三中第五党支部召开“两学一做”专题学习会议");
                itemBean.setIsstudy(j % 2 + "");
                itemBean.setDemo("九江三中第五支部在党员活动室召开了“两学一做”专题学习会议，与会人员有：校党委委员、副校长黄玉华，支部书记方少俊，组织委员郭慧慧，宣传委员邹红爱以及第五支部全体党员。本次会议由支部书记方少俊支持。");
                itemBean.setPurview("0");
                subitemList.add(itemBean);
            }


            bean = new StudyTwoBean(i == 0 ? "0" : "1", i + "", "做合格党员", subitemList);
            itemList.add(bean);
        }
        subitemAdapter.setDataList(itemList);
        adapter.setDataList(itemList.get(0).getSubitem());
    }


    //根据手势 结束下拉上拉
    public void endRefresh(String gesture) {
        switch (gesture) {
            case "":
                break;
            case "Refresh":
                refreshlayout.finishRefreshing();
                break;
            case "LoadMore":
                refreshlayout.finishLoadmore();
                break;
        }
    }


    /**
     * 上来 显示的数据
     */
    public void getEntity() {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());

        //  map.put("page", itemList.get(index).page);
        OkHttpUtil.getInstance(this).doPost(AddressRequest.STUDY_TWO, new OkHttpUtil.ResultCallback<BaseEntity<List<StudyTwoEntity>>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<List<StudyTwoEntity>> response) {
                itemList.clear();
                for (int i = 0; i < response.getData().size(); i++) {
                    subitemList = new ArrayList<>();
                    for (int j = 0; j < response.getData().get(i).getItem().size(); j++) {
                        StudyTwoBean.SubitemBean itemBean = new StudyTwoBean.SubitemBean();
                        itemBean.setId(response.getData().get(i).getItem().get(j).getId());
                        //  itemBean.setImageurl("http://jjxmt.cn/d/file/p/2018-06-26/ec063c9665dce18d33d25ef349661388.jpg");
                        itemBean.setTitle(response.getData().get(i).getItem().get(j).getTitle());
                        itemBean.setIsstudy(response.getData().get(i).getItem().get(j).getIsstudy());
                        itemBean.setDemo(response.getData().get(i).getItem().get(j).getDemo());
                        itemBean.setPurview(response.getData().get(i).getItem().get(j).getPurview());
                        subitemList.add(itemBean);
                    }


                    bean = new StudyTwoBean(i == 0 ? "0" : "1", response.getData().get(i).getId(), response.getData().get(i).getName(), subitemList);
                    itemList.add(bean);
                }
                subitemAdapter.setDataList(itemList);
                adapter.setDataList(itemList.get(0).getSubitem());
                indexId = itemList.get(0).getSubitemid();
                index = 0;
            }
        }, map);
    }

    /**
     * 上拉下拉 调用
     *
     * @param gesture 手势
     * @param indexId 当前显示的子项 id
     */

    private void getEntityFL(final String gesture, final String indexId) {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("cid", indexId);
        map.put("page", itemList.get(index).page);

        OkHttpUtil.getInstance(this).doPost(AddressRequest.STUDY_ONE, new OkHttpUtil.ResultCallback<BaseEntity<StudyOneEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<StudyOneEntity> response) {
                endRefresh(gesture);
                if (itemList.get(index).page <= 1) {
                    subitemList = new ArrayList<>();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    StudyTwoBean.SubitemBean itemBean = new StudyTwoBean.SubitemBean();
                    itemBean.setId(response.getData().getInfo().get(i).getId());
                    itemBean.setTitle(response.getData().getInfo().get(i).getTitle());
                    itemBean.setIsstudy(response.getData().getInfo().get(i).getIsstudy());
                    itemBean.setDemo(response.getData().getInfo().get(i).getDemo());
                    itemBean.setPurview(response.getData().getInfo().get(i).getPurview());
                    subitemList.add(itemBean);
                }

                itemList.get(index).setSubitem(subitemList);

                adapter.setDataList(itemList.get(index).getSubitem());

            }
        }, map);
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}

