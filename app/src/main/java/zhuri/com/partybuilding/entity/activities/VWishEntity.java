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
     * info : [{"id":"活动ID","title":"活动标题","imageurl":"首图|首图","demo":"摘要","stime":"开始时间","etime":"结束时间","status":"认领状态","dname":"发布单位","signup":"报名人数","address":"地点","addtime":"发布时间","purview":"是否登陆可看","flag":"置顶2|推荐1|正常0"}]
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
         * imageurl : 首图|首图
         * demo : 摘要
         * stime : 开始时间
         * etime : 结束时间
         * status : 认领状态
         * dname : 发布单位
         * signup : 报名人数
         * address : 地点
         * addtime : 发布时间
         * purview : 是否登陆可看
         * flag : 置顶2|推荐1|正常0
         */

        private String id;
        private String title;
        private String imageurl;
        private String demo;
        private String stime;
        private String etime;
        private String status;
        private String dname;
        private String signup;
        private String address;
        private String addtime;
        private String purview;
        private String flag;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDname() {
            return dname;
        }

        public void setDname(String dname) {
            this.dname = dname;
        }

        public String getSignup() {
            return signup;
        }

        public void setSignup(String signup) {
            this.signup = signup;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
