package com.zjdx.vehicle.service.cloud;


import org.json.JSONException;
import org.json.JSONObject;

import android.util.SparseArray;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.communication.Connector;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.service.cloud.AbsDownLoadJsonFrame.ICommand;


public class DownloadController {
	private static final String TAG = "DownloadController";	
	
	private SyncJsonFrame mSyncJsonFrame = null;
	private SparseArray<AbsDownLoadJsonFrame> mList = null;
	private Connector mConnector = null;
	private DatabaseHandler mDB = null;
	private ICommand mCommand = null;
	private boolean mQuiting = false;
	
	DownloadController(ICommand command, DatabaseHandler db, Connector connector) {
		mDB = db;
		mCommand = command;
		mConnector = connector;
		createDownloadJsonFrameList();		
	}
	
	void finish() {
		LogUtils.LOGD(TAG, "finish <----");	
		mQuiting = true;
		destroyDownloadJsonFrameList();
		LogUtils.LOGD(TAG, "finish ---->");
	}
	
	boolean doReceiveJson() {
		if (mQuiting || mList.size() == 0)
			return false;
		try {
			do {
				String strJson = receiveFromCloud();
				if (strJson == null) {
					checkCloudState();
					break;				
				}
				
				AbsDownLoadJsonFrame item = null;
				JSONObject json = new JSONObject(strJson);
				String vid = json.getString(CloudHelper.VID);
				if (vid == null || !vid.equals(CloudHelper.getVID())) {
					LogUtils.LOGE(TAG, "doReceiveJson unknown vid: " + vid
							+ " expected vid: " + CloudHelper.getVID());
					break;
				}				
				
				int InfoType = json.getInt(CloudHelper.InfoType);
				if (InfoType == CloudHelper.InfoType_SyncCommand) {
					item = mSyncJsonFrame;
				}
				else if (InfoType == CloudHelper.InfoType_CanFrameCommand) {
					int CtlObj = json.getInt(CloudHelper.CtlObj);
					item = mList.get(CtlObj);					
				}
				
				if (item != null) {
					item.onReceiveJsonFrame(json);
				}
				
			} while (false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return true;
	}
	
	private String receiveFromCloud() {
		String json = null;
		if (mConnector != null) {
			json = mConnector.read();			
		}
		
		return json;
	}
	
	private void checkCloudState() {
		if (mConnector != null) {
			mConnector.checkState();
		}
	}
	
	private void createDownloadJsonFrameList() {
		mList = new SparseArray<AbsDownLoadJsonFrame>();
		mList.put(AirconditionJsonFrame.getCtlObj(),  new AirconditionJsonFrame(mCommand,mDB));
		mList.put(DoorJsonFrame.getCtlObj(),  new DoorJsonFrame(mCommand,mDB));
		mList.put(HornJsonFrame.getCtlObj(),  new HornJsonFrame(mCommand,mDB));
		mList.put(LampJsonFrame.getCtlObj(),  new LampJsonFrame(mCommand,mDB));
		mList.put(VideoJsonFrame.getCtlObj(),  new VideoJsonFrame(mCommand,mDB));	
		
		mSyncJsonFrame = new SyncJsonFrame(mCommand,mDB);
	}
	
	private void destroyDownloadJsonFrameList() {
		if (mList.size() != 0) {
			mSyncJsonFrame.onDestroy();
			for (int i=0,cnt=mList.size(); i<cnt; i++) {
				AbsDownLoadJsonFrame item = mList.get(mList.keyAt(i));
				if (item != null) {
					item.onDestroy();
				}
			}		
			mList.clear();
		}
	}
}
