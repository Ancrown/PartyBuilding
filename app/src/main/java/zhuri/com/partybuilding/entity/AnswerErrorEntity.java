package zhuri.com.partybuilding.entity;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/13
 * 描述: 错题集
 */

public class AnswerErrorEntity {


    /**
     * page : 当前页数
     * totalpage : 总页数
     * info : [{"id":"试题ID","title":"题目","options":"选项|选项|选项","answer":"答案","myanswer":"错误答案","ctype":"单选或多选","tips":"提示","score":"得分"}]
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
         * id : 试题ID
         * title : 题目
         * options : 选项|选项|选项
         * answer : 答案
         * myanswer : 错误答案
         * ctype : 单选或多选
         * tips : 提示
         * score : 得分
         */

        private String id;
        private String title;
        private String options;
        private String answer;
        private String myanswer;
        private String ctype;
        private String tips;
        private String score;

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

        public String getMyanswer() {
            return myanswer;
        }

        public void setMyanswer(String myanswer) {
            this.myanswer = myanswer;
        }

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
