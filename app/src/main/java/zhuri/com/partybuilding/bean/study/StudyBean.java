package zhuri.com.partybuilding.bean.study;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述:
 */

public class StudyBean {
    private String id;
    private String title;
    private String content;
    private String time;
    private String join;
    private String fabulousNum;
    private String isFabulous;
    private String seeNum;

    private String purview;

    public StudyBean(String id, String title, String content, String time, String join, String fabulousNum, String isFabulous, String seeNum, String purview) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.join = join;
        this.fabulousNum = fabulousNum;
        this.isFabulous = isFabulous;
        this.seeNum = seeNum;
        this.purview = purview;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getFabulousNum() {
        return fabulousNum;
    }

    public void setFabulousNum(String fabulousNum) {
        this.fabulousNum = fabulousNum;
    }

    public String getIsFabulous() {
        return isFabulous;
    }

    public void setIsFabulous(String isFabulous) {
        this.isFabulous = isFabulous;
    }

    public String getSeeNum() {
        return seeNum;
    }

    public void setSeeNum(String seeNum) {
        this.seeNum = seeNum;
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }
}
