package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.activitiesdetail.ActivitiesDetail;
import zhuri.com.partybuilding.activity.activitiesdetail.ActivitiesReportDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.ActivitiesItemBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:
 */

public class ActivitiesAdapter extends BaseRecyclerAdapter<ActivitiesItemBean> {


    private String type;

    private boolean isLogin;

    public ActivitiesAdapter(Context context, String type) {
        super(context);
        this.type = type;
        isLogin = TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, "") + "");

    }

    @Override
    public CommonHolder<ActivitiesItemBean> setViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new Holder(parent.getContext(), parent, R.layout.item_activities);
            case 2:
                return new HolderTwo(parent.getContext(), parent, R.layout.item_activities_two);
            case 3:
                return new HolderThree(parent.getContext(), parent, R.layout.item_activities_three);
            default:
                return new Holder(parent.getContext(), parent, R.layout.item_activities);
        }
    }


    @Override
    public int getItemViewType(int position) {

        return getItemList().get(position).getType() == 4 ? 1 : getItemList().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return getItemList().size();

    }

    class Holder extends CommonHolder<ActivitiesItemBean> {
        @BindView(R.id.relativeLayout)
        RelativeLayout relativeLayout;
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
        @BindView(R.id.item_activities_time)
        TextView itemActivitiesTime;
        @BindView(R.id.item_activities_ll)
        LinearLayout itemActivitiesLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final ActivitiesItemBean bean, final int i) {

            if (bean.getType() == 4) {
                relativeLayout.setVisibility(View.GONE);
            } else {
                relativeLayout.setVisibility(View.VISIBLE);
                GlideUtils.LoadImage(context, bean.getImageurl().split("#")[0], itemActivitiesImg);
            }
            itemActivitiesTitle.setText(bean.getTitle());


            if (bean.getStatus().equals("0")) {
                itemActivitiesType.setText("报名中");
                itemActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.label_yes));
                switch (bean.getFlag()) {
                    case "0":
                        itemActivitiesIstop.setVisibility(View.GONE);
                        break;
                    case "1":
                        itemActivitiesIstop.setVisibility(View.VISIBLE);
                        itemActivitiesIstop.setText("推荐");
                        itemActivitiesIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                        break;
                    case "2":
                        itemActivitiesIstop.setVisibility(View.VISIBLE);
                        itemActivitiesIstop.setText("置顶");
                        itemActivitiesIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                        break;
                }
            } else {
                itemActivitiesType.setText("已结束");
                itemActivitiesType.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.label_no));
                itemActivitiesIstop.setVisibility(View.VISIBLE);
                itemActivitiesIstop.setText("报道");
                itemActivitiesIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_orange_4));
            }

            itemActivitiesAddress.setText(bean.getAddress());
            itemActivitiesTime.setText(TimeUtil.stampToDate(bean.getAddtime(),"yyyy-MM-dd"));

            itemActivitiesLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    look(isLogin, bean.getPurview().equals("0"), bean.getStatus(), bean.getId());
                }
            });


        }
    }

    class HolderTwo extends CommonHolder<ActivitiesItemBean> {


        @BindView(R.id.item_activities_two_title)
        TextView itemActivitiesTwoTitle;
        @BindView(R.id.item_activities_two_istop)
        TextView itemActivitiesTwoIstop;
        @BindView(R.id.item_activities_two_img_one)
        ImageView itemActivitiesTwoImgOne;
        @BindView(R.id.item_activities_two_img_two)
        ImageView itemActivitiesTwoImgTwo;
        @BindView(R.id.item_activities_two_ll)
        LinearLayout itemActivitiesTwoLl;

        public HolderTwo(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final ActivitiesItemBean bean, int i) {

            itemActivitiesTwoTitle.setText(bean.getTitle());


            if (bean.getStatus().equals("0")) {
                switch (bean.getFlag()) {
                    case "0":
                        itemActivitiesTwoIstop.setVisibility(View.GONE);
                        break;
                    case "1":
                        itemActivitiesTwoIstop.setVisibility(View.VISIBLE);
                        itemActivitiesTwoIstop.setText("推荐");
                        itemActivitiesTwoIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                        break;
                    case "2":
                        itemActivitiesTwoIstop.setVisibility(View.VISIBLE);
                        itemActivitiesTwoIstop.setText("置顶");
                        itemActivitiesTwoIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                        break;
                }
            } else {
                itemActivitiesTwoIstop.setVisibility(View.VISIBLE);
                itemActivitiesTwoIstop.setText("报道");
                itemActivitiesTwoIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_orange_4));
            }


            GlideUtils.LoadImage(context, bean.getImageurl().split("#")[0], itemActivitiesTwoImgOne);
            GlideUtils.LoadImage(context, bean.getImageurl().split("#")[1], itemActivitiesTwoImgTwo);

            itemActivitiesTwoLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    look(isLogin, bean.getPurview().equals("0"), bean.getStatus(), bean.getId());
                }
            });
        }
    }

    class HolderThree extends CommonHolder<ActivitiesItemBean> {
        @BindView(R.id.item_activities_three_title)
        TextView itemActivitiesThreeTitle;
        @BindView(R.id.item_activities_three_istop)
        TextView itemActivitiesThreeIstop;
        @BindView(R.id.item_activities_three_img_one)
        ImageView itemActivitiesThreeImgOne;
        @BindView(R.id.item_activities_three_img_two)
        ImageView itemActivitiesThreeImgTwo;
        @BindView(R.id.item_activities_three_img_three)
        ImageView itemActivitiesThreeImgThree;
        @BindView(R.id.item_activities_three_ll)
        LinearLayout itemActivitiesThreeLl;

        public HolderThree(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final ActivitiesItemBean bean, int i) {


            itemActivitiesThreeTitle.setText(bean.getTitle());


            if (bean.getStatus().equals("0")) {
                switch (bean.getFlag()) {
                    case "0":
                        itemActivitiesThreeIstop.setVisibility(View.GONE);
                        break;
                    case "1":
                        itemActivitiesThreeIstop.setVisibility(View.VISIBLE);
                        itemActivitiesThreeIstop.setText("推荐");
                        itemActivitiesThreeIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                        break;
                    case "2":
                        itemActivitiesThreeIstop.setVisibility(View.VISIBLE);
                        itemActivitiesThreeIstop.setText("置顶");
                        itemActivitiesThreeIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
                        break;
                }
            } else {
                itemActivitiesThreeIstop.setVisibility(View.VISIBLE);
                itemActivitiesThreeIstop.setText("报道");
                itemActivitiesThreeIstop.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_orange_4));
            }


            GlideUtils.LoadImage(context, bean.getImageurl().split("#")[0], itemActivitiesThreeImgOne);
            GlideUtils.LoadImage(context, bean.getImageurl().split("#")[1], itemActivitiesThreeImgTwo);
            GlideUtils.LoadImage(context, bean.getImageurl().split("#")[2], itemActivitiesThreeImgThree);

            itemActivitiesThreeLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    look(isLogin, bean.getPurview().equals("0"), bean.getStatus(), bean.getId());
                }
            });
        }
    }

    //跳页 type判断是 查看报道 1  报名  0
    public void look(boolean isLogin, boolean purview, String type, String id) {

        if (isLogin) {
            //没登录
            if (purview) {
                if (type.equals("0")) {
                    context.startActivity(new Intent(context, ActivitiesDetail.class).putExtra("type", this.type).putExtra("id", id));
                } else {
                    context.startActivity(new Intent(context, ActivitiesReportDetailActivity.class).putExtra("type", this.type).putExtra("id", id));
                }
            } else {
                ToolUtils.showToast(context, "游客不可");
            }
        } else {
            //登陆了
            if (type.equals("0")) {
                context.startActivity(new Intent(context, ActivitiesDetail.class).putExtra("type", this.type).putExtra("id", id));
            } else {
                context.startActivity(new Intent(context, ActivitiesReportDetailActivity.class).putExtra("type", this.type).putExtra("id", id));
            }

        }

    }
}
