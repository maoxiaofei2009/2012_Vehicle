package com.zjdx.vehicle.service;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

public class SoundPlayer {
	private SoundPool soundPool;
	private SparseIntArray soundPoolMap;
	
	/**
     * Default constructor associates this manager with an instance of SoundPool to manage
     * sounds.
     */
	public SoundPlayer(int maxStreams) {
		soundPool = new SoundPool(maxStreams, AudioManager.STREAM_SYSTEM, 0);
		soundPoolMap = new SparseIntArray();
	}
	
	/** 
	 * Load the sound resource which needs to be played.<br>
	 * Note: This is an asynchronous function. Calling playSound
	 * right after loadSound may cause an error. 
	 * 
  	 * @param key The key information of the sound file.
   	 * @param context The Context of a application.
  	 * @param resId The identity information of the sound file.
     */
	public void loadSound(int key, Context context, int resId) {
		if(soundPoolMap != null && soundPool != null)
			soundPoolMap.put(key, soundPool.load(context, resId, 1));
	}

	/** 
	 * Play the sound resource which had been loaded.
	 * 
  	 * @param key  The key information of the sound file.
     */
	public void playSound(int key){
		if(soundPoolMap != null && soundPool != null)
			soundPool.play(soundPoolMap.get(key), 1f, 1f, 1, 0, 1f);
	}
	
	/** 
	 * Play the sound resource which had been loaded.
	 * 
  	 * @param key  	The key information of the sound file.
  	 * @param volume The volume which the sound play at(range = 0.0 to 1.0).
  	 * @param loop  	Loop mode (0 = no loop, -1 = loop forever).
  	 * @param rate 	Playback rate(1.0 = normal playback, range 0.5 to 2.0).
     */	
	public void playSound(int key, float volume, int loop, float rate){
		if(soundPoolMap != null && soundPool != null)
			soundPool.play(soundPoolMap.get(key), volume, volume, 1, loop, rate);
	}
	
	/** 
	 * Release sound resources used in the SoundPool.
     */	
	public void release(){
		if(soundPool != null){
			soundPool.release();
			soundPool = null;
		}
		if(soundPoolMap != null){
			soundPoolMap.clear();
			soundPoolMap = null;
		}
	}
}
