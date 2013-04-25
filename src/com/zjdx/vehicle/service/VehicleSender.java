package com.zjdx.vehicle.service;

import java.util.Arrays;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;

public class VehicleSender {
	private static final String TAG = "VehicleSender ";
	private static final int SEND_INTERVAL	= 250;
	private static final int FLUSH_INTERVAL	= 1000;
			
	private int mChangedCount = 0;
	private int[] mChangedFrameIds = null;
	private Handler mHandler = null;	
	private long mLastSendTime = 0;
	private long mLastFlushTime= 0;
	
	VehicleSender(Handler handler) {		
		mHandler = handler;
		mLastSendTime = SystemClock.uptimeMillis();	
		mChangedFrameIds = new int[DatabaseHandler.size()];
	}
	
	void trySender(int count, int[] frameIds) {
		setValues(count, frameIds);
		long curTime = SystemClock.uptimeMillis();
		if (curTime >= (mLastFlushTime + FLUSH_INTERVAL)) {
			mLastFlushTime = curTime;
			mLastSendTime = curTime;
			LogUtils.LOGD(TAG, "Reflush all data.");
			setAllValues();
			doSender();	
		}
		else if (curTime >= (mLastSendTime + SEND_INTERVAL)) {
//			LogUtils.LOGD(TAG, "trySender curTime " + curTime
//					+ " mLastSendTime " + mLastSendTime
//					+ " mChangedCount " + mChangedCount);
			mLastSendTime = curTime;
			doSender();			
		}
		
	}
	
	private void setAllValues() {
		mChangedCount = mChangedFrameIds.length;
		int[] ids= DatabaseHandler.getAllFrameIds();
		System.arraycopy(ids, 0, mChangedFrameIds, 0, mChangedCount);
	}
	
	private void setValues(int count, int[] frameIds) {
		if (count == 0 || frameIds == null)
			return;
		
		if (mChangedCount == mChangedFrameIds.length)
			return;
		
		for (int i=0; i<count; i++) {
			if (!hasValue(frameIds[i])) {
				mChangedFrameIds[mChangedCount++] = frameIds[i];
			}
		}		
	}
	
	private void doSender() {
		if (mChangedCount == 0)
			return;
		
		int[] frameIdArray =  Arrays.copyOf(mChangedFrameIds, mChangedCount);
		if (mHandler != null && frameIdArray != null) {
			Message msg = mHandler.obtainMessage(VehicleService.MSG_RESPONSE, 
												frameIdArray.length, 0, frameIdArray);
			mHandler.sendMessage(msg);
		}
		clear();
	}
	
	private void clear() {
		Arrays.fill(mChangedFrameIds, 0);
		mChangedCount = 0;	
	}
	
	private boolean hasValue(int frameId) {
		for (int i=0; i<mChangedCount; i++) {
			if (mChangedFrameIds[i] == frameId) {
				return true;
			}
		}
		return false;
	}
}
