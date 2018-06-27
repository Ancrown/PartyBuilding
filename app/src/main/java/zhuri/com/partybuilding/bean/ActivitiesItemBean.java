package zhuri.com.partybuilding.bean;

import android.util.Log;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/5
 * 描述: 活动描述
 */

public class ActivitiesItemBean {


    /**
     * id : 活动ID
     * title : 活动标题
     * imageurl : 首图
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

    private int type;  //0 三张 2两张 1一张图正常

    public ActivitiesItemBean(String id, String title, String imageurl, String address, String addtime, String purview, String flag, String status) {
        this.id = id;
        this.title = title;
        this.imageurl = imageurl;
        this.address = address;
        this.addtime = addtime;
        this.purview = purview;
        this.flag = flag;
        this.status = status;
        this.type = imageurl.split("#").length > 3 ? 3 : imageurl.split("#").length % 4;
        Log.e("eeeeee", "type type type" + type +"  "+imageurl.split("#").length);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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