package zhuri.com.partybuilding.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


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

//        /**
//         * 初始化common库
//         * 参数1:上下文，不能为空
//         * 参数2:【友盟+】 AppKey
//         * 参数3:【友盟+】 Channel
//         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
//         * 参数5:Push推送业务的secret
//         */
//        UMConfigure.init(this, "5ae1c104b27b0a566f000282", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "910c073bd538d6a46a4c4ad72c057b51");
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                token = deviceToken;
//                Log.i("xxx", token);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                token = s1 + "So文件不存在";
//                Log.i("xxxx", token);
//            }
//        });


//        //初始化imageloader
//        initImageLoader();


//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setDebugMode(true);
//        handler = new Handler();
//
//        //sdk开启通知声音
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
//        // sdk关闭通知声音
////		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//        // 通知声音由服务端控制
////		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);
//
////		mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
////		mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//
//
//        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//            /**
//             * 自定义消息的回调方法
//             * */
//            @Override
//            public void dealWithCustomMessage(final Context context, final UMessage msg) {
//
//                handler.post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        // 对自定义消息的处理方式，点击或者忽略
//                        boolean isClickOrDismissed = true;
//                        if (isClickOrDismissed) {
//                            //自定义消息的点击统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//                        } else {
//                            //自定义消息的忽略统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            /**
//             * 自定义通知栏样式的回调方法
//             * */
//            @Override
//            public Notification getNotification(Context context, UMessage msg) {
//                switch (msg.builder_id) {
//                    case 1:
//                        Notification.Builder builder = new Notification.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView)
//                                .setSmallIcon(getSmallIconId(context, msg))
//                                .setTicker(msg.ticker)
//                                .setAutoCancel(true);
//
//                        return builder.getNotification();
//                    default:
//                        //默认为0，若填写的builder_id并不存在，也使用默认。
//                        return super.getNotification(context, msg);
//                }
//            }
//        };
//        mPushAgent.setMessageHandler(messageHandler);
//
//        /**
//         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
//         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
//         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
//         * */
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//            }
//        };
//        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
//        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
//
//        Log.e(TAG, "start");
//        //注册推送服务 每次调用register都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                Log.e(TAG, "device token: 22222" + deviceToken);
//                //存取token
//                SharedPreferences preferences = getSharedPreferences("UMeng", MODE_PRIVATE);
//                preferences.edit().putString("token", deviceToken).commit();
//                StaticVariables.TOKEN = deviceToken;
//                sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.e(TAG, "register failed: 222" + s + " " + s1);
//                sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
//            }
//        });
//        //此处是完全自定义处理设置，两个例子，任选一种即可
////        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
//        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);
//
//        //ShareSDK短信
//        MobSDK.init(this, this.a(), this.b());
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

}
