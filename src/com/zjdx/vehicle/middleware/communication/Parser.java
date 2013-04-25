package com.zjdx.vehicle.middleware.communication;


import com.zjdx.vehicle.middleware.model.Defines;
import com.zjdx.vehicle.middleware.model.Item;
import com.zjdx.vehicle.middleware.model.ItemMgr;
import com.zjdx.vehicle.middleware.model.ModelHelper;

public class Parser {	
	private static final String TAG = "Parser ";
	
	public static int[] decodePayload(int frameId, byte[] payload) {
		int[] intArray = null;
		do {	
			if (payload == null)
				break;
			
			final Item[] itemArray = ItemMgr.getItems(frameId);
			if (itemArray == null) {
				break;
			}
			
			byte[] byteArray = new byte[ModelHelper.getByteCount(itemArray)];			
			if (0 != JNI_VehicleMiddleWare.GetPayload(payload, frameId, byteArray)) {
				break;						
			}
//			ModelHelper.dumpByteArray("decodePayload " + frameId, byteArray);
			
			intArray = new int[itemArray.length];
			ModelHelper.ByteArray2IntArray(itemArray,intArray, byteArray);
//			ModelHelper.dumpIntArray("decodePayload", intArray);	
			
		}while (false);
	
		return intArray;
	}
	
	public static byte[] encodePayload(int frameId, int[] intArray) {
		byte[] payload = null;
		do {
			if (intArray == null)
				break;
			
			final Item[] itemArray = ItemMgr.getItems(frameId);
			if (itemArray == null) {
				break;
			}
//			ModelHelper.dumpIntArray("encodePayload", intArray);			
			byte[] byteArray = new byte[ModelHelper.getByteCount(itemArray)];			
			ModelHelper.IntArray2ByteArray(itemArray,intArray, byteArray);
//			ModelHelper.dumpByteArray("encodePayload " + frameId, byteArray);
			
			payload = new byte[Defines.PAYLOAD_LENGTH];
			if (0 != JNI_VehicleMiddleWare.SetPayload(payload, frameId, byteArray)) {
				payload = null;
				break;						
			}
			
		}while (false);		
		
		return payload;
	}
	
	public static  byte[] getFackFrame(int frameId, byte[] lastFrame) {	
		byte[] payload = new byte[Defines.PAYLOAD_LENGTH];
		byte[] frame = new byte[Defines.CANFRAME_LENGTH];
		for (int i=0; i<Defines.PAYLOAD_LENGTH; i++) {
			payload[i] = (byte)(lastFrame[i + 5] + 0x1);
		}
		JNI_VehicleMiddleWare.SetCanFrame(frame, frameId, payload);
		return frame;
	}
}
