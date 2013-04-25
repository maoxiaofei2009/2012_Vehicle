package com.zjdx.vehicle.activity.main.control;

import android.content.Context;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.common.CarStatusHelper;
import com.zjdx.vehicle.activity.main.data.VehicleModel;


public class MainControl implements IControl{
	private static final String TAG = "MainControl ";
	private ActionArea mActionArea;
	private ContentArea mContentArea;
	
	public MainControl(Context context) {
    	mActionArea = new ActionArea(context, this);
    	mContentArea = new ContentArea(context, this);
    	LogUtils.LOGD(TAG, "mActionArea = " + mActionArea);
	}

	@Override
	public void actionCarControlEvent(int event, Object obj) {
		LogUtils.LOGD(TAG, "actionCarControlEvent event = " + event);
		mContentArea.resetColseContentCenterTimer(true);
		switch (event) {
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:
			VehicleModel.Instance().sendCommand(event, obj);
			break;
			
		case IControl.EVENT_RIGHT_WINDOW_CONTROL:
		case IControl.EVENT_LEFT_WINDOW_CONTROL:
		case IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL:
		case IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL:
		case IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL:
		case IControl.EVENT_DOOR_LEFT_OPEN_CONTROL:
		case IControl.EVENT_RAIN_WIPER_STAGE_CONTROL:
			VehicleModel.Instance().sendCommand(event, obj);
			break;
		case IControl.EVENT_CLOSE_CONTENT_CENTER:
			mContentArea.startCloseAnim();
			break;
		default:
			break;
		}
	}


	@Override
	public void actionCarDockEvent(int event, Object obj) {
		LogUtils.LOGD(TAG, "actionCarDockEvent event = " + event);
		mContentArea.resetColseContentCenterTimer(true);
		switch (event) {
		case IControl.EVENT_LAMP_CONTROL:
			mContentArea.updateCarControlStatus(event);
			mContentArea.startOpenAnim();
			break;
		case IControl.EVENT_AIR_CONDITION_CONTROL:
		case IControl.EVENT_CAR_WINDOW_CONTROL:
		case IControl.EVENT_RAIN_WIPER_CONTROL:
			mContentArea.updateCarControlStatus(event);
			if (!mContentArea.isContentAreaCenterVisible()){
				mContentArea.startOpenAnim();
			}
			break;
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:
		case IControl.EVENT_DOOR_FRONT_CONTROL:
		case IControl.EVENT_DOOR_BEHIND_CONTROL:
		case IControl.EVENT_HORN_CONTROL:
			VehicleModel.Instance().sendCommand(event, obj);
			break;
		default:
			break;
		}
	}

	@Override
	public void actionCarSettingEvent(int event, Object obj) {
	}

	@Override
	public void actionCarAirCondition(int event, Object obj) {
		LogUtils.LOGD(TAG, "actionCarAirCondition event = " + event);
		mContentArea.resetColseContentCenterTimer(true);
		switch (event) {
		case IControl.EVENT_AIR_CONDITION_OPEN_CHANGE:
		case IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE:
		case IControl.EVENT_AIR_CONDITION_MODEL_CHANGE:
		case IControl.EVENT_AIR_CONDITION_WIND_CHANGE:
			//mContentArea.updateAirConditionStatus(event);
			VehicleModel.Instance().sendCommand(event, obj);
			break;
		default:
			break;
		}
	}

	@Override
	public void showCarInfomation(int event, Object obj) {
		LogUtils.LOGD(TAG, "showCarInfomation event = " + event);			
		switch (event) {
		case IControl.SHOW_INFO_MESSAGE_BASE_INFO:
		case IControl.SHOW_INFO_MESSAGE_CAR_STATUS:
		case IControl.SHOW_INFO_MESSAGE_OTHER_INFO:
			
		case IControl.SHOW_INFO_BATTERY_BASE_INFO:
		case IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO:
		case IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS:
		case IControl.SHOW_INFO_BATTERY_FAULT:
			
		case IControl.SHOW_INFO_MOTOR_MAIN:
		case IControl.SHOW_INFO_AIR_CONDITION_MAIN:
		case IControl.SHOW_INFO_FAULT:
		case IControl.SHOW_INFO_DIAGNOSIS:	
		case IControl.SHOW_INFO_MAINTENANCE:
		case IControl.SHOW_INFO_SETTING:
			obj = VehicleModel.Instance().getInformation(event);
			if (!mContentArea.isContentAreaCenterVisible()){
				mContentArea.startOpenAnim();
			}
			mContentArea.showCarInfomation(event, obj);
			mContentArea.resetColseContentCenterTimer(false);
			break;
		default:			
			break;
		}
		
	}
	
	
	
	//========================================================================
	@Override
	public void upadteCarAlertStatus(int event, Object obj) {
		LogUtils.LOGD(TAG, "upadteCarAlertStatus event = " + event + ", obj = " + (Boolean) obj);
		switch (event) {
		case IControl.EVENT_ALERT_READY_STOP:
		case IControl.EVENT_ALERT_SYSTEM_ERROR:
		case IControl.EVENT_ALERT_SAFTY_AIR_BAG:
		case IControl.EVENT_ALERT_SEAT_BELT:
		case IControl.EVENT_ALERT_BRAKE_SYSTEM_ERROR:
		case IControl.EVENT_ALERT_PARKING_INDICATOR:
		case IControl.EVENT_ALERT_ROTATE_LEFT:
			mActionArea.updateAlertLeft(event, (Boolean) obj);
			break;
			
		case IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_TEMPERATURE:
		case IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_SPEED:
		case IControl.EVENT_ALERT_CHARGING_CONNECT:
		case IControl.EVENT_ALERT_CHARGING:
		case IControl.EVENT_ALERT_POWER_BATTERY:
		case IControl.EVENT_ALERT_INSULATED_SYSTEM:
		case IControl.EVENT_ALERT_ROTATE_RIGHT:
			mActionArea.updateAlertRight(event, (Boolean) obj);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void updateCarContentLeft(int event, Object obj) {
		LogUtils.LOGD(TAG, "updateCarContentLeft event = " + event);
		switch (event) {
		case IControl.EVENT_SECURITY_ALERT_SIDE_WALK:
		case IControl.EVENT_SECURITY_ALERT_PEOPLE:
		case IControl.EVENT_SECURITY_ALERT_LEFT_LANE:
		case IControl.EVENT_SECURITY_ALERT_RIGHT_LANE:
		case IControl.EVENT_SECURITY_ALERT_FRONT_CAR:
		case IControl.EVENT_SECURITY_ALERT_FRONT_CAR_DISTANCE:
		case IControl.EVENT_SECURITY_ALERT_CAR_SPEED:
		case IControl.EVENT_ALERT_CAR_STALLS_LEFT:
			mContentArea.updateContentLeft(event, obj);
			break;
		default:
			break;
		}
	}


	@Override
	public void updateCarContentRight(int event, Object obj) {
		LogUtils.LOGD(TAG, "updateCarContentRight event = " + event);
		switch (event) {
		case IControl.EVENT_SOC_BATTERT_VOLTAGE_CHANGE:
		case IControl.EVENT_SOC_BATTERY_STATUS_CHANGE:
		case IControl.EVENT_SOC_BATTERT_POWER_CHANGE:
		case IControl.EVENT_SOC_BATTERT_SHORTAGE_CHANGE:
		case IControl.EVENT_SOC_CURRENT_CHANGE:
		case IControl.EVENT_SOC_VOLTAGE_CHANGE:
		case IControl.EVNET_SOC_FLIGHT_MILEAGE_CHANGE:
		case IControl.EVNET_SOC_TOTAL_MILEAGE_CHANGE:
		case IControl.EVENT_ALERT_CAR_STALLS_RIGHT:
			mContentArea.updateContentRight(event, obj);
			break;
		default:
			break;
		}
	}


	@Override
	public void updateCarContentCenter(int event, Object obj) {
		LogUtils.LOGD(TAG, "updateCarContentCenter event = " + event);
		switch (event) {
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			mContentArea.updateCarControlStatus(event);
			break;
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampNearlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			mContentArea.updateCarControlStatus(event);
			break;
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFrontflogStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			mContentArea.updateCarControlStatus(event);
			break;
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampBehindflogStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			mContentArea.updateCarControlStatus(event);
			break;
			
		//=================================	
		case IControl.EVENT_RIGHT_WINDOW_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setRightWindowPercent((Float)obj);
			}
			mContentArea.updateCarControlStatus(event);
			break;
		case IControl.EVENT_LEFT_WINDOW_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLeftWindowPercent((Float)obj);
			}
			mContentArea.updateCarControlStatus(event);
			break;
			
		//=========================
		case IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL:{
			if (obj != null){
				CarStatusHelper.getInstance().setCarLampRotateRightStatus((Boolean)obj);
			}
			mContentArea.updateCarControlStatus(event);
			upadteCarAlertStatus(IControl.EVENT_ALERT_ROTATE_RIGHT, 
					CarStatusHelper.getInstance().getCarLampRotateRightStatus());
			break;
		}
		case IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL:{
			if (obj != null){
				CarStatusHelper.getInstance().setCarLampRotateLeftStatus((Boolean)obj);
			}
			mContentArea.updateCarControlStatus(event);
			upadteCarAlertStatus(IControl.EVENT_ALERT_ROTATE_LEFT, 
					CarStatusHelper.getInstance().getCarLampRotateLeftStatus());
			break;
		}
		case IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setCarFrontDoorRightStatus((Boolean)obj);
			}
			mContentArea.updateCarControlStatus(event);
			break;
		case IControl.EVENT_DOOR_LEFT_OPEN_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setCarFrontDoorLeftStatus((Boolean)obj);
			}
			mContentArea.updateCarControlStatus(event);
			break;
		case IControl.EVENT_RAIN_WIPER_STAGE_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setCarRainWiperStage((Integer)obj);
			}
			mContentArea.updateCarControlStatus(event);
			break;
		default:
			break;
		}
	}


	@Override
	public void updateCarAirCondition(int event, Object obj) {
		LogUtils.LOGD(TAG, "updateCarAirCondition event = " + event);
		switch (event) {
		case IControl.EVENT_AIR_CONDITION_MODEL_CHANGE:		
			if (obj != null){
				CarStatusHelper.getInstance().setAirConditionModel((Integer)obj);
			}
			mContentArea.updateAirConditionStatus(event);
			break;
		case IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE:
			if (obj != null){
				CarStatusHelper.getInstance().setAirConditionTemperature((Integer)obj);
			}
			mContentArea.updateAirConditionStatus(event);
			break;
		case IControl.EVENT_AIR_CONDITION_OPEN_CHANGE:
			if (obj != null){
				CarStatusHelper.getInstance().setAirConditionStatus((Boolean)obj);
			}
			mContentArea.updateAirConditionStatus(event);
			break;
		case IControl.EVENT_AIR_CONDITION_WIND_CHANGE:
			if (obj != null){
				CarStatusHelper.getInstance().setAirConditionWind((Integer)obj);
			}
			mContentArea.updateAirConditionStatus(event);
			break;
		default:
			break;
		}
	}


	@Override
	public void updateCarAlertDock(int event, Object obj) {
		LogUtils.LOGD(TAG, "updateCarAlertDock event = " + event);
		switch (event) {
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			break;
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampNearlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			break;
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			break;
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			break;
		case IControl.EVENT_DOOR_FRONT_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			break;
		case IControl.EVENT_DOOR_BEHIND_CONTROL:
			if (obj != null){
				CarStatusHelper.getInstance().setLampFarlightStatus((Boolean)obj);
			}
			mActionArea.updateDockStatus(event);
			break;
		case IControl.EVENT_HORN_CONTROL:
			break;
		default:
			break;
		}
	}

	@Override
	public void updateCarSetting(int event, Object obj) {
		LogUtils.LOGD(TAG, "updateCarAlertDock event = " + event);
		switch (event) {
		case IControl.SHOW_INFO_SETTING:
			obj = VehicleModel.Instance().getInformation(event);
			mContentArea.showCarInfomation(event, obj);
		break;
		default:
			break;
		}
		
	}
	
	@Override
	public Object getCarContorlStatus(int event) {
		switch(event){
		case IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE:
			return mContentArea.isContentAreaCenterVisible();
		}
		return null;
	}
}