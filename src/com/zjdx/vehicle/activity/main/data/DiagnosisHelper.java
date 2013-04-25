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
		new DiagnosisItem("极柱温度高报警", 80, 3),
		new DiagnosisItem("加热状态报警", 30, 4),
		new DiagnosisItem("均衡状态报警", 20, 5),
		new DiagnosisItem("动力电池亏电", 45, 6),
		new DiagnosisItem("高压漏电报警", 90, 7),
		new DiagnosisItem("充电电流报警", 25, 8),
		new DiagnosisItem("放电电流报警", 30, 9),
		new DiagnosisItem("电池组欠温报警", 15, 10),
		new DiagnosisItem("电池组高温报警", 60, 11),
		new DiagnosisItem("电池组过压报警", 25, 12),
		new DiagnosisItem("电池组欠压报警", 25, 13),
		new DiagnosisItem("单体电池欠压报警", 30, 14),
		new DiagnosisItem("单体电池过压报警", 15, 15),
		new DiagnosisItem("电机过热报警", 10, 19),
		new DiagnosisItem("电机超速报警", 5, 20),
		new DiagnosisItem("制动系统故障", 100, 22),
		new DiagnosisItem("安全气囊故障", 30, 29),
		new DiagnosisItem("电动空调故障", 2, 23),
		new DiagnosisItem("DCDC故障", 50, 24),
		new DiagnosisItem("电动气泵故障", 50, 25),
		new DiagnosisItem("电动转向故障", 100, 26),
		new DiagnosisItem("雨刮故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("前舱门故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("后舱门故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("左前门故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("右前门故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("左前门车窗故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("右前门车窗故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("油门踏板故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("刹车踏板故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("档位杆故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("驾驶辅助系统故障", /* 暂时无定义故障项，直接显示正常 */ 0, 0),
		new DiagnosisItem("绝缘系统检测报警", 100, 16),
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
