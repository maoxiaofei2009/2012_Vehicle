package com.zjdx.vehicle.service;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.LooperThread;
import com.zjdx.vehicle.service.cloud.CloudClient;

public class VehicleService extends Service implements Handler.Callback{
	private static final String TAG = "VehicleService ";		
	public static final int MSG_RESPONSE = 0x1000;
		
	public static interface Callback {
		public void onReceive(int count, int[] frameIdArray);
	}
	
	private DatabaseHandler mDB = null;
//	private LooperThread mVehicleThread = null;
	private VehicleThreadHandler mVehicleThreadHandler = null;
	private Handler mMainHandler = null;
	private Callback mCallback = null;
	private CloudClient mCloudClient = null;
	
	public class LocalBinder extends Binder {
		VehicleService getService() {
            return VehicleService.this;
        }
    }
	
	@Override
    public void onCreate() {
        super.onCreate();
        LogUtils.LOGD(TAG, "onCreate <----");
        LocationMgr.Instance().onCreate(this);
        mDB = DatabaseHandler.Instance();          
        mMainHandler = new Handler(this);
        mCloudClient = new CloudClient(this, mDB);
//        mVehicleThread = new LooperThread();
//        mVehicleThread.start();
        mVehicleThreadHandler = new VehicleThreadHandler(this, mMainHandler, mDB);
        mVehicleThreadHandler.init();
        LogUtils.LOGD(TAG, "onCreate ---->");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return mBinder;
    }
    private final IBinder mBinder = new LocalBinder();
    
   public synchronized void sendCommand(int frameId, int[] values) {
	   if (mVehicleThreadHandler == null || values == null) {
		   return;
	   }
	  	   
	   Message msg = new Message();
	   msg.what = VehicleThreadHandler.WRITE;
	   msg.arg1 = frameId;
	   msg.obj = values;
	   mVehicleThreadHandler.sendMessageSync(msg);	   
   }
    
   public void setCallback(Callback cb) {
	   mCallback = cb;
   }
    
    @Override
    public void onDestroy() {
    	LogUtils.LOGD(TAG, "onDestroy <----");  	
		if (mCloudClient != null) {
			mCloudClient.finish();
			mCloudClient = null;
		}
					
		if (mVehicleThreadHandler != null) {
			mVehicleThreadHandler.finish();
			mVehicleThreadHandler = null;			
    	}
    	
		LocationMgr.Instance().onDestroy();
		LogUtils.LOGD(TAG, "onDestroy ---->");
    	super.onDestroy();
    }
    
	@Override
	public boolean handleMessage(Message msg) {
		boolean res = true;
		switch (msg.what) {
		case MSG_RESPONSE:
		{		
			LogUtils.LOGD(TAG, "handleMessage MSG_RESPONSE " + msg.arg1 + " <----"); 	
			onReceive(msg.arg1, (int[]) msg.obj);
			LogUtils.LOGD(TAG, "handleMessage MSG_RESPONSE " + msg.arg1 + " ---->");
		} 	break;
		default:
			res = false;
			break;
		}
		return res;
	}
	
	public void onReceive(int count, int[] frameIdArray) {
		Callback cb = mCallback;
		if (cb != null) {
			cb.onReceive(count, frameIdArray);
		}
		
		if (mCloudClient != null) {
			mCloudClient.onReceive(count, frameIdArray);
		}
	}

}
