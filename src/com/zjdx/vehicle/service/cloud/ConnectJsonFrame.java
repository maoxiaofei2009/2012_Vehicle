package com.zjdx.vehicle.service.cloud;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;

public class ConnectJsonFrame extends AbsUploadJsonFrame {
	private static final String TAG = "ConnectJsonFrame ";
	private static final int InfoType	= CloudHelper.InfoType_ConnectCommand;
	private static final long DELAY		= 5000;
	private static final long PERIOD	= 5000;
	
	private Timer mTimer = null;
	private TimerTask mTask = null;
	private String mStrJsonFrame = null;
	
	protected ConnectJsonFrame(DatabaseHandler db, UploadCallback cb) {
		super(db, cb);
		startTimer();		
	}
		
	protected String genJsonFrame() {
		if (mStrJsonFrame == null) {			
			try {
				JSONObject json = new JSONObject();
				json.put(CloudHelper.VID, CloudHelper.getVID());
				json.put(CloudHelper.InfoType, InfoType);
				mStrJsonFrame = JSON2String(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return mStrJsonFrame;
	}
	
	
	protected void onDestroy() {
		stopTimer();
	}
	
	@Override
	protected boolean onReceive(int count, int[] frameIdArray) {
		return true;
	}
	
	private void startTimer() {
		long period = PERIOD;
		mTimer = new Timer();
		mTask = new MyTimerTask();
		if(period == 0)
			mTimer.schedule(mTask, DELAY);
		else
			mTimer.schedule(mTask, DELAY, PERIOD);
	}
	
	private void stopTimer() {
		if(mTask != null){
			mTask.cancel();
			mTask = null;
		}
		
		if(mTimer != null){
			mTimer.cancel();
			mTimer = null;
		}
	}
	
	private class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			LogUtils.LOGD(TAG, "run ...");
			upload(0,genJsonFrame());
		}		
	}
}
