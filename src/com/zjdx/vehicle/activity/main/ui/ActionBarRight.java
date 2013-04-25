package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;


public class ActionBarRight{
	private static final String TAG = "MainControl";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private ImageView mBtn_0;
	private ImageView mBtn_1;
	private ImageView mBtn_2;
	private ImageView mBtn_3;
	private ImageView mBtn_4;
	private ImageView mBtn_5;
	private ImageView mBtn_6;
	
	public ActionBarRight(Context context){
		mContext = context;
		
		init();
		initStatus();
	}
	
	public void init(){
		mParentLayout = (RelativeLayout) 
				((Activity)mContext).findViewById(R.id.content_area);
		
		if (mParentLayout != null){
			mBtn_0 = (ImageView) mParentLayout.findViewById(R.id.action_r0);
			mBtn_1 = (ImageView) mParentLayout.findViewById(R.id.action_r1);
			mBtn_2 = (ImageView) mParentLayout.findViewById(R.id.action_r2);
			mBtn_3 = (ImageView) mParentLayout.findViewById(R.id.action_r3);
			mBtn_4 = (ImageView) mParentLayout.findViewById(R.id.action_r4);
			mBtn_5 = (ImageView) mParentLayout.findViewById(R.id.action_r5);
			mBtn_6 = (ImageView) mParentLayout.findViewById(R.id.action_r6);
//			
//			mBtn_0.setImageResource(R.drawable.alert_electric_machine_over_temperature_highlight);
//			mBtn_1.setImageResource(R.drawable.alert_electric_machine_over_speed_highlight);
//			mBtn_2.setImageResource(R.drawable.alert_charging_connect_highlight);
//			mBtn_3.setImageResource(R.drawable.alert_charging_highlight);
//			mBtn_4.setImageResource(R.drawable.alert_power_battery_highlight);
//			mBtn_5.setImageResource(R.drawable.alert_insulated_system_highlight);
//			mBtn_6.setImageResource(R.drawable.icon_lamp_rotate_right_highlight);
		}
	}
	
	private void initStatus(){
		updateAlert_0(false);
		updateAlert_1(false);
		updateAlert_2(false);
		updateAlert_3(false);
		updateAlert_4(false);
		updateAlert_5(false);
		updateAlert_6(false);
	}
	
	public void updateAlert(int event, boolean alert){
		switch (event) {
		case IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_TEMPERATURE:
			updateAlert_0(alert);
			break;
		case IControl.EVENT_ALERT_ELECTRIC_MACHINE_OVER_SPEED:
			updateAlert_1(alert);
			break;
		case IControl.EVENT_ALERT_CHARGING_CONNECT:
			updateAlert_2(alert);
			break;
		case IControl.EVENT_ALERT_CHARGING:
			updateAlert_3(alert);
			break;
		case IControl.EVENT_ALERT_POWER_BATTERY:
			updateAlert_4(alert);
			break;
		case IControl.EVENT_ALERT_INSULATED_SYSTEM:
			updateAlert_5(alert);
			break;
		case IControl.EVENT_ALERT_ROTATE_RIGHT:
			updateAlert_6(alert);
			break;
		default:
			break;
		}
	}
	
	private void updateAlert_0(boolean alert){
		mBtn_0.setImageResource(alert? R.drawable.alert_electric_machine_over_temperature_highlight
				: R.drawable.alert_electric_machine_over_temperature);
	}
	
	private void updateAlert_1(boolean alert){
		mBtn_1.setImageResource(alert? R.drawable.alert_electric_machine_over_speed_highlight
				: R.drawable.alert_electric_machine_over_speed);
	}
	
	private void updateAlert_2(boolean alert){
		mBtn_2.setImageResource(alert? R.drawable.alert_charging_connect_highlight
				: R.drawable.alert_charging_connect);
	}
	
	private void updateAlert_3(boolean alert){
		mBtn_3.setImageResource(alert? R.drawable.alert_charging_highlight 
				: R.drawable.alert_charging);
	}
	
	private void updateAlert_4(boolean alert){
		mBtn_4.setImageResource(alert? R.drawable.alert_power_battery_highlight 
				: R.drawable.alert_power_battery);
	}
	
	private void updateAlert_5(boolean alert){
		mBtn_5.setImageResource(alert? R.drawable.alert_insulated_system_highlight
				: R.drawable.alert_insulated_system);
	}
	
	private AnimationDrawable mAnimationDrawable = null;
	private void updateAlert_6(boolean alert){
		if (mAnimationDrawable != null){
			mAnimationDrawable.stop();
		}
		mBtn_6.setImageResource(alert? R.drawable.main_rotate_right_alert_lamp :
			R.drawable.icon_lamp_rotate_right_normal);
		if (alert){
			mAnimationDrawable = (AnimationDrawable) mBtn_6.getDrawable();
			mAnimationDrawable.start();
		}
	}

}