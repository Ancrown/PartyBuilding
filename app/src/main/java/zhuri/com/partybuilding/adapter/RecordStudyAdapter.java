package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.StudyDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.RecordStudyBean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/30
 * 描述:
 */

public class RecordStudyAdapter extends BaseRecyclerAdapter<RecordStudyBean> {


    public RecordStudyAdapter(Context context) {
        super(context);
    }

    @Override
    public CommonHolder<RecordStudyBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_record_study);
    }

    class Holder extends CommonHolder<RecordStudyBean> {
        @BindView(R.id.item_record_study_title)
        TextView itemRecordStudyTitle;
        @BindView(R.id.item_record_study_endtime)
        TextView itemRecordStudyEndtime;
        @BindView(R.id.item_record_study_ll)
        LinearLayout itemRecordStudyLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final RecordStudyBean bean, int i) {
            itemRecordStudyTitle.setText(bean.getTitle());
            itemRecordStudyEndtime.setText(bean.getTime());
            itemRecordStudyLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, StudyDetailActivity.class)
                            .putExtra("id", bean.getId()).putExtra("cid", bean.getType()));
                }
            });
        }
    }
}
