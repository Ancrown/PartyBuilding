package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/30
 * 描述:
 */

public class RecordStudyBean {
    public RecordStudyBean(String id, String type, String title, String time) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String id;
    private String type;
    private String title;
    private String time;

}

