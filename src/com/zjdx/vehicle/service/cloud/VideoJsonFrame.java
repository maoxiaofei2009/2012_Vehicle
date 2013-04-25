package com.zjdx.vehicle.service.cloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;
import com.zjdx.vehicle.service.cloud.video.VideoParameter;


public class VideoJsonFrame extends AbsDownLoadJsonFrame {	
	private static final int InfoType	= CloudHelper.InfoType_CanFrameCommand;
	private static final int CtlObj		= CloudHelper.CtlObj_Video;
	
	protected VideoJsonFrame(ICommand c, DatabaseHandler db) {
		super(c, db);
		// TODO Auto-generated constructor stub
	}
	
	static int getInfoType() {
		return InfoType;
	}
	
	static int getCtlObj() {
		return CtlObj;
	}

	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;		
		do {
			if (json == null)
				break;
			
			try {
				VideoParameter param = new VideoParameter();
				param.mVideoSwitch = json.getBoolean("VideoSwitch");
				param.mHost = json.getString("Host");
				param.mDoorCtlType = json.getInt("DoorCtlType");	
				res = setVideoParams(param);				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		} while(false);
		
		return res;
	}

}
