package com.zjdx.vehicle.service.cloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class DoorJsonFrame extends AbsDownLoadJsonFrame {	
	private static final int InfoType	= CloudHelper.InfoType_CanFrameCommand;
	private static final int CtlObj		= CloudHelper.CtlObj_Door;
	
	protected DoorJsonFrame(ICommand c, DatabaseHandler db) {
		super(c, db);
		// TODO Auto-generated constructor stub
	}

	static int getInfoType() {
		return InfoType;
	}
	
	static int getCtlObj() {
		return CtlObj;
	}
		
	/*****************************************************
	ID_DOOR_LOCK_COMMAND 0x604; ����������������鶨��
	CONTROLMODE ��������ģʽ:  0x00����ָ�� 0x01������������ 0x02���ر���������
				0x03����ָ������  0x04���ر�ָ������ �������޶���
	DOORTYPE �ſ����ͣ���������ģʽΪ����ָ���������򡰹ر�ָ��������ʱ��Ч��
				0x01����ǰ��	0x02����ǰ��
				0x03������� 0x04���Һ���
				0x05������� 0x06��ǰ����
				�������޶���		
				
	UI Integer:	 0: �ر�  1����		
 *****************************************************/

	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;				
			CanFrameData cmd =  new CanFrameData(Defines.ID_DOOR_LOCK_COMMAND);
			try {
				if (json.has("CtlMode")) { // ��������ģʽ
					int CtlMode = json.getInt("CtlMode");					
					cmd.setValue(Defines.CONTROLMODE,CtlMode);
				}
			
				if (json.has("DoorCtlType")) { // �ſ�����		
					int DoorCtlType = json.getInt("DoorCtlType");					
					cmd.setValue(Defines.DOORTYPE,DoorCtlType);
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
