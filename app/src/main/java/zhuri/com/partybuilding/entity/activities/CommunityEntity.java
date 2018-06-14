package zhuri.com.partybuilding.entity.activities;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/8
 * 描述: 活动-社区活动
 */

public class CommunityEntity {


    /**
     * page : 当前页数
     * totalpage : 总页数
     * info : [{"id":"活动ID","title":"活动标题","imageurl":"首图","demo":"摘要","integral":"获得积分","ilike":"点赞数","share":"分享数","hits":"点击数","amount":"参加人数","stime":"开始时间","etime":"结束时间","address":"地点","status":"活动状态","isjoin":"是否报名","addtime":"发布时间","purview":"是否登陆可看","istop":"置顶"},{"id":"活动ID","title":"活动标题","imageurl":"首图","demo":"摘要","integral":"获得积分","ilike":"点赞数","share":"分享数","hits":"点击数","amount":"参加人数","stime":"开始时间","etime":"结束时间","address":"地点","status":"活动状态","isjoin":"是否报名","addtime":"发布时间","purview":"是否登陆可看","istop":"置顶"}]
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
         * id : 活动ID
         * title : 活动标题
         * imageurl : 首图
         * demo : 摘要
         * integral : 获得积分
         * ilike : 点赞数
         * share : 分享数
         * hits : 点击数
         * amount : 参加人数
         * stime : 开始时间
         * etime : 结束时间
         * address : 地点
         * status : 活动状态
         * isjoin : 是否报名
         * addtime : 发布时间
         * purview : 是否登陆可看
         * istop : 置顶
         */

        private String id;
        private String title;
        private String imageurl;
        private String demo;
        private String integral;
        private String ilike;
        private String share;
        private String hits;
        private String amount;
        private String stime;
        private String etime;
        private String address;
        private String status;
        private String isjoin;
        private String addtime;
        private String purview;
        private String istop;

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

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsjoin() {
            return isjoin;
        }

        public void setIsjoin(String isjoin) {
            this.isjoin = isjoin;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPurview() {
            return purview;
        }

        public void setPurview(String purview) {
            this.purview = purview;
        }

        public String getIstop() {
            return istop;
        }

        public void setIstop(String istop) {
            this.istop = istop;
        }
    }
}
