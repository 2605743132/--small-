package com.bway.caoyuan;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.bway.caoyuan.exception.ApiException;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;


import org.json.JSONException;

import java.io.File;

public class WDApplication  extends Application{
    public static Context sContext;
//
//    public static ApiException handleException(Throwable e){
//
//        e.printStackTrace();
//        ApiException ex;
//        if (e instanceof JsonParseException||e instanceof JSONException){
//        }
//    }

    /** 主线程ID */
    private static int mMainThreadId = -1;
    /** 主线程ID */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程Looper */
    private static Looper mMainLooper;

    /**
     * context 全局唯一的上下文
     */
    private static Context context;

    private static SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        //友盟统计
   /*
注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
*/
//        UMConfigure.init(context,  "5c3dd048f1f5567e4d000d7a", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,null);

        initFresco(this);
        context=this;

/*
注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
*/
        UMConfigure.init(context,"5c3dd048f1f5567e4d000d7a", "维度商城",UMConfigure.DEVICE_TYPE_PHONE,null);
        //腾讯bug
        CrashReport.initCrashReport(this, "80ff7752b2", true);

        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        sharedPreferences = getSharedPreferences("share.xml",MODE_PRIVATE);

    }

    public static SharedPreferences getShare(){
        return sharedPreferences;
    }


    public static Context getContext() {
        return context;
    }

    /** 获取主线程ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /** 获取主线程 */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /** 获取主线程的handler */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /** 获取主线程的looper */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
    private void initFresco(Context context) {
        Fresco.initialize(context, ImagePipelineConfig.newBuilder(WDApplication.this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory() + "fresco"))
                                .build()
                )
                .build()
        );
    }

}
