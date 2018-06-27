package zhuri.com.partybuilding.entity.activities;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/8
 * 描述:活动-微心愿
 */

public class VWishEntity {


    /**
     * page : 当前页数
     * totalpage : 总页数
     * info : [{"id":"心愿ID","title":"标题","imageurl":"首图","demo":"摘要","ilike":"点赞数","share":"分享数","hits":"点击数","integral":"获得积分","status":"心愿状态","addtime":"发布时间","endtime":"结束时间","purview":"是否登陆可看","peopleNum":"参加人数","send":"发布者"}]
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
         * id : 心愿ID
         * title : 标题
         * imageurl : 首图
         * demo : 摘要
         * ilike : 点赞数
         * share : 分享数
         * hits : 点击数
         * integral : 获得积分
         * status : 心愿状态
         * addtime : 发布时间
         * endtime : 结束时间
         * purview : 是否登陆可看
         * peopleNum : 参加人数
         * send : 发布者
         */

        private String id;
        private String title;
        private String imageurl;
        private String demo;
        private String ilike;
        private String share;
        private String hits;
        private String integral;
        private String status;
        private String addtime;
        private String endtime;
        private String purview;
        private String peopleNum;
        private String send;

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

        public String getIlike() {
            return ilike;
        }

        public void setIlike(String ilike) {
            this.ilike = ilike;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getPurview() {
            return purview;
        }

        public void setPurview(String purview) {
            this.purview = purview;
        }

        public String getPeopleNum() {
            return peopleNum;
        }

        public void setPeopleNum(String peopleNum) {
            this.peopleNum = peopleNum;
        }

        public String getSend() {
            return send;
        }

        public void setSend(String send) {
            this.send = send;
        }
    }
}
