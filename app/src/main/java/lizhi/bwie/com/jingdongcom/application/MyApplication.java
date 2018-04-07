package lizhi.bwie.com.jingdongcom.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import lizhi.bwie.com.jingdongcom.model.db.AddrDao;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class MyApplication extends Application {

    private static int myTid;
    private static Handler handler;
    private static Context context;
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();

        //初始化自己的异常捕获机制
        //CrashHandler.getInstance().init(this);

        //如果要使用腾讯的bugly处理异常...必须把自己的异常处理给注释掉


        new AddrDao(this).initAddrDao();
        //初始化zxing库
        ZXingLibrary.initDisplayOpinion(this);
        UMShareAPI.get(this);
        Config.DEBUG = true;//开启debug
    }

    //获取主线程的id
    public static int getMainThreadId() {

        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }
}
