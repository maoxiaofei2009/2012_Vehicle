package com.zjdx.vehicle.service.cloud;

import java.util.ArrayList;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.communication.Connector;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.service.cloud.AbsDownLoadJsonFrame.ICommand;


public class UploadContoller implements AbsUploadJsonFrame.UploadCallback{
	private static final String TAG = "UploadContoller ";	
	private static final int MAX_SYNC_FRAMES_COUNT	= 10;	
	
	private ArrayList<AbsUploadJsonFrame> mList = null;
	private Connector mConnector = null;
	private ICommand mCommand = null;
	private DatabaseHandler mDB = null;
	private JsonFramePool mPools = null;
	private boolean mQuiting = false;
	
	UploadContoller(ICommand command, DatabaseHandler db, Connector connector) {
		mDB = db;
		mConnector = connector;
		mCommand = command;
		mPools = new JsonFramePool();
		createUploadJsonFrameList();
	}
			
	void finish() {
		LogUtils.LOGD(TAG, "finish <----");		
		mQuiting = true;
		destroyUploadJsonFrameList();
		LogUtils.LOGD(TAG, "finish ---->");
	}
			
	@Override
	public boolean upload(int time, String json) {
		if (!mQuiting && json != null && mCommand != null) {
			mCommand.uploadJsonFrame(time, json);
		}
		return true;
	}
		
	private void createUploadJsonFrameList() {
		mList = new ArrayList<AbsUploadJsonFrame>();
		mList.add(new BasicInfoJsonFrame(mDB, this));
		mList.add(new ConnectJsonFrame(mDB, this));
		mList.add(new FaultWarningJsonFrame(mDB, this));		
		mList.add(new StateJsonFrame(mDB, this));
	}
	
	private void destroyUploadJsonFrameList() {
		if (!mList.isEmpty()) {
			for (AbsUploadJsonFrame item : mList) {
				if (item != null) {
					item.onDestroy();
				}
			}
			mList.clear();
		}
	}
	
	boolean doReceiveCanFrame(int count, int[] frameIdArray) {
		for (AbsUploadJsonFrame item : mList) {
			if (mQuiting)
				break;
			
			if (item != null) {
				item.onReceive(count, frameIdArray);
			}
		}		
		
		return true;
	}
	
	boolean doUpload(int time, String json) {
		if (json == null)
			return false;
		
		if (!sendToCloud(json) && time != 0) {
			pushJson(time, json);		
		}
		
		return true;
	}
	
	boolean doSyncTime(int time) {
		int cnt = 0;
		while (cnt++ <= MAX_SYNC_FRAMES_COUNT) {
			String json = popJson(time);
			if (json == null)
				break;
			if (!doUpload(time, json)) {
				break;
			}
		}
		
		if (cnt >= MAX_SYNC_FRAMES_COUNT && mCommand != null) {
			mCommand.syncJsonFrames(time);			
		}
		
		return true;
	}
	
	private synchronized boolean pushJson(int time, String json) {
		boolean res = false;
		if (mPools != null) {
			res = mPools.pushJson(time, json);
		}
		return res;
	}
	
	private synchronized String popJson(int time) {
		String json = null;
		if (mPools != null) {
			json = mPools.popJson(time);
		}
		return json;
	}
	
	private boolean sendToCloud(String json) {
		LogUtils.LOGD(TAG, "sendToCloud: "+ json);
		boolean res = false;
		if (mConnector != null && json != null) {
			res = mConnector.write(json);
		}
		return res;
	}
	
}
