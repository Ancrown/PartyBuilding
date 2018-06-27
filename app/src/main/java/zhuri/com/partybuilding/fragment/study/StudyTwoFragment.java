package zhuri.com.partybuilding.fragment.study;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.study.StudyTwoAdapter;
import zhuri.com.partybuilding.adapter.study.StudyTwoSubitemAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.bean.study.StudyTwoBean;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:两学一做
 */

public class StudyTwoFragment extends BaseFragment {


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

    private int page;


    private LinearLayoutManager lmr;
    private LinearLayoutManager lmr1;

    @Override
    public int getLayoutId() {
        return R.layout.fra_study_two;
    }

    @Override
    public void initView() {


        //是否允许越界时显示刷新控件（setOverScrollTopShow,setOverScrollBottomShow统一设置方法）
        refreshlayout.setOverScrollRefreshShow(false);
        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshlayout.setFloatRefresh(true);


        lmr1 = new LinearLayoutManager(getActivity()) {

        };
        //设置为垂直布局，这也是默认的
        lmr1.setOrientation(OrientationHelper.VERTICAL);

        //listview 效果
        lmr = new LinearLayoutManager(getActivity());
        //设置为垂直布局，这也是默认的
        lmr.setOrientation(OrientationHelper.VERTICAL);

        //设置布局管理器
        studyTwoSubitem.setLayoutManager(lmr1);
        recycler.setLayoutManager(lmr);

        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));

        subitemAdapter = new StudyTwoSubitemAdapter(getActivity());
        adapter = new StudyTwoAdapter(getActivity(), "1");

        studyTwoSubitem.setAdapter(subitemAdapter);
        recycler.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();



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

    @Override
    public void refreshData() {
        subitemAdapter.setOnClickItem(new StudyTwoSubitemAdapter.OnClickItem() {
            @Override
            public void onItem(int i) {
                for (int z = 0; z < itemList.size(); z++) {
                    if (z == i) {
                        itemList.get(z).setType("0");
                        subitemList = itemList.get(z).getSubitem();
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
                Log.e("eeeee", "ListView" + "下拉");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        // getEntity("Refresh");
                        endRefresh("Refresh");
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "上拉");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        // getEntity("Loadmore");
                        endRefresh("Loadmore");
                    }
                }, 1000);


            }

        });
    }


    //根据手势 结束下拉上拉
    public void endRefresh(String gesture) {
        switch (gesture) {
            case "":
                break;
            case "Refresh":
                refreshlayout.finishRefreshing();
                break;
            case "Loadmore":
                refreshlayout.finishLoadmore();
                break;
        }
    }

}