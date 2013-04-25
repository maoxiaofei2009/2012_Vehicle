package com.zjdx.vehicle;

import android.util.Log;


public class LogUtils{
	private static final String TAG = "LogUtils"; 
	public static final boolean DEBUG = true;

	
	public static void LOGD(String TAG, String message){
		if(DEBUG) Log.d(TAG, message);
	}
	
	public static void LOGE(String TAG, String message){
		if(DEBUG) Log.e(TAG, message);
	}
	
	public static void LOGV(String TAG, String message){
		if(DEBUG) Log.v(TAG, message);
	}
}