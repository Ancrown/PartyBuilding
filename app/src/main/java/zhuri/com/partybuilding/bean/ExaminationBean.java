package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class ExaminationBean {
    private String id;
    private String title;
    private String label;
    private String content;
    private String type;//0未开始 1进行中 2已打完 3已结束

    private String startTime;
    private String endTime;
    private String time;
    private String integral;

    private String purview;
    private String amount; //总题数
    private String score; //总分数

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ExaminationBean(String id, String title, String label, String content, String type, String startTime, String endTime, String time, String integral, String purview, String amount, String score) {

        this.id = id;
        this.title = title;
        this.label = label;
        this.content = content;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.time = time;
        this.integral = integral;
        this.purview = purview;
        this.amount = amount;
        this.score = score;

    }
}