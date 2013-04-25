package com.zjdx.vehicle.activity.main.ui.view;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.ui.view.PercentImageView.Direction;
import com.zjdx.vehicle.activity.utils.ActivityUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Battery extends RelativeLayout {
	private int mWidth  = 0;
	private int mHeight = 0;	
	private int mResIndex = -1;
	private float mBattery = 0.0f;
	private Context mContext = null;
	private PercentImageView mImageView = null;
	
	private int[] mResIds = {
		R.drawable.main_soc_battery_percent_green,
		R.drawable.main_soc_battery_percent_yellow,
		R.drawable.main_soc_battery_percent_red,
	};
	
	public Battery(Context context) {
		super(context);
		onCreate(context);
	}

	public Battery(Context context, AttributeSet attrs) {
		super(context, attrs);
		onCreate(context);
	}

	public Battery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onCreate(context);
	}

	private void onCreate(Context context) {
		if (mContext != null)
			return;
		mContext = context;
		mWidth = getLayoutWidth();
		mHeight = getLayoutHeight();		
		
		ImageView inner = new ImageView(mContext);
		inner.setImageResource(R.drawable.main_soc_battery_percent_background);
		inner.setLayoutParams(new LayoutParams(mWidth, mHeight));
		addView(inner);
		
		mImageView = new PercentImageView(mContext);  
        mImageView.setDirection(Direction.CUT_DOWN);      
        mImageView.setSize(mWidth, mHeight);       
        mImageView.setLayoutParams(new LayoutParams(mWidth, mHeight));
        
        setBattery(0.0f);
        addView(mImageView); 
	}
		
	public float getBattery() {
		return mBattery;
	}
	
	public void setBattery(float f) {
		mBattery = (f >= 0.0f && f < 1.0f)? f : (f < 0.0f)? 0.0f : 1.0f;		
		int resId = (mBattery >= 0.30f)? 0 : (mBattery > 0.10f)? 1 : 2;
		
		if (resId != mResIndex) {
			 mImageView.setResourceId(mResIds[resId]);
			 mResIndex = resId;
		}
		mImageView.setPercent(1.0f - mBattery);
	}
	
	static public int getLayoutWidth() {
		return ActivityUtils.scaleX(156);
	}
	
	static public int getLayoutHeight() {
		return ActivityUtils.scaleY(248);
	}
	
}
