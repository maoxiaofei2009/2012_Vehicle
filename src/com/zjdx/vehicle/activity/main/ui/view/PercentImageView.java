package com.zjdx.vehicle.activity.main.ui.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import android.widget.ImageView;

public class PercentImageView extends ImageView {
	public static enum Direction {
		CUT_LEFT,
		CUT_RIGHT,
		CUT_UP,
		CUT_DOWN,	
		MOVE_DOWN,
	};
	
	private int mWidth = 0;
	private int mHeight = 0;
	private float mPercent = 1.0f;
	private Direction mDirection = Direction.CUT_DOWN;
		
	private int mRes = 0;
	private Bitmap mBitmap = null;	
	private Rect mSrcRect = new Rect();
	private Rect mDestRect = new Rect();	
	
	public PercentImageView(Context context) {
  		 super(context);    		
	}
	
	public void setResourceId(int res) {
		if (mRes != res) {
			if (mBitmap != null) {
				mBitmap.recycle();
			}
			
			if (res != 0) {
				mBitmap = BitmapFactory.decodeResource(getResources(), res);
				if (mBitmap != null && (mWidth == 0 || mHeight == 0)) {
					setSize(mBitmap.getWidth(), mBitmap.getHeight());
				}
			}
			
			mRes = res;
		}
	}
	
	public void setSize(int width, int height) {
		mWidth = width;
		mHeight = height;
	}
	
	public void setDirection(Direction d) {
		mDirection = d;
	}
	
	public void setPercent(float percent) {
		mPercent = (percent >= 0.0f && percent <= 1.0f)? percent : 
				   (percent < 0.0f)? 0.0f : 1.0f;
		invalidate();
	}
	
	public float getPercent() {
		return mPercent;
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas); 	
		
		if (calculateRects()) {		
			canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, null);
		}
	}
	
	private boolean calculateRects() {
		if (mBitmap == null || mWidth == 0 || mHeight == 0) 
			return false;
		
		float scaleX = 1.0f * mBitmap.getWidth() / mWidth;
		float scaleY = 1.0f * mBitmap.getHeight() / mHeight;
		float scale = Math.max(scaleX, scaleY);
		int width = (int) (mBitmap.getWidth() / scale);
		int height = (int) (mBitmap.getHeight() / scale);
		switch (mDirection) {
		case CUT_LEFT:
			mSrcRect.set((int)(mBitmap.getWidth() * mPercent), 0, mBitmap.getWidth(),mBitmap.getHeight());
			mDestRect.set((int)(mWidth * mPercent), 0, mWidth, mHeight);
			break;
		case CUT_RIGHT:
			mSrcRect.set(0, 0, (int)(mBitmap.getWidth() * (1.0f - mPercent)),mBitmap.getHeight());
			mDestRect.set(0, 0, (int)(mWidth * (1.0f - mPercent)), mHeight);
			break;
		case CUT_UP:			
			mSrcRect.set(0, 0, mBitmap.getWidth(),(int)(mBitmap.getHeight() * (1.0f - mPercent)));
			mDestRect.set(0, 0, mWidth, (int)(mHeight * (1.0f - mPercent)));
			break;
		case CUT_DOWN:
			mSrcRect.set(0, (int)(mBitmap.getHeight() * mPercent), mBitmap.getWidth(),mBitmap.getHeight());
			//mDestRect.set(0, (int)(mHeight * mPercent), mWidth, mHeight);
			mDestRect.set((mWidth - width)/2 , 
					(int)((mHeight - height)/2 + height * mPercent),
					(mWidth + width)/2, 
					(mHeight + height)/2);
			break;			
		case MOVE_DOWN:
			mSrcRect.set(0, 0, mBitmap.getWidth(),(int)(mBitmap.getHeight() * (1.0f - mPercent)));
			//mDestRect.set(0, (int)(mHeight * mPercent), mWidth, mHeight);
			mDestRect.set((mWidth - width)/2 , 
					(int)((mHeight - height)/2 + height * mPercent),
					(mWidth + width)/2, 
					(mHeight + height)/2);
			break;
		default:
			break;
		}
		
		return true;
	}
}
