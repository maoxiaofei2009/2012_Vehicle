package com.zjdx.vehicle.activity.main.ui;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.main.ui.bottom.ContentAreaDiagnosis;
import com.zjdx.vehicle.activity.main.ui.bottom.ContentAreaFault;
import com.zjdx.vehicle.activity.main.ui.bottom.ContentAreaInfo;
import com.zjdx.vehicle.activity.main.ui.bottom.ContentAreaMaintenance;
import com.zjdx.vehicle.activity.main.ui.bottom.ContentAreaSetting;
import com.zjdx.vehicle.activity.utils.ActivityUtils;



public class ContentAreaBottom{
	private static final String TAG = "ContentAreaInfo";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	
	private RelativeLayout mCarInfoArea;
	private RelativeLayout mCarFaultArea;
	private RelativeLayout mCarDiagnosisArea;
	private RelativeLayout mCarMaintenanceArea;
	private RelativeLayout mCarSettingArea;
	
	private ContentAreaSetting mContentAreaSetting;
	private ContentAreaFault mContentAreaFault;
	private ContentAreaDiagnosis mContentAreaDiagnosis; 
	private ContentAreaInfo mContentAreaInfo;
	private ContentAreaMaintenance mContentAreaMaintenance;
	
	private IControl mIControl;
	public ContentAreaBottom(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayout = layout;
		
		initLayout();
	}

	
	private void initLayout(){
		if (mCarInfoArea == null){
			mCarInfoArea = new RelativeLayout(mContext);
			LayoutParams params = ActivityUtils.getLayoutParams(776, 507);
			//mCarInfoArea.setBackgroundResource(R.drawable.main_info_view_background);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			mParentLayout.addView(mCarInfoArea, params);
		}
		
		if (mCarFaultArea == null){
			mCarFaultArea = new RelativeLayout(mContext);
			LayoutParams params = ActivityUtils.getLayoutParams(720, 463);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			mParentLayout.addView(mCarFaultArea, params);
		}
		
		if (mCarDiagnosisArea == null){
			mCarDiagnosisArea = new RelativeLayout(mContext);
			LayoutParams params = ActivityUtils.getLayoutParams(720, 463);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			mParentLayout.addView(mCarDiagnosisArea, params);
		}
		
		if (mCarSettingArea == null){
			mCarSettingArea = new RelativeLayout(mContext);
			mCarSettingArea.setBackgroundResource(R.drawable.main_setting_background);
			LayoutParams params = ActivityUtils.getLayoutParams(720, 463);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			mParentLayout.addView(mCarSettingArea, params);
		}
		
		if (mCarMaintenanceArea == null){
			mCarMaintenanceArea = new RelativeLayout(mContext);
			LayoutParams params = ActivityUtils.getLayoutParams(776, 507);
			mParentLayout.addView(mCarMaintenanceArea, params);
		}
		
		mParentLayout.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return true;
			}
		});
	}
	
	private ContentAreaInfo getContentAreaInfo(){
		if (mContentAreaInfo == null){
			mContentAreaInfo = new ContentAreaInfo(mContext, mIControl, mCarInfoArea);
		}
		return mContentAreaInfo;
	}
	
	private ContentAreaFault getContentAreaFault(){
		if (mContentAreaFault == null){
			mContentAreaFault = new ContentAreaFault(mContext, mIControl, mCarFaultArea);
		}
		return mContentAreaFault;
	}
	
	private ContentAreaDiagnosis getContentAreaDiagnosis(){
		if (mContentAreaDiagnosis == null){
			mContentAreaDiagnosis = new ContentAreaDiagnosis(mContext, mIControl, mCarDiagnosisArea);
		}
		return mContentAreaDiagnosis;
	}
	
	private ContentAreaMaintenance getContentAreaMaintenance(){
		if (mContentAreaMaintenance == null){
			mContentAreaMaintenance = new ContentAreaMaintenance(mContext, mIControl, mCarMaintenanceArea);
		}
		return mContentAreaMaintenance;
	}
	
	private ContentAreaSetting getContentAreaSetting(){
		if (mContentAreaSetting == null){
			mContentAreaSetting = new ContentAreaSetting(mContext, mIControl, mCarSettingArea);
		}
		return mContentAreaSetting;
	}
	
	public void showContentInfo(int event, Object obj){
		switch (event) {
		case IControl.SHOW_INFO_MESSAGE_BASE_INFO:
		case IControl.SHOW_INFO_MESSAGE_CAR_STATUS:
		case IControl.SHOW_INFO_MESSAGE_OTHER_INFO:
			
		case IControl.SHOW_INFO_BATTERY_BASE_INFO:
		case IControl.SHOW_INFO_BATTERY_MODEL_BASE_INFO:
		case IControl.SHOW_INFO_BATTERY_CHARGING_SYSTEM_STATUS:
		case IControl.SHOW_INFO_BATTERY_FAULT:
			
		case IControl.SHOW_INFO_MOTOR_MAIN:
		case IControl.SHOW_INFO_AIR_CONDITION_MAIN:
			mCarInfoArea.setVisibility(View.VISIBLE);
			mCarFaultArea.setVisibility(View.GONE);
			mCarDiagnosisArea.setVisibility(View.GONE);
			mCarSettingArea.setVisibility(View.GONE);
			mCarMaintenanceArea.setVisibility(View.GONE);
			getContentAreaInfo().showContentInfo(event, obj);
			break;
		case IControl.SHOW_INFO_FAULT:
			mCarInfoArea.setVisibility(View.GONE);
			mCarFaultArea.setVisibility(View.VISIBLE);
			mCarDiagnosisArea.setVisibility(View.GONE);
			mCarSettingArea.setVisibility(View.GONE);
			mCarMaintenanceArea.setVisibility(View.GONE);
			getContentAreaFault().showContentInfo(event, obj);
			break;
		case IControl.SHOW_INFO_DIAGNOSIS:
			mCarInfoArea.setVisibility(View.GONE);
			mCarFaultArea.setVisibility(View.GONE);
			mCarSettingArea.setVisibility(View.GONE);
			mCarMaintenanceArea.setVisibility(View.GONE);
			mCarDiagnosisArea.setVisibility(View.VISIBLE);
			getContentAreaDiagnosis().showContentInfo(event, obj);
			break;
		case IControl.SHOW_INFO_MAINTENANCE:
			mCarInfoArea.setVisibility(View.GONE);
			mCarFaultArea.setVisibility(View.GONE);
			mCarDiagnosisArea.setVisibility(View.GONE);
			mCarSettingArea.setVisibility(View.GONE);
			mCarMaintenanceArea.setVisibility(View.VISIBLE);
			getContentAreaMaintenance().showContentInfo(event, obj);
			break;
		case IControl.SHOW_INFO_SETTING:
			mCarInfoArea.setVisibility(View.GONE);
			mCarFaultArea.setVisibility(View.GONE);
			mCarDiagnosisArea.setVisibility(View.GONE);
			mCarMaintenanceArea.setVisibility(View.GONE);
			mCarSettingArea.setVisibility(View.VISIBLE);
			getContentAreaSetting().showContentInfo(event, obj);
			break;
		default:
			break;
		}
	}
}