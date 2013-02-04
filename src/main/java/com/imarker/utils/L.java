package com.imarker.utils;

import android.util.Log;
import com.imarker.constant.Flags;

public final class L {

	private static final String TAG = "iMarker";
	
	public static void e(String msg) {
		if (Flags.LOGGER_ENABLE)
			Log.e(TAG, msg);
	}
	
	public static void e(String tag, String msg) {
		if (Flags.LOGGER_ENABLE)
			Log.e(tag, msg);
	}
	
	public static void e(String msg, Throwable tr) {
		if (Flags.LOGGER_ENABLE)
			Log.e(TAG, msg, tr);
	}
	
	public static void e(String tag, String msg, Throwable tr) {
		if (Flags.LOGGER_ENABLE)
			Log.e(tag, msg, tr);
	}

	public static void d(String msg) {
		if (Flags.LOGGER_ENABLE)
			Log.d(TAG, msg);
	}
	
	public static void d(String tag, String msg) {
		if (Flags.LOGGER_ENABLE)
			Log.d(tag, msg);
	}
	
	public static void d(String msg, Throwable tr) {
		if (Flags.LOGGER_ENABLE)
			Log.d(TAG, msg, tr);
	}
	
	public static void d(String tag, String msg, Throwable tr) {
		if (Flags.LOGGER_ENABLE)
			Log.d(tag, msg, tr);
	}
	
}
