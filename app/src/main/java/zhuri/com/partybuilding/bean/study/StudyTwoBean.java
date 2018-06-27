package zhuri.com.partybuilding.bean.study;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/27
 * 描述:
 */

public class StudyTwoBean {

    /**
     * subitemid : 子项id
     * subitemtext : 子项名
     * subitem : [{"id":"文章ID","title":"文章标题","imageurl":"首图","demo":"摘要","isstudy":"是否学过 0为学习 1已学习","isvideo":"是否有视频 0没有 1有","amount":"参加人数","purview":"是否登陆可看"}]
     */

    private String type;
    private String subitemid;
    private String subitemtext;
    public List<SubitemBean> subitem;

    public StudyTwoBean(String type, String subitemid, String subitemtext, List<SubitemBean> subitem) {
        this.type = type;
        this.subitemid = subitemid;
        this.subitemtext = subitemtext;
        this.subitem = subitem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubitemid() {
        return subitemid;
    }

    public void setSubitemid(String subitemid) {
        this.subitemid = subitemid;
    }

    public String getSubitemtext() {
        return subitemtext;
    }

    public void setSubitemtext(String subitemtext) {
        this.subitemtext = subitemtext;
    }

    public List<SubitemBean> getSubitem() {
        return subitem;
    }

    public void setSubitem(List<SubitemBean> subitem) {
        this.subitem = subitem;
    }

    public static class SubitemBean {
        /**
         * id : 文章ID
         * title : 文章标题
         * imageurl : 首图
         * demo : 摘要
         * isstudy : 是否学过 0为学习 1已学习
         * isvideo : 是否有视频 0没有 1有
         * amount : 参加人数
         * purview : 是否登陆可看
         */

        private String id;
        private String title;
        private String imageurl;
        private String demo;
        private String isstudy;
        private String isvideo;
        private String amount;
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

        public String getIsstudy() {
            return isstudy;
        }

        public void setIsstudy(String isstudy) {
            this.isstudy = isstudy;
        }

        public String getIsvideo() {
            return isvideo;
        }

        public void setIsvideo(String isvideo) {
            this.isvideo = isvideo;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPurview() {
            return purview;
        }

        public void setPurview(String purview) {
            this.purview = purview;
        }
    }
}
