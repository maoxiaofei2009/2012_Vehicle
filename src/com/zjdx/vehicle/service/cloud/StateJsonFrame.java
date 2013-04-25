package com.zjdx.vehicle.service.cloud;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class StateJsonFrame extends AbsUploadJsonFrame {
	private static final int InfoType	= CloudHelper.InfoType_StateInfo;
	
	private static final int CHARGE 	= 0x00;
	private static final int STOP 		= 0x01;
	private static final int READY 		= 0x02;
	private static final int MAX_COUNT	= READY + 1;
	
	private boolean[] mState = null;
	private boolean[] mLastState = null;
	protected StateJsonFrame(DatabaseHandler db, UploadCallback cb) {
		super(db, cb);
		mState = new boolean[MAX_COUNT];
		mLastState = new boolean[MAX_COUNT]; 
	}

	@Override
	protected boolean onReceive(int count, int[] frameIdArray) {
		System.arraycopy(mState, 0, mLastState, 0, mState.length);
		Arrays.fill(mState, false);	
		for (int i=0; i<count; i++) {
			switch (frameIdArray[i]) {
			case Defines.ID_BATTERY_INFO:
				checkBatteryInfo();
				break;
			case Defines.ID_VEHICLE_RUNNING_INFO:
				checkVehicleRunningInfo();
				break;
			default:
				break;
			}
		}
		tryUploadStates();
		return true;
	}

	private void checkBatteryInfo() {
		CanFrameData data = getCanFrameData(Defines.ID_BATTERY_INFO);
		if (data == null)
			return;
		mState[CHARGE] = data.getValue(Defines.CHARGESTATUS) == 1;		
	}
	
	private void checkVehicleRunningInfo() {
		CanFrameData data = getCanFrameData(Defines.ID_VEHICLE_RUNNING_INFO);
		if (data == null)
			return;
		int state = data.getValue(Defines.VEHICLESTATE);
		mState[STOP] = state == 0;
		mState[READY] = state == 1;
	}
	
	private void tryUploadStates() {
		int time = CloudHelper.getTime();
		for (int i=0; i<MAX_COUNT; i++) {
			if (mState[i] && !mLastState[i]) {
				upload(time, genJsonFrame(i, time));
			}
		}
	}
	
	private String genJsonFrame(int state, int time) {
		String strJson = null;
		try {
			JSONObject json = new JSONObject();
			json.put(CloudHelper.VID, CloudHelper.getVID());
			json.put(CloudHelper.InfoType, InfoType);
			json.put("StatusAlert", state);
			json.put("Time", time);
			strJson = JSON2String(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strJson;
	}
}
