package zhuri.com.partybuilding.bean;

import java.io.Serializable;

/**
 * 创建人:Administrator
 * 创建时间:2018/5/14
 * 描述:
 */
public class AnswerItemBean  implements Serializable{
    private String id;
    private String text;
    private int type;
    private boolean isOnClick = true;



    public AnswerItemBean(String id, String text) {
        this.id = id;
        this.text = text;

    }

    public AnswerItemBean(String id, String text, int type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public boolean isOnClick() {
        return isOnClick;
    }

    public void setOnClick(boolean onClick) {
        isOnClick = onClick;
    }

    public AnswerItemBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
