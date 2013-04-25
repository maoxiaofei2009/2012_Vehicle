package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;

public class ADMAirCondition extends AutoDataModel {

	public ADMAirCondition(HashMap<Integer, CanFrameData> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}
	
	protected void initKeyMap() {
//		LogUtils.LOGD(TAG, "initKeyMap <----");
		if (mKeyMap == null)
			return;		
		//========================Air Condition =================
		mKeyMap.put(Defines.AIRCONDITION, IControl.EVENT_AIR_CONDITION_OPEN_CHANGE); //�綯�յ�״̬	
		mKeyMap.put(Defines.AIRCONTEMP, IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE); // �յ��¶�		
		mKeyMap.put(Defines.AIRCONMODE, IControl.EVENT_AIR_CONDITION_MODEL_CHANGE); //�յ�����ģʽ
		mKeyMap.put(Defines.AIRSPEED, IControl.EVENT_AIR_CONDITION_WIND_CHANGE); //����	

//		LogUtils.LOGD(TAG, "initKeyMap ---->");
	}
	
	/************************************************
	 	�յ�����ģʽ: b00 STOP b01 OPEN b10 Error 11 NOTHING
					UI FALSE: �ر�  TRUE����
	 	�յ�����ģʽ: 0x01������ 0x02������ 0x03���Զ� 0x04����ʪ �������޶���
					UI 0������ 1������ 2���Զ� 3����ʪ
		�յ��¶�: factor 1��/bit offset -40
					UI [17,34]
		����: 0x01���ͷ� 0x02���߷� �������޶���
					UI 0���ͷ� 1���߷�	
	 **************************************************/
	protected Object getModelValue(int canFrameKey) {
//		LogUtils.LOGD(TAG, "getModelValue " + canFrameKey);
		int groupId = Defines.getGroupId(canFrameKey);
		if (mDataMap == null || !mDataMap.containsKey(groupId))
			return null;
		
		CanFrameData data = mDataMap.get(groupId);
		if (data == null)
			return null;
		
		Object obj = null;
		int v = data.getValue(canFrameKey);	
		switch (canFrameKey) {
		case Defines.AIRCONDITION:
			obj = Misc.Int2Boolean(v);
			break;
		case Defines.AIRCONTEMP:
			v -= 40;
			v = (v>=17 && v<=34)? v : 24;
			obj = Integer.valueOf(v);
			break;
		case Defines.AIRCONMODE:
			obj = Integer.valueOf(v - 1);
			break;
		case Defines.AIRSPEED:
			obj = Integer.valueOf(v - 1);
			break;
		default:
			break;
		}
		return obj;
	} 
	
	protected void setValue(int key, Object obj) {
		if (mController != null && obj != null) {
//			LogUtils.LOGD(TAG, "setValue " + key + " = " + obj);
			mController.updateCarAirCondition(key, obj);
		}
	}

}
