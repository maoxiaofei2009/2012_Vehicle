package com.zjdx.vehicle.activity.folder;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.folder.entity.HomeAppInfo;

public class AppChoiceActivity extends Activity implements OnItemClickListener{	
	private GridView mAppGridView = null ;
	private AppGridAdapter mAppAdapter = null;
	public static final String EXTRA_CHOICE_RESULT = "CHOICE_RESULT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        
        initUIResource();

        new SearchAPPAyscTask(this).execute("");
    }
    
    private void initUIResource(){
    	mAppGridView = (GridView)findViewById(R.id.app_gridview);
    	mAppGridView.setOnItemClickListener(this);
    	
    	mAppAdapter = new AppGridAdapter(this, null);
    	mAppGridView.setAdapter(mAppAdapter);
    }
    
    private void showAppList(List<ResolveInfo> data){
    	if (mAppAdapter != null){
    		mAppAdapter.update(data);
    		mAppAdapter.notifyDataSetChanged();
    	}
    }
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ResolveInfo res = mAppAdapter.getItem(position);
		HomeAppInfo info = new HomeAppInfo();
		info.packageName = res.activityInfo.packageName ;
		info.className = res.activityInfo.name ;
		Intent intent = new Intent();
		intent.putExtra(EXTRA_CHOICE_RESULT, info);
		setResult(RESULT_OK, intent);
		finish();
	}
	

	private class AppGridAdapter extends BaseAdapter {
		private Context mContext ;
		private List<ResolveInfo> mData ;
		private PackageManager mPackageManager;
		AppGridAdapter(Context context , List<ResolveInfo> data) {
			mContext = context ;
			mData = data;
			mPackageManager = context.getPackageManager();
		}
		
		public void update(List<ResolveInfo> data){
			mData = data;
		}
		
		@Override
		public int getCount() {
			int size = 0;
			if (mData != null){
				size = mData.size();
			}
			return size;
		}

		@Override
		public ResolveInfo getItem(int position) {
			if (mData != null){
				return mData.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null){
				convertView = LayoutInflater.from(mContext).inflate(R.layout.app_item, null);
				holder = new ViewHolder();
				holder.icon = (ImageView)convertView.findViewById(R.id.app_icon);
				holder.title = (TextView)convertView.findViewById(R.id.app_title);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			ResolveInfo res = getItem(position);
			if (res != null){
				holder.icon.setImageDrawable(res.loadIcon(mPackageManager));
				holder.title.setText(res.loadLabel(mPackageManager));
			}
			return convertView;
		}
		
		private class ViewHolder{
			public ImageView icon;
			public TextView title;
		}
	}
	
	
	private class SearchAPPAyscTask extends AsyncTask<String, String, List<ResolveInfo>>{
		private Context mContext;
		private ProgressDialog mDialog;
		public SearchAPPAyscTask(Context context){	
			mContext = context;
		}

		@Override
		protected void onPreExecute() {
			mDialog = new ProgressDialog(mContext);
			mDialog.setIndeterminate(false);
			mDialog.setMessage("正在加载所有应用程序");
			mDialog.show();
		}
		
		@Override
		protected List<ResolveInfo> doInBackground(String... params) {
	    	Intent intent = new Intent(Intent.ACTION_MAIN , null);
	    	intent.addCategory(Intent.CATEGORY_LAUNCHER);

	    	final PackageManager packageManager = mContext.getPackageManager();
	    	List<ResolveInfo> appsList =packageManager.queryIntentActivities(intent, 0);
	    	Collections.sort(appsList, new ResolveInfo.DisplayNameComparator(packageManager));
			return appsList;
		}
		
		@Override
		protected void onPostExecute(List<ResolveInfo> result) {
			if (mDialog != null){
				mDialog.dismiss();
			}
			showAppList(result);
		}
	}	
}