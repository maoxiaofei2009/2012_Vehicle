package com.zjdx.vehicle.activity.main.ui.bottom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.utils.ActivityUtils;

public class FourCloumnListAdapter extends BaseAdapter{
	private static final String TAG = "ChatMessageAdapter";
	protected Context mContext;

	private class ViewHolder{
		public TextView name1;
		public TextView value1;
		public TextView name2;
		public TextView value2;
	}
	
	private ArrayList<TwoCloumnInfo> mArrayList = new ArrayList<TwoCloumnInfo>(); 
	public FourCloumnListAdapter(Context context) {
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
			count = (int) (1.0f * mArrayList.size() / 2 + 0.5f);
		}
		return count;
	}

	@Override
	public TwoCloumnInfo getItem(int position) {
		try{
			if (mArrayList != null){
				return mArrayList.get(position);
			}
		}catch(Exception e){
			
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
			holder.name1 = new TextView(mContext);
			holder.name1.setGravity(Gravity.CENTER_VERTICAL);
			holder.name1.setTextColor(Color.WHITE);
			holder.name1.setId(holder.name1.hashCode());
			holder.name1.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			LayoutParams params = ActivityUtils.getLayoutParams(180, 56);
			layout.addView(holder.name1, params);
			
			holder.value1 = new TextView(mContext);
			holder.value1.setGravity(Gravity.CENTER_VERTICAL);
			holder.value1.setTextColor(Color.WHITE);
			holder.value1.setId(holder.value1.hashCode());
			holder.value1.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(180, 56);
			params.addRule(RelativeLayout.RIGHT_OF, holder.name1.getId());
			layout.addView(holder.value1, params);
			
			holder.name2 = new TextView(mContext);
			holder.name2.setGravity(Gravity.CENTER_VERTICAL);
			holder.name2.setTextColor(Color.WHITE);
			holder.name2.setId(holder.name2.hashCode());
			holder.name2.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(180, 56);
			params.addRule(RelativeLayout.RIGHT_OF, holder.value1.getId());
			layout.addView(holder.name2, params);
			
			holder.value2 = new TextView(mContext);
			holder.value2.setGravity(Gravity.CENTER_VERTICAL);
			holder.value2.setTextColor(Color.WHITE);
			holder.value2.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
			params = ActivityUtils.getLayoutParams(180, 56);
			params.addRule(RelativeLayout.RIGHT_OF, holder.name2.getId());
			layout.addView(holder.value2, params);
			
			convertView = layout;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		TwoCloumnInfo info1 = getItem(position*2 + 0);
		if (info1 != null){
			holder.name1.setText(info1.name);
			holder.value1.setText(info1.value);
		}

		TwoCloumnInfo info2 = getItem(position*2 + 1);
		if (info2 != null){
			holder.name2.setText(info2.name);
			holder.value2.setText(info2.value);
		}else{
			holder.name2.setText("");
			holder.value2.setText("");
		}
		
		return convertView;
	}
}
