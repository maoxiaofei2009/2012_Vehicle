package com.zjdx.vehicle.service.cloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class AirconditionJsonFrame extends AbsDownLoadJsonFrame {
	private static final int InfoType	= CloudHelper.InfoType_CanFrameCommand;
	private static final int CtlObj		= CloudHelper.CtlObj_AirCondition;
		
	protected AirconditionJsonFrame(ICommand c, DatabaseHandler db) {
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
	ID_AIR_CONDITION_COMMAND 0x603;  �յ�������������鶨��
	�յ�����ģʽ: 0x00����ָ�� 0x01�������յ� 0x02���رտյ� �������޶���					
	�յ�����ģʽ: 0x01������ 0x02������ 0x03���Զ� 0x04����ʪ �������޶���				
	�յ��¶�: factor 1��/bit offset -40					
	����: 0x01���ͷ� 0x02���߷� �������޶���					
******************************************************/
	
	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;				
			CanFrameData cmd =  new CanFrameData(Defines.ID_AIR_CONDITION_COMMAND);
			try {
				if (json.has("CtlMode")) { // �յ�����ģʽ	
					int CtlMode = json.getInt("CtlMode");					
					cmd.setValue(Defines.STATUS,CtlMode);
				}
			
				if (json.has("RunModeCtl")) { // �յ�����ģʽ	
					int RunModeCtl = json.getInt("RunModeCtl");					
					cmd.setValue(Defines.MODE,RunModeCtl);
				}			
			
				if (json.has("TempCtl")) { // �յ��¶�	
					int TempCtl = json.getInt("TempCtl");					
					cmd.setValue(Defines.TEMPERTURE,TempCtl + 40);
				}
	
				if (json.has("FanSpeedCtl")) { // ����	
					int FanSpeedCtl = json.getInt("FanSpeedCtl");					
					cmd.setValue(Defines.COMMAND_AIRSPEED,FanSpeedCtl);
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
