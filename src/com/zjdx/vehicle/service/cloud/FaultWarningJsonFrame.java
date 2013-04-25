package com.zjdx.vehicle.service.cloud;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.WarningHelpler;
import com.zjdx.vehicle.middleware.model.WarningHelpler.WarningItem;
import com.zjdx.vehicle.middleware.model.WarningHelpler.WarningItemGroup;

public class FaultWarningJsonFrame extends AbsUploadJsonFrame {
	private static final int InfoType	= CloudHelper.InfoType_FaultWarning;
	
	private boolean[] mWarnings = null; 
	private boolean[] mLastWarnings = null; 
	protected FaultWarningJsonFrame(DatabaseHandler db, UploadCallback cb) {
		super(db, cb);
		mWarnings = WarningHelpler.getDefaultWarningItemResult();
		mLastWarnings = WarningHelpler.getDefaultWarningItemResult();
	}

	@Override
	protected boolean onReceive(int count, int[] frameIdArray) {
		System.arraycopy(mWarnings, 0, mLastWarnings, 0, mWarnings.length);
		Arrays.fill(mWarnings, false);	
		for (int i=0; i<count; i++) {
			WarningItemGroup group = WarningHelpler.getWarningItemGroup(frameIdArray[i]);
			checkWarningValue(group);			
		}
		tryUploadWarnings();
		return true;
	}
	
	private void checkWarningValue(WarningItemGroup group) {
		if (group == null)
			return;
		
		CanFrameData data = getCanFrameData(group.groupId);
		if (data == null)
			return;
		
		for (WarningItem item : group.array) {
			if (item == null) continue;
			if (data.getValue(item.key) == item.value) {
				mWarnings[item.id] = true;
			}
		}		
	}
	
	private void tryUploadWarnings() {
		int time = CloudHelper.getTime();
		for (int i=WarningHelpler.FIRST_INDEX; i<=WarningHelpler.LAST_INDEX; i++) {
			if (mWarnings[i] && !mLastWarnings[i]) {
				upload(time, genJsonFrame(mapWarningId(i), time));
			}
		}
	}
	
	private String genJsonFrame(int warningId, int time) {
		String strJson = null;
		try {
			JSONObject json = new JSONObject();
			json.put(CloudHelper.VID, CloudHelper.getVID());
			json.put(CloudHelper.InfoType, InfoType);
			json.put("AlarmInfo", warningId);
			json.put("Time", time);
			strJson = JSON2String(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strJson;
	}
	
	/*********************************
	 	与充电机通讯报警	0x00
		动力电池连接报警	0x01
		极柱温度高报警	0x02
		加热状态报警	0x03
		均衡状态报警	0x04
		动力电池亏电	0x05
		高压漏电报警	0x06
		充电电流报警	0x07
		放电电流报警	0x08
		电池组欠温报警	0x09
		电池组高温报警	0x0A
		电池组过压报警	0x0B
		电池组欠压报警	0x0C
		单体电池过压报警	0x0D
		单体电池欠压报警	0x0E
		绝缘系统检测报警	0x0F
		动力电池切断提示	0x10
		动力电池故障报警	0x11
		电机过热报警	0x12
		电机超速报警	0x13
		系统故障报警	0x14
		制动系统故障	0x15
		电动空调故障	0x16
		DCDC故障	0x17
		电动气泵故障	0x18
		电动转向故障	0x19
		充电机过热故障	0x1A
		充电机通信错误	0x1B
		安全气囊故障	0x1C
	 ********************************************/
	private int mapWarningId(int id) {
		return (id - 1);
	}

}
