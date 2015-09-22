package me.archerding.framework.application;

import me.archerding.framework.exception.BaseExceptionHandler;
import me.archerding.framework.exception.LocalFileHandler;
import android.util.DisplayMetrics;

/**
 * Created by ArcherDing on 2015/8/24.
 */
public class SimpleApplication extends BaseApplication {
	private static SimpleApplication instance;

	public int screenW = 0;
	public int screenH = 0;

	public static SimpleApplication getInstance() {
		if (instance == null) {
			instance = new SimpleApplication();
		}

		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// 得到屏幕的宽度和高度
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenW = dm.widthPixels;
		screenH = dm.heightPixels;
	}

	@Override
	public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
		return new LocalFileHandler(applicationContext);
	}
}
