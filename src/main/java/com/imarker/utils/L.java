package com.imarker.utils;

import android.util.Log;

public final class L {

	private static final String TAG = "iMarker";
	private static final boolean enable = true;
	
	public static void e(String msg) {
		if (enable)
			Log.e(TAG, msg);
	}
	
	public static void e(String tag, String msg) {
		if (enable)
			Log.e(tag, msg);
	}
	
	public static void e(String msg, Throwable tr) {
		if (enable)
			Log.e(TAG, msg, tr);
	}
	
	public static void e(String tag, String msg, Throwable tr) {
		if (enable)
			Log.e(tag, msg, tr);
	}

	public static void d(String msg) {
		if (enable)
			Log.d(TAG, msg);
	}
	
	public static void d(String tag, String msg) {
		if (enable)
			Log.d(tag, msg);
	}
	
	public static void d(String msg, Throwable tr) {
		if (enable)
			Log.d(TAG, msg, tr);
	}
	
	public static void d(String tag, String msg, Throwable tr) {
		if (enable)
			Log.d(tag, msg, tr);
	}
	
}
