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
	ID_MOTOR_HORN_COMMAND 0x602 喇叭控制命令参数组定义
	0x00：空指令 
	0x01：无定义
	0x02：停止鸣叫 
	0x03：短鸣叫一次 
	其他：无定义 
	
	鸣叫时长（时长鸣叫模式时有效）
********************************************************/
	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;			
		
			CanFrameData cmd =  new CanFrameData(Defines.ID_MOTOR_HORN_COMMAND);
			try {
				if (json.has("CtlMode")) { // 门锁控制模式
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
