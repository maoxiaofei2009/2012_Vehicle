package com.zjdx.vehicle.activity.main.ui.bottom;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;



public class ContentAreaSetting{
	private static final String TAG = "ContentAreaInfo";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private IControl mIControl;
	
	public ContentAreaSetting(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayout = layout;
		
		initLayout();
		updateWifiStatus(false);
		updateCloudStatus(false);
	}
	
	
	private ImageView networkIcon;
	private ImageView cloudIcon;
	private void initLayout(){
		//==========
		ImageView settingIcon = new ImageView(mContext);
		settingIcon.setId(settingIcon.hashCode());
		settingIcon.setImageResource(R.drawable.main_setting_icon);
		LayoutParams params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(115);
		params.topMargin = ActivityUtils.scaleY(65);
		mParentLayout.addView(settingIcon, params);
		
		TextView titleText = new TextView(mContext);
		titleText.setText("设置");
		titleText.setGravity(Gravity.CENTER_VERTICAL);
		titleText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(86, 56);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.topMargin = ActivityUtils.scaleY(65);
		params.addRule(RelativeLayout.RIGHT_OF, settingIcon.getId());
		mParentLayout.addView(titleText, params);
		
		//==========
		networkIcon = new ImageView(mContext);
		networkIcon.setId(networkIcon.hashCode());
		networkIcon.setImageResource(R.drawable.main_setting_network_highlight);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(150);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, settingIcon.getId());
		mParentLayout.addView(networkIcon, params);
		
		TextView textview = new TextView(mContext);
		textview.setText("网络");
		textview.setGravity(Gravity.CENTER_VERTICAL);
		textview.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(86, 56);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, settingIcon.getId());
		params.addRule(RelativeLayout.RIGHT_OF, networkIcon.getId());
		mParentLayout.addView(textview, params);
		
		//==========
		cloudIcon = new ImageView(mContext);
		cloudIcon.setId(cloudIcon.hashCode());
		cloudIcon.setImageResource(R.drawable.main_setting_cloud_service_highlight);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(150);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, networkIcon.getId());
		mParentLayout.addView(cloudIcon, params);
		
		TextView cloudText = new TextView(mContext);
		cloudText.setText("云服务");
		cloudText.setGravity(Gravity.CENTER_VERTICAL);
		cloudText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(86, 56);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, networkIcon.getId());
		params.addRule(RelativeLayout.RIGHT_OF, cloudIcon.getId());
		mParentLayout.addView(cloudText, params);
		
		//=============
		ImageView versionIcon = new ImageView(mContext);
		versionIcon.setId(versionIcon.hashCode());
		versionIcon.setImageResource(R.drawable.main_setting_version_highlight);
		params = ActivityUtils.getLayoutParams(56, 56);
		params.leftMargin = ActivityUtils.scaleX(150);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, cloudIcon.getId());
		mParentLayout.addView(versionIcon, params);
		
		String pName = mContext.getPackageName();
        String versionName = "0.0";
		try { 
		    PackageInfo pinfo = mContext.getPackageManager().getPackageInfo(
		        		pName, PackageManager.GET_CONFIGURATIONS); 
		    versionName = pinfo.versionName; 
		} catch (NameNotFoundException e) { 
		} 
		TextView versionText = new TextView(mContext);
		versionText.setText("版本 " + versionName);
		versionText.setGravity(Gravity.CENTER_VERTICAL);
		versionText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		params = ActivityUtils.getLayoutParams(86, 56);
		params.leftMargin = ActivityUtils.scaleX(5);
		params.topMargin = ActivityUtils.scaleY(0);
		params.addRule(RelativeLayout.BELOW, cloudIcon.getId());
		params.addRule(RelativeLayout.RIGHT_OF, versionIcon.getId());
		mParentLayout.addView(versionText, params);
	}
	
	public void showContentInfo(int event, Object obj){
		switch (event) {
		case IControl.SHOW_INFO_SETTING:
			if (obj != null && obj instanceof boolean[]){
				final boolean[] status = (boolean[]) obj;
				if (status.length == 2){
					updateWifiStatus(status[0]);
					updateCloudStatus(status[1]);
				}
			}
			break;
		default:
			break;
		}
	}
	
	
	private void updateWifiStatus(boolean connect){
		networkIcon.setImageResource(connect? R.drawable.main_setting_network_highlight
				: R.drawable.main_setting_network_normal);
	}
	
	private void updateCloudStatus(boolean connect){
		cloudIcon.setImageResource(connect? R.drawable.main_setting_cloud_service_highlight 
				: R.drawable.main_setting_cloud_service_normal);
	}
}