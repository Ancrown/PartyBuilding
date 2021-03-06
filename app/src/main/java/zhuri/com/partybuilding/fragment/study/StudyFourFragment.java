package zhuri.com.partybuilding.fragment.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.LoginActivity;
import zhuri.com.partybuilding.adapter.ExaminationAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.ExaminationEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:考试系统
 */

public class StudyFourFragment extends BaseRecyclerFragment {


    @BindView(R.id.aty_examination_daikao)
    TextView atyExaminationDaikao;
    @BindView(R.id.aty_examination_yikao)
    TextView atyExaminationYikao;
    @BindView(R.id.aty_examination_go_login)
    TextView atyExaminationGoLogin;


    private ExaminationAdapter adapter;
    private List<ExaminationBean> list;

    private int page = 1;

    //分类 0 代考 1 自考
    private String cid = "0";

    @Override
    public int getLayoutId() {
        return R.layout.fra_examination;
    }

    @Override
    public void initView() {
        super.initView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setMargins(0, SizeUtils.dip2px(2), 0, 0);
        recyclerView.setLayoutParams(params);

        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ExaminationAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        list = new ArrayList<>();
        // getdata();
//        if (isLogin) {
//            startActivity(getActivity(), LoginActivity.class);
//        } else
//            getEntity(null);


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

    @Override
    public void onResume() {
        super.onResume();
        if (isLogin) {
            //没登录
//            ToolUtils.showToast(getActivity(), "游客不可看，请先登陆");
//            startActivity(new Intent(getActivity(), LoginActivity.class));
            atyExaminationGoLogin.setVisibility(View.VISIBLE);

        } else {
            atyExaminationGoLogin.setVisibility(View.GONE);
            getEntity(null);
        }


    }

    @Override
    public void refreshData() {

    }

    //  随机 1~4 ((int) ((Math.random() * 4) + 1) + "")
    public void getdata() {
        for (int i = 0; i < (cid.equals("0") ? 5 : 2); i++) {
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
                    i % 2 + "", "")
            );
        }
        adapter.setDataList(list);
    }

    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("page", page);
        map.put("cid", cid);


        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.EXAMINATION_LIST, new OkHttpUtil.ResultCallback<BaseEntity<ExaminationEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<ExaminationEntity> response) {
                endRefresh(gesture);
                if (page <= 1) {
                    list.clear();
                }
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    list.add(new ExaminationBean(
                            response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getAmount(),
                            response.getData().getInfo().get(i).getScore(),
                            response.getData().getInfo().get(i).getTimes(),
                            response.getData().getInfo().get(i).getStime(),
                            response.getData().getInfo().get(i).getEtime(),
                            response.getData().getInfo().get(i).getScore(),
                            cid,
                            "1",
                            response.getData().getInfo().get(i).getStatus(),
                            response.getData().getInfo().get(i).getAddtime()

                    ));
                }
                adapter.setDataList(list);

            }
        }, map, "加载中", page);
    }


    @OnClick({R.id.aty_examination_daikao, R.id.aty_examination_yikao, R.id.aty_examination_go_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aty_examination_daikao:
                atyExaminationDaikao.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_semicircle_left_orange_5));
                atyExaminationYikao.setBackground(null);
                cid = "0";
                list.clear();
                //getdata();
                page = 1;
                getEntity(null);
                break;
            case R.id.aty_examination_yikao:
                atyExaminationYikao.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_semicircle_right_orange_5));
                atyExaminationDaikao.setBackground(null);
                cid = "1";
                list.clear();
                //getdata();
                page = 1;
                getEntity(null);
                break;
            case R.id.aty_examination_go_login:
                startActivity(getActivity(), LoginActivity.class);
                break;
        }
    }


}
