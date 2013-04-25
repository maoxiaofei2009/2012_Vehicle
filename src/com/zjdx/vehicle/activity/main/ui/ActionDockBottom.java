package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.common.CarStatusHelper;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.GestureUtils;


public class ActionDockBottom implements OnClickListener{
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
	private ImageView mBtn_7;
	private ImageView mBtn_8;
	private ImageView mBtn_9;
	private ImageView mBtn_10;
	private ImageView mBtn_11;
	
	private IControl mIControl;
	private CarStatusHelper mCarStatusHelper;
	public ActionDockBottom(Context context, IControl control){
		mContext = context;
		mIControl = control;
		mCarStatusHelper = CarStatusHelper.getInstance();
		init();
		initStatus();
	}
	
	public void init(){
		mParentLayout = (RelativeLayout) 
				((Activity)mContext).findViewById(R.id.dock_area);
		
		if (mParentLayout != null){
			mBtn_0 = (ImageView) mParentLayout.findViewById(R.id.action_d0);
			mBtn_1 = (ImageView) mParentLayout.findViewById(R.id.action_d1);
			mBtn_2 = (ImageView) mParentLayout.findViewById(R.id.action_d2);
			mBtn_3 = (ImageView) mParentLayout.findViewById(R.id.action_d3);
			mBtn_4 = (ImageView) mParentLayout.findViewById(R.id.action_d4);
			mBtn_5 = (ImageView) mParentLayout.findViewById(R.id.action_d5);
			mBtn_6 = (ImageView) mParentLayout.findViewById(R.id.action_d6);
			mBtn_7 = (ImageView) mParentLayout.findViewById(R.id.action_d7);
			mBtn_8 = (ImageView) mParentLayout.findViewById(R.id.action_d8);
			mBtn_9 = (ImageView) mParentLayout.findViewById(R.id.action_d9);
			mBtn_10 = (ImageView) mParentLayout.findViewById(R.id.action_d10);
			mBtn_11 = (ImageView) mParentLayout.findViewById(R.id.action_d11);
			
			mBtn_0.setOnClickListener(this);
			mBtn_1.setOnClickListener(this);
			mBtn_2.setOnClickListener(this);
			mBtn_3.setOnClickListener(this);
			mBtn_4.setOnClickListener(this);
			mBtn_5.setOnClickListener(this);
			mBtn_6.setOnClickListener(this);
			mBtn_7.setOnClickListener(this);
			mBtn_8.setOnClickListener(this);
			mBtn_9.setOnClickListener(this);
			//mBtn_10.setOnClickListener(this);
			mBtn_11.setOnClickListener(this);
			mBtn_10.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View arg0, MotionEvent event) {
					int action = event.getAction();
					switch (action) {
					case MotionEvent.ACTION_DOWN:
						mBtn_10.setBackgroundResource(R.drawable.main_action_button_press);
						mBtn_10.setImageResource(R.drawable.main_dock_horn_press);
						mIControl.actionCarDockEvent(IControl.EVENT_HORN_CONTROL, 1);
						break;
					case MotionEvent.ACTION_MOVE:
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:
						mBtn_10.setBackgroundResource(0);
						mBtn_10.setImageResource(R.drawable.main_dock_horn_normal);
						mIControl.actionCarDockEvent(IControl.EVENT_HORN_CONTROL, 0);
						break;
					default:
						break;
					}
					return true;
				}
			});
			
			mBtn_0.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_1.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_2.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_3.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_4.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_5.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_6.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_7.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_8.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_9.setBackgroundResource(R.drawable.main_action_btn);
			//mBtn_10.setBackgroundResource(R.drawable.main_action_btn);
			mBtn_11.setBackgroundResource(R.drawable.main_action_btn);
			
			mBtn_0.setImageResource(R.drawable.main_dock_car_lamp_control);
			mBtn_1.setImageResource(R.drawable.main_dock_swicth_ac);
			mBtn_2.setImageResource(R.drawable.main_dock_car_window);
			mBtn_3.setImageResource(R.drawable.main_dock_rain_wiper);
			
			mBtn_4.setImageResource(R.drawable.main_dock_lamp_light_far_close);
			mBtn_5.setImageResource(R.drawable.main_dock_lamp_light_near_close);
			mBtn_6.setImageResource(R.drawable.main_dock_lamp_fog_front_close);
			mBtn_7.setImageResource(R.drawable.main_dock_lamp_fog_behind_close);
			mBtn_8.setImageResource(R.drawable.main_dock_door_front_close);
			mBtn_9.setImageResource(R.drawable.main_dock_door_behind_open);
			mBtn_10.setImageResource(R.drawable.main_dock_horn_normal);
			mBtn_11.setImageResource(R.drawable.main_dock_home);
		}
	}
	

	@Override
	public void onClick(View v) {
		if (mBtn_0 == v){
			mIControl.actionCarDockEvent(IControl.EVENT_LAMP_CONTROL, null);
		}
		
		if (mBtn_1 == v){
			mIControl.actionCarDockEvent(IControl.EVENT_AIR_CONDITION_CONTROL, null);
		}
		
		if (mBtn_2 == v){
			mIControl.actionCarDockEvent(IControl.EVENT_CAR_WINDOW_CONTROL, null);
		}
		
		if (mBtn_3 == v){
			mCarStatusHelper.setCarRainWiperStatus(!mCarStatusHelper.getCarRainWiperStatus());
			mIControl.actionCarDockEvent(IControl.EVENT_RAIN_WIPER_CONTROL, null);
		}
		
		if (mBtn_4 == v){
			mCarStatusHelper.setLampFarlightStatus(!mCarStatusHelper.getLampFarlightStatus());
			mIControl.actionCarDockEvent(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, 
					mCarStatusHelper.getLampFarlightStatus()? 1:0);
			mIControl.updateCarAlertDock(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, null);
			mIControl.updateCarContentCenter(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, null);
		}
		
		if (mBtn_5 == v){
			mCarStatusHelper.setLampNearlightStatus(!mCarStatusHelper.getLampNearlightStatus());		
			mIControl.actionCarDockEvent(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, 
					mCarStatusHelper.getLampNearlightStatus()? 1:0);
			mIControl.updateCarAlertDock(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, null);
			mIControl.updateCarContentCenter(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, null);
		}
		
		if (mBtn_6 == v){
			mCarStatusHelper.setLampFrontflogStatus(!mCarStatusHelper.getLampFrontflogStatus());
			mIControl.actionCarDockEvent(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, 
					mCarStatusHelper.getLampFrontflogStatus()? 1:0);
			mIControl.updateCarAlertDock(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, null);
			mIControl.updateCarContentCenter(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, null);
			//updateLampFrontflogStatus();
		}
		
		if (mBtn_7 == v){
			mCarStatusHelper.setLampBehindflogStatus(!mCarStatusHelper.getLampBehindflogStatus());
			mIControl.actionCarDockEvent(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, 
					mCarStatusHelper.getLampBehindflogStatus()? 1:0);
			mIControl.updateCarAlertDock(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, null);
			mIControl.updateCarContentCenter(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, null);
			//updateLampBehindflogStatus();
		}
		
		if (mBtn_8 == v){
			mCarStatusHelper.setCarFrontDoorStatus(!mCarStatusHelper.getCarFrontDoorStatus());
			mIControl.actionCarDockEvent(IControl.EVENT_DOOR_FRONT_CONTROL, 
					mCarStatusHelper.getCarFrontDoorStatus()? 1:0);
			mIControl.updateCarAlertDock(IControl.EVENT_DOOR_FRONT_CONTROL, null);
			//updateFrontCarDoor();
		}
		
		if (mBtn_9 == v){
			mCarStatusHelper.setCarBehindDoorStatus(!mCarStatusHelper.getCarBehindDoorStatus());
			mIControl.actionCarDockEvent(IControl.EVENT_DOOR_BEHIND_CONTROL, 
					mCarStatusHelper.getCarBehindDoorStatus()? 1:0);
			mIControl.updateCarAlertDock(IControl.EVENT_DOOR_BEHIND_CONTROL, null);
			//updateBehindCarDoor();
		}		
		
		if (mBtn_11 == v){
			((Activity) mContext).finish();
		}
	}
	
	public void updateStatus(int event){
		switch (event) {
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
			updateLampFarlightStatus();
			break;
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
			updateLampNearlightStatus();
			break;
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
			updateLampFrontflogStatus();
			break;
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:
			updateLampBehindflogStatus();
			break;
		case IControl.EVENT_DOOR_FRONT_CONTROL:
			updateFrontCarDoor();
			break;
		case IControl.EVENT_DOOR_BEHIND_CONTROL:
			updateBehindCarDoor();
			break;
		default:
			break;
		}
	}
	
	private void initStatus(){
		updateLampFarlightStatus();
		updateLampNearlightStatus();
		updateLampFrontflogStatus();
		updateLampBehindflogStatus();
		
		updateFrontCarDoor();
		updateBehindCarDoor();
	}
	
	
	private void updateLampFarlightStatus(){
		boolean open = mCarStatusHelper.getLampFarlightStatus();
		mBtn_4.setImageResource(open ? 
				R.drawable.main_dock_lamp_light_far_open
				: R.drawable.main_dock_lamp_light_far_close);
	}
	
	private void updateLampNearlightStatus(){
		boolean open = mCarStatusHelper.getLampNearlightStatus();
		mBtn_5.setImageResource(open ? 
				R.drawable.main_dock_lamp_light_near_open 
				: R.drawable.main_dock_lamp_light_near_close);
	}
	
	private void updateLampFrontflogStatus(){
		boolean open = mCarStatusHelper.getLampFrontflogStatus();
		mBtn_6.setImageResource(open ? 
				R.drawable.main_dock_lamp_fog_front_open 
				: R.drawable.main_dock_lamp_fog_front_close);
	}
	
	private void updateLampBehindflogStatus(){
		boolean open = mCarStatusHelper.getLampBehindflogStatus();
		mBtn_7.setImageResource(open ? 
				R.drawable.main_dock_lamp_fog_behind_open 
				: R.drawable.main_dock_lamp_fog_behind_close);
	}
	
	private void updateFrontCarDoor(){
		boolean open = mCarStatusHelper.getCarFrontDoorStatus();
		mBtn_8.setImageResource(open ? 
				R.drawable.main_dock_door_front_open
				: R.drawable.main_dock_door_front_close);
	}
	
	private void updateBehindCarDoor(){
		boolean open = mCarStatusHelper.getCarBehindDoorStatus();
		mBtn_9.setImageResource(open ? 
				R.drawable.main_dock_door_behind_open
				: R.drawable.main_dock_door_behind_close);
	}
}