package com.zjdx.vehicle.middleware.model;

public class Item {
	public static final int MASK_SIGNED_BYTE 	= 0x00;
	public static final int MASK_UNSIGNED_BYTE 	= 0x01;
	public static final int MASK_SIGNED_SHORT	= 0x02;
	public static final int MASK_UNSIGNED_SHORT = 0x03;
	public static final int MASK_SIGNED_INT		= 0x04;
	public static final int MASK_NUM_TYPE		= 0x0F;
	
	public static final int MASK_RDONLY			= 0x10;
	public static final int MASK_WRONLY			= 0x20;
	public static final int MASK_RDWR			= 0x30;
	public static final int MASK_ACCESS_TYPE	= 0xF0;
	
	public final int id;
	public final int mask;	
	public final int resId;
	public final String text;
	
	public Item(int id, int mask, int resId, String text) {
		this.id = id;
		this.mask = mask;
		this.resId = resId;
		this.text = text;	
	}
}
