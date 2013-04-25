package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.R;
import com.zjdx.vehicle.VehicleApp;
import com.zjdx.vehicle.activity.main.control.IControl;


public class ActionBarLeft implements OnClickListener{
	private static final String TAG = "ActionBarLeft";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private ImageView mBtn_0;
	private ImageView mBtn_1;
	private ImageView mBtn_2;
	private ImageView mBtn_3;
	private ImageView mBtn_4;
	private ImageView mBtn_5;
	private ImageView mBtn_6;
	
	public ActionBarLeft(Context context){
		mContext = context;
		
		init();
		initStatus();
	}
	
	public void init(){
		mParentLayout = (RelativeLayout) 
				((Activity)mContext).findViewById(R.id.content_area);
		
		if (mParentLayout != null){
			mBtn_0 = (ImageView) mParentLayout.findViewById(R.id.action_l0);
			mBtn_1 = (ImageView) mParentLayout.findViewById(R.id.action_l1);
			mBtn_2 = (ImageView) mParentLayout.findViewById(R.id.action_l2);
			mBtn_3 = (ImageView) mParentLayout.findViewById(R.id.action_l3);
			mBtn_4 = (ImageView) mParentLayout.findViewById(R.id.action_l4);
			mBtn_5 = (ImageView) mParentLayout.findViewById(R.id.action_l5);
			mBtn_6 = (ImageView) mParentLayout.findViewById(R.id.action_l6);
			
			
//			mBtn_0.setImageResource(R.drawable.alert_ready);
//			mBtn_1.setImageResource(R.drawable.alert_system_error_highlight);
//			mBtn_2.setImageResource(R.drawable.alert_safty_air_bag_highlight);
//			mBtn_3.setImageResource(R.drawable.alert_seat_belt_highlight);
//			mBtn_4.setImageResource(R.drawable.alert_brake_system_error_highlight);
//			mBtn_5.setImageResource(R.drawable.alert_parking_indicator_highlight);
//			mBtn_6.setImageResource(R.drawable.icon_lamp_rotate_left_highlight);
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

	@Override
	public void onClick(View v) {
		VehicleApp.makeToast("Touch " + (Integer)v.getTag());
		//Toast.makeText(mContext, "Touch " + (Integer)v.getTag(), Toast.LENGTH_SHORT).show();
	}
	
	public void updateAlert(int event, boolean alert){
		switch (event) {
		case IControl.EVENT_ALERT_READY_STOP:
			updateAlert_0(alert);
			break;
		case IControl.EVENT_ALERT_SYSTEM_ERROR:
			updateAlert_1(alert);
			break;
		case IControl.EVENT_ALERT_SAFTY_AIR_BAG:
			updateAlert_2(alert);
			break;
		case IControl.EVENT_ALERT_SEAT_BELT:
			updateAlert_3(alert);
			break;
		case IControl.EVENT_ALERT_BRAKE_SYSTEM_ERROR:
			updateAlert_4(alert);
			break;
		case IControl.EVENT_ALERT_PARKING_INDICATOR:
			updateAlert_5(alert);
			break;
		case IControl.EVENT_ALERT_ROTATE_LEFT:
			updateAlert_6(alert);
			break;
		default:
			break;
		}
	}
	
	
	private void updateAlert_0(boolean alert){
		mBtn_0.setImageResource(alert? R.drawable.alert_stop
				: R.drawable.alert_ready);
	}
	
	private void updateAlert_1(boolean alert){
		mBtn_1.setImageResource(alert? R.drawable.alert_system_error_highlight
				: R.drawable.alert_system_error);
	}
	
	private void updateAlert_2(boolean alert){
		mBtn_2.setImageResource(alert? R.drawable.alert_safty_air_bag_highlight
				: R.drawable.alert_safty_air_bag);
	}
	
	private void updateAlert_3(boolean alert){
		mBtn_3.setImageResource(alert? R.drawable.alert_seat_belt_highlight 
				: R.drawable.alert_seat_belt);
	}
	
	private void updateAlert_4(boolean alert){
		mBtn_4.setImageResource(alert? R.drawable.alert_brake_system_error_highlight 
				: R.drawable.alert_brake_system_error);
	}
	
	private void updateAlert_5(boolean alert){
		mBtn_5.setImageResource(alert? R.drawable.alert_parking_indicator_highlight
				: R.drawable.alert_parking_indicator);
	}
	
	private AnimationDrawable mAnimationDrawable = null;
	private void updateAlert_6(boolean alert){
		if (mAnimationDrawable != null){
			mAnimationDrawable.stop();
		}
		
		mBtn_6.setImageResource(alert? R.drawable.main_rotate_left_alert_lamp
				: R.drawable.icon_lamp_rotate_left_normal);
		if (alert){
			mAnimationDrawable = (AnimationDrawable) mBtn_6.getDrawable();
			mAnimationDrawable.start();
		}
	}
}