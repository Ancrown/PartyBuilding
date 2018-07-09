package zhuri.com.partybuilding.entity.study;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/7
 * 描述:
 */

public class StudyDetailsEntity {

    /**
     * main : {"id":"文章ID","title":"标题","demo":"摘要","imageurl":"首图","videourl":"视频地址","content":"内容","source":"来源","addtime":"时间戳","hits":"点击","share":"分享","ilike":"点赞","integral":"获得积分","purview":"是否登陆可看","times":"强制观看时长","melike":"我是否点过赞","shareurl":"分享路径","logid":"日志ID,返回时原样返回"}
     * perv : {"id":"上一篇ID","title":"上篇标题"}
     * next : {"id":"下一篇ID","title":"下篇标题"}
     */

    private MainBean main;
    private PervBean perv;
    private NextBean next;

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
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

    public static class MainBean {
        /**
         * id : 文章ID
         * title : 标题
         * demo : 摘要
         * imageurl : 首图
         * videourl : 视频地址
         * content : 内容
         * source : 来源
         * addtime : 时间戳
         * hits : 点击
         * share : 分享
         * ilike : 点赞
         * integral : 获得积分
         * purview : 是否登陆可看
         * times : 强制观看时长
         * melike : 我是否点过赞
         * shareurl : 分享路径
         * logid : 日志ID,返回时原样返回
         */

        private String id;
        private String title;
        private String demo;
        private String imageurl;
        private String videourl;
        private String content;
        private String source;
        private String addtime;
        private String hits;
        private String share;
        private String ilike;
        private String integral;
        private String purview;
        private String times;
        private String melike;
        private String shareurl;
        private String logid;

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

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
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

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
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

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
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
}
