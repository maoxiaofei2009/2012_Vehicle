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
		mKeyMap.put(Defines.AIRCONDITION, IControl.EVENT_AIR_CONDITION_OPEN_CHANGE); //电动空调状态	
		mKeyMap.put(Defines.AIRCONTEMP, IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE); // 空调温度		
		mKeyMap.put(Defines.AIRCONMODE, IControl.EVENT_AIR_CONDITION_MODEL_CHANGE); //空调运行模式
		mKeyMap.put(Defines.AIRSPEED, IControl.EVENT_AIR_CONDITION_WIND_CHANGE); //风速	

//		LogUtils.LOGD(TAG, "initKeyMap ---->");
	}
	
	/************************************************
	 	空调控制模式: b00 STOP b01 OPEN b10 Error 11 NOTHING
					UI FALSE: 关闭  TRUE：打开
	 	空调运行模式: 0x01：制冷 0x02：制热 0x03：自动 0x04：抽湿 其他：无定义
					UI 0：制冷 1：制热 2：自动 3：抽湿
		空调温度: factor 1℃/bit offset -40
					UI [17,34]
		风速: 0x01：低风 0x02：高风 其他：无定义
					UI 0：低风 1：高风	
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
