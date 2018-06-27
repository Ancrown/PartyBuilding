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
import zhuri.com.partybuilding.activity.StudyDetailActivity;
import zhuri.com.partybuilding.activity.VWishDetailActivity;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.VWishBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TextViewUitl;
import zhuri.com.partybuilding.util.ToolUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/9
 * 描述:
 */

public class VWishAdapter extends BaseRecyclerAdapter<VWishBean> {

    private boolean isLogin;

    public VWishAdapter(Context context) {
        super(context);
        isLogin = TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, "") + "");
    }

    @Override
    public CommonHolder<VWishBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_v_wish);
    }

    class Holder extends CommonHolder<VWishBean> {

        @BindView(R.id.item_v_wish_title)
        TextView itemVWishTitle;
        @BindView(R.id.item_v_wish_label)
        TextView itemVWishLabel;
        @BindView(R.id.item_v_wish_time)
        TextView itemVWishTime;
        @BindView(R.id.item_v_wish_num)
        TextView itemVWishNum;
        @BindView(R.id.item_v_wish_send)
        TextView itemVWishSend;

        @BindView(R.id.item_v_wish_ll)
        LinearLayout itemVWishLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final VWishBean bean, int i) {
            itemVWishTitle.setText(bean.getTitle());

            itemVWishTime.setText(bean.getTime() + " -- " + bean.getEndtime());
            String num = bean.getPeopleNum() + "人";
            TextViewUitl.toStringChangeColor("人数：" + num, num, "#d5605f", itemVWishNum);
            itemVWishSend.setText("发布者：" + bean.getSend());
            if ("0".equals(bean.getType())) {

                itemVWishLabel.setText("可认领");
                itemVWishLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red_4));
            } else {
                itemVWishLabel.setText("已认领");
                itemVWishLabel.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_light_gray_4));
            }


            itemVWishLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    look(isLogin, bean.getPurview().equals("0"), bean.getId());
                }
            });

        }
    }

    //跳页 type判断是 查看报道 1  报名  0
    public void look(boolean isLogin, boolean purview, String id) {

        if (isLogin) {
            //没登录
            if (purview) {
                //游客可看
                context.startActivity(new Intent(context,VWishDetailActivity.class).putExtra("id",id));
            } else {
                ToolUtils.showToast(context, "游客不可看");
            }
        } else
            context.startActivity(new Intent(context,VWishDetailActivity.class).putExtra("id",id));

    }
}
