package com.zjdx.vehicle.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.folder.HomeAppActivity;
import com.zjdx.vehicle.activity.main.MainActivity;
import com.zjdx.vehicle.middleware.communication.Parser;
import com.zjdx.vehicle.service.VehicleService;

public class HomeActivity extends Activity implements OnClickListener{
	private static final String TAG = "HomeActivity ";
	private View mMainView;
	private ImageView mDockView1;
	private ImageView mDockView2;
	private ImageView mDockView3;
	private ImageView mDockView4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_home);
        
        initUIResource();
        
        LogUtils.LOGD(TAG, "onCreate startService <---");
        Intent intent = new Intent(this, VehicleService.class);     
        startService(intent);       
        LogUtils.LOGD(TAG, "onCreate startService --->");
//        testParser();
    }
        
    private void initUIResource(){
    	mMainView = findViewById(R.id.home_main_image);
    	mMainView.setOnClickListener(this);
    	
    	mDockView1 = (ImageView) findViewById(R.id.dock_security);
    	mDockView1.setOnClickListener(this);
    	
    	mDockView2 = (ImageView) findViewById(R.id.dock_traffic);
    	mDockView2.setOnClickListener(this);
    	
    	mDockView3 = (ImageView) findViewById(R.id.dock_nearby);
    	mDockView3.setOnClickListener(this);
    	
    	mDockView4 = (ImageView) findViewById(R.id.dock_message);
    	mDockView4.setOnClickListener(this);
    }



	@Override
	public void onClick(View v) {
		if (v == mMainView){
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		
		if (v == mDockView1){
			Intent intent = new Intent(this, HomeAppActivity.class);
			intent.putExtra(HomeAppActivity.EXTRA_LOAD_TYPE, HomeAppActivity.LOAD_SECURITY_APP_LIST);
			startActivity(intent);
		}
		
		if (v == mDockView2){
			Intent intent = new Intent(this, HomeAppActivity.class);
			intent.putExtra(HomeAppActivity.EXTRA_LOAD_TYPE, HomeAppActivity.LOAD_TRAFFIC_APP_LIST);
			startActivity(intent);
		}
		
		if (v == mDockView3){
			Intent intent = new Intent(this, HomeAppActivity.class);
			intent.putExtra(HomeAppActivity.EXTRA_LOAD_TYPE, HomeAppActivity.LOAD_NEARBY_APP_LIST);
			startActivity(intent);
		}
		
		if (v == mDockView4){
			Intent intent = new Intent(this, HomeAppActivity.class);
			intent.putExtra(HomeAppActivity.EXTRA_LOAD_TYPE, HomeAppActivity.LOAD_MESSAGE_APP_LIST);
			startActivity(intent);
		}
	}
}
