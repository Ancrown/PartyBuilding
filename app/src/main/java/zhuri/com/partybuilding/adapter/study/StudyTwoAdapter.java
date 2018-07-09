package zhuri.com.partybuilding.adapter.study;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.LoginActivity;
import zhuri.com.partybuilding.activity.StudyDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.study.StudyTwoBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/27
 * 描述:
 */

public class StudyTwoAdapter extends BaseRecyclerAdapter<StudyTwoBean.SubitemBean> {


    private String cid;

    public StudyTwoAdapter(Context context, String cid) {
        super(context);
        this.cid = cid;
    }

    @Override
    public CommonHolder<StudyTwoBean.SubitemBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_study_two);
    }

    class Holder extends CommonHolder<StudyTwoBean.SubitemBean> {
        @BindView(R.id.item_study_two_title)
        TextView itemStudyTwoTitle;
        @BindView(R.id.item_study_two_istop)
        TextView itemStudyTwoIstop;
        @BindView(R.id.item_study_two_content)
        TextView itemStudyTwoContent;
        @BindView(R.id.item_study_two_ll)
        LinearLayout itemStudyTwoLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final StudyTwoBean.SubitemBean bean, int i) {
            itemStudyTwoTitle.setText(bean.getTitle());

            if (bean.getIsstudy().equals("0")) {
                itemStudyTwoIstop.setText("未学习");
                itemStudyTwoIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
            } else {
                itemStudyTwoIstop.setText("已学习");
                itemStudyTwoIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_light_gray_4));
            }
            itemStudyTwoContent.setText(TextUtils.isEmpty(bean.getDemo()) ? bean.getDemo() : bean.getDemo().trim());
            itemStudyTwoLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    look(isLogin, bean.getPurview().equals("0"), bean.getId());
                }
            });
        }
    }

    //跳页 type判断是 查看报道 1  报名  0
    public void look(boolean isLogin, boolean purview, String id) {

        if (isLogin) {
            //没登录
            if (purview) {
                //游客可看
                context.startActivity(new Intent(context, StudyDetailActivity.class).putExtra("cid", cid).putExtra("id", id));
            } else {
                ToolUtils.showToast(context, "游客不可看，请先登陆");
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        } else {
            context.startActivity(new Intent(context, StudyDetailActivity.class).putExtra("cid", cid).putExtra("id", id));
        }

    }
}
