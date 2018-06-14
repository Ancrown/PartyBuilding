package zhuri.com.partybuilding.entity;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/9
 * 描述: 获取考卷列表
 */

public class ExaminationEntity {


    /**
     * page : 当前页数
     * totalpage : 总页数
     * info : [{"id":"试卷ID","title":"试卷名称","demo":"摘要","amount":"总题数","score":"总分数","times":"答卷时间","stime":"开始时间","etime":"结束时间","hits":"点击数","repeat":"重复参加","isjoin":"是否答过","integral":"获得积分","purview":"是否登陆可看","status":"状态"}]
     */

    private String page;
    private String totalpage;
    private List<InfoBean> info;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 试卷ID
         * title : 试卷名称
         * demo : 摘要
         * amount : 总题数
         * score : 总分数
         * times : 答卷时间
         * stime : 开始时间
         * etime : 结束时间
         * hits : 点击数
         * repeat : 重复参加
         * isjoin : 是否答过
         * integral : 获得积分
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
        private String hits;
        private String repeat;
        private String isjoin;
        private String integral;
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

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getRepeat() {
            return repeat;
        }

        public void setRepeat(String repeat) {
            this.repeat = repeat;
        }

        public String getIsjoin() {
            return isjoin;
        }

        public void setIsjoin(String isjoin) {
            this.isjoin = isjoin;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
