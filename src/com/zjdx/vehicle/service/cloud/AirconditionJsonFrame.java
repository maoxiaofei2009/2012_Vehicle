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
	ID_AIR_CONDITION_COMMAND 0x603;  空调控制命令参数组定义
	空调控制模式: 0x00：空指令 0x01：开启空调 0x02：关闭空调 其他：无定义					
	空调运行模式: 0x01：制冷 0x02：制热 0x03：自动 0x04：抽湿 其他：无定义				
	空调温度: factor 1℃/bit offset -40					
	风速: 0x01：低风 0x02：高风 其他：无定义					
******************************************************/
	
	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;
		do {
			if (json == null) 
				break;				
			CanFrameData cmd =  new CanFrameData(Defines.ID_AIR_CONDITION_COMMAND);
			try {
				if (json.has("CtlMode")) { // 空调控制模式	
					int CtlMode = json.getInt("CtlMode");					
					cmd.setValue(Defines.STATUS,CtlMode);
				}
			
				if (json.has("RunModeCtl")) { // 空调运行模式	
					int RunModeCtl = json.getInt("RunModeCtl");					
					cmd.setValue(Defines.MODE,RunModeCtl);
				}			
			
				if (json.has("TempCtl")) { // 空调温度	
					int TempCtl = json.getInt("TempCtl");					
					cmd.setValue(Defines.TEMPERTURE,TempCtl + 40);
				}
	
				if (json.has("FanSpeedCtl")) { // 风速	
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
