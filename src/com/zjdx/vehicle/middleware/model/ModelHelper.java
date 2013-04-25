package com.zjdx.vehicle.middleware.model;

import com.zjdx.vehicle.LogUtils;

import android.content.Context;


public class ModelHelper {
	private static final String TAG = "ModelHelper ";
	
	public static int getByteCount(int mask) {
		int n = 0;
		switch (mask & Item.MASK_NUM_TYPE) {
		case Item.MASK_SIGNED_BYTE:
		case Item.MASK_UNSIGNED_BYTE:
			n = 1;
			break;
		case Item.MASK_SIGNED_SHORT:
		case Item.MASK_UNSIGNED_SHORT:
			n = 2;
			break;
		case Item.MASK_SIGNED_INT:
			n = 4; 
		default:
			n = 0;
			break;
		}
		
		return n;
	}
	
	public static boolean isSigned(int mask) {
		boolean res = true;
		switch (mask & Item.MASK_NUM_TYPE) {		
		case Item.MASK_UNSIGNED_BYTE:		
		case Item.MASK_UNSIGNED_SHORT:
			res = false;
			break;
		default:
			res = true;
			break;
		}
		
		return res;
	}
	
	public static boolean isEnableRead(int mask) {
		return (mask & Item.MASK_ACCESS_TYPE) == Item.MASK_RDONLY;
	}
	
	public static boolean isEnableWrite(int mask) {
		return (mask & Item.MASK_ACCESS_TYPE) == Item.MASK_WRONLY;
	}
	
	public static int getByteCount(final Item[] itemArray) {
		int cnt = 0;
		for (Item item : itemArray) {
			cnt += ModelHelper.getByteCount(item.mask);
		}
		return cnt;
	}
	
	public static int getIndexById(int Id, final Item[] itemArray) {
		int index = -1;
		for (int i=0; i<itemArray.length; i++) {
			if (itemArray[i].id == Id) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	/** byte array is big end **/
	public static boolean Int2ByteArray(int value, byte[] byteArray, int pos,int len) {
		pos = pos + len - 1;	
		while (len > 0 && len <= 4){
			byteArray[pos--] = (byte) (value & 0xff);	
			value = value >> 8;	
			len --;
		}
		
		return true;		
	}
	
	/** byte array is big end **/
	public static int ByteArray2Int(byte[] byteArray, int pos,int len, boolean bSigned) {
		int value = 0;
		for (int i=0; i<len; i++) {		
			value = (value << 8) | (bSigned? byteArray[pos + i] : (byteArray[pos + i] & 0xFF));
			bSigned = false;
		}
		return value;
	}
	
	public static void IntArray2ByteArray(final Item[] itemArray, int[] intArray, byte[] byteArray) {
		int pos = 0;
		int len = 0;		
		for (int i=0,cnt=itemArray.length; i<cnt; i++) {
			len = ModelHelper.getByteCount(itemArray[i].mask);			
			ModelHelper.Int2ByteArray(intArray[i], byteArray, pos, len);
			pos += len;
		}		
	}
	
	public static void ByteArray2IntArray(final Item[] itemArray, int[] intArray, byte[] byteArray) {
		int pos = 0;
		int len = 0;
		boolean bSigned = false;
		for (int i=0,cnt=itemArray.length; i<cnt; i++) {
			len = ModelHelper.getByteCount(itemArray[i].mask);
			bSigned = ModelHelper.isSigned(itemArray[i].mask);
			intArray[i] = ModelHelper.ByteArray2Int(byteArray, pos, len, bSigned);			
			pos += len;
		}
	}
	
	public static String GetDescription(Context context, final Item[] itemArray, int index) {
		String desc = null;
		if (index >=0 && index < itemArray.length) {
			return context.getString(itemArray[index].resId);
		}
		return desc;
	}
	
	public static String[] GetDescriptions(Context context, final Item[] itemArray) {
		String[] array = new String[itemArray.length];
		for (int i=0; i<array.length; i++) {
			array[i] = context.getString(itemArray[i].resId);
		}
		
		return array;		
	}
	
	public static String[] GetTextString(final Item[] itemArray) {
		String[] array = new String[itemArray.length];
		for (int i=0; i<array.length; i++) {
			array[i] = itemArray[i].text;
		}
		return array;
	}
	
	public static void dumpByteArray(String tag, byte[] array) {
		if (array != null) {			
			for (int i=0; i<array.length; i++) {
				LogUtils.LOGD(TAG, tag + "dumpByteArray " + i + "/" + array.length
						+ " = " + (array[i] & 0xFF));
			}			
		}
	}
	
	public static void dumpIntArray(String tag, int[] array) {
		if (array != null) {			
			for (int i=0; i<array.length; i++) {
				LogUtils.LOGD(TAG, tag + "dumpIntArray " + i + "/" + array.length
						+ " = " + array[i]);
			}			
		}
	}
}
