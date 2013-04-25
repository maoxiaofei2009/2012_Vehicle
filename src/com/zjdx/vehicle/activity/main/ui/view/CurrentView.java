package com.zjdx.vehicle.activity.main.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.ui.view.PercentImageView.Direction;
import com.zjdx.vehicle.activity.utils.ActivityUtils;

public class CurrentView extends RelativeLayout {
	private int mWidth  = 0;
	private int mHeight = 0;	
	private float mPercent = 0.0f;
	private Context mContext = null;
	private PercentImageView mImageView = null;
	
	public CurrentView(Context context) {
		super(context);
		onCreate(context);
	}

	public CurrentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		onCreate(context);
	}

	public CurrentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onCreate(context);
	}

	private void onCreate(Context context) {
		if (mContext != null)
			return;
		mContext = context;
		mWidth = ActivityUtils.scaleX(getLayoutWidth());
		mHeight = ActivityUtils.scaleY(getLayoutHeight());		
		
		ImageView inner = new ImageView(mContext);
		inner.setImageResource(R.drawable.main_soc_current_background);
		inner.setLayoutParams(new LayoutParams(mWidth, mHeight));
		addView(inner);
		
		mImageView = new PercentImageView(mContext);  
        mImageView.setDirection(Direction.CUT_DOWN);      
        mImageView.setSize(mWidth, mHeight);       
        mImageView.setResourceId(R.drawable.main_soc_current_blue);
        mImageView.setLayoutParams(new LayoutParams(mWidth, mHeight));
        
        setPercent(0.0f);
        addView(mImageView); 
	}
		
	public float getCurrentPercent() {
		return mPercent;
	}
	
	public void setPercent(float percent) {
		mPercent = percent;
		mImageView.setPercent(1.0f - percent);
		if (mPercent > 0.5){
			mImageView.setResourceId(R.drawable.main_soc_current_blue);
		}else{
			mImageView.setResourceId(R.drawable.main_soc_current_orange);
		}
	}
	
	static public int getLayoutWidth() {
		return 136;
	}
	
	static public int getLayoutHeight() {
		return 219;
	}
}
