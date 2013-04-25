package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.main.ui.view.Battery;
import com.zjdx.vehicle.activity.main.ui.view.CurrentView;
import com.zjdx.vehicle.activity.main.ui.view.VoltageView;
import com.zjdx.vehicle.activity.utils.ActivityUtils;


public class ContentAreaRight{
	private static final String TAG = "ContentAreaLeft";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private TextView mTextSoc;
	private TextView mTextBatteryPercent;
	private Battery  mImgBatteryPercent;
	private ImageView  mImgBattery;
	private ImageView  mImgBatteryPower;
	private ImageView  mImgBatteryShortage;
	
	private TextView mCurrentVaule;
	private CurrentView mCurrentImg;
	private TextView mCurrentUnit;
	
	private TextView mFlightMileageTitle;
	private TextView mFlightMileageValue;
	private TextView mFlightMileageUnit;
	
	private TextView mTotalMileageTitle;
	private TextView mTotalMileageValue;
	private TextView mTotalMileageUnit;
	
	private TextView mVoltageVaule;
	private VoltageView mVoltageImg;
	private TextView mVoltageUnit;
	
	public ContentAreaRight(Context context){
		mContext = context;
		
		init();
		initStatus();
	}
	
	public void init(){
		mParentLayout = (RelativeLayout) 
				((Activity)mContext).findViewById(R.id.content_right);
		if (mParentLayout != null){
			initCarStalls();
			initBattery();
			initCurrent();
			initMileage();
			initVoltage();
		}
	}
	
	private void initStatus(){
		updateCarStalls(1);
		updateBatteryVoltage(10);
		updateBatteryStatus(false);
		updateBatteryPower(false);
		updateBatteryShortage(false);
		updateCurrent(13.5f);
		updateVoltage(154);
		updateFlightMileage(3857);
		updateTotalMileage(99999);
	}
	
	private TextView mD_StallsText;
	private TextView mN_StallsText;
	private void initCarStalls(){
		mD_StallsText = new TextView(mContext);
		mD_StallsText.setTypeface(Typeface.DEFAULT_BOLD);
		mD_StallsText.setId(mD_StallsText.hashCode());
		//mTextSoc.setBackgroundColor(Color.RED);
		mD_StallsText.setTextSize(mContext.getResources().getDimension(R.dimen.main_car_stalls));
		mD_StallsText.setText("D");
		LayoutParams params = ActivityUtils.getLayoutParams(40, 40);
        params.leftMargin = ActivityUtils.scaleX(25);
        params.topMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParentLayout.addView(mD_StallsText, params);
        
        mN_StallsText = new TextView(mContext);
        mN_StallsText.setTypeface(Typeface.DEFAULT_BOLD);
        mN_StallsText.setId(mN_StallsText.hashCode());
		//mTextSoc.setBackgroundColor(Color.RED);
        mN_StallsText.setTextSize(mContext.getResources().getDimension(R.dimen.main_car_stalls));
		mN_StallsText.setText("N");
		params = ActivityUtils.getLayoutParams(40, 40);
        params.leftMargin = ActivityUtils.scaleX(115);
        params.topMargin = ActivityUtils.scaleY(30);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParentLayout.addView(mN_StallsText, params);
	}
	
	private void initBattery(){
		LayoutParams params = new LayoutParams(156, 248);
        params.leftMargin = ActivityUtils.scaleX(260);
        params.topMargin = ActivityUtils.scaleY(180);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mImgBatteryPercent = new Battery(mContext);
        mImgBatteryPercent.setBattery(1.0f);
        mParentLayout.addView(mImgBatteryPercent, params); 
        
		mTextSoc = new TextView(mContext);
		mTextSoc.setGravity(Gravity.CENTER);
		mTextSoc.setTypeface(Typeface.DEFAULT_BOLD);
		mTextSoc.setId(mTextSoc.hashCode());
		//mTextSoc.setBackgroundColor(Color.RED);
		mTextSoc.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mTextSoc.setText("SOC");
        params = ActivityUtils.getLayoutParams(80, 40);
        params.leftMargin = ActivityUtils.scaleX(230);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.topMargin = ActivityUtils.scaleY(130);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParentLayout.addView(mTextSoc, params);
        
        
        mTextBatteryPercent = new TextView(mContext);
        mTextBatteryPercent.setGravity(Gravity.CENTER);
        mTextBatteryPercent.setTypeface(Typeface.DEFAULT_BOLD);
        mTextBatteryPercent.setId(mTextBatteryPercent.hashCode());
        //mTextBatteryPercent.setBackgroundColor(Color.RED);
        mTextBatteryPercent.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
        mTextBatteryPercent.setText("70%");
        params = ActivityUtils.getLayoutParams(80, 40);
        params.leftMargin = ActivityUtils.scaleX(250);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.BELOW, mTextSoc.getId());
        mParentLayout.addView(mTextBatteryPercent, params);
        
        mImgBattery = new ImageView(mContext);
        mImgBattery.setId(mImgBattery.hashCode());
        params = ActivityUtils.getLayoutParams(60, 60);
        params.leftMargin = ActivityUtils.scaleX(250);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mTextBatteryPercent.getId());
        mParentLayout.addView(mImgBattery, params);
        
        mImgBatteryPower = new ImageView(mContext);
        mImgBatteryPower.setId(mImgBatteryPower.hashCode());
        params = ActivityUtils.getLayoutParams(60, 60);
        params.leftMargin = ActivityUtils.scaleX(250);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mImgBattery.getId());
        mParentLayout.addView(mImgBatteryPower, params);
        
        mImgBatteryShortage = new ImageView(mContext);
        mImgBatteryShortage.setId(mImgBatteryShortage.hashCode());
        params = ActivityUtils.getLayoutParams(60, 60);
        params.leftMargin = ActivityUtils.scaleX(250);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mImgBatteryPower.getId());
        mParentLayout.addView(mImgBatteryShortage, params);
	}
	
	private void initCurrent(){
		mCurrentVaule = new TextView(mContext);
		mCurrentVaule.setGravity(Gravity.CENTER);
		mCurrentVaule.setId(mCurrentVaule.hashCode());
		//mCurrentVaule.setBackgroundColor(Color.RED);
		mCurrentVaule.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mCurrentVaule.setText("13.5");
        LayoutParams params = ActivityUtils.getLayoutParams(80, 40);
        params.leftMargin = ActivityUtils.scaleX(25);
        params.topMargin = ActivityUtils.scaleY(-20);
		params.addRule(RelativeLayout.BELOW, mTextSoc.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mParentLayout.addView(mCurrentVaule, params);
        
        mCurrentImg = new CurrentView(mContext);
        mCurrentImg.setId(mCurrentImg.hashCode());
        mCurrentImg.setPercent(0.35f);
        params = ActivityUtils.getLayoutParams(CurrentView.getLayoutWidth(), CurrentView.getLayoutHeight());
        //params.leftMargin = ActivityUtils.scaleX(0);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mCurrentVaule.getId());
        mParentLayout.addView(mCurrentImg, params);
        
		mCurrentUnit = new TextView(mContext);
		mCurrentUnit.setGravity(Gravity.CENTER);
		mCurrentUnit.setId(mCurrentUnit.hashCode());
		//mCurrentVaule.setBackgroundColor(Color.RED);
		mCurrentUnit.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mCurrentUnit.setText("A");
        params = ActivityUtils.getLayoutParams(80, 40);
        params.leftMargin = ActivityUtils.scaleX(25);
		params.addRule(RelativeLayout.BELOW, mCurrentImg.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mParentLayout.addView(mCurrentUnit, params);
	}
	
	private void initMileage(){
		mFlightMileageTitle = new TextView(mContext);
		mFlightMileageTitle.setGravity(Gravity.CENTER);
		mFlightMileageTitle.setId(mFlightMileageTitle.hashCode());
		//mFlightMileageTitle.setBackgroundColor(Color.RED);
		mFlightMileageTitle.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		mFlightMileageTitle.setText("续航里程");
        LayoutParams params = ActivityUtils.getLayoutParams(120, 28);
        params.topMargin = ActivityUtils.scaleY(70);
		params.addRule(RelativeLayout.BELOW, mTextSoc.getId());
        params.leftMargin = ActivityUtils.scaleX(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCurrentVaule.getId());;
        mParentLayout.addView(mFlightMileageTitle, params);
        
        mFlightMileageValue = new TextView(mContext);
        mFlightMileageValue.setGravity(Gravity.CENTER);
        mFlightMileageValue.setTypeface(Typeface.DEFAULT_BOLD);
        mFlightMileageValue.setId(mFlightMileageValue.hashCode());
        //mFlightMileageValue.setBackgroundColor(Color.RED);
        mFlightMileageValue.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
        mFlightMileageValue.setText("3856");
        params = ActivityUtils.getLayoutParams(120, 28);
		params.addRule(RelativeLayout.BELOW, mFlightMileageTitle.getId());
        params.leftMargin = ActivityUtils.scaleX(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCurrentVaule.getId());;
        mParentLayout.addView(mFlightMileageValue, params);
        
        mFlightMileageUnit = new TextView(mContext);
        mFlightMileageUnit.setGravity(Gravity.RIGHT);
        mFlightMileageUnit.setId(mFlightMileageUnit.hashCode());
        //mFlightMileageUnit.setBackgroundColor(Color.RED);
        mFlightMileageUnit.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
        mFlightMileageUnit.setText("Km");
        params = ActivityUtils.getLayoutParams(120, 28);
		params.addRule(RelativeLayout.BELOW, mFlightMileageValue.getId());
        params.leftMargin = ActivityUtils.scaleX(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCurrentVaule.getId());;
        mParentLayout.addView(mFlightMileageUnit, params);
        
        mTotalMileageTitle = new TextView(mContext);
        mTotalMileageTitle.setGravity(Gravity.CENTER);
        mTotalMileageTitle.setId(mTotalMileageTitle.hashCode());
        //mTotalMileageTitle.setBackgroundColor(Color.RED);
        mTotalMileageTitle.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
        mTotalMileageTitle.setText("总里程");
        params = ActivityUtils.getLayoutParams(120, 28);
		params.addRule(RelativeLayout.BELOW, mFlightMileageUnit.getId());
        params.leftMargin = ActivityUtils.scaleX(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCurrentVaule.getId());;
        mParentLayout.addView(mTotalMileageTitle, params);
        
        mTotalMileageValue = new TextView(mContext);
        mTotalMileageValue.setGravity(Gravity.CENTER);
        mTotalMileageValue.setTypeface(Typeface.DEFAULT_BOLD);
        mTotalMileageValue.setId(mTotalMileageValue.hashCode());
        //mTotalMileageValue.setBackgroundColor(Color.RED);
        mTotalMileageValue.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
        mTotalMileageValue.setText("999999");
        params = ActivityUtils.getLayoutParams(120, 28);
		params.addRule(RelativeLayout.BELOW, mTotalMileageTitle.getId());
        params.leftMargin = ActivityUtils.scaleX(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCurrentVaule.getId());;
        mParentLayout.addView(mTotalMileageValue, params);
        
        mTotalMileageUnit = new TextView(mContext);
        mTotalMileageUnit.setGravity(Gravity.RIGHT);
        mTotalMileageUnit.setId(mTotalMileageUnit.hashCode());
        //mTotalMileageUnit.setBackgroundColor(Color.RED);
        mTotalMileageUnit.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
        mTotalMileageUnit.setText("Km");
        params = ActivityUtils.getLayoutParams(120, 28);
		params.addRule(RelativeLayout.BELOW, mTotalMileageValue.getId());
        params.leftMargin = ActivityUtils.scaleX(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCurrentVaule.getId());;
        mParentLayout.addView(mTotalMileageUnit, params);
	}
	
	private void initVoltage(){
		mVoltageVaule = new TextView(mContext);
		mVoltageVaule.setGravity(Gravity.CENTER);
		mVoltageVaule.setId(mVoltageVaule.hashCode());
		//mCurrentVaule.setBackgroundColor(Color.RED);
		mVoltageVaule.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mVoltageVaule.setText("145");
        LayoutParams params = ActivityUtils.getLayoutParams(80, 40);
        params.leftMargin = ActivityUtils.scaleX(125);
        params.topMargin = ActivityUtils.scaleY(-20);
		params.addRule(RelativeLayout.BELOW, mTextSoc.getId());
        params.addRule(RelativeLayout.RIGHT_OF, mTextSoc.getId());
        mParentLayout.addView(mVoltageVaule, params);
        
        mVoltageImg = new VoltageView(mContext);
        mVoltageImg.setId(mVoltageImg.hashCode());
        mVoltageImg.setPercent(0.85f);
        params = ActivityUtils.getLayoutParams(136, 219);
        params.leftMargin = ActivityUtils.scaleX(105);
        params.addRule(RelativeLayout.RIGHT_OF, mTextSoc.getId());
        params.addRule(RelativeLayout.BELOW, mVoltageVaule.getId());
        mParentLayout.addView(mVoltageImg, params);
        
		mVoltageUnit = new TextView(mContext);
		mVoltageUnit.setGravity(Gravity.CENTER);
		mVoltageUnit.setId(mVoltageUnit.hashCode());
		//mCurrentVaule.setBackgroundColor(Color.RED);
		mVoltageUnit.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mVoltageUnit.setText("V");
        params = ActivityUtils.getLayoutParams(80, 40);
        params.leftMargin = ActivityUtils.scaleX(125);
		params.addRule(RelativeLayout.BELOW, mVoltageImg.getId());
        params.addRule(RelativeLayout.RIGHT_OF, mTextSoc.getId());
        mParentLayout.addView(mVoltageUnit, params);
	}
	
	public View getRootView(){
		return mParentLayout;
	}
	
	public void updateStatus(int event, Object object){
		switch (event) {
		case IControl.EVENT_SOC_BATTERT_VOLTAGE_CHANGE:
			updateBatteryVoltage((Integer) object);
			break;
		case IControl.EVENT_SOC_BATTERY_STATUS_CHANGE:
			updateBatteryStatus((Boolean) object);
			break;
		case IControl.EVENT_SOC_BATTERT_POWER_CHANGE:
			updateBatteryPower((Boolean) object);
			break;
		case IControl.EVENT_SOC_BATTERT_SHORTAGE_CHANGE:
			updateBatteryShortage((Boolean) object);
			break;
		case IControl.EVENT_SOC_CURRENT_CHANGE:
			updateCurrent((Float) object);
			break;
		case IControl.EVENT_SOC_VOLTAGE_CHANGE:
			updateVoltage((Float) object);
			break;
		case IControl.EVNET_SOC_FLIGHT_MILEAGE_CHANGE:
			updateFlightMileage((Integer) object);
			break;
		case IControl.EVNET_SOC_TOTAL_MILEAGE_CHANGE:
			updateTotalMileage((Integer) object);
			break;
		case IControl.EVENT_ALERT_CAR_STALLS_RIGHT:
			updateCarStalls((Integer) object);
			break;
		default:
			break;
		}
	}
	
	private void updateBatteryVoltage(int percent){
		mTextBatteryPercent.setText("" + percent + "%");
		mImgBatteryPercent.setBattery(1.0f * percent / 100);
	}
	
	private void updateBatteryStatus(boolean alert){
		mImgBattery.setImageResource(alert? R.drawable.alert_battery_highlight
				: R.drawable.alert_battery);
	}
	
	private void updateBatteryPower(boolean alert){
		mImgBatteryPower.setImageResource(alert? R.drawable.alert_power_battery_error_highlight
				: R.drawable.alert_power_battery_error);
	}
	
	private void updateBatteryShortage(boolean alert){
		mImgBatteryShortage.setImageResource(alert? R.drawable.alert_power_battery_shortage_highlight
				 : R.drawable.alert_power_battery_shortage);
	}
	
	private void updateFlightMileage(int mile){		
		mFlightMileageValue.setText("" + mile/10 + "." + mile%10);
	}
	
	private void updateTotalMileage(int mile){
		mTotalMileageValue.setText("" + mile);
	}
	
	private void updateCurrent(float current){//-200A~200A
		int n = (int)current;
		int m = (int)((current - n) * 10);
		mCurrentVaule.setText("" + n);
		mCurrentImg.setPercent(1.0f * (current + 200)/400);
	}
	
	private void updateVoltage(float voltage){//0~100V
		int n = (int)voltage;
		int m = (int)((voltage - n) * 10);
		mVoltageVaule.setText("" + n);
		mVoltageImg.setPercent(1.0f * voltage/100);
	}
	
	private void updateCarStalls(int stalls){
		if (stalls == 3){
			mD_StallsText.setTextColor(Color.GREEN);
			mN_StallsText.setTextColor(Color.WHITE);
		}else if (stalls == 4){
			mD_StallsText.setTextColor(Color.WHITE);
			mN_StallsText.setTextColor(Color.GREEN);
		}else{
			mD_StallsText.setTextColor(Color.WHITE);
			mN_StallsText.setTextColor(Color.WHITE);
		}
	}
}