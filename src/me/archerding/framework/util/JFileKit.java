package me.archerding.framework.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

/**
 * Created by ArcherDing on 2015/8/24.
 */
public class JFileKit {
	@SuppressLint("NewApi")
	public static String getDiskCacheDir(Context context) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getAbsolutePath();
		} else {
			cachePath = context.getCacheDir().getAbsolutePath();
		}

		return cachePath;
	}
}
