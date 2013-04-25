package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;


public class ContentAreaLeft{
	private static final String TAG = "ContentAreaLeft";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private ImageView mImgSideWalk;
	private ImageView mImgLeftLane;
	private ImageView mImgRightLane;
	private ImageView mImgPeople;
	private ImageView mImgOtherCar;
	//private ImageView mImgSelfCar;
	private TextView mTextDuration;
	private TextView mTextSpeed;
	
	public ContentAreaLeft(Context context){
		mContext = context;
		
		init();
		initStatus();
	}
	
	public void init(){
		mParentLayout = (RelativeLayout) 
				((Activity)mContext).findViewById(R.id.content_left);
		if (mParentLayout != null){
			initCarStalls();
			initSideWalk();
			initLeftLane();
			initRightLane();
			initPeople();
			initOtherCar();
			initSelfCar();
			initDuration();
			initSpeed();
		}
	}
	
	private void initStatus(){
		updateCarStalls(1);
		updateAlertSideWalk(!false);
		updateAlertPeople(!false);
		updateAlertLeftLane(!false);
		updateAlertRightLane(!false);
		updateAlertFrontCar(1);
		updateAlertFrontCardistance("0.6  s");
		updateAlertSpeed(0);
	}
	
	private TextView mR_StallsText;
	private TextView mP_StallsText;
	private void initCarStalls(){
		mR_StallsText = new TextView(mContext);
		mR_StallsText.setTypeface(Typeface.DEFAULT_BOLD);
		mR_StallsText.setId(mR_StallsText.hashCode());
		//mTextSoc.setBackgroundColor(Color.RED);
		mR_StallsText.setTextSize(mContext.getResources().getDimension(R.dimen.main_car_stalls));
		mR_StallsText.setText("R");
		LayoutParams params = ActivityUtils.getLayoutParams(40, 40);
        params.rightMargin = ActivityUtils.scaleX(25);
        params.topMargin = ActivityUtils.scaleY(20);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParentLayout.addView(mR_StallsText, params);
        
        mP_StallsText = new TextView(mContext);
        mP_StallsText.setTypeface(Typeface.DEFAULT_BOLD);
        mP_StallsText.setId(mP_StallsText.hashCode());
		//mTextSoc.setBackgroundColor(Color.RED);
        mP_StallsText.setTextSize(mContext.getResources().getDimension(R.dimen.main_car_stalls));
		mP_StallsText.setText("P");
		params = ActivityUtils.getLayoutParams(40, 40);
        params.rightMargin = ActivityUtils.scaleX(115);
        params.topMargin = ActivityUtils.scaleY(30);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParentLayout.addView(mP_StallsText, params);
	}
	
	private void initSideWalk(){
		mImgSideWalk = new ImageView(mContext);
		mImgSideWalk.setId(mImgSideWalk.hashCode());
		mImgSideWalk.setPadding(ActivityUtils.scaleX(20), ActivityUtils.scaleY(20), 
        		ActivityUtils.scaleX(20), ActivityUtils.scaleY(20));
		LayoutParams params = ActivityUtils.getLayoutParams(372, 121);
        params.leftMargin = ActivityUtils.scaleX(140);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.topMargin = ActivityUtils.scaleY(120);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParentLayout.addView(mImgSideWalk, params);
	}
	
	private void initLeftLane(){
        mImgLeftLane = new ImageView(mContext);
		mImgLeftLane.setId(mImgLeftLane.hashCode());
		mImgLeftLane.setPadding(ActivityUtils.scaleX(40), ActivityUtils.scaleY(20), 
        		ActivityUtils.scaleX(40), ActivityUtils.scaleY(20));
		LayoutParams params = ActivityUtils.getLayoutParams(247, 238);
        params.leftMargin = ActivityUtils.scaleX(120);
        params.topMargin = ActivityUtils.scaleY(-30);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, mImgSideWalk.getId());
        mParentLayout.addView(mImgLeftLane, params);
	}
	
	private void initRightLane(){
        mImgRightLane = new ImageView(mContext);
		mImgRightLane.setId(mImgRightLane.hashCode());
		mImgRightLane.setPadding(ActivityUtils.scaleX(20), ActivityUtils.scaleY(20), 
        		ActivityUtils.scaleX(20), ActivityUtils.scaleY(20));
		LayoutParams params = ActivityUtils.getLayoutParams(207, 238);
        params.leftMargin = ActivityUtils.scaleX(-70);
        params.topMargin = ActivityUtils.scaleY(-30);
        params.addRule(RelativeLayout.RIGHT_OF, mImgLeftLane.getId());
        params.addRule(RelativeLayout.BELOW, mImgSideWalk.getId());
        mParentLayout.addView(mImgRightLane, params);
	}
	
	private void initPeople(){
        mImgPeople = new ImageView(mContext);
        mImgPeople.setId(mImgPeople.hashCode());
        mImgPeople.setPadding(ActivityUtils.scaleX(20), ActivityUtils.scaleY(20), 
        		ActivityUtils.scaleX(20), ActivityUtils.scaleY(20));
        LayoutParams params = ActivityUtils.getLayoutParams(136, 136);
        params.leftMargin = ActivityUtils.scaleX(270);
        params.topMargin = ActivityUtils.scaleY(110);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);  
        //params.addRule(RelativeLayout.ALIGN_BOTTOM, mImgSideWalk.getId());
        mParentLayout.addView(mImgPeople, params);
	}
	
	private void initOtherCar(){
        mImgOtherCar = new ImageView(mContext);
        mImgOtherCar.setId(mImgOtherCar.hashCode());
        mImgOtherCar.setPadding(ActivityUtils.scaleX(20), ActivityUtils.scaleY(20), 
        		ActivityUtils.scaleX(20), ActivityUtils.scaleY(20));
        LayoutParams params = ActivityUtils.getLayoutParams(136, 136);
        params.leftMargin = ActivityUtils.scaleX(270);
        params.topMargin = ActivityUtils.scaleY(320);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //params.addRule(RelativeLayout.BELOW, mImgPeople.getId());
        mParentLayout.addView(mImgOtherCar, params);
	}
	
	private void initSelfCar(){
//        mImgSelfCar = new ImageView(mContext);
//        mImgSelfCar.setImageResource(R.drawable.main_security_self_car);
//        mImgSelfCar.setId(mImgSelfCar.hashCode());
//        LayoutParams params = ActivityUtils.getLayoutParams(96, 96);
//        params.leftMargin = ActivityUtils.scaleX(290);
//        params.topMargin = ActivityUtils.scaleY(-20);
//        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//		params.addRule(RelativeLayout.BELOW, mImgOtherCar.getId());
//        mParentLayout.addView(mImgSelfCar, params);
	}
	
	private void initDuration(){
		mTextDuration = new TextView(mContext);
		mTextDuration.setGravity(Gravity.CENTER);
		mTextDuration.setTypeface(Typeface.DEFAULT_BOLD);
		mTextDuration.setId(mTextDuration.hashCode());
		//mTextDuration.setBackgroundColor(Color.RED);
		mTextDuration.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mTextDuration.setText("0.6 S");
        LayoutParams params = ActivityUtils.getLayoutParams(180, 40);
        params.leftMargin = ActivityUtils.scaleX(240);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.topMargin = ActivityUtils.scaleY(60);
		params.addRule(RelativeLayout.BELOW, mImgSideWalk.getId());
        mParentLayout.addView(mTextDuration, params);
	}
	
	private void initSpeed(){
        mTextSpeed = new TextView(mContext);
        mTextSpeed.setGravity(Gravity.CENTER);
        mTextSpeed.setTypeface(Typeface.DEFAULT_BOLD);
        mTextSpeed.setId(mTextSpeed.hashCode());
        //mTextSpeed.setBackgroundColor(Color.RED);
        mTextSpeed.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
        mTextSpeed.setText("1000 Km/h");
        LayoutParams params = ActivityUtils.getLayoutParams(180, 60);
        params.leftMargin = ActivityUtils.scaleX(240);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.topMargin = ActivityUtils.scaleY(100);
		params.addRule(RelativeLayout.BELOW, mTextDuration.getId());
        mParentLayout.addView(mTextSpeed, params);
	}
	
	public View getRootView(){
		return mParentLayout;
	}
	
	
	public void updateStatus(int event, Object object){
		switch (event) {
		case IControl.EVENT_SECURITY_ALERT_SIDE_WALK:
			updateAlertSideWalk((Boolean)object);
			break;
		case IControl.EVENT_SECURITY_ALERT_PEOPLE:
			updateAlertPeople((Boolean)object);
			break;
		case IControl.EVENT_SECURITY_ALERT_LEFT_LANE:
			updateAlertLeftLane((Boolean)object);
			break;
		case IControl.EVENT_SECURITY_ALERT_RIGHT_LANE:
			updateAlertRightLane((Boolean)object);
			break;
		case IControl.EVENT_SECURITY_ALERT_FRONT_CAR:
			if (object != null && object instanceof Integer){
				updateAlertFrontCar((Integer)object);
			}
			break;
		case IControl.EVENT_SECURITY_ALERT_FRONT_CAR_DISTANCE:
			updateAlertFrontCardistance((String) object);
			break;
		case IControl.EVENT_SECURITY_ALERT_CAR_SPEED:
			updateAlertSpeed((Float) object);
			break;
		case IControl.EVENT_ALERT_CAR_STALLS_LEFT:
			updateCarStalls((Integer) object);
			break;
		default:
			break;
		}
	}
	
	private AnimationDrawable mSideWalkDrawable;
	private void updateAlertSideWalk(boolean alert){
		if (mSideWalkDrawable != null){
			mSideWalkDrawable.stop();
		}
		
		mImgSideWalk.setImageResource(alert? R.drawable.main_security_people_road_drawable
				: R.drawable.main_security_people_road_nromal);
		if (alert){
			mSideWalkDrawable = (AnimationDrawable) mImgSideWalk.getDrawable();
			mSideWalkDrawable.start();
		}
		
	}
	
	private AnimationDrawable mPeopleDrawable;
	private void updateAlertPeople(boolean alert){
		if (mPeopleDrawable != null){
			mPeopleDrawable.stop();
		}
		 mImgPeople.setImageResource(alert? R.drawable.main_security_people_drawable
					: 0);
		if (alert){
			mPeopleDrawable = (AnimationDrawable) mImgPeople.getDrawable();
			mPeopleDrawable.start();
		}
	}
	
	private AnimationDrawable mLeftLaneDrawable;
	private void updateAlertLeftLane(boolean alert){
		if (mLeftLaneDrawable != null){
			mLeftLaneDrawable.stop();
		}
		mImgLeftLane.setImageResource(alert? R.drawable.main_security_left_road_drawable
				: R.drawable.main_security_left_road_normal);
		if (alert){
			mLeftLaneDrawable = (AnimationDrawable) mImgLeftLane.getDrawable();
			mLeftLaneDrawable.start();
		}
	}
	
	private AnimationDrawable mRightLaneDrawable;
	private void updateAlertRightLane(boolean alert){
		if (mRightLaneDrawable != null){
			mRightLaneDrawable.stop();
		}
		mImgRightLane.setImageResource(alert? R.drawable.main_security_right_road_drawable
				: R.drawable.main_security_right_road_normal);
		if (alert){
			mRightLaneDrawable = (AnimationDrawable) mImgRightLane.getDrawable();
			mRightLaneDrawable.start();
		}
	}
	
	private AnimationDrawable mFrontCarDrawable;
	private void updateAlertFrontCar(int value){
		if (mFrontCarDrawable != null){
			mFrontCarDrawable.stop();
		}
		
		if (value == 0){
			mImgOtherCar.setVisibility(View.INVISIBLE);
		}else if (value == 1){
			mImgOtherCar.setVisibility(View.VISIBLE);
			mImgOtherCar.setImageResource(R.drawable.main_security_self_car);
		}else if (value == 2){
			mImgOtherCar.setVisibility(View.VISIBLE);
			mImgOtherCar.setImageResource(R.drawable.main_security_other_car_red);
		}else if (value == 3){
			mImgOtherCar.setVisibility(View.VISIBLE);
			mImgOtherCar.setImageResource(R.drawable.main_security_other_car_drawable);

			mFrontCarDrawable = (AnimationDrawable) mImgOtherCar.getDrawable();
			mFrontCarDrawable.start();
		}
	}
	
	private void updateAlertFrontCardistance(String distance){
		mTextDuration.setText(distance == null ? "" : distance);
	}
	
	private void updateAlertSpeed(float speed){
		int n = (int)speed;
		int m = (int)((speed - n) * 100);
		mTextSpeed.setText("" + n + "." + m + " Km/h");
	}
	
	
	private void updateCarStalls(int stalls){
		if (stalls == 1){
			mP_StallsText.setTextColor(Color.GREEN);
			mR_StallsText.setTextColor(Color.WHITE);
		}else if (stalls == 2){
			mP_StallsText.setTextColor(Color.WHITE);
			mR_StallsText.setTextColor(Color.GREEN);
		}else{
			mP_StallsText.setTextColor(Color.WHITE);
			mR_StallsText.setTextColor(Color.WHITE);
		}
	}
}