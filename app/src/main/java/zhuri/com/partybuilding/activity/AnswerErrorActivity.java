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
        answerItemBeanList0.add(new AnswerItemBean("0", "选项XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
        answerItemBeanList0.add(new AnswerItemBean("1", "选项"));
        answerItemBeanList0.add(new AnswerItemBean("2", "选项"));
        answerItemBeanList0.add(new AnswerItemBean("3", "选项"));
        answerBeanList.add(new AnswerBean("1000", "1.你选那个选项", "0", answerItemBeanList0, "这个题应该选C", "2", "25"));

        answerItemBeanList1 = new ArrayList<>();
        answerItemBeanList1.add(new AnswerItemBean("0", "刘德华"));
        answerItemBeanList1.add(new AnswerItemBean("1", "刘晓伟"));
        answerBeanList.add(new AnswerBean("1001", "2.谁最高", "1", answerItemBeanList1, "刘晓伟最高", "1", "25"));

        answerItemBeanList2 = new ArrayList<>();
        answerItemBeanList2.add(new AnswerItemBean("0", "秦朝"));
        answerItemBeanList2.add(new AnswerItemBean("1", "隋朝"));
        answerItemBeanList2.add(new AnswerItemBean("2", "西晋"));
        answerItemBeanList2.add(new AnswerItemBean("3", "元朝"));
        answerBeanList.add(new AnswerBean("1002", "3.下列王朝中统治时间最短的是： ", "0", answerItemBeanList2, "秦朝最短！！！", "0", "25"));

        answerItemBeanList3 = new ArrayList<>();
        answerItemBeanList3.add(new AnswerItemBean("0", "1+9"));
        answerItemBeanList3.add(new AnswerItemBean("1", "2+8"));
        answerItemBeanList3.add(new AnswerItemBean("2", "4+4"));
        answerItemBeanList3.add(new AnswerItemBean("3", "5+5"));
        answerBeanList.add(new AnswerBean("1003", "4.那个等于10？", "1", answerItemBeanList3, "1+9 / 2+8 / 5+5", "0,1,3", "25"));


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
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
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
