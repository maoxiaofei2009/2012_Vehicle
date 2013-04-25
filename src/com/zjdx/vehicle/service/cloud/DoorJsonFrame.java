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
	ID_DOOR_LOCK_COMMAND 0x604; 门锁控制命令参数组定义
	CONTROLMODE 门锁控制模式:  0x00：空指令 0x01：打开所有门锁 0x02：关闭所有门锁
				0x03：打开指定门锁  0x04：关闭指定门锁 其他：无定义
	DOORTYPE 门控类型（门锁控制模式为“打开指定门锁”或“关闭指定门锁”时有效）
				0x01：左前门	0x02：右前门
				0x03：左后门 0x04：右后门
				0x05：后舱门 0x06：前舱门
				其他：无定义		
				
	UI Integer:	 0: 关闭  1：打开		
 *****************************************************/

	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;				
			CanFrameData cmd =  new CanFrameData(Defines.ID_DOOR_LOCK_COMMAND);
			try {
				if (json.has("CtlMode")) { // 门锁控制模式
					int CtlMode = json.getInt("CtlMode");					
					cmd.setValue(Defines.CONTROLMODE,CtlMode);
				}
			
				if (json.has("DoorCtlType")) { // 门控类型		
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
