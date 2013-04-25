package com.zjdx.vehicle.activity.main.ui.bottom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;



public class ContentAreaFault{
	private static final String TAG = "ContentAreaInfo";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private IControl mIControl;
	
	private ListView  mFaultList;
	private FaultListAdapter mFaultAdapter;
	private TextView mMessageText;
	private ArrayList<FaultListInfo> mArrayList = new ArrayList<FaultListInfo>(); 
	public ContentAreaFault(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayout = layout;
		
		initLayout();
	}
	
	private void initLayout(){
		//==========
		LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mFaultList = (ListView) mInflater.inflate(R.layout.main_listview, null);
		mFaultList.setBackgroundResource(R.drawable.main_info_background);
		mFaultList.setCacheColorHint(Color.TRANSPARENT);
		mFaultList.setScrollbarFadingEnabled(false);
		LayoutParams params = ActivityUtils.getLayoutParams(720, 463);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mFaultList.setPadding(ActivityUtils.scaleX(20), 
				ActivityUtils.scaleY(20), ActivityUtils.scaleX(20), ActivityUtils.scaleY(20));
		mParentLayout.addView(mFaultList, params);
		
		mFaultAdapter = new FaultListAdapter(mContext);
		mFaultList.setAdapter(mFaultAdapter);
		
		mMessageText = new TextView(mContext);
		mMessageText.setTextColor(Color.WHITE);
		mMessageText.setGravity(Gravity.CENTER);
		mMessageText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		mMessageText.setBackgroundResource(R.drawable.main_info_background);
		mMessageText.setText("Œﬁπ ’œ–≈œ¢");
		params = ActivityUtils.getLayoutParams(720, 463);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mParentLayout.addView(mMessageText, params);
		
		
//		FaultListInfo info = new FaultListInfo();
//		for (int i=0; i<10; i++){
//			 info = new FaultListInfo();
//			 info.faultName = "123";
//			 info.faultPart = "456";
//			 info.faultWay = "789";
//			 mArrayList.add(info);
//		}
		
	}
	
	public void showContentInfo(int event, Object obj){
		switch (event) {
		case IControl.SHOW_INFO_FAULT:
			if (obj != null){
				mArrayList = (ArrayList<FaultListInfo>) obj;
			}
			mMessageText.setVisibility(mArrayList.isEmpty() ? View.VISIBLE: View.GONE);
			mFaultList.setVisibility(!mArrayList.isEmpty() ? View.VISIBLE: View.GONE);
			mFaultAdapter.setData(mArrayList);
			mFaultAdapter.notifyDataSetChanged();

			break;
		default:
			break;
		}
	}
}