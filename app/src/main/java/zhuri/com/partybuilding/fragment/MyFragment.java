package zhuri.com.partybuilding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.AnswerErrorActivity;
import zhuri.com.partybuilding.base.BaseFragment;
import zhuri.com.partybuilding.util.ToolUtils;

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


    private boolean isLogin;

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
        isLogin = ToolUtils.isLogin(getActivity());
        if (isLogin) {
            myLogin.setVisibility(View.GONE);
            myRlInfo.setVisibility(View.VISIBLE);
        } else {
            myLogin.setVisibility(View.VISIBLE);
            myRlInfo.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.my_info, R.id.my_activity_record, R.id.my_learning_records, R.id.my_examination_records, R.id.my_contribution, R.id.my_login, R.id.my_rl_info, R.id.my_examination_error})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_info:
                if (isLogin) {

                } else {
                    ToolUtils.showToast(getActivity(), "请先登录！");
                }
                break;
            case R.id.my_activity_record:
                if (isLogin) {

                } else {
                    ToolUtils.showToast(getActivity(), "请先登录！");
                }
                break;
            case R.id.my_learning_records:
                if (isLogin) {

                } else {
                    ToolUtils.showToast(getActivity(), "请先登录！");
                }
                break;
            case R.id.my_examination_records:
                if (isLogin) {

                } else {
                    ToolUtils.showToast(getActivity(), "请先登录！");
                }
                break;
            case R.id.my_contribution:
                if (isLogin) {

                } else {
                    ToolUtils.showToast(getActivity(), "请先登录！");
                }
                break;
            case R.id.my_examination_error:
                startActivity(new Intent(getActivity(), AnswerErrorActivity.class));
                break;

            case R.id.my_login:
                ToolUtils.showToast(getActivity(), "跳到登陆页！");
                break;
            case R.id.my_rl_info:

                break;
        }
    }


}
