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
	 	ID_LIGHT_COMMAND 0x601  ���ƿ�����������鶨��
		00����ָ�� 
		10���رն�Ӧ
		11���򿪶�Ӧ
		01��δ���� 
		
		UI Integer:	 0: �ر�  1����		
	 ***********************************************/
	
	private CanFrameData MakeLampCommand(int commandId, Object obj) {
		if (obj == null || !(obj instanceof Integer))
			return null;
		int value = ((Integer)obj).intValue();		
		CanFrameData cmd = new CanFrameData(Defines.ID_LIGHT_COMMAND);
		switch (commandId) {
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:	/** COMMAND_HIGHBEAM Զ��� **/			
			cmd.setValue(Defines.COMMAND_HIGHBEAM, value == 1? 3 : 2);
//			if (value == 1) {
//				cmd.setValue(Defines.COMMAND_LOWBEAM, 2);
//			}
			break;
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:/** COMMAND_LOWBEAM ����� **/
			cmd.setValue(Defines.COMMAND_LOWBEAM, value == 1? 3 : 2);
//			if (value == 1) {
//				cmd.setValue(Defines.COMMAND_HIGHBEAM, 2);
//			}
			break;
		case IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL: /** COMMAND_RIGHTTURN ��ת�� **/
			cmd.setValue(Defines.COMMAND_RIGHTTURN, value == 1? 3 : 2);
			if (value == 1) {
				cmd.setValue(Defines.COMMAND_LEFTTURN, 2);
			}
			break;
		case IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL: /** COMMAND_LEFTTURN ��ת�� **/	
			cmd.setValue(Defines.COMMAND_LEFTTURN, value == 1? 3 : 2);
			if (value == 1) {
				cmd.setValue(Defines.COMMAND_RIGHTTURN, 2);
			}
			break;
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL: /** COMMAND_FRONTFOGLAMP ǰ��� **/
			cmd.setValue(Defines.COMMAND_FRONTFOGLAMP, value == 1? 3 : 2);
			break;
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:/** COMMAND_REARFOGLAMP ����� **/		
			cmd.setValue(Defines.COMMAND_REARFOGLAMP, value == 1? 3 : 2);
			break;
		default:
			cmd = null;
			break;
		}
		
		return cmd;
	}
	
	/*****************************************************
		ID_AIR_CONDITION_COMMAND 0x603;  �յ�������������鶨��
		�յ�����ģʽ: 0x00����ָ�� 0x01�������յ� 0x02���رտյ� �������޶���
					UI 0: �ر�  1����
		�յ�����ģʽ: 0x01������ 0x02������ 0x03���Զ� 0x04����ʪ �������޶���
					UI 0������ 1������ 2���Զ� 3����ʪ
		�յ��¶�: factor 1��/bit offset -40
					UI [17,34]
		����: 0x01���ͷ� 0x02���߷� �������޶���
					UI 0���ͷ� 1���߷�	
	******************************************************/
	private CanFrameData MakeAirConditionCommand(int commandId, Object obj) {
		if (obj == null || !(obj instanceof Integer))
			return null;
		int value = ((Integer)obj).intValue();
		CanFrameData cmd =  new CanFrameData(Defines.ID_AIR_CONDITION_COMMAND);
		switch (commandId) {
		case IControl.EVENT_AIR_CONDITION_OPEN_CHANGE: /** STATUS �յ�����ģʽ **/	
			cmd.setValue(Defines.STATUS, value == 1? 1: 2);
			break;
		case IControl.EVENT_AIR_CONDITION_MODEL_CHANGE: /** MODE �յ�����ģʽ **/
			cmd.setValue(Defines.MODE, value + 1);
			break;
		case IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE: /** TEMPERTURE �յ��¶�**/
			cmd.setValue(Defines.TEMPERTURE, value + 40);
			break;
		case IControl.EVENT_AIR_CONDITION_WIND_CHANGE: /** COMMAND_AIRSPEED ���� **/
			cmd.setValue(Defines.COMMAND_AIRSPEED, value + 1);
			break;
		default:
			cmd = null;
			break;
		}
		return cmd;
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
		ID_WIPER_COMMAND 0x605  ��ο�����������鶨��
		COMMAND_CONTROLMODE ��ο���ģʽ 0x00����ָ�� 0x01����ο��� 0x02����ιر� �������޶���
		COMMAND_WIPERSPEED ����ٶȣ����ģʽΪ����ο�����ʱ��Ч��
					0x40���������� 0x80���������� 0XC0����������
					
		UI 0: �ر�  1����������  2����������  3����������
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
		ID_MOTOR_HORN_COMMAND 0x602 ���ȿ�����������鶨��
		0x00����ָ�� 
		0x01��ʱ������
		0x02��ֹͣ���� 
		0x03��������һ�� 
		�������޶��� 
		
		����ʱ����ʱ������ģʽʱ��Ч��
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
