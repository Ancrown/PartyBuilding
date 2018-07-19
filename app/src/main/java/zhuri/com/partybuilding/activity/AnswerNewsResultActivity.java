package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.AnswerNewsResultAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.bean.AnswerItemBean;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.TimeUtil;
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
    //用时
    private long longTime;

    //击败了
    private String recy;

    private String type;


    private String id;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("考试结果", 0, R.drawable.back, null, 0, null, this);

        GridLayoutManager mgr = new GridLayoutManager(this, 10);
        answerNewsRecy.setLayoutManager(mgr);
        adapter = new AnswerNewsResultAdapter(this);
        answerNewsRecy.setAdapter(adapter);

        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        longTime = getIntent().getLongExtra("longTime", 1000*60*10);
        score = getIntent().getStringExtra("score");
        answerNewsResultTitle.setText(title);
        answerNewsResultTime.setText("答题用时：" + TimeUtil.getFormatHMS(longTime));
        answerNewsResultMyScore.setText(score);

        if (TextUtils.isEmpty(type)) {

            answerBeanList = (List<AnswerBean>) getIntent().getSerializableExtra("list");
            adapter.setDataList(answerBeanList);



        } else {

            id = getIntent().getStringExtra("id");
            getEntity();
        }
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

    public void getEntity() {

        answerBeanList = new ArrayList<>();
        List<AnswerItemBean> list = new ArrayList<>();
        list.add(new AnswerItemBean("0", "伟大斗争、伟大工程、伟大事业、伟大梦想 ", 1));
        list.add(new AnswerItemBean("1", "伟大斗争、伟大建设、伟大事业、伟大梦想 "));
        list.add(new AnswerItemBean("2", "伟大斗争、伟大工程、伟大发展、伟大梦想"));
        list.add(new AnswerItemBean("3", "伟大斗争、伟大工程、伟大事业、伟大理想"));
        answerBeanList.add(new AnswerBean("1000",
                "1、在习近平新时代中国特色社会主义思想指导下，中国共产党领导全国各族人民，统揽（），推动中国特色社会主义进入了新时代。",
                "0",
                list,
                "解析",
                "0",
                "0",
                true,
                "25"));

         list = new ArrayList<>();
        list.add(new AnswerItemBean("0", "毛泽东思想"));
        list.add(new AnswerItemBean("1", "马克思列宁主义"));
        list.add(new AnswerItemBean("2", "习近平新时代中国特色社会主义思想", 1));
        list.add(new AnswerItemBean("3", "“三个代表”重要思想"));
        answerBeanList.add(new AnswerBean("1001",
                "2、在（）指导下，中国共产党领导全国各族人民，统揽伟大斗争、伟大工程、伟大事业、伟大梦想，推动中国特色社会主义进入了新时代。",
                "0",
                list,
                "解析",
                "2",
                "2",
                true,
                "25"));

        list = new ArrayList<>();
        list.add(new AnswerItemBean("0", "邓小平理论", 1));
        list.add(new AnswerItemBean("1", "三个代表”重要思想", 1));
        list.add(new AnswerItemBean("2", "科学发展观 ", 1));
        list.add(new AnswerItemBean("3", "习近平新时代中国特色社会主义思想", 1));
        answerBeanList.add(new AnswerBean("1002", "3、中国共产党以马克思列宁主义、毛泽东思想、（ ）作为自己的行动指南。  ",
                "1",
                list,
                "解析",
                "0,1,2,3",
                "0,1,2,3",
                true,
                "25"));

        list = new ArrayList<>();
        list.add(new AnswerItemBean("0", "推进现代化建设"));
        list.add(new AnswerItemBean("1", "推进经济建设"));
        list.add(new AnswerItemBean("2", "完成祖国统一", 1));
        list.add(new AnswerItemBean("3", "维护世界和平与促进共同发展", 1));
        answerBeanList.add(new AnswerBean("1003",
                "4、全党同志要为实现（ ）、（ ）、（ ）这三大历史任务，实现“两个一百年”奋斗目标、实现中华民族伟大复兴的中国梦而奋斗。 ",
                "1",
                list,
                "解析",
                "0,2,3",
                "2,3",
                false,
                "25"));

        adapter.setDataList(answerBeanList);

    }
}
