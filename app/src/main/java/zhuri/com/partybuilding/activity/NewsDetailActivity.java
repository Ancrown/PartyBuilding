package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.NewsDetailsEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.htmlutils.HtmlFormat;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.ScrollViewExt;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/31
 * 描述:
 */

public class NewsDetailActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {

    @BindView(R.id.news_details_title)
    TextView newsDetailsTitle;
    @BindView(R.id.news_details_time_in)
    TextView newsDetailsTimeIn;
    @BindView(R.id.news_details_fabulous)
    TextView newsDetailsFabulous;


    @BindView(R.id.news_details_browse)
    TextView newsDetailsBrowse;
    @BindView(R.id.news_details_forward)
    TextView newsDetailsForward;
    @BindView(R.id.news_details_zan_dian_zhuan)
    LinearLayout newsDetailsZanDianZhuan;
    @BindView(R.id.news_details_zhuan)
    LinearLayout newsDetailsZhuan;

    private UMWeb web;

    //分享路径
    private String shareURL;
    //首图
    private String shareImageurl;
    //title
    private String shareTitle;
    //描述
    private String shareDemo;


    @BindView(R.id.news_details_content)
    WebView newsDetailsContent;

    @BindView(R.id.news_details_scrollviewext)
    ScrollViewExt newsDetailsScrollviewext;


    private String id;
    private String fabulousType;

    private String text = "<p style=\"text-indent:2em;\"> <strong>央广网北京4月20日消息 2018年4月17日-19日</strong>，由<span style=\"color:#E53333;\">中国石油学会、中国石油、</span>中国石化、中国海油、中国中化、延长石油联合主办，以“大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化”为主题的“2018中国石油石化企业信息技术交流大会暨展示会”在北京成功召开。会议围绕进一步推进我国石油石化企业两化深度融合，全面提升企业数字化、网络化、智能化水平和网络安全能力展开交流研讨。 </p> <p style=\"text-indent:2em;\"> 近年来，我国石油石化企业积极顺应信息技术发展趋势，围绕主营业务提质增效、转型升级，大力开展信息化建设和应用，以信息化驱动现代化，加快推进智慧油田、智慧工厂、智慧管道、智慧加油站建设，努力开创信息化推动企业数字化转型、智能化发展的新时代。 </p> <p> <img src=\"http://www.rifengsy.com/UploadFiles/2016012313132086730.jpg\" alt=\"\" /> </p><video width=\"100%\"  controls=\"controls\"> <source src=\" \" type=\"video/ogg\"> <source src=\"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/118064948_28fd2f3b834a56b5c1c14a471f77e75b_0.mp4\" type=\"video/mp4\"> Your browser does not support the video tag. </video> <p style=\"text-indent:2em;\"> 本次大会以大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化为主题，深度探讨搭建共享服务平台、大数据技术应用、物联网、互联网+行动计划、移动应用、网络安全解决方案、信息系统应用等技术，广泛交流推广、集中展示各单位在工业互联、共享智能、网络安全等方面取得的新成果、新进展，为“十三五”信息化规划与目标任务的全面实现提供了保障。旨在通过交流研讨，大力推进能源行业工业互联网的建设应用，促进传统产业转型升级；有效促进行业间、企业间的广泛合作，共同推动石油石化企业依靠信息技术实现创新发展，不断提升企业现代化管理水平、可持续发展能力。 </p> <p style=\"text-indent:2em;\"> 会议期间，石化行业专家围绕智慧油田、智慧工厂、智慧管道、智慧加油站、云计算与大数据、网络安全等主题进行了深入研讨，内容丰富、观点新颖，受到了与会各方的广泛好评。同时，会议期间还举办了新技术、新成果、新设备应用推广与展示，为一百多家信息技术服务商提供了展台，充分展现了石油石化行业创新发展的良好形象。 </p>";

    private Drawable fabulousNo = AppUtils.getDrawable(R.drawable.fabulous);

    private Drawable fabulousYes = AppUtils.getDrawable(R.drawable.fabulous_yes);


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        newsDetailsScrollviewext.setScrollViewListener(new ScrollViewExt.IScrollChangedListener() {
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
        getTitleView().setData("新闻详情", 0, R.drawable.back, null, 0, null, this);
        id = getIntent().getStringExtra("id");
        //   newsDetailsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);
        getEntity();
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_news_detail;
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
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("id", id);

        OkHttpUtil.getInstance(this).doPost(AddressRequest.NEWS_DETAIL, new OkHttpUtil.ResultCallback<BaseEntity<NewsDetailsEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<NewsDetailsEntity> response) {

                if (response.isStatus()) {
                    newsDetailsTitle.setText(response.getData().getTitle());
                    newsDetailsTimeIn.setText(TimeUtil.stampToDate(response.getData().getAddtime(), "yyyy-MM-dd HH:mm:ss")
                            + "  来源：" + response.getData().getSource());
                    newsDetailsFabulous.setText(response.getData().getIlike());


                    newsDetailsBrowse.setText(response.getData().getHits());
                    newsDetailsForward.setText(response.getData().getShare());
                    text = response.getData().getContent();


                    newsDetailsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);

                    fabulousNo.setBounds(0, 0, fabulousNo.getMinimumWidth(), fabulousNo.getMinimumHeight());
                    fabulousYes.setBounds(0, 0, fabulousYes.getMinimumWidth(), fabulousYes.getMinimumHeight());

                    if (response.getData().getMelike().equals("0")) {
                        newsDetailsFabulous.setCompoundDrawables(fabulousNo, null, null, null);
                    } else {
                        newsDetailsFabulous.setCompoundDrawables(fabulousYes, null, null, null);
                    }
                    newsDetailsZanDianZhuan.setVisibility(View.VISIBLE);
                    newsDetailsZhuan.setVisibility(View.VISIBLE);

                    shareURL = response.getData().getShareurl();
                    shareImageurl = response.getData().getImageurl();
                    shareTitle = response.getData().getTitle();
                    shareDemo = response.getData().getDemo();

                    //分享的内容
                    web = new UMWeb(shareURL);
                    web.setTitle(shareTitle);//标题
                    web.setThumb(new UMImage(NewsDetailActivity.this, shareImageurl));  //缩略图
                    web.setDescription(shareDemo);//描述

                }
            }
        }, map, "");

    }


    @OnClick({R.id.news_details_fabulous, R.id.news_details_browse, R.id.news_details_forward, R.id.news_details_wechat, R.id.news_details_wechat_pengyouquan, R.id.news_details_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.news_details_fabulous:
                //点赞
                Function.fabulous(isLogin, this, newsDetailsFabulous, id, "1");
                break;
            case R.id.news_details_browse:
                break;
            case R.id.news_details_forward:
                break;
            case R.id.news_details_wechat:
                Log.e("eeeee", "微信");
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(web)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.news_details_wechat_pengyouquan:
                Log.e("eeeee", "微信——朋友圈");
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(web)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.news_details_qq:
                Log.e("eeeee", "QQ");
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(web)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(NewsDetailActivity.this, "分享成功！", Toast.LENGTH_LONG).show();
            Function.forward(isLogin, NewsDetailActivity.this, newsDetailsForward, id, "1");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(NewsDetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(NewsDetailActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


}
