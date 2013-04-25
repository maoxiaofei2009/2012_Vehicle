package com.zjdx.vehicle.middleware.communication;

public interface Connector {
	public boolean open();
	public boolean read(MyDataPackage pkg);
	public boolean write(byte[] frame);
	public boolean close();
	
	public String read();
	public boolean write(String data);
	public void checkState();
}
