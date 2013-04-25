package com.zjdx.vehicle.activity.main.ui.bottom;


public class MaintenanceInfo{
	public int realBrakeMiles;
	public int realCheckMiles;
	
	public MaintenanceInfo() {
		
	}
	
	public MaintenanceInfo(int brakeMiles, int checkMiles) {
		this.realBrakeMiles = brakeMiles;
		this.realCheckMiles = checkMiles;
	}
}