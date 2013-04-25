package com.zjdx.vehicle.activity.folder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class DragGridView extends GridView {

	private int dragPosition;// ��ʼ��ק��λ��
	private int dropPosition;// ������ק��λ��
	private int dragPointX; // �����item��x����
	private int dragPointY; // �����item��y����
	private int dragOffsetX;
	private int dragOffsetY;
	private ImageView dragImageView; // �϶�item��preview

	private WindowManager windowManager;
	private WindowManager.LayoutParams windowParams;

	public DragGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

//		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//
//			int x = (int) ev.getX();
//			int y = (int) ev.getY();
//			dragPosition = dropPosition = pointToPosition(x, y);
//			System.out.println(dragPosition);
//			if (dragPosition == AdapterView.INVALID_POSITION) {
//				return super.onInterceptTouchEvent(ev);
//			}
//			ViewGroup itemView = (ViewGroup) getChildAt(dragPosition
//					- getFirstVisiblePosition());
//			// �õ���ǰ����item�ڲ���ƫ���� �������item���Ͻǵ�����
//			dragPointX = x - itemView.getLeft();
//			dragPointY = y - itemView.getTop();
//
//			dragOffsetX = (int) (ev.getRawX() - x);
//			dragOffsetY = (int) (ev.getRawY() - y);
//			// �������3
//			// ÿ�ζ�����һ��cache����������һ��bitmap
//			itemView.destroyDrawingCache();
//			itemView.setDrawingCacheEnabled(true);
//			Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache());
//			// ����item������ͼ
//			startDrag(bm, x, y);
//
//			return false;
//		}

		return super.onInterceptTouchEvent(ev);
	}

	private void startDrag(Bitmap bm, int x, int y) {
		stopDrag();

		windowParams = new WindowManager.LayoutParams();
		System.out.println("X: " + x + " dragPointX: " + dragPointX
				+ " dragOffsetX: " + dragOffsetX);
		windowParams.gravity = Gravity.TOP | Gravity.LEFT;// ��������
		// �õ�preview���Ͻ��������Ļ������
		windowParams.x = x - dragPointX + dragOffsetX;
		windowParams.y = y - dragPointY + dragOffsetY;
		// ���ÿ�͸�
		windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

		windowParams.format = PixelFormat.TRANSLUCENT;
		windowParams.windowAnimations = 0;

		ImageView iv = new ImageView(getContext());
		iv.setImageBitmap(bm);
		windowManager = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);// "window"
		windowManager.addView(iv, windowParams);
		dragImageView = iv;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (dragImageView != null
				&& dragPosition != AdapterView.INVALID_POSITION) {
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			switch (ev.getAction()) {
			case MotionEvent.ACTION_MOVE:
				onDrag(x, y);
				break;
			case MotionEvent.ACTION_UP:
				stopDrag();
				onDrop(x, y);
				break;
			}
		}
		return super.onTouchEvent(ev);
	}

	private void onDrag(int x, int y) {
		if (dragImageView != null) {
			windowParams.alpha = 0.6f;
			windowParams.x = x - dragPointX + dragOffsetX;
			windowParams.y = y - dragPointY + dragOffsetY;
			windowManager.updateViewLayout(dragImageView, windowParams);
		}
	}

	private void onDrop(int x, int y) {
		int tempPosition = pointToPosition(x, y);
		if (tempPosition != AdapterView.INVALID_POSITION) {
			dropPosition = tempPosition;
		}
		if (dropPosition != dragPosition) {
			System.out.println("dragPosition: " + dragPosition
					+ " dropPosition: " + dropPosition);
			// ImageAdapter adapter = (ImageAdapter)this.getAdapter();
			// adapter.exchange(dragPosition, dropPosition);

			// �������3
			/*
			 * ViewGroup itemView1 = (ViewGroup)getChildAt(dragPosition -
			 * getFirstVisiblePosition()); ViewGroup itemView2 =
			 * (ViewGroup)getChildAt(dropPosition - getFirstVisiblePosition());
			 * itemView1.destroyDrawingCache(); itemView2.destroyDrawingCache();
			 */
		}
	}

	private void stopDrag() {
		if (dragImageView != null) {
			windowManager.removeView(dragImageView);
			dragImageView = null;
		}
	}
}