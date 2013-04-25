package com.zjdx.vehicle.service.cloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.zjdx.vehicle.middleware.db.DatabaseHandler;


public class SyncJsonFrame extends AbsDownLoadJsonFrame {
	private static final int InfoType	= CloudHelper.InfoType_SyncCommand;
	
	protected SyncJsonFrame(ICommand c, DatabaseHandler db) {
		super(c, db);
		// TODO Auto-generated constructor stub
	}
	
	static int getInfoType() {
		return InfoType;
	}

	@Override
	protected boolean onReceiveJsonFrame(JSONObject json) {
		boolean res = false;		
		do {
			if (json == null)
				break;
			
			try {
				int time = json.getInt("Time");	// Ê±¼ä
				res = syncJsonFrames(time);				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		} while(false);
		
		return res;
	}
	
	

}
