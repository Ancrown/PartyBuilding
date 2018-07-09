package zhuri.com.partybuilding.view.bannerview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import zhuri.com.partybuilding.util.glideutils.GlideUtils;


/**
 * 创建人: Administrator
 * 创建时间: 2018/7/4
 * 描述:
 */

public class BannerViewFactory implements BannerView.ViewFactory<BannerItem> {
    @Override
    public View create(BannerItem item, final int position, ViewGroup container) {
        ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClikImg != null) {
                    onClikImg.onItemImg(position);
                }
            }
        });
        Log.e("eeeeeeeee", item.image);
        GlideUtils.LoadImage(container.getContext().getApplicationContext(), item.image, iv);
        return iv;
    }

    public OnClikImg onClikImg;


    public void setOnClikImg(OnClikImg onClikImg) {
        this.onClikImg = onClikImg;
    }

    public interface OnClikImg {
        void onItemImg(int i);
    }
}