package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;

public class ADMAlertLeftRight extends AutoDataModel {
	private static final String TAG = "ADMAlertLeftRight ";

	public ADMAlertLeftRight(HashMap<Integer, CanFrameData> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}
	
	protected void initKeyMap() {
//		LogUtils.LOGD(TAG, "initKeyMap <----");
		if (mKeyMap == null)
			return;		
		//========================Alert Left / Right=================
		mKeyMap.put(Defines.VEHICLESTATE, IControl.EVENT_ALERT_READY_STOP); 
		mKeyMap.put(Defines.SYSTEMERROR,IControl.EVENT_ALERT_SYSTEM_ERROR); //系统故障报警
		mKeyMap.put(Defines.AIRBAGRESTRAINT,IControl.EVENT_ALERT_SAFTY_AIR_BAG);  //安全气囊指示灯
		mKeyMap.put(Defines.SAFETYBELTFAULT,IControl.EVENT_ALERT_SEAT_BELT); //安全带指示灯
		mKeyMap.put(Defines.BRAKESYSTEMFAULT,IControl.EVENT_ALERT_BRAKE_SYSTEM_ERROR); //制动系统故障
		mKeyMap.put(Defines.PARKING,IControl.EVENT_ALERT_PARKING_INDICATOR); //驻车指示
		mKeyMap.put(Defines.LEFTSTEERING,IControl.EVENT_ALERT_ROTATE_LEFT); //左转向 
			
		mKeyMap.put(Defines.MOTOROVERHEATING, IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_TEMPERATURE); ////电机过热报警
		mKeyMap.put(Defines.MOTOROVERSPEED,IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_SPEED); //电机超速报警
		mKeyMap.put(Defines.CHARGINGCONNECTION,IControl.EVENT_ALERT_CHARGING_CONNECT); //充电连接提示
		mKeyMap.put(Defines.CHARGING,IControl.EVENT_ALERT_CHARGING); //充电中状态提示
		mKeyMap.put(Defines.BATTERYOFF,IControl.EVENT_ALERT_POWER_BATTERY); //动力电池切断提示
		mKeyMap.put(Defines.INSULATIONSYSTEM,IControl.EVENT_ALERT_INSULATED_SYSTEM);  //绝缘系统检测报警
		mKeyMap.put(Defines.RIGHTSTEERING,IControl.EVENT_ALERT_ROTATE_RIGHT); //右转向	
		
//		LogUtils.LOGD(TAG, "initKeyMap ---->");
	}
	
	protected Object getModelValue(int canFrameKey) {
//		LogUtils.LOGD(TAG, "getModelValue " + canFrameKey);
		int groupId = Defines.getGroupId(canFrameKey);
		if (mDataMap == null || !mDataMap.containsKey(groupId))
			return null;
		
		CanFrameData data = mDataMap.get(groupId);
		if (data == null)
			return null;
		int v = data.getValue(canFrameKey);
		if (canFrameKey == Defines.VEHICLESTATE) {
			/**
			 * 车辆行驶状态：指示动力系统处于状态。
			 * 00=系统处于STOP 状态；01= 系统处于READY状态；10、11= 未定义
			 */
			v = (v == 0)? 1 : 0;
		}
		return Misc.Int2Boolean(v);
	} 
	
	protected void setValue(int key, Object obj) {
		if (mController != null && obj != null && obj instanceof Boolean) {
//			LogUtils.LOGD(TAG, "setValue " + key + " = " + obj);
			mController.upadteCarAlertStatus(key, obj);
		}
	}

}
