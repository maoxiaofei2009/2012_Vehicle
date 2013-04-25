package com.zjdx.vehicle.activity.main.control;

import android.content.Context;

import com.zjdx.vehicle.activity.main.ui.ActionBarBottom;
import com.zjdx.vehicle.activity.main.ui.ActionBarLeft;
import com.zjdx.vehicle.activity.main.ui.ActionBarRight;
import com.zjdx.vehicle.activity.main.ui.ActionDockBottom;


public class ActionArea{
	private static final String TAG = "ActionArea ";
	private ActionBarLeft mActionBarLeft;
	private ActionBarRight mActionBarRight;
	private ActionBarBottom mActionBarBottom;
	private ActionDockBottom mActionDockBottom;
	
	public ActionArea(Context context, IControl control){
		mActionBarLeft = new ActionBarLeft(context);
		mActionBarRight = new ActionBarRight(context);
		mActionBarBottom = new ActionBarBottom(context, control);
		mActionDockBottom = new ActionDockBottom(context, control);
	}
	
	
	public void updateDockStatus(int event){
		mActionDockBottom.updateStatus(event);
	}
	
	public void updateAlertLeft(int event, boolean alert){
		mActionBarLeft.updateAlert(event, alert);
	}
	
	public void updateAlertRight(int event, boolean alert){
		mActionBarRight.updateAlert(event, alert);
	}
}