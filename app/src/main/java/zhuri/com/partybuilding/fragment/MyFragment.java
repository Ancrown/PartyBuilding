package zhuri.com.partybuilding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.AnswerErrorActivity;
import zhuri.com.partybuilding.activity.LoginActivity;
import zhuri.com.partybuilding.activity.MyInfoActivity;
import zhuri.com.partybuilding.activity.RecordAnswerActivity;
import zhuri.com.partybuilding.activity.RecordIntegralActivity;
import zhuri.com.partybuilding.activity.RecordStudyActivity;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * 我的
 */

public class MyFragment extends BaseFragment {
    //头像
    @BindView(R.id.my_img)
    ImageView img;
    //名字
    @BindView(R.id.my_name)
    TextView myName;
    //职位
    @BindView(R.id.my_position)
    TextView myPosition;
    //标签
    @BindView(R.id.my_label)
    TextView myLabel;
    //累计
    @BindView(R.id.my_accumulative)
    TextView myAccumulative;
    //积分
    @BindView(R.id.my_integral)
    TextView myIntegral;

    @BindView(R.id.my_info)
    TextView myInfo;
    @BindView(R.id.my_activity_record)
    TextView myActivityRecord;
    @BindView(R.id.my_learning_records)
    TextView myLearningRecords;
    @BindView(R.id.my_examination_records)
    TextView myExaminationRecords;
    @BindView(R.id.my_contribution)
    TextView myContribution;

    @BindView(R.id.my_examination_error)
    TextView myExaminationError;

    @BindView(R.id.my_login)
    TextView myLogin;
    @BindView(R.id.my_rl_info)
    RelativeLayout myRlInfo;
    @BindView(R.id.my_dayuhao)
    ImageView myDayuhao;



    @Override
    public int getLayoutId() {
        return R.layout.fra_my;
    }

    @Override
    public void initView() {
        getTitleView().setData(getResources().getString(R.string.my), 0, 0, null, 0, null, null);
    }

    @Override
    public void refreshData() {
        //GlideUtils.LoadCircleImage(getActivity(), "https://gss0.bdstatic.com/70cFfyinKgQIm2_p8IuM_a/daf/pic/item/30adcbef76094b36ccec2775afcc7cd98d109d2f.jpg", img);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isLogin) {
            myLogin.setVisibility(View.VISIBLE);
            myRlInfo.setVisibility(View.GONE);
        } else {
            getEntity();
            myLogin.setVisibility(View.GONE);
            myRlInfo.setVisibility(View.VISIBLE);

        }

    }

    @OnClick({R.id.my_info, R.id.my_activity_record, R.id.my_learning_records, R.id.my_examination_records, R.id.my_contribution, R.id.my_login, R.id.my_rl_info, R.id.my_examination_error})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_info:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), MyInfoActivity.class));
                } else {
                    goLogin();
                }
                break;
            case R.id.my_activity_record:
                if (!isLogin) {

                } else {
                    goLogin();
                }
                break;
            case R.id.my_learning_records:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), RecordStudyActivity.class));
                } else {
                    goLogin();
                }
                break;
            case R.id.my_examination_records:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), RecordAnswerActivity.class));

                } else {
                    goLogin();
                }
                break;
            case R.id.my_contribution:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), RecordIntegralActivity.class));
                } else {
                    goLogin();
                }
                break;
            case R.id.my_examination_error:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), AnswerErrorActivity.class));
                } else {
                    goLogin();
                }
                break;
            case R.id.my_login:
                goLogin();
                break;
            case R.id.my_rl_info:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), MyInfoActivity.class));
                } else {
                    goLogin();
                }
                break;
        }
    }

    public void getEntity() {


        //名字
        myName.setText(SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_NAME, "") + "");
        //头像
        GlideUtils.LoadCircleImage(getActivity(), SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_HEAD_IMG, "") + "", img);
        //积分
        myIntegral.setText("当前积分：" + SharedPreferencesUtils.getParam(getActivity(), StaticVariables.INTEGRAL, "") + "分");

        myLabel.setText( SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_NICK_NAME, "")+"");

        myPosition.setText(SharedPreferencesUtils.getParam(getActivity(), StaticVariables.D_NAME, "") + "");

        myAccumulative.setText("已加入党"+TimeUtil.differentDays(SharedPreferencesUtils.getParam(getActivity(), StaticVariables.JOINTIME, "") + "")
                +"天    累计学习"+23+"次");

        Log.e("eeeeee 入党时间", SharedPreferencesUtils.getParam(getActivity(), StaticVariables.JOINTIME, "")+"");

        //昵称
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.USER_NICK_NAME, "");
        //性别
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.SEX, "");
        //年龄
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.AGE, "");
        //生日
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.BIRTHDAY, "");
        //所在支部ID
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.DID, "");
        //所在支部名称
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.D_NAME, "");
        //电话
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.TEL, "");
        //邮箱
        SharedPreferencesUtils.getParam(getActivity(), StaticVariables.EMAIL, "");


    }

    public void goLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

}
