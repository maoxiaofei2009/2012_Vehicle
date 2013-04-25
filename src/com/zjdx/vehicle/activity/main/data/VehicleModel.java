package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import android.content.Context;
import android.content.Intent;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.service.VehicleBinding;
import com.zjdx.vehicle.service.VehicleReceiver;
import com.zjdx.vehicle.service.VehicleService;

public class VehicleModel implements VehicleService.Callback, VehicleBinding.Callback  {
	private static final String TAG = "VehicleModel ";
		
	private static final int[] AUTOMATIC_FRAME_IDS = new int[] {
		Defines.ID_BATTERY_INFO,		/** 0x101, 电池基本信息 **/
		Defines.ID_BATTERY_TOTAL_V_A,	/** 0x102, 电池总电压电流  **/		
		Defines.ID_BATTERY_ERROR,		/** 0x105, 电池故障报警信息 **/		
		Defines.ID_BODYLIGHT_INFO,		/** 0x301, 车灯状态 **/
		Defines.ID_VEHICL_ERROR,		/** 0x302, 整车故障和指示 **/
		Defines.ID_ACCESSORY_STATUS,	/** 0x303, 辅助部件 **/
		Defines.ID_VEHICLE_RUNNING_INFO, /** 0x304, 车辆运行状态信息 **/
		Defines.ID_CONTROLLERS_INFO,	/** 0x305, 操纵件系统状态信息 **/
		Defines.ID_SPEED_MILEAGE_INFO,	/** 0x307, 车速和里程信息 **/
		Defines.ID_AIR_CONDITION_INFO,  /** 0x308, 电动空调状态 **/
		Defines.ID_WARNING_INFO_FROM_DAS, /** 0x700, DAS 显示与预警输出 **/
	};
	
	private Context mContext = null;	
	private IControl mController = null;
	private DatabaseHandler mDB = null;
	private VehicleService mService = null;	
	private CommandHelper mCommandHelper = null;
	private InformationHelper mInformationHelper = null;
	private HashMap<Integer, CanFrameData> mDataMap = null;
	private List<AutoDataModel> mAutoDataModelList = null;
	private boolean mPausing = true;
		
	private static VehicleModel theInstance = null;
	public static VehicleModel Instance() {
		if (theInstance == null) {
			theInstance = new VehicleModel();
		}
		return theInstance;
	}

	private VehicleModel() {		
	}
	
	public synchronized void onCreate(Context context) {	
		LogUtils.LOGD(TAG, "onCreate <----");
		mDB = DatabaseHandler.Instance();
		mDataMap = new HashMap<Integer, CanFrameData>();
		for (int i=0; i<AUTOMATIC_FRAME_IDS.length; i++) {
			int id = AUTOMATIC_FRAME_IDS[i];
			CanFrameData data = new CanFrameData(id);
			data.setDB(mDB);
			mDataMap.put(id, data);
		}
		
		mCommandHelper = new CommandHelper();
		mInformationHelper = new InformationHelper(mDataMap, mDB);
		mAutoDataModelList = new LinkedList<AutoDataModel>();
		mAutoDataModelList.add(new ADMAlertLeftRight(mDataMap));
		mAutoDataModelList.add(new ADMAlertDock(mDataMap));
		mAutoDataModelList.add(new ADMContentLeft(mDataMap));
		mAutoDataModelList.add(new ADMContentRight(mDataMap));		
		mAutoDataModelList.add(new ADMContentCenter(mDataMap));		
		mAutoDataModelList.add(new ADMAirCondition(mDataMap));		
		LogUtils.LOGD(TAG, "onCreate ---->");
	}
	
	public synchronized void onPause() {
		LogUtils.LOGD(TAG, "onPause <----");
		if (!mPausing) {
			mPausing = true;
			mContext = null;
			mController = null;
			setComponentsEnv();
		}
		LogUtils.LOGD(TAG, "onPause ---->");
	}
	
	public synchronized void onResume(Context context, IControl control) {
		LogUtils.LOGD(TAG, "onResume <----");
		mContext = context;
		mController = control;

		setComponentsEnv();		
		reflush();
		mPausing = false;
		LogUtils.LOGD(TAG, "onResume ---->");
	}	
	
	public synchronized void onDestroy() {
		if (mDataMap != null) {
			mDataMap.clear();
			mDataMap = null;
		}
		
		if (mAutoDataModelList != null) {
			mAutoDataModelList.clear();
			mAutoDataModelList = null;
		}
		
		mDB = null;		
		mService = null;
	}
	
	public void reflush() {
		LogUtils.LOGD(TAG, "reflush <----");
		if (mDataMap == null || mDataMap.isEmpty() || mAutoDataModelList == null)
			return;
		
		Set<Integer> identities = mDataMap.keySet();
		Iterator<Integer> iter = identities.iterator();
		while(iter.hasNext()) {
			Integer identity = iter.next();
			CanFrameData data = mDataMap.get(identity);
			if (data != null) {
				data.loadFromDB();			
				updateAutoDataModels(identity);
			}
		}
		LogUtils.LOGD(TAG, "reflush ---->");
	}
	
	private void setComponentsEnv() {
//		setCanFrameDataEnv();
		setHelpersEnv();
		setAutoDataModelEnv();
	}
	 
//	private void setCanFrameDataEnv() {
//		LogUtils.LOGD(TAG, "setCanFrameDataEnv <----");
//				
//		if (mDataMap == null || mDataMap.isEmpty()) {
//			return;
//		}
//		
//		Set<Integer> identities = mDataMap.keySet();
//		Iterator<Integer> iter = identities.iterator();
//		while(iter.hasNext()) {
//			Integer identity = iter.next();
//			CanFrameData data = mDataMap.get(identity);
//			if (data != null) {
//				data.setDB(mDB);
//			}
//		}
//		LogUtils.LOGD(TAG, "setCanFrameDataEnv ---->");
//	}
		
	private void setHelpersEnv() {
		if (mInformationHelper != null) {
			mInformationHelper.setEnv(mContext);
		}
	}
	
	private void setAutoDataModelEnv() {
		LogUtils.LOGD(TAG, "setAutoDataModelEnv <----");
		if (mAutoDataModelList == null || mAutoDataModelList.isEmpty())
			 return;
		
		ListIterator<AutoDataModel> iter =  mAutoDataModelList.listIterator();
		while(iter.hasNext()) {
			AutoDataModel model = iter.next();
			if (model != null) {
				model.setController(mController);
			}
		}
		
		LogUtils.LOGD(TAG, "setAutoDataModelEnv ---->");
	}
	
	private void updateAutoDataModels(int identity) {
//		LogUtils.LOGD(TAG, "updateAutoDataModels " +  identity + " <----");
		if (mAutoDataModelList == null || mAutoDataModelList.isEmpty())
			 return;
			 
		ListIterator<AutoDataModel> iter =  mAutoDataModelList.listIterator();
		while(iter.hasNext()) {
			AutoDataModel model = iter.next();
			if (model != null) {
				model.update(identity);
			}
		}
//		LogUtils.LOGD(TAG, "updateAutoDataModels " +  identity + " ---->");
	 }	 
	
	@Override
	public synchronized void onChangingServiceConnectity(VehicleService service) {
		LogUtils.LOGD(TAG, "onChangingServiceConnectity service= " + service);
		mService = service;
		
		if (mService != null) {
			mService.setCallback(this);
		}
	} 
	
	public synchronized void doSendCommand(int identity, int[] values) {
		 if (mService != null && values != null) {
			 mService.sendCommand(identity, values);
		 }
	}
	
	public void sendCommand(int commandId, Object obj) {
		if (mCommandHelper == null) 
			return;
		
		CanFrameData cmd = mCommandHelper.MakeCommand(commandId, obj);
		if (cmd == null)
			return;
		
		doSendCommand(cmd.getCanFrameId(), cmd.getAllValues());		
	}
	
	public Object getInformation(int InfromationId) {
		Object obj = null;
		if (mInformationHelper != null) {
			obj = mInformationHelper.getInformation(InfromationId);
		}
		
		return obj;
	}

	@Override
	public void onReceive(int count, int[] frameIdArray) {
//		LogUtils.LOGD(TAG, "onReceive mPausing " + mPausing + " <----");
		if (mPausing || count == 0 || frameIdArray == null || mDataMap == null)
			return;		
	
		
		for (int i=0; i<count; i++) {
			int frameId = frameIdArray[i];
			if (!mDataMap.containsKey(frameId)) {
				continue;
			}
			
			CanFrameData data = mDataMap.get(frameId);	
			if (data == null) {
				continue;
			}
			data.loadFromDB();			
			updateAutoDataModels(frameId);
		}
//		LogUtils.LOGD(TAG, "onReceive ---->");
	} 

}
