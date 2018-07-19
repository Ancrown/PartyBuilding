package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.VWishDetailActivity;
import zhuri.com.partybuilding.activity.activitiesdetail.ActivitiesDetail;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.RecordActivitiesItemBean;
import zhuri.com.partybuilding.util.AppUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/9
 * 描述:
 */

public class RecordActivitiesAdapter extends BaseRecyclerAdapter<RecordActivitiesItemBean> {

    private String type;
    private String[] typeString;

    public RecordActivitiesAdapter(Context context, String type) {
        super(context);
        this.type = type;
        if (type.equals("4")) {
            typeString = new String[]{"未完成", "已完成"};
        } else {
            typeString = new String[]{"未参与", "已参与"};
        }
    }

    @Override
    public CommonHolder<RecordActivitiesItemBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_record_activities);
    }

    class Holder extends CommonHolder<RecordActivitiesItemBean> {
        @BindView(R.id.item_record_activities_title)
        TextView itemRecordActivitiesTitle;
        @BindView(R.id.item_record_activities_time)
        TextView itemRecordActivitiesTime;
        @BindView(R.id.item_record_activities_type)
        TextView itemRecordActivitiesType;
        @BindView(R.id.item_record_activities_rl)
        RelativeLayout itemRecordActivitiesRl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final RecordActivitiesItemBean bean, int i) {

            itemRecordActivitiesTitle.setText(bean.getTitle());
            itemRecordActivitiesTime.setText(bean.getTime());
            if (bean.getType().equals("0")) {
                itemRecordActivitiesType.setText(typeString[0]);
                itemRecordActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_orange_4));
            } else {
                itemRecordActivitiesType.setText(typeString[1]);
                itemRecordActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
            }
            itemRecordActivitiesRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type.equals("2") || type.equals("3")) {
                        context.startActivity(new Intent(context, ActivitiesDetail.class).putExtra("type", type).putExtra("id", bean.getId()));
                    } else {
                        context.startActivity(new Intent(context, VWishDetailActivity.class).putExtra("id", bean.getId()));
                    }
                }
            });
        }
    }
}
