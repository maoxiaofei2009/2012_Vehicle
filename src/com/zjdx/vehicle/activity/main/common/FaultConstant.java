package com.zjdx.vehicle.activity.main.common;

import com.zjdx.vehicle.middleware.model.WarningHelpler;



public class FaultConstant{
	private static final int CLOUMN_NAME = 0;
	private static final int CLOUMN_RESOLVE_PART = 1;
	private static final int CLOUMN_RESOLVE_WAY = 2;	
	private static final String[][] FAULT_ITEM = new String[][]{
		{"�����ͨѶ����", "���", 		"ֹͣ������ʻ"},
		{"����������ӱ���", "���", 		"���ܳ��"},
		{"�����¶ȸ߱���", 	"���", 		"ֹͣ������ʻ"},
		{"����״̬����", 	"���", 		"ֹͣ������ʻ"},
		{"����״̬����", 	"���", 		"ֹͣ������ʻ"},
		{"������ؿ���", 	"���", 		"ֹͣ��ʻ"},
		{"��ѹ©�籨��", 	"���", 		"ͣ�����©��Դ"},
		{"����������", 	"����", 	"ֹͣ���"},
		{"�ŵ��������", 	"��أ�������", 				"���ٵ���10km/h"},
		{"�����Ƿ�±���", 	"��ع���ϵͳ�Ӳ�������", 	"ͣ�����µ�ػ�������"},
		{"�������±���", 	"���", 						"ͣ��"},
		{"������ѹ����", 	"��أ�������������", 		"ֹͣ������ʻ���������"},
		{"�����Ƿѹ����", 	"��أ�������", 				"ͣ�����ص磬����ؿ��ѹ������������"},
		{"�����ع�ѹ����", "��أ�������������", 		"ֹͣ������ʻ���������"},
		{"������Ƿѹ����", "��أ�������", 				"ͣ�����ص磬����ؿ��ѹ������������"},
		{"��Եϵͳ��ⱨ��", "��أ�������", 				"ͣ�����©��Դ"},
		{"��������ж���ʾ", "���", 						"ͣ��"},
		{"������ع��ϱ���", "���",	 					"ͣ��"},
		{"������ȱ���", 	"���", 						"ͣ��"},
		{"������ٱ���", 	"���", 						"ͣ��"},
		{"ϵͳ���ϱ���", 	"����������������ϵͳ����ء���ع���ϵͳ", "ͣ��"},
		{"�ƶ�ϵͳ����", 	"��ɲ����ɲ", 				"ͣ��"},
		{"�綯�ÿյ�����", 	"�綯�ÿյ�", 				"�رյ綯�յ�"},
		{"DCDC����", 		"DCDC", 					"ͣ��"},
		{"�綯���ù���", 	"�綯����", 					"ͣ��"},
		{"�綯ת�����", 	"������", 					"ͣ��"},
		{"�������ȹ���", 	"����", 					"ֹͣ���"},
		{"����ͨ�Ŵ���", 	"��������ع���ϵͳ", 		"ֹͣ���"},
		{"��ȫ���ҹ���", 	"��ȫ����", 					"���ٵ���10km/h"}
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