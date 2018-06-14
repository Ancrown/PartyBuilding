package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:
 */

public class ActivitiesAdapter extends BaseRecyclerAdapter<ActivitiesItemBean> {



    private boolean isLogin;

    public ActivitiesAdapter(Context context) {
        super(context);
        isLogin = TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, "") + "");

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


            if (bean.getType().equals("0") || bean.getType().equals("3")) {
                itemActivitiesTitle.setTextColor(context.getResources().getColor(R.color.black));
                itemActivitiesTime.setTextColor(context.getResources().getColor(R.color.gray));
                view.setBackgroundColor(AppUtils.getColor(R.color.gray));
                itemActivitiesType.setText(bean.getType().equals("0") ? "未开始" : "已结束");
                itemActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.label_no));
                itemActivitiesButton.setText(bean.getType().equals("0") ? "未开始" : "已结束");
                itemActivitiesButton.setBackgroundColor(AppUtils.getColor(R.color.gray));

            } else {
                view.setBackgroundColor(AppUtils.getColor(R.color.red));
                itemActivitiesTitle.setTextColor(context.getResources().getColor(R.color.red));
                itemActivitiesTime.setTextColor(context.getResources().getColor(R.color.light_red));
                itemActivitiesType.setText("进行中");
                itemActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.label_yes));
                itemActivitiesButton.setBackgroundColor(AppUtils.getColor(R.color.light_red));
                itemActivitiesButton.setText(bean.getType().equals("1") ? "报名" : "已报名");
            }


            itemActivitiesTitle.setText(bean.getTitle());

            if (bean.getIsTop().equals("0")) {
                itemActivitiesIstop.setVisibility(View.VISIBLE);
            } else {
                itemActivitiesIstop.setVisibility(View.GONE);
            }

            if (TextUtils.isEmpty(bean.getAddress())) {
                itemActivitiesAddress.setVisibility(View.GONE);
            } else {
                itemActivitiesAddress.setText("地址：" + bean.getAddress());
                itemActivitiesAddress.setVisibility(View.VISIBLE);
            }
            itemActivitiesCommentJoin.setText(bean.getComment() + "预览    " + (TextUtils.isEmpty(bean.getJoin()) ? "" : bean.getJoin() + "人已参与"));

            if (TextUtils.isEmpty(bean.getStartTime()) || TextUtils.isEmpty(bean.getEndTime())) {
                itemActivitiesTime.setVisibility(View.GONE);
            } else {
                itemActivitiesTime.setText("活动时间：" + bean.getStartTime() + "~" + bean.getEndTime());
                itemActivitiesTime.setVisibility(View.VISIBLE);
            }

            itemActivitiesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (bean.getType().equals("0") || bean.getType().equals("3")) {
                        if (bean.getType().equals("0")) {
                            ToolUtils.showToast(context, "未开始");
                        } else {
                            ToolUtils.showToast(context, "已结束");
                        }
                    } else {
                        if (bean.getType().equals("1")) {
                            ToolUtils.showToast(context, "报名");
                        } else {
                            ToolUtils.showToast(context, "已报名");
                        }
                    }

                }
            });
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLogin && bean.getPurview().equals("0")) {
                        Log.e("eeeeee", "查看： NO ×××××××");
                    } else {

                        Log.e("eeeeee", "查看： YES √√√√√√√");
                    }
                }
            });
        }
    }
}
