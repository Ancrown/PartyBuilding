package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.AnswerBean;
import zhuri.com.partybuilding.util.AppUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/28
 * 描述:
 */

public class AnswerIndexAdapter extends BaseRecyclerAdapter<AnswerBean> {


    public AnswerIndexAdapter(Context context) {
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
        public void bindData(AnswerBean answerBean, final int i) {
            itemAnswerIndex.setText((i + 1) + "");
            if (answerBean.getMyOptions().equals("-1")) {
                itemAnswerIndex.setTextColor(AppUtils.getColor(R.color.black));
                itemAnswerIndex.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.xian_dotted_light_gray));
            } else {
                itemAnswerIndex.setTextColor(AppUtils.getColor(R.color.white));
                itemAnswerIndex.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red));
            }
            itemAnswerIndex.setOnClickListener(new View.OnClickListener() {
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
