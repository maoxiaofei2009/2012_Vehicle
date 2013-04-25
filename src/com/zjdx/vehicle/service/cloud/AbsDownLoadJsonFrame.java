package com.zjdx.vehicle.service.cloud;

import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.service.cloud.video.VideoParameter;

public abstract class AbsDownLoadJsonFrame {
	public static interface ICommand {
		public boolean doSendCommand(int frameId, int[] values);
		public boolean syncJsonFrames(int time);
		public boolean setVideoParams(VideoParameter param);
		public boolean uploadJsonFrame(int time, String json); 
	}
	
	protected DatabaseHandler mDB = null;
	protected ICommand mCommand = null;
	
	protected AbsDownLoadJsonFrame(ICommand c, DatabaseHandler db) {
		mDB = db;
		mCommand = c;
	}
	
	protected boolean doSendCommand(int frameId, int[] values) {
		boolean res = false;
		if (mCommand != null && values != null) {
			res = mCommand.doSendCommand(frameId, values);
		}
		return res;
	}
	
	protected boolean syncJsonFrames(int time) {
		boolean res = false;
		if (mCommand != null) {
			res = mCommand.syncJsonFrames(time);
		}
		return res;
	}
	
	protected boolean setVideoParams(VideoParameter param) {
		boolean res = false;
		if (mCommand != null) {
			res = mCommand.setVideoParams(param);
		}
		return res;
	}
	
	protected void onDestroy() {
		
	}
	
	protected abstract boolean onReceiveJsonFrame(JSONObject json);
}
