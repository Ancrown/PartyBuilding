package zhuri.com.partybuilding.bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class ExaminationBean {

    public ExaminationBean(String id, String title, String demo, String amount, String score, String times, String stime, String etime, String myscore, String isjoin, String purview, String status) {
        this.id = id;
        this.title = title;
        this.demo = demo;
        this.amount = amount;
        this.score = score;
        this.times = times;
        this.stime = stime;
        this.etime = etime;
        this.myscore = myscore;
        this.isjoin = isjoin;
        this.purview = purview;
        this.status = status;
    }

    /**
     * id : 试卷ID
     * title : 试卷名称
     * demo : 摘要
     * amount : 总题数
     * score : 总分数
     * times : 答卷时长
     * stime : 开始时间
     * etime : 结束时间
     * myscore :  我的考卷的分 isjoin=1的时候
     * isjoin : 是否答过
     * purview : 是否登陆可看
     * status : 状态
     */

    private String id;
    private String title;
    private String demo;
    private String amount;
    private String score;
    private String times;
    private String stime;
    private String etime;
    private String myscore;
    private String isjoin;
    private String purview;
    private String status;

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

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getMyscore() {
        return myscore;
    }

    public void setMyscore(String myscore) {
        this.myscore = myscore;
    }

    public String getIsjoin() {
        return isjoin;
    }

    public void setIsjoin(String isjoin) {
        this.isjoin = isjoin;
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}