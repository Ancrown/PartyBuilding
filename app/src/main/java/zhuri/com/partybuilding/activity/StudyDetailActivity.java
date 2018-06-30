package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.dialog.RuleDialog;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.htmlutils.HtmlFormat;
import zhuri.com.partybuilding.view.ScrollViewExt;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/25
 * 描述: 学习
 */

public class StudyDetailActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.study_details_title)
    TextView studyDetailsTitle;
    @BindView(R.id.study_details_time_in)
    TextView studyDetailsTimeIn;
    @BindView(R.id.study_details_fabulous)
    TextView studyDetailsFabulous;
    @BindView(R.id.study_details_browse)
    TextView studyDetailsBrowse;
    @BindView(R.id.study_details_content)
    WebView studyDetailsContent;
    @BindView(R.id.study_details_jishi)
    TextView studyDetailsJishi;
    @BindView(R.id.study_details_rule)
    TextView studyDetailsRule;
    @BindView(R.id.study_details_up)
    TextView studyDetailsUp;
    @BindView(R.id.study_details_down)
    TextView studyDetailsDown;
    @BindView(R.id.study_details_scrollviewext)
    ScrollViewExt studyDetailsScrollviewext;


    private Drawable fabulousNo = AppUtils.getDrawable(R.drawable.fabulous);

    private Drawable fabulousYes = AppUtils.getDrawable(R.drawable.fabulous_yes);


    //19大 , 2学1做 , 党务工作，
    private String cid;
    private String id;

    private boolean fabulousType;

    private String text = "<p style=\"text-indent:2em;\"> <strong>央广网北京4月20日消息 2018年4月17日-19日</strong>，由<span style=\"color:#E53333;\">中国石油学会、中国石油、</span>中国石化、中国海油、中国中化、延长石油联合主办，以“大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化”为主题的“2018中国石油石化企业信息技术交流大会暨展示会”在北京成功召开。会议围绕进一步推进我国石油石化企业两化深度融合，全面提升企业数字化、网络化、智能化水平和网络安全能力展开交流研讨。 </p> <p style=\"text-indent:2em;\"> 近年来，我国石油石化企业积极顺应信息技术发展趋势，围绕主营业务提质增效、转型升级，大力开展信息化建设和应用，以信息化驱动现代化，加快推进智慧油田、智慧工厂、智慧管道、智慧加油站建设，努力开创信息化推动企业数字化转型、智能化发展的新时代。 </p> <p> <img src=\"http://www.rifengsy.com/UploadFiles/2016012313132086730.jpg\" alt=\"\" /> </p><video width=\"100%\"  controls=\"controls\"> <source src=\" \" type=\"video/ogg\"> <source src=\"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/118064948_28fd2f3b834a56b5c1c14a471f77e75b_0.mp4\" type=\"video/mp4\"> Your browser does not support the video tag. </video> <p style=\"text-indent:2em;\"> 本次大会以大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化为主题，深度探讨搭建共享服务平台、大数据技术应用、物联网、互联网+行动计划、移动应用、网络安全解决方案、信息系统应用等技术，广泛交流推广、集中展示各单位在工业互联、共享智能、网络安全等方面取得的新成果、新进展，为“十三五”信息化规划与目标任务的全面实现提供了保障。旨在通过交流研讨，大力推进能源行业工业互联网的建设应用，促进传统产业转型升级；有效促进行业间、企业间的广泛合作，共同推动石油石化企业依靠信息技术实现创新发展，不断提升企业现代化管理水平、可持续发展能力。 </p> <p style=\"text-indent:2em;\"> 会议期间，石化行业专家围绕智慧油田、智慧工厂、智慧管道、智慧加油站、云计算与大数据、网络安全等主题进行了深入研讨，内容丰富、观点新颖，受到了与会各方的广泛好评。同时，会议期间还举办了新技术、新成果、新设备应用推广与展示，为一百多家信息技术服务商提供了展台，充分展现了石油石化行业创新发展的良好形象。 </p>";


    /*****************计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            if (!isPause) {
                currentSecond = currentSecond + 1000;
                studyDetailsJishi.setText("正在阅读：" + TimeUtil.getFormatHMS(currentSecond));
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandle.postDelayed(this, 1000);
            }
        }
    };
    //计时器
    private Handler mhandle = new Handler();
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mhandle.removeCallbacksAndMessages(null);
    }

    /*****************计时器*******************/


    @Override
    protected void initData() {
        fabulousNo.setBounds(0, 0, fabulousNo.getMinimumWidth(), fabulousNo.getMinimumHeight());
        fabulousYes.setBounds(0, 0, fabulousYes.getMinimumWidth(), fabulousYes.getMinimumHeight());

    }

    @Override
    protected void initListener() {
        studyDetailsScrollviewext.setScrollViewListener(new ScrollViewExt.IScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                Log.e("eeeeee", "滑动底部了");
            }

            @Override
            public void onScrolledToTop() {
                Log.e("eeeeee", "滑动头部了");
            }
        });
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();

        cid = intent.getStringExtra("cid");
        id = intent.getStringExtra("id");
        String title = null;
        switch (cid) {
            case "0":
                title = "十九大报告";
                break;
            case "1":
                title = "两学一做";
                break;
            case "2":
                title = "党务工作";
                break;
        }
        getTitleView().setData(title, 0, R.drawable.back, null, 0, null, this);
        studyDetailsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);
        //开启计时
        timeRunable.run();


    }

    @Override
    protected int getLayout() {
        return R.layout.aty_study_detail;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }


    @OnClick({R.id.study_details_fabulous, R.id.study_details_rule, R.id.study_details_up, R.id.study_details_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.study_details_fabulous:
                if (!fabulousType) {
                    studyDetailsFabulous.setCompoundDrawables(fabulousYes, null, null, null);
                } else {
                    studyDetailsFabulous.setCompoundDrawables(fabulousNo, null, null, null);
                }
                fabulousType = !fabulousType;
                break;
            case R.id.study_details_rule:
                RuleDialog dialog = new RuleDialog(this, R.style.custom_dialog);
                dialog.setTitle("积分获取");
                dialog.setText("一、如何获得积分？\n" +
                        "积分：会员通过团购或商城购物获得相应积分，并以积分享受不同程度的专属优惠券或实物礼品兑换（实物兑换后期上线）、抽奖及参与知我药妆网不定期举行的活动。\n" +
                        "\n" +
                        "\n" +
                        "二、获得积分规则\n" +
                        "登录商城购物 在商城或团购购物每消费1元，获得1积分，积分按实际消费取整后金额计算， 无封顶\n" +
                        "\n" +
                        "\n" +
                        "三、如何查询积分？\n" +
                        "在\"个人中心\"-\"我的积分\"里查看积分数值\n" +
                        "\n" +
                        "步骤：登陆知我药妆网—点击个人中心\n" +
                        "\n" +
                        "\n" +
                        "四、何时获得积分？\n" +
                        "购物获得积分：订单发货后，积分为冻结状态，新积分暂时不显示，确认收货后积分会自动充值到账户。\n" +
                        "\n" +
                        "\n" +
                        "五、如何使用积分？\n" +
                        "兑礼券 可享受各种优惠的红包或代金券，优惠券和红包自兑换起3天有效，不定期积分抽奖，只需手指轻轻一动，梦想大奖随时拿回家！");
                dialog.show();
                break;
            case R.id.study_details_up:
                //停止
                isPause = true;
                Log.e("eeeeee", "上一个" + "  " + currentSecond);

                break;
            case R.id.study_details_down:
                //继续
                isPause = false;
                //开启计时
                timeRunable.run();

                Log.e("eeeeee", "下一个" + "  " + currentSecond);
                break;
        }
    }
}
