package zhuri.com.partybuilding.util;

import android.content.Context;

import zhuri.com.partybuilding.base.BaseApplication;

/**
 * 作者 ：刘晓伟
 * 包名 ：com.ioi.distribution.util
 * 描述 ：存储应用的静态变量
 * 创建时间 ：2016/11/30
 */
public class StaticVariables {


    //app运行次数
    public static final String APP_START_NUM = "appStartNum";
    //用户id
    public static final String USER_ID = "uid";
    //用户名字
    public static final String USER_NAME = "uName";
    //用户昵称
    public static final String USER_NICK_NAME = "uNickName";
    //用户头像
    public static final String USER_HEAD_IMG = "img";
    //唯一标示
    public static final String TOKEN = "token";
    //编号
    public static final String CODES = "codes";
    //性别
    public static final String SEX = "sex";
    //年龄
    public static final String AGE = "age";
    //生日
    public static final String BIRTHDAY = "birthday";
    //电话
    public static final String TEL = "tel";
    //邮箱
    public static final String EMAIL = "email";
    //我的积分
    public static final String INTEGRAL = "integral";
    //所在支部id
    public static final String DID = "did";
    //所在支部名字
    public static final String D_NAME = "dname";
    //证件号
    public static final String IDCARD = "idcard";
    //证件类型
    public static final String IDTYPE = "idtype";
    //入党时间
    public static final String JOINTIME = "jointime";
    //注册时间
    public static final String REGTIME = "regtime";
    //累计学习次数
    public static final String STUDY_COUNT = "studycount";


    public static String getAppStartNum() {
        return getString(APP_START_NUM);
    }

    public static String getUserId() {
        return getString(USER_ID);
    }

    public static String getUserName() {
        return getString(USER_NAME);
    }

    public static String getUserNickName() {
        return getString(USER_NICK_NAME);
    }

    public static String getUserHeadImg() {
        return getString(USER_HEAD_IMG);
    }

    public static String getTOKEN() {
        return getString(TOKEN);
    }

    public static String getCODES() {
        return getString(CODES);
    }

    public static String getSEX() {
        return getString(SEX);
    }

    public static String getAGE() {
        return getString(AGE);
    }

    public static String getBIRTHDAY() {
        return getString(BIRTHDAY);
    }

    public static String getTEL() {
        return getString(TEL);
    }

    public static String getEMAIL() {
        return getString(EMAIL);
    }

    public static String getINTEGRAL() {
        return getString(INTEGRAL);
    }

    public static String getDID() {
        return getString(DID);
    }

    public static String getdName() {
        return getString(D_NAME);
    }

    public static String getIDCARD() {
        return getString(IDCARD);
    }

    public static String getIDTYPE() {
        return getString(IDTYPE);
    }

    public static String getJOINTIME() {
        return getString(JOINTIME);
    }

    public static String getREGTIME() {
        return getString(REGTIME);
    }

    public static String getStudyCount() {
        return getString(STUDY_COUNT);
    }

    public static String getString(String name) {
        return SharedPreferencesUtils.getParam(BaseApplication.appContext, name, "") + "";
    }
}
