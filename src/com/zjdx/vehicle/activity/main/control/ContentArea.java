package com.zjdx.vehicle.activity.main.control;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.zjdx.vehicle.activity.main.ui.ContentAreaAirCondition;
import com.zjdx.vehicle.activity.main.ui.ContentAreaBottom;
import com.zjdx.vehicle.activity.main.ui.ContentAreaCenter;
import com.zjdx.vehicle.activity.main.ui.ContentAreaLeft;
import com.zjdx.vehicle.activity.main.ui.ContentAreaRight;
import com.zjdx.vehicle.activity.utils.AnimUtils;


public class ContentArea{
	private static final String TAG = "ContentArea ";
	private final Context mContext;
	private final IControl mIControl;
	private ContentAreaLeft mContentAreaLeft;
	private ContentAreaCenter mContentAreaCenter;
	private ContentAreaRight mContentAreaRight;
	private boolean mContentAreaCenterVisible = false;
	
	private static final int MESSAGE_INIT_CONTENT_CENTER = 0;
	private static final int MESSAGE_CLOSE_CONTENT_CENTER = 1;
	
	public ContentArea(Context context, IControl control){
		mContext = context;
		mIControl = control;
		mContentAreaLeft = new ContentAreaLeft(context);
		//mContentAreaCenter = new ContentAreaCenter(context, control);
		mContentAreaRight = new ContentAreaRight(context);
		mHandler.sendEmptyMessageDelayed(MESSAGE_INIT_CONTENT_CENTER, 2000);
	}	
	
	public View getLeftContentView(){
		return mContentAreaLeft.getRootView();
	} 
	
	public View getRightContentView(){
		return mContentAreaRight.getRootView();
	}
	
	public View getCenterContentView(){
		ensureContentAreaCenterAvailable();
		return mContentAreaCenter.getRootView();
	} 

	public boolean isContentAreaCenterVisible(){
		return mContentAreaCenterVisible;
	}
	
	public void startOpenAnim(){
		if (!mContentAreaCenterVisible){
			mContentAreaCenterVisible = true;
			AnimUtils.startAnimOpen(getLeftContentView(), getRightContentView());
			getCenterContentView().setVisibility(View.VISIBLE);
			AnimUtils.startScaleCenterAnim(getCenterContentView(), AnimUtils.AnimType.SCALE_OUT);
			
			resetColseContentCenterTimer(true);
		}
	}
	
	public void startCloseAnim(){
		if (mContentAreaCenterVisible){
			mContentAreaCenterVisible = false;
			AnimUtils.startAnimClose(getLeftContentView(), getRightContentView());
			AnimUtils.startScaleCenterAnim(getCenterContentView(), AnimUtils.AnimType.SCALE_IN);
		}
	}
	
	public void updateContentLeft(int event, Object object){
		mContentAreaLeft.updateStatus(event, object);
	}
	
	public void updateContentRight(int event, Object object){
		mContentAreaRight.updateStatus(event, object);
	}
	
	public void updateCarControlStatus(int event){
		ensureContentAreaCenterAvailable();
		mContentAreaCenter.updateStatus(event);
	}
	
	public void showCarInfomation(int event, Object obj){
		mContentAreaCenter.updateCarInfoLayoutVisible(true);
		ContentAreaBottom contentAreaBottom = mContentAreaCenter.getContentAreaBottom();
		if (contentAreaBottom != null){
			contentAreaBottom.showContentInfo(event, obj);
		}
	}
	
	
	public void updateAirConditionStatus(int event){
		ensureContentAreaCenterAvailable();
		ContentAreaAirCondition airCondition = mContentAreaCenter.getContentAirCondition();
		if (airCondition != null){
			airCondition.updateAirConditionStatus(event) ;
		}
	}
	
	//==================================================================
	private void ensureContentAreaCenterAvailable(){
		if (mContentAreaCenter == null){
			mContentAreaCenter = new ContentAreaCenter(mContext, mIControl);
		}
	}
	
	public void resetColseContentCenterTimer(boolean reset){
		if (reset){
			mHandler.removeMessages(MESSAGE_CLOSE_CONTENT_CENTER);
			mHandler.sendEmptyMessageDelayed(MESSAGE_CLOSE_CONTENT_CENTER, 10000);
		}else{
			mHandler.removeMessages(MESSAGE_CLOSE_CONTENT_CENTER);
		}
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_INIT_CONTENT_CENTER:
				ensureContentAreaCenterAvailable();
				break;

			case MESSAGE_CLOSE_CONTENT_CENTER:
				if (mContentAreaCenterVisible){
					startCloseAnim();
				}
				break;
			default:
				break;
			}
		};
	};
}