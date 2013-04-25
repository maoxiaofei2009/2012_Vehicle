package com.zjdx.vehicle.middleware.model;


public class Misc {
	public static int getGears(int bits) {
//		000= �յ�(N��)��001= ����R��010=P���� 100 = D����
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
		bcdTotal[0] = data.getValue(Defines.TOTALMILEAGE_MOST); //�ܼ����ʮ��λ
		bcdTotal[1] = data.getValue(Defines.TOTALMILEAGE_MYRIABIT); //�ܼ������λ 
		bcdTotal[2] = data.getValue(Defines.TOTALMILEAGE_THOUSANDS); //�ܼ����ǧλ
		bcdTotal[3] = data.getValue(Defines.TOTALMILEAGE_HUNDREDS); //�ܼ���̰�λ
		bcdTotal[4] = data.getValue(Defines.TOTALMILEAGE_TENS);//�ܼ����ʮλ
		bcdTotal[5] = data.getValue(Defines.TOTALMILEAGE_ONES); //�ܼ���̸�λ 
		
		return Misc.getBCDValue(bcdTotal);		
	}
	
	public static int getFlightMileAge(CanFrameData data) {		
		if (data == null) {
			return 0;
		}		
		
		int[] bcdFlight = new int[4];		
		bcdFlight[0] = data.getValue(Defines.SMALLMILEAGE_HUNDREDS); //С����̰�λ
		bcdFlight[1] = data.getValue(Defines.SMALLMILEAGE_TENS); //С�����ʮλ 
		bcdFlight[2] = data.getValue(Defines.SMALLMILEAGE_ONES); //С����̸�λ
		bcdFlight[3] = data.getValue(Defines.SMALLMILEAGE_DECIMALS); //С�����С��λ 
		return Misc.getBCDValue(bcdFlight);
	}
	
	public static String getHumanGreats(int great) {
		String s = "";
//		000= �յ�(N��)��001= ����R��010=P���� 100 = D����
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
		case 0: s = "û�л���"; break;
		case 1: s = "��绥��"; break;
		case 2: s = "��տ�������"; break;
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
		case 0: s = "��"; break;
		case 1: s = "��"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanWiperState(int n) {
		String s = "";
		switch(n) {
		case 0x00: s = "��"; break;	
		case 0x40: s = "����"; break;
		case 0x80: s = "����"; break;				
		case 0xC0: s = "����"; break;				
		default: break;				
		}		
		return s;
	}
	
	public static String getHumanState2(int n){
		String s = "";
		switch (n) {
		case 0: s = "����"; break;
		case 1: s = "�쳣"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanState3(int n){
		String s = "";
		switch (n) {
		case 0: s = "ֹͣ"; break;
		case 1: s = "������"; break;	
		case 2: s = "����"; break;		
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
		case 0x00: s = "δ���"; break;
		case 0x01: s = "�����"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanChargeInterface(int n){
		String s = "";
		switch (n) {
		case 0x00: s = "δ����"; break;
		case 0x01: s = "����"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanMotoMode(int n){
		String s = "";
		switch (n) {
		case 1: s = "ǣ��"; break;
		case 2: s = "�ƶ�"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanMotoSteer(int n){
		String s = "";
		switch (n) {
		case 1: s = "˳ʱ��"; break;
		case 2: s = "��ʱ��"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanMotoState(int n){
		String s = "";
		switch (n) {
		case 1: s = "����"; break;
		case 2: s = "ֹͣ"; break;		
		default:break;
		}
		return s;
	}
	
	public static String getHumanAirMode(int n) {
		String s = "";
		switch (n) {
		case 0x01: s = "����"; break;
		case 0x02: s = "����"; break;
		case 0x03: s = "�Զ�"; break;
		case 0x04: s = "��ʪ"; break;
		default:break;
		}
		return s;
	}
	public static String getHumanAirSpeed(int n) {
		String s = "";
		switch (n) {	
		case 0x01: s = "�ͷ�"; break;
		case 0x02: s = "�߷�"; break;
		default:break;
		}
		return s;
	}

	
}
