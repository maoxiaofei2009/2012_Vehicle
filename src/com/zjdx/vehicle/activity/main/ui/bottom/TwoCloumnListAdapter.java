package com.zjdx.vehicle.activity.main.ui.bottom;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.utils.ActivityUtils;

public class TwoCloumnListAdapter extends BaseAdapter{
	private static final String TAG = "ChatMessageAdapter";
	protected Context mContext;

	private class ViewHolder{
		public TextView name;
		public TextView value;
	}
	
	private ArrayList<TwoCloumnInfo> mArrayList = new ArrayList<TwoCloumnInfo>(); 
	public TwoCloumnListAdapter(Context context) {
		super();
		mContext = context;
	}
	
	public void setData(ArrayList<TwoCloumnInfo> list){
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
	public TwoCloumnInfo getItem(int position) {
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
			holder.name = new TextView(mContext);
			holder.name.setGravity(Gravity.CENTER_VERTICAL);
			holder.name.setTextColor(Color.WHITE);
			holder.name.setId(holder.name.hashCode());
			holder.name.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			LayoutParams params = ActivityUtils.getLayoutParams(180, 56);
			layout.addView(holder.name, params);
			
			holder.value = new TextView(mContext);
			holder.value.setGravity(Gravity.CENTER_VERTICAL);
			holder.value.setTextColor(Color.WHITE);
			holder.value.setId(holder.value.hashCode());
			holder.value.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(180, 56);
			params.leftMargin = ActivityUtils.scaleX(50);
			params.addRule(RelativeLayout.RIGHT_OF, holder.name.getId());
			layout.addView(holder.value, params);
			
			convertView = layout;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		TwoCloumnInfo info = getItem(position);
		if (info != null){
			holder.name.setText(info.name);
			holder.value.setText(info.value);
		}

		return convertView;
	}
}
