package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;

public class ADMContentCenter extends AutoDataModel {
	private static final String TAG = "ADMContentCenter "; 
	public ADMContentCenter(HashMap<Integer, CanFrameData> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}
		
	protected void initKeyMap() {
//		LogUtils.LOGD(TAG, "initKeyMap <----");
		if (mKeyMap == null)
			return;		
		//========================Content Center =================
		//BOOL
		mKeyMap.put(Defines.HIGHBEAM, IControl.EVENT_LAMP_FAR_LIGHT_CONTROL); //远光灯
		mKeyMap.put(Defines.LOWBEAM, IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL); //近光灯
		mKeyMap.put(Defines.HEADFOGLIGHT, IControl.EVENT_LAMP_FRONT_FLOG_CONTROL); //前雾灯
		mKeyMap.put(Defines.REARFOGLIGHT, IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL); //后雾灯
		mKeyMap.put(Defines.RIGHTSTEERING, IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL); //右转向
		mKeyMap.put(Defines.LEFTSTEERING, IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL); //左转向 
		mKeyMap.put(Defines.FRONTRIGHTDOOR, IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL); //右前门状态
		mKeyMap.put(Defines.FRONTLEFTDOOR, IControl.EVENT_DOOR_LEFT_OPEN_CONTROL); //左前门状态
		// INTEGER
		mKeyMap.put(Defines.Wiper_Status, IControl.EVENT_RAIN_WIPER_STAGE_CONTROL); //雨刮状态
		// FLOAT
		mKeyMap.put(Defines.FRONTRIGHTWINDOW, IControl.EVENT_RIGHT_WINDOW_CONTROL); //右前门车窗状态 
		mKeyMap.put(Defines.FRONTLEFTWINDOW, IControl.EVENT_LEFT_WINDOW_CONTROL); //左前门车窗状态

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
		
		Object obj = null;
		int v = data.getValue(canFrameKey);	
		switch (canFrameKey) {
		case Defines.Wiper_Status:
			switch(v) {
			case 0x40: v = 1; break;
			case 0x80: v = 2; break;				
			case 0xC0: v = 3; break;				
			default:   v = 0; break;				
			}
			obj = Integer.valueOf(v);
			break;
		case Defines.FRONTRIGHTWINDOW:
		case Defines.FRONTLEFTWINDOW:
			obj = Float.valueOf(v==1? 1.0f : 0.0f);
			break;
		default:
			obj = Misc.Int2Boolean(v);
			break;
		}
		return obj;
	} 
	
	protected void setValue(int key, Object obj) {
		if (mController != null && obj != null) {
//			LogUtils.LOGD(TAG, "setValue " + key + " = " + obj);
			mController.updateCarContentCenter(key, obj);
		}
	}
}
