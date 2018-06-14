package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述:
 */

public class AnswerConfirmActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {

    @BindView(R.id.answer_title)
    TextView answerTitle;

    @BindView(R.id.answer_score)
    TextView answerScore;

    @BindView(R.id.answer_num)
    TextView answerNum;
    @BindView(R.id.answer_times)
    TextView answerTimes;
    @BindView(R.id.answer_stime_etime)
    TextView answerStimeEtime;
    @BindView(R.id.answer_content)
    TextView answerContent;
    @BindView(R.id.answer_start)
    Button answerStart;
    @BindView(R.id.answer_integral)
    TextView answerIntegral;

    private Intent intent;

    private String id;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initView() {
        intent = getIntent();
        id = intent.getStringExtra("id");
        getTitleView().setData("在线考试", 0, R.drawable.back, null, 0, null, this);
        answerTitle.setText(intent.getStringExtra("title"));
        answerScore.setText("考卷总分：" + intent.getStringExtra("score") + "分");
        answerNum.setText("总题数：" + intent.getStringExtra("amount") + "题");
        answerTimes.setText("考卷时长：" + intent.getStringExtra("integral") + "分");
        answerIntegral.setText("获得积分：" + intent.getStringExtra("times") + "分钟");
        answerStimeEtime.setText(intent.getStringExtra("stime") + "至" + intent.getStringExtra("etime"));
        answerContent.setText(intent.getStringExtra("demo"));
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_answer_confirm;
    }


    @OnClick(R.id.answer_start)
    public void onViewClicked() {
        onBack();
        startActivity(new Intent(AnswerConfirmActivity.this, AnswerActivity.class).putExtra("id", id));
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }


}
