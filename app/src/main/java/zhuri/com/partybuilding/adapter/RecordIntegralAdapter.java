package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.AnswerResultActivity;
import zhuri.com.partybuilding.activity.StudyDetailActivity;
import zhuri.com.partybuilding.activity.activitiesdetail.ActivitiesReportDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.RecordIntegralBean;
import zhuri.com.partybuilding.util.AppUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/29
 * 描述:
 */

public class RecordIntegralAdapter extends BaseRecyclerAdapter<RecordIntegralBean> {


    public RecordIntegralAdapter(Context context) {
        super(context);
    }

    @Override
    public CommonHolder<RecordIntegralBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_record_integral);
    }


    class Holder extends CommonHolder<RecordIntegralBean> {
        @BindView(R.id.item_record_integral_label)
        TextView itemRecordIntegralLabel;
        @BindView(R.id.item_record_integral_title)
        TextView itemRecordIntegralTitle;
        @BindView(R.id.item_record_integral_time)
        TextView itemRecordIntegralTime;
        @BindView(R.id.item_record_integral_add)
        TextView itemRecordIntegralAdd;
        @BindView(R.id.item_record_integral_rl)
        RelativeLayout itemRecordIntegralRl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final RecordIntegralBean bean, int i) {
            itemRecordIntegralTitle.setText(bean.getTitle());
            itemRecordIntegralTime.setText(bean.getTime());
            itemRecordIntegralAdd.setText("+" + bean.getIntegral());
            switch (bean.getType()) {
                case "0":
                    //活动
                    itemRecordIntegralLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                    if (bean.getActivitiesTyep().equals("0")) {
                        itemRecordIntegralLabel.setText("活动" + "/社区活动");
                    } else {
                        itemRecordIntegralLabel.setText("活动" + "/微志愿");
                    }

                    break;
                case "1":
                    //学习
                    itemRecordIntegralLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_orange_4));
                    if (bean.getActivitiesTyep().equals("0")) {
                        itemRecordIntegralLabel.setText("学习" + "/十九大报告");
                    } else if (bean.getActivitiesTyep().equals("1")) {
                        itemRecordIntegralLabel.setText("学习" + "/两学一做");
                    } else {
                        itemRecordIntegralLabel.setText("学习" + "/党务工作");
                    }
                    break;
                case "2":
                    //考试
                    itemRecordIntegralLabel.setText("考试");
                    itemRecordIntegralLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_green_4));
                    break;
            }
            itemRecordIntegralRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (bean.getType()) {
                        case "0":
                            //活动
                            context.startActivity(new Intent(context, ActivitiesReportDetailActivity.class).putExtra("id", bean.getId()).putExtra("type", bean.getActivitiesTyep()));
                            break;
                        case "1":
                            //学习
                            context.startActivity(new Intent(context, StudyDetailActivity.class).putExtra("id", bean.getId()).putExtra("type", bean.getActivitiesTyep()));
                            break;
                        case "2":
                            //考试
                            context.startActivity(new Intent(context, AnswerResultActivity.class).putExtra("id", bean.getId()));
                            break;
                    }
                }
            });
        }
    }
}
