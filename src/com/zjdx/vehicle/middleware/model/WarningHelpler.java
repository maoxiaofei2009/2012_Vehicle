package com.zjdx.vehicle.middleware.model;

public class WarningHelpler {
	public static final int FIRST_INDEX		= 1;
	public static final int LAST_INDEX		= 29;
	public static final int MAX_FAULT_COUNT = LAST_INDEX + 1;
			
	public static class WarningItem {
		public int id;
		public int key;
		public int value;
		
		public WarningItem(int id, int key, int value) {
			this.id = id;
			this.key = key;
			this.value = value;
		}
	}
	
	public static class WarningItemGroup {
		public int groupId;
		public WarningItem[] array;
		
		public WarningItemGroup(int groupId, WarningItem[] array) {
			this.groupId = groupId;
			this.array = array;
		}
	}
	
	// ID_BATTERY_ERROR
	public static final WarningItem[] g105 = new WarningItem[] {
		new WarningItem(1, Defines.CHARGECONNECTION, 1), //与充电机通信报警
		new WarningItem(2, Defines.BATTERCONNECTION, 1), //动力电池通信报警
		new WarningItem(3, Defines.HIGHTEMP, 1), //极柱温度高报警
		new WarningItem(4, Defines.HEAT, 1), //加热状态报警
		new WarningItem(5, Defines.BALANCED, 1), //均衡状态报警
		new WarningItem(6, Defines.LOWBATTERY, 1), //动力电池亏电
		new WarningItem(7, Defines.LEAKAGE, 1), //高压漏电报警
		new WarningItem(8, Defines.CHARGECURRENT, 1), //充电电流报警
		new WarningItem(9, Defines.DISCHARGECONNECTION, 1), //放电电流报警
		new WarningItem(10, Defines.ARRAYLOWTEMP, 1), //电池组欠温报警
		new WarningItem(11, Defines.ARRAYHIGHTEMP, 1), //电池组高温报警
		new WarningItem(12, Defines.OVERVOLTAGE, 1), //电池组过压报警
		new WarningItem(13, Defines.UNDERVOLTAGE, 1), //电池组欠压报警
		new WarningItem(14, Defines.CELLOVERVOLTAGE, 1), //单体电池过压报警
		new WarningItem(15, Defines.CELLUNDERVOLTAGE, 1), //单体电池欠压报警
	};
	
	// ID_VEHICL_ERROR
	public static final WarningItem[] g302 = new WarningItem[] {
		new WarningItem(16, Defines.INSULATIONSYSTEM, 1), //绝缘系统检测报警
		new WarningItem(17, Defines.BATTERYOFF, 1), //动力电池切断提示
		new WarningItem(18, Defines.BATTERYERROR, 1), //动力电池故障报警
		new WarningItem(19, Defines.MOTOROVERHEATING, 1), //电机过热报警
		new WarningItem(20, Defines.MOTOROVERSPEED, 1), //电机超速报警
		new WarningItem(21, Defines.SYSTEMERROR, 1), //系统故障报警
	};
	
	// ID_ACCESSORY_STATUS 
	public static final WarningItem[] g303 = new WarningItem[] {
		new WarningItem(22, Defines.BRAKESYSTEMFAULT, 1), //制动系统故障
		new WarningItem(29, Defines.AIRBAGRESTRAINT, 1), //安全气囊指示灯
		new WarningItem(23, Defines.AIRCONDITION, 2), //电动空调状态
		new WarningItem(24, Defines.DCSTATUS, 2), //DC/DC 状态
		new WarningItem(25, Defines.ELECTRICPUMP, 2), //电动气泵状态
		new WarningItem(26, Defines.ELECTRICSTEER, 2), //电动转向状态
	};
	
	// ID_CHARGE_INFO 
	public static final WarningItem[] g306 = new WarningItem[] {
		new WarningItem(28, Defines.COMFAULT, 1), //通信错误
		new WarningItem(27, Defines.OVERHEATING, 1), //充电机过热错误
	};
	
	public static final WarningItemGroup[] GROUPS = new WarningItemGroup[] {
		new WarningItemGroup(Defines.ID_BATTERY_ERROR, g105),
		new WarningItemGroup(Defines.ID_VEHICL_ERROR, g302),
		new WarningItemGroup(Defines.ID_ACCESSORY_STATUS, g303),
		new WarningItemGroup(Defines.ID_CHARGE_INFO, g306),
	};	
	
	public static boolean[] getDefaultWarningItemResult() {
		return new boolean[MAX_FAULT_COUNT];		
	}
	
	public static final WarningItemGroup getWarningItemGroup(int frameId) {
		WarningItemGroup group = null;
		for (int i=0,cnt=GROUPS.length; i<cnt; i++) {
			if (frameId == GROUPS[i].groupId) {
				group = GROUPS[i];
				break;
			}
		}
		return group;
	}
	
}
