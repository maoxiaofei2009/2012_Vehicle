package com.zjdx.vehicle.activity.main.ui.bottom;


public class FaultListInfo{
	public String faultName;
	public String faultPart;
	public String faultWay;
	
	public FaultListInfo() {
		
	}
	
	public FaultListInfo(String name, String part, String way) {
		faultName = name;
		faultPart = part;
		faultWay = way;
	}	
}