package zhuri.com.partybuilding.entity;

/**
 * 创建人: Administrator
 * 创建时间: 2018/7/4
 * 描述: 新闻详情
 */

public class NewsDetailsEntity {

    /**
     * id : 文章ID
     * title : 标题
     * demo : 摘要
     * imageurl : 首图
     * content : 内容
     * source : 来源
     * addtime : 时间戳
     * hits : 点击
     * share : 分享
     * ilike : 点赞
     * melike : 我是否点过赞
     * purview : 是否登陆可看
     * shareurl : 分享路径
     */

    private String id;
    private String title;
    private String demo;
    private String imageurl;
    private String content;
    private String source;
    private String addtime;
    private String hits;
    private String share;
    private String ilike;
    private String melike;
    private String purview;
    private String shareurl;

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

    public String getMelike() {
        return melike;
    }

    public void setMelike(String melike) {
        this.melike = melike;
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }
}
