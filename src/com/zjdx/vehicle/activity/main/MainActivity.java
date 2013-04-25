package com.zjdx.vehicle.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.control.MainControl;
import com.zjdx.vehicle.activity.main.data.VehicleModel;
import com.zjdx.vehicle.activity.utils.ActivityUtils;
import com.zjdx.vehicle.service.VehicleBinding;

public class MainActivity extends Activity {
	private static final String TAG = "Main ";
		
	private MainControl mMainControl = null;
	private VehicleModel mVehicleModel = null;
	private VehicleBinding mBinding = null;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LogUtils.LOGD(TAG, "onCreate <----");
    	/** initialize screen size */
		initScreenSize();      
        setContentView(R.layout.activity_main);          
        LogUtils.LOGD(TAG, "onCreate new MainControl");
    	mMainControl = new MainControl(this);
    	mVehicleModel = VehicleModel.Instance();
		mBinding = new VehicleBinding(this,mVehicleModel);	
		LogUtils.LOGD(TAG, "onCreate ---->");
    }
       
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	LogUtils.LOGD(TAG, "onWindowFocusChanged " + hasFocus + " <----");
    	super.onWindowFocusChanged(hasFocus);
    	if (hasFocus){
    		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    		if (mVehicleModel != null) {
        		mVehicleModel.onResume(this, mMainControl);
        	} 
    	}
    	else {    		
        	if (mVehicleModel != null) {
        		mVehicleModel.onPause();
        	}
        	getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    	}
    	LogUtils.LOGD(TAG, "onWindowFocusChanged " + hasFocus + " ---->");
    }
        
    public void onDestroy() {     
    	LogUtils.LOGD(TAG, "onDestroy <----");
    	if (mBinding != null) {
    		mBinding.onDestroy();
    		mBinding = null;
    	}
    	
    	if (mVehicleModel != null) {
    		mVehicleModel.onPause(); /* YES, THIS IS PAUSE. */
    		mVehicleModel = null;
    	}    	
    	    	
    	super.onDestroy(); 
    	LogUtils.LOGD(TAG, "onDestroy ---->");
    }

    private void initScreenSize() {
    	/** Get screen width and height */
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
			
		int w = display.getWidth() < display.getHeight()? display.getHeight() :  display.getWidth();
		int h = display.getWidth() < display.getHeight()? display.getWidth() :  display.getHeight();
    
		/** Get Android DPI from system*/
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		ActivityUtils.scaleInit(w, h, dm.densityDpi, 1280, 800, 240);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	return super.onOptionsItemSelected(item);
    } 
	
}
