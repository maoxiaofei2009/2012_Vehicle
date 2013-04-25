package com.zjdx.vehicle.activity.main.common;



public class CarInfoConstant{
	//=================================================================
	public static final int SHOW_INFO_MESSAGE_BASE_INFO = 0;
	public static final int SHOW_INFO_MESSAGE_CAR_STATUS = 1;
	public static final int SHOW_INFO_MESSAGE_OTHER_INFO = 2;
	public static final String[] MESSAGE_TEXT_BASE_INFO_ITEM = new String[]{
		"车速", "电量(SOC)", "档位", "续驶里程", "总里程", "系统互锁", "行驶状态"};
	public static final String[] MESSAGE_TEXT_CAR_STATUS_LEFT = new String[]{
		"驻车指示", "后雾灯", "前雾灯", "远光灯", "近光灯", "右转向","左转向"};
	public static final String[] MESSAGE_TEXT_CAR_STATUS_RIGHT = new String[]{
		"前舱门", "后舱门", "右门", "左门", "右门车窗", "左门车窗", "雨刮"};
	public static final String[] MESSAGE_TEXT_OTHER_INFO_LEFT = new String[]{
		"安全带指示灯", "安全气囊指示灯", "制动系统故障", "DC/DC 状态"};
	public static final String[] MESSAGE_TEXT_OTHER_INFO_RIGHT = new String[]{
		"电动气泵状态", "电动转向状态", "钥匙开关状态", "油门大小开合度"};
	
	//=================================================================
	public static final int SHOW_INFO_BATTERY_BASE_INFO = 0;
	public static final int SHOW_INFO_BATTERY_MODEL_BASE_INFO = 1;
	public static final int SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS = 2;
	public static final int SHOW_INFO_BATTERY_FAULT = 3;
	public static final String[] BATTERY_BASE_INFO_ITEM = new String[]{
		"蓄电池组编号", "蓄电池系统模块总数量", "电池充电状态", "蓄电池荷电状态(SOC)",
		"蓄电池充电次数", "动力电池的总电压", "动力电池的总电流"};
	public static final String[] BATTERY_MODEL_BASE_INFO_ITEM_LEFT = new String[]{
		"模块号", "单体电池数", "温度采样点数", "最低温度", "最高温度"};
	public static final String[] BATTERY_MODEL_BASE_INFO_ITEM_RIGHT = new String[]{
		"模块SOC", "最低电压", "最高电压", "模块总电压", "模块总电流"};
	public static final String[] BATTERY_CHARGING_SYSTEM_STATUS_ITEM = new String[]{
		"充电接口状态", "通信状态", "输入交流电压", "充电机过热", "充电电压", "充电电流"};
	public static final String[] BATTERY_FAULT_ITEM = new String[]{
		"与充电机通信", "动力电池连接", "极柱温度高", "加热状态", "均衡状态", "动力电池亏电",
		"高压漏电", /*"动力电池状态",*/ "充电电流", "放电电流", "电池组欠温", "电池组高温",
		"电池组过压", "电池组欠压", "单体电池过压", "单体电池欠压"};
	
	
	//=================================================================
	public static final int SHOW_INFO_MOTOR_MAIN = 0;
	public static final String[] MOTOR_MAIN_ITEM_LEFT = new String[]{
		"转向", "电机运行模式", "电机运行状态", "电机温度", "控制器温度"};
	public static final String[] MOTOR_MAIN_ITEM_RIGHT = new String[]{
		"电机转速", "电机转矩", "电机相电流", "电机相电压"};
	
	//=================================================================
	public static final int SHOW_INFO_AIR_CONDITION_MAIN = 0;
	public static final String[] AIR_CONDITION_MAIN_ITEM = new String[]{
		"空调运行模式", "空调温度", "风速"};
	
	//=================================================================
}