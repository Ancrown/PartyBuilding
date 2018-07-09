package zhuri.com.partybuilding.base;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.CustomErrorActivity;
import zhuri.com.partybuilding.activity.SplashActivity;


/**
 * 应用基础类
 *
 * @创建时间 ：2016/11/21
 * @项目名称 ：Circle
 * @类型名称 : BaseApplication
 * @文件类型 : BaseApplication.java
 * @创始人 : liuxi
 * @包名 ：com.ioi.distribution.base
 */
public class BaseApplication extends Application {
    public static final String TAG = "MyApplication";
    /**
     * 栈
     */
    public static Stack<Activity> activityStack;

    private static int mainTid;
    /**
     * 应用程序数据存储
     */
    private static Map<String, Object> appData;
    /**
     * 实例
     */
    private static BaseApplication instance;
    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";
    private Handler handler;
    public static Context appContext;

    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    // login user name
    public final String PREF_USERNAME = "username";

    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";
    public static Context applicationContext;
    public static String token = "";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }


    @Override
    public void onCreate() {
//        MultiDex.install(this);
        super.onCreate();
        //全局监听异常
//        CrashHandler catchExcept = new CrashHandler(this);
//        Thread.setDefaultUncaughtExceptionHandler(catchExcept);


        applicationContext = this;
        instance = this;


        appContext = this;

        instance = this;


        //初始化集合
        appData = new HashMap<>();

        mainTid = android.os.Process.myTid();

//        handler = new Handler();

        //初始化Activity管理
        initActivityManager();

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        appError();
        UMConfigure.init(this, "5b2c4ce7f43e487250000092", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
        initUmeng();


    }

    //崩溃设置
    public void appError() {
        //整个配置属性，可以设置一个或多个，也可以一个都不设置
        CaocConfig.Builder.create()
                //程序在后台时，发生崩溃的三种处理方式
                //BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: //当应用程序处于后台时崩溃，也会启动错误页面，
                //BackgroundMode.BACKGROUND_MODE_CRASH:      //当应用程序处于后台崩溃时显示默认系统错误（一个系统提示的错误对话框），
                //BackgroundMode.BACKGROUND_MODE_SILENT:     //当应用程序处于后台时崩溃，默默地关闭程序！
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
                .enabled(true)     //false表示对崩溃的拦截阻止。用它来禁用customactivityoncrash框架
                .showErrorDetails(false) //这将隐藏错误活动中的“错误详细信息”按钮，从而隐藏堆栈跟踪,—》针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。。
                .showRestartButton(false)    //是否可以重启页面,针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。
                .trackActivities(true)     //错误页面中显示错误详细信息；针对框架自带程序崩溃后显示的页面有用(DefaultErrorActivity)。
                .minTimeBetweenCrashesMs(3000)      //定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
                .errorDrawable(R.mipmap.ic_launcher)     //崩溃页面显示的图标
                .restartActivity(SplashActivity.class)      //重新启动后的页面
                .errorActivity(CustomErrorActivity.class) //程序崩溃后显示的页面
                .eventListener(new CustomEventListener())//设置监听
                .apply();
        //如果没有任何配置，程序崩溃显示的是默认的设置
        CustomActivityOnCrash.install(this);
    }

    /**
     * 监听程序崩溃/重启
     */
    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        //程序崩溃回调
        @Override
        public void onLaunchErrorActivity() {
            Log.e(TAG, "onLaunchErrorActivity()");
        }

        //重启程序时回调
        @Override
        public void onRestartAppFromErrorActivity() {
            Log.e(TAG, "onRestartAppFromErrorActivity()");
        }

        //在崩溃提示页面关闭程序时回调
        @Override
        public void onCloseAppFromErrorActivity() {
            Log.e(TAG, "onCloseAppFromErrorActivity()");
        }

    }

    private void initUmeng() {
        UMConfigure.setLogEnabled(true);

        PushAgent mPushAgent = PushAgent.getInstance(this);


        /**
         *  通知的展示及提醒
         自定义通知打开动作
         开发者可自定义用户点击通知栏时的后续动作。自定义行为的数据放在UMessage.custom字段。
         在【友盟+】后台或通过API发送消息时，在“后续动作”中的“自定义行为”中输入相应的值或代码即可实现。
         若开发者需要处理自定义行为，则可以重写方法dealWithCustomAction()。
         其中自定义行为的内容，存放在UMessage.custom中。请在自定义Application类中添加以下代码：
         */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {


//                UmengMsgBean bean = new Gson().fromJson(msg.getRaw().toString(), UmengMsgBean.class);
//
//                Log.e("eeeeee", "dealWithCustomAction:" + bean.getPayload().getBody().getCustom() + " #######               " + msg.getRaw().toString());
//
//                Log.e("eeeeee", "dealWithCustomAction:" + msg.custom + "    " + bean.toString());

                Log.e("eeeeee", "dealWithCustomAction:" + msg.custom);
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);


        //自定义通知栏样式
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 通知的回调方法（通知送达时会回调）
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super，会展示通知，不调用super，则不展示通知。
                super.dealWithNotificationMessage(context, msg);


                Log.e("eeeeee", "dealWithNotificationMessage:" + msg.custom);
            }

            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        //mPushAgent.setPushIntentServiceClass


        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        //  mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATIONPLAYSERVER);

        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                Log.i(TAG, "device token: " + deviceToken);

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i(TAG, "register failed: " + s + " " + s1);

            }
        });
    }


    /**
     * 初始化Activity管理
     */
    private void initActivityManager() {

        activityStack = new Stack<>();

        if (!activityStack.empty()) {
            throw new RuntimeException("不能二次初始化activity管理栈");
        }
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public Map<String, Object> getAppData() {
        return appData;
    }

    public void setAppData(Map<String, Object> appData) {
        this.appData = appData;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public Handler getHandler() {
        return handler;
    }

    public void finishActivites() {

        if (activityStack != null && activityStack.size() != 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }


    {
        //微信
        PlatformConfig.setWeixin("wxf5795f97fb05cb6e", "233f0ab09e84cc267948d3f1fa600edf");
        //qq
        PlatformConfig.setQQZone("1106988350", "5dS59TyeosacpZ88");

    }
}
