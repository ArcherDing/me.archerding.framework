package me.archerding.framework.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import me.archerding.framework.util.JFileKit;

/**
 * Created by ArcherDing on 2015/8/24.
 */
public class LocalFileHandler extends BaseExceptionHandler {
    private Context context;

    public LocalFileHandler(Context context) {
        this.context = context;
    }


    @Override
    public boolean handlerException(Throwable ex) {

        if (ex == null) return false;
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉，程序出现异常，正在从错误中恢复!", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
//      保存错误日志
        savaLog(ex);

        return true;
    }

    public void savaLog(Throwable ex) {
        try {
//          创建App崩溃日志目录
            File appFolder = new File(JFileKit.getDiskCacheDir(context) + "/log");
            if (!appFolder.exists()) {
                appFolder.mkdirs();
            }

//          创建日志文件
            File errorFile = new File(JFileKit.getDiskCacheDir(context) + "/log/crash.log");
            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(errorFile, true);
            out.write(("\n\n------错误分割线 " + dateFormat.format(new Date()) + "------\n\n").getBytes());
            PrintStream stream = new PrintStream(out);
            ex.printStackTrace(stream);
            stream.flush();
            stream.close();
            out.close();

        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }
}
