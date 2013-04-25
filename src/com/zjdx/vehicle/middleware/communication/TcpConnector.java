package com.zjdx.vehicle.middleware.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.model.Defines;

import android.content.Context;


public class TcpConnector implements Connector {
	private static final String TAG = "TcpConnector ";

	private Socket mSocket = null;
	private NetConfigure mConfigure = null;
	private String mCfgFilePath = null;
	private byte[] mBuffer = null;
	
	public TcpConnector(Context context, String cfgPath) {
		mCfgFilePath = cfgPath;
		mBuffer = new byte[4096];
	}
	
	@Override
	public synchronized boolean open() {
		try {
			if (mSocket != null && mSocket.isClosed()) {
				mSocket = null;
			}
			
			if (mSocket == null) {
				LogUtils.LOGD(TAG, "open");
				mConfigure = new NetConfigure(mCfgFilePath);				
				mSocket = new Socket(mConfigure.getServerAddress(), mConfigure.getServerPort());
				mSocket.setKeepAlive(true);
				mSocket.setTcpNoDelay(true);
				mSocket.setSoTimeout(mConfigure.getTimeOut());				
			} 				
		} catch (UnknownHostException e) {
			mSocket = null;
//			e.printStackTrace();
		} catch (IOException e) {
			mSocket = null;
//			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public synchronized boolean read(MyDataPackage pkg) {
		boolean res = false;
		open();
		if (pkg != null && pkg.content != null && mSocket != null && mSocket.isConnected()) {
			try {
				InputStream in = mSocket.getInputStream();				
				pkg.count = in.read(pkg.content) / Defines.CANFRAME_LENGTH;
				if (pkg.count > 0) {
					LogUtils.LOGD(TAG, "receive data ok getData= " + pkg.count);					
					res = true;					
				}
			} catch (IOException e) {
//				e.printStackTrace();
			}				
		}
		return res;
	}

	@Override
	public synchronized boolean write(byte[] frame) {
		if (frame == null)
			return false;		
	
		boolean res = false;		
		open();
		if (mSocket != null && mSocket.isConnected()) {				
			try {
				OutputStream out = mSocket.getOutputStream();
				out.write(frame);
				out.flush();
				res = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}				
		}
		LogUtils.LOGD(TAG, "write frame res= " + res);
		
		return res;
	}

	@Override
	public synchronized boolean close() {
		if (mSocket != null) {
			try {
				LogUtils.LOGD(TAG, "close");
				mSocket.close();
				mSocket = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public synchronized String read() {
		String data = null;
		open();
		if (mSocket != null && mSocket.isConnected()) {
			try {
				InputStream in = mSocket.getInputStream();				
				int len = in.read(mBuffer);
				if (len > 0) {
					mBuffer[len] = '\0';
					data = new String(mBuffer);
					LogUtils.LOGD(TAG, "receive string ok:  " + data);										
				}
			} catch (IOException e) {
//				e.printStackTrace();
			}				
		}
		return data;
	}

	@Override
	public synchronized boolean write(String data) {
		if (data == null)
			return false;		
	
		boolean res = false;		
		open();
		if (mSocket != null && mSocket.isConnected()) {				
			try {
				OutputStream out = mSocket.getOutputStream();
				out.write(data.getBytes());
				out.flush();
				res = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}				
		}
		LogUtils.LOGD(TAG, "write string res= " + res);
		
		return res;
	}

	@Override
	public synchronized void checkState() {
		if (mSocket != null && mSocket.isConnected()) {	
			try {
				mSocket.setOOBInline(false);
				mSocket.sendUrgentData(0xFF);
			} catch (IOException e) {
				LogUtils.LOGE(TAG, "checkState failed: ");
				e.printStackTrace();
				close();
			}
		}
		
	}

}
