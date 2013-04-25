package com.zjdx.vehicle.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.R;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.LooperThread;

public class ADSSoundPlayer implements Handler.Callback {
	private static final String TAG = "ADSSoundPlayer ";
	private static final int UPDATE_TIME = 250;
	private static final int LATENCY_TIME = 500;
		
	private final int MSG_PLAY = 0x01;
	private final int MSG_STOP = 0x02;
	
	private Context mContext = null;
	private SoundPlayer mPlayer = null;
	private LooperThread mThread = null;
	private Handler mHandler = null;
	private CanFrameData mADS = null;
	private boolean mQuiting = false;
	private boolean mPlaying = false;
	private int mCurrentId = 0;
	private long mLastTime = 0;
	
	public ADSSoundPlayer(Context context) {
		mContext = context;
		mADS = new CanFrameData(Defines.ID_WARNING_INFO_FROM_DAS);
			
		mPlayer = new SoundPlayer(1);
		mPlayer.loadSound(R.raw.audio_hmw_alert, mContext, R.raw.audio_hmw_alert);
		mPlayer.loadSound(R.raw.audio_ldw_alert, mContext, R.raw.audio_ldw_alert);
		mPlayer.loadSound(R.raw.audio_pcw_fcw_alert, mContext, R.raw.audio_pcw_fcw_alert);
		
		mThread = new LooperThread();
		mThread.start();
		mHandler = new Handler(mThread.getLooper(), this);	
		LogUtils.LOGD(TAG, " constructor ");
	}
	
	public void update(int[] ads) {
		mADS.setAllValues(ads);
		
		long curTime = SystemClock.uptimeMillis();
		if (curTime >= (mLastTime + UPDATE_TIME)) {
			mLastTime = curTime;		
		
			int id = 0;
			int soundId = mADS.getValue(Defines.SOUNDMODE);
			switch(soundId) {
			case 1: id = R.raw.audio_ldw_alert; break;
			case 2: id = R.raw.audio_ldw_alert; break;
			case 3: id = R.raw.audio_hmw_alert; break;
			case 6: id = R.raw.audio_pcw_fcw_alert; break;		
			default: break;
			}
//			LogUtils.LOGD(TAG, "update soundId " + soundId + " mCurrentId " + mCurrentId);
			Message msg = mHandler.obtainMessage(MSG_PLAY, id, 0);
			mHandler.sendMessage(msg);
		}
	}	
	
	public void finish() {
		LogUtils.LOGD(TAG, "finish <----");
		try {
			if (mThread != null) {
				mQuiting = true;		
				mThread.getLooper().quit();			
				mThread.join();			
				mThread = null;
			}
			if (mPlayer != null) {
				mPlayer.release();
				mPlayer = null;
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogUtils.LOGD(TAG, "finish ---->");
	}
	
	@Override
	public boolean handleMessage(Message msg) {
		if (mQuiting) 
			return true;		
		
		boolean res = true;
		switch (msg.what) {
		case MSG_PLAY:
			play(msg.arg1);
			break;
		case MSG_STOP:
			stop();
			break;		
		default:
			res = false;
			break;
		}
		return res;
	}
	
	private void play(int id) {	
//		LogUtils.LOGD(TAG, "play id " + id + 
//				" mCurrentId " + mCurrentId
//				+ " mPlaying " + mPlaying);
		if (id != mCurrentId) {
			stop();
			mCurrentId = id;
		}
		
		if (!mPlaying && mCurrentId != 0) {
			mPlayer.playSound(mCurrentId);
			mPlaying = true;
			mHandler.sendEmptyMessageDelayed(MSG_STOP, LATENCY_TIME);
		}
	}
	
	private void stop() {
//		LogUtils.LOGD(TAG, "stop " + " mCurrentId " + mCurrentId + " mPlaying " + mPlaying);
		mHandler.removeMessages(MSG_STOP);
		if (mPlaying) {
			mCurrentId = 0;
			mPlaying = false;
		}
	}
}
