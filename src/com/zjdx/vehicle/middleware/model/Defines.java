package com.zjdx.vehicle.middleware.model;

public class Defines {
	/********************************************************************
	*
	* !!!  NOTE: KEEP THE SAME WITH C_Api_Type.h && MiddleWare_Types.h !!!
	*
	*********************************************************************/
	public static final int CANFRAME_LENGTH = 13;
	public static final int PAYLOAD_LENGTH = 8;	
	public static final int INVALIDE_FRAME_ID				  	= 0x000;
	
	public static final int MASK_GROUP							= 0xFFFF << 16;
		
	/********************************************************
	 *     Battery Info 
	 ********************************************************/
	public static final int ID_BATTERY_INFO 					= 0x101;
	public static final int ID_BATTERY_TOTAL_V_A 				= 0x102;
	public static final int ID_BATTERY_CELL_INFO 				= 0x103;
	public static final int ID_BATTERY_CELL_V_W_INFO 			= 0x104;
	public static final int ID_BATTERY_ERROR 					= 0x105;
	
	public static final int BatteryInfoIndex 	= (ID_BATTERY_INFO << 16);
	public static final int NUM 				= BatteryInfoIndex + 0x01; // ��������
	public static final int COUNTER 			= BatteryInfoIndex + 0x02; // ����ϵͳģ��������
	public static final int CHARGESTATUS 		= BatteryInfoIndex + 0x03; // ��س��״̬
	public static final int SOC 				= BatteryInfoIndex + 0x04; // ���غɵ�״̬
	public static final int CHARGETIME0 		= BatteryInfoIndex + 0x05; // ���س�����
	public static final int CHARGETIME1 		= BatteryInfoIndex + 0x06; // ���س�����1
	
	public static final int BatteryTotalVAIndex = (ID_BATTERY_TOTAL_V_A << 16);
	public static final int TOTAL_VOLAGE0 		= BatteryTotalVAIndex + 0x01;	// ������ص��ܵ�ѹ
	public static final int TOTAL_VOLAGE1 		= BatteryTotalVAIndex + 0x02; // ������ص��ܵ�ѹ1
	public static final int TOTAL_CURRENT0 		= BatteryTotalVAIndex + 0x03;	// ������ص��ܵ���
	public static final int TOTAL_CURRENT1 		= BatteryTotalVAIndex + 0x04; // ������ص��ܵ���1
	
	public static final int BatteryCellInfoIndex= (ID_BATTERY_CELL_INFO << 16);
	public static final int CELLINFO_NUM 		= BatteryCellInfoIndex + 0x01; //����ģ���
	public static final int CELLINFO_COUNTER 	= BatteryCellInfoIndex + 0x02; //ģ���ڵ�������
	public static final int SAMPLECOUNTER 		= BatteryCellInfoIndex + 0x03;//ģ�����¶Ȳ�������
	public static final int CELLINFO_SOC 		= BatteryCellInfoIndex + 0x04; //ģ��SOC
	public static final int CELLINFO_VOLAGE0 	= BatteryCellInfoIndex + 0x05; //ģ���ܵ�ѹ
	public static final int CELLINFO_VOLAGE1 	= BatteryCellInfoIndex + 0x06; //ģ���ܵ�ѹ1
	public static final int CELLINFO_CURRENT0	= BatteryCellInfoIndex + 0x07; //������ص��ܵ���
	public static final int CELLINFO_CURRENT1 	= BatteryCellInfoIndex + 0x08; //ģ���ܵ���1
	
	public static final int BatteryCellOtherInfoIndex = (ID_BATTERY_CELL_V_W_INFO << 16);
	public static final int CELLOTHERINFO_NUM 	= BatteryCellOtherInfoIndex + 0x01; //����ģ���
	public static final int TMIN 				= BatteryCellOtherInfoIndex + 0x02; //ģ��������¶�
	public static final int TMAX 				= BatteryCellOtherInfoIndex + 0x03; //ģ��������¶�
	public static final int VMIN0 				= BatteryCellOtherInfoIndex + 0x04; //ģ���ڵ�����͵�ѹ
	public static final int VMIN1 				= BatteryCellOtherInfoIndex + 0x05; //ģ���ڵ�����͵�ѹ1
	public static final int VMAX0 				= BatteryCellOtherInfoIndex + 0x06;  //ģ���ڵ�����͵�ѹ
	public static final int VMAX1 				= BatteryCellOtherInfoIndex + 0x07;  //ģ���ڵ�����͵�ѹ1
	 
	public static final int BatteryErrorIndex 	= (ID_BATTERY_ERROR << 16);
	public static final int CHARGECONNECTION 	= BatteryErrorIndex + 0x01; //�����ͨ�ű���
	public static final int BATTERCONNECTION 	= BatteryErrorIndex + 0x02; //�������ͨ�ű���
	public static final int HIGHTEMP 			= BatteryErrorIndex + 0x03; //�����¶ȸ߱���
	public static final int HEAT 				= BatteryErrorIndex + 0x04; //����״̬����
	public static final int BALANCED 			= BatteryErrorIndex + 0x05; //����״̬����
	public static final int LOWBATTERY 			= BatteryErrorIndex + 0x06; //������ؿ���
	public static final int LEAKAGE 			= BatteryErrorIndex + 0x07; //��ѹ©�籨��
	public static final int CHARGECURRENT 		= BatteryErrorIndex + 0x08; //����������
	public static final int DISCHARGECONNECTION = BatteryErrorIndex + 0x09; //�ŵ��������
	public static final int ARRAYLOWTEMP 		= BatteryErrorIndex + 0x10; //�����Ƿ�±���
	public static final int ARRAYHIGHTEMP 		= BatteryErrorIndex + 0x11; //�������±���
	public static final int OVERVOLTAGE 		= BatteryErrorIndex + 0x12; //������ѹ����
	public static final int UNDERVOLTAGE 		= BatteryErrorIndex + 0x13; //�����Ƿѹ����
	public static final int CELLOVERVOLTAGE 	= BatteryErrorIndex + 0x14; //�����ع�ѹ����
	public static final int CELLUNDERVOLTAGE 	= BatteryErrorIndex + 0x15; //������Ƿѹ����

	/*******************************************************************
	 *   Motor Info 
	 ***************************************************************/
	public static final int ID_MOTOR_INFO 						= 0x201;
	public static final int ID_MOTOR_RUNNING_INFO 			    = 0x202;
	
	public static final int Motor_InfoIndex 	= (ID_MOTOR_INFO << 16);
	public static final int STEER 				= Motor_InfoIndex + 0x01; //ת��
	public static final int MOTOR_HIGHTEMP 		= Motor_InfoIndex + 0x02; //�������ģʽ
	public static final int MOTOR_HEAT 			= Motor_InfoIndex + 0x03; //�������״̬
	public static final int MOTORTEMPERTURE 	= Motor_InfoIndex + 0x04; //����¶�
	public static final int CONTROLLERTEMP 		= Motor_InfoIndex + 0x05; //�������¶�
	public static final int ROTATESPEED0 		= Motor_InfoIndex + 0x06; //���ת��
	public static final int ROTATESPEED1 		= Motor_InfoIndex + 0x07; //���ת��
	public static final int TORQUE0	 			= Motor_InfoIndex + 0x08; //���ת��
	public static final int TORQUE1 			= Motor_InfoIndex + 0x09; //���ת��
	 
	public static final int Motor_Running_InfoIndex = (ID_MOTOR_RUNNING_INFO << 16);
	public static final int PHASECURRENT0 		= Motor_Running_InfoIndex + 0x01; //��������
	public static final int PHASECURRENT1 		= Motor_Running_InfoIndex + 0x02; //��������1
	public static final int PHASEVOLTAGE0	 	= Motor_Running_InfoIndex + 0x03; //������ѹ
	public static final int PHASEVOLTAGE1 		= Motor_Running_InfoIndex + 0x04; //������ѹ1
	
	
	/********************************************************
	 *   Vehicle control 
	 **********************************************************/
	public static final int ID_BODYLIGHT_INFO 					= 0x301;
	public static final int ID_VEHICL_ERROR   					= 0x302;
	public static final int ID_ACCESSORY_STATUS 				= 0x303;
	public static final int ID_VEHICLE_RUNNING_INFO 			= 0x304;
	public static final int ID_CONTROLLERS_INFO  				= 0x305;
	public static final int ID_CHARGE_INFO 						= 0x306;
	public static final int ID_SPEED_MILEAGE_INFO 				= 0x307;
	public static final int ID_AIR_CONDITION_INFO 				= 0x308;

	public static final int BodyLightIndex 		= (ID_BODYLIGHT_INFO << 16); 
	public static final int PARKING 			= BodyLightIndex + 0x01; //פ��ָʾ
	public static final int REARFOGLIGHT 		= BodyLightIndex + 0x02; //�����
	public static final int HEADFOGLIGHT 		= BodyLightIndex + 0x03; //ǰ���
	public static final int HIGHBEAM 			= BodyLightIndex + 0x04; //Զ���
	public static final int LOWBEAM 			= BodyLightIndex + 0x05; //�����
	public static final int RIGHTSTEERING  		= BodyLightIndex + 0x06; //��ת��
	public static final int LEFTSTEERING  		= BodyLightIndex + 0x07; //��ת�� 

	public static final int Vehicl_ErrorIndex 	= (ID_VEHICL_ERROR << 16);
	public static final int INSULATIONSYSTEM 	= Vehicl_ErrorIndex + 0x01; //��Եϵͳ��ⱨ��
	public static final int CHARGINGCONNECTION 	= Vehicl_ErrorIndex + 0x02; //���������ʾ
	public static final int CHARGING 			= Vehicl_ErrorIndex + 0x03; //�����״̬��ʾ
	public static final int BATTERYOFF 			= Vehicl_ErrorIndex + 0x04; //��������ж���ʾ
	public static final int BATTERYERROR 		= Vehicl_ErrorIndex + 0x05; //������ع��ϱ���
	public static final int MOTOROVERHEATING 	= Vehicl_ErrorIndex + 0x06; //������ȱ���
	public static final int MOTOROVERSPEED  	= Vehicl_ErrorIndex + 0x07; //������ٱ���
	public static final int SYSTEMERROR  		= Vehicl_ErrorIndex + 0x08; //ϵͳ���ϱ���
	
	public static final int Accessory_StatusIndex = (ID_ACCESSORY_STATUS << 16); 
	public static final int BRAKESYSTEMFAULT 	= Accessory_StatusIndex + 0x01; //�ƶ�ϵͳ����
	public static final int SAFETYBELTFAULT  	= Accessory_StatusIndex + 0x02; //��ȫ��ָʾ��
	public static final int AIRBAGRESTRAINT 	= Accessory_StatusIndex + 0x03; //��ȫ����ָʾ��
	public static final int AIRCONDITION 		= Accessory_StatusIndex + 0x04; //�綯�յ�״̬
	public static final int DCSTATUS 			= Accessory_StatusIndex + 0x05; //DC/DC ״̬
	public static final int ELECTRICPUMP 		= Accessory_StatusIndex + 0x06; //�綯����״̬
	public static final int ELECTRICSTEER  		= Accessory_StatusIndex + 0x07; //�綯ת��״̬
	public static final int Wiper_Status 		= Accessory_StatusIndex + 0x08; //���״̬
	public static final int ENGINEHOOD 			= Accessory_StatusIndex + 0x09; //ǰ����
	public static final int TRUNK 				= Accessory_StatusIndex + 0x10; //�����
	public static final int FRONTRIGHTDOOR 		= Accessory_StatusIndex + 0x11; //��ǰ��״̬
	public static final int FRONTLEFTDOOR 		= Accessory_StatusIndex + 0x12;  //��ǰ��״̬
	public static final int REARRIGHTDOOR 		= Accessory_StatusIndex + 0x13; //�Һ���״̬ TODO
	public static final int REARLEFTDOOR 		= Accessory_StatusIndex + 0x14;   //�����״̬ TODO
	public static final int FRONTRIGHTWINDOW 	= Accessory_StatusIndex + 0x15; //��ǰ�ų���״̬
	public static final int FRONTLEFTWINDOW 	= Accessory_StatusIndex + 0x16; //��ǰ�ų���״̬
	public static final int REARRIGHTWINDOW 	= Accessory_StatusIndex + 0x17; //�Һ��ų���״̬
	public static final int REARLEFTWINDOW 		= Accessory_StatusIndex + 0x18; //����ų���״̬

	public static final int VehicleRunningInfoIndex = (ID_VEHICLE_RUNNING_INFO << 16);
	public static final int SYSTEMINTERLOCK 	= VehicleRunningInfoIndex+ 0x01; //ϵͳ����
	public static final int VEHICLESTATE 		= VehicleRunningInfoIndex+ 0x02; //������ʻ״̬ 
	
	public static final int ControllerInfoIndex = (ID_CONTROLLERS_INFO << 16);
	public static final int ACCELERATORPEDAL 	= ControllerInfoIndex + 0x01; //����̤��״̬
	public static final int BRAKEPEDAL 			= ControllerInfoIndex + 0x02; //ɲ��̤��״̬
	public static final int START 				= ControllerInfoIndex + 0x03; //һ��������ť״̬
	public static final int GEARS 				= ControllerInfoIndex + 0x04; //��λ״̬
	public static final int KEYSTATUS 			= ControllerInfoIndex + 0x05; //Կ�׿���״̬
	public static final int CONTROLLER_TRUNK 	= ControllerInfoIndex + 0x06; //�����״̬
	public static final int ACCELERATORPEDALDEGREE	= ControllerInfoIndex + 0x07; //���Ŵ�С���϶� ACCELERATOR PEDAL POSITION��APP��
	
	public static final int ChargeInfoIndex 	= (ID_CHARGE_INFO << 16);
	public static final int CHARGEINTERFACE 	= ChargeInfoIndex + 0x01; //���ӿ�״̬
	public static final int COMFAULT 			= ChargeInfoIndex + 0x02; //ͨ�Ŵ���
	public static final int AVFAULT 			= ChargeInfoIndex + 0x03; //���뽻����ѹ������
	public static final int OVERHEATING 		= ChargeInfoIndex + 0x04; //�������ȴ���
	public static final int CHARGEVOLAGE0 		= ChargeInfoIndex + 0x05;  //����ѹ
	public static final int CHARGEVOLAGE1 		= ChargeInfoIndex + 0x06; //����ѹ1
	public static final int CHARGECURRENT0 		= ChargeInfoIndex + 0x07; //������
	public static final int CHARGECURRENT1 		= ChargeInfoIndex + 0x08; //������1

	public static final int SpeedMileageInfoIndex 	= (ID_SPEED_MILEAGE_INFO <<16);
	public static final int TOTALMILEAGE_MOST 		= SpeedMileageInfoIndex + 0x01; //�ܼ����ʮ��λ
	public static final int TOTALMILEAGE_MYRIABIT 	= SpeedMileageInfoIndex + 0x02; //�ܼ������λ   
	public static final int TOTALMILEAGE_THOUSANDS 	= SpeedMileageInfoIndex + 0x03; //�ܼ����ǧλ
	public static final int TOTALMILEAGE_HUNDREDS 	= SpeedMileageInfoIndex + 0x04; //�ܼ���̰�λ   
	public static final int TOTALMILEAGE_TENS 		= SpeedMileageInfoIndex + 0x05; //�ܼ����ʮλ
	public static final int TOTALMILEAGE_ONES 		= SpeedMileageInfoIndex + 0x06; //�ܼ���̸�λ   
	public static final int SMALLMILEAGE_HUNDREDS 	= SpeedMileageInfoIndex + 0x07; //С����̰�λ
	public static final int SMALLMILEAGE_TENS 		= SpeedMileageInfoIndex + 0x08; //С�����ʮλ   
	public static final int SMALLMILEAGE_ONES 		= SpeedMileageInfoIndex + 0x09; //С����̸�λ
	public static final int SMALLMILEAGE_DECIMALS 	= SpeedMileageInfoIndex + 0x10; //С�����С��λ   
	public static final int SPEED0					= SpeedMileageInfoIndex + 0x11; //����
	public static final int SPEED1 					= SpeedMileageInfoIndex + 0x12; //����

	public static final int AirConditon_InfoIndex	= (ID_AIR_CONDITION_INFO << 16);
	public static final int AIRCONMODE 				= AirConditon_InfoIndex + 0x01; //�յ�����ģʽ
	public static final int AIRCONTEMP 				= AirConditon_InfoIndex + 0x02; // �յ��¶�
	public static final int AIRSPEED 				= AirConditon_InfoIndex + 0x03; //����

	/**************************
	*
	*  Driver Assistance Systems 
	*
	****************************/
	public static final int ID_VEHICL_INFO_TO_DAS 				= 0x400;
	public static final int ID_WARNING_INFO_FROM_DAS 			= 0x700;
	
	public static final int VehiclInfoToDASIndex 	= (ID_VEHICL_INFO_TO_DAS << 16);
	public static final int BRAKESIGNAL 			= VehiclInfoToDASIndex + 0x01; //ɲ���ź�
	public static final int LEFTTURN 				= VehiclInfoToDASIndex + 0x02; //��ת����ź�
	public static final int RIGHTTURN 				= VehiclInfoToDASIndex + 0x03;  //  ��ת����ź�
	public static final int WIPERSIGNAL 			= VehiclInfoToDASIndex + 0x04; //����ź�
	public static final int HIGHBEAMSIGNAL 			= VehiclInfoToDASIndex + 0x05; //Զ����ź�
	public static final int SPEEDSIGNAL 			= VehiclInfoToDASIndex + 0x06; //�ٶ��ź�
	 

	public static final int WarningInfoFromDASIndex = (ID_WARNING_INFO_FROM_DAS << 16);
	public static final int  NIGHTSIGNAL 			= WarningInfoFromDASIndex + 0x01; //ҹ��ָʾ 
	public static final int  NIGHTFALLSIGNAL 		= WarningInfoFromDASIndex + 0x02; //�ƻ�ָʾ
	public static final int  SOUNDMODE 				= WarningInfoFromDASIndex + 0x03; //  ��������
	public static final int  HIGHLOWBEAM 			= WarningInfoFromDASIndex + 0x04; //Զ/����� 
	public static final int  ZEROSPEED 				= WarningInfoFromDASIndex + 0x05; //���ٶ�
	public static final int  RESERVED 				= WarningInfoFromDASIndex + 0x06;
	public static final int  HEADWAYMEASURE 		= WarningInfoFromDASIndex + 0x07; //ʱ��ֵ
	public static final int  HEADWAYVALID 			= WarningInfoFromDASIndex + 0x08; //ʱ��ֵ�Ƿ���Ч
	public static final int  ERRORCODE 				= WarningInfoFromDASIndex + 0x09; // �������
	public static final int  ERRORVALID 			= WarningInfoFromDASIndex + 0x10; //��������Ƿ���Ч
	public static final int  FAILSAFE 				= WarningInfoFromDASIndex + 0x11;  //�ڲ�ʧЧ����ģʽָʾ��ͼ��ģ��������ͼ�񣬲�������������͸����
	public static final int  ERROR 					= WarningInfoFromDASIndex + 0x12; //�ڲ�����ָʾ   
	public static final int  FCW 					= WarningInfoFromDASIndex + 0x13; //ǰ��ײԤ��
	public static final int  RIGHTLDWOPEN 			= WarningInfoFromDASIndex + 0x14; //����ƫ��Ԥ��
	public static final int  LEFTLDWOPEN 			= WarningInfoFromDASIndex + 0x15; //����ƫ��Ԥ��
	public static final int  LDWCLOSE 				= WarningInfoFromDASIndex + 0x16; //LDW����
	public static final int  PASSERBYDANGER 		= WarningInfoFromDASIndex + 0x17;  //���˴���Σ������  
	public static final int  PASSERBYPCW 			= WarningInfoFromDASIndex + 0x18; //����ǰ��ײԤ��
	public static final int  HEADWAYWARNINGLEVEL 	= WarningInfoFromDASIndex + 0x19;  //���������趨

	 
	/**************************
	*
	*  IFMU Command Systems 
	*
	****************************/
	public static final int ID_LIGHT_COMMAND 					= 0x601;
	public static final int ID_MOTOR_HORN_COMMAND 				= 0x602;
	public static final int ID_AIR_CONDITION_COMMAND 			= 0x603;
	public static final int ID_DOOR_LOCK_COMMAND 				= 0x604;
	public static final int ID_WIPER_COMMAND 					= 0x605;

	public static final int LightCommandIndex 		= (ID_LIGHT_COMMAND << 16); 
	public static final int COMMAND_HIGHBEAM 		= LightCommandIndex + 0x01; //Զ���
	public static final int COMMAND_LOWBEAM 		= LightCommandIndex + 0x02; //�����
	public static final int COMMAND_RIGHTTURN 		= LightCommandIndex + 0x03; //��ת��
	public static final int COMMAND_LEFTTURN 		= LightCommandIndex + 0x04; //��ת��
	public static final int COMMAND_PARKING 		= LightCommandIndex + 0x05; //פ��ָʾ
	public static final int COMMAND_REARFOGLAMP 	= LightCommandIndex + 0x06; //�����
	public static final int COMMAND_FRONTFOGLAMP	= LightCommandIndex + 0x07; //ǰ���

	public static final int MotorHornCommandIndex 	= (ID_MOTOR_HORN_COMMAND << 16);
	public static final int VALUE 					= MotorHornCommandIndex + 0x01; //���ȿ���ģʽ
	public static final int SINGTIME 				= MotorHornCommandIndex + 0x02;  //����ʱ����ʱ������ģʽʱ��Ч��

	public static final int AirConditionCommandIndex= (ID_AIR_CONDITION_COMMAND << 16);
	public static final int STATUS 					= AirConditionCommandIndex + 0x01; //�յ�����ģʽ
	public static final int MODE 					= AirConditionCommandIndex + 0x02;  //�յ�����ģʽ
	public static final int TEMPERTURE 				= AirConditionCommandIndex + 0x03; //�յ��¶�
	public static final int COMMAND_AIRSPEED 		= AirConditionCommandIndex + 0x04; //����
	 

	public static final int DoorLockCommandIndex 	= (ID_DOOR_LOCK_COMMAND << 16);
	public static final int CONTROLMODE 			= DoorLockCommandIndex + 0x01;   //��������ģʽ
	public static final int DOORTYPE 				= DoorLockCommandIndex + 0x02; //�ſ�����

	public static final int WiperCommandIndex 		= (ID_WIPER_COMMAND << 16);
	public static final int COMMAND_CONTROLMODE 	= WiperCommandIndex	+ 0x01;   //��ο���ģʽ
	public static final int COMMAND_WIPERSPEED 		= WiperCommandIndex	+ 0x02; //����ٶ�
	
	
	public static boolean isInGroup(int keyId, int groupId) {
		groupId = groupId << 16;
		return (keyId & MASK_GROUP) == (groupId & MASK_GROUP);
	}
	
	public static int getGroupId(int keyId) {
		return (keyId & MASK_GROUP) >> 16;
	}
	
}