package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.AnswerConfirmActivity;
import zhuri.com.partybuilding.activity.AnswerNewsResultActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.ExaminationBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.TextViewUitl;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class ExaminationAdapter extends BaseRecyclerAdapter<ExaminationBean> {


    public ExaminationAdapter(Context context) {
        super(context);
    }

    @Override
    public CommonHolder<ExaminationBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_examination);
    }

    class Holder extends CommonHolder<ExaminationBean> {

        @BindView(R.id.item_examination_title)
        TextView itemExaminationTitle;
        @BindView(R.id.item_examination_label)
        TextView itemExaminationLabel;
        @BindView(R.id.item_examination_my_s)
        TextView itemExaminationMyS;
        @BindView(R.id.item_examination_endtime)
        TextView itemExaminationEndtime;
        @BindView(R.id.item_examination_join)
        TextView itemExaminationJoin;
        @BindView(R.id.item_examination_ll)
        LinearLayout itemExaminationLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final ExaminationBean bean, final int i) {
            itemExaminationTitle.setText(bean.getTitle());

            if (bean.getIsjoin().equals("0")) {
                itemExaminationLabel.setVisibility(View.VISIBLE);
                itemExaminationMyS.setVisibility(View.INVISIBLE);

                if (bean.getStatus().equals("0")) {
                    itemExaminationLabel.setText("进行中");
                    itemExaminationLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                } else {
                    itemExaminationLabel.setText("已过期");
                    itemExaminationLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_light_gray_4));
                }
                itemExaminationEndtime.setText(bean.getEtime() + " 截止");
                itemExaminationJoin.setVisibility(View.VISIBLE);
                itemExaminationJoin.setText(bean.getAmount() + "人参与");
            } else {
                itemExaminationLabel.setVisibility(View.INVISIBLE);
                itemExaminationMyS.setVisibility(View.VISIBLE);

                itemExaminationEndtime.setText("参加时间：" + bean.getAddTime());
                TextViewUitl.toStringChangeSizeAndColor(bean.getMyscore() + "分", bean.getMyscore(), 20, "#d5605f", itemExaminationMyS);
                itemExaminationJoin.setVisibility(View.GONE);
            }


            itemExaminationLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    look(isLogin, bean.getPurview().equals("0"), bean.getStatus(), bean.getIsjoin(), bean);
                }
            });
        }
    }

    //跳页 type判断是 查看报道 1  报名  0
    public void look(boolean isLogin, boolean purview, String status, String isjoin, ExaminationBean bean) {

        if (isLogin) {
            //没登录
            if (purview) {
                if (isjoin.equals("0")) {
                    if (status.equals("0")) {
                        Intent intent = new Intent(context, AnswerConfirmActivity.class);
                        intent.putExtra("id", bean.getId());
                        intent.putExtra("title", bean.getTitle());
                        intent.putExtra("score", bean.getScore());
                        intent.putExtra("amount", bean.getAmount());
                        intent.putExtra("times", bean.getTimes());
                        intent.putExtra("stime", bean.getStime());
                        intent.putExtra("etime", bean.getEtime());
                        intent.putExtra("demo", bean.getDemo());
                        context.startActivity(intent);
                    } else {
                        ToolUtils.showToast(context, "当前试卷已过期！");
                    }
                } else {

                    Intent intent = new Intent(context, AnswerNewsResultActivity.class);
                    intent.putExtra("type", "type");
                    intent.putExtra("id", bean.getId());
                    intent.putExtra("title", bean.getTitle());
                    intent.putExtra("longTime", bean.getTimes());
                    intent.putExtra("score", bean.getScore());
                    context.startActivity(intent);
                    //  ToolUtils.showToast(context, "正在做查看试卷页!");
                }
            } else {
                ToolUtils.showToast(context, "请去登陆再来！");
            }
        } else {
            //登陆了
            if (isjoin.equals("0")) {
                if (status.equals("0")) {
                    Intent intent = new Intent(context, AnswerConfirmActivity.class);
                    intent.putExtra("id", bean.getId());
                    intent.putExtra("title", bean.getTitle());
                    intent.putExtra("score", bean.getScore());
                    intent.putExtra("amount", bean.getAmount());
                    intent.putExtra("times", bean.getTimes());
                    intent.putExtra("stime", bean.getStime());
                    intent.putExtra("etime", bean.getEtime());
                    intent.putExtra("demo", bean.getDemo());
                    context.startActivity(intent);
                } else {
                    ToolUtils.showToast(context, "当前试卷已过期！");
                }
            } else {
                Intent intent = new Intent(context, AnswerNewsResultActivity.class);
                intent.putExtra("type", "type");
                intent.putExtra("id", bean.getId());
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("longTime", bean.getTimes());
                intent.putExtra("score", bean.getScore());
                context.startActivity(intent);
                //  ToolUtils.showToast(context, "正在做查看试卷页!");
            }


        }
    }

}



