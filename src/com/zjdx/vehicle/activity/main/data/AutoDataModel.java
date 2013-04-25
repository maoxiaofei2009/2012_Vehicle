package com.zjdx.vehicle.activity.main.data;

import java.util.HashMap;

import android.util.SparseIntArray;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.middleware.model.CanFrameData;
import com.zjdx.vehicle.middleware.model.Defines;

public class AutoDataModel {
	private static final String TAG = "AutoDataModel "; 
	public static final Object NULL_OBJECT = new Object();
	
	protected IControl mController = null;
	protected int[] mInterestKeys = null;
	protected SparseIntArray mKeyMap = null;
	protected HashMap<Integer, CanFrameData> mDataMap = null;
	
	public AutoDataModel(HashMap<Integer, CanFrameData> map) {		
		mDataMap = map;
		mKeyMap = new SparseIntArray();
		
		init();
	}	
	
	public synchronized void setController(IControl control) {
		mController = control;
	}
	
	public void update(int identity) {
		if (!isInterestkey(identity))
			return;
		
		for (int i=0, cnt=mKeyMap.size(); i<cnt; i++) {
			if (Defines.isInGroup(mKeyMap.keyAt(i), identity)) {
				setValue(mKeyMap.valueAt(i), getModelValue(mKeyMap.keyAt(i)));
			}			
		}
	}
	
	/** PLEASE OVERRIDE THIES METHOD. **/
	protected void initKeyMap() {		
	}
	
	/** PLEASE OVERRIDE THIES METHOD. **/
	protected Object getModelValue(int canFrameKey) {
		return null;
	}
	
	/** PLEASE OVERRIDE THIES METHOD. **/
	protected void setValue(int key, Object obj) {
		
	}	
	
	protected void init() {
		initKeyMap();
		initInterestKeys();
	}
		
	protected void initInterestKeys() {	
		int size = mKeyMap.size();
//		LogUtils.LOGD(TAG, "initInterestKeys mKeyMap.size() " + size + "  <----");
		
		if (size == 0)
			return;		
		
		int cnt = 0;
		int[] array = new int[size];
		for (int i=0; i<size; i++) {
			int groupId = Defines.getGroupId(mKeyMap.keyAt(i));
			if (!containsKey(array, groupId, cnt)) {
				array[cnt++] = groupId;
			}
		}
		
		if (cnt > 0) {
			mInterestKeys = new int[cnt];
			System.arraycopy(array, 0, mInterestKeys, 0, mInterestKeys.length);
		}
//		LogUtils.LOGD(TAG, "initInterestKeys " + mInterestKeys.length + " ---->");
	}
	
	protected boolean containsKey(int[] array, int key, int length) {
		boolean res = false; 
		if (array == null) {
			return false;
		}
		
		length = (length>0 && length<=array.length)? length : array.length;
		for (int i=0; i<length; i++) {
			if (array[i] == key) {
				res = true;
				break;
			}
		}			
	
		return res;
	}
	
	protected boolean isInterestkey(int identity) {
		return containsKey(mInterestKeys, identity, 0);
	}
}
