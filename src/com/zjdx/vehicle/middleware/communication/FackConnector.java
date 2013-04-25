package com.zjdx.vehicle.middleware.communication;


import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.model.Defines;

import android.content.Context;

public class FackConnector implements Connector {
	private static final String TAG = "FackConnector ";
	
	public FackConnector(Context context) {
		
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean read(MyDataPackage pkg) {
		if (pkg == null || pkg.content == null)
			return false;
		
		boolean res = false;
		pkg.count = 0;
		if (TestReadingFakeData(pkg.content)) {
			pkg.count = 1;
			res = true;
		}
		return res;
	}

	@Override
	public boolean write(byte[] frame) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	private static int sN = 0;	
	private static int sIndex = 0;
//	private static Integer[] sFrameIdArray = ItemMgr.getAllFrameId();
	
	private static Integer[] sFrameIdArray = {		
		Defines.ID_WARNING_INFO_FROM_DAS, /** 0x700, DAS 显示与预警输出 **/
		Defines.ID_VEHICL_INFO_TO_DAS,
		
		Defines.ID_AIR_CONDITION_INFO,  /** 0x308, 电动空调状态 **/
		Defines.ID_SPEED_MILEAGE_INFO,	/** 0x307, 车速和里程信息 **/
		Defines.ID_CHARGE_INFO,			/** 0x306, 充电系统状态信息 **/
		Defines.ID_CONTROLLERS_INFO,	/** 0x305, 操纵件系统状态信息 **/
		Defines.ID_VEHICLE_RUNNING_INFO, /** 0x304, 车辆运行状态信息 **/
		Defines.ID_ACCESSORY_STATUS,	/** 0x303, 辅助部件 **/
		Defines.ID_VEHICL_ERROR,		/** 0x302, 整车故障和指示 **/
		Defines.ID_BODYLIGHT_INFO,		/** 0x301, 车灯状态 **/
		
		Defines.ID_MOTOR_RUNNING_INFO,	/** 0x202 电机运行参数  **/
		Defines.ID_MOTOR_INFO,   		/** 0x201 电机运行状态  **/
		
		Defines.ID_BATTERY_ERROR,		/** 0x105, 电池故障报警信息 **/
		Defines.ID_BATTERY_CELL_V_W_INFO,/** 0x104 模块电压、温度信息  **/
		Defines.ID_BATTERY_CELL_INFO,   /** 0x103 电池模块基本信息  **/
		Defines.ID_BATTERY_TOTAL_V_A,	/** 0x102, 电池总电压电流  **/
		Defines.ID_BATTERY_INFO,		/** 0x101, 电池基本信息 **/
	};
	
	private static byte[] sLastFrame = new byte[Defines.CANFRAME_LENGTH];			
	private boolean TestReadingFakeData(byte[] frame) {
		boolean res = (sN++ % (sFrameIdArray.length + 1)) != 0;
		if (res) {
			int frameId = sFrameIdArray[sIndex++ % sFrameIdArray.length];
			System.arraycopy(Parser.getFackFrame(frameId, sLastFrame), 0, frame, 0, Defines.CANFRAME_LENGTH);
			System.arraycopy(frame, 0, sLastFrame, 0, Defines.CANFRAME_LENGTH);
			LogUtils.LOGD(TAG, "read ok, frameId: " + frameId);
		}
		else {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}

	@Override
	public String read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean write(String data) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void checkState() {
		// TODO Auto-generated method stub
		
	}
	
	

}
