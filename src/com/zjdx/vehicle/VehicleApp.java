package com.zjdx.vehicle;

import com.zjdx.vehicle.activity.main.data.VehicleModel;
import com.zjdx.vehicle.activity.utils.AudioUtils;
import com.zjdx.vehicle.activity.utils.SharedPreferenceUtils;

import android.app.Application;
import android.widget.Toast;

/* adb logcat -v time > E:/log.txt */

public class VehicleApp extends Application {
	private static final String TAG = "VehicleApp ";
	private static Toast mToast = null;
	private final static String DATA_PATH = "/data/data/com.zjdx.vehicle/";
	
	public void onCreate() {
		super.onCreate();
		loadLib();
		AudioUtils.init(this);
		VehicleModel.Instance().onCreate(this);
		//AudioUtils.getInstance().playRingTone(R.raw.audio_ldw_alert, true);
		mToast = Toast.makeText(getApplicationContext(), " ", Toast.LENGTH_SHORT);	
		
		initMaintanenceSetting();
	}
	
	private void initMaintanenceSetting(){
		long time = SharedPreferenceUtils.getMaintanenceBrakeTimerRecord(this);
		if (time == -1){
			SharedPreferenceUtils.setMaintanenceBrakeTimerRecord(this, System.currentTimeMillis());
		}
		
		time = SharedPreferenceUtils.getMaintanenceCheckTimerRecord(this);
		if (time == -1){
			SharedPreferenceUtils.setMaintanenceCheckTimerRecord(this, System.currentTimeMillis());
		}
		
		long mile = SharedPreferenceUtils.getMaintanenceBrakeMileRecord(this);
		if (mile == -1){
			SharedPreferenceUtils.setMaintanenceBrakeMileRecord(this, 0);
		}
		
		mile = SharedPreferenceUtils.getMaintanenceCheckMileRecord(this);
		if (mile == -1){
			SharedPreferenceUtils.setMaintanenceCheckMileRecord(this, 0);
		}
	}
	
	public static void makeToast(String message){
		if (mToast != null){
			//mToast.cancel();
			mToast.setText(message);
			mToast.show();
		}
	}
	
	private void loadLib() {
		//System.load(DATA_PATH + "lib/libmiddleware.so");
		System.loadLibrary("middleware");
	}

}
