package com.zjdx.vehicle.service.cloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class LampJsonFrame extends AbsDownLoadJsonFrame {	
	private static final int InfoType	= CloudHelper.InfoType_CanFrameCommand;
	private static final int CtlObj		= CloudHelper.CtlObj_Light;
	
	protected LampJsonFrame(ICommand c, DatabaseHandler db) {
		super(c, db);
		// TODO Auto-generated constructor stub
	}
	
	static int getInfoType() {
		return InfoType;
	}
	
	static int getCtlObj() {
		return CtlObj;
	}
	
	/**********************************************
	 	ID_LIGHT_COMMAND 0x601  ���ƿ�����������鶨��
		00����ָ�� 
		10���رն�Ӧ
		11���򿪶�Ӧ
		01��δ���� 
	 ***********************************************/

	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;			
		
			CanFrameData cmd =  new CanFrameData(Defines.ID_MOTOR_HORN_COMMAND);
			try {
				int LightType = json.getInt("LightType"); //�ƹ����	
				boolean LightSwitch = json.getBoolean("LightSwitch"); // ���ƿ���	
				switch (LightType) {
				case 0x00: //Զ��ָʾ�ƿ���	
					cmd.setValue(Defines.COMMAND_HIGHBEAM, LightSwitch? 3 : 2);
					break;
				case 0x01: //��תָʾ�ƿ���	
					cmd.setValue(Defines.COMMAND_LEFTTURN, LightSwitch? 3 : 2);
					if (LightSwitch) {
						cmd.setValue(Defines.COMMAND_RIGHTTURN, 2);
					}
					break;
				case 0x02: //��תָʾ�ƿ���	
					cmd.setValue(Defines.COMMAND_RIGHTTURN, LightSwitch? 3 : 2);
					if (LightSwitch) {
						cmd.setValue(Defines.COMMAND_LEFTTURN, 2);
					}
					break;
				case 0x03: //����ָʾ�ƿ���	
					cmd.setValue(Defines.COMMAND_LOWBEAM, LightSwitch? 3 : 2);
					break;					
				case 0x04: //ǰ��ָʾ�ƿ���	
					cmd.setValue(Defines.COMMAND_FRONTFOGLAMP, LightSwitch? 3 : 2);
					break;
				case 0x05: // ����ָʾ�ƿ���	
					cmd.setValue(Defines.COMMAND_REARFOGLAMP, LightSwitch? 3 : 2);
					break;
				default:
					break;
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
