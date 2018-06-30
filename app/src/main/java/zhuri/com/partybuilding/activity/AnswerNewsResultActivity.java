package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.AnswerNewsResultAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/28
 * 描述:
 */

public class AnswerNewsResultActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {

    @BindView(R.id.answer_news_result_title)
    TextView answerNewsResultTitle;
    @BindView(R.id.answer_news_result_my_score)
    TextView answerNewsResultMyScore;
    @BindView(R.id.answer_news_result_time)
    TextView answerNewsResultTime;
    @BindView(R.id.answer_news_beat)
    TextView answerNewsBeat;
    @BindView(R.id.answer_news_recy)
    RecyclerView answerNewsRecy;
    @BindView(R.id.answer_news_result_start)
    Button answerNewsResultStart;

    private AnswerNewsResultAdapter adapter;


    //题
    private List<AnswerBean> answerBeanList;

    //标题
    private String title;
    //得分
    private String score;


    //击败了
    private String recy;


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


        GridLayoutManager mgr = new GridLayoutManager(this, 10);
        answerNewsRecy.setLayoutManager(mgr);

        adapter = new AnswerNewsResultAdapter(this);
        answerNewsRecy.setAdapter(adapter);

        adapter.setDataList(answerBeanList);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_answer_news_result;
    }


    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }


    @OnClick(R.id.answer_news_result_start)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(this, AnswerResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) answerBeanList);//序列化,要注意转化(Serializable)
        intent.putExtras(bundle);//发送数据

        intent.putExtra("score", "");
        startActivity(intent);//启动intent
        onBack();
    }
}
