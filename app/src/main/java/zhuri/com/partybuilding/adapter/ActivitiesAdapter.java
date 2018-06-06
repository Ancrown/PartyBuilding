package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:
 */

public class ActivitiesAdapter extends BaseRecyclerAdapter<ActivitiesItemBean> {


    public ActivitiesAdapter(Context context) {
        super(context);
    }

    @Override
    public CommonHolder<ActivitiesItemBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Hodler(parent.getContext(), parent, R.layout.item_activities);
    }

    class Hodler extends CommonHolder<ActivitiesItemBean> {

        @BindView(R.id.item_activities_ll)
        LinearLayout ll;
        @BindView(R.id.item_activities_view)
        View view;
        @BindView(R.id.item_activities_img)
        ImageView itemActivitiesImg;
        @BindView(R.id.item_activities_type)
        TextView itemActivitiesType;
        @BindView(R.id.item_activities_title)
        TextView itemActivitiesTitle;
        @BindView(R.id.item_activities_istop)
        TextView itemActivitiesIstop;
        @BindView(R.id.item_activities_address)
        TextView itemActivitiesAddress;
        @BindView(R.id.item_activities_comment_join)
        TextView itemActivitiesCommentJoin;
        @BindView(R.id.item_activities_time)
        TextView itemActivitiesTime;
        @BindView(R.id.item_activities_button)
        Button itemActivitiesButton;

        public Hodler(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final ActivitiesItemBean bean, final int i) {

            GlideUtils.LoadImage(getContext(), bean.getImg(), itemActivitiesImg);
            switch (bean.getType().substring(0, 1)) {
                case "0":
                    view.setBackgroundColor(AppUtils.getColor(R.color.red));
                    itemActivitiesTitle.setTextColor(context.getResources().getColor(R.color.red));
                    itemActivitiesTime.setTextColor(context.getResources().getColor(R.color.light_red));
                    itemActivitiesType.setText("进行中");
                    itemActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.label_yes));
                    itemActivitiesButton.setBackgroundColor(AppUtils.getColor(R.color.light_red));
                    if (bean.getType().equals("0.0")) {
                        itemActivitiesButton.setText("报名");
                    } else if (bean.getType().equals("0.1")) {
                        itemActivitiesButton.setText("已报名");
                    }
                    break;
                case "1":
                    itemActivitiesTitle.setTextColor(context.getResources().getColor(R.color.black));
                    itemActivitiesTime.setTextColor(context.getResources().getColor(R.color.gray));
                    view.setBackgroundColor(AppUtils.getColor(R.color.gray));
                    itemActivitiesType.setText("已结束");
                    itemActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.label_no));
                    itemActivitiesButton.setText("已结束");
                    itemActivitiesButton.setBackgroundColor(AppUtils.getColor(R.color.gray));
                    break;

            }
            itemActivitiesTitle.setText(bean.getTitle());
            if (bean.getIsTop().equals("0")) {
                itemActivitiesIstop.setVisibility(View.VISIBLE);
            } else {
                itemActivitiesIstop.setVisibility(View.GONE);
            }

            itemActivitiesAddress.setText("地址：" + bean.getAddress());
            itemActivitiesCommentJoin.setText(bean.getComment() + "条评论    " + bean.getJoin() + "人已参与");
            itemActivitiesTime.setText("活动时间：" + bean.getTime());

            itemActivitiesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (bean.getType().substring(0, 1)) {
                        case "0":
                            if (bean.getType().equals("0.0")) {
                                ToolUtils.showToast(getContext(), "恭喜 报名成功！");
                            } else if (bean.getType().equals("0.1")) {
                                ToolUtils.showToast(getContext(), "抱歉 你已报名！");
                            }
                            break;
                        case "1":
                            ToolUtils.showToast(getContext(), "抱歉 活动结束！");
                            break;

                    }
                }
            });
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToolUtils.showToast(getContext(), "点击我了！" + i);
                }
            });
        }
    }
}
