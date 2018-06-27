package zhuri.com.partybuilding.activity.activitiesdetail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.activities.ActivitiesOneActivity;
import zhuri.com.partybuilding.activity.activities.ActivitiesTwoActivity;
import zhuri.com.partybuilding.adapter.ActivitiesAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.htmlutils.HtmlFormat;
import zhuri.com.partybuilding.util.permission.PermissionManager;
import zhuri.com.partybuilding.view.NoScrollWebView;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;
import zhuri.com.partybuilding.zbarcode.CaptureActivity;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/26
 * 描述: 活动详情
 */

public class ActivitiesDetail extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.activities_detail_title)
    TextView activitiesDetailTitle;
    @BindView(R.id.activities_detail_sendtime)
    TextView activitiesDetailSendtime;
    @BindView(R.id.activities_detail_webview)
    NoScrollWebView activitiesDetailWebview;
    @BindView(R.id.activities_detail_time)
    TextView activitiesDetailTime;
    @BindView(R.id.activities_detail_address)
    TextView activitiesDetailAddress;
    @BindView(R.id.activities_detail_signendtime)
    TextView activitiesDetailSignendtime;
    @BindView(R.id.activities_detail_peoplenum)
    TextView activitiesDetailPeoplenum;
    @BindView(R.id.activities_detail_signup)
    TextView activitiesDetailSignup;
    @BindView(R.id.activities_detail_more)
    TextView activitiesDetailMore;
    @BindView(R.id.recycler)
    RecyclerView recycler;


    private String text = "<p style=\"text-indent:2em;\"> <strong>央广网北京4月20日消息 2018年4月17日-19日</strong>，由<span style=\"color:#E53333;\">中国石油学会、中国石油、</span>中国石化、中国海油、中国中化、延长石油联合主办，以“大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化”为主题的“2018中国石油石化企业信息技术交流大会暨展示会”在北京成功召开。会议围绕进一步推进我国石油石化企业两化深度融合，全面提升企业数字化、网络化、智能化水平和网络安全能力展开交流研讨。 </p> <p style=\"text-indent:2em;\"> 近年来，我国石油石化企业积极顺应信息技术发展趋势，围绕主营业务提质增效、转型升级，大力开展信息化建设和应用，以信息化驱动现代化，加快推进智慧油田、智慧工厂、智慧管道、智慧加油站建设，努力开创信息化推动企业数字化转型、智能化发展的新时代。 </p> <p> <img src=\"http://www.rifengsy.com/UploadFiles/2016012313132086730.jpg\" alt=\"\" /> </p><video width=\"100%\"  controls=\"controls\"> <source src=\" \" type=\"video/ogg\"> <source src=\"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/118064948_28fd2f3b834a56b5c1c14a471f77e75b_0.mp4\" type=\"video/mp4\"> Your browser does not support the video tag. </video> <p style=\"text-indent:2em;\"> 本次大会以大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化为主题，深度探讨搭建共享服务平台、大数据技术应用、物联网、互联网+行动计划、移动应用、网络安全解决方案、信息系统应用等技术，广泛交流推广、集中展示各单位在工业互联、共享智能、网络安全等方面取得的新成果、新进展，为“十三五”信息化规划与目标任务的全面实现提供了保障。旨在通过交流研讨，大力推进能源行业工业互联网的建设应用，促进传统产业转型升级；有效促进行业间、企业间的广泛合作，共同推动石油石化企业依靠信息技术实现创新发展，不断提升企业现代化管理水平、可持续发展能力。 </p> <p style=\"text-indent:2em;\"> 会议期间，石化行业专家围绕智慧油田、智慧工厂、智慧管道、智慧加油站、云计算与大数据、网络安全等主题进行了深入研讨，内容丰富、观点新颖，受到了与会各方的广泛好评。同时，会议期间还举办了新技术、新成果、新设备应用推广与展示，为一百多家信息技术服务商提供了展台，充分展现了石油石化行业创新发展的良好形象。 </p>";


    private String type;
    private String id;


    private ActivitiesAdapter adapter;
    private List<ActivitiesItemBean> itemList;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        id = intent.getStringExtra("id");
        String title = null;
        switch (type) {
            case "0":
                title = "社区活动";
                break;
            case "1":
                title = "微志愿";
                break;

        }
        getTitleView().setData(title, 0, R.drawable.back, null, R.drawable.saoyisao, null, this);


        activitiesDetailWebview.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);


        //listview 效果
        LinearLayoutManager lmr = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        //设置为垂直布局，这也是默认的
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recycler.setLayoutManager(lmr);


        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new ActivitiesAdapter(this, type);


        recycler.setAdapter(adapter);
        itemList = new ArrayList<>();
        getdata();
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_activities_detail_head;
    }

    public void getdata() {
        itemList.add(new ActivitiesItemBean(
                "0",
                "郑州市洛阳商会开展迎七一“三新”党建活动。",
                "http://img3.utuku.china.com/600x0/henan/20180626/780e3df8-073b-48b0-b2b9-c95ef4238a45.jpg",
                "河南郑州市洛阳",
                "2018-06-27 08:52:57",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "1",
                "为深入学习宣传贯彻党的十九大精神，推到广大党员群众学习新思想、掌握新本领、创造新业绩，郑州市洛阳商会党总支积极响应洛阳市委组织部号召，建设新时代“三新”讲习所。",
                "http://img3.utuku.china.com/600x0/henan/20180626/780e3df8-073b-48b0-b2b9-c95ef4238a45.jpg",
                "辽宁沈阳平和平区",
                "2018-06-25 09:52:57",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));

        itemList.add(new ActivitiesItemBean(
                "2",
                "上海这个新建白领中心与全国首批开发区为邻， 让党建活动告别“土味”。",
                "https://images.shobserver.com/news/690_390/2018/6/26/1e270dfd-644c-4af3-9892-7f4ab43244d5.jpg#https://images.shobserver.com/news/news/2018/6/26/11a6eaf3-5bb8-4f2a-be91-9326f81ecab9.jpg#https://images.shobserver.com/news/news/2018/6/26/3f2228c2-2689-4474-a02b-661c0e758d54.jpg",
                "上海虹桥白领中心位于红宝石路500号",
                "2018-06-26 16:38:00",
                "0",
                ((int) (Math.random() * 3)) + "",
                ((int) (Math.random() * 2)) + ""));
//        for (int i = 0; i < 3; i++) {
//
//            String img = "http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180626386413961154.jpg#";
//
//
//            for (int j = 0; j < ((int) (Math.random() * 3)); j++) {
//                img = img + "http://images.wenming.cn/web_djw/djw2016sy/djwbfq/201806/W020180626386413961154.jpg#";
//            }
//            itemList.add(new ActivitiesItemBean(
//                    i + "",
//                    "为喜迎十九大，山塘片区新庄联合市职工文体协会结合建党96周年等，举办猜谜解题受党教、沙画创作学党史、以画会友庆党建、漫画诙谐做党人、舞动新庄歌唱党等丰富多彩系列活动，通过丰富多彩的活动，为党的生日献一份礼，抒发社区群众永远跟党走的心声。 ",
//                    img,
//                    "辽宁省沈阳市和平区",
//                    "2018-7-1",
//                    i % 2 + "",
//                    ((int) (Math.random() * 3)) + "",
//                    ((int) (Math.random() * 2)) + ""));
//        }
        adapter.setDataList(itemList);
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {
        if (PermissionManager.permissionApplication(this, PermissionManager.Camera(), PermissionManager.PERMISSION)) {
            Intent intent2 = new Intent(this, CaptureActivity.class);
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
            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
        }
    }

    //授权回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0) return;
        int count = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                count++;
            }
        }
        if (count == grantResults.length) {
            if (requestCode == PermissionManager.PERMISSION) {
                Intent intent2 = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent2, CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            if (requestCode == PermissionManager.PERMISSION) {
                PermissionManager.showDialog(this,
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'相机)");
            }

        }
    }


    @OnClick({R.id.activities_detail_signup, R.id.activities_detail_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activities_detail_signup:

                break;
            case R.id.activities_detail_more:
                if ("0".equals(type)) {
                    startActivity(new Intent(this, ActivitiesOneActivity.class));
                } else {
                    startActivity(new Intent(this, ActivitiesTwoActivity.class));
                }
                break;
        }
    }
}
