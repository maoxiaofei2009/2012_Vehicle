package com.zjdx.vehicle.activity.utils;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class AnimUtils{
	private final static int ANIM_DURATION = 500;
	public static enum AnimType{
		ROTATE_IN,
		ROTATE_OUT,
		SCALE_IN,
		SCALE_OUT
	}
	
	public static void startAnimOpen(View leftView, View rightView) {
		startRotateLeftAnim(leftView, AnimType.ROTATE_OUT);
		startRotateRightAnim(rightView, AnimType.ROTATE_OUT);
	}
	
	public static void startAnimClose(View leftView, View rightView){
		startRotateLeftAnim(leftView, AnimType.ROTATE_IN);
		startRotateRightAnim(rightView, AnimType.ROTATE_IN);
	}
	
	private static void startRotateLeftAnim(View view, AnimType type){
		AnimationSet animSet = new AnimationSet(true);
		float fromXValue = 0;
		float toXValue = 0;
		if (type == AnimType.ROTATE_OUT){
			fromXValue = 0.0f;
			toXValue = -0.5f;
		}else if(type == AnimType.ROTATE_IN){
			fromXValue = -0.5f;
			toXValue = 0.0f;
		}		
		TranslateAnimation transAnim = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, fromXValue, 
				Animation.RELATIVE_TO_SELF, toXValue,
				Animation.RELATIVE_TO_SELF, 0.0f, 
				Animation.RELATIVE_TO_SELF, 0.0f);
		transAnim.setDuration(ANIM_DURATION);
		transAnim.setFillAfter(true);
		//animSet.addAnimation(transAnim);
		

		Rect rect = new Rect();
		view.getDrawingRect(rect);	
		float fromDegrees = 0;
		float toDegrees  = -30;
		float depth = 10;
		boolean reverse = true;
		if (type == AnimType.ROTATE_OUT){
			fromDegrees = 0;
			toDegrees = 45;
			reverse = true;
		}else if(type == AnimType.ROTATE_IN){
			fromDegrees = 45;
			toDegrees = 0;
			reverse = false;
		}	
		Animation rotation = new Rotate3d(fromDegrees, toDegrees, rect.left, rect.centerY(), depth, reverse);
		rotation.setDuration(ANIM_DURATION);
		rotation.setFillAfter(true);
		
		animSet.addAnimation(rotation);
		animSet.setFillAfter(true);
		view.startAnimation(animSet);
	}
	
	private static void startRotateRightAnim(View view, AnimType type){
		AnimationSet animSet = new AnimationSet(true);
		float fromXValue = 0;
		float toXValue = 0;
		if (type == AnimType.ROTATE_OUT){
			fromXValue = 0.0f;
			toXValue = 0.5f;
		}else if(type == AnimType.ROTATE_IN){
			fromXValue = 0.5f;
			toXValue = 0.0f;
		}		
		TranslateAnimation transAnim = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, fromXValue, 
				Animation.RELATIVE_TO_SELF, toXValue,
				Animation.RELATIVE_TO_SELF, 0.0f, 
				Animation.RELATIVE_TO_SELF, 0.0f);
		transAnim.setDuration(ANIM_DURATION);
		transAnim.setFillAfter(true);
		//animSet.addAnimation(transAnim);
		

		Rect rect = new Rect();
		view.getDrawingRect(rect);	
		float fromDegrees = 0;
		float toDegrees  = 30;
		float depth = 10;
		boolean reverse = true;
		if (type == AnimType.ROTATE_OUT){
			fromDegrees = 0;
			toDegrees = -45;
			reverse = true;
		}else if(type == AnimType.ROTATE_IN){
			fromDegrees = -45;
			toDegrees = 0;
			reverse = false;
		}	
		Animation rotation = new Rotate3d(fromDegrees, toDegrees, rect.right, rect.centerY(), depth, reverse);
		rotation.setDuration(ANIM_DURATION);
		rotation.setFillAfter(true);
		
		animSet.addAnimation(rotation);
		animSet.setFillAfter(true);
		view.startAnimation(animSet);
	}
	
	public static void startScaleCenterAnim(View view, AnimType type){
		ScaleAnimation scaleAnim = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1f,  
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,  
                0.5f);  
		if (type == AnimType.SCALE_OUT){
			scaleAnim = new ScaleAnimation(0.001f, 1.0f, 0.1f, 1f,  
	                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,  
	                0.5f);  
		}else if (type == AnimType.SCALE_IN){
			scaleAnim = new ScaleAnimation(1.0f, 0.001f, 1.0f, 0.1f,  
	                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,  
	                0.5f); 
		}
		scaleAnim.setInterpolator(new AccelerateInterpolator());  
        AnimationSet animSet = new AnimationSet(true);  
        animSet.addAnimation(scaleAnim);  
        animSet.setFillAfter(true);
        animSet.setDuration(ANIM_DURATION);
        
        view.startAnimation(animSet);
	}
	
	public static class Rotate3d extends Animation {
		private final float mFromDegrees;
		private final float mToDegrees;
		private final float mCenterX;
		private final float mCenterY;
		private final float mDepthZ;
		private final boolean mReverse;
		private Camera mCamera;

		public Rotate3d(float fromDegrees, float toDegrees, float centerX,
				float centerY, float depthZ, boolean reverse) {
			mFromDegrees = fromDegrees;
			mToDegrees = toDegrees;
			mCenterX = centerX;
			mCenterY = centerY;
			mDepthZ = depthZ;
			mReverse = reverse;
		}

		@Override
		public void initialize(int width, int height, int parentWidth,
				int parentHeight) {
			super.initialize(width, height, parentWidth, parentHeight);
			mCamera = new Camera();
		}

		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t) {
			final float fromDegrees = mFromDegrees;
			float degrees = fromDegrees
					+ ((mToDegrees - fromDegrees) * interpolatedTime);
			final float centerX = mCenterX;
			final float centerY = mCenterY;
			final Camera camera = mCamera;
			final Matrix matrix = t.getMatrix();
			camera.save();
			if (mReverse) {
				camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
			} else {
				camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
			}

			camera.rotateY(degrees);
			camera.getMatrix(matrix);
			camera.restore();

			//matrix.preTranslate(-centerX, -centerY);
			//matrix.postTranslate(centerX, centerY);
			matrix.preTranslate(-centerX, -centerY);
			matrix.postTranslate(centerX, centerY);
		}
	}
}