package zhuri.com.partybuilding.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.AnswerAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.base.BaseRecyclerActivity;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.bean.AnswerItemBean;
import zhuri.com.partybuilding.entity.AnswerErrorEntity;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.StringUtil;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述: 错题集
 */

public class AnswerErrorActivity extends BaseRecyclerActivity implements TranslucentActionBar.ActionBarClickListener {

    //题
    private List<AnswerBean> answerBeanList;
    private List<AnswerItemBean> answerItemBeanList;
    //选项
    private List<AnswerItemBean> answerItemBeanList0;
    private List<AnswerItemBean> answerItemBeanList1;
    private List<AnswerItemBean> answerItemBeanList2;
    private List<AnswerItemBean> answerItemBeanList3;

    private AnswerAdapter adapter;

    private int page;

    @Override
    protected void initData() {
        adapter = new AnswerAdapter(this);
        getData();
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        recyclerView.setAdapter(adapter);
        adapter.setDataList(answerBeanList);

    }

    private void getData() {
        answerBeanList = new ArrayList<>();
        answerItemBeanList0 = new ArrayList<>();
        answerItemBeanList0.add(new AnswerItemBean("0", "伟大斗争、伟大工程、伟大事业、伟大梦想 "));
        answerItemBeanList0.add(new AnswerItemBean("1", "伟大斗争、伟大建设、伟大事业、伟大梦想 "));
        answerItemBeanList0.add(new AnswerItemBean("2", "伟大斗争、伟大工程、伟大发展、伟大梦想"));
        answerItemBeanList0.add(new AnswerItemBean("3", "伟大斗争、伟大工程、伟大事业、伟大理想",1));
        answerBeanList.add(new AnswerBean("1000",
                "1、在习近平新时代中国特色社会主义思想指导下，中国共产党领导全国各族人民，统揽（），推动中国特色社会主义进入了新时代。",
                "0",
                answerItemBeanList0,
                "解析",
                "0",
                "3",
                false,
                "25"));

        answerItemBeanList1 = new ArrayList<>();
        answerItemBeanList1.add(new AnswerItemBean("0", "毛泽东思想",1));
        answerItemBeanList1.add(new AnswerItemBean("1", "马克思列宁主义"));
        answerItemBeanList1.add(new AnswerItemBean("2", "习近平新时代中国特色社会主义思想"));
        answerItemBeanList1.add(new AnswerItemBean("3", "“三个代表”重要思想"));
        answerBeanList.add(new AnswerBean("1001",
                "2、在（）指导下，中国共产党领导全国各族人民，统揽伟大斗争、伟大工程、伟大事业、伟大梦想，推动中国特色社会主义进入了新时代。",
                "0",
                answerItemBeanList1,
                "解析",
                "2",
                "0",
                false,
                "25"));

        answerItemBeanList2 = new ArrayList<>();
        answerItemBeanList2.add(new AnswerItemBean("0", "邓小平理论"));
        answerItemBeanList2.add(new AnswerItemBean("1", "三个代表”重要思想"));
        answerItemBeanList2.add(new AnswerItemBean("2", "科学发展观 ",1));
        answerItemBeanList2.add(new AnswerItemBean("3", "习近平新时代中国特色社会主义思想",1));
        answerBeanList.add(new AnswerBean("1002", "3、中国共产党以马克思列宁主义、毛泽东思想、（ ）作为自己的行动指南。  ",
                "1",
                answerItemBeanList2,
                "解析",
                "0,1,2,3",
                "2,3",
                false,
                "25"));

        answerItemBeanList3 = new ArrayList<>();
        answerItemBeanList3.add(new AnswerItemBean("0", "推进现代化建设"));
        answerItemBeanList3.add(new AnswerItemBean("1", "推进经济建设"));
        answerItemBeanList3.add(new AnswerItemBean("2", "完成祖国统一",1));
        answerItemBeanList3.add(new AnswerItemBean("3", "维护世界和平与促进共同发展",1));
        answerBeanList.add(new AnswerBean("1003",
                "4、全党同志要为实现（ ）、（ ）、（ ）这三大历史任务，实现“两个一百年”奋斗目标、实现中华民族伟大复兴的中国梦而奋斗。 ",
                "1",
                answerItemBeanList3,
                "解析",
                "0,2,3",
                "2,3",
                false,
                "25"));


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

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("page", page == 0 ? "1" : page + "");
        OkHttpUtil.getInstance(this).doPostList(AddressRequest.ERROR_ANSWER, new OkHttpUtil.ResultCallback<BaseEntity<AnswerErrorEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<AnswerErrorEntity> response) {
                endRefresh(gesture);

                if (page <= 0) {
                    answerBeanList.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    answerItemBeanList = new ArrayList<>();

                    for (int j = 0; j < response.getData().getInfo().get(i).getOptions().split(",").length; j++) {
                        answerItemBeanList.add(new AnswerItemBean(j + "", StringUtil.getLetter(j) + " " + response.getData().getInfo().get(i).getOptions().split(",")[j]));
                    }
                    answerBeanList.add(new AnswerBean(response.getData().getInfo().get(i).getId(),
                            i + ". " + response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getCtype(),
                            answerItemBeanList,
                            response.getData().getInfo().get(i).getTips(),
                            response.getData().getInfo().get(i).getAnswer(),
                            response.getData().getInfo().get(i).getMyanswer(),
                            Arrays.equals(response.getData().getInfo().get(i).getMyanswer().split(","), response.getData().getInfo().get(i).getAnswer().split(",")),
                            response.getData().getInfo().get(i).getScore()
                    ));
                }
            }
        }, map, "", page);
    }

    @Override
    protected void initView() {
        super.initView();
        getTitleView().setData("错题集", 0, R.drawable.back, null, 0, null, this);
    }


    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }
}
