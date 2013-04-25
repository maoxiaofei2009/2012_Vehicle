package com.zjdx.vehicle.activity.main.common;


public class CarStatusHelper{
	private static CarStatusHelper mInstance;
	public static CarStatusHelper getInstance(){
		if (mInstance == null){
			mInstance = new CarStatusHelper();
		}
		return mInstance;
	}
	
	private CarStatusHelper(){
		
	}
	
	//================ FAR/NEAR LAMP ==============
	private boolean mLampFarlightOpen = false;
	private boolean mLampNearlightOpen = false;
	public void setLampFarlightStatus(boolean open){
		mLampFarlightOpen = open;
	}
	
	public boolean getLampFarlightStatus(){
		return mLampFarlightOpen;
	}

	public void setLampNearlightStatus(boolean open){
		mLampNearlightOpen = open;
	}
	
	public boolean getLampNearlightStatus(){
		return mLampNearlightOpen;
	}
	
	//============== FLOG LAMP ==================
	private boolean mLampFrontflogOpen = false;
	private boolean mLampBehindflogOpen = false;
	public void setLampFrontflogStatus(boolean open){
		mLampFrontflogOpen = open;
	}
	
	public boolean getLampFrontflogStatus(){
		return mLampFrontflogOpen;
	}
	
	public void setLampBehindflogStatus(boolean open){
		mLampBehindflogOpen = open;
	}
	
	public boolean getLampBehindflogStatus(){
		return mLampBehindflogOpen;
	}
	
	//=============== FRONT/BEHIND DOOR============
	private boolean mCarFrontDoorOpen = false;
	private boolean mCarBehindDoorOpen = false;
	public void setCarFrontDoorStatus(boolean open){
		mCarFrontDoorOpen = open;
	}
	
	public boolean getCarFrontDoorStatus(){
		return mCarFrontDoorOpen;
	}
	
	public void setCarBehindDoorStatus(boolean open){
		mCarBehindDoorOpen = open;
	}
	
	public boolean getCarBehindDoorStatus(){
		return mCarBehindDoorOpen;
	}
	
	//================ RIGHT/LEFT DOOR ==============
	private boolean mCarFrontDoorRightOpen = false;
	private boolean mCarFrontDoorLeftOpen = false;
	public void setCarFrontDoorRightStatus(boolean open){
		mCarFrontDoorRightOpen = open;
	}
	
	public boolean getCarFrontDoorRightStatus(){
		return mCarFrontDoorRightOpen;
	}
	
	public void setCarFrontDoorLeftStatus(boolean open){
		mCarFrontDoorLeftOpen = open;
	}
	
	public boolean getCarFrontDoorLeftStatus(){
		return mCarFrontDoorLeftOpen;
	}
	
	//================================
	private float mRightWindowPercent = 0;
	private float mLeftWindowPercent = 0;
	public void setRightWindowPercent(float percent){
		mRightWindowPercent = percent;
	}
	
	public float getRightWindowPercent(){
		return mRightWindowPercent;
	}
	
	public void setLeftWindowPercent(float percent){
		mLeftWindowPercent = percent;
	}
	
	public float getLeftWindowPercent(){
		return mLeftWindowPercent;
	}
	
	//================RIGHT/LEFT ROTATE LAMP ==========
	private boolean mCarLampRotateRightOpen = false;
	private boolean mCarLampRotateLeftOpen = false;
	public void setCarLampRotateRightStatus(boolean open){
		mCarLampRotateRightOpen = open;
	}
	
	public boolean getCarLampRotateRightStatus(){
		return mCarLampRotateRightOpen;
	}
	
	public void setCarLampRotateLeftStatus(boolean open){
		mCarLampRotateLeftOpen = open;
	}
	
	public boolean getCarLampRotateLeftStatus(){
		return mCarLampRotateLeftOpen;
	}
	
	//============== CAR RAIN WIPER =============
	private int mCarRainWiperStage = RAIN_WIPER_CLOSE;
	private boolean mCarRainWiperOpen = false;
	public static final int RAIN_WIPER_CLOSE = 0;
	public static final int RAIN_WIPER_SLOW = 1;
	public static final int RAIN_WIPER_MIDDLE = 2;
	public static final int RAIN_WIPER_FAST = 3;
	public void setCarRainWiperStage(int stage){
		mCarRainWiperStage = stage;
	}
	
	public int getCarRainWiperStage(){
		return mCarRainWiperStage;
	}
	
	public void setCarRainWiperStatus(boolean open){
		mCarRainWiperOpen = open;
	}
	
	public boolean getCarRainWiperStatus(){
		return mCarRainWiperOpen;
	}
	
	//============== Air Conditioning ===========
	public int mAirConditionModeL = AIR_MODEL_AUTO;
	public static final int AIR_MODEL_COLD = 0;
	public static final int AIR_MODEL_HOT = 1;
	public static final int AIR_MODEL_AUTO = 2;
	public static final int AIR_MODEL_WET = 3;
	
	public void setAirConditionModel(int model){
		mAirConditionModeL = model;
	}
	
	public int getAirConditionModel(){
		return mAirConditionModeL;
	}
	
	public int mAirConditionWind = 0;
	public static final int AIR_WIND_STYLE_LOW = 0;
	public static final int AIR_WIND_STYLE_HIGH = 1;
	public void setAirConditionWind(int windStyle){
		mAirConditionWind = windStyle;
	}
	
	public int getAirConditionWind(){
		return mAirConditionWind;
	}
	
	public boolean mAirConditionOpen = false;
	public void setAirConditionStatus(boolean open){
		mAirConditionOpen = open;
	}
	
	public boolean getAirConditionStatus(){
		return mAirConditionOpen;
	}
	
	public int mAirConditionTemperature = 24;
	public void setAirConditionTemperature(int temperature){
		mAirConditionTemperature = temperature;
	}
	
	public int getAirConditionTemperature(){
		return mAirConditionTemperature;
	}
}