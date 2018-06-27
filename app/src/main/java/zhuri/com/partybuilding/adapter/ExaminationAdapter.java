package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.AnswerConfirmActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class ExaminationAdapter extends BaseRecyclerAdapter<ExaminationBean> {


    private boolean isLogin;

    public ExaminationAdapter(Context context) {
        super(context);
        isLogin = TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, "") + "");
    }

    @Override
    public CommonHolder<ExaminationBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_examination);
    }

    class Holder extends CommonHolder<ExaminationBean> {
        @BindView(R.id.item_examination_view)
        View itemExaminationView;
        @BindView(R.id.item_examination_title)
        TextView itemExaminationTitle;
        @BindView(R.id.item_examination_news)
        TextView itemExaminationNews;
        @BindView(R.id.item_examination_content)
        TextView itemExaminationContent;
        @BindView(R.id.item_examination_button)
        Button itemExaminationButton;
        @BindView(R.id.item_examination_time)
        TextView itemExaminationTime;
        @BindView(R.id.item_examination_start_time)
        TextView itemExaminationStartTime;
        @BindView(R.id.item_examination_ll)
        LinearLayout ll;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final ExaminationBean bean, final int i) {
            itemExaminationTitle.setText(bean.getTitle());
            if (bean.getLabel().equals("0")) {
                itemExaminationNews.setVisibility(View.VISIBLE);
            } else {
                itemExaminationNews.setVisibility(View.GONE);
            }
            itemExaminationContent.setText(bean.getContent());
            itemExaminationTime.setText("答题时间：" + bean.getStartTime() + "~" + bean.getEndTime() + "    "
                    + (Integer.parseInt(bean.getTime()) == 0 ? "不限时间" : bean.getTime() + "分钟"));
            itemExaminationStartTime.setText(bean.getIntegral() + "积分");

            if (bean.getType().equals("0") || bean.getType().equals("3")) {
                itemExaminationView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                itemExaminationButton.setBackgroundColor(context.getResources().getColor(R.color.gray));

                if (bean.getType().equals("0")) {
                    itemExaminationButton.setText("未开始");
                    itemExaminationStartTime.setTextColor(context.getResources().getColor(R.color.red));
                    itemExaminationTime.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    itemExaminationButton.setText("已结束");
                    itemExaminationStartTime.setTextColor(context.getResources().getColor(R.color.gray));
                    itemExaminationTime.setTextColor(context.getResources().getColor(R.color.gray));
                }
            } else {
                itemExaminationView.setBackgroundColor(context.getResources().getColor(R.color.orange));
                itemExaminationTime.setTextColor(context.getResources().getColor(R.color.red));
                itemExaminationStartTime.setTextColor(context.getResources().getColor(R.color.red));
                itemExaminationButton.setBackgroundColor(context.getResources().getColor(R.color.orange));


                if (bean.getType().equals("1")) {
                    itemExaminationButton.setText("进行中");
                } else {
                    itemExaminationButton.setText("已答完");
                }
            }

            itemExaminationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (bean.getType()) {
                        case "0":
                            ToolUtils.showToast(getContext(), "未开始！" + i);
                            break;
                        case "1":
                            ToolUtils.showToast(getContext(), "答题开始！" + i);
                            break;
                        case "2":
                            ToolUtils.showToast(getContext(), "你已答完！" + i);
                            break;
                        case "3":
                            ToolUtils.showToast(getContext(), "已经结束！" + i);
                            break;
                    }
                }
            });
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    look(isLogin, bean.getPurview().equals("0"), bean);
                }
            });
        }
    }

    //跳页 type判断是 查看报道 1  报名  0
    public void look(boolean isLogin, boolean purview, ExaminationBean bean) {

        if (isLogin) {
            //没登录
            if (purview) {
                //游客可看
                Intent intent = new Intent(context, AnswerConfirmActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("score", bean.getScore());
                intent.putExtra("amount", bean.getAmount());
                intent.putExtra("integral", bean.getIntegral());
                intent.putExtra("times", bean.getTime());
                intent.putExtra("stime", bean.getStartTime());
                intent.putExtra("etime", bean.getEndTime());
                intent.putExtra("demo", bean.getContent());
                context.startActivity(intent);
            } else {
                ToolUtils.showToast(context, "游客不可");
            }
        } else {
            //登陆了
            Intent intent = new Intent(context, AnswerConfirmActivity.class);
            intent.putExtra("id", bean.getId());
            intent.putExtra("title", bean.getTitle());
            intent.putExtra("score", bean.getScore());
            intent.putExtra("amount", bean.getAmount());
            intent.putExtra("integral", bean.getIntegral());
            intent.putExtra("times", bean.getTime());
            intent.putExtra("stime", bean.getStartTime());
            intent.putExtra("etime", bean.getEndTime());
            intent.putExtra("demo", bean.getContent());
            context.startActivity(intent);

        }

    }
}
