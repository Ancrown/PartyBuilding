package zhuri.com.partybuilding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 监听滑到底部的webview
 */

public class SlideBottomWebView extends WebView {
    public ScrollInterface mScrollInterface;

    public SlideBottomWebView(Context context) {
        super(context);
    }

    public SlideBottomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SlideBottomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);

        mScrollInterface.onSChanged(l, t, oldl, oldt);

    }

    public void setOnCustomScroolChangeListener(ScrollInterface scrollInterface) {

        this.mScrollInterface = scrollInterface;

    }

    public interface ScrollInterface {

        public void onSChanged(int l, int t, int oldl, int oldt);

    }

}