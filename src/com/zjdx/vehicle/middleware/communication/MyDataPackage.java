package com.zjdx.vehicle.middleware.communication;

public class MyDataPackage {
	public int count;
	public byte[] content;
	
	public MyDataPackage() {
		
	}
	
	public MyDataPackage(int count, byte[] content) {
		this.count = count;
		this.content = content;
	}
}
