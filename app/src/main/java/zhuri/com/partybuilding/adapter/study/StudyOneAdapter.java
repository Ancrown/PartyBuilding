package zhuri.com.partybuilding.adapter.study;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.LoginActivity;
import zhuri.com.partybuilding.activity.StudyDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.study.StudyOneBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/27
 * 描述:
 */

public class StudyOneAdapter extends BaseRecyclerAdapter<StudyOneBean> {

    private Drawable video = context.getResources().getDrawable(R.drawable.ic_video);

    private String cid;

    public StudyOneAdapter(Context context, String cid) {
        super(context);
        this.cid = cid;
        video.setBounds(0, 0, video.getMinimumWidth(), video.getMinimumHeight());
    }

    @Override
    public CommonHolder<StudyOneBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_study_one);
    }

    class Holder extends CommonHolder<StudyOneBean> {
        @BindView(R.id.item_study_one_img)
        ImageView itemStudyOneImg;
        @BindView(R.id.item_study_one_title)
        TextView itemStudyOneTitle;
        @BindView(R.id.item_study_one_istop)
        TextView itemStudyOneIstop;
        @BindView(R.id.item_study_one_content)
        TextView itemStudyOneContent;
        @BindView(R.id.item_study_one_join)
        TextView itemStudyOneJoin;
        @BindView(R.id.item_study_one_ll)
        LinearLayout itemStudyOneLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final StudyOneBean bean, final int i) {
            GlideUtils.LoadImage(context, bean.getImageurl(), itemStudyOneImg);

            if (bean.getIsvideo().equals("1")) {
                itemStudyOneTitle.setCompoundDrawables(video, null, null, null);
                itemStudyOneTitle.setCompoundDrawablePadding(SizeUtils.dip2px(5));
            } else {
                itemStudyOneTitle.setCompoundDrawables(null, null, null, null);
                itemStudyOneTitle.setCompoundDrawablePadding(SizeUtils.dip2px(0));
            }
            if (bean.getIsstudy().equals("0")) {
                itemStudyOneIstop.setText("未学习");
                itemStudyOneIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
            } else {
                itemStudyOneIstop.setText("已学习");
                itemStudyOneIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_light_gray_4));
            }
            itemStudyOneContent.setText(TextUtils.isEmpty(bean.getDemo()) ? bean.getDemo() : bean.getDemo().trim());
            itemStudyOneJoin.setText(bean.getAmount() + "人已学习");
            itemStudyOneLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("eeeeee", "点击：：：：" + bean.getId() + "  " + bean.getTitle());
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
