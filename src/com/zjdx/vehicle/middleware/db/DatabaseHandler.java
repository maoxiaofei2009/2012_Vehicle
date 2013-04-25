package com.zjdx.vehicle.middleware.db;


import java.util.Arrays;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class DatabaseHandler {
	private static final String TAG = "DatabaseHandler ";
	public static final int INVALIDE_INDEX = -1;
	private static final int[] ALL_FRAME_IDS = new int[] {			
		Defines.ID_BATTERY_INFO,		/** 0x101, 电池基本信息 **/
		Defines.ID_BATTERY_TOTAL_V_A,	/** 0x102, 电池总电压电流  **/
		Defines.ID_BATTERY_CELL_INFO,   /** 0x103 电池模块基本信息  **/
		Defines.ID_BATTERY_CELL_V_W_INFO,/** 0x104 模块电压、温度信息  **/
		Defines.ID_BATTERY_ERROR,		/** 0x105, 电池故障报警信息 **/
		
		Defines.ID_MOTOR_INFO,   		/** 0x201 电机运行状态  **/
		Defines.ID_MOTOR_RUNNING_INFO,	/** 0x202 电机运行参数  **/
		
		Defines.ID_BODYLIGHT_INFO,		/** 0x301, 车灯状态 **/
		Defines.ID_VEHICL_ERROR,		/** 0x302, 整车故障和指示 **/
		Defines.ID_ACCESSORY_STATUS,	/** 0x303, 辅助部件 **/
		Defines.ID_VEHICLE_RUNNING_INFO, /** 0x304, 车辆运行状态信息 **/
		Defines.ID_CONTROLLERS_INFO,	/** 0x305, 操纵件系统状态信息 **/
		Defines.ID_CHARGE_INFO,			/** 0x306, 充电系统状态信息 **/
		Defines.ID_SPEED_MILEAGE_INFO,	/** 0x307, 车速和里程信息 **/
		Defines.ID_AIR_CONDITION_INFO,  /** 0x308, 电动空调状态 **/
		
		Defines.ID_WARNING_INFO_FROM_DAS, /** 0x700, DAS 显示与预警输出 **/		
	};
	
	private static DatabaseHandler theInstance = null;	
	private CanFrameData[] dataArray = null;		
	private DatabaseHandler() {
		dataArray = new CanFrameData[ALL_FRAME_IDS.length];
		for (int i=0; i<ALL_FRAME_IDS.length; i++) {
			dataArray[i] = new CanFrameData(ALL_FRAME_IDS[i]);
		}
	}
	
	public static DatabaseHandler Instance() {
		if (theInstance == null) {
			theInstance = new DatabaseHandler();
		}
		return theInstance;
	}
	
	public static int size() {
		return ALL_FRAME_IDS.length;
	}
	
	public static final int[] getAllFrameIds() {
		return ALL_FRAME_IDS;
	}
	
	public static int getIndex(int frameId) {
		int index = Arrays.binarySearch(ALL_FRAME_IDS, frameId);
		index = (index >=0 && index < ALL_FRAME_IDS.length)? index : INVALIDE_INDEX;
		
		return index;
	}
	
	public boolean queryTable(int frameId, int[] values) {
//		LogUtils.LOGV(TAG, "queryTable " + frameId);	
		CanFrameData data = get(frameId);
		if (data == null)
			return false;
		
		data.getAllValues(values);
		return true;
	}
	
	public boolean updateTable(int frameId, int[] values) {
//		LogUtils.LOGV(TAG, "updateTable " + frameId);	
		CanFrameData data = get(frameId);
		if (data == null)
			return false;
		
		if (data.different(values)) {
			data.setAllValues(values);		
			return true;
		}
		return false;
	}	
		
	private CanFrameData get(int frameId) {
		CanFrameData data = null;
		int index = getIndex(frameId);
		if (index != INVALIDE_INDEX) {
			data = dataArray[index];			
		}
		
		return data;
	}
}
