package com.zjdx.vehicle.service;


import com.zjdx.vehicle.LogUtils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class VehicleBinding {
	private static final String TAG = "VehicleBinding ";
	public static interface Callback {
		public void onChangingServiceConnectity(VehicleService service);
	}
	
	private Context mContext = null;
	private boolean mIsBound = false;
	private VehicleService mBoundService = null;
	private Callback mCallback = null;
	
	public VehicleBinding(Context context, Callback cb) {
		LogUtils.LOGD(TAG, "VehicleBinding <----");
		mContext = context;
		mCallback = cb;
		onCreate();
		LogUtils.LOGD(TAG, "VehicleBinding ---->");
	}
	
	private synchronized void onCreate() {
		doBindService();
	}

	public synchronized void onDestroy() {
		mCallback = null;
		doUnbindService();
	}
	
	public VehicleService getService() {
		return mBoundService;
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
        	LogUtils.LOGD(TAG, "onServiceConnected <----");
            mBoundService = ((VehicleService.LocalBinder)service).getService();  
            if (mCallback != null) {
            	mCallback.onChangingServiceConnectity(mBoundService);
            }
            LogUtils.LOGD(TAG, "onServiceConnected ---->");
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mBoundService = null; 
            if (mCallback != null) {
            	mCallback.onChangingServiceConnectity(mBoundService);
            }
        }
    };
    
    private void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
    	
    	LogUtils.LOGD(TAG, "doBindService <----");
    	mContext.bindService(new Intent(mContext, 
    			VehicleService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
        LogUtils.LOGD(TAG, "doBindService ---->");
    }
    
    private void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
        	LogUtils.LOGD(TAG, "doUnbindService <----");
        	mContext.unbindService(mConnection);
            mIsBound = false;
            LogUtils.LOGD(TAG, "doUnbindService ---->");
        }
    }
}
