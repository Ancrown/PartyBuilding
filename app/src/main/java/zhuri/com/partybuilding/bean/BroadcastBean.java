package zhuri.com.partybuilding.bean;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/7
 * 描述:
 */

public class BroadcastBean {
    private List<String> id;
    private List<String> img;
    private List<String> text;

    public BroadcastBean(List<String> id, List<String> img, List<String> text) {
        this.id = id;
        this.img = img;
        this.text = text;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
