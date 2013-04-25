package com.zjdx.vehicle.middleware.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zjdx.vehicle.R;


public class ItemMgr {
	private static final Item[] BatteryInfoIndex = new Item[] {
		new Item(Defines.NUM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.num, "NUM"),//��������
		new Item(Defines.COUNTER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.counter, "COUNTER"),//����ϵͳģ��������
		new Item(Defines.CHARGESTATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargestatus, "CHARGESTATUS"),//��س��״̬
		new Item(Defines.SOC, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.soc, "SOC"),//���غɵ�״̬
		new Item(Defines.CHARGETIME0, Item.MASK_UNSIGNED_SHORT| Item.MASK_RDONLY, 
			R.string.chargetime0, "CHARGETIME0"),//���س�����
	//	new Item(Defines.CHARGETIME1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.chargetime1, "CHARGETIME1"),//���س�����1
	};
	
	private static final Item[] BatteryTotalVAIndex = new Item[] {
		new Item(Defines.TOTAL_VOLAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.total_volage0, "TOTAL_VOLAGE0"),//������ص��ܵ�ѹ
	//	new Item(Defines.TOTAL_VOLAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.total_volage1, "TOTAL_VOLAGE1"),//������ص��ܵ�ѹ1
		new Item(Defines.TOTAL_CURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.total_current0, "TOTAL_CURRENT0"),//������ص��ܵ���
	//	new Item(Defines.TOTAL_CURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.total_current1, "TOTAL_CURRENT1"),//������ص��ܵ���1
	};
	
	private static final Item[] BatteryCellInfoIndex = new Item[] {
		new Item(Defines.CELLINFO_NUM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellinfo_num, "CELLINFO_NUM"),//����ģ���
		new Item(Defines.CELLINFO_COUNTER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellinfo_counter, "CELLINFO_COUNTER"),//ģ���ڵ�������
		new Item(Defines.SAMPLECOUNTER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.samplecounter, "SAMPLECOUNTER"),//ģ�����¶Ȳ�������
		new Item(Defines.CELLINFO_SOC, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellinfo_soc, "CELLINFO_SOC"),//ģ��SOC
		new Item(Defines.CELLINFO_VOLAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.cellinfo_volage0, "CELLINFO_VOLAGE0"),//ģ���ܵ�ѹ
	//	new Item(Defines.CELLINFO_VOLAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.cellinfo_volage1, "CELLINFO_VOLAGE1"),//ģ���ܵ�ѹ1
		new Item(Defines.CELLINFO_CURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.cellinfo_current0, "CELLINFO_CURRENT0"),//������ص��ܵ���
	//	new Item(Defines.CELLINFO_CURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.cellinfo_current1, "CELLINFO_CURRENT1"),//ģ���ܵ���1
	};
	
	private static final Item[] BatteryCellOtherInfoIndex = new Item[] {
		new Item(Defines.CELLOTHERINFO_NUM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellotherinfo_num, "CELLOTHERINFO_NUM"),//����ģ���
		new Item(Defines.TMIN, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.tmin, "TMIN"),//ģ��������¶�
		new Item(Defines.TMAX, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.tmax, "TMAX"),//ģ��������¶�
		new Item(Defines.VMIN0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.vmin0, "VMIN0"),//ģ���ڵ�����͵�ѹ
	//	new Item(Defines.VMIN1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.vmin1, "VMIN1"),//ģ���ڵ�����͵�ѹ1
		new Item(Defines.VMAX0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.vmax0, "VMAX0"),//ģ���ڵ�����͵�ѹ
	//	new Item(Defines.VMAX1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.vmax1, "VMAX1"),//ģ���ڵ�����͵�ѹ1
	};
	
	private static final Item[] BatteryErrorIndex = new Item[] {
		new Item(Defines.CHARGECONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargeconnection, "CHARGECONNECTION"),//�����ͨ�ű���
		new Item(Defines.BATTERCONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.batterconnection, "BATTERCONNECTION"),//�������ͨ�ű���
		new Item(Defines.HIGHTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.hightemp, "HIGHTEMP"),//�����¶ȸ߱���
		new Item(Defines.HEAT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.heat, "HEAT"),//����״̬����
		new Item(Defines.BALANCED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.balanced, "BALANCED"),//����״̬����
		new Item(Defines.LOWBATTERY, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.lowbattery, "LOWBATTERY"),//������ؿ���
		new Item(Defines.LEAKAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.leakage, "LEAKAGE"),//��ѹ©�籨��
		new Item(Defines.CHARGECURRENT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargecurrent, "CHARGECURRENT"),//����������
		new Item(Defines.DISCHARGECONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.dischargeconnection, "DISCHARGECONNECTION"),//�ŵ��������
		new Item(Defines.ARRAYLOWTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.arraylowtemp, "ARRAYLOWTEMP"),//�����Ƿ�±���
		new Item(Defines.ARRAYHIGHTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.arrayhightemp, "ARRAYHIGHTEMP"),//�������±���
		new Item(Defines.OVERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.overvoltage, "OVERVOLTAGE"),//������ѹ����
		new Item(Defines.UNDERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.undervoltage, "UNDERVOLTAGE"),//�����Ƿѹ����
		new Item(Defines.CELLOVERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellovervoltage, "CELLOVERVOLTAGE"),//�����ع�ѹ����
		new Item(Defines.CELLUNDERVOLTAGE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.cellundervoltage, "CELLUNDERVOLTAGE"),//������Ƿѹ����
	};
	
	private static final Item[] Motor_InfoIndex = new Item[] {
		new Item(Defines.STEER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.steer, "STEER"),//ת��
		new Item(Defines.MOTOR_HIGHTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motor_hightemp, "MOTOR_HIGHTEMP"),//�������ģʽ
		new Item(Defines.MOTOR_HEAT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motor_heat, "MOTOR_HEAT"),//�������״̬
		new Item(Defines.MOTORTEMPERTURE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motortemperture, "MOTORTEMPERTURE"),//����¶�
		new Item(Defines.CONTROLLERTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.controllertemp, "CONTROLLERTEMP"),//�������¶�
		new Item(Defines.ROTATESPEED0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.rotatespeed0, "ROTATESPEED0"),//���ת��
	//	new Item(Defines.ROTATESPEED1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.rotatespeed1, "ROTATESPEED1"),//���ת��
		new Item(Defines.TORQUE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.torque0, "TORQUE0"),//���ת��
	//	new Item(Defines.TORQUE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.torque1, "TORQUE1"),//���ת��
	};
	
	private static final Item[] Motor_Running_InfoIndex = new Item[] {
		new Item(Defines.PHASECURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.phasecurrent0, "PHASECURRENT0"),//��������
	//	new Item(Defines.PHASECURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.phasecurrent1, "PHASECURRENT1"),//��������1
		new Item(Defines.PHASEVOLTAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.phasevoltage0, "PHASEVOLTAGE0"),//������ѹ
	//	new Item(Defines.PHASEVOLTAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.phasevoltage1, "PHASEVOLTAGE1"),//������ѹ1
	};
	
	private static final Item[] BodyLightIndex = new Item[] {
		new Item(Defines.PARKING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.parking, "PARKING"),//פ��ָʾ
		new Item(Defines.REARFOGLIGHT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearfoglight, "REARFOGLIGHT"),//�����
		new Item(Defines.HEADFOGLIGHT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headfoglight, "HEADFOGLIGHT"),//ǰ���
		new Item(Defines.HIGHBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.highbeam, "HIGHBEAM"),//Զ���
		new Item(Defines.LOWBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.lowbeam, "LOWBEAM"),//�����
		new Item(Defines.RIGHTSTEERING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rightsteering, "RIGHTSTEERING"),//��ת��
		new Item(Defines.LEFTSTEERING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.leftsteering, "LEFTSTEERING"),//��ת��
	};
	
	private static final Item[] Vehicl_ErrorIndex = new Item[] {
		new Item(Defines.INSULATIONSYSTEM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.insulationsystem, "INSULATIONSYSTEM"),//��Եϵͳ��ⱨ��
		new Item(Defines.CHARGINGCONNECTION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargingconnection, "CHARGINGCONNECTION"),//���������ʾ
		new Item(Defines.CHARGING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.charging, "CHARGING"),//�����״̬��ʾ
		new Item(Defines.BATTERYOFF, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.batteryoff, "BATTERYOFF"),//��������ж���ʾ
		new Item(Defines.BATTERYERROR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.batteryerror, "BATTERYERROR"),//������ع��ϱ���
		new Item(Defines.MOTOROVERHEATING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motoroverheating, "MOTOROVERHEATING"),//������ȱ���
		new Item(Defines.MOTOROVERSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.motoroverspeed, "MOTOROVERSPEED"),//������ٱ���
		new Item(Defines.SYSTEMERROR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.systemerror, "SYSTEMERROR"),//ϵͳ���ϱ���
	};
	
	private static final Item[] Accessory_StatusIndex = new Item[] {
		new Item(Defines.BRAKESYSTEMFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.brakesystemfault, "BRAKESYSTEMFAULT"),//�ƶ�ϵͳ����
		new Item(Defines.SAFETYBELTFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.safetybeltfault, "SAFETYBELTFAULT"),//��ȫ��ָʾ��
		new Item(Defines.AIRBAGRESTRAINT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.airbagrestraint, "AIRBAGRESTRAINT"),//��ȫ����ָʾ��
		new Item(Defines.AIRCONDITION, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.aircondition, "AIRCONDITION"),//�綯�յ�״̬
		new Item(Defines.DCSTATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.dcstatus, "DCSTATUS"),//DC/DC״̬
		new Item(Defines.ELECTRICPUMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.electricpump, "ELECTRICPUMP"),//�綯����״̬
		new Item(Defines.ELECTRICSTEER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.electricsteer, "ELECTRICSTEER"),//�綯ת��״̬
		new Item(Defines.Wiper_Status, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.wiper_status, "Wiper_Status"),//���״̬
		new Item(Defines.ENGINEHOOD, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.enginehood, "ENGINEHOOD"),//ǰ����
		new Item(Defines.TRUNK, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.trunk, "TRUNK"),//�����
		new Item(Defines.FRONTRIGHTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontrightdoor, "FRONTRIGHTDOOR"),//��ǰ��״̬
		new Item(Defines.FRONTLEFTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontleftdoor, "FRONTLEFTDOOR"),//��ǰ��״̬
		new Item(Defines.REARRIGHTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearrightdoor, "REARRIGHTDOOR"),//�Һ���״̬
		new Item(Defines.REARLEFTDOOR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearleftdoor, "REARLEFTDOOR"),//�����״̬
		new Item(Defines.FRONTRIGHTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontrightwindow, "FRONTRIGHTWINDOW"),//��ǰ�ų���״̬
		new Item(Defines.FRONTLEFTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.frontleftwindow, "FRONTLEFTWINDOW"),//��ǰ�ų���״̬
		new Item(Defines.REARRIGHTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearrightwindow, "REARRIGHTWINDOW"),//�Һ��ų���״̬
		new Item(Defines.REARLEFTWINDOW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rearleftwindow, "REARLEFTWINDOW"),//����ų���״̬
	};
	
	private static final Item[] VehicleRunningInfoIndex = new Item[] {
		new Item(Defines.SYSTEMINTERLOCK, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.systeminterlock, "SYSTEMINTERLOCK"),//ϵͳ����
		new Item(Defines.VEHICLESTATE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.vehiclestate, "VEHICLESTATE"),//������ʻ״̬
	};
	
	private static final Item[] ControllerInfoIndex = new Item[] {
		new Item(Defines.ACCELERATORPEDAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.acceleratorpedal, "ACCELERATORPEDAL"),//����̤��״̬
		new Item(Defines.BRAKEPEDAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.brakepedal, "BRAKEPEDAL"),//ɲ��̤��״̬
		new Item(Defines.START, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.start, "START"),//һ��������ť״̬
		new Item(Defines.GEARS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.gears, "GEARS"),//��λ״̬
		new Item(Defines.KEYSTATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.keystatus, "KEYSTATUS"),//Կ�׿���״̬
		new Item(Defines.CONTROLLER_TRUNK, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.controller_trunk, "CONTROLLER_TRUNK"),//�����״̬
		new Item(Defines.ACCELERATORPEDALDEGREE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.acceleratorpedaldegree, "ACCELERATORPEDALDEGREE"),//���Ŵ�С���϶�ACCELERATORPEDALPOSITION��APP��
	};
	
	private static final Item[] ChargeInfoIndex = new Item[] {
		new Item(Defines.CHARGEINTERFACE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.chargeinterface, "CHARGEINTERFACE"),//���ӿ�״̬
		new Item(Defines.COMFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.comfault, "COMFAULT"),//ͨ�Ŵ���
		new Item(Defines.AVFAULT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.avfault, "AVFAULT"),//���뽻����ѹ������
		new Item(Defines.OVERHEATING, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.overheating, "OVERHEATING"),//�������ȴ���
		new Item(Defines.CHARGEVOLAGE0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.chargevolage0, "CHARGEVOLAGE0"),//����ѹ
	//	new Item(Defines.CHARGEVOLAGE1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.chargevolage1, "CHARGEVOLAGE1"),//����ѹ1
		new Item(Defines.CHARGECURRENT0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.chargecurrent0, "CHARGECURRENT0"),//������
	//	new Item(Defines.CHARGECURRENT1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.chargecurrent1, "CHARGECURRENT1"),//������1
	};
	
	private static final Item[] SpeedMileageInfoIndex = new Item[] {
		new Item(Defines.TOTALMILEAGE_MOST, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_most, "TOTALMILEAGE_MOST"),//�ܼ����ʮ��λ
		new Item(Defines.TOTALMILEAGE_MYRIABIT, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_myriabit, "TOTALMILEAGE_MYRIABIT"),//�ܼ������λ
		new Item(Defines.TOTALMILEAGE_THOUSANDS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_thousands, "TOTALMILEAGE_THOUSANDS"),//�ܼ����ǧλ
		new Item(Defines.TOTALMILEAGE_HUNDREDS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_hundreds, "TOTALMILEAGE_HUNDREDS"),//�ܼ���̰�λ
		new Item(Defines.TOTALMILEAGE_TENS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_tens, "TOTALMILEAGE_TENS"),//�ܼ����ʮλ
		new Item(Defines.TOTALMILEAGE_ONES, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.totalmileage_ones, "TOTALMILEAGE_ONES"),//�ܼ���̸�λ
		new Item(Defines.SMALLMILEAGE_HUNDREDS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_hundreds, "SMALLMILEAGE_HUNDREDS"),//С����̰�λ
		new Item(Defines.SMALLMILEAGE_TENS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_tens, "SMALLMILEAGE_TENS"),//С�����ʮλ
		new Item(Defines.SMALLMILEAGE_ONES, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_ones, "SMALLMILEAGE_ONES"),//С����̸�λ
		new Item(Defines.SMALLMILEAGE_DECIMALS, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.smallmileage_decimals, "SMALLMILEAGE_DECIMALS"),//С�����С��λ
		new Item(Defines.SPEED0, Item.MASK_UNSIGNED_SHORT | Item.MASK_RDONLY, 
			R.string.speed0, "SPEED0"),//����
	//	new Item(Defines.SPEED1, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
	//		R.string.speed1, "SPEED1"),//����
	};
	
	private static final Item[] AirConditon_InfoIndex = new Item[] {
		new Item(Defines.AIRCONMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.airconmode, "AIRCONMODE"),//�յ�����ģʽ
		new Item(Defines.AIRCONTEMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.aircontemp, "AIRCONTEMP"),//�յ��¶�
		new Item(Defines.AIRSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.airspeed, "AIRSPEED"),//����
	};
	
	private static final Item[] VehiclInfoToDASIndex = new Item[] {
		new Item(Defines.BRAKESIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.brakesignal, "BRAKESIGNAL"),//ɲ���ź�
		new Item(Defines.LEFTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.leftturn, "LEFTTURN"),//��ת����ź�
		new Item(Defines.RIGHTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.rightturn, "RIGHTTURN"),//��ת����ź�
		new Item(Defines.WIPERSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.wipersignal, "WIPERSIGNAL"),//����ź�
		new Item(Defines.HIGHBEAMSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.highbeamsignal, "HIGHBEAMSIGNAL"),//Զ����ź�
		new Item(Defines.SPEEDSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.speedsignal, "SPEEDSIGNAL"),//�ٶ��ź�
	};
	
	private static final Item[] WarningInfoFromDASIndex = new Item[] {
		new Item(Defines.NIGHTSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.nightsignal, "NIGHTSIGNAL"),//ҹ��ָʾ
		new Item(Defines.NIGHTFALLSIGNAL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.nightfallsignal, "NIGHTFALLSIGNAL"),//�ƻ�ָʾ
		new Item(Defines.SOUNDMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.soundmode, "SOUNDMODE"),//��������
		new Item(Defines.HIGHLOWBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.highlowbeam, "HIGHLOWBEAM"),//Զ/�����
		new Item(Defines.ZEROSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.zerospeed, "ZEROSPEED"),//���ٶ�
		new Item(Defines.RESERVED, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.reserved, "RESERVED"), 	
		new Item(Defines.HEADWAYMEASURE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headwaymeasure, "HEADWAYMEASURE"),//ʱ��ֵ
		new Item(Defines.HEADWAYVALID, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headwayvalid, "HEADWAYVALID"),//ʱ��ֵ�Ƿ���Ч
		new Item(Defines.ERRORCODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.errorcode, "ERRORCODE"),//�������
		new Item(Defines.ERRORVALID, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.errorvalid, "ERRORVALID"),//��������Ƿ���Ч
		new Item(Defines.FAILSAFE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.failsafe, "FAILSAFE"),//�ڲ�ʧЧ����ģʽָʾ��ͼ��ģ��������ͼ�񣬲�������������͸����
		new Item(Defines.ERROR, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.error, "ERROR"),//�ڲ�����ָʾ
		new Item(Defines.FCW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.fcw, "FCW"),//ǰ��ײԤ��
		new Item(Defines.RIGHTLDWOPEN, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.rightldwopen, "RIGHTLDWOPEN"),//����ƫ��Ԥ��
		new Item(Defines.LEFTLDWOPEN, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.leftldwopen, "LEFTLDWOPEN"),//����ƫ��Ԥ��
		new Item(Defines.LDWCLOSE, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.ldwclose, "LDWCLOSE"),//LDW����
		new Item(Defines.PASSERBYDANGER, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.passerbydanger, "PASSERBYDANGER"),//���˴���Σ������
		new Item(Defines.PASSERBYPCW, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.passerbypcw, "PASSERBYPCW"),//����ǰ��ײԤ��
		new Item(Defines.HEADWAYWARNINGLEVEL, Item.MASK_UNSIGNED_BYTE | Item.MASK_RDONLY, 
			R.string.headwaywarninglevel, "HEADWAYWARNINGLEVEL"),//���������趨
	};
	
	private static final Item[] LightCommandIndex = new Item[] {
		new Item(Defines.COMMAND_HIGHBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_highbeam, "COMMAND_HIGHBEAM"),//Զ���
		new Item(Defines.COMMAND_LOWBEAM, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_lowbeam, "COMMAND_LOWBEAM"),//�����
		new Item(Defines.COMMAND_RIGHTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_rightturn, "COMMAND_RIGHTTURN"),//��ת��
		new Item(Defines.COMMAND_LEFTTURN, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_leftturn, "COMMAND_LEFTTURN"),//��ת��
		new Item(Defines.COMMAND_PARKING, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_parking, "COMMAND_PARKING"),//פ��ָʾ
		new Item(Defines.COMMAND_REARFOGLAMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_rearfoglamp, "COMMAND_REARFOGLAMP"),//�����
		new Item(Defines.COMMAND_FRONTFOGLAMP, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_frontfoglamp, "COMMAND_FRONTFOGLAMP"),//ǰ���
	};
	
	private static final Item[] MotorHornCommandIndex = new Item[] {
		new Item(Defines.VALUE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.value, "VALUE"),//���ȿ���ģʽ
		new Item(Defines.SINGTIME, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.singtime, "SINGTIME"),//����ʱ����ʱ������ģʽʱ��Ч��
	};
	
	private static final Item[] AirConditionCommandIndex = new Item[] {
		new Item(Defines.STATUS, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.status, "STATUS"),//�յ�����ģʽ
		new Item(Defines.MODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.mode, "MODE"),//�յ�����ģʽ
		new Item(Defines.TEMPERTURE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.temperture, "TEMPERTURE"),//�յ��¶�
		new Item(Defines.COMMAND_AIRSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_airspeed, "COMMAND_AIRSPEED"),//����
	};
	
	private static final Item[] DoorLockCommandIndex = new Item[] {
		new Item(Defines.CONTROLMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.controlmode, "CONTROLMODE"),//��������ģʽ
		new Item(Defines.DOORTYPE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.doortype, "DOORTYPE"),//�ſ�����
	};
	
	private static final Item[] WiperCommandIndex = new Item[] {
		new Item(Defines.COMMAND_CONTROLMODE, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_controlmode, "COMMAND_CONTROLMODE"),//��ο���ģʽ
		new Item(Defines.COMMAND_WIPERSPEED, Item.MASK_UNSIGNED_BYTE | Item.MASK_WRONLY, 
			R.string.command_wiperspeed, "COMMAND_WIPERSPEED"),//����ٶ�
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
