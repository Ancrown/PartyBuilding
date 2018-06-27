package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/9
 * 描述:
 */

public class VWishBean {
    private String id;
    private String title;
    private String isTop;
    private String content;
    private String type;
    private String time;
    private String endtime;
    private String integral;
    private String purview;
    private String peopleNum;
    private String send;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public VWishBean(String id, String title, String isTop, String content, String type, String time, String endtime, String integral, String purview, String peopleNum, String send) {

        this.id = id;
        this.title = title;
        this.isTop = isTop;
        this.content = content;
        this.type = type;
        this.time = time;
        this.endtime = endtime;
        this.integral = integral;
        this.purview = purview;
        this.peopleNum = peopleNum;
        this.send = send;
    }
}