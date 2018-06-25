package zhuri.com.partybuilding.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.AnswerAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.TextViewUitl;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述: 考试结果页
 */

public class AnswerResultActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.answer_score)
    TextView answerScore;

    @BindView(R.id.answer_text)
    TextView text;

    private AnswerAdapter adapter;


    //题
    private List<AnswerBean> answerBeanList;

    //得分
    private String score;


    //总题数
    private String amount;
    //错题数
    private String amountError;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("考试结果", 0, R.drawable.back, null, 0, null, this);

        answerBeanList = (List<AnswerBean>) getIntent().getSerializableExtra("list");
        score = getIntent().getStringExtra("score");
        amount = answerBeanList.size() + "";
        amountError = getIntent().getStringExtra("amountError");

        answerScore.setText("得分:" + score + "分");
        text.setText("总共：" + amount + "题   " + amountError + "题错");
        TextViewUitl.toStringChangeColor(text.getText().toString(), amountError + "题错", "#d73734", text);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycler.setLayoutManager(llm);
        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));

        adapter = new AnswerAdapter(this, 0);
        recycler.setAdapter(adapter);
        Log.e("eeeeee ", "answerBeanList   " + answerBeanList.get(0).getTitle());
        adapter.setDataList(answerBeanList);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_answer_result;
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }


}
