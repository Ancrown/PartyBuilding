package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SizeUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/28
 * 描述: 显示对错
 */

public class AnswerNewsResultAdapter extends BaseRecyclerAdapter<AnswerBean> {


    public AnswerNewsResultAdapter(Context context) {
        super(context);
    }

    @Override
    public CommonHolder<AnswerBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_answer_index);
    }

    class Holder extends CommonHolder<AnswerBean> {
        @BindView(R.id.item_answer_index)
        TextView itemAnswerIndex;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(AnswerBean answerBean, int i) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemAnswerIndex.getLayoutParams();
            layoutParams.setMargins(0, SizeUtils.dip2px(10), 0, 0);
            itemAnswerIndex.setLayoutParams(layoutParams);

            itemAnswerIndex.setText((i + 1) + "");
            if (answerBean.isErrorOptions()) {
                itemAnswerIndex.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.ianswer_index_true));
            } else {
                itemAnswerIndex.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.ianswer_index_false));
            }

        }
    }
}
