package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.StudyDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.study.StudyBean;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TextViewUitl;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:
 */

public class StudyAdapter extends BaseRecyclerAdapter<StudyBean> {

    private Drawable fabulousNo = context.getResources().getDrawable(R.drawable.fabulous);

    private Drawable fabulousYes = context.getResources().getDrawable(R.drawable.fabulous_yes);


    public StudyAdapter(Context context, String cid) {
        super(context);
        this.cid = cid;
        fabulousNo.setBounds(0, 0, fabulousNo.getMinimumWidth(), fabulousNo.getMinimumHeight());
        fabulousYes.setBounds(0, 0, fabulousYes.getMinimumWidth(), fabulousYes.getMinimumHeight());


    }

    private String cid;

    @Override
    public CommonHolder<StudyBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_study);
    }

    class Holder extends CommonHolder<StudyBean> {
        @BindView(R.id.item_study_ll)
        LinearLayout ll;
        @BindView(R.id.item_study_title)
        TextView itemStudyTitle;
        @BindView(R.id.item_study_content)
        TextView itemStudyContent;
        @BindView(R.id.item_study_time_join)
        TextView itemStudyTimeJoin;
        @BindView(R.id.item_study_fabulous_num)
        TextView itemStudyFabulousNum;
        @BindView(R.id.item_study_see_num)
        TextView itemStudySeeNum;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final StudyBean bean, final int i) {
            itemStudyTitle.setText(bean.getTitle());
            itemStudyContent.setText(bean.getContent());
            TextViewUitl.toStringChangeColor(bean.getTime() + "    " + bean.getJoin() + "人已参与", bean.getJoin() + "人已参与", "#d73734", itemStudyTimeJoin);
            itemStudyFabulousNum.setText(bean.getFabulousNum());
            itemStudySeeNum.setText(bean.getSeeNum());

            if (bean.getIsFabulous().equals("0")) {
                itemStudyFabulousNum.setCompoundDrawables(fabulousNo, null, null, null);
            } else {
                itemStudyFabulousNum.setCompoundDrawables(fabulousYes, null, null, null);
            }

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    look(isLogin,bean.getPurview().equals("0"),bean.getId());
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
                ToolUtils.showToast(context, "游客不可看");
            }
        } else
            context.startActivity(new Intent(context, StudyDetailActivity.class).putExtra("cid", cid).putExtra("id", id));

    }

}
