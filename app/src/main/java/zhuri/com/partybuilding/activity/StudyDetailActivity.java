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
                dialog.setTitle("积分获取说明");
                dialog.setText(AppUtils.getString(R.string.rule));
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
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
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
        map.put("uid", StaticVariables.getUserId());
        map.put("token", StaticVariables.getTOKEN());
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
