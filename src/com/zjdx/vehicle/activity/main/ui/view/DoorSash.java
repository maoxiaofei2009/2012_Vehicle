package com.zjdx.vehicle.activity.main.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.zjdx.vehicle.R;
import com.zjdx.vehicle.activity.main.ui.view.PercentImageView.Direction;
import com.zjdx.vehicle.activity.utils.ActivityUtils;

public class DoorSash extends RelativeLayout implements OnTouchListener{
	private int mWidth  = 0;
	private int mHeight = 0;	
	private float mPercent = 0.0f;
	private Context mContext = null;
	private PercentImageView mSash = null;
	private float mTouchDownY = 0;
	
	public DoorSash(Context context) {
		super(context);
		onCreate(context);
	}

	public DoorSash(Context context, AttributeSet attrs) {
		super(context, attrs);
		onCreate(context);
	}

	public DoorSash(Context context, AttributeSet attrs, int defStyle) {
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
		inner.setImageResource(R.drawable.d_w_1);
		inner.setLayoutParams(new LayoutParams(mWidth, mHeight));
		addView(inner);
		
		mSash = new PercentImageView(mContext);  
		mSash.setDirection(Direction.MOVE_DOWN);      
		mSash.setSize((int)(mWidth), (int)(mHeight));   
		mSash.setResourceId(R.drawable.d_w_2);
		mSash.setLayoutParams(new LayoutParams(mWidth, mHeight));
        
		setSash(0.0f);
        addView(mSash); 
        
        ImageView outer = new ImageView(mContext);
        outer.setImageResource(R.drawable.d_w_3);
        outer.setLayoutParams(new LayoutParams(mWidth, mHeight));
        outer.setOnTouchListener(this);
		addView(outer);
		
	}
	
	public float getSash() {
		return mPercent;
	}
	
	public void setSash(float f) {
		f = f /4 * 3;
		mPercent = (f >= 0.0f && f < 1.0f)? f : (f < 0.0f)? 0.0f : 1.0f;			
		mSash.setPercent(mPercent);
	}
	
	static public int getLayoutWidth() {
		return ((int)(422/1.2));
	}
	
	static public int getLayoutHeight() {
		return ((int)(234/1.2));
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mTouchDownY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float distance = event.getY() - mTouchDownY;
			mTouchDownY = event.getY();
			float percent = 0;
			if (distance > 0){
				percent = mPercent +  0.75f * 1.0f * distance/(mHeight);
				if (percent > 0.75){
					percent = 0.75f;
				}
			}else{
				percent = mPercent -  0.75f * 1.0f * (-distance)/(mHeight);
				if (percent < 0.0f){
					percent = 0.0f;
				}
			}
			
			if (mPercent != percent){
				setSash(percent * 4 / 3);
				if (mStatusListener != null){
					mStatusListener.OnStatusChange(percent * 4 / 3);
				}
			}
			break;	
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchDownY = 0;
			break;
			
		default:
			break;
		}
		return true;
	}
	
	private StatusListener mStatusListener;
	public void setStatusListener(StatusListener listener){
		mStatusListener = listener;
	}
	
	public interface StatusListener{
		public void OnStatusChange(float percent);
	}
}
