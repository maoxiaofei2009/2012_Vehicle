package com.zjdx.vehicle.middleware.model;


public class Misc {
	public static int getGears(int bits) {
//		000= 空档(N档)；001= 倒档R；010=P档； 100 = D档；
		int stall = 0;
		switch (bits) {	
		case 0: stall = 4; break;	/* N */	
		case 1: stall = 1; break;	/* R */			
		case 2: stall = 2; break;	/* P */			
		case 4: stall = 3; break;	/* D */			
		default: stall = 0; break;	
		}			
		return stall;
	}
	
	public static int getBCDValue(int bcd) {
		int n = bcd % 10;
		return n;
	}
	
	public static int getBCDValue(int[] bcd) {
		if (bcd == null)
			return 0;
		
		int n = 0;
		for(int i=0; i<bcd.length; i++) {
			n = n * 10 + getBCDValue(bcd[i]);
		}
		return n;
	}
	
	public static Boolean Int2Boolean(int b) {
		return (b == 1)? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static int getTotalMileAge(CanFrameData data) {		
		if (data == null) {
			return 0;
		}
		
		int[] bcdTotal  = new int[6];
		bcdTotal[0] = data.getValue(Defines.TOTALMILEAGE_MOST); //总计里程十万位
		bcdTotal[1] = data.getValue(Defines.TOTALMILEAGE_MYRIABIT); //总计里程万位 
		bcdTotal[2] = data.getValue(Defines.TOTALMILEAGE_THOUSANDS); //总计里程千位
		bcdTotal[3] = data.getValue(Defines.TOTALMILEAGE_HUNDREDS); //总计里程百位
		bcdTotal[4] = data.getValue(Defines.TOTALMILEAGE_TENS);//总计里程十位
		bcdTotal[5] = data.getValue(Defines.TOTALMILEAGE_ONES); //总计里程个位 
		
		return Misc.getBCDValue(bcdTotal);		
	}
	
	public static int getFlightMileAge(CanFrameData data) {		
		if (data == null) {
			return 0;
		}		
		
		int[] bcdFlight = new int[4];		
		bcdFlight[0] = data.getValue(Defines.SMALLMILEAGE_HUNDREDS); //小计里程百位
		bcdFlight[1] = data.getValue(Defines.SMALLMILEAGE_TENS); //小计里程十位 
		bcdFlight[2] = data.getValue(Defines.SMALLMILEAGE_ONES); //小计里程个位
		bcdFlight[3] = data.getValue(Defines.SMALLMILEAGE_DECIMALS); //小计里程小数位 
		return Misc.getBCDValue(bcdFlight);
	}
	
	public static String getHumanGreats(int great) {
		String s = "";
//		000= 空档(N档)；001= 倒档R；010=P档； 100 = D档；
		switch(great) {
		case 0: s = "N"; break;
		case 1: s = "R"; break;
		case 2: s = "P"; break;
		case 4: s = "D"; break;
		default:break;
		}
		return s;
	}
	
	public static String getHumanSystemInterLock(int n) {
		String s = "";
		switch (n) {
		case 0: s = "没有互锁"; break;
		case 1: s = "充电互锁"; break;
		case 2: s = "后舱开启互锁"; break;
		default:break;
		}
		return s;
	}
	
	public static String getHumanVehicleState(int n) {
		String s = "";
		switch (n) {
		case 0: s = "STOP"; break;
		case 1: s = "READY"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanOnOff(int n){
		String s = "";
		switch (n) {
		case 0: s = "关"; break;
		case 1: s = "开"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanWiperState(int n) {
		String s = "";
		switch(n) {
		case 0x00: s = "关"; break;	
		case 0x40: s = "低速"; break;
		case 0x80: s = "中速"; break;				
		case 0xC0: s = "高速"; break;				
		default: break;				
		}		
		return s;
	}
	
	public static String getHumanState2(int n){
		String s = "";
		switch (n) {
		case 0: s = "正常"; break;
		case 1: s = "异常"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanState3(int n){
		String s = "";
		switch (n) {
		case 0: s = "停止"; break;
		case 1: s = "运行中"; break;	
		case 2: s = "故障"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanKeyState(int n) {
		String s = "";
		switch (n) {		
		case 1: s = "ACC"; break;	
		case 2: s = "ON"; break;	
		case 3: s = "START"; break;
		default:break;
		}
		return s;
	}
	
	public static String getHumanBatteryState(int n){
		String s = "";
		switch (n) {
		case 0x00: s = "未充电"; break;
		case 0x01: s = "充电中"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanChargeInterface(int n){
		String s = "";
		switch (n) {
		case 0x00: s = "未插上"; break;
		case 0x01: s = "插上"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanMotoMode(int n){
		String s = "";
		switch (n) {
		case 1: s = "牵引"; break;
		case 2: s = "制动"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanMotoSteer(int n){
		String s = "";
		switch (n) {
		case 1: s = "顺时针"; break;
		case 2: s = "逆时针"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanMotoState(int n){
		String s = "";
		switch (n) {
		case 1: s = "运行"; break;
		case 2: s = "停止"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanAirMode(int n) {
		String s = "";
		switch (n) {
		case 0x01: s = "制冷"; break;
		case 0x02: s = "制热"; break;
		case 0x03: s = "自动"; break;
		case 0x04: s = "抽湿"; break;
		default:break;
		}
		return s;
	}
	public static String getHumanAirSpeed(int n) {
		String s = "";
		switch (n) {	
		case 0x01: s = "低风"; break;
		case 0x02: s = "高风"; break;
		default:break;
		}
		return s;
	}

	
}
