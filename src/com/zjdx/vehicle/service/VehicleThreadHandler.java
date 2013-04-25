package com.zjdx.vehicle.service;


import java.util.Arrays;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.communication.Connector;
import com.zjdx.vehicle.middleware.communication.FackConnector;
import com.zjdx.vehicle.middleware.communication.JNI_VehicleMiddleWare;
import com.zjdx.vehicle.middleware.communication.MyDataPackage;
import com.zjdx.vehicle.middleware.communication.Parser;
import com.zjdx.vehicle.middleware.communication.SocketConnector;
import com.zjdx.vehicle.middleware.communication.TcpConnector;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.LooperThread;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;


public class VehicleThreadHandler implements Handler.Callback {
	private static final String TAG = "VehicleThreadHandler ";
	private static final int MAX_PACKAGE_LENGTH = Defines.CANFRAME_LENGTH * 100;
	private static final long RECONNECT_TIME = 2000;
	
	public static final int DELAY_TIME = 30; 
	public static final int INIT = 0x2001;
	public static final int READ = 0x2002;
	public static final int WRITE = 0x2003;
	public static final int DESTROY = 0x2004;
	
	private boolean mConnected = false;
	private boolean mQuiting = false;
	private Context mContext = null;
	private Connector mConnector = null;
	private DatabaseHandler mDB = null;
	private MyDataPackage mPkg = null;
	private VehicleSender mSender = null;
	private ADSSoundPlayer mADSSoundPlayer = null;
	private LooperThread mThread = null;
	private Handler mHandler = null;
		
	private long mLastFailReadTime = 0;
	private int[] mIdentity = new int[1];
	private int[] mChangedFrameIdArray = null;
	private byte[] mPayload = new byte[Defines.PAYLOAD_LENGTH];
	
	public VehicleThreadHandler(Context context, Handler handler, DatabaseHandler db) {
		mThread = new LooperThread();
		mThread.start();
		mHandler = new Handler(mThread.getLooper(), this);		
		
		mDB = db;
		mContext = context;		
		mSender = new VehicleSender(handler);
		mPkg = new MyDataPackage(0, new byte[MAX_PACKAGE_LENGTH]);
		mConnector = VehicleThreadHandler.ConnectorFactory(mContext);	
		mChangedFrameIdArray = new int[MAX_PACKAGE_LENGTH/Defines.CANFRAME_LENGTH];
	}
	
	public boolean handleMessage(Message msg) {
		if (mQuiting && msg.what != DESTROY) 
			return true;		
		
		switch (msg.what) {	
		case INIT:
			doInit();
			break;
					
		case READ:
			// TODO check socket is connected.
			
//			LogUtils.LOGD(TAG, "receive READ Msg...");
			if (!mHandler.hasMessages(WRITE)) {
				read();	
			}
			else {
				LogUtils.LOGD(TAG, "Has WRITE MSG, Ignore READ.");
			}
			break;
		case WRITE:
			LogUtils.LOGD(TAG, "receive WRITE Msg...");
			write(msg);			
			break;
		case DESTROY:
			doUninit();
			break;
		default:
			break;
		}
		keepAlive();
		
		return true;
	}
	
	private static Connector ConnectorFactory(Context context) {		
//		This is factory method.
		Connector connector= null;
		
//		Socket Connector. 
//		connector = new SocketConnector(context, "vehicle.cfg");

//		TCP Socket Connector.
		connector = new TcpConnector(context,"vehicle.cfg");
		
//		Fake Connector.
//		connector = new FackConnector(context);
		
		return connector;
	}
	
	public void init() {
		if (mHandler != null && !mQuiting) {
			mHandler.sendEmptyMessage(INIT);
		}
	}
	
	public void doInit() {
		if (mConnector != null) {
			mConnected = mConnector.open();
		}	
		
		if (mADSSoundPlayer == null) {
			mADSSoundPlayer = new ADSSoundPlayer(mContext);
		}
		keepAlive();
	}
	
	private void doUninit() {
		if (mConnector != null) {
			mConnector.close();	
			mConnected = false;
		}
		
		if (mADSSoundPlayer != null) {
			mADSSoundPlayer.finish();
			mADSSoundPlayer = null;
		}
	}
	
	public void finish() {
		LogUtils.LOGD(TAG, "finish <----");
		if (!mQuiting && mHandler != null) {
			Message msg = new Message();
			msg.what = DESTROY;
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
	
	// THE CANFRAME.	
	private void read() {
		mHandler.removeMessages(READ);		
		
		if (!mConnected || mSender == null)
			return;
		
		int count = 0;
		mPkg.count = 0;
		long curTime = SystemClock.uptimeMillis();
		if (mConnector.read(mPkg) && mPkg.count != 0) {
			int pos = 0;
			mLastFailReadTime = curTime;
			Arrays.fill(mChangedFrameIdArray, 0);
			for (int i=0; i<mPkg.count; i++) {	
				pos = Defines.CANFRAME_LENGTH * i;
				if (0 != JNI_VehicleMiddleWare.GetCanFrame(mPkg.content, pos, 
															mIdentity, mPayload)) {
					LogUtils.LOGD(TAG, "GetCanFrame Error: " + mPkg.content[pos]);
					continue;			
				}
			   
		        int[] intArray = Parser.decodePayload(mIdentity[0], mPayload);		        
	//	        LogUtils.LOGD(TAG, "GetCanFrame ID: " + mIdentity[0]);
		        preprocess(mIdentity[0], intArray);
		        if (intArray != null && mDB.updateTable(mIdentity[0], intArray)) {	    	
		        	mChangedFrameIdArray[count++] = mIdentity[0];
		    		if (count >= mChangedFrameIdArray.length) {
		    			LogUtils.LOGE(TAG, "read count >= max count.");
		    			break;
		    		}	    		
		        }
			}							
		}
		else if (curTime >= (mLastFailReadTime + RECONNECT_TIME)) {
			mConnector.close();
			mLastFailReadTime = curTime;
			LogUtils.LOGE(TAG, "reach RECONNECT_TIME .... ");
		}
//		LogUtils.LOGD(TAG, "read Message frame Count: " + mPkg.count + " changed: " + count);	
		mSender.trySender(count, mChangedFrameIdArray);		
	}
	
	// THE CANFRAME.
	private void write(Message msg) {
		if (!mConnected || msg.obj == null)
			return;
		
		int identity = msg.arg1;
		int[] values = (int[])msg.obj;
		byte[] frame = new byte[Defines.CANFRAME_LENGTH];		
		byte[] payload = Parser.encodePayload(identity, values);
		if (payload == null) {
			return;
		}
		
//		ModelHelper.dumpByteArray(TAG + " write payload: ", payload);
		if (0 != JNI_VehicleMiddleWare.SetCanFrame(frame, identity, payload)) {
			LogUtils.LOGE(TAG, "SetCanFrame failed, id: " + identity);
			return;	
		}
		mConnector.write(frame);
		LogUtils.LOGD(TAG, "write id: " + identity + " ok ");
	}	
	
//	private void write(Message msg) {
//		byte[] frame = new byte[Defines.CANFRAME_LENGTH];
//		byte[] payload = new byte[] {
//			12,34,56,78,12,34,56,78
//		};
//		JNI_VehicleMiddleWare.SetCanFrame(frame, 0x101, payload);
//		mConnector.write(frame);
//	}
	
	private synchronized void keepAlive() {
		if (mQuiting) 
			return;
				
		mHandler.sendEmptyMessageDelayed(READ, DELAY_TIME);
		
//		mHandler.sendEmptyMessageDelayed(WRITE, DELAY_TIME);
	}
	
	public synchronized void sendMessageSync(Message msg) {
		mHandler.sendMessageAtFrontOfQueue(msg);
//		mHandler.sendMessage(msg);
	}
	
	private void preprocess(int frameId, int[] values) {
		if (values == null)
			return;			
	
		if (mADSSoundPlayer != null && frameId == Defines.ID_WARNING_INFO_FROM_DAS) {
			mADSSoundPlayer.update(values);
		}
	}
	
}
