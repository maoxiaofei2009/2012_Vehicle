package com.zjdx.vehicle.activity.main.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.common.CarStatusHelper;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;


public class ContentAreaAirCondition implements OnClickListener{
	private static final String TAG = "ContentAreaLeft";
	private final Context mContext;
	private RelativeLayout mParentLayoutArea;	
	private RelativeLayout mAirConditionArea;
	
	private IControl mIControl;
	public ContentAreaAirCondition(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayoutArea = layout;
		
		initLayout();
		initStatus();
	}
	
	private void initLayout(){
		initAirConditionArea();
		initAirModel();
		initAirWind();
		initControl();
		
		mParentLayoutArea.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
	}
	
	private void initStatus(){
		updateAirModelSelection();
		updateAirWindSelection();
		updateAirConditionTemperature();
		updateAirConditionControl();
	}
	
	private void initAirConditionArea(){
		mAirConditionArea = new RelativeLayout(mContext);
		mAirConditionArea.setBackgroundResource(R.drawable.main_air_condition_background);
		LayoutParams params = ActivityUtils.getLayoutParams(696, 456);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mParentLayoutArea.addView(mAirConditionArea, params);
	}
	
	private ImageView mAirConditionHot;
	private ImageView mAirConditionCold;
	private ImageView mAirConditionAuto;
	private ImageView mAirConditionWet;
	private void initAirModel(){
		mAirConditionHot = new ImageView(mContext);
		mAirConditionHot.setImageResource(R.drawable.main_air_condition_hot_select);
		mAirConditionHot.setId(mAirConditionHot.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(64, 64);
		params.leftMargin = ActivityUtils.scaleX(75);
		params.topMargin = ActivityUtils.scaleY(100);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mAirConditionArea.addView(mAirConditionHot, params);
        
        mAirConditionCold = new ImageView(mContext);
        mAirConditionCold.setImageResource(R.drawable.main_air_condition_cold_normal);
        mAirConditionCold.setId(mAirConditionCold.hashCode());
		params = ActivityUtils.getLayoutParams(64, 64);
		params.leftMargin = ActivityUtils.scaleX(75);
		params.topMargin = ActivityUtils.scaleY(2);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mAirConditionHot.getId());
        mAirConditionArea.addView(mAirConditionCold, params);
        
        mAirConditionAuto = new ImageView(mContext);
        mAirConditionAuto.setImageResource(R.drawable.main_air_condition_auto_normal);
        mAirConditionAuto.setId(mAirConditionAuto.hashCode());
		params = ActivityUtils.getLayoutParams(64, 64);
		params.leftMargin = ActivityUtils.scaleX(75);
		params.topMargin = ActivityUtils.scaleY(2);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mAirConditionCold.getId());
        mAirConditionArea.addView(mAirConditionAuto, params);
        
        mAirConditionWet = new ImageView(mContext);
        mAirConditionWet.setImageResource(R.drawable.main_air_condition_wet_normal);
        mAirConditionWet.setId(mAirConditionWet.hashCode());
		params = ActivityUtils.getLayoutParams(64, 64);
		params.leftMargin = ActivityUtils.scaleX(75);
		params.topMargin = ActivityUtils.scaleY(2);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mAirConditionAuto.getId());
        mAirConditionArea.addView(mAirConditionWet, params);
        
        
        mAirConditionHot.setBackgroundResource(R.drawable.main_air_mode_background);
        mAirConditionHot.setOnClickListener(this);
        mAirConditionCold.setBackgroundResource(R.drawable.main_air_mode_background);
        mAirConditionCold.setOnClickListener(this);
        mAirConditionAuto.setBackgroundResource(R.drawable.main_air_mode_background);
        mAirConditionAuto.setOnClickListener(this);
        mAirConditionWet.setBackgroundResource(R.drawable.main_air_mode_background);
        mAirConditionWet.setOnClickListener(this);
	}
	
	
	private ImageView mAirWindHigh;
	private ImageView mAirWindLow;
	private void initAirWind(){
		mAirWindHigh = new ImageView(mContext);
		mAirWindHigh.setImageResource(R.drawable.main_air_condition_low_wind_normal);
		mAirWindHigh.setId(mAirWindHigh.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(64, 64);
		params.rightMargin = ActivityUtils.scaleX(75);
		params.topMargin = ActivityUtils.scaleY(150);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mAirConditionArea.addView(mAirWindHigh, params);
        
        mAirWindLow = new ImageView(mContext);
        mAirWindLow.setImageResource(R.drawable.main_air_condition_high_wind_normal);
        mAirWindLow.setId(mAirWindLow.hashCode());
        params = ActivityUtils.getLayoutParams(64, 64);
		params.rightMargin = ActivityUtils.scaleX(75);
		params.topMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.BELOW, mAirWindHigh.getId());
        mAirConditionArea.addView(mAirWindLow, params);
        
        mAirWindHigh.setBackgroundResource(R.drawable.main_air_mode_background);
        mAirWindHigh.setOnClickListener(this);
        mAirWindLow.setBackgroundResource(R.drawable.main_air_mode_background);
        mAirWindLow.setOnClickListener(this);
	}
	
	
	private ImageView mAirConditionOnOff;
	private ImageView mAirConditionAdd;
	private ImageView mAirConditionReduce;
	private TextView mAirConditionText;
	private void initControl(){
		mAirConditionOnOff = new ImageView(mContext);
		mAirConditionOnOff.setImageResource(R.drawable.main_air_condition_btn_off);
		mAirConditionOnOff.setId(mAirConditionOnOff.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(64, 64);
		params.bottomMargin= ActivityUtils.scaleY(55);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mAirConditionArea.addView(mAirConditionOnOff, params);
        
        
        mAirConditionReduce = new ImageView(mContext);
        mAirConditionReduce.setImageResource(R.drawable.main_air_condition_btn_reduce);
        mAirConditionReduce.setId(mAirConditionReduce.hashCode());
		params = ActivityUtils.getLayoutParams(64, 64);
		params.leftMargin = ActivityUtils.scaleX(230);
		params.bottomMargin= ActivityUtils.scaleY(35);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ABOVE, mAirConditionOnOff.getId());
        mAirConditionArea.addView(mAirConditionReduce, params);
        
        mAirConditionAdd = new ImageView(mContext);
        mAirConditionAdd.setImageResource(R.drawable.main_air_condition_btn_add);
        mAirConditionAdd.setId(mAirConditionAdd.hashCode());
		params = ActivityUtils.getLayoutParams(64, 64);
		params.leftMargin = ActivityUtils.scaleX(390);
		params.bottomMargin= ActivityUtils.scaleY(35);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ABOVE, mAirConditionOnOff.getId());
        mAirConditionArea.addView(mAirConditionAdd, params);
        
        mAirConditionText = new TextView(mContext);
        mAirConditionText.setId(mAirConditionText.hashCode());
        mAirConditionText.setTextSize((mContext.getResources().getDimension(R.dimen.main_text_air_condition)));
        mAirConditionText.setText("24 .¡æ");
        mAirConditionText.setGravity(Gravity.CENTER);
		params = ActivityUtils.getLayoutParams(200, 100);
		params.leftMargin = ActivityUtils.scaleX(245);
		params.bottomMargin= ActivityUtils.scaleY(95);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ABOVE, mAirConditionOnOff.getId());
        mAirConditionArea.addView(mAirConditionText, params);
        
        mAirConditionOnOff.setBackgroundResource(R.drawable.main_air_on_off_background);
        mAirConditionOnOff.setOnClickListener(this);
        mAirConditionReduce.setBackgroundResource(R.drawable.main_air_add_reduce_background);
        mAirConditionReduce.setOnClickListener(this);
        mAirConditionAdd.setBackgroundResource(R.drawable.main_air_add_reduce_background);
        mAirConditionAdd.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View view) {
		if (view == mAirConditionHot){
			CarStatusHelper.getInstance().setAirConditionModel(CarStatusHelper.AIR_MODEL_HOT);
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, CarStatusHelper.AIR_MODEL_HOT);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, null);
		}
		
		if (view == mAirConditionCold){
			CarStatusHelper.getInstance().setAirConditionModel(CarStatusHelper.AIR_MODEL_COLD);
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, CarStatusHelper.AIR_MODEL_COLD);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, null);
		}
		
		if (view == mAirConditionAuto){
			CarStatusHelper.getInstance().setAirConditionModel(CarStatusHelper.AIR_MODEL_AUTO);
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, CarStatusHelper.AIR_MODEL_AUTO);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, null);
		}
		
		if (view == mAirConditionWet){
			CarStatusHelper.getInstance().setAirConditionModel(CarStatusHelper.AIR_MODEL_WET);
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, CarStatusHelper.AIR_MODEL_WET);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_MODEL_CHANGE, null);
		}
		
		
		if (view == mAirWindLow){
			CarStatusHelper.getInstance().setAirConditionWind(CarStatusHelper.AIR_WIND_STYLE_LOW);
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_WIND_CHANGE, CarStatusHelper.AIR_WIND_STYLE_LOW);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_WIND_CHANGE, null);
		}
		
		if (view == mAirWindHigh){
			CarStatusHelper.getInstance().setAirConditionWind(CarStatusHelper.AIR_WIND_STYLE_HIGH);
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_WIND_CHANGE, CarStatusHelper.AIR_WIND_STYLE_HIGH);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_WIND_CHANGE, null);
		}
		
		if (view == mAirConditionOnOff){
			CarStatusHelper.getInstance().setAirConditionStatus(
					!CarStatusHelper.getInstance().getAirConditionStatus());
			mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_OPEN_CHANGE, 
					CarStatusHelper.getInstance().getAirConditionStatus()? 1:0);
			mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_OPEN_CHANGE, null);
		}
		
		if (view == mAirConditionAdd){
			int vaule = CarStatusHelper.getInstance().getAirConditionTemperature();
			if (vaule + 1 < 35){
				vaule = vaule + 1;
				CarStatusHelper.getInstance().setAirConditionTemperature(vaule);
				mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE, vaule);
				mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE, null);
			}
		}
		
		if (view == mAirConditionReduce){
			int vaule = CarStatusHelper.getInstance().getAirConditionTemperature();
			if (vaule - 1 > 16){
				vaule = vaule - 1;
				CarStatusHelper.getInstance().setAirConditionTemperature(vaule);
				mIControl.actionCarAirCondition(IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE, vaule);
				mIControl.updateCarAirCondition(IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE, null);
			}
		}
	}
	
	public void updateAirConditionStatus(int event){
		switch (event) {
		case IControl.EVENT_AIR_CONDITION_OPEN_CHANGE:
			updateAirConditionControl();
			break;
		case IControl.EVENT_AIR_CONDITION_TEMPERATURE_CHANGE:
			updateAirConditionTemperature();
			break;
		case IControl.EVENT_AIR_CONDITION_MODEL_CHANGE:
			updateAirModelSelection();
			break;
		case IControl.EVENT_AIR_CONDITION_WIND_CHANGE:
			updateAirWindSelection();
			break;
		default:
			break;
		}
	}
	
	private void updateAirModelSelection(){
		int model = CarStatusHelper.getInstance().getAirConditionModel();
		if (model == CarStatusHelper.AIR_MODEL_HOT){
			mAirConditionHot.setImageResource(R.drawable.main_air_condition_hot_select);
			mAirConditionCold.setImageResource(R.drawable.main_air_condition_cold_normal);
			mAirConditionAuto.setImageResource(R.drawable.main_air_condition_auto_normal);
			mAirConditionWet.setImageResource(R.drawable.main_air_condition_wet_normal);
		}else if(model == CarStatusHelper.AIR_MODEL_COLD){
			mAirConditionHot.setImageResource(R.drawable.main_air_condition_hot_normal);
			mAirConditionCold.setImageResource(R.drawable.main_air_condition_cold_select);
			mAirConditionAuto.setImageResource(R.drawable.main_air_condition_auto_normal);
			mAirConditionWet.setImageResource(R.drawable.main_air_condition_wet_normal);
		}else if(model == CarStatusHelper.AIR_MODEL_AUTO){
			mAirConditionHot.setImageResource(R.drawable.main_air_condition_hot_normal);
			mAirConditionCold.setImageResource(R.drawable.main_air_condition_cold_normal);
			mAirConditionAuto.setImageResource(R.drawable.main_air_condition_auto_select);
			mAirConditionWet.setImageResource(R.drawable.main_air_condition_wet_normal);
		}else if(model == CarStatusHelper.AIR_MODEL_WET){
			mAirConditionHot.setImageResource(R.drawable.main_air_condition_hot_normal);
			mAirConditionCold.setImageResource(R.drawable.main_air_condition_cold_normal);
			mAirConditionAuto.setImageResource(R.drawable.main_air_condition_auto_normal);
			mAirConditionWet.setImageResource(R.drawable.main_air_condition_wet_select);
		}
	}
	
	private void updateAirWindSelection(){
		int windStyle = CarStatusHelper.getInstance().getAirConditionWind();
		if (windStyle == CarStatusHelper.AIR_WIND_STYLE_LOW){
			mAirWindLow.setImageResource(R.drawable.main_air_condition_low_wind_select);
			mAirWindHigh.setImageResource(R.drawable.main_air_condition_high_wind_normal);
		}else if(windStyle == CarStatusHelper.AIR_WIND_STYLE_HIGH){
			mAirWindLow.setImageResource(R.drawable.main_air_condition_low_wind_normal);
			mAirWindHigh.setImageResource(R.drawable.main_air_condition_high_wind_select);
		}
	}
	
	private void updateAirConditionControl(){
		boolean open = CarStatusHelper.getInstance().getAirConditionStatus();
		mAirConditionOnOff.setImageResource(open ? 
				R.drawable.main_air_condition_btn_off : R.drawable.main_air_condition_btn_on);

		mAirConditionText.setVisibility(open? View.VISIBLE:View.INVISIBLE);
		mAirWindLow.setEnabled(open);
		mAirWindHigh.setEnabled(open);
		mAirConditionHot.setEnabled(open);
		mAirConditionCold.setEnabled(open);
		mAirConditionAuto.setEnabled(open);
		mAirConditionWet.setEnabled(open);
		mAirConditionAdd.setEnabled(open);
		mAirConditionReduce.setEnabled(open);
	}
	
	private void updateAirConditionTemperature(){
		int temperature = CarStatusHelper.getInstance().getAirConditionTemperature();
		mAirConditionText.setText("" + temperature + " .¡æ");
	}
}