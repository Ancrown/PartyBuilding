package zhuri.com.partybuilding.entity;

import java.util.List;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/12
 * 描述:获取正式考卷信息
 */

public class ExaminationInfoEntity {


    /**
     * info : [{"id":"14","title":"123123","options":"123123##asfassad##lkasdfjsakld##sadksakldf##asdfasdf","answer":"1","ctype":"0","score":"10","tips":"123123"},{"id":"10","title":"123123123","options":"123##asdfasdfs##asdfasfsadf##asdfasdfasdfsdf","answer":"1","ctype":"0","score":"10","tips":"123123123"},{"id":"11","title":"123123123","options":"1231231##asdfasfd##sadfassdf##asdfsafd##asdfasdfasdfsaf","answer":"1","ctype":"0","score":"10","tips":"123123123"},{"id":"5","title":"123","options":"123##asdfasfdasdf##asdfasdfasdfsadf","answer":"1","ctype":"0","score":"10","tips":"123"},{"id":"9","title":"123","options":"123##asdfsafsd##asdfasfdsadf##asdfasdfsafsadf","answer":"1","ctype":"0","score":"10","tips":"123123"},{"id":"13","title":"1231231","options":"23123123##asdfasdfasdfsdf","answer":"1","ctype":"0","score":"10","tips":""},{"id":"15","title":"123123","options":"12312313423##asdfasfasf##asdfasfdasfs##asdfasdfasdf##asdfsadfasdf","answer":"1","ctype":"0","score":"10","tips":"123123"},{"id":"12","title":"123123123","options":"123123","answer":"1","ctype":"0","score":"10","tips":"123123"},{"id":"7","title":"123123","options":"123123123##asdfsaf##a963f65af848282ac","answer":"1","ctype":"0","score":"10","tips":"234234234"},{"id":"6","title":"12312312312","options":"1233123123","answer":"1","ctype":"1","score":"10","tips":"123123"}]
     * main : {"id":"7","title":"20185e747b2c36b216d4b8bd5","imageurl":"","demo":"","ctype":"1","integral":"1","integralrule":"1","usecount":"0","amount":"10","score":"100","passscore":"90","onescore":"10","stime":"2018-07-08 08:55:20","etime":"2018-08-11 08:55:20","times":"60","idlist":"5,7,9,10,12,11,15,13,14,6,","repeat":"0","purview":"1","orderid":"1","uid":"1","uname":"admin","did":"8","dname":"6c889633515a7ec47ec7","state":"1","addtime":"2018-07-07 08:55:54"}
     */

    private MainBean main;
    private List<InfoBean> info;

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class MainBean {
        /**
         * id : 7
         * title : 20185e747b2c36b216d4b8bd5
         * imageurl :
         * demo :
         * ctype : 1
         * integral : 1
         * integralrule : 1
         * usecount : 0
         * amount : 10
         * score : 100
         * passscore : 90
         * onescore : 10
         * stime : 2018-07-08 08:55:20
         * etime : 2018-08-11 08:55:20
         * times : 60
         * idlist : 5,7,9,10,12,11,15,13,14,6,
         * repeat : 0
         * purview : 1
         * orderid : 1
         * uid : 1
         * uname : admin
         * did : 8
         * dname : 6c889633515a7ec47ec7
         * state : 1
         * addtime : 2018-07-07 08:55:54
         */

        private String id;
        private String title;
        private String imageurl;
        private String demo;
        private String ctype;
        private String integral;
        private String integralrule;
        private String usecount;
        private String amount;
        private String score;
        private String passscore;
        private String onescore;
        private String stime;
        private String etime;
        private String times;
        private String idlist;
        private String repeat;
        private String purview;
        private String orderid;
        private String uid;
        private String uname;
        private String did;
        private String dname;
        private String state;
        private String addtime;

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

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIntegralrule() {
            return integralrule;
        }

        public void setIntegralrule(String integralrule) {
            this.integralrule = integralrule;
        }

        public String getUsecount() {
            return usecount;
        }

        public void setUsecount(String usecount) {
            this.usecount = usecount;
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

        public String getPassscore() {
            return passscore;
        }

        public void setPassscore(String passscore) {
            this.passscore = passscore;
        }

        public String getOnescore() {
            return onescore;
        }

        public void setOnescore(String onescore) {
            this.onescore = onescore;
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

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getIdlist() {
            return idlist;
        }

        public void setIdlist(String idlist) {
            this.idlist = idlist;
        }

        public String getRepeat() {
            return repeat;
        }

        public void setRepeat(String repeat) {
            this.repeat = repeat;
        }

        public String getPurview() {
            return purview;
        }

        public void setPurview(String purview) {
            this.purview = purview;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getDname() {
            return dname;
        }

        public void setDname(String dname) {
            this.dname = dname;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }

    public static class InfoBean {
        /**
         * id : 14
         * title : 123123
         * options : 123123##asfassad##lkasdfjsakld##sadksakldf##asdfasdf
         * answer : 1
         * ctype : 0
         * score : 10
         * tips : 123123
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
