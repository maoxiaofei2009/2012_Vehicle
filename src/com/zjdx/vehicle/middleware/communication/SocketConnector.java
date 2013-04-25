package com.zjdx.vehicle.middleware.communication;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.model.Defines;

import android.content.Context;


public class SocketConnector implements Connector {
	private static final String TAG = "SocketConnector ";	
	private NetConfigure mConfigure = null;
	private DatagramSocket mDS = null;
	private String mCfgFilePath = null;
	
	public SocketConnector(Context context, String cfgPath) {	
		mCfgFilePath = cfgPath;
	}

	@Override
	public synchronized boolean open() {			
		try {
			if (mDS == null) {
				LogUtils.LOGD(TAG, "open");
				mConfigure = new NetConfigure(mCfgFilePath);			
				mDS = new DatagramSocket(mConfigure.getLocalPort());				
				mDS.setSoTimeout(mConfigure.getTimeOut());
//				mDS.setReceiveBufferSize(mConfigure.getMaxDatagramLength());
				mDS.setSendBufferSize(mConfigure.getMaxDatagramLength());
				
			} 
		}catch (SocketException e) {
			LogUtils.LOGD(TAG, "open failed SocketException");
			mDS = null;
//			e.printStackTrace();				
		}
		
		return true;
	}

	@Override
	public synchronized boolean read(MyDataPackage pkg){
		if (pkg == null || pkg.content == null)
			return false;
				
		boolean res = false;
		pkg.count = 0;
		try {
			open();
			if (mDS != null) {
//				LogUtils.LOGD(TAG,"receive port: " + mDS.getLocalPort()
//						+ " getLocalAddress " + mDS.getLocalAddress()
//						+ " getLocalSocketAddress " + mDS.getLocalSocketAddress());
				DatagramPacket dp = new DatagramPacket(pkg.content, pkg.content.length);			
				mDS.receive(dp);
				pkg.count = dp.getLength() / Defines.CANFRAME_LENGTH;
				if (pkg.count > 0) {
					LogUtils.LOGD(TAG, "receive data ok getData= " + pkg.count);					
					res = true;					
				}				
			}
		} catch (IOException e) {
//			LogUtils.LOGD(TAG, "receive failed IOException");
//			e.printStackTrace();
		} catch (Exception e) {
//			LogUtils.LOGD(TAG, "receive failed Exception");
//			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public synchronized boolean write(byte[] frame) {
		if (frame == null)
			return false;		
	
		boolean res = false;
		try {
			open();
			if (mDS != null) {				
				InetAddress serverAddr = InetAddress.getByName(mConfigure.getServerAddress());
		        DatagramPacket dp = new DatagramPacket(frame, frame.length, serverAddr, mConfigure.getServerPort());
		        mDS.send(dp);
		        res = true;
			}
		} catch (UnknownHostException e) {
			LogUtils.LOGD(TAG, "send failed UnknownHostException");
//			e.printStackTrace();
		} catch (IOException e) {
			LogUtils.LOGD(TAG, "send failed IOException");
//			e.printStackTrace();
		} catch (Exception e) {
			LogUtils.LOGD(TAG, "send failed Exception");
//			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public synchronized boolean close() {
		LogUtils.LOGD(TAG, "close");		
		if (mDS != null) {
			mDS.close();
			mDS = null;
		}
		return true;
	}

	@Override
	public String read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean write(String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkState() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
