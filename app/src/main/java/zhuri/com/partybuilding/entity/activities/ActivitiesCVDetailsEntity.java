package zhuri.com.partybuilding.entity.activities;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/5
 * 描述:社会-微志愿 详情
 */

public class ActivitiesCVDetailsEntity {


    /**
     * main : {"title":"标题","demo":"摘要","imageurl":"首图","content":"活动详情内容","source":"来源","addtime":"发布时间","hits":"点击","share":"分享","ilike":"点赞","purview":"是否登陆可看","status":"状态","integral":"获得积分","isjoin":"是否参加","melike":"我是否点过赞","shareurl":"分享路径","signup":"报名人数","signin":"参加人数","dname":"举办者","stime":"开始时间","etime":"结束时间","address":"地址"}
     * report : 活动报导
     * perv : {"id":"上一篇ID","title":"上篇标题"}
     * next : {"id":"下一篇ID","title":"下篇标题"}
     * user : [{"id":"会员ID","nickname":"会员名称","avatar":"会员头像"},{"id":"会员ID","nickname":"会员名称","avatar":"会员头像"},{"id":"会员ID","nickname":"会员名称","avatar":"会员头像"}]
     * info : [{"id":"活动ID","title":"活动标题","imageurl":"首图|首图","address":"地点","addtime":"发布时间","purview":"是否登陆可看","flag":"置顶2|推荐1|正常0","status":"活动状态"}]
     */

    private MainBean main;
    private String report;
    private PervBean perv;
    private NextBean next;
    private List<UserBean> user;
    private List<InfoBean> info;

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public PervBean getPerv() {
        return perv;
    }

    public void setPerv(PervBean perv) {
        this.perv = perv;
    }

    public NextBean getNext() {
        return next;
    }

    public void setNext(NextBean next) {
        this.next = next;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class MainBean {
        /**
         * title : 标题
         * demo : 摘要
         * imageurl : 首图
         * content : 活动详情内容
         * source : 来源
         * addtime : 发布时间
         * hits : 点击
         * share : 分享
         * ilike : 点赞
         * purview : 是否登陆可看
         * status : 状态
         * integral : 获得积分
         * isjoin : 是否参加
         * melike : 我是否点过赞
         * shareurl : 分享路径
         * signup : 报名人数
         * signin : 参加人数
         * dname : 举办者
         * stime : 开始时间
         * etime : 结束时间
         * address : 地址
         */

        private String title;
        private String demo;
        private String imageurl;
        private String content;
        private String source;
        private String addtime;
        private String hits;
        private String share;
        private String ilike;
        private String purview;
        private String status;
        private String integral;
        private String isjoin;
        private String melike;
        private String shareurl;
        private String signup;
        private String signin;
        private String dname;
        private String stime;
        private String etime;
        private String address;

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

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getIlike() {
            return ilike;
        }

        public void setIlike(String ilike) {
            this.ilike = ilike;
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

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIsjoin() {
            return isjoin;
        }

        public void setIsjoin(String isjoin) {
            this.isjoin = isjoin;
        }

        public String getMelike() {
            return melike;
        }

        public void setMelike(String melike) {
            this.melike = melike;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public String getSignup() {
            return signup;
        }

        public void setSignup(String signup) {
            this.signup = signup;
        }

        public String getSignin() {
            return signin;
        }

        public void setSignin(String signin) {
            this.signin = signin;
        }

        public String getDname() {
            return dname;
        }

        public void setDname(String dname) {
            this.dname = dname;
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
    }

    public static class PervBean {
        /**
         * id : 上一篇ID
         * title : 上篇标题
         */

        private String id;
        private String title;

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
    }

    public static class NextBean {
        /**
         * id : 下一篇ID
         * title : 下篇标题
         */

        private String id;
        private String title;

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
    }

    public static class UserBean {
        /**
         * id : 会员ID
         * nickname : 会员名称
         * avatar : 会员头像
         */

        private String id;
        private String nickname;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class InfoBean {
        /**
         * id : 活动ID
         * title : 活动标题
         * imageurl : 首图|首图
         * address : 地点
         * addtime : 发布时间
         * purview : 是否登陆可看
         * flag : 置顶2|推荐1|正常0
         * status : 活动状态
         */

        private String id;
        private String title;
        private String imageurl;
        private String address;
        private String addtime;
        private String purview;
        private String flag;
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

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
