package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/24
 * 描述:
 */

public class HomePageItemBean {
    //id
    private String id;
    //图片
    private String img;
    //标签
    private String label;
    //标题
    private String title;
    //内容
    private String text;
    //时间
    private String time;
    //赞数
    private String fabulousNum;
    //浏览数
    private String seeNum;

    public HomePageItemBean(String id, String img, String label, String title, String text, String time, String fabulousNum, String seeNum) {
        this.id = id;
        this.img = img;
        this.label = label;
        this.title = title;
        this.text = text;
        this.time = time;
        this.fabulousNum = fabulousNum;
        this.seeNum = seeNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFabulousNum() {
        return fabulousNum;
    }

    public void setFabulousNum(String fabulousNum) {
        this.fabulousNum = fabulousNum;
    }

    public String getSeeNum() {
        return seeNum;
    }

    public void setSeeNum(String seeNum) {
        this.seeNum = seeNum;
    }
}
