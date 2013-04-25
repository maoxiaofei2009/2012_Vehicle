package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.common.CarStatusHelper;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;

public class ADMAlertDock extends AutoDataModel {
	private static final String TAG = "ADMAlertDock ";
	public ADMAlertDock(HashMap<Integer, CanFrameData> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}
	
	protected void initKeyMap() {
//		LogUtils.LOGD(TAG, "initKeyMap <----");
		if (mKeyMap == null)
			return;		
		//========================Alert Doc =================
		mKeyMap.put(Defines.HIGHBEAM, IControl.EVENT_LAMP_FAR_LIGHT_CONTROL); //远光灯
		mKeyMap.put(Defines.LOWBEAM,IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL);  //近光灯
		mKeyMap.put(Defines.HEADFOGLIGHT,IControl.EVENT_LAMP_FRONT_FLOG_CONTROL); //前雾灯
		mKeyMap.put(Defines.REARFOGLIGHT,IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL); //后雾灯 
		mKeyMap.put(Defines.ENGINEHOOD,IControl.EVENT_DOOR_FRONT_CONTROL); //前舱门
		mKeyMap.put(Defines.TRUNK,IControl.EVENT_DOOR_BEHIND_CONTROL); //后舱门

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
		return Misc.Int2Boolean(v);
	} 
	
	protected void setValue(int key, Object obj) {
		if (mController != null && obj != null && obj instanceof Boolean) {
//			LogUtils.LOGD(TAG, "setValue " + key + " = " + obj);
			mController.updateCarAlertDock(key, obj);
		}
	}
}
