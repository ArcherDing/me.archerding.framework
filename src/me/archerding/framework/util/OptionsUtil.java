package me.archerding.framework.util;

import me.archerding.framework.R;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class OptionsUtil {

	private static DisplayImageOptions PicNormal = null;

	/** 图片未加载图像 */
	@SuppressWarnings("deprecation")
	public static DisplayImageOptions PicNormal() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showStubImage(R.color.ECECEC) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.color.ECECEC) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.color.ECECEC) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
					.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}

	/** 目的地图片未加载图像 */
	@SuppressWarnings("deprecation")
	public static DisplayImageOptions PicMudidiNormal() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showStubImage(R.color.ECECEC) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.color.ECECEC) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.color.ECECEC) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
					.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}
}
