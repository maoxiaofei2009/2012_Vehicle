package com.zjdx.vehicle.middleware.model;

import android.content.Context;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.db.DatabaseHandler;

public class CanFrameData {
	private static final String TAG = "CanFrameData ";
	protected final int mCanFrameId;
	protected final Item[] mItems;
	protected int[] mValues = null;
	protected DatabaseHandler mDB = null;
		
	public CanFrameData(int canFrameId) {
//		LogUtils.LOGD(TAG, "CanFrameData " + canFrameId + " <----");		
		mCanFrameId = canFrameId;
		mItems = ItemMgr.getItems(canFrameId);
		mValues = new int[mItems.length];		
//		LogUtils.LOGD(TAG, "CanFrameData " + canFrameId + " ---->");
	}
	
	public int getCanFrameId() {
		return mCanFrameId;
	}
	
	public int getIndex(int id) {
		return ModelHelper.getIndexById(id, mItems);
	}
	
	public int getValueByIndex(int index) {
		return (index >= 0)? mValues[index] : 0;
	}
	
	public String GetDescription(Context context, int index) {
		return ModelHelper.GetDescription(context, mItems, index);
	}
	
	public int getValue(int id) {
		int index = ModelHelper.getIndexById(id, mItems);
//		LogUtils.LOGD(TAG, "getValue id " + id + " index= " + index);
		return (index >= 0)? mValues[index] : 0;
	}
	
	public boolean setValue(int id, int value) {
		boolean res = false;
		int index = ModelHelper.getIndexById(id, mItems);
		if (index >= 0 && ModelHelper.isEnableWrite(mItems[index].mask)) {
			mValues[index] = value;
			res = true;
		}
		
		return res;
	}
		
	public int[] getAllValues() {
		return mValues;
	}
	
	public synchronized void getAllValues(int[] values) {
//		LogUtils.LOGD(TAG, "getAllValues <----");
		if (values != null && values.length == mValues.length) {
			System.arraycopy(mValues, 0, values, 0, mValues.length);
		}
//		LogUtils.LOGD(TAG, "getAllValues ---->");
	}
	
	public synchronized void setAllValues(int[] values) {
//		LogUtils.LOGD(TAG, "setAllValues <----");
		if (values != null && values.length == mValues.length) {
			System.arraycopy(values, 0, mValues, 0, mValues.length);
		}
//		LogUtils.LOGD(TAG, "setAllValues ---->");
	}
	
	public boolean different(int[] values) {
		if (values != null && values.length == mValues.length) {
			for (int i=0,cnt=values.length; i<cnt; i++) {
				if (values[i] != mValues[i]) {
					return true;
				}
			}			
		}
		return false;
	}
		
	public void loadFromDB() {
//		LogUtils.LOGD(TAG, "loadFromDB " + mCanFrameId + " <----");
		if (mDB != null) {
			mDB.queryTable(mCanFrameId, mValues);
		}
//		LogUtils.LOGD(TAG, "loadFromDB " + mCanFrameId + " ----->");
	}	 
	
	public synchronized void setDB(DatabaseHandler db) {
		mDB = db;
	}
	
}
