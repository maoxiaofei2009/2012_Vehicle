package com.zjdx.vehicle.service.cloud;

import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;

public abstract class AbsUploadJsonFrame {
	public static interface UploadCallback {
		boolean upload(int time, String json);
	}
	
	protected DatabaseHandler mDB = null;
	protected UploadCallback  mUploadCallback = null;
	
	protected AbsUploadJsonFrame(DatabaseHandler db, UploadCallback cb) {
		mDB = db;
		mUploadCallback = cb;
	}	
	
	protected String JSON2String(JSONObject json) {
		if (json == null)
			return null;
		
		StringBuilder builder = new StringBuilder();
		builder.append(json.toString());
		builder.append('\n');
		
		return builder.toString();
	}
	
	protected boolean upload(int time, String json) {
		boolean res = false;
		if (mUploadCallback != null) {
			res = mUploadCallback.upload(time, json);
		}
		
		return res;
	}
	
	protected CanFrameData getCanFrameData(int frameId) {
		CanFrameData data = new CanFrameData(frameId);
		data.setDB(mDB);
		data.loadFromDB();
		
		return data;
	}
	
	protected void onDestroy() {
		
	}
	
	protected abstract boolean onReceive(int count, int[] frameIdArray);
}
