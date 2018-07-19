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
import zhuri.com.partybuilding.activity.LoginActivity;
import zhuri.com.partybuilding.activity.NewsDetailActivity;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/24
 * 描述:
 */

public class HomePageAdapter extends BaseRecyclerAdapter<HomePageItemBean> {
    public boolean showLabel;


    public String title;

    public HomePageAdapter(Context context) {
        super(context);

    }

    public HomePageAdapter(boolean showLabel, Context context, String title) {
        super(context);
        this.showLabel = showLabel;
        this.title = title;
    }

    @Override
    public CommonHolder<HomePageItemBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_home_page);
    }

    class Holder extends CommonHolder<HomePageItemBean> {
        @BindView(R.id.item_home_page_ll)
        RelativeLayout itemHomePageLl;
        @BindView(R.id.item_home_page_img)
        ImageView itemHomePageImg;
        @BindView(R.id.item_home_page_label)
        TextView itemHomePageLabel;
        @BindView(R.id.item_home_page_title)
        TextView itemHomePageTitle;
        @BindView(R.id.item_home_page_text)
        TextView itemHomePageText;
        @BindView(R.id.item_home_page_time)
        TextView itemHomePageTime;
        @BindView(R.id.item_home_page_fabulous_num)
        TextView itemHomePageFabulousNum;
        @BindView(R.id.item_home_page_see_num)
        TextView itemHomePageSeeNum;

        public Holder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
        }

        @Override
        public void bindData(final HomePageItemBean homePageItemBean, final int i) {

            GlideUtils.LoadImage(getContext(), homePageItemBean.getImg(), itemHomePageImg);
            if (!showLabel) {
                if (TextUtils.isEmpty(homePageItemBean.getLabel())) {
                    itemHomePageLabel.setVisibility(View.GONE);
                } else {
                    itemHomePageLabel.setVisibility(View.VISIBLE);
                    itemHomePageLabel.setText(homePageItemBean.getLabel());
                }
            } else {
                itemHomePageLabel.setVisibility(View.GONE);
            }
            itemHomePageTitle.setText(homePageItemBean.getTitle());
            itemHomePageText.setText(homePageItemBean.getText());
            itemHomePageTime.setText(TimeUtil.stampToDate(homePageItemBean.getTime(), "yyyy-MM-dd"));
            itemHomePageFabulousNum.setText(homePageItemBean.getFabulousNum());
            itemHomePageSeeNum.setText(homePageItemBean.getSeeNum());
            itemHomePageLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItem != null) {
                        onClickItem.onItem(homePageItemBean.getId(), i);
                    }

                    look(isLogin, homePageItemBean.getPurview().equals("0"), homePageItemBean.getId());


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
                context.startActivity(new Intent(new Intent(context, NewsDetailActivity.class).putExtra("id", id).putExtra("title",title)));
            } else {
                ToolUtils.showToast(context, "游客不可看，请先登陆");
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        } else
            context.startActivity(new Intent(new Intent(context, NewsDetailActivity.class).putExtra("id", id).putExtra("title",title)));

    }

    /**
     * 点击item
     */
    public OnClickItem onClickItem;


    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onItem(String t, int i);
    }
}
