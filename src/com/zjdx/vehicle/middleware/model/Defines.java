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
	public static final int NUM 				= BatteryInfoIndex + 0x01; // 蓄电池组编号
	public static final int COUNTER 			= BatteryInfoIndex + 0x02; // 蓄电池系统模块总数量
	public static final int CHARGESTATUS 		= BatteryInfoIndex + 0x03; // 电池充电状态
	public static final int SOC 				= BatteryInfoIndex + 0x04; // 蓄电池荷电状态
	public static final int CHARGETIME0 		= BatteryInfoIndex + 0x05; // 蓄电池充电次数
	public static final int CHARGETIME1 		= BatteryInfoIndex + 0x06; // 蓄电池充电次数1
	
	public static final int BatteryTotalVAIndex = (ID_BATTERY_TOTAL_V_A << 16);
	public static final int TOTAL_VOLAGE0 		= BatteryTotalVAIndex + 0x01;	// 动力电池的总电压
	public static final int TOTAL_VOLAGE1 		= BatteryTotalVAIndex + 0x02; // 动力电池的总电压1
	public static final int TOTAL_CURRENT0 		= BatteryTotalVAIndex + 0x03;	// 动力电池的总电流
	public static final int TOTAL_CURRENT1 		= BatteryTotalVAIndex + 0x04; // 动力电池的总电流1
	
	public static final int BatteryCellInfoIndex= (ID_BATTERY_CELL_INFO << 16);
	public static final int CELLINFO_NUM 		= BatteryCellInfoIndex + 0x01; //蓄电池模块号
	public static final int CELLINFO_COUNTER 	= BatteryCellInfoIndex + 0x02; //模块内单体电池数
	public static final int SAMPLECOUNTER 		= BatteryCellInfoIndex + 0x03;//模块内温度采样点数
	public static final int CELLINFO_SOC 		= BatteryCellInfoIndex + 0x04; //模块SOC
	public static final int CELLINFO_VOLAGE0 	= BatteryCellInfoIndex + 0x05; //模块总电压
	public static final int CELLINFO_VOLAGE1 	= BatteryCellInfoIndex + 0x06; //模块总电压1
	public static final int CELLINFO_CURRENT0	= BatteryCellInfoIndex + 0x07; //动力电池的总电流
	public static final int CELLINFO_CURRENT1 	= BatteryCellInfoIndex + 0x08; //模块总电流1
	
	public static final int BatteryCellOtherInfoIndex = (ID_BATTERY_CELL_V_W_INFO << 16);
	public static final int CELLOTHERINFO_NUM 	= BatteryCellOtherInfoIndex + 0x01; //蓄电池模块号
	public static final int TMIN 				= BatteryCellOtherInfoIndex + 0x02; //模块内最低温度
	public static final int TMAX 				= BatteryCellOtherInfoIndex + 0x03; //模块内最高温度
	public static final int VMIN0 				= BatteryCellOtherInfoIndex + 0x04; //模块内单体最低电压
	public static final int VMIN1 				= BatteryCellOtherInfoIndex + 0x05; //模块内单体最低电压1
	public static final int VMAX0 				= BatteryCellOtherInfoIndex + 0x06;  //模块内单体最低电压
	public static final int VMAX1 				= BatteryCellOtherInfoIndex + 0x07;  //模块内单体最低电压1
	 
	public static final int BatteryErrorIndex 	= (ID_BATTERY_ERROR << 16);
	public static final int CHARGECONNECTION 	= BatteryErrorIndex + 0x01; //与充电机通信报警
	public static final int BATTERCONNECTION 	= BatteryErrorIndex + 0x02; //动力电池通信报警
	public static final int HIGHTEMP 			= BatteryErrorIndex + 0x03; //极柱温度高报警
	public static final int HEAT 				= BatteryErrorIndex + 0x04; //加热状态报警
	public static final int BALANCED 			= BatteryErrorIndex + 0x05; //均衡状态报警
	public static final int LOWBATTERY 			= BatteryErrorIndex + 0x06; //动力电池亏电
	public static final int LEAKAGE 			= BatteryErrorIndex + 0x07; //高压漏电报警
	public static final int CHARGECURRENT 		= BatteryErrorIndex + 0x08; //充电电流报警
	public static final int DISCHARGECONNECTION = BatteryErrorIndex + 0x09; //放电电流报警
	public static final int ARRAYLOWTEMP 		= BatteryErrorIndex + 0x10; //电池组欠温报警
	public static final int ARRAYHIGHTEMP 		= BatteryErrorIndex + 0x11; //电池组高温报警
	public static final int OVERVOLTAGE 		= BatteryErrorIndex + 0x12; //电池组过压报警
	public static final int UNDERVOLTAGE 		= BatteryErrorIndex + 0x13; //电池组欠压报警
	public static final int CELLOVERVOLTAGE 	= BatteryErrorIndex + 0x14; //单体电池过压报警
	public static final int CELLUNDERVOLTAGE 	= BatteryErrorIndex + 0x15; //单体电池欠压报警

	/*******************************************************************
	 *   Motor Info 
	 ***************************************************************/
	public static final int ID_MOTOR_INFO 						= 0x201;
	public static final int ID_MOTOR_RUNNING_INFO 			    = 0x202;
	
	public static final int Motor_InfoIndex 	= (ID_MOTOR_INFO << 16);
	public static final int STEER 				= Motor_InfoIndex + 0x01; //转向
	public static final int MOTOR_HIGHTEMP 		= Motor_InfoIndex + 0x02; //电机运行模式
	public static final int MOTOR_HEAT 			= Motor_InfoIndex + 0x03; //电机运行状态
	public static final int MOTORTEMPERTURE 	= Motor_InfoIndex + 0x04; //电机温度
	public static final int CONTROLLERTEMP 		= Motor_InfoIndex + 0x05; //控制器温度
	public static final int ROTATESPEED0 		= Motor_InfoIndex + 0x06; //电机转速
	public static final int ROTATESPEED1 		= Motor_InfoIndex + 0x07; //电机转速
	public static final int TORQUE0	 			= Motor_InfoIndex + 0x08; //电机转矩
	public static final int TORQUE1 			= Motor_InfoIndex + 0x09; //电机转矩
	 
	public static final int Motor_Running_InfoIndex = (ID_MOTOR_RUNNING_INFO << 16);
	public static final int PHASECURRENT0 		= Motor_Running_InfoIndex + 0x01; //电机相电流
	public static final int PHASECURRENT1 		= Motor_Running_InfoIndex + 0x02; //电机相电流1
	public static final int PHASEVOLTAGE0	 	= Motor_Running_InfoIndex + 0x03; //电机相电压
	public static final int PHASEVOLTAGE1 		= Motor_Running_InfoIndex + 0x04; //电机相电压1
	
	
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
	public static final int PARKING 			= BodyLightIndex + 0x01; //驻车指示
	public static final int REARFOGLIGHT 		= BodyLightIndex + 0x02; //后雾灯
	public static final int HEADFOGLIGHT 		= BodyLightIndex + 0x03; //前雾灯
	public static final int HIGHBEAM 			= BodyLightIndex + 0x04; //远光灯
	public static final int LOWBEAM 			= BodyLightIndex + 0x05; //近光灯
	public static final int RIGHTSTEERING  		= BodyLightIndex + 0x06; //右转向
	public static final int LEFTSTEERING  		= BodyLightIndex + 0x07; //左转向 

	public static final int Vehicl_ErrorIndex 	= (ID_VEHICL_ERROR << 16);
	public static final int INSULATIONSYSTEM 	= Vehicl_ErrorIndex + 0x01; //绝缘系统检测报警
	public static final int CHARGINGCONNECTION 	= Vehicl_ErrorIndex + 0x02; //充电连接提示
	public static final int CHARGING 			= Vehicl_ErrorIndex + 0x03; //充电中状态提示
	public static final int BATTERYOFF 			= Vehicl_ErrorIndex + 0x04; //动力电池切断提示
	public static final int BATTERYERROR 		= Vehicl_ErrorIndex + 0x05; //动力电池故障报警
	public static final int MOTOROVERHEATING 	= Vehicl_ErrorIndex + 0x06; //电机过热报警
	public static final int MOTOROVERSPEED  	= Vehicl_ErrorIndex + 0x07; //电机超速报警
	public static final int SYSTEMERROR  		= Vehicl_ErrorIndex + 0x08; //系统故障报警
	
	public static final int Accessory_StatusIndex = (ID_ACCESSORY_STATUS << 16); 
	public static final int BRAKESYSTEMFAULT 	= Accessory_StatusIndex + 0x01; //制动系统故障
	public static final int SAFETYBELTFAULT  	= Accessory_StatusIndex + 0x02; //安全带指示灯
	public static final int AIRBAGRESTRAINT 	= Accessory_StatusIndex + 0x03; //安全气囊指示灯
	public static final int AIRCONDITION 		= Accessory_StatusIndex + 0x04; //电动空调状态
	public static final int DCSTATUS 			= Accessory_StatusIndex + 0x05; //DC/DC 状态
	public static final int ELECTRICPUMP 		= Accessory_StatusIndex + 0x06; //电动气泵状态
	public static final int ELECTRICSTEER  		= Accessory_StatusIndex + 0x07; //电动转向状态
	public static final int Wiper_Status 		= Accessory_StatusIndex + 0x08; //雨刮状态
	public static final int ENGINEHOOD 			= Accessory_StatusIndex + 0x09; //前舱门
	public static final int TRUNK 				= Accessory_StatusIndex + 0x10; //后舱门
	public static final int FRONTRIGHTDOOR 		= Accessory_StatusIndex + 0x11; //右前门状态
	public static final int FRONTLEFTDOOR 		= Accessory_StatusIndex + 0x12;  //左前门状态
	public static final int REARRIGHTDOOR 		= Accessory_StatusIndex + 0x13; //右后门状态 TODO
	public static final int REARLEFTDOOR 		= Accessory_StatusIndex + 0x14;   //左后门状态 TODO
	public static final int FRONTRIGHTWINDOW 	= Accessory_StatusIndex + 0x15; //右前门车窗状态
	public static final int FRONTLEFTWINDOW 	= Accessory_StatusIndex + 0x16; //左前门车窗状态
	public static final int REARRIGHTWINDOW 	= Accessory_StatusIndex + 0x17; //右后门车窗状态
	public static final int REARLEFTWINDOW 		= Accessory_StatusIndex + 0x18; //左后门车窗状态

	public static final int VehicleRunningInfoIndex = (ID_VEHICLE_RUNNING_INFO << 16);
	public static final int SYSTEMINTERLOCK 	= VehicleRunningInfoIndex+ 0x01; //系统互锁
	public static final int VEHICLESTATE 		= VehicleRunningInfoIndex+ 0x02; //车辆行驶状态 
	
	public static final int ControllerInfoIndex = (ID_CONTROLLERS_INFO << 16);
	public static final int ACCELERATORPEDAL 	= ControllerInfoIndex + 0x01; //油门踏板状态
	public static final int BRAKEPEDAL 			= ControllerInfoIndex + 0x02; //刹车踏板状态
	public static final int START 				= ControllerInfoIndex + 0x03; //一键启动按钮状态
	public static final int GEARS 				= ControllerInfoIndex + 0x04; //档位状态
	public static final int KEYSTATUS 			= ControllerInfoIndex + 0x05; //钥匙开关状态
	public static final int CONTROLLER_TRUNK 	= ControllerInfoIndex + 0x06; //后舱门状态
	public static final int ACCELERATORPEDALDEGREE	= ControllerInfoIndex + 0x07; //油门大小开合度 ACCELERATOR PEDAL POSITION（APP）
	
	public static final int ChargeInfoIndex 	= (ID_CHARGE_INFO << 16);
	public static final int CHARGEINTERFACE 	= ChargeInfoIndex + 0x01; //充电接口状态
	public static final int COMFAULT 			= ChargeInfoIndex + 0x02; //通信错误
	public static final int AVFAULT 			= ChargeInfoIndex + 0x03; //输入交流电压不正常
	public static final int OVERHEATING 		= ChargeInfoIndex + 0x04; //充电机过热错误
	public static final int CHARGEVOLAGE0 		= ChargeInfoIndex + 0x05;  //充电电压
	public static final int CHARGEVOLAGE1 		= ChargeInfoIndex + 0x06; //充电电压1
	public static final int CHARGECURRENT0 		= ChargeInfoIndex + 0x07; //充电电流
	public static final int CHARGECURRENT1 		= ChargeInfoIndex + 0x08; //充电电流1

	public static final int SpeedMileageInfoIndex 	= (ID_SPEED_MILEAGE_INFO <<16);
	public static final int TOTALMILEAGE_MOST 		= SpeedMileageInfoIndex + 0x01; //总计里程十万位
	public static final int TOTALMILEAGE_MYRIABIT 	= SpeedMileageInfoIndex + 0x02; //总计里程万位   
	public static final int TOTALMILEAGE_THOUSANDS 	= SpeedMileageInfoIndex + 0x03; //总计里程千位
	public static final int TOTALMILEAGE_HUNDREDS 	= SpeedMileageInfoIndex + 0x04; //总计里程百位   
	public static final int TOTALMILEAGE_TENS 		= SpeedMileageInfoIndex + 0x05; //总计里程十位
	public static final int TOTALMILEAGE_ONES 		= SpeedMileageInfoIndex + 0x06; //总计里程个位   
	public static final int SMALLMILEAGE_HUNDREDS 	= SpeedMileageInfoIndex + 0x07; //小计里程百位
	public static final int SMALLMILEAGE_TENS 		= SpeedMileageInfoIndex + 0x08; //小计里程十位   
	public static final int SMALLMILEAGE_ONES 		= SpeedMileageInfoIndex + 0x09; //小计里程个位
	public static final int SMALLMILEAGE_DECIMALS 	= SpeedMileageInfoIndex + 0x10; //小计里程小数位   
	public static final int SPEED0					= SpeedMileageInfoIndex + 0x11; //车速
	public static final int SPEED1 					= SpeedMileageInfoIndex + 0x12; //车速

	public static final int AirConditon_InfoIndex	= (ID_AIR_CONDITION_INFO << 16);
	public static final int AIRCONMODE 				= AirConditon_InfoIndex + 0x01; //空调运行模式
	public static final int AIRCONTEMP 				= AirConditon_InfoIndex + 0x02; // 空调温度
	public static final int AIRSPEED 				= AirConditon_InfoIndex + 0x03; //风速

	/**************************
	*
	*  Driver Assistance Systems 
	*
	****************************/
	public static final int ID_VEHICL_INFO_TO_DAS 				= 0x400;
	public static final int ID_WARNING_INFO_FROM_DAS 			= 0x700;
	
	public static final int VehiclInfoToDASIndex 	= (ID_VEHICL_INFO_TO_DAS << 16);
	public static final int BRAKESIGNAL 			= VehiclInfoToDASIndex + 0x01; //刹车信号
	public static final int LEFTTURN 				= VehiclInfoToDASIndex + 0x02; //左转向灯信号
	public static final int RIGHTTURN 				= VehiclInfoToDASIndex + 0x03;  //  右转向灯信号
	public static final int WIPERSIGNAL 			= VehiclInfoToDASIndex + 0x04; //雨刮信号
	public static final int HIGHBEAMSIGNAL 			= VehiclInfoToDASIndex + 0x05; //远光灯信号
	public static final int SPEEDSIGNAL 			= VehiclInfoToDASIndex + 0x06; //速度信号
	 

	public static final int WarningInfoFromDASIndex = (ID_WARNING_INFO_FROM_DAS << 16);
	public static final int  NIGHTSIGNAL 			= WarningInfoFromDASIndex + 0x01; //夜间指示 
	public static final int  NIGHTFALLSIGNAL 		= WarningInfoFromDASIndex + 0x02; //黄昏指示
	public static final int  SOUNDMODE 				= WarningInfoFromDASIndex + 0x03; //  声音类型
	public static final int  HIGHLOWBEAM 			= WarningInfoFromDASIndex + 0x04; //远/近光灯 
	public static final int  ZEROSPEED 				= WarningInfoFromDASIndex + 0x05; //零速度
	public static final int  RESERVED 				= WarningInfoFromDASIndex + 0x06;
	public static final int  HEADWAYMEASURE 		= WarningInfoFromDASIndex + 0x07; //时距值
	public static final int  HEADWAYVALID 			= WarningInfoFromDASIndex + 0x08; //时距值是否有效
	public static final int  ERRORCODE 				= WarningInfoFromDASIndex + 0x09; // 错误编码
	public static final int  ERRORVALID 			= WarningInfoFromDASIndex + 0x10; //错误编码是否有效
	public static final int  FAILSAFE 				= WarningInfoFromDASIndex + 0x11;  //内部失效保护模式指示（图像模糊，饱和图像，部分阻塞，部分透明）
	public static final int  ERROR 					= WarningInfoFromDASIndex + 0x12; //内部错误指示   
	public static final int  FCW 					= WarningInfoFromDASIndex + 0x13; //前防撞预警
	public static final int  RIGHTLDWOPEN 			= WarningInfoFromDASIndex + 0x14; //车道偏离预警
	public static final int  LEFTLDWOPEN 			= WarningInfoFromDASIndex + 0x15; //车道偏离预警
	public static final int  LDWCLOSE 				= WarningInfoFromDASIndex + 0x16; //LDW禁用
	public static final int  PASSERBYDANGER 		= WarningInfoFromDASIndex + 0x17;  //行人处于危险区域  
	public static final int  PASSERBYPCW 			= WarningInfoFromDASIndex + 0x18; //行人前防撞预警
	public static final int  HEADWAYWARNINGLEVEL 	= WarningInfoFromDASIndex + 0x19;  //报警方案设定

	 
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
	public static final int COMMAND_HIGHBEAM 		= LightCommandIndex + 0x01; //远光灯
	public static final int COMMAND_LOWBEAM 		= LightCommandIndex + 0x02; //近光灯
	public static final int COMMAND_RIGHTTURN 		= LightCommandIndex + 0x03; //右转向
	public static final int COMMAND_LEFTTURN 		= LightCommandIndex + 0x04; //左转向
	public static final int COMMAND_PARKING 		= LightCommandIndex + 0x05; //驻车指示
	public static final int COMMAND_REARFOGLAMP 	= LightCommandIndex + 0x06; //后雾灯
	public static final int COMMAND_FRONTFOGLAMP	= LightCommandIndex + 0x07; //前雾灯

	public static final int MotorHornCommandIndex 	= (ID_MOTOR_HORN_COMMAND << 16);
	public static final int VALUE 					= MotorHornCommandIndex + 0x01; //喇叭控制模式
	public static final int SINGTIME 				= MotorHornCommandIndex + 0x02;  //鸣叫时长（时长鸣叫模式时有效）

	public static final int AirConditionCommandIndex= (ID_AIR_CONDITION_COMMAND << 16);
	public static final int STATUS 					= AirConditionCommandIndex + 0x01; //空调控制模式
	public static final int MODE 					= AirConditionCommandIndex + 0x02;  //空调运行模式
	public static final int TEMPERTURE 				= AirConditionCommandIndex + 0x03; //空调温度
	public static final int COMMAND_AIRSPEED 		= AirConditionCommandIndex + 0x04; //风速
	 

	public static final int DoorLockCommandIndex 	= (ID_DOOR_LOCK_COMMAND << 16);
	public static final int CONTROLMODE 			= DoorLockCommandIndex + 0x01;   //门锁控制模式
	public static final int DOORTYPE 				= DoorLockCommandIndex + 0x02; //门控类型

	public static final int WiperCommandIndex 		= (ID_WIPER_COMMAND << 16);
	public static final int COMMAND_CONTROLMODE 	= WiperCommandIndex	+ 0x01;   //雨刮控制模式
	public static final int COMMAND_WIPERSPEED 		= WiperCommandIndex	+ 0x02; //雨刮速度
	
	
	public static boolean isInGroup(int keyId, int groupId) {
		groupId = groupId << 16;
		return (keyId & MASK_GROUP) == (groupId & MASK_GROUP);
	}
	
	public static int getGroupId(int keyId) {
		return (keyId & MASK_GROUP) >> 16;
	}
	
}