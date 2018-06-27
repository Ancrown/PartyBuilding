package zhuri.com.partybuilding.adapter.study;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.HomePageAdapter;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.study.StudyTwoBean;
import zhuri.com.partybuilding.util.AppUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/27
 * 描述:
 */

public class StudyTwoSubitemAdapter extends BaseRecyclerAdapter<StudyTwoBean> {


    public StudyTwoSubitemAdapter(Context context) {
        super(context);
    }

    @Override
    public CommonHolder<StudyTwoBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_study_two_sub);
    }

    class Holder extends CommonHolder<StudyTwoBean> {
        @BindView(R.id.study_two_subitem_ll)
        LinearLayout studyTwoSubitemLl;
        @BindView(R.id.study_two_subitem_view)
        View studyTwoSubitemView;
        @BindView(R.id.study_two_subitem_text)
        TextView studyTwoSubitemText;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final StudyTwoBean bean, final int i) {
            studyTwoSubitemText.setText(bean.getSubitemtext());
            if (bean.getType().equals("0")) {
                studyTwoSubitemLl.setBackgroundColor(AppUtils.getColor(R.color.white));
                studyTwoSubitemView.setVisibility(View.VISIBLE);
                studyTwoSubitemText.setTextColor(AppUtils.getColor(R.color.light_red));
            } else {
                studyTwoSubitemLl.setBackgroundColor(AppUtils.getColor(R.color.background));
                studyTwoSubitemView.setVisibility(View.INVISIBLE);
                studyTwoSubitemText.setTextColor(AppUtils.getColor(R.color.black));
            }
            studyTwoSubitemLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItem != null) {
                        onClickItem.onItem(i);
                    }
                }
            });
        }
    }

    /**
     * 点击item
     */
    public OnClickItem onClickItem;


    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onItem(int i);
    }
}
