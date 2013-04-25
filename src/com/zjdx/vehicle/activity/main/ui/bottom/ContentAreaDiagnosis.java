package com.zjdx.vehicle.activity.main.ui.bottom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.IControl;
import com.zjdx.vehicle.activity.utils.ActivityUtils;



public class ContentAreaDiagnosis{
	private static final String TAG = "ContentAreaInfo";
	private final Context mContext;
	private RelativeLayout mParentLayout;
	private IControl mIControl;
	
	private TextView mMessageText;
	private ListView  mDiagnosisList;
	private DiagnosisListAdapter mDiagnosisAdapter;
	private ArrayList<DiagnosisListInfo> mArrayList = new ArrayList<DiagnosisListInfo>(); 
	public ContentAreaDiagnosis(Context context, IControl control, RelativeLayout layout){
		mContext = context;
		mIControl = control;
		mParentLayout = layout;
		
		initLayout();
	}
	
	private void initLayout(){
		//==========
		RelativeLayout rootLayout = new RelativeLayout(mContext);
		rootLayout.setId(rootLayout.hashCode());
		rootLayout.setBackgroundResource(R.drawable.main_info_background);
		LayoutParams params = ActivityUtils.getLayoutParams(720, 463);
		mParentLayout.addView(rootLayout, params);
		
		ImageView icon = new ImageView(mContext);
		icon.setId(icon.hashCode());
		icon.setImageResource(R.drawable.main_diagnosis_icon);
		params = ActivityUtils.getLayoutParams(128, 128);
		params.leftMargin = ActivityUtils.scaleX(50);
		rootLayout.addView(icon, params);
		
		RelativeLayout topbarLayout = new RelativeLayout(mContext);
		topbarLayout.setId(topbarLayout.hashCode());
		topbarLayout.setBackgroundResource(R.drawable.main_diagnosis_top_bar);
		params = ActivityUtils.getLayoutParams(440, 102);
		params.topMargin = ActivityUtils.scaleY(10);
		params.leftMargin = ActivityUtils.scaleX(10);
		params.addRule(RelativeLayout.RIGHT_OF, icon.getId());
		rootLayout.addView(topbarLayout, params);
		
		TextView message = new TextView(mContext);
		message.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		message.setLines(2);
		message.setId(message.hashCode());
		message.setText("已经扫描30项");
		message.setGravity(Gravity.CENTER_VERTICAL);
		mMessageText = message;
		params = ActivityUtils.getLayoutParams(290, 70);
		params.leftMargin = ActivityUtils.scaleX(30);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		topbarLayout.addView(message, params);
		
		
		ImageView refreshBtn = new ImageView(mContext);
		refreshBtn.setId(refreshBtn.hashCode());
		refreshBtn.setImageResource(R.drawable.main_diagnosis_refresh_drawable);
		params = ActivityUtils.getLayoutParams(78, 52);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.leftMargin = ActivityUtils.scaleX(20);
		params.addRule(RelativeLayout.RIGHT_OF, message.getId());
		topbarLayout.addView(refreshBtn, params);
		
		TextView resultText = new TextView(mContext);
		resultText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_small));
		resultText.setLines(1);
		resultText.setId(resultText.hashCode());
		resultText.setText("扫描结果：");
		params = ActivityUtils.getLayoutParams(320, 40);
		params.leftMargin = ActivityUtils.scaleX(70);
		params.addRule(RelativeLayout.BELOW, icon.getId());
		rootLayout.addView(resultText, params);
		
		
		LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDiagnosisList = (ListView) mInflater.inflate(R.layout.main_listview, null);
		mDiagnosisList.setCacheColorHint(Color.TRANSPARENT);
		mDiagnosisList.setScrollbarFadingEnabled(false);
		params = ActivityUtils.getLayoutParams(590, 280);
		params.leftMargin = ActivityUtils.scaleX(70);
		params.addRule(RelativeLayout.BELOW, resultText.getId());
		rootLayout.addView(mDiagnosisList, params);
		
		mDiagnosisAdapter = new DiagnosisListAdapter(mContext);
		mDiagnosisList.setAdapter(mDiagnosisAdapter);
		
		DiagnosisListInfo info = new DiagnosisListInfo();
		for (int i=0; i<10; i++){
			 info = new DiagnosisListInfo();
			 info.name = "123";
			 info.safe = true;
			 mArrayList.add(info);
		}
		
		refreshBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				mIControl.showCarInfomation(IControl.SHOW_INFO_DIAGNOSIS, null);
			}
		});
	}
	
	public void showContentInfo(int event, Object obj){
		switch (event) {
		case IControl.SHOW_INFO_DIAGNOSIS:
			if (obj != null){
				mArrayList = (ArrayList<DiagnosisListInfo>) obj;
				updateDiagnosisResult();
			}
			mDiagnosisAdapter.setData(mArrayList);
			mDiagnosisAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	
	private void updateDiagnosisResult() {
		int cnt = 0;
		int value = 100;
		if (mArrayList != null) {
			cnt = mArrayList.size();
			for (DiagnosisListInfo info : mArrayList) {
				if (info == null) {
					continue;
				}
				
				if (!info.safe) {
					value -= info.weight;
					
					if (value <= 0) {
						break;
					}
				}
			}
		}
		
		value = (value >= 0)? value : 0;
		mMessageText.setText("已经扫描 " + cnt+"项, 得分: " + value);
		
	}
}