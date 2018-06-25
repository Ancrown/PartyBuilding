package zhuri.com.partybuilding.entity;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/14
 * 描述:
 */

public class LogInEntity {

    /**
     * id : id
     * token :
     * codes : 编号
     * name : 姓名
     * img : 头像
     * nickname : 昵称
     * sex : 性别
     * age : 年龄
     * birthday : 生日
     * did : 所在支部ID
     * dname : 所在支部名称
     * tel : 电话
     * email : 邮箱
     * integral : 我积分
     */

    private String id;
    private String token;
    private String codes;
    private String name;
    private String img;
    private String nickname;
    private String sex;
    private String age;
    private String birthday;
    private String did;
    private String dname;
    private String tel;
    private String email;
    private String integral;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
