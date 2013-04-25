package com.zjdx.vehicle.service.cloud;


import java.util.Arrays;

import android.os.Handler;
import android.os.Message;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.communication.Connector;
import com.zjdx.vehicle.middleware.communication.TcpConnector;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.LooperThread;
import com.zjdx.vehicle.service.VehicleService;
import com.zjdx.vehicle.service.cloud.AbsDownLoadJsonFrame.ICommand;
import com.zjdx.vehicle.service.cloud.video.VideoController;
import com.zjdx.vehicle.service.cloud.video.VideoParameter;


public class CloudClient implements Handler.Callback {
	private static final String TAG = "CloudClient ";
	
	public static final int MSG_RECEIVE_JSON 		= 0x01;
	public static final int MSG_RECEIVE_CANFRAMES 	= 0x02;
	public static final int MSG_UPLOAD_JSONFRAMS 	= 0x03;
	public static final int MSG_SYNC_TIME			= 0x04;
	public static final int MSG_INIT				= 0x05;
	public static final int MSG_DESTROY				= 0x06;
		
	private VehicleService mService = null;
	private DatabaseHandler mDB = null;
	
	private Connector mConnector = null;
	private VideoController mVideoController = null;
	private UploadContoller mUpload = null;
	private DownloadController mDownload = null;
	private LooperThread mThread = null;
	private Handler mHandler = null;
	private boolean mQuiting = false;

	
	public CloudClient(VehicleService service, DatabaseHandler db) {
		mDB = db;
		mService = service;			
		mThread = new LooperThread();
		mThread.start();
		mHandler = new Handler(mThread.getLooper(), this);	
		mHandler.sendEmptyMessageDelayed(MSG_INIT, 5000);
	}
	
	
	public void onReceive(int count, int[] frameIdArray) {
		if (mQuiting || count <= 0 || frameIdArray == null)
			return;
		receiveCanFrame(count, frameIdArray);		
	}

	public synchronized void finish() {
		LogUtils.LOGD(TAG, "finish <----");
		if (!mQuiting && mHandler != null) {
			Message msg = new Message();
			msg.what = MSG_DESTROY;
			mHandler.sendMessageAtFrontOfQueue(msg);
		}
		
		try {
			if (mThread != null) {
				mQuiting = true;
				mThread.getLooper().quit();			
				mThread.join();			
				mThread = null;
			}				
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LogUtils.LOGD(TAG, "finish ---->");
	}
	
	private void init() {
		mVideoController = new VideoController(mService);
		
		if (mConnector == null) {
			mConnector = new TcpConnector(mService, "vehicle_cloud.cfg");
		}
		
		if (mUpload == null) {
			mUpload = new UploadContoller(mCommand, mDB, mConnector);
		}
		if (mDownload == null) {
			mDownload = new DownloadController(mCommand, mDB, mConnector);
		}
	}
	
	private void uninit() {		
		if (mUpload != null) {
			mUpload.finish();
			mUpload = null;
		}
		
		if (mDownload != null) {
			mDownload.finish();
			mDownload = null;
		}
		
		if (mConnector != null) {
			mConnector.close();
			mConnector = null;
		}
		
		if (mVideoController != null) {
			mVideoController.finish();
			mVideoController = null;
		}
	}
	
	private ICommand mCommand = new ICommand() {

		@Override
		public synchronized boolean doSendCommand(int frameId, int[] values) {
			LogUtils.LOGD(TAG, "doSendCommand: frameId: " + frameId);
			if (!mQuiting && mService != null && values != null) {
				mService.sendCommand(frameId, values);
				return true;
			}
			
			return false;
		}

		@Override
		public boolean syncJsonFrames(int time) {
			LogUtils.LOGD(TAG, "syncJsonFrames: time: " + time);
			if (!mQuiting) {
				syncTime(time);
				return true;
			}
			return false;
		}

		@Override
		public boolean setVideoParams(VideoParameter param) {
			LogUtils.LOGD(TAG, "setVideoParams: " + param);
			if (!mQuiting && mVideoController != null) {
				mVideoController.setParameter(param);
				return true;
			}
			return false;
		}

		@Override
		public boolean uploadJsonFrame(int time, String json) {
			LogUtils.LOGD(TAG, "uploadJsonFrame: time: " + time + " json " + json);
			
			if (!mQuiting) {
				upload(time, json);
				return true;
			}
			return false;
		}
		
	};


	@Override
	public boolean handleMessage(Message msg) {
		if (mQuiting && msg.what != MSG_DESTROY) 
			return true;		
		
		boolean res = true;
		switch (msg.what) {
		case MSG_INIT:
			init();
			break;
		case MSG_DESTROY:
			uninit();
			break;
		case MSG_RECEIVE_JSON:
			// I CHECK SOCKET CONNECT IN THIS FUNCTION.
			if (mDownload != null)
				mDownload.doReceiveJson();
			break;
		case MSG_RECEIVE_CANFRAMES:
			if (mUpload != null)
				mUpload.doReceiveCanFrame(msg.arg1, (int[])msg.obj);
			break;
		case MSG_UPLOAD_JSONFRAMS:
			if (mUpload != null)
				mUpload.doUpload(msg.arg1, (String)msg.obj);
			break;
		case MSG_SYNC_TIME:
			if (mUpload != null)
				mUpload.doSyncTime(msg.arg1);
			break;
		default:
			res = false;
			break;
		}
		keepAlive(250);
		return res;
	}
	
	private boolean receiveCanFrame(int count, int[] frameIdArray) {
		Message msg = new Message();
		msg.what = MSG_RECEIVE_CANFRAMES;
		msg.obj = Arrays.copyOf(frameIdArray, count);
		sendMessage(msg, true);
		return true;
	}	
	
	private boolean syncTime(int time) {
		Message msg = new Message();
		msg.what = MSG_SYNC_TIME;
		msg.arg1 = time;
		sendMessage(msg, false);
		return true;
	}
	
	private boolean upload(int time, String json) {
		if (!mQuiting && json != null) {
			Message msg = new Message();
			msg.what = MSG_UPLOAD_JSONFRAMS;
			msg.arg1 = time;
			msg.obj = json;
			sendMessage(msg, true);
		}
		return true;
	}
	
	private synchronized void sendMessage(Message msg, boolean bFront) {
		if (!mQuiting) {
			if (bFront)
				mHandler.sendMessageAtFrontOfQueue(msg);	
			else
				mHandler.sendMessage(msg);
		}
	}
	
	private synchronized void keepAlive(int delayMillis) {
		if (mQuiting)
			return;
		if (mHandler.hasMessages(MSG_RECEIVE_JSON))
			return;
		
		if (delayMillis > 0) {
			mHandler.sendEmptyMessageDelayed(MSG_RECEIVE_JSON, delayMillis);
		}
		else {
			mHandler.sendEmptyMessage(MSG_RECEIVE_JSON);
		}
	}
}
