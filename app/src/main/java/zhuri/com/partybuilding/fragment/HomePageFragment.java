package zhuri.com.partybuilding.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.ExaminationActivity;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.base.BaseRecyclerFragment;
import zhuri.com.partybuilding.bean.BroadcastBean;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.HomeEntity;
import zhuri.com.partybuilding.twinklingrefreshlayout.RefreshListenerAdapter;
import zhuri.com.partybuilding.twinklingrefreshlayout.TwinklingRefreshLayout;
import zhuri.com.partybuilding.twinklingrefreshlayout.header.progresslayout.ProgressLayout;
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

public class HomePageFragment extends BaseRecyclerFragment implements TranslucentActionBar.ActionBarClickListener {
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
        getTitleView().setData(getResources().getString(R.string.app_name), 0, R.drawable.dian_dian_dian, null, R.drawable.plus, null, this);

        setupListView();
    }

    @Override
    public void refreshData() {
        //在线答题
        onlineAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExaminationActivity.class));
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

    @Override
    public void onResume() {
        super.onResume();
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
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(5)));
        adapter = new HomePageAdapter(true, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setHeadHolder(exHeader);
        getdata();


    }

    //数据
    public void getdata() {

        //轮播图
        imageResIds = new ArrayList<>();
        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925682&di=d12775547359b0c44d837f51f02e6518&imgtype=0&src=http%3A%2F%2Fu5.qiyipic.com%2Fimage%2F20170815%2F9c%2F5d%2Fuv_3066296689_m_601_480_270.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=51e62c9a9f63768cbb089f90668a17a2&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201601%2F06%2F20160106173647_yiXdf.jpeg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=f92d19cc97cb850b93d622c248a9327a&imgtype=0&src=http%3A%2F%2Fi2.qhmsg.com%2Ft01e909ef83b1591352.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=ae1442d7c0da3969ab98dfcdf8bf2a31&imgtype=0&src=http%3A%2F%2Fp2.qhmsg.com%2Ft01fcf832a92d4bd986.jpg");

        contentDescs = new ArrayList<>();
        contentDescs.add("巩俐不低俗，我就不能低俗");
        contentDescs.add("扑树又回来啦！再唱经典老歌引万人大合唱");
        contentDescs.add("揭秘北京电影如何升级");
        contentDescs.add("乐视网TV版大派送");
        contentDescs.add("热血屌丝的反杀");
        bView.text = "A";
        broadcastBean = new BroadcastBean(null, imageResIds, contentDescs);
        bView.setData(broadcastBean);

        //轮播文字
        info = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            info.add("这里是热门头条" + i);
        }

        marqueeview.startWithList(info);

        //item数据
        itemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            itemList.add(new HomePageItemBean(i + ""
                    , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_" +
                    "10000&sec=1527142406419&di=bcdf4024750e8647e876f6df31334935&imgt" +
                    "ype=0&src=http%3A%2F%2Fjiweixin168.com%2FUploads%2F2015%2F12%2F17%" +
                    "2F56725e9c1ebac.png"
                    , "党中央", "习近平书记说:XXXXXXX"
                    , "2017年12月13日，中共中央总书记、国家主席、中央军委主席习近平到第71集团军视察。这是习近平同“王杰班”战士合影。"
                    , "2018-5-24 11:27", "100", "999+", i % 2 + ""));
        }
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

    @Override
    public void onPause() {
        super.onPause();
        bView.setRunning(false);
    }


    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        if (PermissionManager.permissionApplicationF(this, PermissionManager.Camera(), PermissionManager.PERMISSION)) {
            Intent intent2 = new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent2, CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA);
        }

    }

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
