package com.zjdx.vehicle.activity.main.ui.bottom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.R;
import com.zjdx.vehicle.VehicleApp;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;
import com.zjdx.vehicle.activity.utils.SharedPreferenceUtils;



public class ContentAreaMaintenance implements OnClickListener{
	private static final String TAG = "ContentAreaInfo";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private IControl mIControl;
	
	private RelativeLayout mMaintenanceLayout;
	private TextView mMaintenanceText;
	private RelativeLayout mSettingBrakesLayout;
	private RelativeLayout mSettingCheckLayout;
	
	private TextView mMainTenanceTab;
	private TextView mBrakesMiles; 
	private TextView mBrakesTimer;
	private TextView mCheckMiles;
	private TextView mCheckTimer;
	private TextView mSettingTab;
	
	private ImageView mBrakesMilesReset;
	private ImageView mBrakesTimerReset;
	private ImageView mCheckMilesReset;
	private ImageView mCheckTimerReset;
	
	public ContentAreaMaintenance(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayout = layout;
		
		initLayout();
		initStatus();
		updateStatus();
	}
	
	private void initLayout(){
		//==========
		mMainTenanceTab = new TextView(mContext);
		mMainTenanceTab.setId(mMainTenanceTab.hashCode());
		mMainTenanceTab.setText("保养提示");
		mMainTenanceTab.setGravity(Gravity.CENTER);
		mMainTenanceTab.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		mMainTenanceTab.setBackgroundResource(R.drawable.main_maintenance_tab_select);
		LayoutParams params = ActivityUtils.getLayoutParams(274, 73);
		params.leftMargin = ActivityUtils.scaleX(115);
		params.topMargin = ActivityUtils.scaleY(45);
		mParentLayout.addView(mMainTenanceTab, params);
		
		mSettingTab = new TextView(mContext);
		mSettingTab.setId(mSettingTab.hashCode());
		mSettingTab.setText("设置");
		mSettingTab.setGravity(Gravity.CENTER);
		mSettingTab.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		mSettingTab.setBackgroundResource(R.drawable.main_maintenance_tab_normal);
		params = ActivityUtils.getLayoutParams(274, 73);
		params.topMargin = ActivityUtils.scaleY(45);
		params.addRule(RelativeLayout.RIGHT_OF, mMainTenanceTab.getId());
		mParentLayout.addView(mSettingTab, params);
		
		//==============================
		initTabMaintenance();
		initTabSetting();
	}
	
	private void initTabMaintenance(){
		mMaintenanceLayout = new RelativeLayout(mContext);
		mMaintenanceLayout.setBackgroundResource(R.drawable.main_info_background);
		LayoutParams params = ActivityUtils.getLayoutParams(504, 350);
		params.leftMargin = ActivityUtils.scaleX(135);
		params.topMargin = ActivityUtils.scaleY(15);
		params.addRule(RelativeLayout.BELOW, mMainTenanceTab.getId());
		mParentLayout.addView(mMaintenanceLayout, params);
		
		mMaintenanceText = new TextView(mContext);
		mMaintenanceText.setId(mMaintenanceText.hashCode());
		mMaintenanceText.setText("");
		mMaintenanceText.setGravity(Gravity.CENTER_VERTICAL);
		mMaintenanceText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(420, 330);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mMaintenanceLayout.addView(mMaintenanceText, params);
	}
	
	
	private void initTabSetting(){
		mSettingBrakesLayout = new RelativeLayout(mContext);
		mSettingBrakesLayout.setId(mSettingBrakesLayout.hashCode());
		mSettingBrakesLayout.setBackgroundResource(R.drawable.main_maintenance_item_background);
		mSettingBrakesLayout.setPadding(ActivityUtils.scaleX(5), ActivityUtils.scaleY(5), 
				ActivityUtils.scaleX(5), ActivityUtils.scaleY(5));
		
		LayoutParams params = ActivityUtils.getLayoutParams(524, 163);
		params.leftMargin = ActivityUtils.scaleX(125);
		params.topMargin = ActivityUtils.scaleY(125);
		mParentLayout.addView(mSettingBrakesLayout, params);
		
		TextView brakesText = new TextView(mContext);
		brakesText.setId(brakesText.hashCode());
		brakesText.setText("制动器");
		brakesText.setGravity(Gravity.CENTER_VERTICAL);
		brakesText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(160, 50);
		params.leftMargin = ActivityUtils.scaleX(45);
		mSettingBrakesLayout.addView(brakesText, params);
		
		mBrakesMiles = new TextView(mContext);
		mBrakesMiles.setId(mBrakesMiles.hashCode());
		mBrakesMiles.setText("里程间隔: " + "    " + 25000 + " km");
		mBrakesMiles.setGravity(Gravity.CENTER_VERTICAL);
		mBrakesMiles.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(360, 50);
		params.leftMargin = ActivityUtils.scaleX(85);
		params.addRule(RelativeLayout.BELOW, brakesText.getId());
		mSettingBrakesLayout.addView(mBrakesMiles, params);
		
		mBrakesMilesReset = new ImageView(mContext);
		mBrakesMilesReset.setId(mBrakesMilesReset.hashCode());
		mBrakesMilesReset.setImageResource(R.drawable.main_maintanece_reset_drawable);
		params = ActivityUtils.getLayoutParams(63, 50);
		params.rightMargin = ActivityUtils.scaleX(40);
		params.addRule(RelativeLayout.BELOW, brakesText.getId());
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mSettingBrakesLayout.addView(mBrakesMilesReset, params);
		
		
		mBrakesTimer = new TextView(mContext);
		mBrakesTimer.setId(mBrakesTimer.hashCode());
		mBrakesTimer.setText("时间间隔:" + "     " + 12 + " 月" );
		mBrakesTimer.setGravity(Gravity.CENTER_VERTICAL);
		mBrakesTimer.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(360, 50);
		params.leftMargin = ActivityUtils.scaleX(85);
		params.addRule(RelativeLayout.BELOW, mBrakesMiles.getId());
		mSettingBrakesLayout.addView(mBrakesTimer, params);
		
		
		mBrakesTimerReset = new ImageView(mContext);
		mBrakesTimerReset.setId(mBrakesTimerReset.hashCode());
		mBrakesTimerReset.setImageResource(R.drawable.main_maintanece_reset_drawable);
		params = ActivityUtils.getLayoutParams(63, 50);
		params.rightMargin = ActivityUtils.scaleX(40);
		params.addRule(RelativeLayout.BELOW, mBrakesMiles.getId());
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mSettingBrakesLayout.addView(mBrakesTimerReset, params);
		
		
		//=================================
		mSettingCheckLayout = new RelativeLayout(mContext);
		mSettingCheckLayout.setBackgroundResource(R.drawable.main_maintenance_item_background);
		mSettingCheckLayout.setPadding(ActivityUtils.scaleX(5), ActivityUtils.scaleY(5), 
				ActivityUtils.scaleX(5), ActivityUtils.scaleY(5));
		
		params = ActivityUtils.getLayoutParams(524, 163);
		params.leftMargin = ActivityUtils.scaleX(125);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, mSettingBrakesLayout.getId());
		mParentLayout.addView(mSettingCheckLayout, params);
		
		TextView checkText = new TextView(mContext);
		checkText.setId(brakesText.hashCode());
		checkText.setText("车辆检查");
		checkText.setGravity(Gravity.CENTER_VERTICAL);
		checkText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(160, 50);
		params.leftMargin = ActivityUtils.scaleX(45);
		mSettingCheckLayout.addView(checkText, params);
		
		mCheckMiles = new TextView(mContext);
		mCheckMiles.setId(mCheckMiles.hashCode());
		mCheckMiles.setText("里程间隔: " + "    " + 25000 + " km");
		mCheckMiles.setGravity(Gravity.CENTER_VERTICAL);
		mCheckMiles.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(360, 50);
		params.leftMargin = ActivityUtils.scaleX(85);
		params.addRule(RelativeLayout.BELOW, checkText.getId());
		mSettingCheckLayout.addView(mCheckMiles, params);
		
		mCheckMilesReset = new ImageView(mContext);
		mCheckMilesReset.setId(mCheckMilesReset.hashCode());
		mCheckMilesReset.setImageResource(R.drawable.main_maintanece_reset_drawable);
		params = ActivityUtils.getLayoutParams(63, 50);
		params.rightMargin = ActivityUtils.scaleX(40);
		params.addRule(RelativeLayout.BELOW, checkText.getId());
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mSettingCheckLayout.addView(mCheckMilesReset, params);
		
		
		mCheckTimer = new TextView(mContext);
		mCheckTimer.setId(mCheckTimer.hashCode());
		mCheckTimer.setText("时间间隔:" + "     " + 24 + " 月" );
		mCheckTimer.setGravity(Gravity.CENTER_VERTICAL);
		mCheckTimer.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(360, 50);
		params.leftMargin = ActivityUtils.scaleX(85);
		params.addRule(RelativeLayout.BELOW, mCheckMiles.getId());
		mSettingCheckLayout.addView(mCheckTimer, params);
		
		
		mCheckTimerReset = new ImageView(mContext);
		mCheckTimerReset.setId(mCheckTimerReset.hashCode());
		mCheckTimerReset.setImageResource(R.drawable.main_maintanece_reset_drawable);
		params = ActivityUtils.getLayoutParams(63, 50);
		params.rightMargin = ActivityUtils.scaleX(40);
		params.addRule(RelativeLayout.BELOW, mCheckMiles.getId());
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mSettingCheckLayout.addView(mCheckTimerReset, params);
	}
	
	private void initStatus(){
		mMainTenanceTab.setOnClickListener(this);
		mSettingTab.setOnClickListener(this);
		
		mBrakesMiles.setOnClickListener(this);
		mBrakesTimer.setOnClickListener(this);
		mCheckMiles.setOnClickListener(this);
		mCheckTimer.setOnClickListener(this);
		
		mBrakesMilesReset.setOnClickListener(this);
		mBrakesTimerReset.setOnClickListener(this);
		mCheckMilesReset.setOnClickListener(this);
		mCheckTimerReset.setOnClickListener(this);
		
		mMaintenanceLayout.setVisibility(View.VISIBLE);
		mSettingBrakesLayout.setVisibility(View.GONE);
		mSettingCheckLayout.setVisibility(View.GONE);
	}
	
	private static final int DIALOG_BRAKE_MILE_DURATION = 0;
	private static final int DIALOG_BRAKE_TIMER_DURATION = 1;	
	private static final int DIALOG_CHECK_MILE_DURATION = 2;
	private static final int DIALOG_CHECK_TIMER_DURATION = 3;
	private void showDialog(final int dialog_id){
		final EditText editText = new EditText(mContext);
		editText.setSingleLine();
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)}); 
		
		new AlertDialog.Builder(mContext)
		.setTitle("请设置新的数值")
		.setView(editText)
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
			}
		})
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				String number = editText.getText().toString();
				if (number.trim().equals("")){
					return;
				}
				if (Integer.valueOf(number) == 0){
					VehicleApp.makeToast("设置值不能为零");
					return;
				}
				if (dialog_id == DIALOG_BRAKE_MILE_DURATION){
					SharedPreferenceUtils.setMaintanenceBrakeMileDuration(
							mContext, Integer.valueOf(number));
					updateStatus();
				}else if (dialog_id == DIALOG_BRAKE_TIMER_DURATION){
					SharedPreferenceUtils.setMaintanenceBrakeTimerDuration(
							mContext, Integer.valueOf(number));
					updateStatus();
				}else if (dialog_id == DIALOG_CHECK_MILE_DURATION){
					SharedPreferenceUtils.setMaintanenceCheckMileDuration(
							mContext, Integer.valueOf(number));
					updateStatus();
				}else if (dialog_id == DIALOG_CHECK_TIMER_DURATION){
					SharedPreferenceUtils.setMaintanenceCheckTimerDuration(
							mContext, Integer.valueOf(number));
					updateStatus();
				}
			}
		}).create().show();
	}
	
	@Override
	public void onClick(View v) {
		if (v == mMainTenanceTab){
			mMainTenanceTab.setBackgroundResource(R.drawable.main_maintenance_tab_select);
			mSettingTab.setBackgroundResource(R.drawable.main_maintenance_tab_normal);
			mMaintenanceLayout.setVisibility(View.VISIBLE);
			mSettingBrakesLayout.setVisibility(View.GONE);
			mSettingCheckLayout.setVisibility(View.GONE);
			
			mIControl.showCarInfomation(IControl.SHOW_INFO_MAINTENANCE, null);
		}
		
		if (v == mSettingTab){
			mSettingTab.setBackgroundResource(R.drawable.main_maintenance_tab_select);
			mMainTenanceTab.setBackgroundResource(R.drawable.main_maintenance_tab_normal);
			mMaintenanceLayout.setVisibility(View.GONE);
			mSettingBrakesLayout.setVisibility(View.VISIBLE);
			mSettingCheckLayout.setVisibility(View.VISIBLE);
		}
		
		//========================================
		if (v == mBrakesMiles){
			showDialog(DIALOG_BRAKE_MILE_DURATION);
		}
		
		if (v == mBrakesTimer){
			showDialog(DIALOG_BRAKE_TIMER_DURATION);
		}
		
		if (v == mCheckMiles){
			showDialog(DIALOG_CHECK_MILE_DURATION);
		}
		
		if (v == mCheckTimer){
			showDialog(DIALOG_CHECK_TIMER_DURATION);
		}
		
		
		//===============================
		if (v == mBrakesMilesReset){
			SharedPreferenceUtils.setMaintanenceBrakeMileRecord(mContext, mTotalMiles);
			VehicleApp.makeToast("重置成功");
		}
		
		if (v == mBrakesTimerReset){
			SharedPreferenceUtils.setMaintanenceBrakeTimerRecord(mContext, System.currentTimeMillis());
			VehicleApp.makeToast("重置成功");
		}
		
		if (v == mCheckMilesReset){
			SharedPreferenceUtils.setMaintanenceCheckMileRecord(mContext, mTotalMiles);
			VehicleApp.makeToast("重置成功");
		}
		
		if (v == mCheckTimerReset){
			SharedPreferenceUtils.setMaintanenceCheckTimerRecord(mContext, System.currentTimeMillis());
			VehicleApp.makeToast("重置成功");
		}
	}
	
	private int mTotalMiles = 0;
	public void showContentInfo(int event, Object obj){
		switch (event) {
		case IControl.SHOW_INFO_MAINTENANCE:
			if (obj == null){
				//test code
				//MaintenanceInfo info = new MaintenanceInfo(1000, 1000);
				mTotalMiles = 1000;
				showMaintenanceInfo(mTotalMiles);
			}else{
				mTotalMiles = (Integer) obj;
				showMaintenanceInfo(mTotalMiles);
			}
			break;
		default:
			break;
		}
	}
	
	private void showMaintenanceInfo(int flightMiles){
		String message = "请到4s店进行车辆保养";
		if (isBrakeTimerReach() || isBrakeMilesReach(flightMiles)){
			message = message + "\n"
			+ "\n" + "更换制动摩擦卡，清洁制动器内表面" 
			+ "\n" + "检查制动盘表面和厚度" 
			+ "\n" + "检查驻车制动摩擦片（对于后制动器）"
			+ "\n";
		}
		
		if (isCheckTimerReach() || isCheckMilesReach(flightMiles)){
			message = message 
			+ "\n" + "1、检查照明、安全带、蓄电池、转向、底盘、制动车内功能，如喇叭、闪烁报警、仪表、空调等"
			+ "\n" + "2、试车：制动磨合、转向、离合、变速箱、减震器；"
			+ "\n" ;
		}
		
		if (message.equals("请到4s店进行车辆保养")){
			message = "目前没有保养提示";
		}
		
		mMaintenanceText.setText(message);
	}
	
	private boolean isBrakeMilesReach(int totalMiles){
		int brakeMileDuration = SharedPreferenceUtils.getMaintanenceBrakeMileDuration(mContext);
		int brakeMileRecord = SharedPreferenceUtils.getMaintanenceBrakeMileRecord(mContext);
		LogUtils.LOGD(TAG, "isBrakeMilesReach totalMiles = " + totalMiles);
		LogUtils.LOGD(TAG, "isBrakeMilesReach brakeMileRecord = " + brakeMileRecord);
		LogUtils.LOGD(TAG, "isBrakeMilesReach brakeMileDuration = " + brakeMileDuration);
		if ((totalMiles-brakeMileRecord) > brakeMileDuration){
			return true;
		}
		return false;
	}
	
	private boolean isBrakeTimerReach(){
		int month = SharedPreferenceUtils.getMaintanenceBrakeTimerDuration(mContext);
		long time = SharedPreferenceUtils.getMaintanenceBrakeTimerRecord(mContext);
		long duration = System.currentTimeMillis() - time;
		int month_duration = (int) (duration/(1000 * 3600 * 24 * 30));
		LogUtils.LOGD(TAG, "isBrakeTimerReach duration = " + duration);
		LogUtils.LOGD(TAG, "isBrakeTimerReach month_duration = " + month_duration);
		if (month_duration > month){
			return true;
		}
		return false;
	}
	
	private boolean isCheckMilesReach(int totalMiles){
		int checkMilesDuration = SharedPreferenceUtils.getMaintanenceCheckMileDuration(mContext);
		int checkMilesRecord = SharedPreferenceUtils.getMaintanenceCheckMileRecord(mContext);
		LogUtils.LOGD(TAG, "isCheckMilesReach realCheckMiles = " + totalMiles);
		LogUtils.LOGD(TAG, "isCheckMilesReach checkMilesRecord = " + checkMilesRecord);
		LogUtils.LOGD(TAG, "isCheckMilesReach checkMilesDuration = " + checkMilesDuration);
		if ((totalMiles-checkMilesRecord) > checkMilesDuration){
			return true;
		}
		return false;
	}
	
	private boolean isCheckTimerReach(){
		int month = SharedPreferenceUtils.getMaintanenceCheckTimerDuration(mContext);
		long time = SharedPreferenceUtils.getMaintanenceCheckTimerRecord(mContext);
		long duration = System.currentTimeMillis() - time;
		int month_duration = (int) (duration/(1000 * 3600 * 24 * 30));
		LogUtils.LOGD(TAG, "isCheckTimerReach duration = " + duration);
		LogUtils.LOGD(TAG, "isCheckTimerReach month_duration = " + month_duration);
		if (month_duration > month){
			return true;
		}
		return false;
	}
	
	private void updateStatus(){
		mBrakesMiles.setText("里程间隔:" + "     " + 
				SharedPreferenceUtils.getMaintanenceBrakeMileDuration(mContext) + " km" );
		mBrakesTimer.setText("时间间隔:" + "     " + 
				SharedPreferenceUtils.getMaintanenceBrakeTimerDuration(mContext) + " 月" );
		mCheckMiles.setText("里程间隔:" + "     " + 
				SharedPreferenceUtils.getMaintanenceCheckMileDuration(mContext) + " km" );
		mCheckTimer.setText("时间间隔:" + "     " + 
				SharedPreferenceUtils.getMaintanenceCheckTimerDuration(mContext) + " 月" );
	}
}