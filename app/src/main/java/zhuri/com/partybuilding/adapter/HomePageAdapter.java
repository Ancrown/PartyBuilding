package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import zhuri.com.partybuilding.activity.NewsDetailActivity;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.adapter.base.BaseRecyclerAdapter;
import zhuri.com.partybuilding.adapter.base.CommonHolder;
import zhuri.com.partybuilding.bean.HomePageItemBean;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/24
 * 描述:
 */

public class HomePageAdapter extends BaseRecyclerAdapter<HomePageItemBean> {
    public boolean showLabel;

    public HomePageAdapter(Context context) {
        super(context);

    }
    public HomePageAdapter(boolean showLabel, Context context) {
        super(context);
        this.showLabel = showLabel;
    }

    @Override
    public CommonHolder<HomePageItemBean> setViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent.getContext(), parent, R.layout.item_home_page);
    }

    class Holder extends CommonHolder<HomePageItemBean> {
        @BindView(R.id.item_home_page_ll)
        LinearLayout itemHomePageLl;
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
                itemHomePageLabel.setVisibility(View.VISIBLE);
                itemHomePageLabel.setText(homePageItemBean.getLabel());
            } else {
                itemHomePageLabel.setVisibility(View.GONE);
            }
            itemHomePageTitle.setText(homePageItemBean.getTitle());
            itemHomePageText.setText(homePageItemBean.getText());
            itemHomePageTime.setText(homePageItemBean.getTime());
            itemHomePageFabulousNum.setText(homePageItemBean.getFabulousNum());
            itemHomePageSeeNum.setText(homePageItemBean.getSeeNum());
            itemHomePageLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItem != null) {
                        onClickItem.onItem(homePageItemBean.getId(), i);
                    }
                    getContext().startActivity(new Intent(new Intent(getContext(), NewsDetailActivity.class)));

                }
            });
        }
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
