package com.zjdx.vehicle.activity.utils;


public final class GestureUtils{
	private static final String TAG = "GestureUtils";
	public final static int DIRECTION_NONE			= 0;
	public final static int DIRECTION_UP			= 1;
	public final static int DIRECTION_DOWN			= -1;
	public final static int DIRECTION_LEFT			= 2;
	public final static int DIRECTION_UP_LEFT		= 3;
	
	public final static int DIRECTION_DOWN_LEFT	= -4;
	public final static int DIRECTION_RIGHT		= -2;
	public final static int DIRECTION_UP_RIGHT		= 4;
	public final static int DIRECTION_DOWN_RIGHT	=-3;
	
	private static final float MIN_MOVE_DISTANCE = ActivityUtils.scaleX(10);

	public static int getDirection(int x, int y, int refX, int refY ) {
		int direction = DIRECTION_NONE;
		int xOffset = y - refY;
		int yOffset = refX - x;
		if(Math.abs(xOffset)>=MIN_MOVE_DISTANCE || Math.abs(yOffset)>=MIN_MOVE_DISTANCE) {
			double d = Math.atan2(xOffset, yOffset);
			if(d<=(Math.PI /8) && d> (-Math.PI /8)){
				direction = DIRECTION_LEFT;
			}else if(d<=(Math.PI *3 /8) && d> (Math.PI /8)){
				direction = DIRECTION_DOWN_LEFT;
			}else if(d<=(Math.PI *5 /8) && d> (Math.PI *3/8)){
				direction = DIRECTION_DOWN;
			}else if(d<=(Math.PI *7 /8) && d> (Math.PI *5/8)){
				direction = DIRECTION_DOWN_RIGHT;
			}else if(d<=(Math.PI *8 /8) && d> (Math.PI *7/8)){
				direction = DIRECTION_RIGHT;
			}else if(d<=(- Math.PI *7 /8) && d>= (-Math.PI *8/8)){
				direction = DIRECTION_RIGHT;	
			}else if(d<=(- Math.PI *5 /8) && d> (-Math.PI *7/8)){
				direction = DIRECTION_UP_RIGHT;
			}else if(d<=(- Math.PI *3 /8) && d> (-Math.PI *5/8)){
				direction = DIRECTION_UP;
			}else if(d<=(- Math.PI *1 /8) && d> (-Math.PI *3/8)){
				direction = DIRECTION_UP_LEFT;
			}
		}
		return direction;
	}
}
