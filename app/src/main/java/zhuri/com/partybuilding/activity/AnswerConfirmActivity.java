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
import zhuri.com.partybuilding.util.TextViewUitl;
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
    @BindView(R.id.answer_time_long)
    TextView answerTimeLong;
    @BindView(R.id.answer_time)
    TextView answerTime;
    @BindView(R.id.answer_content)
    TextView answerContent;
    @BindView(R.id.answer_start)
    Button answerStart;
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

        TextViewUitl.toStringChangeColor("考卷总分：" + intent.getStringExtra("score") + "分", "考卷总分：", "#535353", answerScore);
        TextViewUitl.toStringChangeColor("考题数量：" + intent.getStringExtra("amount") + "题", "考题数量：", "#535353", answerNum);
        TextViewUitl.toStringChangeColor("考卷时长：" + intent.getStringExtra("times") + "分", "考卷时长：", "#535353", answerTimeLong);
        TextViewUitl.toStringChangeColor("考试时间：" + intent.getStringExtra("stime") + "至" + intent.getStringExtra("etime"), "考试时间：", "#535353", answerTime);


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
