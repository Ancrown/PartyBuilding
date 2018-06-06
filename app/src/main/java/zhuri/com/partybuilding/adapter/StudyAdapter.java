package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.StudyBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:
 */

public class StudyAdapter extends BaseRecyclerAdapter<StudyBean> {

    private Drawable fabulousNo = context.getResources().getDrawable(R.drawable.fabulous);

    private Drawable fabulousYes = context.getResources().getDrawable(R.drawable.fabulous);

    public StudyAdapter(Context context) {
        super(context);
        fabulousNo.setBounds(0, 0, fabulousNo.getMinimumWidth(), fabulousNo.getMinimumHeight());
        fabulousYes.setBounds(0, 0, fabulousYes.getMinimumWidth(), fabulousYes.getMinimumHeight());
    }

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
        public void bindData(StudyBean bean, final int i) {
            itemStudyTitle.setText(bean.getTitle());
            itemStudyContent.setText(bean.getContent());
            ToolUtils.toStringChangeColor(bean.getTime() + "    " + bean.getJoin() + "人已参与", bean.getJoin() + "人已参与", "#d73734", itemStudyTimeJoin);
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
                    ToolUtils.showToast(getContext(), "点击我了！" + i);
                }
            });
        }
    }
}
