package com.zjdx.vehicle.activity.main.data;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.zjdx.vehicle.activity.main.common.CarInfoConstant;
import com.zjdx.vehicle.activity.main.common.FaultConstant;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.main.ui.bottom.DiagnosisListInfo;
import com.zjdx.vehicle.activity.main.ui.bottom.FaultListInfo;
import com.zjdx.vehicle.activity.main.ui.bottom.TwoCloumnInfo;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;
import com.zjdx.vehicle.middleware.model.WarningHelpler;
import com.zjdx.vehicle.middleware.model.WarningHelpler.WarningItem;
import com.zjdx.vehicle.middleware.model.WarningHelpler.WarningItemGroup;

public class InformationHelper {
	private HashMap<Integer, CanFrameData> mDataMap = null;
	private Context mContext = null;
	private DatabaseHandler mDB = null;
	private CanFrameData mCacheData = null;
	
	public InformationHelper(HashMap<Integer, CanFrameData> map, DatabaseHandler db) {		
		mDataMap = map;		
		mDB = db;
	}
	
	public synchronized void setEnv(Context context) {
		mContext = context;		
	}
	
	public Object getInformation(int InfromationId){
		if (mContext == null || mDB == null)
			return null;
		
		Object obj = null;
		switch (InfromationId) {
		case IControl.SHOW_INFO_MESSAGE_BASE_INFO:
			obj = getMsgBasicInfo();
			break;
		case IControl.SHOW_INFO_MESSAGE_CAR_STATUS:
			obj = getMsgCarStatus();
			break;
		case IControl.SHOW_INFO_MESSAGE_OTHER_INFO:
			obj = getMsgOtherInfo();
			break;
			
		case IControl.SHOW_INFO_BATTERY_BASE_INFO:
			obj = getBatteryBasicInfo();
			break;
			
		case IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO:
			obj = getBatteryModelInfo();
			break;
			
		case IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS:
			obj = getBatteryChargingSystem();
			break;
			
		case IControl.SHOW_INFO_BATTERY_FAULT:
			obj = getBatteryFault();
			break;
			
		case IControl.SHOW_INFO_MOTOR_MAIN:
			obj = getMotoMainInfo();
			break;
			
		case IControl.SHOW_INFO_AIR_CONDITION_MAIN:
			obj = getAirConditionInfo();
			break;

		case IControl.SHOW_INFO_FAULT:
			obj = getFaultList();
			break;
		case IControl.SHOW_INFO_DIAGNOSIS:
			obj = getDiagnosis();
			break;
		case IControl.SHOW_INFO_MAINTENANCE:
			obj = getMaintenance();
			break;
		case IControl.SHOW_INFO_SETTING:
			break;
		default:
			break;
		}
		return obj;
	}
	
	private CanFrameData getFrameData(int frameId) {
		CanFrameData data = null;
		if (mDataMap.containsKey(frameId)) {
			data = mDataMap.get(frameId);
		}
		else if (mCacheData != null && mCacheData.getCanFrameId() == frameId) {
			data = mCacheData;
		}
		else {
			data = new CanFrameData(frameId);
			data.setDB(mDB);
			mCacheData = data;
		}
		
		return data;
	}
	
	private ArrayList<TwoCloumnInfo> getArrayList(String[] name, String[] values) {
		ArrayList<TwoCloumnInfo> list = new ArrayList<TwoCloumnInfo>();
		for (int i=0; i<name.length; i++) {
			list.add(new TwoCloumnInfo(name[i], values[i]));
		}
		return list;
	}	
	
	private ArrayList<TwoCloumnInfo> addArrayList(ArrayList<TwoCloumnInfo> list, String[] name, String[] values) {
		if (list != null) {
			for (int i=0; i<name.length; i++) {
				list.add(new TwoCloumnInfo(name[i], values[i]));
			}
		}
		return list;
	}
	
// IControl.SHOW_INFO_MESSAGE_BASE_INFO:
	private ArrayList<TwoCloumnInfo> getMsgBasicInfo() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.MESSAGE_TEXT_BASE_INFO_ITEM;
		String[] values = new String[names.length];
		// "车速",, ,
		if ((data = getFrameData(Defines.ID_SPEED_MILEAGE_INFO)) != null) {	
			int speed = (int) (data.getValue(Defines.SPEED0)/256.0f);
			values[index++] = new String(""+speed + " Km/h");
		}
		
		//  "电量(SOC)"	
		if ((data = getFrameData(Defines.ID_BATTERY_INFO)) != null) {	
			int soc = ((int) (0.4f * data.getValue(Defines.SOC))) % 101;
			values[index++] = new String(""+soc + "%");
		}
			
		// "档位"
		if ((data = getFrameData(Defines.ID_CONTROLLERS_INFO)) != null) {	
			int gears = data.getValue(Defines.GEARS);
			values[index++] = Misc.getHumanGreats(gears);
		}
		
//		 "续驶里程", 
//		"总里程", 		
		if ((data = getFrameData(Defines.ID_SPEED_MILEAGE_INFO)) != null) {	
			int v =  Misc.getFlightMileAge(data);
			values[index++] = new String("" + v/10 + "." + v%10 + " Km");
			values[index++] = new String("" + Misc.getTotalMileAge(data) + " Km");	
		}
		
//		"系统互锁", 
//		"行驶状态"
		if ((data = getFrameData(Defines.ID_VEHICLE_RUNNING_INFO)) != null) {	
			values[index++] = Misc.getHumanSystemInterLock(data.getValue(Defines.SYSTEMINTERLOCK));
			values[index++] = Misc.getHumanVehicleState(data.getValue(Defines.VEHICLESTATE));	
		}		
		
		return getArrayList(names, values);
	}
	
// IControl.SHOW_INFO_MESSAGE_CAR_STATUS:
	private ArrayList<TwoCloumnInfo> getMsgCarStatus() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.MESSAGE_TEXT_CAR_STATUS_LEFT;
		String[] values = new String[names.length];
		
		if ((data = getFrameData(Defines.ID_BODYLIGHT_INFO)) != null) {	
//			驻车指示	
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.PARKING));
//			后雾灯
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.REARFOGLIGHT));
//			前雾灯
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.HEADFOGLIGHT));
//			远光灯
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.HIGHBEAM));
//			近光灯
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.LOWBEAM));
//			右转向
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.RIGHTSTEERING));
//			左转向
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.LEFTSTEERING));
		}

		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.MESSAGE_TEXT_CAR_STATUS_RIGHT;
		values = new String[names.length];
		if ((data = getFrameData(Defines.ID_ACCESSORY_STATUS)) != null) {
//		前舱门		
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.ENGINEHOOD));
//		后舱门
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.TRUNK));
//		右门
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTRIGHTDOOR));
//		左门
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTLEFTDOOR));
//		右门车窗
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTRIGHTWINDOW));
//		左门车窗
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTLEFTWINDOW));
//		雨刮	
		values[index++] = Misc.getHumanWiperState(data.getValue(Defines.Wiper_Status));
		}
		
		return addArrayList(list, names, values);
	}
	
// IControl.SHOW_INFO_MESSAGE_OTHER_INFO:
	private ArrayList<TwoCloumnInfo> getMsgOtherInfo() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.MESSAGE_TEXT_OTHER_INFO_LEFT;
		String[] values = new String[names.length];
		
		if ((data = getFrameData(Defines.ID_ACCESSORY_STATUS)) != null) {
//		"安全带指示灯",
			values[index++] = Misc.getHumanState2(data.getValue(Defines.SAFETYBELTFAULT));	
//		"安全气囊指示灯",
			values[index++] = Misc.getHumanState2(data.getValue(Defines.AIRBAGRESTRAINT));
//		"制动系统故障", 
			values[index++] = Misc.getHumanState2(data.getValue(Defines.BRAKESYSTEMFAULT));
//		"DC/DC 状态"	
			values[index++] = Misc.getHumanState3(data.getValue(Defines.DCSTATUS));	
		}
		
		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.MESSAGE_TEXT_OTHER_INFO_RIGHT;
		values = new String[names.length];
		
		if (data != null) {
//		"电动气泵状态",
			values[index++] = Misc.getHumanState3(data.getValue(Defines.ELECTRICPUMP));
//		"电动转向状态", 
			values[index++] = Misc.getHumanState3(data.getValue(Defines.ELECTRICSTEER));
		}
		
		if ((data = getFrameData(Defines.ID_CONTROLLERS_INFO)) != null) {		
//		"钥匙开关状态",
			values[index++] = Misc.getHumanKeyState(data.getValue(Defines.KEYSTATUS));
//		"油门大小开合度"
			int dgree = ((int) (0.4f * data.getValue(Defines.ACCELERATORPEDALDEGREE))) % 101;
			values[index++] = new String(""+dgree + "%");
		}
		return addArrayList(list, names, values);
	}
	
// IControl.SHOW_INFO_BATTERY_BASE_INFO:
	private ArrayList<TwoCloumnInfo> getBatteryBasicInfo() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.BATTERY_BASE_INFO_ITEM;
		String[] values = new String[names.length];
		
		if ((data = getFrameData(Defines.ID_BATTERY_INFO)) != null) {
//			"蓄电池组编号", 
			values[index++] = new String(""+ data.getValue(Defines.NUM));			
//			"蓄电池系统模块总数量", 
			values[index++] = new String(""+ data.getValue(Defines.COUNTER));
//			"电池充电状态", 
			values[index++] = Misc.getHumanBatteryState(data.getValue(Defines.CHARGESTATUS));
//			"蓄电池荷电状态(SOC)",
			int soc = ((int) (0.4f * data.getValue(Defines.SOC))) % 101;
			values[index++] = new String(""+soc + "%");
//			"蓄电池充电次数", 	
			values[index++] = new String("" + data.getValue(Defines.CHARGETIME0) + " 次");
		}
		if ((data = getFrameData(Defines.ID_BATTERY_TOTAL_V_A)) != null) {
			int volage = (int)(0.02f* data.getValue(Defines.TOTAL_VOLAGE0)); //动力电池的总电压
			int current =(int)(0.1f* data.getValue(Defines.TOTAL_CURRENT0) - 3200); //动力电池的总电压
			values[index++] = new String("" + volage + " V");
			values[index++] = new String("" + current + " A");
		}	
		
		return getArrayList(names, values);
	}
// IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO:
	private ArrayList<TwoCloumnInfo> getBatteryModelInfo() {
		int index = 0;
		CanFrameData data = null;
		CanFrameData data1 = getFrameData(Defines.ID_BATTERY_CELL_INFO);
		CanFrameData data2 = getFrameData(Defines.ID_BATTERY_CELL_V_W_INFO);
		String[] names = CarInfoConstant.BATTERY_MODEL_BASE_INFO_ITEM_LEFT;
		String[] values = new String[names.length];
		if (data1 != null) {
			data = data1;
//		"模块号",
			values[index++] = new String("" + data.getValue(Defines.CELLINFO_NUM));
//		"单体电池数", 
			values[index++] = new String("" + data.getValue(Defines.CELLINFO_COUNTER) + " 个");
//		"温度采样点数", 
			values[index++] = new String("" + data.getValue(Defines.SAMPLECOUNTER) + " 个");
		}
		if (data2 != null) {
			data = data2;
//		"最低温度",
			int t1 = data.getValue(Defines.TMIN) - 40;
			values[index++] = new String("" + t1 + " ℃");
//		"最高温度"
			int t2 = data.getValue(Defines.TMAX) - 40;
			values[index++] = new String("" + t2 + " ℃");
		}
		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.BATTERY_MODEL_BASE_INFO_ITEM_RIGHT;
		values = new String[names.length];
		if (data1 != null) {
			data = data1;
//		"模块SOC",
			int soc = (int)(0.4f * data.getValue(Defines.CELLINFO_SOC)) % 101;
			values[index++] = new String("" + soc + "%");
		}
		if (data2 != null) {
			data = data2;
//		"最低电压", 
			int v1 = (int)(0.001 * data.getValue(Defines.VMIN0));
			values[index++] = new String("" + v1 + " V");
//		"最高电压",
			int v2 = (int)(0.001 * data.getValue(Defines.VMAX0));
			values[index++] = new String("" + v2 + " V");
		}
		if (data1 != null) {
			data = data1;
//		"模块总电压",
			int v = (int)(0.02* data.getValue(Defines.CELLINFO_VOLAGE0));
			values[index++] = new String("" + v + " V");
//		"模块总电流"
			int a = (int)(0.01* data.getValue(Defines.CELLINFO_CURRENT0) - 3200);
			values[index++] = new String("" + a + " A");
		}
		
		return addArrayList(list, names, values);
	}
// IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS:
	private ArrayList<TwoCloumnInfo> getBatteryChargingSystem() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.BATTERY_CHARGING_SYSTEM_STATUS_ITEM;
		String[] values = new String[names.length];
		if ((data = getFrameData(Defines.ID_CHARGE_INFO)) != null) {
//			"充电接口状态", 
			values[index++] = Misc.getHumanChargeInterface(data.getValue(Defines.CHARGEINTERFACE));
//			"通信状态", 
			values[index++] = Misc.getHumanState2(data.getValue(Defines.COMFAULT));
//			"输入交流电压", 
			values[index++] = Misc.getHumanState2(data.getValue(Defines.AVFAULT));
//			"充电机过热",
			values[index++] = Misc.getHumanState2(data.getValue(Defines.OVERHEATING));
//			"充电电压", 
			int v = (int)(0.1f * data.getValue(Defines.CHARGEVOLAGE0));
			values[index++] = new String("" + v + " V");	
//			"充电电流"
			int a = (int)(0.01f * data.getValue(Defines.CHARGECURRENT0));
			values[index++] = new String("" + a + " A");
		}
		return getArrayList(names, values);
	}
// IControl.SHOW_INFO_BATTERY_FAULT:
	private ArrayList<TwoCloumnInfo> getBatteryFault() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.BATTERY_FAULT_ITEM;
		String[] values = new String[names.length];
		if ((data = getFrameData(Defines.ID_BATTERY_ERROR)) != null) {
			/*
			"与充电机通信", "动力电池连接", "极柱温度高", "加热状态", "均衡状态", "动力电池亏电",
			"高压漏电",充电电流", "放电电流", "电池组欠温", "电池组高温",
			"电池组过压", "电池组欠压", "单体电池过压", "单体电池欠压"	
			*/
			int[] intArray = data.getAllValues();
			for (int n : intArray) {
				values[index++] = Misc.getHumanState2(n);
			}
		}
		return getArrayList(names, values);
	}
	
// IControl.SHOW_INFO_MOTOR_MAIN:
	private ArrayList<TwoCloumnInfo> getMotoMainInfo() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.MOTOR_MAIN_ITEM_LEFT;
		String[] values = new String[names.length];
		
		if ((data = getFrameData(Defines.ID_MOTOR_INFO)) != null) {
//		"转向", "电机运行模式", "电机运行状态", "电机温度", "控制器温度"
			values[index++] = Misc.getHumanMotoSteer(data.getValue(Defines.STEER));
			values[index++] = Misc.getHumanMotoMode(data.getValue(Defines.MOTOR_HIGHTEMP));
			values[index++] = Misc.getHumanMotoState(data.getValue(Defines.MOTOR_HEAT));
			values[index++] = new String("" + data.getValue(Defines.MOTORTEMPERTURE) + " ℃");
			values[index++] = new String("" + data.getValue(Defines.CONTROLLERTEMP) + " ℃");
		}
		
		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.MOTOR_MAIN_ITEM_RIGHT;
		values = new String[names.length];
		
		if (data != null) {
//			"电机转速", "电机转矩", 	
			values[index++] = new String("" + data.getValue(Defines.ROTATESPEED0) + " rpm");
			int nm = (int)(0.1f * data.getValue(Defines.TORQUE0));
			values[index++] = new String("" + nm + " NM");
		}
		if ((data = getFrameData(Defines.ID_MOTOR_RUNNING_INFO)) != null) {
//			"电机相电流", "电机相电压"			
			int a = (int)(0.5f * data.getValue(Defines.PHASECURRENT0));
			values[index++] = new String("" + a + " A");
			int v = (int)(0.5f * data.getValue(Defines.PHASEVOLTAGE0));
			values[index++] = new String("" + v + " V");
		}
		
		return addArrayList(list, names, values);
	}
// IControl.SHOW_INFO_AIR_CONDITION_MAIN:
	private ArrayList<TwoCloumnInfo> getAirConditionInfo() {
		int index = 0;
		CanFrameData data = null;
		String[] names = CarInfoConstant.AIR_CONDITION_MAIN_ITEM;
		String[] values = new String[names.length];
		if ((data = getFrameData(Defines.ID_AIR_CONDITION_INFO)) != null) {
//			"空调运行模式", "空调温度", "风速"	
			values[index++] = Misc.getHumanAirMode(data.getValue(Defines.AIRCONMODE));
			int t = data.getValue(Defines.AIRCONTEMP) - 40; /*17 ~ 34*/
			t = (t>=17 && t<=34)? t : 24;
			values[index++] = new String("" + t + " ℃");
			values[index++] = Misc.getHumanAirSpeed(data.getValue(Defines.AIRSPEED));
		}
		return getArrayList(names, values);
	}
	
//	IControl.SHOW_INFO_FAULT
	private ArrayList<FaultListInfo> getFaultList() {		
		return getFaultList(getWarningItemResult());
	}
	
//	IControl.SHOW_INFO_DIAGNOSIS
	private ArrayList<DiagnosisListInfo> getDiagnosis() {		
		return DiagnosisHelper.getDiagonsisList(getWarningItemResult());
	}
	
//	case IControl.SHOW_INFO_MAINTENANCE
	private Integer getMaintenance() {
		int v = Misc.getTotalMileAge(getFrameData(Defines.ID_SPEED_MILEAGE_INFO));
		return Integer.valueOf(v);
	}
	
	private boolean[] getWarningItemResult() {
		boolean[] faultArray = WarningHelpler.getDefaultWarningItemResult();
		CanFrameData data = null;
		for (WarningItemGroup group : WarningHelpler.GROUPS) {
			if (group == null || group.array == null) continue;
			if ((data = getFrameData(group.groupId)) == null) continue;
			for (WarningItem item : group.array) {
				if (item == null) continue;
				if (data.getValue(item.key) == item.value) {
					faultArray[item.id] = true;
				}
			}			
		}
		return faultArray;
	}
	
	private FaultListInfo getFaultListInfo(int id) {
		return new FaultListInfo(FaultConstant.getFaultName(id),
				FaultConstant.getFaultResolvePart(id),
				FaultConstant.getFaultResolveWay(id));
	}
	
	private ArrayList<FaultListInfo> getFaultList(boolean[] faultArray) {
		ArrayList<FaultListInfo> list = new ArrayList<FaultListInfo>();
		for (int i=1; i<faultArray.length; i++) {
			if  (faultArray[i]) {
				list.add(getFaultListInfo(i));
			}
		}
		
		return list;
	}
}
