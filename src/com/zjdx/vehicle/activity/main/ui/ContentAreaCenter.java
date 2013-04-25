package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.VehicleApp;
import com.zjdx.vehicle.activity.main.common.CarStatusHelper;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.main.ui.view.DoorSash;
import com.zjdx.vehicle.activity.utils.ActivityUtils;
import com.zjdx.vehicle.activity.utils.GestureUtils;


public class ContentAreaCenter{
	private static final String TAG = "ContentAreaLeft";
	private final Context mContext;
	private LinearLayout mRootLayout;
	private RelativeLayout mCarControlLayout;
	private RelativeLayout mCarDoorSashLayout;
	private RelativeLayout mCarAirConditionLayout;
	private RelativeLayout mCarInfoLayout;
	
	
	private ImageView mFRightLampNear;
	private ImageView mFRightLampFar;
	private ImageView mFRightLampFlog;
	private ImageView mFRightLampRotate;
	
	private ImageView mFLeftLampNear;
	private ImageView mFLeftLampFar;
	private ImageView mFLeftLampFlog;
	private ImageView mFLeftLampRotate;
	
	private ImageView mBRightLampRotate;
	private ImageView mBRightLampFlog;
	
	private ImageView mBLeftLampRotate;
	private ImageView mBLeftLampFlog;
	
	private ImageView mRightCarDoor;
	private ImageView mLeftCarDoor;
	
	private ImageView mCarRainWiper;
	private ImageView mCarRainWiperS1;
	private ImageView mCarRainWiperS2;
	private ImageView mCarRainWiperS3;
	private ImageView mCarRainWiperS4;	
	private TextView mCarRainWiperTxt1;
	private TextView mCarRainWiperTxt2;
	private TextView mCarRainWiperTxt3;
	private TextView mCarRainWiperTxt4;
	
	
	private IControl mIControl;
	private ContentAreaAirCondition mContentAirCondition;
	private ContentAreaBottom mContentAreaBottom;
	public ContentAreaCenter(Context context, IControl control){
		mContext = context;
		mIControl = control;
		
		initLayout();
		initStatus();
	}

	public void initLayout(){
		mRootLayout = (LinearLayout) ((Activity)mContext).findViewById(R.id.content_center);
		mCarControlLayout = (RelativeLayout) mRootLayout.findViewById(R.id.content_center_area);
		
		mCarDoorSashLayout = (RelativeLayout) mRootLayout.findViewById(R.id.content_center_area_for_doorsash);
		mCarDoorSashLayout.setVisibility(View.GONE);
		
		mCarAirConditionLayout = (RelativeLayout) mRootLayout.findViewById(R.id.content_center_area_air_condition);
		mCarAirConditionLayout.setVisibility(View.GONE);
		
		mCarInfoLayout = (RelativeLayout) mRootLayout.findViewById(R.id.content_center_area_info_view);
		mCarInfoLayout.setVisibility(View.GONE);
		if (mCarControlLayout != null){
			initFrontRightLamp();
			initFrontLeftLamp();
			
			initBehindRightLamp();
			initBehindLeftLamp();
			
			initRightCarDoor();
			initLeftCarDoor();
			
			initTouchArea();
			
			initCarRainWiper();
			initCarDoorSash();
		}
		
		//initContentAirCondition(mContext, mIControl);
	}
	
	public View getRootView(){
		return mRootLayout;
	}
	
	public ContentAreaAirCondition getContentAirCondition(){
		if (mContentAirCondition == null){
			mContentAirCondition = new ContentAreaAirCondition(mContext, mIControl, mCarAirConditionLayout);
		}
		return mContentAirCondition;
	}
	
	public ContentAreaBottom getContentAreaBottom(){
		if (mContentAreaBottom == null){
			mContentAreaBottom = new ContentAreaBottom(mContext, mIControl, mCarInfoLayout);
		}
		
		return mContentAreaBottom;
	}
	
	private float mTouchDownX;
	private float mTouchDownY;
	private boolean mTouchMove = false;
	private boolean mTouchTwoPointer = false;
	private int mDirection;
	private View mFrontLampTouchArea;
	private View mRainWiperTouchArea;
	private View mRightDoorTouchArea;
	private View mLeftDoorTouchArea;
	private View mBehindLampTouchArea;
	
	private void initTouchArea(){
		mFrontLampTouchArea = new LinearLayout(mContext);
		mFrontLampTouchArea.setId(mFrontLampTouchArea.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(240, 539);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mCarControlLayout.addView(mFrontLampTouchArea, params);
		
        mRainWiperTouchArea = new LinearLayout(mContext);
        mRainWiperTouchArea.setId(mRainWiperTouchArea.hashCode());
        //mRainWiperTouchArea.setBackgroundColor(Color.RED);
		params = ActivityUtils.getLayoutParams(120, 539);
        params.addRule(RelativeLayout.RIGHT_OF, mFrontLampTouchArea.getId());
        mCarControlLayout.addView(mRainWiperTouchArea, params);
		
		
        mRightDoorTouchArea = new LinearLayout(mContext);
        mRightDoorTouchArea.setId(mRightDoorTouchArea.hashCode());
		params = ActivityUtils.getLayoutParams(238, 270);
        params.addRule(RelativeLayout.RIGHT_OF, mRainWiperTouchArea.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mRightDoorTouchArea, params);
		
        mLeftDoorTouchArea = new LinearLayout(mContext);
        mLeftDoorTouchArea.setId(mLeftDoorTouchArea.hashCode());
		params = ActivityUtils.getLayoutParams(238, 270);
        params.addRule(RelativeLayout.RIGHT_OF, mRainWiperTouchArea.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mLeftDoorTouchArea, params);
		
		
        mBehindLampTouchArea = new LinearLayout(mContext);
        mBehindLampTouchArea.setId(mBehindLampTouchArea.hashCode());
        //mBehindLampTouchArea.setBackgroundColor(Color.RED);
		params = ActivityUtils.getLayoutParams(183, 539);
        params.addRule(RelativeLayout.RIGHT_OF, mRightDoorTouchArea.getId());
        mCarControlLayout.addView(mBehindLampTouchArea, params);
		
		
        mFrontLampTouchArea.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				//VehicleApp.makeToast("onTouch " + action);
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return true;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					//VehicleApp.makeToast("onTouch 1_DOWN");
					mTouchTwoPointer = false;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					mTouchTwoPointer = true;
					//VehicleApp.makeToast("onTouch 2_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					//VehicleApp.makeToast("onTouch direction = " + direction);
					break;	
				//case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					//VehicleApp.makeToast("onTouch 1_UP");
					//VehicleApp.makeToast("onTouch mDirection  = " + mDirection);
					if (!mTouchTwoPointer){
						if (mDirection == GestureUtils.DIRECTION_LEFT){
							float distanceX = Math.abs(event.getX()- mTouchDownX);
							float distanceY = Math.abs(event.getY()- mTouchDownY);
							if (distanceX > ActivityUtils.scaleX(120) 
								|| distanceY > ActivityUtils.scaleY(120)){
								if (!CarStatusHelper.getInstance().getLampFarlightStatus()){
									CarStatusHelper.getInstance().setLampFarlightStatus(true);
									if (mIControl != null){
										mIControl.actionCarControlEvent(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, 1);
										mIControl.updateCarContentCenter(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, null);
									}
								}
							}else{
								if (!CarStatusHelper.getInstance().getLampNearlightStatus()){
									CarStatusHelper.getInstance().setLampNearlightStatus(true);
									if (mIControl != null){
										mIControl.actionCarControlEvent(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, 1);
										mIControl.updateCarContentCenter(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, null);
									}
								}
							}
						}
						
						if (mDirection == GestureUtils.DIRECTION_RIGHT){
							float distanceX = Math.abs(event.getX()- mTouchDownX);
							float distanceY = Math.abs(event.getY()- mTouchDownY);
							if (distanceX > ActivityUtils.scaleX(120) 
								|| distanceY > ActivityUtils.scaleY(120)){
								if (CarStatusHelper.getInstance().getLampFarlightStatus()){
									CarStatusHelper.getInstance().setLampFarlightStatus(false);
									//updateLampFarLight();
									if (mIControl != null){
										mIControl.actionCarControlEvent(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, 0);
										mIControl.updateCarContentCenter(IControl.EVENT_LAMP_FAR_LIGHT_CONTROL, null);
									}
								}
							}else{
								if (CarStatusHelper.getInstance().getLampNearlightStatus()){
									CarStatusHelper.getInstance().setLampNearlightStatus(false);
									//updateLampNearLight();
									if (mIControl != null){
										mIControl.actionCarControlEvent(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, 0);
										mIControl.updateCarContentCenter(IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL, null);
									}
								}
							}
						}
						
	
						if (mDirection == GestureUtils.DIRECTION_UP
							|| mDirection == GestureUtils.DIRECTION_UP_LEFT){
							if (!CarStatusHelper.getInstance().getCarLampRotateLeftStatus()){
								CarStatusHelper.getInstance().setCarLampRotateRightStatus(true);
								if (mIControl != null){
									mIControl.actionCarControlEvent(IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL, 1);
									mIControl.updateCarContentCenter(IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL, null);
								}
							}
						}
						
						if (mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
							if (CarStatusHelper.getInstance().getCarLampRotateRightStatus()){
								CarStatusHelper.getInstance().setCarLampRotateRightStatus(false);
								if (mIControl != null){
									mIControl.actionCarControlEvent(IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL, 0);
									mIControl.updateCarContentCenter(IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL, null);
								}
							}
						}
						
						if (mDirection == GestureUtils.DIRECTION_UP_RIGHT){
							if (CarStatusHelper.getInstance().getCarLampRotateLeftStatus()){
								CarStatusHelper.getInstance().setCarLampRotateLeftStatus(false);
								if (mIControl != null){
									mIControl.actionCarControlEvent(IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL, 0);
									mIControl.updateCarContentCenter(IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL, null);
								}
							}	
						}
						
						if (mDirection == GestureUtils.DIRECTION_DOWN
							|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT){
							if (!CarStatusHelper.getInstance().getCarLampRotateRightStatus()){
								CarStatusHelper.getInstance().setCarLampRotateLeftStatus(true);
								if (mIControl != null){
									mIControl.actionCarControlEvent(IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL, 1);
									mIControl.updateCarContentCenter(IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL, null);
								}
							}
						}
					}
					break;
				case MotionEvent.ACTION_POINTER_UP:
					//VehicleApp.makeToast("onTouch 2_UP");
					if (mDirection == GestureUtils.DIRECTION_LEFT){
						CarStatusHelper.getInstance().setLampFrontflogStatus(true);
						//updateLampFrontFlog();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, 1);
							mIControl.updateCarContentCenter(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, null);
						}
					}
					
					if (mDirection == GestureUtils.DIRECTION_RIGHT){
						CarStatusHelper.getInstance().setLampFrontflogStatus(false);
						//updateLampFrontFlog();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, 0);
							mIControl.updateCarContentCenter(IControl.EVENT_LAMP_FRONT_FLOG_CONTROL, null);
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
        
        mRainWiperTouchArea.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return true;
				}
				switch (action) {
				case MotionEvent.ACTION_UP:
					CarStatusHelper.getInstance().setCarRainWiperStatus(
							!CarStatusHelper.getInstance().getCarRainWiperStatus());
					updateCarRainWiper();
					break;	
				default:
					break;
				}			
				return true;
			}
        });
        
        mRightDoorTouchArea.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return true;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchMove = false;
				break;
				case MotionEvent.ACTION_MOVE:
					mTouchMove = true;
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;
				case MotionEvent.ACTION_UP:
					if (mDirection == GestureUtils.DIRECTION_UP 
						|| mDirection == GestureUtils.DIRECTION_UP_LEFT
						|| mDirection == GestureUtils.DIRECTION_UP_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorRightStatus(true);
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL, 1);
							mIControl.updateCarContentCenter(IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL, null);
						}
						//updateCarFrontRightDoor();
					}
		
					if (mDirection == GestureUtils.DIRECTION_DOWN
						|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT
						|| mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorRightStatus(false);
						//updateCarFrontRightDoor();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL, 0);
							mIControl.updateCarContentCenter(IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL, null);
						}
					}
					
					if (!mTouchMove){
						updateDoorSash();
					}
					break;	
				default:
					break;
				}			
				return true;
			}
        });      
        
        mLeftDoorTouchArea.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return true;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchMove = false;
				break;
				case MotionEvent.ACTION_MOVE:
					mTouchMove = true;
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;
				case MotionEvent.ACTION_UP:
					if (mDirection == GestureUtils.DIRECTION_UP 
					|| mDirection == GestureUtils.DIRECTION_UP_LEFT
					|| mDirection == GestureUtils.DIRECTION_UP_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorLeftStatus(false);
						//updateCarFrontLeftDoor();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_DOOR_LEFT_OPEN_CONTROL, 0);
							mIControl.updateCarContentCenter(IControl.EVENT_DOOR_LEFT_OPEN_CONTROL, null);
						}
					}
		
					if (mDirection == GestureUtils.DIRECTION_DOWN
							|| mDirection == GestureUtils.DIRECTION_DOWN_LEFT
							|| mDirection == GestureUtils.DIRECTION_DOWN_RIGHT){
						CarStatusHelper.getInstance().setCarFrontDoorLeftStatus(true);
						//updateCarFrontLeftDoor();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_DOOR_LEFT_OPEN_CONTROL, 1);
							mIControl.updateCarContentCenter(IControl.EVENT_DOOR_LEFT_OPEN_CONTROL, null);
						}
					}
					
					if (!mTouchMove){
						updateDoorSash();
					}
					break;	
				default:
					break;
				}			
				return true;
			}
        });
        
        mBehindLampTouchArea.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return true;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mTouchDownX = event.getX();
					mTouchDownY = event.getY();
					mTouchTwoPointer = false;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					mTouchTwoPointer = true;
					break;
				case MotionEvent.ACTION_MOVE:
					mDirection = GestureUtils.getDirection((int)event.getX(), (int)event.getY(), 
							(int)mTouchDownX, (int)mTouchDownY);
					break;	
				//case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					//VehicleApp.makeToast("onTouch 1_UP");
					//VehicleApp.makeToast("onTouch mDirection  = " + mDirection);
					break;
				case MotionEvent.ACTION_POINTER_UP:
					if (mDirection == GestureUtils.DIRECTION_RIGHT){
						CarStatusHelper.getInstance().setLampBehindflogStatus(true);
						//updateLampBehindFlog();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, 1);
							mIControl.updateCarContentCenter(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, null);
						}
					}
					
					if (mDirection == GestureUtils.DIRECTION_LEFT){
						CarStatusHelper.getInstance().setLampBehindflogStatus(false);
						//updateLampBehindFlog();
						if (mIControl != null){
							mIControl.actionCarControlEvent(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, 0);
							mIControl.updateCarContentCenter(IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL, null);
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
        
        mRootLayout.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction() & MotionEvent.ACTION_MASK;
				switch (action) {
				case MotionEvent.ACTION_UP:
					mIControl.actionCarControlEvent(IControl.EVENT_CLOSE_CONTENT_CENTER, null);
					break;	
				default:
					break;
				}			
				return true;
			}
        	
        });
	}
	
	private void initFrontRightLamp(){
		mFRightLampFar = new ImageView(mContext);
		mFRightLampFar.setImageResource(R.drawable.main_car_control_front_lamp_right_far);
		mFRightLampFar.setId(mFRightLampFar.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(258, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mFRightLampFar, params);
        
		mFRightLampNear = new ImageView(mContext);
		mFRightLampNear.setImageResource(R.drawable.main_car_control_front_lamp_right_near);
		mFRightLampNear.setId(mFRightLampNear.hashCode());
		params = ActivityUtils.getLayoutParams(773, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mFRightLampNear, params);
        
        mFRightLampFlog = new ImageView(mContext);
		mFRightLampFlog.setImageResource(R.drawable.main_car_control_front_lamp_right_flog);
		mFRightLampFlog.setId(mFRightLampFlog.hashCode());
		params = ActivityUtils.getLayoutParams(387, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mFRightLampFlog, params);
        
        mFRightLampRotate = new ImageView(mContext);
        mFRightLampRotate.setImageResource(0);
        mFRightLampRotate.setId(mFRightLampRotate.hashCode());
		params = ActivityUtils.getLayoutParams(258, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mFRightLampRotate, params);
	}
	
	private void initFrontLeftLamp(){
		mFLeftLampFar = new ImageView(mContext);
		mFLeftLampFar.setImageResource(R.drawable.main_car_control_front_lamp_left_far);
		mFLeftLampFar.setId(mFLeftLampFar.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(258, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mFLeftLampFar, params);
        
		mFLeftLampNear = new ImageView(mContext);
		mFLeftLampNear.setImageResource(R.drawable.main_car_control_front_lamp_left_near);
		mFLeftLampNear.setId(mFLeftLampNear.hashCode());
		params = ActivityUtils.getLayoutParams(773, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mFLeftLampNear, params);
        
        mFLeftLampFlog = new ImageView(mContext);
        mFLeftLampFlog.setImageResource(R.drawable.main_car_control_front_lamp_left_flog);
        mFLeftLampFlog.setId(mFLeftLampFlog.hashCode());
		params = ActivityUtils.getLayoutParams(387, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mFLeftLampFlog, params);
        
        mFLeftLampRotate = new ImageView(mContext);
        mFLeftLampRotate.setImageResource(0);
        mFLeftLampRotate.setId(mFLeftLampRotate.hashCode());
		params = ActivityUtils.getLayoutParams(258, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mFLeftLampRotate, params);
	}
	
	private void initBehindRightLamp(){
		mBRightLampFlog = new ImageView(mContext);
		mBRightLampFlog.setImageResource(R.drawable.main_car_control_behind_lamp_right_flog);
		mBRightLampFlog.setId(mBRightLampFlog.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(387, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mBRightLampFlog, params);
        
        mBRightLampRotate = new ImageView(mContext);
        mBRightLampRotate.setImageResource(0);
        mBRightLampRotate.setId(mBRightLampRotate.hashCode());
		params = ActivityUtils.getLayoutParams(773, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mBRightLampRotate, params);
	}
	
	private void initBehindLeftLamp(){
		mBLeftLampFlog = new ImageView(mContext);
		mBLeftLampFlog.setImageResource(R.drawable.main_car_control_behind_lamp_left_flog);
		mBLeftLampFlog.setId(mBLeftLampFlog.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(387, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mBLeftLampFlog, params);
        
        mBLeftLampRotate = new ImageView(mContext);
        mBLeftLampRotate.setImageResource(0);
        mBLeftLampRotate.setId(mBLeftLampRotate.hashCode());
		params = ActivityUtils.getLayoutParams(773, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mBLeftLampRotate, params);
	}
	
	
	private void initRightCarDoor(){
		mRightCarDoor = new ImageView(mContext);
		mRightCarDoor.setImageResource(R.drawable.main_car_control_door_right_close);
		mRightCarDoor.setId(mRightCarDoor.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(773, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mRightCarDoor, params);
	}
	
	private void initLeftCarDoor(){
		mLeftCarDoor = new ImageView(mContext);
		mLeftCarDoor.setImageResource(R.drawable.main_car_control_door_left_open);
		mLeftCarDoor.setId(mLeftCarDoor.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(773, 270);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mLeftCarDoor, params);
	}
	
	private void initCarRainWiper(){
		mCarRainWiper = new ImageView(mContext);
		mCarRainWiper.setImageResource(R.drawable.main_car_control_rain_wiper);
		mCarRainWiper.setId(mCarRainWiper.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(122, 236);
		params.leftMargin = ActivityUtils.scaleX(200);
		params.topMargin = ActivityUtils.scaleY(130);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mCarControlLayout.addView(mCarRainWiper, params);
        
		mCarRainWiperS1 = new ImageView(mContext);
		mCarRainWiperS1.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
		mCarRainWiperS1.setBackgroundResource(R.drawable.main_rain_wiper_background);
		mCarRainWiperS1.setId(mCarRainWiperS1.hashCode());
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(28);
		params.bottomMargin = ActivityUtils.scaleY(48);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperS1, params);
        
		mCarRainWiperTxt1 = new TextView(mContext);
		mCarRainWiperTxt1.setText(R.string.main_rain_wiper_close);
		mCarRainWiperTxt1.setTextSize((mContext.getResources().getDimension(R.dimen.main_text_rainwiper)));
		mCarRainWiperTxt1.setTextColor(Color.WHITE);
		mCarRainWiperTxt1.setGravity(Gravity.CENTER);
		mCarRainWiperTxt1.setId(mCarRainWiperTxt1.hashCode());
		params = ActivityUtils.getLayoutParams(76, 28);
		params.leftMargin = ActivityUtils.scaleX(18);
		params.bottomMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperTxt1, params);
             
        //=====================
		mCarRainWiperS2 = new ImageView(mContext);
		mCarRainWiperS2.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
		mCarRainWiperS2.setBackgroundResource(R.drawable.main_rain_wiper_background);
		mCarRainWiperS2.setId(mCarRainWiperS2.hashCode());
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(28);
		params.bottomMargin = ActivityUtils.scaleY(48);
        params.addRule(RelativeLayout.RIGHT_OF, mCarRainWiperS1.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperS2, params);
               
		mCarRainWiperTxt2 = new TextView(mContext);
		mCarRainWiperTxt2.setText(R.string.main_rain_wiper_slow);
		mCarRainWiperTxt2.setTextSize((mContext.getResources().getDimension(R.dimen.main_text_rainwiper)));
		mCarRainWiperTxt2.setTextColor(Color.WHITE);
		mCarRainWiperTxt2.setGravity(Gravity.CENTER);
		mCarRainWiperTxt2.setId(mCarRainWiperTxt2.hashCode());
		params = ActivityUtils.getLayoutParams(76, 28);
		params.leftMargin = ActivityUtils.scaleX(8);
		params.bottomMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCarRainWiperTxt1.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperTxt2, params);
        
        //=====================================================
		mCarRainWiperS3 = new ImageView(mContext);
		mCarRainWiperS3.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
		mCarRainWiperS3.setBackgroundResource(R.drawable.main_rain_wiper_background);
		mCarRainWiperS3.setId(mCarRainWiperS3.hashCode());
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(28);
		params.bottomMargin = ActivityUtils.scaleY(48);
        params.addRule(RelativeLayout.RIGHT_OF, mCarRainWiperS2.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperS3, params);
        
		mCarRainWiperTxt3 = new TextView(mContext);
		mCarRainWiperTxt3.setText(R.string.main_rain_wiper_middle);
		mCarRainWiperTxt3.setTextSize((mContext.getResources().getDimension(R.dimen.main_text_rainwiper)));
		mCarRainWiperTxt3.setTextColor(Color.WHITE);
		mCarRainWiperTxt3.setGravity(Gravity.CENTER);
		mCarRainWiperTxt3.setId(mCarRainWiperTxt3.hashCode());
		params = ActivityUtils.getLayoutParams(76, 28);
		params.leftMargin = ActivityUtils.scaleX(8);
		params.bottomMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCarRainWiperTxt2.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperTxt3, params);
        
        
        //=======================================================
		mCarRainWiperS4 = new ImageView(mContext);
		mCarRainWiperS4.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
		mCarRainWiperS4.setBackgroundResource(R.drawable.main_rain_wiper_background);
		mCarRainWiperS4.setId(mCarRainWiperS4.hashCode());
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(28);
		params.bottomMargin = ActivityUtils.scaleY(48);
        params.addRule(RelativeLayout.RIGHT_OF, mCarRainWiperS3.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperS4, params);
        
		mCarRainWiperTxt4 = new TextView(mContext);
		mCarRainWiperTxt4.setText(R.string.main_rain_wiper_fast);
		mCarRainWiperTxt4.setTextSize((mContext.getResources().getDimension(R.dimen.main_text_rainwiper)));
		mCarRainWiperTxt4.setTextColor(Color.WHITE);
		mCarRainWiperTxt4.setGravity(Gravity.CENTER);
		mCarRainWiperTxt4.setId(mCarRainWiperTxt4.hashCode());
		params = ActivityUtils.getLayoutParams(76, 28);
		params.leftMargin = ActivityUtils.scaleX(8);
		params.bottomMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.RIGHT_OF, mCarRainWiperTxt3.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCarControlLayout.addView(mCarRainWiperTxt4, params);      
        //=============================================================
        mCarRainWiperS1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return;
				}
				CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_CLOSE);
				mIControl.actionCarControlEvent(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, CarStatusHelper.RAIN_WIPER_CLOSE);
				mIControl.updateCarContentCenter(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, null);
			}
		});
        mCarRainWiperS2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return;
				}
				CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_SLOW);
				mIControl.actionCarControlEvent(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, CarStatusHelper.RAIN_WIPER_SLOW);
				mIControl.updateCarContentCenter(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, null);
			}
		});
        mCarRainWiperS3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return;
				}
				CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_MIDDLE);
				mIControl.actionCarControlEvent(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, CarStatusHelper.RAIN_WIPER_MIDDLE);
				mIControl.updateCarContentCenter(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, null);
			}
		});
        mCarRainWiperS4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return;
				}
				CarStatusHelper.getInstance().setCarRainWiperStage(CarStatusHelper.RAIN_WIPER_FAST);
				mIControl.actionCarControlEvent(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, CarStatusHelper.RAIN_WIPER_FAST);
				mIControl.updateCarContentCenter(IControl.EVENT_RAIN_WIPER_STAGE_CONTROL, null);
			}
		});
	}
	
	
	private DoorSash mDoorSash1;
	private DoorSash mDoorSash2;
	private TextView mDoorSashText1;
	private TextView mDoorSashText2;
	private RelativeLayout mDoorSashArea;
	private void initCarDoorSash(){
		mDoorSashArea = new RelativeLayout(mContext);
		mDoorSashArea.setId(mDoorSashArea.hashCode());
		mDoorSashArea.setBackgroundResource(R.drawable.main_content_doorsash_background);
		LayoutParams params = ActivityUtils.getLayoutParams(720, 463);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mCarDoorSashLayout.addView(mDoorSashArea, params);
		
		mDoorSash1 = new DoorSash(mContext);
		mDoorSash1.setId(mDoorSash1.hashCode());
		mDoorSash1.setStatusListener(new DoorSash.StatusListener() {
			public void OnStatusChange(float percent) {
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return;
				}
				CarStatusHelper.getInstance().setRightWindowPercent(percent);
				mIControl.actionCarControlEvent(IControl.EVENT_RIGHT_WINDOW_CONTROL, (int)(100 * percent));
				mIControl.updateCarContentCenter(IControl.EVENT_RIGHT_WINDOW_CONTROL, null);
				VehicleApp.makeToast("" + (int)(100 * percent) + "%");
			}
		});
		params = ActivityUtils.getLayoutParams(
				DoorSash.getLayoutWidth(), DoorSash.getLayoutHeight());
        params.leftMargin = ActivityUtils.scaleX(15);
        params.topMargin = ActivityUtils.scaleY(135);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        //mDoorSash1.setBackgroundColor(Color.RED);
        mDoorSashArea.addView(mDoorSash1,params); 
        
        mDoorSashText1 = new TextView(mContext);
        mDoorSashText1.setText(R.string.main_control_right_door);
        mDoorSashText1.setGravity(Gravity.CENTER);
        mDoorSashText1.setTextColor(Color.BLACK);
        mDoorSashText1.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		params = ActivityUtils.getLayoutParams((int)(422/1.2), 40);
        params.leftMargin = ActivityUtils.scaleX(15);
        params.addRule(RelativeLayout.BELOW, mDoorSash1.getId());
        mDoorSashArea.addView(mDoorSashText1,params); 
        
        //==========================================================
		mDoorSash2 = new DoorSash(mContext);
		mDoorSash2.setId(mDoorSash2.hashCode());
		mDoorSash2.setStatusListener(new DoorSash.StatusListener() {
			public void OnStatusChange(float percent) {
				if (!(Boolean) mIControl.getCarContorlStatus(
						IControl.EVENT_GET_CAR_CENTER_CONTENT_VISIBLE)){
					return;
				}
				CarStatusHelper.getInstance().setLeftWindowPercent(percent);
				mIControl.actionCarControlEvent(IControl.EVENT_LEFT_WINDOW_CONTROL, (int)(100 * percent));
				mIControl.updateCarContentCenter(IControl.EVENT_LEFT_WINDOW_CONTROL, null);
				VehicleApp.makeToast("" + (int)(100 * percent) + "%");
			}
		});
		params = ActivityUtils.getLayoutParams(
				DoorSash.getLayoutWidth(), DoorSash.getLayoutHeight());
        params.rightMargin = ActivityUtils.scaleX(15);
        params.topMargin = ActivityUtils.scaleY(135);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mDoorSashArea.addView(mDoorSash2,params); 
        
        mDoorSashText2 = new TextView(mContext);
        mDoorSashText2.setText(R.string.main_control_left_door);
        mDoorSashText2.setGravity(Gravity.CENTER);
        mDoorSashText2.setTextColor(Color.BLACK);
        mDoorSashText2.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		params = ActivityUtils.getLayoutParams((int)(422/1.2), 40);
        params.leftMargin = ActivityUtils.scaleX(15);
        params.topMargin = ActivityUtils.scaleY(5);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.BELOW, mDoorSash2.getId());
        mDoorSashArea.addView(mDoorSashText2,params); 
      
        //========================================================
        
        mCarDoorSashLayout.setVisibility(View.GONE);
        mCarDoorSashLayout.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
	}
	
	public void updateCarInfoLayoutVisible(boolean visible){
		if (visible){
			mCarControlLayout.setVisibility(View.GONE);
			mCarDoorSashLayout.setVisibility(View.GONE);
			mCarAirConditionLayout.setVisibility(View.GONE);
			mCarInfoLayout.setVisibility(View.VISIBLE);
		}
	}
	
	public void updateStatus(int event){
		switch (event) {
		case IControl.EVENT_LAMP_CONTROL:
			mCarControlLayout.setVisibility(View.VISIBLE);
			mCarDoorSashLayout.setVisibility(View.GONE);
			mCarAirConditionLayout.setVisibility(View.GONE);
			mCarInfoLayout.setVisibility(View.GONE);
			CarStatusHelper.getInstance().setCarRainWiperStatus(false);
			updateCarRainWiper();
			break;
			
		case IControl.EVENT_CAR_WINDOW_CONTROL:
			mCarControlLayout.setVisibility(View.VISIBLE);
			mCarAirConditionLayout.setVisibility(View.GONE);
			mCarInfoLayout.setVisibility(View.GONE);
			CarStatusHelper.getInstance().setCarRainWiperStatus(false);
			updateCarRainWiper();
			
			mCarDoorSashLayout.setVisibility(View.VISIBLE);
			break;
			
		case IControl.EVENT_AIR_CONDITION_CONTROL:
			mCarControlLayout.setVisibility(View.VISIBLE);
			mCarDoorSashLayout.setVisibility(View.GONE);
			mCarInfoLayout.setVisibility(View.GONE);
			CarStatusHelper.getInstance().setCarRainWiperStatus(false);
			updateCarRainWiper();
			
			if (mContentAirCondition == null){
				getContentAirCondition();
			}
			mCarAirConditionLayout.setVisibility(View.VISIBLE);

			break;
			
		case IControl.EVENT_RAIN_WIPER_CONTROL:
			mCarControlLayout.setVisibility(View.VISIBLE);
			mCarDoorSashLayout.setVisibility(View.GONE);
			mCarAirConditionLayout.setVisibility(View.GONE);
			mCarInfoLayout.setVisibility(View.GONE);
			CarStatusHelper.getInstance().setCarRainWiperStatus(true);
			updateCarRainWiper();
			break;
			
		case IControl.EVENT_LAMP_FAR_LIGHT_CONTROL:
			updateLampFarLight();
			break;
		case IControl.EVENT_LAMP_NEAR_LIGHT_CONTROL:
			updateLampNearLight();
			break;
		
		case IControl.EVENT_LAMP_FRONT_FLOG_CONTROL:
			updateLampFrontFlog();
			break;
			
		case IControl.EVENT_LAMP_BEHIND_FLOG_CONTROL:
			updateLampBehindFlog();
			break;
			
			
			
		case IControl.EVENT_RIGHT_WINDOW_CONTROL:
			updateCarRightWindow();
			break;
		case IControl.EVENT_LEFT_WINDOW_CONTROL:
			updateCarLeftWindow();
			break;
		case IControl.EVENT_LAMP_ROTATE_RIGHT_CONTROL:
			updateRotateRightLamp();
			break;
		case IControl.EVENT_LAMP_ROTATE_LEFT_CONTROL:
			updateRotateLeftLamp();
			break;
		case IControl.EVENT_DOOR_RIGHT_OPEN_CONTROL:
			updateCarFrontRightDoor();
			break;
		case IControl.EVENT_DOOR_LEFT_OPEN_CONTROL:
			updateCarFrontLeftDoor();
			break;
		case IControl.EVENT_RAIN_WIPER_STAGE_CONTROL:
			updateCarRainWiperStage();
			break;
		default:
			break;
		}
	}
	
	private void initStatus(){
		updateLampFarLight();
		updateLampNearLight();
		updateLampFrontFlog();
		updateLampBehindFlog();
		updateCarFrontLeftDoor();
		updateCarFrontRightDoor();
		updateCarRainWiper();
		updateCarRainWiperStage();
		
		updateRotateRightLamp();
		updateRotateLeftLamp();
		
		updateCarRightWindow();
		updateCarLeftWindow();
	}
	
	private void updateDoorSash(){
		if (mCarDoorSashLayout != null){
			if (mCarDoorSashLayout.getVisibility() == View.GONE){
				mCarDoorSashLayout.setVisibility(View.VISIBLE);
			}else{
				mCarDoorSashLayout.setVisibility(View.GONE);
			}
		}
	}
	
	private void updateLampFarLight(){
		if (mFRightLampFar != null && mFLeftLampFar != null){
			boolean open = CarStatusHelper.getInstance().getLampFarlightStatus();
			mFRightLampFar.setImageResource(open ? R.drawable.main_car_control_front_lamp_right_far : 0);
			mFLeftLampFar.setImageResource(open ? R.drawable.main_car_control_front_lamp_left_far : 0);
		}
	}
	
	private void updateLampNearLight(){
		if (mFRightLampNear != null && mFLeftLampNear != null){
			boolean open = CarStatusHelper.getInstance().getLampNearlightStatus();
			mFRightLampNear.setImageResource(open ? R.drawable.main_car_control_front_lamp_right_near : 0);
			mFLeftLampNear.setImageResource(open ? R.drawable.main_car_control_front_lamp_left_near : 0);
		}
	}
	
	private void updateLampFrontFlog(){
		if (mFRightLampFlog != null && mFLeftLampFlog != null){
			boolean open = CarStatusHelper.getInstance().getLampFrontflogStatus();
			mFRightLampFlog.setImageResource(open ? R.drawable.main_car_control_front_lamp_right_flog : 0);
			mFLeftLampFlog.setImageResource(open ? R.drawable.main_car_control_front_lamp_left_flog : 0);
		}
	}
	
	
	private void updateLampBehindFlog(){
		if (mBRightLampFlog != null && mBLeftLampFlog != null){
			boolean open = CarStatusHelper.getInstance().getLampBehindflogStatus();
			mBRightLampFlog.setImageResource(open ? R.drawable.main_car_control_behind_lamp_right_flog : 0);
			mBLeftLampFlog.setImageResource(open ? R.drawable.main_car_control_behind_lamp_left_flog : 0);
		}
	}
	
	
	private void updateCarFrontRightDoor(){
		if (mRightCarDoor != null){
			boolean open = CarStatusHelper.getInstance().getCarFrontDoorRightStatus();
			mRightCarDoor.setImageResource(open ? R.drawable.main_car_control_door_right_open 
					: R.drawable.main_car_control_door_right_close);
		}
	}
	
	private void updateCarFrontLeftDoor(){
		if (mLeftCarDoor != null){
			boolean open = CarStatusHelper.getInstance().getCarFrontDoorLeftStatus();
			mLeftCarDoor.setImageResource(open ? R.drawable.main_car_control_door_left_open
					:  R.drawable.main_car_control_door_left_close);
		}
	}
	
	private void updateCarRightWindow(){
		float percent = CarStatusHelper.getInstance().getRightWindowPercent();
		mDoorSash1.setSash(percent);
	}
	
	private void updateCarLeftWindow(){
		float percent = CarStatusHelper.getInstance().getLeftWindowPercent();
		mDoorSash2.setSash(percent);
	}
	
	
	private AnimationDrawable mFRightLampRotateDrawable;
	private AnimationDrawable mBRightLampRotateDrawable;
	private void updateRotateRightLamp(){
		if (mFRightLampRotate != null && mBRightLampRotate != null){
			boolean open = CarStatusHelper.getInstance().getCarLampRotateRightStatus();
			//mFRightLampRotate.setVisibility(open ? View.VISIBLE : View.GONE);
			//.setVisibility(open ? View.VISIBLE : View.GONE);

			if (mFRightLampRotateDrawable != null){
				mFRightLampRotateDrawable.stop();
			}
			if (mBRightLampRotateDrawable != null){
				mBRightLampRotateDrawable.stop();
			}
			
			mFRightLampRotate.setImageResource(open ? 
					R.drawable.main_rotate_right_front_lamp : 0);
			mBRightLampRotate.setImageResource(open ? 
					R.drawable.main_rotate_right_behind_lamp : 0);
			if (open){
				mFRightLampRotateDrawable = (AnimationDrawable) mFRightLampRotate.getDrawable();
				mBRightLampRotateDrawable = (AnimationDrawable) mBRightLampRotate.getDrawable();
				mFRightLampRotateDrawable.start();
				mBRightLampRotateDrawable.start();
			}
		}
	}
	
	private AnimationDrawable mFLeftLampRotateDrawable;
	private AnimationDrawable mBLeftLampRotateDrawable;
	private void updateRotateLeftLamp(){
		if (mFLeftLampRotate != null && mBLeftLampRotate != null){
			boolean open = CarStatusHelper.getInstance().getCarLampRotateLeftStatus();
			if (mFLeftLampRotateDrawable != null){
				mFLeftLampRotateDrawable.stop();
			}
			if (mBLeftLampRotateDrawable != null){
				mBLeftLampRotateDrawable.stop();
			}
			
			mFLeftLampRotate.setImageResource(open ? 
					R.drawable.main_rotate_left_front_lamp : 0);
			mBLeftLampRotate.setImageResource(open ? 
					R.drawable.main_rotate_left_behind_lamp : 0);
			if (open){
				mFLeftLampRotateDrawable = (AnimationDrawable) mFLeftLampRotate.getDrawable();
				mBLeftLampRotateDrawable = (AnimationDrawable) mBLeftLampRotate.getDrawable();
				mFLeftLampRotateDrawable.start();
				mBLeftLampRotateDrawable.start();
			}
		}
	}
	
	private void updateCarRainWiperStage(){
		if (mCarRainWiperS1 != null && mCarRainWiperS2 != null 
				&& mCarRainWiperS3 != null && mCarRainWiperS4 != null){
			int stage = CarStatusHelper.getInstance().getCarRainWiperStage();
			if (stage == CarStatusHelper.RAIN_WIPER_CLOSE){
				mCarRainWiperS1.setImageResource(R.drawable.main_car_control_rain_wiper_select);
				mCarRainWiperS2.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS3.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS4.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
			}else if (stage == CarStatusHelper.RAIN_WIPER_SLOW){
				mCarRainWiperS1.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS2.setImageResource(R.drawable.main_car_control_rain_wiper_select);
				mCarRainWiperS3.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS4.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
			}else if (stage == CarStatusHelper.RAIN_WIPER_MIDDLE){
				mCarRainWiperS1.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS2.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS3.setImageResource(R.drawable.main_car_control_rain_wiper_select);
				mCarRainWiperS4.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
			}else if (stage == CarStatusHelper.RAIN_WIPER_FAST){
				mCarRainWiperS1.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS2.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS3.setImageResource(R.drawable.main_car_control_rain_wiper_normal);
				mCarRainWiperS4.setImageResource(R.drawable.main_car_control_rain_wiper_select);
			}
		}
	}
	
	private void updateCarRainWiper(){
		if (mCarRainWiperS1 != null && mCarRainWiperS2 != null 
				&& mCarRainWiperS3 != null && mCarRainWiperS4 != null){
			boolean open = CarStatusHelper.getInstance().getCarRainWiperStatus();
			mCarRainWiperS1.setVisibility(open? View.VISIBLE : View.GONE);
			mCarRainWiperS2.setVisibility(open? View.VISIBLE : View.GONE);
			mCarRainWiperS3.setVisibility(open? View.VISIBLE : View.GONE);
			mCarRainWiperS4.setVisibility(open? View.VISIBLE : View.GONE);
			
			mCarRainWiperTxt1.setVisibility(open? View.VISIBLE : View.GONE);
			mCarRainWiperTxt2.setVisibility(open? View.VISIBLE : View.GONE);
			mCarRainWiperTxt3.setVisibility(open? View.VISIBLE : View.GONE);
			mCarRainWiperTxt4.setVisibility(open? View.VISIBLE : View.GONE);
		}
	}
	
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
		};
	};
}