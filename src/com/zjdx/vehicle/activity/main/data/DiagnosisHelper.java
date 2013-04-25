package com.zjdx.vehicle.activity.main.data;

import java.util.ArrayList;

import com.zjdx.vehicle.activity.main.ui.bottom.DiagnosisListInfo;

public class DiagnosisHelper {
	public static class DiagnosisItem {
		public String name;
		public int weight;
		public int faultId;
		
		public DiagnosisItem(String name, int weight, int faultId) {
			this.name = name;
			this.weight = weight;
			this.faultId = faultId;			
		}
	}
	
	public static final DiagnosisItem[] DIAGONSIS_ARRAY = new DiagnosisItem[] {
		new DiagnosisItem("�����¶ȸ߱���", 80, 3),
		new DiagnosisItem("����״̬����", 30, 4),
		new DiagnosisItem("����״̬����", 20, 5),
		new DiagnosisItem("������ؿ���", 45, 6),
		new DiagnosisItem("��ѹ©�籨��", 90, 7),
		new DiagnosisItem("����������", 25, 8),
		new DiagnosisItem("�ŵ��������", 30, 9),
		new DiagnosisItem("�����Ƿ�±���", 15, 10),
		new DiagnosisItem("�������±���", 60, 11),
		new DiagnosisItem("������ѹ����", 25, 12),
		new DiagnosisItem("�����Ƿѹ����", 25, 13),
		new DiagnosisItem("������Ƿѹ����", 30, 14),
		new DiagnosisItem("�����ع�ѹ����", 15, 15),
		new DiagnosisItem("������ȱ���", 10, 19),
		new DiagnosisItem("������ٱ���", 5, 20),
		new DiagnosisItem("�ƶ�ϵͳ����", 100, 22),
		new DiagnosisItem("��ȫ���ҹ���", 30, 29),
		new DiagnosisItem("�綯�յ�����", 2, 23),
		new DiagnosisItem("DCDC����", 50, 24),
		new DiagnosisItem("�綯���ù���", 50, 25),
		new DiagnosisItem("�綯ת�����", 100, 26),
		new DiagnosisItem("��ι���", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("ǰ���Ź���", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("����Ź���", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��ǰ�Ź���", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��ǰ�Ź���", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��ǰ�ų�������", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��ǰ�ų�������", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("����̤�����", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("ɲ��̤�����", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��λ�˹���", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��ʻ����ϵͳ����", /* ��ʱ�޶�������ֱ����ʾ���� */ 0, 0),
		new DiagnosisItem("��Եϵͳ��ⱨ��", 100, 16),
	};
		
	public static ArrayList<DiagnosisListInfo> getDiagonsisList(boolean[] faultArray) {
		ArrayList<DiagnosisListInfo> list = new ArrayList<DiagnosisListInfo>();
		for (DiagnosisItem item : DIAGONSIS_ARRAY) {
			if (item == null) continue;
			list.add(new DiagnosisListInfo(item.name,item.weight, !faultArray[item.faultId]));
		}
		return list;
	}
}
