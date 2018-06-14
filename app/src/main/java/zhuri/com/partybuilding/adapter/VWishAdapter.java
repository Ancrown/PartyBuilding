package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.VWishBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
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

        @BindView(R.id.item_v_wish_view)
        View itemVWishView;
        @BindView(R.id.item_v_wish_title)
        TextView itemVWishTitle;
        @BindView(R.id.item_v_wish_istop)
        TextView itemVWishIstop;
        @BindView(R.id.item_v_wish_content)
        TextView itemVWishContent;
        @BindView(R.id.item_v_wish_button)
        Button itemVWishButton;
        @BindView(R.id.item_v_wish_time)
        TextView itemVWishTime;
        @BindView(R.id.item_v_wish_integral)
        TextView itemVWishIntegral;
        @BindView(R.id.item_v_wish_ll)
        LinearLayout itemVWishLl;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final VWishBean bean, int i) {
            itemVWishTitle.setText(bean.getTitle());
            itemVWishContent.setText(bean.getContent());
            itemVWishTime.setText("发布时间：" + bean.getTime());
            itemVWishIntegral.setText(bean.getIntegral() + "积分");
            if ("0".equals(bean.getIsTop())) {
                itemVWishIstop.setVisibility(View.VISIBLE);
            } else {
                itemVWishIstop.setVisibility(View.GONE);
            }
            if ("0".equals(bean.getType())) {
                itemVWishView.setBackgroundColor(AppUtils.getColor(R.color.red));
                itemVWishButton.setText("认领");
                itemVWishButton.setBackgroundColor(AppUtils.getColor(R.color.light_red));

                itemVWishTime.setTextColor(AppUtils.getColor(R.color.red));
                itemVWishIntegral.setTextColor(AppUtils.getColor(R.color.red));
            } else {
                itemVWishView.setBackgroundColor(AppUtils.getColor(R.color.gray));
                itemVWishButton.setText("已认领");
                itemVWishButton.setBackgroundColor(AppUtils.getColor(R.color.gray));

                itemVWishTime.setTextColor(AppUtils.getColor(R.color.gray));
                itemVWishIntegral.setTextColor(AppUtils.getColor(R.color.gray));
            }


            itemVWishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getPurview().equals("0")) {
                        ToolUtils.showToast(context, "认领成功");
                    } else {
                        ToolUtils.showToast(context, "已认领");
                    }
                }
            });

            itemVWishLl.setOnClickListener(new View.OnClickListener() {
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
