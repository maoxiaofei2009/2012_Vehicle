package com.zjdx.vehicle.activity.main.ui.bottom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.utils.ActivityUtils;

public class FaultListAdapter extends BaseAdapter{
	private static final String TAG = "ChatMessageAdapter";
	protected Context mContext;

	private class ViewHolder{
		public ImageView faultIcon;
		public TextView faultNameText;
		public ImageView faultPartIcon;
		public TextView faultPartText;
		public ImageView faultWayIcon;
		public TextView faultWayText;
	}
	
	private ArrayList<FaultListInfo> mArrayList = new ArrayList<FaultListInfo>(); 
	public FaultListAdapter(Context context) {
		super();
		mContext = context;
	}
	
	public void setData(ArrayList<FaultListInfo> list){
		mArrayList = list;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (mArrayList != null){
			count = mArrayList.size();
		}
		return count;
	}

	@Override
	public FaultListInfo getItem(int position) {
		if (mArrayList != null){
			return mArrayList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup group) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			RelativeLayout layout = new RelativeLayout(mContext);
			holder.faultIcon = new ImageView(mContext);
			holder.faultIcon.setId(holder.faultIcon.hashCode());
			holder.faultIcon.setImageResource(R.drawable.main_fault_item_icon);
			LayoutParams params = ActivityUtils.getLayoutParams(56, 56);
			params.leftMargin = ActivityUtils.scaleX(0);
			layout.addView(holder.faultIcon, params);
			
			holder.faultNameText = new TextView(mContext);
			holder.faultNameText.setGravity(Gravity.CENTER_VERTICAL);
			holder.faultNameText.setTextColor(Color.WHITE);
			holder.faultNameText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(440, 56);
			params.addRule(RelativeLayout.RIGHT_OF, holder.faultIcon.getId());
			layout.addView(holder.faultNameText, params);
			
			holder.faultPartIcon = new ImageView(mContext);
			holder.faultPartIcon.setId(holder.faultPartIcon.hashCode());
			holder.faultPartIcon.setImageResource(R.drawable.main_fault_part_icon);
			params = ActivityUtils.getLayoutParams(46, 46);
			params.leftMargin = ActivityUtils.scaleX(56);
			params.addRule(RelativeLayout.BELOW, holder.faultIcon.getId());
			layout.addView(holder.faultPartIcon, params);
			
			holder.faultPartText = new TextView(mContext);
			holder.faultPartText.setGravity(Gravity.CENTER_VERTICAL);
			holder.faultPartText.setTextColor(Color.WHITE);
			holder.faultPartText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(440, 46);
			params.addRule(RelativeLayout.RIGHT_OF, holder.faultPartIcon.getId());
			params.addRule(RelativeLayout.BELOW, holder.faultIcon.getId());
			layout.addView(holder.faultPartText, params);
			
			holder.faultWayIcon = new ImageView(mContext);
			holder.faultWayIcon.setId(holder.faultWayIcon.hashCode());
			holder.faultWayIcon.setImageResource(R.drawable.main_fault_way_icon);
			params = ActivityUtils.getLayoutParams(46, 46);
			params.leftMargin = ActivityUtils.scaleX(56);
			params.addRule(RelativeLayout.BELOW, holder.faultPartIcon.getId());
			layout.addView(holder.faultWayIcon, params);
			
			holder.faultWayText = new TextView(mContext);
			holder.faultWayText.setGravity(Gravity.CENTER_VERTICAL);
			holder.faultWayText.setTextColor(Color.WHITE);
			holder.faultWayText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(440, 46);
			params.addRule(RelativeLayout.BELOW, holder.faultPartIcon.getId());
			params.addRule(RelativeLayout.RIGHT_OF, holder.faultWayIcon.getId());
			layout.addView(holder.faultWayText, params);
			
			convertView = layout;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FaultListInfo info = getItem(position);
		if (info != null){
			holder.faultNameText.setText("故障项: " + info.faultName);
			holder.faultPartText.setText("检修部件: " + info.faultPart);
			holder.faultWayText.setText("处理方式: " + info.faultWay);
		}

		return convertView;
	}
}
