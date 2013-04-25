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

public class DiagnosisListAdapter extends BaseAdapter{
	private static final String TAG = "DiagnosisListAdapter";
	protected Context mContext;

	private class ViewHolder{
		public ImageView icon;
		public TextView name;
	}
	
	private ArrayList<DiagnosisListInfo> mArrayList = new ArrayList<DiagnosisListInfo>(); 
	public DiagnosisListAdapter(Context context) {
		super();
		mContext = context;
	}
	
	public void setData(ArrayList<DiagnosisListInfo> list){
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
	public DiagnosisListInfo getItem(int position) {
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
			holder.icon = new ImageView(mContext);
			holder.icon.setId(holder.icon.hashCode());
			holder.icon.setImageResource(R.drawable.main_fault_item_icon);
			LayoutParams params = ActivityUtils.getLayoutParams(56, 56);
			params.leftMargin = ActivityUtils.scaleX(0);
			layout.addView(holder.icon, params);
			
			holder.name = new TextView(mContext);
			holder.name.setGravity(Gravity.CENTER_VERTICAL);
			holder.name.setTextColor(Color.WHITE);
			holder.name.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(440, 56);
			params.addRule(RelativeLayout.RIGHT_OF, holder.icon.getId());
			layout.addView(holder.name, params);
			
			convertView = layout;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		DiagnosisListInfo info = getItem(position);
		if (info != null){
			holder.icon.setImageResource(info.safe ? 
					R.drawable.main_diagnosis_safe : R.drawable.main_diagnosis_danger);
			holder.name.setText(info.name);
		}

		return convertView;
	}
}
