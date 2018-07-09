package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.dialog.RuleDialog;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.study.StudyDetailsEntity;
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
    private String dUpId;

    @BindView(R.id.study_details_down)
    TextView studyDetailsDown;
    private String dDownId;

    @BindView(R.id.study_details_scrollviewext)
    ScrollViewExt studyDetailsScrollviewext;
    @BindView(R.id.zhanwei)
    View zhanwei;

    private Drawable fabulousNo = AppUtils.getDrawable(R.drawable.fabulous);

    private Drawable fabulousYes = AppUtils.getDrawable(R.drawable.fabulous_yes);


    //19大 , 2学1做 , 党务工作，
    private String cid;
    private String id;

    // private boolean fabulousType;

    private String text;

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

    //记录id
    private String logId;


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


        getEntity();

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_study_detail;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        upLog(logId, currentSecond + "");
    }

    @Override
    public void onLeftClick() {

        upLog(logId, currentSecond + "");
    }

    @Override
    public void onRightClick() {

    }


    @OnClick({R.id.study_details_fabulous, R.id.study_details_rule, R.id.study_details_up, R.id.study_details_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.study_details_fabulous:
                //点赞
                Function.fabulous(isLogin, this, studyDetailsFabulous, id, "5");

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
                if (!dUpId.equals("0")) {
                    upLog(logId, currentSecond + "");
                    startActivity(new Intent(this, StudyDetailActivity.class).putExtra("cid", cid).putExtra("id", dUpId));
                }


                break;
            case R.id.study_details_down:
                if (!dDownId.equals("0")) {
                    upLog(logId, currentSecond + "");
                    startActivity(new Intent(this, StudyDetailActivity.class).putExtra("cid", cid).putExtra("id", dDownId));
                }


                //继续
                isPause = false;
                //开启计时
                timeRunable.run();

                Log.e("eeeeee", "下一个" + "  " + currentSecond);
                break;
        }
    }

    public void getEntity() {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("id", id);

        OkHttpUtil.getInstance(this).doPost(AddressRequest.STUDY_DETAILES, new OkHttpUtil.ResultCallback<BaseEntity<StudyDetailsEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<StudyDetailsEntity> response) {
                studyDetailsTitle.setText(response.getData().getMain().getTitle());
                studyDetailsTimeIn.setText("发布时间：" + response.getData().getMain().getAddtime());

                if (response.getData().getMain().getMelike().equals("0")) {
                    studyDetailsFabulous.setCompoundDrawables(fabulousNo, null, null, null);
                } else {
                    studyDetailsFabulous.setCompoundDrawables(fabulousYes, null, null, null);
                }
                studyDetailsFabulous.setText(response.getData().getMain().getIlike());
                studyDetailsBrowse.setText(response.getData().getMain().getHits());
                text = response.getData().getMain().getContent();
                studyDetailsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);

                logId = response.getData().getMain().getLogid();

                dUpId = response.getData().getPerv().getId();
                studyDetailsUp.setText("上一篇：" + response.getData().getPerv().getTitle());

                dDownId = response.getData().getNext().getId();
                studyDetailsDown.setText("下一篇：" + response.getData().getNext().getTitle());
                zhanwei.setVisibility(View.GONE);

                //开启计时
                timeRunable.run();
            }
        }, map, "");

    }

    private void upLog(String logId, String longTime) {
        onBack();
        //停止
        isPause = true;

        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("id", id);
        map.put("logid", logId);
        map.put("seetime", longTime);
        OkHttpUtil.getInstance(this).doPost(AddressRequest.STUDY_UPLOG, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<String> response) {

            }
        }, map);

    }

}
