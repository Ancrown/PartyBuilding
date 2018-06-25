package zhuri.com.partybuilding.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.StudyActivity;
import zhuri.com.partybuilding.activity.activities.ActivitiesOneActivity;
import zhuri.com.partybuilding.activity.activities.ActivitiesThreeActivity;
import zhuri.com.partybuilding.activity.activities.ActivitiesTwoActivity;
import zhuri.com.partybuilding.activity.ExaminationActivity;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.BroadcastBean;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.HomeEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.util.permission.PermissionManager;
import zhuri.com.partybuilding.view.BroadcastView;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;
import zhuri.com.partybuilding.zbarcode.CaptureActivity;

/**
 * 首页
 */

public class HomePageFragment extends BaseRecyclerFragment  {
    //轮播图
    // @BindView(R.id.fra_home_bro)
    private BroadcastView bView;
    //轮播文字
    //  @BindView(R.id.fra_home_marqueeview)
    private MarqueeView marqueeview;
    //社区活动
    private RelativeLayout communityAactivities;
    //微志愿
    private RelativeLayout vVolunteer;
    //微心愿
    private RelativeLayout vWish;
    //在线答题
    private RelativeLayout onlineAnswer;
    //两学一做
    private RelativeLayout twoOne;
    //十九大精神
    private RelativeLayout nineteen;
    //党务工作指导
    private RelativeLayout workGuidance;
    //更多
    private TextView more;


//    @BindView(R.id.recycler)
//    RecyclerView recycler;
//    @BindView(R.id.refreshlayout)
//    TwinklingRefreshLayout refreshLayout;


    private HomePageAdapter adapter;
    private List<HomePageItemBean> itemList;

    private BroadcastBean broadcastBean;
    private List<String> broId;
    private List<String> imageResIds;
    private List<String> contentDescs;

    private List<String> info;
    private List<HomeEntity.VoteBean> voteBeanList;

//    @Override
//    public int getLayoutId() {
//        return R.layout.fra_homepage;
//    }

    @Override
    public void initView() {
        super.initView();
        //设置标题
        getTitleView().setData(getResources().getString(R.string.app_name), 0, 0, null, 0, null, null);

        setupListView();
    }

    @Override
    public void refreshData() {
        //社区活动
        communityAactivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivitiesOneActivity.class));
            }
        });
        //微志愿
        vVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivitiesTwoActivity.class));
            }
        });
        //微心愿
        vWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivitiesThreeActivity.class));
            }
        });
        //社区活动
        communityAactivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivitiesOneActivity.class));
            }
        });
        //在线答题
        onlineAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExaminationActivity.class));
            }
        });
        //两学一做
        twoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StudyActivity.class).putExtra("cid", "1"));
            }
        });
        //十九大精神
        nineteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StudyActivity.class).putExtra("cid", "0"));
            }
        });
        //党务工作
        workGuidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StudyActivity.class).putExtra("cid", "2"));
            }
        });

        //轮播图点击
        bView.setOnImgClick(new BroadcastView.OnImgClick() {
            @Override
            public void onItme(String id) {
                Log.e("eeeee", "position:" + id);
            }
        });

        //轮播文字点击
        marqueeview.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getActivity(), String.valueOf(marqueeview.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        //下拉上拉
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endRefresh("Refresh");
                    }
                }, 1000);
                page = 1;
                //getEntity("Refresh");
            }

//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                Log.e("eeeee", "ListView" + "上拉");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.finishLoadmore();
//                    }
//                }, 1000);
//       getEntity("Loadmore");
//
//            }

        });
    }


    private void setupListView() {

        View exHeader = View.inflate(getActivity(), R.layout.head_homepage, null);
        bView = exHeader.findViewById(R.id.fra_home_bro);
        marqueeview = exHeader.findViewById(R.id.fra_home_marqueeview);
        communityAactivities = exHeader.findViewById(R.id.fra_home_community_activities);
        vVolunteer = exHeader.findViewById(R.id.fra_home_v_volunteer);
        vWish = exHeader.findViewById(R.id.fra_home_v_wish);
        onlineAnswer = exHeader.findViewById(R.id.fra_home_online_answer);
        twoOne = exHeader.findViewById(R.id.fra_home_two_one);
        nineteen = exHeader.findViewById(R.id.fra_home_nineteen);
        workGuidance = exHeader.findViewById(R.id.fra_home_work_guidance);
        more = exHeader.findViewById(R.id.fra_home_more);


        //设置不允许上拉
        refreshLayout.setEnableLoadmore(false);
        //设置间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new HomePageAdapter(true, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setHeadHolder(exHeader);
        getdata();


    }

    //数据
    public void getdata() {

        //轮播图
        imageResIds = new ArrayList<>();
        imageResIds.add("http://new.syist.cn/public/home/images/sy.jpg");
        imageResIds.add("http://www.nsxf.cn/upload/2018/0605/92be28d16e4f46d88938f189c3681d5a.jpg");
        imageResIds.add("http://www.nsxf.cn/upload/2018/0605/640be637218b42738664351f6a120a03.jpg");
        imageResIds.add("http://www.nsxf.cn/upload/2018/0521/d845ed9eeb0d4672b8e62674ebf9fadc.jpg");
        imageResIds.add("http://new.syist.cn/public/home/images/sy.jpg");

        contentDescs = new ArrayList<>();
        contentDescs.add("党建网 - 中宣部主管全国性党建网站");
        contentDescs.add("人民网·中国共产党新闻网主办的全国性党建频道,传播党的声音,密切党群关系,推动党的工作,展现党的形象。");
        contentDescs.add("党的建设即马克思主义建党理论同党的建设实践的统一，马克思主义党的学说的应用");
        contentDescs.add("光明网党建频道_报道最新党建工作,党建研究新闻");
        contentDescs.add("党建工作有哪些方面");

        broadcastBean = new BroadcastBean(null, imageResIds, contentDescs);
        bView.setData(broadcastBean);

        //轮播文字
        info = new ArrayList<>();
        info.add("中共浙江盘石信息技术股份有限公司委员会");
        info.add("恭喜盘石股份党委被评委市级示范点（互联网业党建双强示范点)");
        info.add("关于召开《向解放军学习，打造盘石铁军》观看8.1建军节阅兵视频的活动");
        info.add("关于表彰2017年度盘石先进党支部及优秀党员的决定");
        info.add("盘石先进党支部及优秀党员评比的通知");


        marqueeview.startWithList(info);

        //item数据
        itemList = new ArrayList<>();
        itemList.add(new HomePageItemBean(1 + ""
                , "http://a4.peoplecdn.cn/cdd73b1852da4edbc70ca622c5208bae.jpg"
                , "", "习近平要求把这项工作作为重大政治任务 “没有比人更高的山,没有比脚更长的路。"
                , ""
                , "2018-5-24", "100", "999+", 0 % 2 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://a1.peoplecdn.cn/64a06ff0f3c188ab6edd29a824c3c5c2.jpg"
                , "党中央", "中共中央总书记、国家主席、中央军委主席习近平在山西太原市主持召开深度贫困地区脱贫攻坚座谈会并发表重要讲话。"
                , "2017年12月13日，中共中央总书记、国家主席、中央军委主席习近平到第71集团军视察。这是习近平同“王杰班”战士合影。"
                , "2017-6-23", "60", "52", 1 % 2 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://a2.peoplecdn.cn/51b4412cdaf821971abc8d3a5b5487d3.jpg"
                , "", "难干的事要派能干的人,有针对性地选配政治素质高、工作能力强、熟悉“三农”工作的干部担任贫困乡镇党政主要领导。"
                , ""
                , "2017-6-30", "5", "23", 2 % 2 + ""));
        itemList.add(new HomePageItemBean(1 + ""
                , "http://a4.peoplecdn.cn/43e27090904975c9abd514c5cb749548.jpg"
                , "", "传达学习贯彻习近平总书记重要讲话和重要指示精神"
                , ""
                , "2018-06-18", "66", "89", 3 % 2 + ""));


        adapter.setDataList(itemList);

    }

    private int page;

    //网络数据
    public void getEntity(final String gesture) {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TOKEN, ""));
        OkHttpUtil.getInstance(getActivity()).doPostList(AddressRequest.HOME, new OkHttpUtil.ResultCallback<BaseEntity<HomeEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                endRefresh(gesture);
            }

            @Override
            public void onResponse(BaseEntity<HomeEntity> response) {
                endRefresh(gesture);

                broId = new ArrayList<>();
                imageResIds = new ArrayList<>();
                contentDescs = new ArrayList<>();
                for (int i = 0; i < response.getData().getSlide().size(); i++) {
                    broId.add(response.getData().getSlide().get(i).getId());
                    imageResIds.add(response.getData().getSlide().get(i).getImageurl());
                    contentDescs.add(response.getData().getSlide().get(i).getTitle());
                }
                broadcastBean = new BroadcastBean(broId, imageResIds, contentDescs);
                bView.setData(broadcastBean);


                //轮播文字
                info = new ArrayList<>();
                voteBeanList = response.getData().getVote();
                for (int i = 0; i < voteBeanList.size(); i++) {
                    info.add(voteBeanList.get(i).getTitle());
                }

                marqueeview.startWithList(info);

                //item数据
                itemList = new ArrayList<>();
                for (int i = 0; i < response.getData().getInfo().size(); i++) {
                    itemList.add(new HomePageItemBean(response.getData().getInfo().get(i).getId(),
                            response.getData().getInfo().get(i).getImageurl(),
                            "",
                            response.getData().getInfo().get(i).getTitle(),
                            response.getData().getInfo().get(i).getDemo(),
                            response.getData().getInfo().get(i).getAddtime(),
                            response.getData().getInfo().get(i).getIlike(),
                            response.getData().getInfo().get(i).getHits(),
                            response.getData().getInfo().get(i).getPurview()));
                }
                adapter.setDataList(itemList);


            }
        }, map, "加载中", page);

    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeview.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeview.stopFlipping();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        bView.setRunning(true);
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        bView.setRunning(false);
//    }
//

//    @Override
//    public void onLeftClick() {
//
//    }
//
//    @Override
//    public void onRightClick() {
//        if (PermissionManager.permissionApplicationF(this, PermissionManager.Camera(), PermissionManager.PERMISSION)) {
//            Intent intent2 = new Intent(getActivity(), CaptureActivity.class);
//            startActivityForResult(intent2, CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA);
//        }
//
//    }

    //扫码回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA) {
            if (null == data) return;
            Bundle b = data.getExtras();
            String result = b.getString(CaptureActivity.EXTRA_STRING);
            Toast.makeText(getActivity(), result + "", Toast.LENGTH_SHORT).show();
        }
    }

    //授权回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("eeeee", "eeeeeeeeeeeeeeeeeeee" + requestCode);
        if (grantResults.length == 0) return;
        int count = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                count++;
            }
        }
        if (count == grantResults.length) {
            if (requestCode == PermissionManager.PERMISSION) {
                Intent intent2 = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent2, CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            if (requestCode == PermissionManager.PERMISSION) {
                PermissionManager.showDialog(getActivity(),
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'相机)");
            }

        }
    }
}
