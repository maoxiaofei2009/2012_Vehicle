package com.zjdx.vehicle.service;

import com.zjdx.vehicle.LogUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VehicleReceiver extends BroadcastReceiver {
	private static final String TAG = "VehicleReceiver ";
	public static final String ACTION = "com.zjdx.vehicle.service.VehicleReceiver";
	public static final String KEY_FRAMEID_COUNT	= "KeyFrameIdCount";
	public static final String KEY_FRAMEID_VALUE	= "KeyFrameIdValue";
	
	public static interface Callback {
		public void onReceive(Context context, Intent intent);
	}
	
	private Callback mCallback = null;
	public VehicleReceiver(Callback cb) {
		super();
		mCallback = cb;
	}
	
	public synchronized void onDestroy() {
		mCallback = null;
	}
			
	@Override
	public synchronized void onReceive(Context context, Intent intent) {
		LogUtils.LOGD(TAG, "onReceive <----");
		if (mCallback != null) {
			mCallback.onReceive(context, intent);
		}
		LogUtils.LOGD(TAG, "onReceive ---->");

	}

}
