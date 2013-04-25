package com.zjdx.vehicle.activity.main.ui.bottom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.common.CarInfoConstant;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;



public class ContentAreaInfo{
	private static final String TAG = "ContentAreaInfo";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private TextView[] mTextTabArrays = new TextView[4];
	private TextView[][] mTextInfoArrays = new TextView[7][4];
	
	private ListView mInfoList;
	private FourCloumnListAdapter mFCloumnsAdapter;
	private TwoCloumnListAdapter mTCloumnsAdapter;
	private ArrayList<TwoCloumnInfo> mArrayList = new ArrayList<TwoCloumnInfo>(); 
	
	private IControl mIControl;
	public ContentAreaInfo(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayout = layout;
		
		initLayout();
	}
	
	private void initLayout(){
		LinearLayout tableLayout = new LinearLayout(mContext);
		tableLayout.setId(tableLayout.hashCode());
		tableLayout.setBackgroundResource(R.drawable.main_info_top_bar);
		LayoutParams params = ActivityUtils.getLayoutParams(720, 56);
		//params.leftMargin = ActivityUtils.scaleX(25);
		params.topMargin = ActivityUtils.scaleY(20);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mParentLayout.addView(tableLayout, params);
		
		//init content tab
		for (int i=0; i<4; i++){
			mTextTabArrays[i] = new TextView(mContext);
			mTextTabArrays[i].setText("" + i);
			mTextTabArrays[i].setGravity(Gravity.CENTER);
			//mTextTabArrays[i].setWidth(ActivityUtils.scaleX(180));
			//mTextTabArrays[i].setHeight(ActivityUtils.scaleY(56));
			mTextTabArrays[i].setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			mTextTabArrays[i].setBackgroundResource(R.drawable.main_info_tab_drawable);
			params = ActivityUtils.getLayoutParams(180, 56);
			tableLayout.addView(mTextTabArrays[i], params);
		}
		
		LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInfoList = (ListView) mInflater.inflate(R.layout.main_listview, null);
		mInfoList.setBackgroundResource(R.drawable.main_info_background);
		mInfoList.setCacheColorHint(Color.TRANSPARENT);
		mInfoList.setScrollbarFadingEnabled(false);
		params = ActivityUtils.getLayoutParams(720, 407);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		params.addRule(RelativeLayout.BELOW, tableLayout.getId());
		mInfoList.setPadding(ActivityUtils.scaleX(20), ActivityUtils.scaleY(0), ActivityUtils.scaleX(5), ActivityUtils.scaleY(5));
		mParentLayout.addView(mInfoList, params);
		
		mFCloumnsAdapter = new FourCloumnListAdapter(mContext);
		mTCloumnsAdapter = new TwoCloumnListAdapter(mContext);
		//mInfoList.setAdapter(mFCloumnsAdapter);
		
		TwoCloumnInfo info = new TwoCloumnInfo();
		for (int i=0; i<21; i++){
			 info = new TwoCloumnInfo();
			 info.name = "123";
			 info.value = "456";
			 mArrayList.add(info);
		}
		//mFCloumnsAdapter.setData(mArrayList);
		//mFCloumnsAdapter.notifyDataSetChanged();
//		//init content
//		for (int i=0; i<7; i++){
//			tableRow = new TableRow(mContext); 
//			tableRow.setOrientation(LinearLayout.HORIZONTAL);
//			tableLayout.addView(tableRow);
//			for (int j=0; j<4; j++){
//				mTextInfoArrays[i][j] = new TextView(mContext);
//				mTextInfoArrays[i][j].setText("" + i + j);
//				//mTextInfoArrays[i][j].setGravity(Gravity.CENTER_VERTICAL);
//				mTextInfoArrays[i][j].setWidth(ActivityUtils.scaleX(170));
//				mTextInfoArrays[i][j].setHeight(ActivityUtils.scaleY(56));
//				//mTextInfoArrays[i][j].setBackgroundColor(Color.RED);
//				mTextInfoArrays[i][j].setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
//				tableRow.addView(mTextInfoArrays[i][j]);
//			}
//		}
		
	}
	
	private void resetTextTabArrays(){
		for (int i=0; i<4; i++){
			mTextTabArrays[i].setText("");
			mTextTabArrays[i].setOnClickListener(null);
			mTextTabArrays[i].setEnabled(false);
		}
	}
	
	private void resetTextInfoArrays(){
		for (int i=0; i<7; i++){
			for (int j=0; j<4; j++){
				mTextInfoArrays[i][j].setText("");
			}
		}
	}
	
	
	public void showContentInfo(int event, Object obj){
		switch (event) {
		case IControl.SHOW_INFO_MESSAGE_BASE_INFO:
		case IControl.SHOW_INFO_MESSAGE_CAR_STATUS:
		case IControl.SHOW_INFO_MESSAGE_OTHER_INFO:
			if (obj != null){
				mArrayList = (ArrayList<TwoCloumnInfo>) obj;
			}
			showMessageInfo(event);
			break;
			
		case IControl.SHOW_INFO_BATTERY_BASE_INFO:
		case IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO:
		case IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS:
		case IControl.SHOW_INFO_BATTERY_FAULT:
			if (obj != null){
				mArrayList = (ArrayList<TwoCloumnInfo>) obj;
			}
			showBatteryInfo(event);
			break;
			
		case IControl.SHOW_INFO_MOTOR_MAIN:
			if (obj != null){
				mArrayList = (ArrayList<TwoCloumnInfo>) obj;
			}
			showMotorInfo(event);
			break;
		case IControl.SHOW_INFO_AIR_CONDITION_MAIN:
			if (obj != null){
				mArrayList = (ArrayList<TwoCloumnInfo>) obj;
			}
			showAirConditionInfo(event);
			break;
		default:
			break;
		}
	}
	
	
	private void showMessageInfo(int tab){
		resetTextTabArrays();
		//resetTextInfoArrays();
		
		mTextTabArrays[0].setText("基本信息");
		mTextTabArrays[1].setText("车身状态");
		mTextTabArrays[2].setText("其他");
		mTextTabArrays[0].setEnabled(true);
		mTextTabArrays[1].setEnabled(true);
		mTextTabArrays[2].setEnabled(true);
		mTextTabArrays[0].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_MESSAGE_BASE_INFO, null);
			}
		});
		mTextTabArrays[1].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_MESSAGE_CAR_STATUS, null);
			}
		});
		mTextTabArrays[2].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_MESSAGE_OTHER_INFO, null);
			}
		});
		
		if (tab == IControl.SHOW_INFO_MESSAGE_BASE_INFO){
//			for (int i=0; i<7; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.MESSAGE_TEXT_BASE_INFO_ITEM[i]);
//			}
			mInfoList.setAdapter(mTCloumnsAdapter);
			mTCloumnsAdapter.setData(mArrayList);
			mTCloumnsAdapter.notifyDataSetChanged();
		}else if(tab == IControl.SHOW_INFO_MESSAGE_CAR_STATUS){
//			for (int i=0; i<7; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.MESSAGE_TEXT_CAR_STATUS_LEFT[i]);
//				mTextInfoArrays[i][2].setText(CarInfoConstant.MESSAGE_TEXT_CAR_STATUS_RIGHT[i]);
//			}
			mInfoList.setAdapter(mFCloumnsAdapter);
			mFCloumnsAdapter.setData(mArrayList);
			mFCloumnsAdapter.notifyDataSetChanged();
		}else if(tab == IControl.SHOW_INFO_MESSAGE_OTHER_INFO){
//			for (int i=0; i<4; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.MESSAGE_TEXT_OTHER_INFO_LEFT[i]);
//				mTextInfoArrays[i][2].setText(CarInfoConstant.MESSAGE_TEXT_OTHER_INFO_RIGHT[i]);
//			}
			mInfoList.setAdapter(mFCloumnsAdapter);
			mFCloumnsAdapter.setData(mArrayList);
			mFCloumnsAdapter.notifyDataSetChanged();
		}
	}
	
	
	
	private void showBatteryInfo(int tab){
		resetTextTabArrays();
		//resetTextInfoArrays();
		
		mTextTabArrays[0].setText("基本信息");
		mTextTabArrays[1].setText("模块基本信息");
		mTextTabArrays[2].setText("充电系统状态");
		mTextTabArrays[3].setText("电池故障");
		mTextTabArrays[0].setEnabled(true);
		mTextTabArrays[1].setEnabled(true);
		mTextTabArrays[2].setEnabled(true);
		mTextTabArrays[3].setEnabled(true);
		mTextTabArrays[0].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_BATTERY_BASE_INFO, null);
			}
		});
		mTextTabArrays[1].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO, null);
			}
		});
		mTextTabArrays[2].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS, null);
			}
		});
		mTextTabArrays[3].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_BATTERY_FAULT, null);
			}
		});
		
		if (tab == IControl.SHOW_INFO_BATTERY_BASE_INFO){
//			for (int i=0; i<7; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.BATTERY_BASE_INFO_ITEM[i]);
//			}
			mInfoList.setAdapter(mTCloumnsAdapter);
			mTCloumnsAdapter.setData(mArrayList);
			mTCloumnsAdapter.notifyDataSetChanged();
		}else if(tab == IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO){
//			for (int i=0; i<4; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.BATTERY_MODEL_BASE_INFO_ITEM_LEFT[i]);
//			}
//			for (int i=0; i<5; i++){
//				mTextInfoArrays[i][2].setText(CarInfoConstant.MESSAGE_TEXT_CAR_STATUS_RIGHT[i]);
//			}
			mInfoList.setAdapter(mFCloumnsAdapter);
			mFCloumnsAdapter.setData(mArrayList);
			mFCloumnsAdapter.notifyDataSetChanged();
		}else if(tab == IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS){
//			for (int i=0; i<6; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.BATTERY_CHARGING_SYSTEM_STATUS_ITEM[i]);
//			}
			mInfoList.setAdapter(mTCloumnsAdapter);
			mTCloumnsAdapter.setData(mArrayList);
			mTCloumnsAdapter.notifyDataSetChanged();
		}else if (tab == IControl.SHOW_INFO_BATTERY_FAULT){
			mInfoList.setAdapter(mTCloumnsAdapter);
			mTCloumnsAdapter.setData(mArrayList);
			mTCloumnsAdapter.notifyDataSetChanged();
		}
	}
	
	
	private void showMotorInfo(int tab){
		resetTextTabArrays();
		//resetTextInfoArrays();
		
		mTextTabArrays[0].setText("电机运转状态");
		mTextTabArrays[0].setEnabled(true);
		if (tab == IControl.SHOW_INFO_MOTOR_MAIN){
//			for (int i=0; i<5; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.MOTOR_MAIN_ITEM_LEFT[i]);
//			}
//			for (int i=0; i<4; i++){
//				mTextInfoArrays[i][2].setText(CarInfoConstant.MOTOR_MAIN_ITEM_RIGHT[i]);
//			}
			mInfoList.setAdapter(mFCloumnsAdapter);
			mFCloumnsAdapter.setData(mArrayList);
			mFCloumnsAdapter.notifyDataSetChanged();
			
		}
	}
	
	private void showAirConditionInfo(int tab){
		resetTextTabArrays();
		//resetTextInfoArrays();
		
		mTextTabArrays[0].setText("空调状态");
		mTextTabArrays[0].setEnabled(true);
		if (tab == IControl.SHOW_INFO_AIR_CONDITION_MAIN){
//			for (int i=0; i<3; i++){
//				mTextInfoArrays[i][0].setText(CarInfoConstant.AIR_CONDITION_MAIN_ITEM[i]);
//			}
			mInfoList.setAdapter(mTCloumnsAdapter);
			mTCloumnsAdapter.setData(mArrayList);
			mTCloumnsAdapter.notifyDataSetChanged();
		}
	}
}