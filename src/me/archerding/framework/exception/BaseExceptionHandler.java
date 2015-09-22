package me.archerding.framework.exception;

import java.text.DateFormat;

/**
 * Created by ArcherDing on 2015/8/24.
 */
public abstract class BaseExceptionHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    protected static final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);

/*
* 未捕获异常跳�?
*
* */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        如果正确处理了未捕获异常
        if (handlerException(ex)){
            try {
//                让程序继续运�?3秒，保证错误日志能够保存
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

//            �?出程�?
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    public abstract boolean handlerException(Throwable ex);
}
