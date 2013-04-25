package com.zjdx.vehicle.middleware.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zjdx.vehicle.R;


public class ItemMgr {
	private static final Item[] BatteryInfoIndex = new Item[] {
		new Item(Defines.NUM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.num, "NUM"),//蓄电池组编号
		new Item(Defines.COUNTER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.counter, "COUNTER"),//蓄电池系统模块总数量
		new Item(Defines.CHARGESTATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargestatus, "CHARGESTATUS"),//电池充电状态
		new Item(Defines.SOC, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.soc, "SOC"),//蓄电池荷电状态
		new Item(Defines.CHARGETIME0, Item.MASK_UNSIGNED_SHORT| Item.MASK_RDONLY, 
			R.string.chargetime0, "CHARGETIME0"),//蓄电池充电次数
	//	new Item(Defines.CHARGETIME1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.chargetime1, "CHARGETIME1"),//蓄电池充电次数1
	};
	
	private static final Item[] BatteryTotalVAIndex = new Item[] {
		new Item(Defines.TOTAL_VOLAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.total_volage0, "TOTAL_VOLAGE0"),//动力电池的总电压
	//	new Item(Defines.TOTAL_VOLAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.total_volage1, "TOTAL_VOLAGE1"),//动力电池的总电压1
		new Item(Defines.TOTAL_CURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.total_current0, "TOTAL_CURRENT0"),//动力电池的总电流
	//	new Item(Defines.TOTAL_CURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.total_current1, "TOTAL_CURRENT1"),//动力电池的总电流1
	};
	
	private static final Item[] BatteryCellInfoIndex = new Item[] {
		new Item(Defines.CELLINFO_NUM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellinfo_num, "CELLINFO_NUM"),//蓄电池模块号
		new Item(Defines.CELLINFO_COUNTER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellinfo_counter, "CELLINFO_COUNTER"),//模块内单体电池数
		new Item(Defines.SAMPLECOUNTER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.samplecounter, "SAMPLECOUNTER"),//模块内温度采样点数
		new Item(Defines.CELLINFO_SOC, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellinfo_soc, "CELLINFO_SOC"),//模块SOC
		new Item(Defines.CELLINFO_VOLAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.cellinfo_volage0, "CELLINFO_VOLAGE0"),//模块总电压
	//	new Item(Defines.CELLINFO_VOLAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.cellinfo_volage1, "CELLINFO_VOLAGE1"),//模块总电压1
		new Item(Defines.CELLINFO_CURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.cellinfo_current0, "CELLINFO_CURRENT0"),//动力电池的总电流
	//	new Item(Defines.CELLINFO_CURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.cellinfo_current1, "CELLINFO_CURRENT1"),//模块总电流1
	};
	
	private static final Item[] BatteryCellOtherInfoIndex = new Item[] {
		new Item(Defines.CELLOTHERINFO_NUM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellotherinfo_num, "CELLOTHERINFO_NUM"),//蓄电池模块号
		new Item(Defines.TMIN, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.tmin, "TMIN"),//模块内最低温度
		new Item(Defines.TMAX, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.tmax, "TMAX"),//模块内最高温度
		new Item(Defines.VMIN0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.vmin0, "VMIN0"),//模块内单体最低电压
	//	new Item(Defines.VMIN1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.vmin1, "VMIN1"),//模块内单体最低电压1
		new Item(Defines.VMAX0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.vmax0, "VMAX0"),//模块内单体最低电压
	//	new Item(Defines.VMAX1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.vmax1, "VMAX1"),//模块内单体最低电压1
	};
	
	private static final Item[] BatteryErrorIndex = new Item[] {
		new Item(Defines.CHARGECONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargeconnection, "CHARGECONNECTION"),//与充电机通信报警
		new Item(Defines.BATTERCONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.batterconnection, "BATTERCONNECTION"),//动力电池通信报警
		new Item(Defines.HIGHTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.hightemp, "HIGHTEMP"),//极柱温度高报警
		new Item(Defines.HEAT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.heat, "HEAT"),//加热状态报警
		new Item(Defines.BALANCED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.balanced, "BALANCED"),//均衡状态报警
		new Item(Defines.LOWBATTERY, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.lowbattery, "LOWBATTERY"),//动力电池亏电
		new Item(Defines.LEAKAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.leakage, "LEAKAGE"),//高压漏电报警
		new Item(Defines.CHARGECURRENT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargecurrent, "CHARGECURRENT"),//充电电流报警
		new Item(Defines.DISCHARGECONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.dischargeconnection, "DISCHARGECONNECTION"),//放电电流报警
		new Item(Defines.ARRAYLOWTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.arraylowtemp, "ARRAYLOWTEMP"),//电池组欠温报警
		new Item(Defines.ARRAYHIGHTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.arrayhightemp, "ARRAYHIGHTEMP"),//电池组高温报警
		new Item(Defines.OVERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.overvoltage, "OVERVOLTAGE"),//电池组过压报警
		new Item(Defines.UNDERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.undervoltage, "UNDERVOLTAGE"),//电池组欠压报警
		new Item(Defines.CELLOVERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellovervoltage, "CELLOVERVOLTAGE"),//单体电池过压报警
		new Item(Defines.CELLUNDERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellundervoltage, "CELLUNDERVOLTAGE"),//单体电池欠压报警
	};
	
	private static final Item[] Motor_InfoIndex = new Item[] {
		new Item(Defines.STEER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.steer, "STEER"),//转向
		new Item(Defines.MOTOR_HIGHTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motor_hightemp, "MOTOR_HIGHTEMP"),//电机运行模式
		new Item(Defines.MOTOR_HEAT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motor_heat, "MOTOR_HEAT"),//电机运行状态
		new Item(Defines.MOTORTEMPERTURE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motortemperture, "MOTORTEMPERTURE"),//电机温度
		new Item(Defines.CONTROLLERTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.controllertemp, "CONTROLLERTEMP"),//控制器温度
		new Item(Defines.ROTATESPEED0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.rotatespeed0, "ROTATESPEED0"),//电机转速
	//	new Item(Defines.ROTATESPEED1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.rotatespeed1, "ROTATESPEED1"),//电机转速
		new Item(Defines.TORQUE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.torque0, "TORQUE0"),//电机转矩
	//	new Item(Defines.TORQUE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.torque1, "TORQUE1"),//电机转矩
	};
	
	private static final Item[] Motor_Running_InfoIndex = new Item[] {
		new Item(Defines.PHASECURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.phasecurrent0, "PHASECURRENT0"),//电机相电流
	//	new Item(Defines.PHASECURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.phasecurrent1, "PHASECURRENT1"),//电机相电流1
		new Item(Defines.PHASEVOLTAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.phasevoltage0, "PHASEVOLTAGE0"),//电机相电压
	//	new Item(Defines.PHASEVOLTAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.phasevoltage1, "PHASEVOLTAGE1"),//电机相电压1
	};
	
	private static final Item[] BodyLightIndex = new Item[] {
		new Item(Defines.PARKING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.parking, "PARKING"),//驻车指示
		new Item(Defines.REARFOGLIGHT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearfoglight, "REARFOGLIGHT"),//后雾灯
		new Item(Defines.HEADFOGLIGHT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headfoglight, "HEADFOGLIGHT"),//前雾灯
		new Item(Defines.HIGHBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.highbeam, "HIGHBEAM"),//远光灯
		new Item(Defines.LOWBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.lowbeam, "LOWBEAM"),//近光灯
		new Item(Defines.RIGHTSTEERING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rightsteering, "RIGHTSTEERING"),//右转向
		new Item(Defines.LEFTSTEERING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.leftsteering, "LEFTSTEERING"),//左转向
	};
	
	private static final Item[] Vehicl_ErrorIndex = new Item[] {
		new Item(Defines.INSULATIONSYSTEM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.insulationsystem, "INSULATIONSYSTEM"),//绝缘系统检测报警
		new Item(Defines.CHARGINGCONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargingconnection, "CHARGINGCONNECTION"),//充电连接提示
		new Item(Defines.CHARGING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.charging, "CHARGING"),//充电中状态提示
		new Item(Defines.BATTERYOFF, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.batteryoff, "BATTERYOFF"),//动力电池切断提示
		new Item(Defines.BATTERYERROR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.batteryerror, "BATTERYERROR"),//动力电池故障报警
		new Item(Defines.MOTOROVERHEATING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motoroverheating, "MOTOROVERHEATING"),//电机过热报警
		new Item(Defines.MOTOROVERSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motoroverspeed, "MOTOROVERSPEED"),//电机超速报警
		new Item(Defines.SYSTEMERROR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.systemerror, "SYSTEMERROR"),//系统故障报警
	};
	
	private static final Item[] Accessory_StatusIndex = new Item[] {
		new Item(Defines.BRAKESYSTEMFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.brakesystemfault, "BRAKESYSTEMFAULT"),//制动系统故障
		new Item(Defines.SAFETYBELTFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.safetybeltfault, "SAFETYBELTFAULT"),//安全带指示灯
		new Item(Defines.AIRBAGRESTRAINT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.airbagrestraint, "AIRBAGRESTRAINT"),//安全气囊指示灯
		new Item(Defines.AIRCONDITION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.aircondition, "AIRCONDITION"),//电动空调状态
		new Item(Defines.DCSTATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.dcstatus, "DCSTATUS"),//DC/DC状态
		new Item(Defines.ELECTRICPUMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.electricpump, "ELECTRICPUMP"),//电动气泵状态
		new Item(Defines.ELECTRICSTEER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.electricsteer, "ELECTRICSTEER"),//电动转向状态
		new Item(Defines.Wiper_Status, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.wiper_status, "Wiper_Status"),//雨刮状态
		new Item(Defines.ENGINEHOOD, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.enginehood, "ENGINEHOOD"),//前舱门
		new Item(Defines.TRUNK, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.trunk, "TRUNK"),//后舱门
		new Item(Defines.FRONTRIGHTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontrightdoor, "FRONTRIGHTDOOR"),//右前门状态
		new Item(Defines.FRONTLEFTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontleftdoor, "FRONTLEFTDOOR"),//左前门状态
		new Item(Defines.REARRIGHTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearrightdoor, "REARRIGHTDOOR"),//右后门状态
		new Item(Defines.REARLEFTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearleftdoor, "REARLEFTDOOR"),//左后门状态
		new Item(Defines.FRONTRIGHTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontrightwindow, "FRONTRIGHTWINDOW"),//右前门车窗状态
		new Item(Defines.FRONTLEFTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontleftwindow, "FRONTLEFTWINDOW"),//左前门车窗状态
		new Item(Defines.REARRIGHTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearrightwindow, "REARRIGHTWINDOW"),//右后门车窗状态
		new Item(Defines.REARLEFTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearleftwindow, "REARLEFTWINDOW"),//左后门车窗状态
	};
	
	private static final Item[] VehicleRunningInfoIndex = new Item[] {
		new Item(Defines.SYSTEMINTERLOCK, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.systeminterlock, "SYSTEMINTERLOCK"),//系统互锁
		new Item(Defines.VEHICLESTATE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.vehiclestate, "VEHICLESTATE"),//车辆行驶状态
	};
	
	private static final Item[] ControllerInfoIndex = new Item[] {
		new Item(Defines.ACCELERATORPEDAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.acceleratorpedal, "ACCELERATORPEDAL"),//油门踏板状态
		new Item(Defines.BRAKEPEDAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.brakepedal, "BRAKEPEDAL"),//刹车踏板状态
		new Item(Defines.START, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.start, "START"),//一键启动按钮状态
		new Item(Defines.GEARS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.gears, "GEARS"),//档位状态
		new Item(Defines.KEYSTATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.keystatus, "KEYSTATUS"),//钥匙开关状态
		new Item(Defines.CONTROLLER_TRUNK, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.controller_trunk, "CONTROLLER_TRUNK"),//后舱门状态
		new Item(Defines.ACCELERATORPEDALDEGREE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.acceleratorpedaldegree, "ACCELERATORPEDALDEGREE"),//油门大小开合度ACCELERATORPEDALPOSITION（APP）
	};
	
	private static final Item[] ChargeInfoIndex = new Item[] {
		new Item(Defines.CHARGEINTERFACE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargeinterface, "CHARGEINTERFACE"),//充电接口状态
		new Item(Defines.COMFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.comfault, "COMFAULT"),//通信错误
		new Item(Defines.AVFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.avfault, "AVFAULT"),//输入交流电压不正常
		new Item(Defines.OVERHEATING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.overheating, "OVERHEATING"),//充电机过热错误
		new Item(Defines.CHARGEVOLAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.chargevolage0, "CHARGEVOLAGE0"),//充电电压
	//	new Item(Defines.CHARGEVOLAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.chargevolage1, "CHARGEVOLAGE1"),//充电电压1
		new Item(Defines.CHARGECURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.chargecurrent0, "CHARGECURRENT0"),//充电电流
	//	new Item(Defines.CHARGECURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.chargecurrent1, "CHARGECURRENT1"),//充电电流1
	};
	
	private static final Item[] SpeedMileageInfoIndex = new Item[] {
		new Item(Defines.TOTALMILEAGE_MOST, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_most, "TOTALMILEAGE_MOST"),//总计里程十万位
		new Item(Defines.TOTALMILEAGE_MYRIABIT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_myriabit, "TOTALMILEAGE_MYRIABIT"),//总计里程万位
		new Item(Defines.TOTALMILEAGE_THOUSANDS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_thousands, "TOTALMILEAGE_THOUSANDS"),//总计里程千位
		new Item(Defines.TOTALMILEAGE_HUNDREDS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_hundreds, "TOTALMILEAGE_HUNDREDS"),//总计里程百位
		new Item(Defines.TOTALMILEAGE_TENS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_tens, "TOTALMILEAGE_TENS"),//总计里程十位
		new Item(Defines.TOTALMILEAGE_ONES, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_ones, "TOTALMILEAGE_ONES"),//总计里程个位
		new Item(Defines.SMALLMILEAGE_HUNDREDS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_hundreds, "SMALLMILEAGE_HUNDREDS"),//小计里程百位
		new Item(Defines.SMALLMILEAGE_TENS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_tens, "SMALLMILEAGE_TENS"),//小计里程十位
		new Item(Defines.SMALLMILEAGE_ONES, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_ones, "SMALLMILEAGE_ONES"),//小计里程个位
		new Item(Defines.SMALLMILEAGE_DECIMALS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_decimals, "SMALLMILEAGE_DECIMALS"),//小计里程小数位
		new Item(Defines.SPEED0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.speed0, "SPEED0"),//车速
	//	new Item(Defines.SPEED1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.speed1, "SPEED1"),//车速
	};
	
	private static final Item[] AirConditon_InfoIndex = new Item[] {
		new Item(Defines.AIRCONMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.airconmode, "AIRCONMODE"),//空调运行模式
		new Item(Defines.AIRCONTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.aircontemp, "AIRCONTEMP"),//空调温度
		new Item(Defines.AIRSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.airspeed, "AIRSPEED"),//风速
	};
	
	private static final Item[] VehiclInfoToDASIndex = new Item[] {
		new Item(Defines.BRAKESIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.brakesignal, "BRAKESIGNAL"),//刹车信号
		new Item(Defines.LEFTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.leftturn, "LEFTTURN"),//左转向灯信号
		new Item(Defines.RIGHTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.rightturn, "RIGHTTURN"),//右转向灯信号
		new Item(Defines.WIPERSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.wipersignal, "WIPERSIGNAL"),//雨刮信号
		new Item(Defines.HIGHBEAMSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.highbeamsignal, "HIGHBEAMSIGNAL"),//远光灯信号
		new Item(Defines.SPEEDSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.speedsignal, "SPEEDSIGNAL"),//速度信号
	};
	
	private static final Item[] WarningInfoFromDASIndex = new Item[] {
		new Item(Defines.NIGHTSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.nightsignal, "NIGHTSIGNAL"),//夜间指示
		new Item(Defines.NIGHTFALLSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.nightfallsignal, "NIGHTFALLSIGNAL"),//黄昏指示
		new Item(Defines.SOUNDMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.soundmode, "SOUNDMODE"),//声音类型
		new Item(Defines.HIGHLOWBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.highlowbeam, "HIGHLOWBEAM"),//远/近光灯
		new Item(Defines.ZEROSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.zerospeed, "ZEROSPEED"),//零速度
		new Item(Defines.RESERVED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.reserved, "RESERVED"), 	
		new Item(Defines.HEADWAYMEASURE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headwaymeasure, "HEADWAYMEASURE"),//时距值
		new Item(Defines.HEADWAYVALID, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headwayvalid, "HEADWAYVALID"),//时距值是否有效
		new Item(Defines.ERRORCODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.errorcode, "ERRORCODE"),//错误编码
		new Item(Defines.ERRORVALID, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.errorvalid, "ERRORVALID"),//错误编码是否有效
		new Item(Defines.FAILSAFE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.failsafe, "FAILSAFE"),//内部失效保护模式指示（图像模糊，饱和图像，部分阻塞，部分透明）
		new Item(Defines.ERROR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.error, "ERROR"),//内部错误指示
		new Item(Defines.FCW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.fcw, "FCW"),//前防撞预警
		new Item(Defines.RIGHTLDWOPEN, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rightldwopen, "RIGHTLDWOPEN"),//车道偏离预警
		new Item(Defines.LEFTLDWOPEN, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.leftldwopen, "LEFTLDWOPEN"),//车道偏离预警
		new Item(Defines.LDWCLOSE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.ldwclose, "LDWCLOSE"),//LDW禁用
		new Item(Defines.PASSERBYDANGER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.passerbydanger, "PASSERBYDANGER"),//行人处于危险区域
		new Item(Defines.PASSERBYPCW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.passerbypcw, "PASSERBYPCW"),//行人前防撞预警
		new Item(Defines.HEADWAYWARNINGLEVEL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headwaywarninglevel, "HEADWAYWARNINGLEVEL"),//报警方案设定
	};
	
	private static final Item[] LightCommandIndex = new Item[] {
		new Item(Defines.COMMAND_HIGHBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_highbeam, "COMMAND_HIGHBEAM"),//远光灯
		new Item(Defines.COMMAND_LOWBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_lowbeam, "COMMAND_LOWBEAM"),//近光灯
		new Item(Defines.COMMAND_RIGHTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_rightturn, "COMMAND_RIGHTTURN"),//右转向
		new Item(Defines.COMMAND_LEFTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_leftturn, "COMMAND_LEFTTURN"),//左转向
		new Item(Defines.COMMAND_PARKING, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_parking, "COMMAND_PARKING"),//驻车指示
		new Item(Defines.COMMAND_REARFOGLAMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_rearfoglamp, "COMMAND_REARFOGLAMP"),//后雾灯
		new Item(Defines.COMMAND_FRONTFOGLAMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_frontfoglamp, "COMMAND_FRONTFOGLAMP"),//前雾灯
	};
	
	private static final Item[] MotorHornCommandIndex = new Item[] {
		new Item(Defines.VALUE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.value, "VALUE"),//喇叭控制模式
		new Item(Defines.SINGTIME, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.singtime, "SINGTIME"),//鸣叫时长（时长鸣叫模式时有效）
	};
	
	private static final Item[] AirConditionCommandIndex = new Item[] {
		new Item(Defines.STATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.status, "STATUS"),//空调控制模式
		new Item(Defines.MODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.mode, "MODE"),//空调运行模式
		new Item(Defines.TEMPERTURE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.temperture, "TEMPERTURE"),//空调温度
		new Item(Defines.COMMAND_AIRSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_airspeed, "COMMAND_AIRSPEED"),//风速
	};
	
	private static final Item[] DoorLockCommandIndex = new Item[] {
		new Item(Defines.CONTROLMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.controlmode, "CONTROLMODE"),//门锁控制模式
		new Item(Defines.DOORTYPE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.doortype, "DOORTYPE"),//门控类型
	};
	
	private static final Item[] WiperCommandIndex = new Item[] {
		new Item(Defines.COMMAND_CONTROLMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_controlmode, "COMMAND_CONTROLMODE"),//雨刮控制模式
		new Item(Defines.COMMAND_WIPERSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_wiperspeed, "COMMAND_WIPERSPEED"),//雨刮速度
	};
	
	private class ItemArray {
		final String desc;
		final Item[] array;
		
		public ItemArray(final String desc, final Item[] array) {
			this.desc = desc;
			this.array = array;
		}
	}
	
	private static ItemMgr sInstance = null;	
	private static final ItemMgr TheInstance() {
		if (sInstance == null) {
			sInstance = new ItemMgr();
		}
		return sInstance;
	}	
		
	private Map<Integer, ItemArray> map = null; 
	private ItemMgr() {
		map = new HashMap<Integer, ItemArray>();
		map.put(Defines.ID_BATTERY_INFO, 
				new ItemArray("BatteryInfoIndex", BatteryInfoIndex));
		map.put(Defines.ID_BATTERY_TOTAL_V_A, 
				new ItemArray("BatteryTotalVAIndex", BatteryTotalVAIndex));
		map.put(Defines.ID_BATTERY_CELL_INFO, 
				new ItemArray("BatteryCellInfoIndex", BatteryCellInfoIndex));
		map.put(Defines.ID_BATTERY_CELL_V_W_INFO, 
				new ItemArray("BatteryCellOtherInfoIndex", BatteryCellOtherInfoIndex));
		map.put(Defines.ID_BATTERY_ERROR, 
				new ItemArray("BatteryErrorIndex", BatteryErrorIndex));
		map.put(Defines.ID_MOTOR_INFO, 
				new ItemArray("Motor_InfoIndex", Motor_InfoIndex));
		map.put(Defines.ID_MOTOR_RUNNING_INFO, 
				new ItemArray("Motor_Running_InfoIndex", Motor_Running_InfoIndex));
		/*  Vehicle control */
		map.put(Defines.ID_BODYLIGHT_INFO, 
				new ItemArray("BodyLightIndex", BodyLightIndex));
		map.put(Defines.ID_VEHICL_ERROR, 
				new ItemArray("Vehicl_ErrorIndex", Vehicl_ErrorIndex));
		map.put(Defines.ID_ACCESSORY_STATUS, 
				new ItemArray("Accessory_StatusIndex", Accessory_StatusIndex));
		map.put(Defines.ID_VEHICLE_RUNNING_INFO, 
				new ItemArray("VehicleRunningInfoIndex", VehicleRunningInfoIndex));
		map.put(Defines.ID_CONTROLLERS_INFO, 
				new ItemArray("ControllerInfoIndex", ControllerInfoIndex));
		map.put(Defines.ID_CHARGE_INFO, 
				new ItemArray("ChargeInfoIndex", ChargeInfoIndex));		
		map.put(Defines.ID_SPEED_MILEAGE_INFO, 
				new ItemArray("SpeedMileageInfoIndex", SpeedMileageInfoIndex));
		map.put(Defines.ID_AIR_CONDITION_INFO, 
				new ItemArray("AirConditon_InfoIndex", AirConditon_InfoIndex));
		/* Driver Assistance Systems */
		map.put(Defines.ID_VEHICL_INFO_TO_DAS, 
				new ItemArray("VehiclInfoToDASIndex", VehiclInfoToDASIndex));
		map.put(Defines.ID_WARNING_INFO_FROM_DAS, 
				new ItemArray("ID_WARNING_INFO_FROM_DAS", WarningInfoFromDASIndex));
		/*  IFMU Command Systems */
		map.put(Defines.ID_LIGHT_COMMAND, 
				new ItemArray("LightCommandIndex", LightCommandIndex));
		map.put(Defines.ID_MOTOR_HORN_COMMAND, 
				new ItemArray("MotorHornCommandIndex", MotorHornCommandIndex));
		map.put(Defines.ID_AIR_CONDITION_COMMAND, 
				new ItemArray("AirConditionCommandIndex", AirConditionCommandIndex));
		map.put(Defines.ID_DOOR_LOCK_COMMAND, 
				new ItemArray("DoorLockCommandIndex", DoorLockCommandIndex));
		map.put(Defines.ID_WIPER_COMMAND, 
				new ItemArray("WiperCommandIndex", WiperCommandIndex));
	
	}
	
	public ItemArray getItemArray(int frameId) {
		return map.get(frameId);
	}	
	
	public Set<Integer> getFrameIdSet() {
		return map.keySet();
	}
	
	public static Integer[] getAllFrameId() {
		Integer[] array = null;
		Set<Integer> set = TheInstance().getFrameIdSet();
		if (!set.isEmpty()) {
			array = set.toArray(new Integer[set.size()]);			
		}
		return array;
	}
	
	public static final Item[] getItems(int frameId) {
		Item[] items = null;
		ItemArray array = TheInstance().getItemArray(frameId);
		if (array != null) {
			items = array.array;
		}
		
		return items;
	}
	
	public static final String getDesc(int frameId) {
		String desc = null;
		ItemArray array = TheInstance().getItemArray(frameId);
		if (array != null) {
			desc = array.desc;
		}
		
		return desc;
	}

}
