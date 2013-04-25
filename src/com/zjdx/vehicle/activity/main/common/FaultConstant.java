package com.zjdx.vehicle.activity.main.common;

import com.zjdx.vehicle.middleware.model.WarningHelpler;



public class FaultConstant{
	private static final int CLOUMN_NAME = 0;
	private static final int CLOUMN_RESOLVE_PART = 1;
	private static final int CLOUMN_RESOLVE_WAY = 2;	
	private static final String[][] FAULT_ITEM = new String[][]{
		{"与充电机通讯报警", "电池", 		"停止充电或行驶"},
		{"动力电池连接报警", "电池", 		"不能充电"},
		{"极柱温度高报警", 	"电池", 		"停止充电或行驶"},
		{"加热状态报警", 	"电池", 		"停止充电或行驶"},
		{"均衡状态报警", 	"电池", 		"停止充电或行驶"},
		{"动力电池亏电", 	"电池", 		"停止行驶"},
		{"高压漏电报警", 	"电池", 		"停车检查漏电源"},
		{"充电电流报警", 	"充电机", 	"停止充电"},
		{"放电电流报警", 	"电池，控制器", 				"车速低于10km/h"},
		{"电池组欠温报警", 	"电池管理系统接插件，电池", 	"停车保温电池或检查线束"},
		{"电池组高温报警", 	"电池", 						"停车"},
		{"电池组过压报警", 	"电池，控制器，充电机", 		"停止充电或行驶，更换电池"},
		{"电池组欠压报警", 	"电池，控制器", 				"停车，关电，检查电池框低压极柱或更换电池"},
		{"单体电池过压报警", "电池，控制器，充电机", 		"停止充电或行驶，更换电池"},
		{"单体电池欠压报警", "电池，控制器", 				"停车，关电，检查电池框低压极柱或更换电池"},
		{"绝缘系统检测报警", "电池，控制器", 				"停车检查漏电源"},
		{"动力电池切断提示", "电池", 						"停车"},
		{"动力电池故障报警", "电池",	 					"停车"},
		{"电机过热报警", 	"电机", 						"停车"},
		{"电机超速报警", 	"电机", 						"停车"},
		{"系统故障报警", 	"各个控制器、辅助系统、电池、电池管理系统", "停车"},
		{"制动系统故障", 	"脚刹、手刹", 				"停车"},
		{"电动该空调故障", 	"电动该空调", 				"关闭电动空调"},
		{"DCDC故障", 		"DCDC", 					"停车"},
		{"电动气泵故障", 	"电动气泵", 					"停车"},
		{"电动转向故障", 	"方向盘", 					"停车"},
		{"充电机过热故障", 	"充电机", 					"停止充电"},
		{"充电机通信错误", 	"充电机、电池管理系统", 		"停止充电"},
		{"安全气囊故障", 	"安全气囊", 					"车速低于10km/h"}
	};
		
	public static String getFaultName(int faultNumber){
		if (!isFaultNumberAvailable(faultNumber)){
			return null;
		}
		
		return FAULT_ITEM[faultNumber-1][CLOUMN_NAME];
	}
	
	public static String getFaultResolvePart(int faultNumber){
		if (!isFaultNumberAvailable(faultNumber)){
			return null;
		}
		
		return FAULT_ITEM[faultNumber-1][CLOUMN_RESOLVE_PART];
	}
	
	public static String getFaultResolveWay(int faultNumber){
		if (!isFaultNumberAvailable(faultNumber)){
			return null;
		}
		
		return FAULT_ITEM[faultNumber-1][CLOUMN_RESOLVE_WAY];
	}
	
	private static boolean isFaultNumberAvailable(int faultNumber){
		if (faultNumber < WarningHelpler.FIRST_INDEX || faultNumber > WarningHelpler.LAST_INDEX){
			return false;
		}else{
			return true;
		}
	}
}