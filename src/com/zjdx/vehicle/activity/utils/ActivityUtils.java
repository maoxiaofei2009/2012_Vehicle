package com.zjdx.vehicle.activity.utils;


import android.content.Context;
import android.content.Intent;
import android.widget.RelativeLayout.LayoutParams;

public class ActivityUtils {
	public static int SCREEN_WIDTH 		= 800;
	public static int SCREEN_HEIGHT 	= 480;
	public static int SCREEN_DPI		= 240;
	public static int SCALE_BASE_X 		= 800;
	public static int SCALE_BASE_Y 		= 480;
	public static int SCALE_BASE_DPI	= 240;
	
	
	public static void showMap(Context context){
//		Intent intent = new Intent(Intent.ACTION_VIEW);
		Intent intent = new Intent(Intent.ACTION_MAIN);
				//, Uri.parse("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=39.940409,116.355257(Î÷Ö±ÃÅ)"));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		context.startActivity(intent);
	}
	
	public static void showFmRadio(Context context){
		
	}
	
	public static void scaleInit(int scrWidth, int scrHeight, int scrDPI, int baseWidth, int baseHeight, int baseDPI){		
		SCREEN_WIDTH = scrWidth;
		SCREEN_HEIGHT = scrHeight;
		SCREEN_DPI = scrDPI;
		SCALE_BASE_X = baseWidth;
		SCALE_BASE_Y = baseHeight;
		SCALE_BASE_DPI = baseDPI;
		
	}
	
	public static float getScaleX(){
		float scaleX = 1.0f * SCREEN_WIDTH / SCALE_BASE_X;
		return scaleX;
	}
	
	public static float getScaleY(){
		float scaleX = 1.0f * SCREEN_HEIGHT / SCALE_BASE_Y;
		return scaleX;
	}
	
	public static LayoutParams getLayoutParams(int width, int height){
		return new LayoutParams((int) (width * getScaleX()), (int) (height * getScaleY()));
	}
	
	public static int scaleX(int x){
		return (x * SCREEN_WIDTH / SCALE_BASE_X);
	}

	public static int scaleXWithDPI(int x){
		return (x * SCREEN_WIDTH * SCALE_BASE_DPI / SCALE_BASE_X / SCREEN_DPI);
	}
	
	public static int scaleY(int y){
		return (y * SCREEN_HEIGHT / SCALE_BASE_Y);
	}
	
	public static int scaleYWithDPI(int y){
		return (y * SCREEN_HEIGHT * SCALE_BASE_DPI / SCALE_BASE_Y / SCREEN_DPI);
	}
}