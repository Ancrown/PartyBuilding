package zhuri.com.partybuilding.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.squareup.okhttp.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.AnswerItemAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.bean.AnswerItemBean;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.ExaminationInfoEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.NoSlideListView;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TextViewUitl;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.countdown.CountDownTimerSupport;
import zhuri.com.partybuilding.util.countdown.OnCountDownTimerListener;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * Created by Administrator on 2018/5/14.
 */

public class AnswerActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    //剩余时间
    @BindView(R.id.answer_time)
    TextView time;
    private boolean aBoolean;

    //答题下标
    @BindView(R.id.answer_index)
    TextView index;

//    private MyCountDownTimer myCountDownTimer;

    //倒计时
    private CountDownTimerSupport mTimer;


    //标题
    @BindView(R.id.answer_subject)
    TextView subject;
    //选项
    @BindView(R.id.answer_listview)
    NoSlideListView listview;
    //上一题
    @BindView(R.id.btn_previous)
    Button previous;
    //下一题
    @BindView(R.id.btn_next)
    Button next;
    //解释
    @BindView(R.id.answer_explaination)
    TextView explaination;
    //题
    private List<AnswerBean> answerBeanList;
    private List<AnswerItemBean> answerItemBeanList;


    //选项
    private List<AnswerItemBean> answerItemBeanList0;
    private List<AnswerItemBean> answerItemBeanList1;
    private List<AnswerItemBean> answerItemBeanList2;
    private List<AnswerItemBean> answerItemBeanList3;


    //考卷id
    private String id;

    private int current;

    private AnswerItemAdapter answerAdapter;
    //得分
    private int score;
    //我的选择
    private String allanswer;
    //错题数
    private int errorNum;
    //错题id
    private String errorid;
    //错误题我的选择
    private String erroranswer;
    //用时
    private long usetime;
    //答题时间
    private long lengthTime = 1000 * 60 * 1;


    //提示框
    AlertDialog.Builder builderOne;
    AlertDialog.Builder builderTwo;

    @Override
    protected void initData() {

        getData();


    }

    private void getData() {
        answerBeanList = new ArrayList<>();
        answerItemBeanList0 = new ArrayList<>();
        answerItemBeanList0.add(new AnswerItemBean("0", "选项XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
        answerItemBeanList0.add(new AnswerItemBean("1", "选项"));
        answerItemBeanList0.add(new AnswerItemBean("2", "选项"));
        answerItemBeanList0.add(new AnswerItemBean("3", "选项"));
        answerBeanList.add(new AnswerBean("1000", "1.你选那个选项", "0", answerItemBeanList0, "这个题应该选C", "2", "25"));

        answerItemBeanList1 = new ArrayList<>();
        answerItemBeanList1.add(new AnswerItemBean("0", "刘德华"));
        answerItemBeanList1.add(new AnswerItemBean("1", "刘晓伟"));
        answerBeanList.add(new AnswerBean("1001", "2.谁最高", "1", answerItemBeanList1, "刘晓伟最高", "1", "25"));

        answerItemBeanList2 = new ArrayList<>();
        answerItemBeanList2.add(new AnswerItemBean("0", "秦朝"));
        answerItemBeanList2.add(new AnswerItemBean("1", "隋朝"));
        answerItemBeanList2.add(new AnswerItemBean("2", "西晋"));
        answerItemBeanList2.add(new AnswerItemBean("3", "元朝"));
        answerBeanList.add(new AnswerBean("1002", "3.下列王朝中统治时间最短的是： ", "0", answerItemBeanList2, "秦朝最短！！！", "0", "25"));

        answerItemBeanList3 = new ArrayList<>();
        answerItemBeanList3.add(new AnswerItemBean("0", "1+9"));
        answerItemBeanList3.add(new AnswerItemBean("1", "2+8"));
        answerItemBeanList3.add(new AnswerItemBean("2", "4+4"));
        answerItemBeanList3.add(new AnswerItemBean("3", "5+5"));
        answerBeanList.add(new AnswerBean("1003", "4.那个等于10？", "1", answerItemBeanList3, "1+9 / 2+8 / 5+5", "0,1,3", "25"));


        setData();
    }


    @Override
    protected void initListener() {

        answerAdapter.setOnClickLL(new AnswerItemAdapter.OnClickLL() {
            @Override
            public void onLL(int position, String id) {

                Log.e("eeeeee", " 点击:" + answerBeanList.get(current).getOptionList().get(position).getText());


                if (answerBeanList.get(current).getSinglePair().equals("0")) {
                    //单选设置
                    for (int i = 0; i < answerBeanList.get(current).getOptionList().size(); i++) {
                        answerBeanList.get(current).getOptionList().get(i).setType(i == position ? (
                                //选中>取消 取消>选中
                                answerBeanList.get(current).getOptionList().get(position).getType() == 0 ? 1 : 0
                        ) : 0);
                    }
                } else {
                    //多选设置
                    answerBeanList.get(current).getOptionList().get(position).setType(
                            //选中>取消 取消>选中
                            answerBeanList.get(current).getOptionList().get(position).getType() == 0 ? 1 : 0);
                }


                answerAdapter.setRefreshList(answerBeanList.get(current).getOptionList());


                StringBuilder aa = new StringBuilder("");
                for (int j = 0; j < answerBeanList.get(current).getOptionList().size(); j++) {
                    if (answerBeanList.get(current).getOptionList().get(j).getType() == 1) {
                        aa.append(answerBeanList.get(current).getOptionList().get(j).getId() +
                                (answerBeanList.get(current).getSinglePair().equals("1") ? "," : ""));
                    }
                }

                answerBeanList.get(current).setMyOptions(aa.toString().equals("") ? "-1" : aa.toString());
                Log.e("eeeeee", " 我的选项：" + answerBeanList.get(current).getMyOptions());

            }
        });

    }

    @Override
    protected void initView() {
        getTitleView().setData("答题", 0, R.drawable.back, null, 0, null, this);
        id = getIntent().getStringExtra("id");
        answerAdapter = new AnswerItemAdapter(this);
        listview.setAdapter(answerAdapter);
        builderOne = new AlertDialog.Builder(AnswerActivity.this);
        builderTwo = new AlertDialog.Builder(AnswerActivity.this);

        mTimer = new CountDownTimerSupport(1000 * 60 * 1, 1000);
        mTimer.start();
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!AnswerActivity.this.isFinishing()) {
                    //  time.setText("剩余时间：" + (millisUntilFinished / 1000) / 60 + "分" + (millisUntilFinished / 1000) % 60 + "秒");
                    time.setText("剩余时间：" + TimeUtil.millisecond2String(mTimer.getMillisUntilFinished(), "mm分ss秒"));
                }
            }

            @Override
            public void onFinish() {
                if (!AnswerActivity.this.isFinishing()) {
                    time.setText("剩余时间：" + "0分0秒");

                    if (!aBoolean) {

                        //操作 到时间了 交卷

                        //在这显示是否提交~！
                        builderOne.setTitle("提示").setMessage("时间已到 确认交卷！").setCancelable(false)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //计时结束
                                        mTimer.stop();
                                        usetime = lengthTime - mTimer.getMillisUntilFinished();
                                        Log.e("eeeeee", "用时  " + TimeUtil.millisecond2String(usetime, "mm:ss"));
                                        contrastiveProblem();


                                        Intent intent = new Intent();
                                        intent.setClass(AnswerActivity.this, AnswerResultActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("list", (Serializable) answerBeanList);//序列化,要注意转化(Serializable)
                                        intent.putExtras(bundle);//发送数据
                                        intent.putExtra("amountError", errorNum + "");
                                        intent.putExtra("score", score + "");
                                        startActivity(intent);//启动intent
                                        onBack();
                                    }
                                })
                                .show();
                    }
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_answer;
    }


    @OnClick({R.id.btn_previous, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_previous:
                Log.e("eeeee", "I qian :" + current + "   " + answerBeanList.size());
                comparison();

                current--;
                Log.e("eeeee", "I :" + current + "   " + answerBeanList.size());
                if (current < 0) {
                    current = 0;
                    ToolUtils.showToast(AnswerActivity.this, "不可往前！");
                    return;
                }


                setData();
                break;
            case R.id.btn_next:
                Log.e("eeeee", "I qian :" + current + "   " + answerBeanList.size());

                comparison();

                current++;
                Log.e("eeeee", "I :" + current + "   " + answerBeanList.size());
                if (current == answerBeanList.size()) {
                    current = answerBeanList.size() - 1;

                    //在这显示是否提交~！
                    builderOne.setTitle("提示").setMessage("已经达到最后一题，是否确认交卷？ 交卷就不可修改！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //计时结束
                                    mTimer.stop();
                                    usetime = lengthTime - mTimer.getMillisUntilFinished();

                                    Log.e("eeeeee", "用时  " + TimeUtil.millisecond2String(usetime, "mm:ss"));

                                    contrastiveProblem();

                                    Log.e("eeeeee", "我的选择   " + allanswer);
                                    Log.e("eeeeee", "错误题ID   " + errorid);
                                    Log.e("eeeeee", "错误题我的选择   " + erroranswer);


                                    Intent intent = new Intent();
                                    intent.setClass(AnswerActivity.this, AnswerResultActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("list", (Serializable) answerBeanList);//序列化,要注意转化(Serializable)
                                    intent.putExtras(bundle);//发送数据
                                    intent.putExtra("amountError", errorNum + "");
                                    intent.putExtra("score", score + "");
                                    startActivity(intent);//启动intent
                                    onBack();
                                }
                            }).setNegativeButton("取消", null)
                            .show();

                    return;
                }


                setData();

                break;
        }
    }


    //比对标记
    private void comparison() {
        Log.e("eeeeee", " TTTT: " + answerBeanList.get(current).getMyOptions() + "   " + answerBeanList.get(current).getCorrectOptions());
        if (Arrays.equals(answerBeanList.get(current).getMyOptions().split(","), answerBeanList.get(current).getCorrectOptions().split(","))) {
            answerBeanList.get(current).setErrorOptions(true);
            Log.e("eeeeee", " 你做的正确");
        } else {
            answerBeanList.get(current).setErrorOptions(false);
            Log.e("eeeeee", " 你做的错误！！！！");
        }
    }

    //对比错误个数
    private void contrastiveProblem() {
        score = 0;
        errorNum = 0;
        allanswer = "";
        errorid = "";
        erroranswer = "";
        for (int i = 0; i < answerBeanList.size(); i++) {
            allanswer = allanswer + answerBeanList.get(i).getMyOptions() + "#";
            if (!answerBeanList.get(i).isErrorOptions()) {
                errorid = errorid + answerBeanList.get(i).getId() + "#";
                erroranswer = erroranswer + answerBeanList.get(i).getMyOptions() + "#";
                errorNum++;
            } else {
                score = score + Integer.parseInt(answerBeanList.get(i).getScore());
            }
        }

    }

    //题数据
    private void setData() {
        //  singlePair.setText("当前: " + current);
        String ttt = "（" + (answerBeanList.get(current).getSinglePair().equals("0") ? "单选题" : "多选题") + answerBeanList.get(current).getScore() + "）";
        subject.setText(answerBeanList.get(current).getTitle() + ttt);


        TextViewUitl.toStringChangeSizeAndColor(subject.getText().toString(), ttt, 15, "#7b7b7b", subject);

        explaination.setVisibility(View.INVISIBLE);
        answerAdapter.setRefreshList(answerBeanList.get(current).getOptionList());

        showIndex();
    }


    //显示当 当前题/总题数
    private void showIndex() {
        index.setText((current + 1) + "/" + answerBeanList.size());
        if (current == 0) {
            previous.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
            next.setText("下一题");
        } else if (current == answerBeanList.size() - 1) {
            previous.setVisibility(View.VISIBLE);
            previous.setText("上一题");
            next.setVisibility(View.VISIBLE);
            next.setText("交卷");
        } else {
            previous.setVisibility(View.VISIBLE);
            previous.setText("上一题");
            next.setVisibility(View.VISIBLE);
            next.setText("下一题");
        }

    }


    @Override
    public void onLeftClick() {
        aBoolean = true;
        //在这显示是否提交~！
        builderOne.setTitle("提示").setMessage("是否交卷！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //计时结束
                        mTimer.stop();
                        //这个位置加 交卷接口

                        onBack();
                    }
                }).setNegativeButton("取消", null)
                .show();

    }


    @Override
    public void onBackPressed() {
        aBoolean = true;
        //在这显示是否提交~！
        builderOne.setTitle("提示").setMessage("是否交卷！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //计时结束
                        mTimer.stop();
                        //这个位置加 交卷接口

                        onBack();
                    }
                }).setNegativeButton("取消", null)
                .show();

    }

    @Override
    public void onRightClick() {

    }

    //获得试卷
    public void getexaminfo() {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("id", id);

        OkHttpUtil.getInstance(this).doPost(AddressRequest.GET_EXAMINATION, new OkHttpUtil.ResultCallback<BaseEntity<ExaminationInfoEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<ExaminationInfoEntity> response) {
                if (response.isStatus()) {
                    answerBeanList = new ArrayList<>();
                    for (int i = 0; i < response.getData().getInfo().size(); i++) {
                        answerItemBeanList = new ArrayList<>();
                        String[] options = response.getData().getInfo().get(i).getOptions().split("#");
                        for (int j = 0; j < options.length; j++) {
                            answerItemBeanList.add(new AnswerItemBean("" + j, options[j]));
                        }
                        answerBeanList.add(new AnswerBean(response.getData().getInfo().get(i).getId(),
                                response.getData().getInfo().get(i).getTitle(),
                                response.getData().getInfo().get(i).getCtype(),
                                answerItemBeanList,
                                response.getData().getInfo().get(i).getTips(),
                                response.getData().getInfo().get(i).getAnswer(),
                                response.getData().getInfo().get(i).getScore()
                        ));
                    }
                    setData();
                }

            }
        }, map, "");
    }

    //交卷
    public void saveexam(final int type) {
        //type 判断是否跳页  正常答题和计时结束跳转  返回不跳

        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("id", id);
        map.put("score", score);
        map.put("allanswer", score);
        map.put("errorid", score);
        map.put("erroranswer", score);
        map.put("usetime", usetime);

        OkHttpUtil.getInstance(this).doPost(AddressRequest.UP_EXAMINATION, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<String> response) {
                if (response.isStatus()) {
                    if (type == 0) {
                        Intent intent = new Intent();
                        intent.setClass(AnswerActivity.this, AnswerResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", (Serializable) answerBeanList);//序列化,要注意转化(Serializable)
                        intent.putExtras(bundle);//发送数据
                        intent.putExtra("amountError", errorNum + "");
                        intent.putExtra("score", score + "");
                        startActivity(intent);//启动intent
                    }
                    onBack();
                }

            }
        }, map, "交卷中");
    }

}
