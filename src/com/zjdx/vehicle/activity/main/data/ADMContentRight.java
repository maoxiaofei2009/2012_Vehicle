package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;

public class ADMContentRight extends AutoDataModel {

	public ADMContentRight(HashMap<Integer, CanFrameData> map) {
		super( map);
		// TODO Auto-generated constructor stub
	}
	
	public void update(int identity) {
		if (mDataMap == null && mDataMap.isEmpty())
			return;
		
		switch (identity) {	
		case Defines.ID_BATTERY_INFO:
			onBatterInfo();
			break;
		case Defines.ID_BATTERY_TOTAL_V_A:
			onBatterTotalVA();
			break;
		case Defines.ID_BATTERY_ERROR:
			onBatterErrorFrame();
			break;
		case Defines.ID_VEHICL_ERROR:
			setBatteryError();
			break;
		case Defines.ID_SPEED_MILEAGE_INFO:
			setMileAge();
			break;
		case Defines.ID_CONTROLLERS_INFO:
			setCallStall();
			break;
		default:
			break;
		}
	}

	private void onBatterTotalVA() {
		CanFrameData data = mDataMap.get(Defines.ID_BATTERY_TOTAL_V_A);
		if (data == null) {
			return;
		}
		
		int volage = data.getValue(Defines.TOTAL_VOLAGE0); //动力电池的总电压
		int current = data.getValue(Defines.TOTAL_CURRENT0); //动力电池的总电压
		setValue(IControl.EVENT_SOC_VOLTAGE_CHANGE, Float.valueOf(0.02f * volage));
		setValue(IControl.EVENT_SOC_CURRENT_CHANGE, Float.valueOf(0.1f * current - 3200));
	}
	
	private void onBatterInfo() {
		CanFrameData data = mDataMap.get(Defines.ID_BATTERY_INFO);
		if (data == null) {
			return;
		}
		
		int value = data.getValue(Defines.CHARGESTATUS);  // 电池充电状态
		int soc = ((int) (0.4f * data.getValue(Defines.SOC))) % 101; // 蓄电池荷电状态 (SOC)	
		setValue(IControl.EVENT_SOC_BATTERY_STATUS_CHANGE, Misc.Int2Boolean(value));
		setValue(IControl.EVENT_SOC_BATTERT_VOLTAGE_CHANGE, Integer.valueOf(soc));
	}
	
	private void onBatterErrorFrame() {
		CanFrameData data = mDataMap.get(Defines.ID_BATTERY_ERROR);
		if (data == null) {
			return;
		}
		
		int value = data.getValue(Defines.LOWBATTERY);  //动力电池亏电
		setValue(IControl.EVENT_SOC_BATTERT_POWER_CHANGE, Misc.Int2Boolean(value));
	}
	
	private void setBatteryError() {
		CanFrameData data = mDataMap.get(Defines.ID_VEHICL_ERROR);
		if (data == null) {
			return;
		}
		
		int value = data.getValue(Defines.BATTERYERROR); //动力电池故障报警
		setValue(IControl.EVENT_SOC_BATTERT_SHORTAGE_CHANGE, Misc.Int2Boolean(value));
	}
	
	private void setMileAge() {
		CanFrameData data = mDataMap.get(Defines.ID_SPEED_MILEAGE_INFO);
		if (data == null) {
			return;
		}
		setValue(IControl.EVNET_SOC_TOTAL_MILEAGE_CHANGE, Integer.valueOf(Misc.getTotalMileAge(data)));
		setValue(IControl.EVNET_SOC_FLIGHT_MILEAGE_CHANGE, Integer.valueOf(Misc.getFlightMileAge(data)));
	}
	
	private void setCallStall() {			
		CanFrameData data = mDataMap.get(Defines.ID_CONTROLLERS_INFO);
		if (data == null) {
			return;
		}
		
		int stall = Misc.getGears(data.getValue(Defines.GEARS));		
		setValue(IControl.EVENT_ALERT_CAR_STALLS_RIGHT, Integer.valueOf(stall));
	}
	
	protected void setValue(int key, Object obj) {
		if (mController != null && obj != null) {
//			LogUtils.LOGD(TAG, "setValue " + key + " = " + obj);
			mController.updateCarContentRight(key, obj);
		}
	}	

}
