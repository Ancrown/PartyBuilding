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
import zhuri.com.partybuilding.util.NoSlideListView;
import zhuri.com.partybuilding.util.StringUtil;
import zhuri.com.partybuilding.util.TextViewUitl;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述:
 */

public class AnswerAdapter extends BaseRecyclerAdapter<AnswerBean> {


    private int red;


    public AnswerAdapter(Context context) {
        super(context);
        red = R.color.black;
    }

    public AnswerAdapter(Context context, int type) {
        super(context);
        if (type == 0) {
            red = R.color.red;
        } else {
            red = R.color.black;
        }


    }


    @Override
    public CommonHolder<AnswerBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_answer);
    }

    class Holder extends CommonHolder<AnswerBean> {

        @BindView(R.id.answer_subject)
        TextView answerSubject;
        @BindView(R.id.answer_listview)
        NoSlideListView answerListview;
        @BindView(R.id.answer_explaination)
        TextView answerExplaination;

        @BindView(R.id.answer_my_option)
        TextView answerMyOption;
        @BindView(R.id.answer_true_option)
        TextView answerTrueOption;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(AnswerBean b, int i) {


            AnswerItemAdapter answerAdapter = new AnswerItemAdapter(context);
            answerListview.setAdapter(answerAdapter);
            answerAdapter.setRefreshList(b.getOptionList());
            if (b.getMyOptions().equals("-1")) {
                answerMyOption.setText("我的选项：" + "你没有选择");
            } else {
                answerMyOption.setText("我的选项：" + StringUtil.getLetter(b.getMyOptions()));
            }


            answerTrueOption.setText("正确选项:" + StringUtil.getLetter(b.getCorrectOptions()));

            answerExplaination.setText("分析:" + b.getAnalysis());

            answerMyOption.setVisibility(View.VISIBLE);
            answerTrueOption.setVisibility(View.VISIBLE);
            answerExplaination.setVisibility(View.VISIBLE);

            if (!b.isErrorOptions()) {
                answerSubject.setTextColor(AppUtils.getColor(red));
            }

            String ttt = "（" + (b.getSinglePair().equals("0") ? "单选题" : "多选题") + b.getScore() + "分）";
            answerSubject.setText(b.getTitle() + ttt);

            TextViewUitl.toStringChangeSizeAndColor(answerSubject.getText().toString(), ttt, 15, "#7b7b7b", answerSubject);

        }
    }
}
