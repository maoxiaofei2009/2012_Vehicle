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
		// "����",, ,
		if ((data = getFrameData(Defines.ID_SPEED_MILEAGE_INFO)) != null) {	
			int speed = (int) (data.getValue(Defines.SPEED0)/256.0f);
			values[index++] = new String(""+speed + " Km/h");
		}
		
		//  "����(SOC)"	
		if ((data = getFrameData(Defines.ID_BATTERY_INFO)) != null) {	
			int soc = ((int) (0.4f * data.getValue(Defines.SOC))) % 101;
			values[index++] = new String(""+soc + "%");
		}
			
		// "��λ"
		if ((data = getFrameData(Defines.ID_CONTROLLERS_INFO)) != null) {	
			int gears = data.getValue(Defines.GEARS);
			values[index++] = Misc.getHumanGreats(gears);
		}
		
//		 "��ʻ���", 
//		"�����", 		
		if ((data = getFrameData(Defines.ID_SPEED_MILEAGE_INFO)) != null) {	
			int v =  Misc.getFlightMileAge(data);
			values[index++] = new String("" + v/10 + "." + v%10 + " Km");
			values[index++] = new String("" + Misc.getTotalMileAge(data) + " Km");	
		}
		
//		"ϵͳ����", 
//		"��ʻ״̬"
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
//			פ��ָʾ	
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.PARKING));
//			�����
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.REARFOGLIGHT));
//			ǰ���
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.HEADFOGLIGHT));
//			Զ���
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.HIGHBEAM));
//			�����
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.LOWBEAM));
//			��ת��
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.RIGHTSTEERING));
//			��ת��
			values[index++] = Misc.getHumanOnOff(data.getValue(Defines.LEFTSTEERING));
		}

		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.MESSAGE_TEXT_CAR_STATUS_RIGHT;
		values = new String[names.length];
		if ((data = getFrameData(Defines.ID_ACCESSORY_STATUS)) != null) {
//		ǰ����		
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.ENGINEHOOD));
//		�����
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.TRUNK));
//		����
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTRIGHTDOOR));
//		����
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTLEFTDOOR));
//		���ų���
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTRIGHTWINDOW));
//		���ų���
		values[index++] = Misc.getHumanOnOff(data.getValue(Defines.FRONTLEFTWINDOW));
//		���	
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
//		"��ȫ��ָʾ��",
			values[index++] = Misc.getHumanState2(data.getValue(Defines.SAFETYBELTFAULT));	
//		"��ȫ����ָʾ��",
			values[index++] = Misc.getHumanState2(data.getValue(Defines.AIRBAGRESTRAINT));
//		"�ƶ�ϵͳ����", 
			values[index++] = Misc.getHumanState2(data.getValue(Defines.BRAKESYSTEMFAULT));
//		"DC/DC ״̬"	
			values[index++] = Misc.getHumanState3(data.getValue(Defines.DCSTATUS));	
		}
		
		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.MESSAGE_TEXT_OTHER_INFO_RIGHT;
		values = new String[names.length];
		
		if (data != null) {
//		"�綯����״̬",
			values[index++] = Misc.getHumanState3(data.getValue(Defines.ELECTRICPUMP));
//		"�綯ת��״̬", 
			values[index++] = Misc.getHumanState3(data.getValue(Defines.ELECTRICSTEER));
		}
		
		if ((data = getFrameData(Defines.ID_CONTROLLERS_INFO)) != null) {		
//		"Կ�׿���״̬",
			values[index++] = Misc.getHumanKeyState(data.getValue(Defines.KEYSTATUS));
//		"���Ŵ�С���϶�"
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
//			"��������", 
			values[index++] = new String(""+ data.getValue(Defines.NUM));			
//			"����ϵͳģ��������", 
			values[index++] = new String(""+ data.getValue(Defines.COUNTER));
//			"��س��״̬", 
			values[index++] = Misc.getHumanBatteryState(data.getValue(Defines.CHARGESTATUS));
//			"���غɵ�״̬(SOC)",
			int soc = ((int) (0.4f * data.getValue(Defines.SOC))) % 101;
			values[index++] = new String(""+soc + "%");
//			"���س�����", 	
			values[index++] = new String("" + data.getValue(Defines.CHARGETIME0) + " ��");
		}
		if ((data = getFrameData(Defines.ID_BATTERY_TOTAL_V_A)) != null) {
			int volage = (int)(0.02f* data.getValue(Defines.TOTAL_VOLAGE0)); //������ص��ܵ�ѹ
			int current =(int)(0.1f* data.getValue(Defines.TOTAL_CURRENT0) - 3200); //������ص��ܵ�ѹ
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
//		"ģ���",
			values[index++] = new String("" + data.getValue(Defines.CELLINFO_NUM));
//		"��������", 
			values[index++] = new String("" + data.getValue(Defines.CELLINFO_COUNTER) + " ��");
//		"�¶Ȳ�������", 
			values[index++] = new String("" + data.getValue(Defines.SAMPLECOUNTER) + " ��");
		}
		if (data2 != null) {
			data = data2;
//		"����¶�",
			int t1 = data.getValue(Defines.TMIN) - 40;
			values[index++] = new String("" + t1 + " ��");
//		"����¶�"
			int t2 = data.getValue(Defines.TMAX) - 40;
			values[index++] = new String("" + t2 + " ��");
		}
		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.BATTERY_MODEL_BASE_INFO_ITEM_RIGHT;
		values = new String[names.length];
		if (data1 != null) {
			data = data1;
//		"ģ��SOC",
			int soc = (int)(0.4f * data.getValue(Defines.CELLINFO_SOC)) % 101;
			values[index++] = new String("" + soc + "%");
		}
		if (data2 != null) {
			data = data2;
//		"��͵�ѹ", 
			int v1 = (int)(0.001 * data.getValue(Defines.VMIN0));
			values[index++] = new String("" + v1 + " V");
//		"��ߵ�ѹ",
			int v2 = (int)(0.001 * data.getValue(Defines.VMAX0));
			values[index++] = new String("" + v2 + " V");
		}
		if (data1 != null) {
			data = data1;
//		"ģ���ܵ�ѹ",
			int v = (int)(0.02* data.getValue(Defines.CELLINFO_VOLAGE0));
			values[index++] = new String("" + v + " V");
//		"ģ���ܵ���"
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
//			"���ӿ�״̬", 
			values[index++] = Misc.getHumanChargeInterface(data.getValue(Defines.CHARGEINTERFACE));
//			"ͨ��״̬", 
			values[index++] = Misc.getHumanState2(data.getValue(Defines.COMFAULT));
//			"���뽻����ѹ", 
			values[index++] = Misc.getHumanState2(data.getValue(Defines.AVFAULT));
//			"��������",
			values[index++] = Misc.getHumanState2(data.getValue(Defines.OVERHEATING));
//			"����ѹ", 
			int v = (int)(0.1f * data.getValue(Defines.CHARGEVOLAGE0));
			values[index++] = new String("" + v + " V");	
//			"������"
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
			"�����ͨ��", "�����������", "�����¶ȸ�", "����״̬", "����״̬", "������ؿ���",
			"��ѹ©��",������", "�ŵ����", "�����Ƿ��", "��������",
			"������ѹ", "�����Ƿѹ", "�����ع�ѹ", "������Ƿѹ"	
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
//		"ת��", "�������ģʽ", "�������״̬", "����¶�", "�������¶�"
			values[index++] = Misc.getHumanMotoSteer(data.getValue(Defines.STEER));
			values[index++] = Misc.getHumanMotoMode(data.getValue(Defines.MOTOR_HIGHTEMP));
			values[index++] = Misc.getHumanMotoState(data.getValue(Defines.MOTOR_HEAT));
			values[index++] = new String("" + data.getValue(Defines.MOTORTEMPERTURE) + " ��");
			values[index++] = new String("" + data.getValue(Defines.CONTROLLERTEMP) + " ��");
		}
		
		index = 0;
		ArrayList<TwoCloumnInfo> list = getArrayList(names, values);
		names = CarInfoConstant.MOTOR_MAIN_ITEM_RIGHT;
		values = new String[names.length];
		
		if (data != null) {
//			"���ת��", "���ת��", 	
			values[index++] = new String("" + data.getValue(Defines.ROTATESPEED0) + " rpm");
			int nm = (int)(0.1f * data.getValue(Defines.TORQUE0));
			values[index++] = new String("" + nm + " NM");
		}
		if ((data = getFrameData(Defines.ID_MOTOR_RUNNING_INFO)) != null) {
//			"��������", "������ѹ"			
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
//			"�յ�����ģʽ", "�յ��¶�", "����"	
			values[index++] = Misc.getHumanAirMode(data.getValue(Defines.AIRCONMODE));
			int t = data.getValue(Defines.AIRCONTEMP) - 40; /*17 ~ 34*/
			t = (t>=17 && t<=34)? t : 24;
			values[index++] = new String("" + t + " ��");
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
