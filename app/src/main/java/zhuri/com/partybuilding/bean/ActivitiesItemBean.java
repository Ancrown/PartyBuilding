package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述: 活动描述
 */

public class ActivitiesItemBean {
    private String id;
    private String img;
    //  private String progress; //进度
    private String title;
    private String isTop; //是否顶置
    private String address;
    private String comment;
    private String join;
    private String endTime;
    private String startTime;

    private String type; // 0:进行0.1:未报名 0.2:已报名   1:结束

    private String purview;

    public ActivitiesItemBean(String id, String img, String title, String isTop, String address, String comment, String join, String endTime, String startTime, String type, String purview) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.isTop = isTop;
        this.address = address;
        this.comment = comment;
        this.join = join;
        this.endTime = endTime;
        this.startTime = startTime;
        this.type = type;
        this.purview = purview;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }
}