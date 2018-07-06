package zhuri.com.partybuilding.util;

/**
 * @功能 ：网络请求地址
 */
public class AddressRequest {

    public static final String URL = "http://sydj.196tuan.com/api.php";
    //加载页
    public static final String GET_LOAD_PAGE = URL + "/Api/Index/getLoadpage";
    //登陆
    public static final String LOGIN = URL + "/Api/Index/login";
    //点赞
    public static final String FABULOUS = URL + "/Api/Tool/ilike";
    //分享
    public static final String FORWARD =URL+"/Api/Tool/share" ;


    //首页
    public static final String HOME = URL + "/Api/Index/index";
    //新闻详情
    public static final String NEWS_DETAIL = URL + "/Api/News/getinfo";

    //咨询
    public static final String CONSULTATION = URL + "/Api/News/index";
    //活动-社区活动
    public static final String ACTIVITIES_COMMUNTIY = URL + "/Api/Activity/getcommunity";
    //活动-社区详情
    public static final String ACTIVITIES_C_DETAILS = URL + "/Api/Activity/getcommunityinfo";
    //活动-社区报名
    public static final String ACTIVITIES_C_SING_UP = URL + "/Api/Activity/savecommunitysignup";
    //活动-社区签到
    public static final String ACTIVITIES_C_SING_IN = URL + "/Api/Activity/savecommunitysignin";

    //活动-微志愿
    public static final String ACTIVITIES_V_VOLUNTEER = URL + "/Api/Activity/getvolunteer";
    //活动-微志愿详情
    public static final String ACTIVITIES_V_DETAILS = URL + "/Api/Activity/getvolunteerinfo";
    //活动-微志愿报名
    public static final String ACTIVITIES_V_SING_UP = URL + "/Api/Activity/savevolunteersignup";
    //活动-微志愿签到
    public static final String ACTIVITIES_V_SING_IN = URL + "/Api/Activity/savevolunteersignin";


    //活动-微心愿
    public static final String ACTIVITIES_V_WISH = URL + "/Api/Activity/getwish";
    //活动-微心愿详情
    public static final String ACTIVITIES_W_DETAILS=URL + "/Api/Activity/getwishinfo";
    //活动-微心愿领取
    public static final String ACTIVITIES_W_SING_UP=URL + "/Api/Activity/savewishsignup";



    public static final String STUDY = URL + "";
    //学习 19大
    public static final String STUDY_ONE = URL + "";
    //学习 两学一做
    public static final String STUDY_TWO = URL + "";
    //学习 党务工作
    public static final String STUDY_THREE = URL + "";


    //考试列表
    public static final String EXAMINATION_LIST = URL + "";
    //试卷
    public static final String GET_EXAMINATION = URL + "";
    //交卷
    public static final String UP_EXAMINATION = URL + "";

    //错题集
    public static final String ERROR_ANSWER = URL + "";



}