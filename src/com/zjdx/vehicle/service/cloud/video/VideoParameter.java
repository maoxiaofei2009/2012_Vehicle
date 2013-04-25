package com.zjdx.vehicle.service.cloud.video;

public class VideoParameter {
	private static final String TAG = "VideoParameter ";
	public boolean mVideoSwitch = false;
	public String mHost = null;
	public int mDoorCtlType = 0;
	public int mID = 0x01;
	
	public VideoParameter() {
		
	}
	
	public String toString() {
		return new String(TAG + ": " + mVideoSwitch + "; " + mHost + "; " + mDoorCtlType + "; ");
	}
}
