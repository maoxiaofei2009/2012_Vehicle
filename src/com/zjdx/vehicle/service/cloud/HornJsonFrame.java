package com.zjdx.vehicle.service.cloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class HornJsonFrame extends AbsDownLoadJsonFrame {	
	private static final int InfoType	= CloudHelper.InfoType_CanFrameCommand;
	private static final int CtlObj		= CloudHelper.CtlObj_Horn;
	
	protected HornJsonFrame(ICommand c, DatabaseHandler db) {
		super(c, db);
		// TODO Auto-generated constructor stub
	}
	
	static int getInfoType() {
		return InfoType;
	}
	
	static int getCtlObj() {
		return CtlObj;
	}

	/******************************************************
	ID_MOTOR_HORN_COMMAND 0x602 ���ȿ�����������鶨��
	0x00����ָ�� 
	0x01���޶���
	0x02��ֹͣ���� 
	0x03��������һ�� 
	�������޶��� 
	
	����ʱ����ʱ������ģʽʱ��Ч��
********************************************************/
	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;			
		
			CanFrameData cmd =  new CanFrameData(Defines.ID_MOTOR_HORN_COMMAND);
			try {
				if (json.has("CtlMode")) { // ��������ģʽ
					int CtlMode = json.getInt("CtlMode");					
					cmd.setValue(Defines.VALUE,CtlMode);
				}			
				res = doSendCommand(cmd.getCanFrameId(), cmd.getAllValues());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} while(false);
		
		return res;
	}
}
