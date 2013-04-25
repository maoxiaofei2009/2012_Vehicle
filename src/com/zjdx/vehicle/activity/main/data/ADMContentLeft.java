package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Misc;

public class ADMContentLeft extends AutoDataModel {
	private static final String TAG = "ADMContentLeft ";

	public ADMContentLeft(HashMap<Integer, CanFrameData> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}
	
	public void update(int identity) {
		if (mDataMap == null && mDataMap.isEmpty())
			return;
		
		switch (identity) {
		case Defines.ID_WARNING_INFO_FROM_DAS:
			setDAS();
			break;
		case Defines.ID_SPEED_MILEAGE_INFO:
			setSpeed();
			break;
		case Defines.ID_CONTROLLERS_INFO:
			setCallStall();
			break;
		default:
			break;
		}
	}
	
	protected void setValue(int key, Object obj) {
		if (mController != null && obj != null) {
//			LogUtils.LOGD(TAG, "setValue " + key + " = " + obj);
			mController.updateCarContentLeft(key, obj);
		}
	}	
	
	private void setDAS() {
		CanFrameData data = mDataMap.get(Defines.ID_WARNING_INFO_FROM_DAS);
		if (data == null) {
			return;
		}
		
		int pcw = data.getValue(Defines.PASSERBYPCW); //行人前防撞预警
		int pdg = data.getValue(Defines.PASSERBYDANGER);  //行人处于危险区域 
		int ldwLeft = data.getValue(Defines.LEFTLDWOPEN);  //车道偏离预警
		int ldwRight = data.getValue(Defines.RIGHTLDWOPEN);  //车道偏离预警
		int fcw = data.getValue(Defines.FCW);  //前防撞预警
		int headwayValid = data.getValue(Defines.HEADWAYVALID);  //时距值是否有效
		int headwayMeasure = data.getValue(Defines.HEADWAYMEASURE); //时距值
		int level = data.getValue(Defines.HEADWAYWARNINGLEVEL); //报警方案设定
		String distance = "";
		if (headwayValid == 1 && level > 0 && headwayMeasure <= 99) {			
			distance = new String("" + headwayMeasure/10 + "." + headwayMeasure%10 + " s");
		}
		int carState = (fcw == 1) ? 3 : (headwayValid == 1)? level : 0;		
		setValue(IControl.EVENT_SECURITY_ALERT_SIDE_WALK, Misc.Int2Boolean(pcw));
		setValue(IControl.EVENT_SECURITY_ALERT_PEOPLE, Misc.Int2Boolean(pcw | pdg));
		setValue(IControl.EVENT_SECURITY_ALERT_LEFT_LANE, Misc.Int2Boolean(ldwLeft));
		setValue(IControl.EVENT_SECURITY_ALERT_RIGHT_LANE, Misc.Int2Boolean(ldwRight));		
		setValue(IControl.EVENT_SECURITY_ALERT_FRONT_CAR, Integer.valueOf(carState));
		setValue(IControl.EVENT_SECURITY_ALERT_FRONT_CAR_DISTANCE, distance);
	}
		
	private void setSpeed() {
		CanFrameData data = mDataMap.get(Defines.ID_SPEED_MILEAGE_INFO);
		if (data == null) {
			return;
		}
		
		float speed = 1.0f * data.getValue(Defines.SPEED0)/256.0f;
		setValue(IControl.EVENT_SECURITY_ALERT_CAR_SPEED, Float.valueOf(speed));
	}
	
	private void setCallStall() {			
		CanFrameData data = mDataMap.get(Defines.ID_CONTROLLERS_INFO);
		if (data == null) {
			return;
		}
		
		int stall = Misc.getGears(data.getValue(Defines.GEARS));		
		setValue(IControl.EVENT_ALERT_CAR_STALLS_LEFT, Integer.valueOf(stall));
	}

}
