package com.zjdx.vehicle.service.cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.os.Environment;

import com.zjdx.vehicle.LogUtils;


public class CloudHelper {
	private static final String TAG = "CloudHelper ";
	private static final String VID_TEST	= "VidTest";
	private static final String VID_NAME	= "VID:";
	
	public static final int InfoType_BasicInfo 		= 0x00;
	public static final int InfoType_FaultWarning	= 0x01;
	public static final int InfoType_StateInfo		= 0x02;
	public static final int InfoType_SyncCommand	= 0x04;
	public static final int InfoType_ConnectCommand	= 0x05;	
	public static final int InfoType_CanFrameCommand= 0x03;
	
	public static final int CtlObj_Light			= 0x00;
	public static final int CtlObj_AirCondition		= 0x01;
	public static final int CtlObj_Horn				= 0x02;
	public static final int CtlObj_Door				= 0x03;
	public static final int CtlObj_Video			= 0x04;
		
	public static final String VID = "VID";
	public static final String InfoType = "InfoType";
	public static final String CtlObj = "CtlObj";
		
	private static String sVID = null;
	public static final String getVID() {
		if (sVID == null) {
			File sdcard = Environment.getExternalStorageDirectory();
	    	File file = new File(sdcard, "vehicle_vid.cfg");  
	       	try {
	    		if (file.exists()) { 
	    			int index;
	    			String line;
		    	    BufferedReader br = new BufferedReader(new FileReader(file)); 
					while ((line = br.readLine()) != null) {
						LogUtils.LOGV(TAG, "get string: " + line);
						index = line.indexOf(VID_NAME);
						if (index != -1) {
							sVID = line.substring(index + VID_NAME.length()).trim();
							break;
						}
					}
	    		}
		    }catch (NullPointerException  e) {    		
			
	    	} catch (FileNotFoundException e) {
	    		
	    	} catch (IOException e) {
	    	    //You'll need to add proper error handling here
	    	} finally {
	    		LogUtils.LOGV(TAG, "VID: " + sVID);
	    	}
		}
		
		return (sVID != null)? sVID : VID_TEST;
	}
	
	public static final int  getTime() {
		return (int)System.currentTimeMillis();
	}
}
