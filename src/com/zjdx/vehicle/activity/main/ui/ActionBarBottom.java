package com.zjdx.vehicle.activity.main.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;


public class ActionBarBottom implements OnClickListener{
	private static final String TAG = "MainControl";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private TextView mBtn_0;
	private TextView mBtn_1;
	private TextView mBtn_2;
	private TextView mBtn_3;
	private TextView mBtn_4;
	private TextView mBtn_5;
	private TextView mBtn_6;
	private TextView mBtn_7;
	
	private IControl mIControl;
	public ActionBarBottom(Context context, IControl control){
		mContext = context;
		mIControl = control;
		
		init();
	}
	
	public void init(){
		mParentLayout = (RelativeLayout) 
				((Activity)mContext).findViewById(R.id.bottom_area);
		
		if (mParentLayout != null){
			mBtn_0 = (TextView) mParentLayout.findViewById(R.id.action_b0);
			mBtn_1 = (TextView) mParentLayout.findViewById(R.id.action_b1);
			mBtn_2 = (TextView) mParentLayout.findViewById(R.id.action_b2);
			mBtn_3 = (TextView) mParentLayout.findViewById(R.id.action_b3);
			mBtn_4 = (TextView) mParentLayout.findViewById(R.id.action_b4);
			mBtn_5 = (TextView) mParentLayout.findViewById(R.id.action_b5);
			mBtn_6 = (TextView) mParentLayout.findViewById(R.id.action_b6);
			mBtn_7 = (TextView) mParentLayout.findViewById(R.id.action_b7);
			
			mBtn_0.setOnClickListener(this);
			mBtn_1.setOnClickListener(this);
			mBtn_2.setOnClickListener(this);
			mBtn_3.setOnClickListener(this);
			mBtn_4.setOnClickListener(this);
			mBtn_5.setOnClickListener(this);
			mBtn_6.setOnClickListener(this);
			mBtn_7.setOnClickListener(this);
			
			mBtn_0.setTag(0);
			mBtn_1.setTag(1);
			mBtn_2.setTag(2);
			mBtn_3.setTag(3);
			mBtn_4.setTag(4);
			mBtn_5.setTag(5);
			mBtn_6.setTag(6);
			mBtn_7.setTag(7);
			
			mBtn_0.setText(R.string.main_bottom_message);
			mBtn_1.setText(R.string.main_bottom_battery);
			mBtn_2.setText(R.string.main_bottom_motor);
			mBtn_3.setText(R.string.main_bottom_air_conditioning);
			mBtn_4.setText(R.string.main_bottom_fault);
			mBtn_5.setText(R.string.main_bottom_diagnosis);
			mBtn_6.setText(R.string.main_bottom_vehicle_maintenance);
			mBtn_7.setText(R.string.main_bottom_setting);
			
			float size = mContext.getResources().getDimension(R.dimen.main_text_size);
			mBtn_0.setTextSize(size);
			mBtn_1.setTextSize(size);
			mBtn_2.setTextSize(size);
			mBtn_3.setTextSize(size);
			mBtn_4.setTextSize(size);
			mBtn_5.setTextSize(size);
			mBtn_6.setTextSize(size);
			mBtn_7.setTextSize(size);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == mBtn_0){
			mIControl.showCarInfomation(IControl.SHOW_INFO_MESSAGE_BASE_INFO, null);
		}
		
		if (v == mBtn_1){
			mIControl.showCarInfomation(IControl.SHOW_INFO_BATTERY_BASE_INFO, null);
		}
		
		if (v == mBtn_2){
			mIControl.showCarInfomation(IControl.SHOW_INFO_MOTOR_MAIN, null);
		}
		
		if (v == mBtn_3){
			mIControl.showCarInfomation(IControl.SHOW_INFO_AIR_CONDITION_MAIN, null);
		}
		
		if (v == mBtn_4){
			mIControl.showCarInfomation(IControl.SHOW_INFO_FAULT, null);
		}
		
		if (v == mBtn_5){
			mIControl.showCarInfomation(IControl.SHOW_INFO_DIAGNOSIS, null);
		}
		
		if (v == mBtn_6){
			mIControl.showCarInfomation(IControl.SHOW_INFO_MAINTENANCE, null);
		}
		
		if (v == mBtn_7){
			mIControl.showCarInfomation(IControl.SHOW_INFO_SETTING, null);
		}
	}
	
}