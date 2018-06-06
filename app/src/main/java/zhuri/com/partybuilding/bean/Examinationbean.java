package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class Examinationbean {
    private String id;
    private String title;
    private String label;
    private String content;
    private String time;
    private String type;//0未开始 1进行中 2已打完 3已结束
    private String startTime;

    public Examinationbean(String id, String title, String label, String content, String time, String type, String startTime) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.content = content;
        this.time = time;
        this.type = type;
        this.startTime = startTime;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
