package com.zjdx.vehicle.activity.main.control;


public interface IControl {
	public static final String TAG = "MainControl ";
	//========================Alert Dock===============
	public static final int EVENT_LAMP_CONTROL = 0;
	public static final int EVENT_AIR_CONDITION_CONTROL = 1;
	public static final int EVENT_CAR_WINDOW_CONTROL = 2;
	public static final int EVENT_RAIN_WIPER_CONTROL = 3;

	public static final int EVENT_LAMP_FAR_LIGHT_CONTROL = 4;
	public static final int EVENT_LAMP_NEAR_LIGHT_CONTROL = 5;
	public static final int EVENT_LAMP_FRONT_FLOG_CONTROL = 6;
	public static final int EVENT_LAMP_BEHIND_FLOG_CONTROL = 7;
	public static final int EVENT_DOOR_FRONT_CONTROL = 8;
	public static final int EVENT_DOOR_BEHIND_CONTROL = 9;
	public static final int EVENT_HORN_CONTROL = 10;	
	
	//=========================Content Center ==============
	public static final int EVENT_RIGHT_WINDOW_CONTROL = 21;
	public static final int EVENT_LEFT_WINDOW_CONTROL = 22;
	public static final int EVENT_LAMP_ROTATE_RIGHT_CONTROL = 23;
	public static final int EVENT_LAMP_ROTATE_LEFT_CONTROL = 24;
	public static final int EVENT_DOOR_RIGHT_OPEN_CONTROL = 25;
	public static final int EVENT_DOOR_LEFT_OPEN_CONTROL = 26;
	public static final int EVENT_RAIN_WIPER_STAGE_CONTROL = 27;
	public static final int EVENT_CLOSE_CONTENT_CENTER = 28;
	
	//========================Air Condition==================
	public static final int EVENT_AIR_CONDITION_OPEN_CHANGE = 31;
	public static final int EVENT_AIR_CONDITION_TEMPERATURE_CHANGE = 32;	
	public static final int EVENT_AIR_CONDITION_MODEL_CHANGE = 33;
	public static final int EVENT_AIR_CONDITION_WIND_CHANGE = 34;	

	
	
	//========================Alert Left / Right=================
	public static final int EVENT_ALERT_READY_STOP = 41;
	public static final int EVENT_ALERT_SYSTEM_ERROR = 42;
	public static final int EVENT_ALERT_SAFTY_AIR_BAG = 43;
	public static final int EVENT_ALERT_SEAT_BELT = 44;
	public static final int EVENT_ALERT_BRAKE_SYSTEM_ERROR = 45;
	public static final int EVENT_ALERT_PARKING_INDICATOR = 46;
	public static final int EVENT_ALERT_ROTATE_LEFT = 47;
	public static final int EVENT_ALERT_CAR_STALLS_LEFT = 48;
	
	public static final int EVENT_ALERT_ELECTRIC_MACHINE_OVER_TEMPERATURE = 51;
	public static final int EVENT_ALERT_ELECTRIC_MACHINE_OVER_SPEED = 52;
	public static final int EVENT_ALERT_CHARGING_CONNECT = 53;
	public static final int EVENT_ALERT_CHARGING = 54;
	public static final int EVENT_ALERT_POWER_BATTERY = 55;
	public static final int EVENT_ALERT_INSULATED_SYSTEM = 56;
	public static final int EVENT_ALERT_ROTATE_RIGHT = 57;
	public static final int EVENT_ALERT_CAR_STALLS_RIGHT = 58;
	
	//======================Content Left===============================
	public static final int EVENT_SECURITY_ALERT_SIDE_WALK = 61;
	public static final int EVENT_SECURITY_ALERT_PEOPLE = 62;	
	public static final int EVENT_SECURITY_ALERT_LEFT_LANE = 63;	
	public static final int EVENT_SECURITY_ALERT_RIGHT_LANE = 64;	
	public static final int EVENT_SECURITY_ALERT_FRONT_CAR = 65;	
	public static final int EVENT_SECURITY_ALERT_FRONT_CAR_DISTANCE = 66;	
	public static final int EVENT_SECURITY_ALERT_CAR_SPEED = 67;	
	
	//======================Content Right==============================
	public static final int EVENT_SOC_BATTERT_VOLTAGE_CHANGE = 71;
	public static final int EVENT_SOC_BATTERY_STATUS_CHANGE = 72;	
	public static final int EVENT_SOC_BATTERT_POWER_CHANGE = 73;	
	public static final int EVENT_SOC_BATTERT_SHORTAGE_CHANGE = 74;	
	public static final int EVENT_SOC_CURRENT_CHANGE = 75;
	public static final int EVENT_SOC_VOLTAGE_CHANGE = 76;
	public static final int EVNET_SOC_FLIGHT_MILEAGE_CHANGE = 77;
	public static final int EVNET_SOC_TOTAL_MILEAGE_CHANGE = 78;	
	
	
	//=================================================
	//Message
	public static final int SHOW_INFO_MESSAGE_BASE_INFO = 101;
	public static final int SHOW_INFO_MESSAGE_CAR_STATUS = 102;
	public static final int SHOW_INFO_MESSAGE_OTHER_INFO = 103;
	
	//Battery
	public static final int SHOW_INFO_BATTERY_BASE_INFO = 106;
	public static final int SHOW_INFO_BATTERY_MODEL_BASE_INFO = 107;
	public static final int SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS = 108;
	public static final int SHOW_INFO_BATTERY_FAULT = 109;
	
	//MOTOR
	public static final int SHOW_INFO_MOTOR_MAIN = 111;
	//Air Condition
	public static final int SHOW_INFO_AIR_CONDITION_MAIN = 112;
	//Fault
	public static final int SHOW_INFO_FAULT = 121;
	//Diagnosis
	public static final int SHOW_INFO_DIAGNOSIS = 122;
	//Maintenance
	public static final int SHOW_INFO_MAINTENANCE = 123;
	//Setting
	public static final int SHOW_INFO_SETTING = 134;
	
	//========================================================
	public void actionCarControlEvent(int event, Object obj);
	public void actionCarAirCondition(int event, Object obj);
	public void actionCarDockEvent(int event, Object obj);
	public void actionCarSettingEvent(int event, Object obj);
	public void showCarInfomation(int event, Object obj);
		
	public void upadteCarAlertStatus(int event, Object obj);
	public void updateCarContentLeft(int event, Object obj);
	public void updateCarContentRight(int event, Object obj);
	public void updateCarContentCenter(int event, Object obj);
	public void updateCarAirCondition(int event, Object obj);
	public void updateCarAlertDock(int event, Object obj);
	public void updateCarSetting(int event, Object obj);
	
	public static final int EVENT_GET_CAR_CENTER_CONTENT_VISIBLE = 1;
	public Object getCarContorlStatus(int event);
}