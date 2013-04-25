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
	 	�����ͨѶ����	0x00
		����������ӱ���	0x01
		�����¶ȸ߱���	0x02
		����״̬����	0x03
		����״̬����	0x04
		������ؿ���	0x05
		��ѹ©�籨��	0x06
		����������	0x07
		�ŵ��������	0x08
		�����Ƿ�±���	0x09
		�������±���	0x0A
		������ѹ����	0x0B
		�����Ƿѹ����	0x0C
		�����ع�ѹ����	0x0D
		������Ƿѹ����	0x0E
		��Եϵͳ��ⱨ��	0x0F
		��������ж���ʾ	0x10
		������ع��ϱ���	0x11
		������ȱ���	0x12
		������ٱ���	0x13
		ϵͳ���ϱ���	0x14
		�ƶ�ϵͳ����	0x15
		�綯�յ�����	0x16
		DCDC����	0x17
		�綯���ù���	0x18
		�綯ת�����	0x19
		�������ȹ���	0x1A
		����ͨ�Ŵ���	0x1B
		��ȫ���ҹ���	0x1C
	 ********************************************/
	private int mapWarningId(int id) {
		return (id - 1);
	}

}
