package me.archerding.framework.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import me.archerding.framework.exception.BaseExceptionHandler;
import me.archerding.framework.exception.LocalFileHandler;

/**
 * Created by ArcherDing on 2015/8/24.
 */
public abstract class BaseApplication extends Application {
    public static final String TAG = "Application";
    public static Context applicationContext;

//  键值对的形式存储
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        if (getDefaultUncaughtExceptionHandler() == null){
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));
        }else{
            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
        }

//      初始化键值对存储
        sharedPreferences = getSharedPreferences("local_kv",MODE_PRIVATE);
    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();
}
