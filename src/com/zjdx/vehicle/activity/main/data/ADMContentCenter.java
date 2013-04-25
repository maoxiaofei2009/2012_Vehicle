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
		mKeyMap.put(Defines.HIGHBEAM, IControl.EVENT_LAMP_FAR_LIGHT_CONTROL); //Զ���
		mKeyMap.put(Defines.LOWBEAM, IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL); //�����
		mKeyMap.put(Defines.HEADFOGLIGHT, IControl.EVENT_LAMP_FRONT_FLOG_CONTROL); //ǰ���
		mKeyMap.put(Defines.REARFOGLIGHT, IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL); //�����
		mKeyMap.put(Defines.RIGHTSTEERING, IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL); //��ת��
		mKeyMap.put(Defines.LEFTSTEERING, IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL); //��ת�� 
		mKeyMap.put(Defines.FRONTRIGHTDOOR, IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL); //��ǰ��״̬
		mKeyMap.put(Defines.FRONTLEFTDOOR, IControl.EVENT_DOOR_LEFT_OPEN_CONTROL); //��ǰ��״̬
		// INTEGER
		mKeyMap.put(Defines.Wiper_Status, IControl.EVENT_RAIN_WIPER_STAGE_CONTROL); //���״̬
		// FLOAT
		mKeyMap.put(Defines.FRONTRIGHTWINDOW, IControl.EVENT_RIGHT_WINDOW_CONTROL); //��ǰ�ų���״̬ 
		mKeyMap.put(Defines.FRONTLEFTWINDOW, IControl.EVENT_LEFT_WINDOW_CONTROL); //��ǰ�ų���״̬

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
