package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/29
 * 描述: 积分记录
 */

public class RecordIntegralBean {
    private String id;
    private String title;
    private String time;
    private String type;
    private String activitiesTyep; // type=0的时候  0社区活动 1 微志愿
    private String integral;

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

    public String getActivitiesTyep() {
        return activitiesTyep;
    }

    public void setActivitiesTyep(String activitiesTyep) {
        this.activitiesTyep = activitiesTyep;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public RecordIntegralBean(String id, String title, String time, String type, String activitiesTyep, String integral) {

        this.id = id;
        this.title = title;
        this.time = time;
        this.type = type;
        this.activitiesTyep = activitiesTyep;
        this.integral = integral;
    }
}
