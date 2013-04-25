package com.zjdx.vehicle.activity.main.data;


import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class CommandHelper  {
	private static final String TAG = "CommandHelper ";		
	
	public CanFrameData MakeCommand(int commandId, Object obj) {
		CanFrameData cmd = null;
		switch (commandId) {
		case IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL:
		case IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL:
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:	
			cmd = MakeLampCommand(commandId, obj);
			break;
			
		case IControl.EVENT_RIGHT_WINDOW_CONTROL:
		case IControl.EVENT_LEFT_WINDOW_CONTROL:
			break;
			
		case IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL:
		case IControl.EVENT_DOOR_LEFT_OPEN_CONTROL:		
		case IControl.EVENT_DOOR_FRONT_CONTROL:
		case IControl.EVENT_DOOR_BEHIND_CONTROL:
			cmd = MakeDoorCommand(commandId, obj);
			break;
			
		case IControl.EVENT_AIR_CONDITION_OPEN_CHANGE:
		case IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE:
		case IControl.EVENT_AIR_CONDITION_MODEL_CHANGE:
		case IControl.EVENT_AIR_CONDITION_WIND_CHANGE:
			cmd = MakeAirConditionCommand(commandId, obj);
			break;
			
		case IControl.EVENT_RAIN_WIPER_STAGE_CONTROL:	
			cmd = MakeWiperCommand(commandId, obj);
			break;
		case IControl.EVENT_HORN_CONTROL:
			cmd = MakeHornCommand(commandId, obj);
			break;
			
		default:
			LogUtils.LOGE(TAG, "MakeCommand unknown " + commandId);
			break;
		}
		LogUtils.LOGV(TAG, "MakeCommand " + commandId + " cmd= " + cmd);
		return cmd;
	}
	
	/**********************************************
	 	ID_LIGHT_COMMAND 0x601  车灯控制命令参数组定义
		00：空指令 
		10：关闭对应
		11：打开对应
		01：未定义 
		
		UI Integer:	 0: 关闭  1：打开		
	 ***********************************************/
	
	private CanFrameData MakeLampCommand(int commandId, Object obj) {
		if (obj == null || !(obj instanceof Integer))
			return null;
		int value = ((Integer)obj).intValue();		
		CanFrameData cmd = new CanFrameData(Defines.ID_LIGHT_COMMAND);
		switch (commandId) {
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:	/** COMMAND_HIGHBEAM 远光灯 **/			
			cmd.setValue(Defines.COMMAND_HIGHBEAM, value == 1? 3 : 2);
//			if (value == 1) {
//				cmd.setValue(Defines.COMMAND_LOWBEAM, 2);
//			}
			break;
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:/** COMMAND_LOWBEAM 近光灯 **/
			cmd.setValue(Defines.COMMAND_LOWBEAM, value == 1? 3 : 2);
//			if (value == 1) {
//				cmd.setValue(Defines.COMMAND_HIGHBEAM, 2);
//			}
			break;
		case IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL: /** COMMAND_RIGHTTURN 右转向 **/
			cmd.setValue(Defines.COMMAND_RIGHTTURN, value == 1? 3 : 2);
			if (value == 1) {
				cmd.setValue(Defines.COMMAND_LEFTTURN, 2);
			}
			break;
		case IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL: /** COMMAND_LEFTTURN 左转向 **/	
			cmd.setValue(Defines.COMMAND_LEFTTURN, value == 1? 3 : 2);
			if (value == 1) {
				cmd.setValue(Defines.COMMAND_RIGHTTURN, 2);
			}
			break;
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL: /** COMMAND_FRONTFOGLAMP 前雾灯 **/
			cmd.setValue(Defines.COMMAND_FRONTFOGLAMP, value == 1? 3 : 2);
			break;
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:/** COMMAND_REARFOGLAMP 后雾灯 **/		
			cmd.setValue(Defines.COMMAND_REARFOGLAMP, value == 1? 3 : 2);
			break;
		default:
			cmd = null;
			break;
		}
		
		return cmd;
	}
	
	/*****************************************************
		ID_AIR_CONDITION_COMMAND 0x603;  空调控制命令参数组定义
		空调控制模式: 0x00：空指令 0x01：开启空调 0x02：关闭空调 其他：无定义
					UI 0: 关闭  1：打开
		空调运行模式: 0x01：制冷 0x02：制热 0x03：自动 0x04：抽湿 其他：无定义
					UI 0：制冷 1：制热 2：自动 3：抽湿
		空调温度: factor 1℃/bit offset -40
					UI [17,34]
		风速: 0x01：低风 0x02：高风 其他：无定义
					UI 0：低风 1：高风	
	******************************************************/
	private CanFrameData MakeAirConditionCommand(int commandId, Object obj) {
		if (obj == null || !(obj instanceof Integer))
			return null;
		int value = ((Integer)obj).intValue();
		CanFrameData cmd =  new CanFrameData(Defines.ID_AIR_CONDITION_COMMAND);
		switch (commandId) {
		case IControl.EVENT_AIR_CONDITION_OPEN_CHANGE: /** STATUS 空调控制模式 **/	
			cmd.setValue(Defines.STATUS, value == 1? 1: 2);
			break;
		case IControl.EVENT_AIR_CONDITION_MODEL_CHANGE: /** MODE 空调运行模式 **/
			cmd.setValue(Defines.MODE, value + 1);
			break;
		case IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE: /** TEMPERTURE 空调温度**/
			cmd.setValue(Defines.TEMPERTURE, value + 40);
			break;
		case IControl.EVENT_AIR_CONDITION_WIND_CHANGE: /** COMMAND_AIRSPEED 风速 **/
			cmd.setValue(Defines.COMMAND_AIRSPEED, value + 1);
			break;
		default:
			cmd = null;
			break;
		}
		return cmd;
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
	private CanFrameData MakeDoorCommand(int commandId, Object obj) {		
		if (obj == null || !(obj instanceof Integer))
			return null;
		
		int door = 0;
		int mode = ((Integer)obj).intValue() == 1? 0x03 : 0x04;				
		switch (commandId) {
		case IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL: /****/	
			door = 0x02;
			break;
		case IControl.EVENT_DOOR_LEFT_OPEN_CONTROL:	 /****/	
			door = 0x01;
			break;
		case IControl.EVENT_DOOR_FRONT_CONTROL: /****/
			door = 0x06;
			break;
		case IControl.EVENT_DOOR_BEHIND_CONTROL: /****/
			door = 0x05;
			break;
		default:
			break;
		}
		CanFrameData cmd =  new CanFrameData(Defines.ID_DOOR_LOCK_COMMAND);
		cmd.setValue(Defines.CONTROLMODE, mode);
		cmd.setValue(Defines.DOORTYPE, door);
		
		return cmd;
	}	
	
	/*****************************************************
		ID_WIPER_COMMAND 0x605  雨刮控制命令参数组定义
		COMMAND_CONTROLMODE 雨刮控制模式 0x00：空指令 0x01：雨刮开启 0x02：雨刮关闭 其他：无定义
		COMMAND_WIPERSPEED 雨刮速度（雨刮模式为“雨刮开启”时有效）
					0x40：慢速运行 0x80：中速运行 0XC0：快速运行
					
		UI 0: 关闭  1：慢速运行  2：中速运行  3：快速运行
	 *******************************************************/
	private CanFrameData MakeWiperCommand(int commandId, Object obj) {
		if (IControl.EVENT_RAIN_WIPER_STAGE_CONTROL != commandId 
				|| obj == null || !(obj instanceof Integer)) {
			return null;
		}
		
		int mode = 0;
		int speed = 0;		
		switch(((Integer)obj).intValue()) {
		case 0:
			mode = 0x02;
			break;
		case 1:
			mode = 0x01;
			speed = 0x40;
			break;
		case 2:
			mode = 0x01;
			speed = 0x80;
			break;
		case 3:
			mode = 0x01;
			speed = 0xC0;
			break;
		default:	
			break;
		}
		CanFrameData cmd = new CanFrameData(Defines.ID_WIPER_COMMAND);
		cmd.setValue(Defines.COMMAND_CONTROLMODE, mode);
		cmd.setValue(Defines.COMMAND_WIPERSPEED, speed);
		return cmd;
	}	
	

	/******************************************************
		ID_MOTOR_HORN_COMMAND 0x602 喇叭控制命令参数组定义
		0x00：空指令 
		0x01：时长鸣叫
		0x02：停止鸣叫 
		0x03：短鸣叫一次 
		其他：无定义 
		
		鸣叫时长（时长鸣叫模式时有效）
	********************************************************/
	private CanFrameData MakeHornCommand(int commandId, Object obj) {
		if (IControl.EVENT_HORN_CONTROL != commandId 
				|| obj == null || !(obj instanceof Integer)) {
			return null;
		}
		
		CanFrameData cmd = null;
		if (((Integer)obj).intValue() == 1) {
			cmd = new CanFrameData(Defines.ID_MOTOR_HORN_COMMAND);
			cmd.setValue(Defines.VALUE, 0x03);
		}
		return cmd;
	}
}
