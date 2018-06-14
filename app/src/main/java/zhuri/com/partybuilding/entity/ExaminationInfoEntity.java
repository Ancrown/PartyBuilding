package zhuri.com.partybuilding.entity;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/12
 * 描述:获取正式考卷信息
 */

public class ExaminationInfoEntity {


    /**
     * mian : {"id":"试卷ID","title":"试卷名称","imageurl":"首图","demo":"摘要","amount":"总题数","score":"总分数","times":"答卷时间","hits":"点击数","addtime":"发布时间","repeat":"重复参加","isjoin":"是否答过","integral":"获得积分","purview":"是否登陆可看"}
     * info : [{"id":"试题ID","title":"题目","options":"选项#选项#选项","answer":"答案","ctype":"单选或多选","score":"得分","tips":"解析"}]
     */

    private MianBean mian;
    private List<InfoBean> info;

    public MianBean getMian() {
        return mian;
    }

    public void setMian(MianBean mian) {
        this.mian = mian;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class MianBean {
        /**
         * id : 试卷ID
         * title : 试卷名称
         * imageurl : 首图
         * demo : 摘要
         * amount : 总题数
         * score : 总分数
         * times : 答卷时间
         * hits : 点击数
         * addtime : 发布时间
         * repeat : 重复参加
         * isjoin : 是否答过
         * integral : 获得积分
         * purview : 是否登陆可看
         */

        private String id;
        private String title;
        private String imageurl;
        private String demo;
        private String amount;
        private String score;
        private String times;
        private String hits;
        private String addtime;
        private String repeat;
        private String isjoin;
        private String integral;
        private String purview;

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

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
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

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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
    }

    public static class InfoBean {
        /**
         * id : 试题ID
         * title : 题目
         * options : 选项#选项#选项
         * answer : 答案
         * ctype : 单选或多选
         * score : 得分
         * tips : 解析
         */

        private String id;
        private String title;
        private String options;
        private String answer;
        private String ctype;
        private String score;
        private String tips;

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

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }
}
