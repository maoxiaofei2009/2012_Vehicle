package com.zjdx.vehicle.middleware.communication;

import com.zjdx.vehicle.middleware.model.Defines;

public class JNI_VehicleMiddleWare {
	public native static int GetPayload(byte[] payload, int identity, byte[] values);
	public native static int SetPayload(byte[] payload, int identity, byte[] values);
	
//	public static int GetPayload(byte[] payload, int identity, byte[] values) {
//		return -1;
//	}
//	public static int SetPayload(byte[] payload, int identity, byte[] values) {
//		return -1;
//	}
	
	public static int GetCanFrame(byte[] frame, int pos, int[] identity, byte[] payload)	{
		if (frame == null || (frame.length - pos) < Defines.CANFRAME_LENGTH
			|| payload == null || payload.length != Defines.PAYLOAD_LENGTH
			|| identity == null) {
			return -1;
		}
		
		if (frame[pos] != 0x08)
			return -1;
		
		identity[0] = ((frame[pos + 3] & 0x07) << 8)|(frame[pos + 4] & 0xFF);
		System.arraycopy(frame, pos + 5, payload, 0, Defines.PAYLOAD_LENGTH);				
		
		return 0;
	}
	
	public static int SetCanFrame(byte[] frame, int identity, byte[] payload) {
		if (frame == null || frame.length != Defines.CANFRAME_LENGTH
			|| payload == null || payload.length != Defines.PAYLOAD_LENGTH) {
				
			return -1;
		}
		frame[0] = 0x08;
		frame[1] = 0x00;
		frame[2] = 0x00;
		frame[3] = (byte)((identity & 0x0700) >> 8);
		frame[4] = (byte)(identity & 0xFF);
		System.arraycopy(payload, 0, frame, 5, Defines.PAYLOAD_LENGTH);		
		
		return 0;
	}
}
