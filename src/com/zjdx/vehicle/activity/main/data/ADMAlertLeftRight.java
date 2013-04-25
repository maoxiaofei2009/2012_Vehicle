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
		mKeyMap.put(Defines.SYSTEMERROR,IControl.EVENT_ALERT_SYSTEM_ERROR); //ϵͳ���ϱ���
		mKeyMap.put(Defines.AIRBAGRESTRAINT,IControl.EVENT_ALERT_SAFTY_AIR_BAG);  //��ȫ����ָʾ��
		mKeyMap.put(Defines.SAFETYBELTFAULT,IControl.EVENT_ALERT_SEAT_BELT); //��ȫ��ָʾ��
		mKeyMap.put(Defines.BRAKESYSTEMFAULT,IControl.EVENT_ALERT_BRAKE_SYSTEM_ERROR); //�ƶ�ϵͳ����
		mKeyMap.put(Defines.PARKING,IControl.EVENT_ALERT_PARKING_INDICATOR); //פ��ָʾ
		mKeyMap.put(Defines.LEFTSTEERING,IControl.EVENT_ALERT_ROTATE_LEFT); //��ת�� 
			
		mKeyMap.put(Defines.MOTOROVERHEATING, IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_TEMPERATURE); ////������ȱ���
		mKeyMap.put(Defines.MOTOROVERSPEED,IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_SPEED); //������ٱ���
		mKeyMap.put(Defines.CHARGINGCONNECTION,IControl.EVENT_ALERT_CHARGING_CONNECT); //���������ʾ
		mKeyMap.put(Defines.CHARGING,IControl.EVENT_ALERT_CHARGING); //�����״̬��ʾ
		mKeyMap.put(Defines.BATTERYOFF,IControl.EVENT_ALERT_POWER_BATTERY); //��������ж���ʾ
		mKeyMap.put(Defines.INSULATIONSYSTEM,IControl.EVENT_ALERT_INSULATED_SYSTEM);  //��Եϵͳ��ⱨ��
		mKeyMap.put(Defines.RIGHTSTEERING,IControl.EVENT_ALERT_ROTATE_RIGHT); //��ת��	
		
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
			 * ������ʻ״̬��ָʾ����ϵͳ����״̬��
			 * 00=ϵͳ����STOP ״̬��01= ϵͳ����READY״̬��10��11= δ����
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
