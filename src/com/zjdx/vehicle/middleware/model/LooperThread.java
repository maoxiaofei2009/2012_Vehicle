package com.zjdx.vehicle.middleware.model;

import com.zjdx.vehicle.LogUtils;

import android.os.ConditionVariable;
import android.os.Looper;


public class LooperThread extends Thread {
	private final static String TAG = "LooperThread"; 
    private ConditionVariable mThreadLooperAvailable; 
	private Looper mThreadLooper = null;


	public LooperThread(){
        mThreadLooperAvailable  = new ConditionVariable();
		mThreadLooper = null;
	}
	public Looper getLooper(){
		//wait for its create
		if (null == mThreadLooper){
			LogUtils.LOGD(TAG, "wait for thread looper.");				
	        mThreadLooperAvailable.block();
	        mThreadLooperAvailable.close();
		}
		return mThreadLooper;
	}
		
	@Override 
	public void run() {
		LogUtils.LOGD(TAG, "run <----");
		Looper.prepare();		
		mThreadLooper = Looper.myLooper();	
		LogUtils.LOGD(TAG, "create amui thread looper");
		mThreadLooperAvailable.open();
		Looper.loop();
		LogUtils.LOGD(TAG, "run ---->");		
	}	
}
