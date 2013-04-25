package com.zjdx.vehicle.activity.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class AudioUtils{

	private Context mContext;
	private static AudioUtils mInstance;
	private MediaPlayer mMediaPlayer;
	public static void init(Context context){
		if (mInstance == null){
			mInstance = new AudioUtils(context);
		}
	}
	public static AudioUtils getInstance(){
		return mInstance;
	}
	
	private AudioUtils(Context context){
		mContext = context;
	}
	
	public void playRingTone(int resId, boolean looping) {
		stopRingTone();
		
		//Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		try {
			//mMediaPlayer = new MediaPlayer();
			//mMediaPlayer.setDataSource(context, alert);
			mMediaPlayer = MediaPlayer.create(mContext, resId);
//			final AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
//			if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
//				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
//			}
			mMediaPlayer.setLooping(looping);
			//mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopRingTone(){
		if (mMediaPlayer != null){
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
}