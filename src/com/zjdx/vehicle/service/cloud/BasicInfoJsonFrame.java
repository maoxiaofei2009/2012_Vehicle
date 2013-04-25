package com.zjdx.vehicle.service.cloud;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;
import com.zjdx.vehicle.service.LocationMgr;


public class BasicInfoJsonFrame extends AbsUploadJsonFrame {
	private static final String TAG = "BasicInfoJsonFrame ";
	private static final int InfoType	= CloudHelper.InfoType_BasicInfo;
	
	private static final long DELAY		= 5000;
	private static final long PERIOD	= 1000;
	
	private Timer mTimer = null;
	private TimerTask mTask = null;
	private int mTimeStamp = 0;
	
	protected BasicInfoJsonFrame(DatabaseHandler db, UploadCallback cb) {
		super(db, cb);
		startTimer();		
	}
		
	protected String genJsonFrame() {
		String strJsonFrame = null;			
		try {
			mTimeStamp = CloudHelper.getTime();
			JSONObject json = new JSONObject();
			json.put(CloudHelper.VID, CloudHelper.getVID());
			json.put(CloudHelper.InfoType, InfoType);
			json.put("Time", mTimeStamp);
			
			genGPS(json);
			genBatteryInfo(json);
			genBatCurrentAndVoltage(json);
			genBodyLight(json);
			genAirCondition(json);
			genStalls(json);
			genSpeedMileageInfo(json);
			
			strJsonFrame = JSON2String(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strJsonFrame;
	}
	
	private String double2String(double d) {
		int v = (int) d * 10000;
		StringBuilder builder = new StringBuilder();	
		if (v > 0) {
			builder.append('+');
		}
		else {
			builder.append('-');
		}
		builder.append("" + (v/10000)).append("" + (v%1000));
		return builder.toString();
	}
	
	private void genGPS(JSONObject json) throws JSONException {
		boolean valid = false;
		String lat = "+0.0000";
		String lon = "+0.0000";
		Location loc = LocationMgr.getLocation();
		if (loc != null) {
			lat = double2String(loc.getLatitude());
			lon = double2String(loc.getLongitude());
			valid = true;
		}
		
		json.put("GpsValid", valid);
		json.put("LatValue", lat);
		json.put("LonValue", lon);		
	}
	
	private void genBatteryInfo(JSONObject json) throws JSONException {
		CanFrameData data = getCanFrameData(Defines.ID_BATTERY_INFO);
		if (data != null) {				
			float soc = data.getValue(Defines.SOC) * 0.004f;
			soc = (soc < 0.0f)? 0.0f: (soc > 1.0f)? 1.0f : soc;
			int DrivMileage = (int)(soc * 0.8f * 100);
			json.put("SOC", soc);
			json.put("DrivMileage", DrivMileage);
		}
	}
	
	private void genBatCurrentAndVoltage(JSONObject json) throws JSONException {
		CanFrameData data = getCanFrameData(Defines.ID_BATTERY_TOTAL_V_A);
		if (data != null) {	
			float volage = 0.02f * data.getValue(Defines.TOTAL_VOLAGE0); //动力电池的总电压
			float current= 0.1f * data.getValue(Defines.TOTAL_CURRENT0) - 3200; //动力电池的总电压
			json.put("BatVoltage", volage);
			json.put("BatCurrent", current);
		}
	}
	
	private void genBodyLight(JSONObject json) throws JSONException {
		CanFrameData data = getCanFrameData(Defines.ID_BODYLIGHT_INFO);
		if (data != null) {			
			json.put("HighBeam", data.getValue(Defines.HIGHBEAM) == 1); //远光指示灯		
			json.put("LowLamp", data.getValue(Defines.LOWBEAM) == 1); //近光指示灯		
			json.put("LeftLamp", data.getValue(Defines.LEFTSTEERING) == 1); //左转指示灯	
			json.put("RightLamp", data.getValue(Defines.RIGHTSTEERING) == 1); //右转指示灯			
			json.put("FrontFogLamp", data.getValue(Defines.HEADFOGLIGHT) == 1); //前雾指示灯	
			json.put("RearFogLamp", data.getValue(Defines.REARFOGLIGHT) == 1); //后雾指示灯	
		}
	}
	
	private void genAirCondition(JSONObject json) throws JSONException {
		CanFrameData data = getCanFrameData(Defines.ID_ACCESSORY_STATUS);
		if (data != null) {			
			json.put("AirCondStatus", data.getValue(Defines.AIRCONDITION) == 1); //空调状态	
		}
		
		data = getCanFrameData(Defines.ID_AIR_CONDITION_INFO);
		if (data != null) {
			int temp = data.getValue(Defines.AIRCONTEMP) - 40; /*17 ~ 34*/
			temp = (temp>=17 && temp<=34)? temp : 24;
			json.put("AirCondMode", data.getValue(Defines.AIRCONMODE)); //空调模式	
			json.put("AirCondTemp", temp); //空调温度	
			json.put("FanSpeed", data.getValue(Defines.AIRSPEED)); //空调风速	
		}		
	}
	
	private void genStalls(JSONObject json) throws JSONException {
		CanFrameData data = getCanFrameData(Defines.ID_CONTROLLERS_INFO);
		if (data != null) {			
			json.put("Stalls", data.getValue(Defines.GEARS)); //档位
		}
	}
	
	private void genSpeedMileageInfo(JSONObject json) throws JSONException {
		CanFrameData data = getCanFrameData(Defines.ID_SPEED_MILEAGE_INFO);
		if (data != null) {		
			int speed = (int)(1.0f * data.getValue(Defines.SPEED0)/256.0f);
			json.put("Speed", speed); //速度
			json.put("TotalMileage", Misc.getTotalMileAge(data)); 
			json.put("SmallMileage", Misc.getFlightMileAge(data)/10); 
		}
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
			upload(mTimeStamp,genJsonFrame());
		}		
	}

}
