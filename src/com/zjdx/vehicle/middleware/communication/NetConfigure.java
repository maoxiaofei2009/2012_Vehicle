package com.zjdx.vehicle.middleware.communication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.os.Environment;

import com.zjdx.vehicle.LogUtils;

public class NetConfigure {
	private static final String TAG = "NetConfigure "; 	
		
	private static final String SERVER_ADDRESS = "ServerAddress:";
	private static final String SERVER_PORT = "ServerPort:";
	private static final String LOCAL_PORT = "LocalPort:";
	private static final String TIMEOUT = "TimeOut:";
	private static final String MAX_DATAGRAM_LENGTH = "MaxDatagramLength:";
	
	private String mServerAddress = "192.168.43.10";// "192.168.1.88"; 
	private int	mServerPort = 8400;
	private int mLocalPort = 8400; /* between 0 and 65535 */
	private int mTimeOut = 20;
	private int mMaxDatagramLength = 13;
	
	private String mCfgFilePath = "vehicle.cfg";
	
	NetConfigure(String cfgPath) {
		if (cfgPath != null)
			mCfgFilePath = cfgPath;
		getNetConfigure();
	}
	
	String getServerAddress() {
		return mServerAddress;
	}
	
	int getServerPort() {
		return mServerPort;
	}
	
	int getLocalPort() {
		return mLocalPort;
	}
	
	int getTimeOut() {
		return mTimeOut;
	}	
	
	int getMaxDatagramLength() {
		return mMaxDatagramLength;
	}
	
	void getNetConfigure() {
		LogUtils.LOGV(TAG, "getNetConfigure " + mCfgFilePath +" <----");
    	File sdcard = Environment.getExternalStorageDirectory();
    	File file = new File(sdcard,mCfgFilePath);  
       	try {
    		if (file.exists()) { 
    			int index;
    			String line;
	    	    BufferedReader br = new BufferedReader(new FileReader(file)); 
				while ((line = br.readLine()) != null) {
					LogUtils.LOGV(TAG, "get string: " + line);
					index = line.indexOf(SERVER_ADDRESS);
					if (index != -1) {
						mServerAddress = line.substring(index + SERVER_ADDRESS.length()).trim();
						continue;
					}
				   
				   try {
					   index = line.indexOf(SERVER_PORT);
					   if (index != -1) {
						   mServerPort = Integer.valueOf(line.substring(index + SERVER_PORT.length()).trim());
						   continue;
					   }
				   } catch (NumberFormatException  e) {
					   
				   }
				   try {
					   index = line.indexOf(LOCAL_PORT);
					   if (index != -1) {
						   mLocalPort = Integer.valueOf(line.substring(index + LOCAL_PORT.length()).trim());
						   continue;
					   }
				   } catch (NumberFormatException  e) {
					   
				   }
				   
				   try {
					   index = line.indexOf(TIMEOUT);
					   if (index != -1) {
						   mTimeOut = Integer.valueOf(line.substring(index + TIMEOUT.length()).trim());
						   continue;
					   }
				   } catch (NumberFormatException  e) {
					   
				   }
				   
				   try {
					   index = line.indexOf(MAX_DATAGRAM_LENGTH);
					   if (index != -1) {
						   mMaxDatagramLength = Integer.valueOf(line.substring(index + MAX_DATAGRAM_LENGTH.length()).trim());
						   continue;
					   }
				   } catch (NumberFormatException  e) {
					   
				   }
				}
    		}
    	} catch (NullPointerException  e) {    		
			
    	} catch (FileNotFoundException e) {
    		
    	} catch (IOException e) {
    	    //You'll need to add proper error handling here
    	} finally {
    		LogUtils.LOGV(TAG, "serverAddress: " + mServerAddress);
    		LogUtils.LOGV(TAG, "serverPort: " + mServerPort);
    		LogUtils.LOGV(TAG, "localPort: " + mLocalPort);
    		LogUtils.LOGV(TAG, "mTimeOut: " + mTimeOut);
    		LogUtils.LOGV(TAG, "mMaxDatagramLength: " + mMaxDatagramLength);
    	}
       	LogUtils.LOGV(TAG, "getNetConfigure ---->");
    }
}
