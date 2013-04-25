package com.zjdx.vehicle.activity.main.ui.bottom;


public class DiagnosisListInfo{
	public String name;
	public boolean safe;
	public int weight;
	
	public DiagnosisListInfo() {
		
	}
	
	public DiagnosisListInfo(String name, int weight, boolean safe) {
		this.name = name;
		this.safe = safe;
		this.weight = weight;
	}
}