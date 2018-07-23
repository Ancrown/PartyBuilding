package zhuri.com.partybuilding.activity.activitiesdetail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.Function;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.entity.activities.ActivitiesCVDetailsEntity;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.htmlutils.HtmlFormat;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/27
 * 描述:
 */

public class ActivitiesReportDetailActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {

    @BindView(R.id.activities_report_title)
    TextView activitiesReportTitle;
    @BindView(R.id.activities_report_time_in)
    TextView activitiesReportTimeIn;
    @BindView(R.id.activities_report_fabulous)
    TextView activitiesReportFabulous;
    @BindView(R.id.activities_report_browse)
    TextView activitiesReportBrowse;
    @BindView(R.id.activities_report_address)
    TextView activitiesReportAddress;
    @BindView(R.id.activities_report_acttime)
    TextView activitiesReportActtime;
    @BindView(R.id.activities_report_people_num)
    TextView activitiesReportPeopleNum;
    @BindView(R.id.activities_report_host)
    TextView activitiesReportHost;
    @BindView(R.id.activities_report_content)
    WebView activitiesReportContent;
    //上一篇 id
    private String reportUpId;
    @BindView(R.id.activities_report_up)
    TextView activitiesReportUp;
    //下一篇 id
    private String reportDownId;
    @BindView(R.id.activities_report_down)
    TextView activitiesReportDown;
    @BindView(R.id.zhanwei)
    View zhanwei;
    private String type;

    private String id;

    private String text = "<p style=\"text-indent:2em;\"> <strong>央广网北京4月20日消息 2018年4月17日-19日</strong>，由<span style=\"color:#E53333;\">中国石油学会、中国石油、</span>中国石化、中国海油、中国中化、延长石油联合主办，以“大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化”为主题的“2018中国石油石化企业信息技术交流大会暨展示会”在北京成功召开。会议围绕进一步推进我国石油石化企业两化深度融合，全面提升企业数字化、网络化、智能化水平和网络安全能力展开交流研讨。 </p> <p style=\"text-indent:2em;\"> 近年来，我国石油石化企业积极顺应信息技术发展趋势，围绕主营业务提质增效、转型升级，大力开展信息化建设和应用，以信息化驱动现代化，加快推进智慧油田、智慧工厂、智慧管道、智慧加油站建设，努力开创信息化推动企业数字化转型、智能化发展的新时代。 </p> <p> <img src=\"http://www.rifengsy.com/UploadFiles/2016012313132086730.jpg\" alt=\"\" /> </p><video width=\"100%\"  controls=\"controls\"> <source src=\" \" type=\"video/ogg\"> <source src=\"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/118064948_28fd2f3b834a56b5c1c14a471f77e75b_0.mp4\" type=\"video/mp4\"> Your browser does not support the video tag. </video> <p style=\"text-indent:2em;\"> 本次大会以大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化为主题，深度探讨搭建共享服务平台、大数据技术应用、物联网、互联网+行动计划、移动应用、网络安全解决方案、信息系统应用等技术，广泛交流推广、集中展示各单位在工业互联、共享智能、网络安全等方面取得的新成果、新进展，为“十三五”信息化规划与目标任务的全面实现提供了保障。旨在通过交流研讨，大力推进能源行业工业互联网的建设应用，促进传统产业转型升级；有效促进行业间、企业间的广泛合作，共同推动石油石化企业依靠信息技术实现创新发展，不断提升企业现代化管理水平、可持续发展能力。 </p> <p style=\"text-indent:2em;\"> 会议期间，石化行业专家围绕智慧油田、智慧工厂、智慧管道、智慧加油站、云计算与大数据、网络安全等主题进行了深入研讨，内容丰富、观点新颖，受到了与会各方的广泛好评。同时，会议期间还举办了新技术、新成果、新设备应用推广与展示，为一百多家信息技术服务商提供了展台，充分展现了石油石化行业创新发展的良好形象。 </p>";


    final Drawable fabulousNo = AppUtils.getDrawable(R.drawable.fabulous);
    final Drawable fabulousYes = AppUtils.getDrawable(R.drawable.fabulous_yes);


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
            case "2":
                title = "活动报道";
                break;
            case "3":
                title = "微志愿报道";
                break;

        }

        getTitleView().setData(title, 0, R.drawable.back, null, 0, null, this);

        fabulousNo.setBounds(0, 0, fabulousNo.getMinimumWidth(), fabulousNo.getMinimumHeight());
        fabulousYes.setBounds(0, 0, fabulousYes.getMinimumWidth(), fabulousYes.getMinimumHeight());

        getEntity();

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_activities_report_detail;
    }


    @OnClick({R.id.activities_report_fabulous, R.id.activities_report_up, R.id.activities_report_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activities_report_fabulous:

                Function.fabulous(isLogin, this, activitiesReportFabulous, id, type);

                break;
            case R.id.activities_report_up:
                if (reportUpId.equals("0")) {
                } else {
                    startActivity(new Intent(this, ActivitiesReportDetailActivity.class).putExtra("type", type).putExtra("id", reportUpId));
                }
                break;
            case R.id.activities_report_down:
                if (reportDownId.equals("0")) {
                } else {
                    startActivity(new Intent(this, ActivitiesReportDetailActivity.class).putExtra("type", type).putExtra("id", reportDownId));
                }
                break;
        }
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }

    public void getEntity() {
        Map map = new HashMap();
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
        map.put("id", id);
        OkHttpUtil.getInstance(this).doPost(type.equals("2") ? AddressRequest.ACTIVITIES_C_DETAILS : AddressRequest.ACTIVITIES_V_DETAILS, new OkHttpUtil.ResultCallback<BaseEntity<ActivitiesCVDetailsEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<ActivitiesCVDetailsEntity> response) {

                activitiesReportTitle.setText(response.getData().getMain().getTitle());
                activitiesReportTimeIn.setText("发布时间：" + TimeUtil.stampToDate(response.getData().getMain().getAddtime(), "yyyy-mm-dd HH:mm:ss"));

                if (response.getData().getMain().getMelike().equals("0")) {
                    activitiesReportFabulous.setCompoundDrawables(fabulousNo, null, null, null);
                } else {
                    activitiesReportFabulous.setCompoundDrawables(fabulousYes, null, null, null);
                }
                activitiesReportFabulous.setText(response.getData().getMain().getIlike());
                activitiesReportBrowse.setText(response.getData().getMain().getHits());

                activitiesReportAddress.setText("地址：" + response.getData().getMain().getAddress());
                activitiesReportActtime.setText("活动时间：" + TimeUtil.stampToDate(response.getData().getMain().getStime(), "yyyy-mm-dd") + "~" +
                        TimeUtil.stampToDate(response.getData().getMain().getEtime(), "yyyy-mm-dd"));

                activitiesReportPeopleNum.setText("报名人数：" + response.getData().getMain().getSignup());
                activitiesReportHost.setText("举办者：" + response.getData().getMain().getDname());


                text = response.getData().getReport();
                activitiesReportContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);

                reportUpId = response.getData().getPerv().getId();
                activitiesReportUp.setText("上一篇：" + response.getData().getPerv().getTitle());

                reportDownId = response.getData().getNext().getId();
                activitiesReportDown.setText("上一篇：" + response.getData().getNext().getTitle());
                zhanwei.setVisibility(View.GONE);

            }
        }, map, "");
    }
}
