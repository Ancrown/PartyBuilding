package zhuri.com.partybuilding.view.bannerview;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/4
 * 描述:
 */

public class BannerItem {
    public String image;
    public String title;
    public String id;

    public BannerItem() {
    }

    public BannerItem(String image, String title, String id) {
        this.image = image;
        this.title = title;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}
