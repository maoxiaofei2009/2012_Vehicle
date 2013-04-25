package com.zjdx.vehicle.service.cloud;

import java.util.ArrayList;

public class JsonFramePool {
	private static final String TAG = "JsonFramePool ";
	private static final int MAX_COUNT = 100;
	
	private static class Item {
		int time;
		String json;	
		public Item(int time, String json) {
			this.time = time;
			this.json = json;
		}
	}

	private ArrayList<Item> mList = null;
	public JsonFramePool() {
		mList = new ArrayList<Item>();
	}
	
	synchronized boolean pushJson(int time, String json) {
		if (mList.size() < MAX_COUNT) {
			mList.add(new Item(time, json));			
		}
		else {
			mList.remove(0);
			mList.add(new Item(time, json));			
		}
		return true;
	}
	
	synchronized String popJson(int time) {		
		String json = null;	
		for (int index=0, cnt=mList.size(); index<cnt; index++) {
			Item item = mList.get(index);
			if (item != null && item.time >= time) {
				json = item.json;
				mList.remove(index);
				break;
			}		
		}
		
		return json;
	}
}
