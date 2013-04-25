package com.zjdx.vehicle.middleware.db;

import com.zjdx.vehicle.LogUtils;
import com.zjdx.vehicle.middleware.model.Item;
import com.zjdx.vehicle.middleware.model.ItemMgr;
import com.zjdx.vehicle.middleware.model.ModelHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler2 extends SQLiteOpenHelper {
	private static final String TAG = "DatabaseHandler ";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "vehicle";
	private static final String KEY_VEHICLE = "_vehicleId_";
	private static final int LOCAL_VEHICLE_ID = 0;
	private static final String KEY_UPDATE_TIME = "_date_";
	
	public DatabaseHandler2(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createAllTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		
		deleleAllTables(db);
		createAllTables(db);
	}	
	
	private void createAllTables(SQLiteDatabase db) {
		Integer[] ids = ItemMgr.getAllFrameId();
		for (int i=0, cnt=ids.length; i<cnt; i++) {
			if (ids[i] == null)
				continue;
			
			int id = ids[i].intValue();
			String tableName = ItemMgr.getDesc(id);
			Item[] items= ItemMgr.getItems(id);
			if (tableName == null || items == null)
				continue;
			String[] keys = ModelHelper.GetTextString(items);
			createTable(db, tableName, keys);			
		}
	}

	private void createTable(SQLiteDatabase db, String tableName, String[] keys) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName)
		.append("(").append(KEY_VEHICLE).append(" INTEGER PRIMARY KEY,");
		for (int i=0,cnt=keys.length; i<cnt; i++) {
			sql.append(keys[i]).append(" INTEGER,");
		}		
		sql.append(KEY_UPDATE_TIME).append(" date default CURRENT_DATE").append(")");
		db.execSQL(sql.toString());
	}
	
	public boolean updateTable(int frameId, int[] values) {
		LogUtils.LOGV(TAG, "updateTable " + frameId + " <---");		
		
		String tableName = ItemMgr.getDesc(frameId);
		Item[] items= ItemMgr.getItems(frameId);
		if (tableName == null || items == null) {
			return false;
		}
		String[] keys = ModelHelper.GetTextString(items);
		boolean res = updateTable(tableName, keys, values);	
		LogUtils.LOGV(TAG, "updateTable " + frameId + " --->");
		return res;
	}
	
	public boolean updateTable(String tableName, String[] keys, int[] values) {
		boolean res = true;
		if (tableName == null || keys == null || values == null || keys.length > values.length)
			return false;
		SQLiteDatabase db = getWritableDatabase();
		// delete first 
		db.delete(tableName, KEY_VEHICLE + " = ?",
				new String[] { String.valueOf(LOCAL_VEHICLE_ID) });
		
		// then insert
		ContentValues content = new ContentValues();
		content.put(KEY_VEHICLE, LOCAL_VEHICLE_ID); 
		for (int i=0,cnt=keys.length; i<cnt; i++) {
			content.put(keys[i], values[i]); 
		}
		db.insert(tableName, null, content);
		
		db.close();
		return res;
	}
	
	public int[] queryTable(int frameId) {
		LogUtils.LOGV(TAG, "queryTable " + frameId);		
		String tableName = ItemMgr.getDesc(frameId);
		Item[] items= ItemMgr.getItems(frameId);
		if (tableName == null || items == null) {
			return null;
		}
		String[] keys = ModelHelper.GetTextString(items);	
		int[] values = new int[keys.length];	
		return queryTable(tableName, keys, values)? values : null;
	}
	
	public boolean queryTable(int frameId, int[] values) {
		LogUtils.LOGV(TAG, "queryTable " + frameId);		
		
		String tableName = ItemMgr.getDesc(frameId);
		Item[] items= ItemMgr.getItems(frameId);
		if (tableName == null || items == null) {
			return false;
		}
		String[] keys = ModelHelper.GetTextString(items);		
		return queryTable(tableName, keys, values);
	}
	
	public boolean queryTable(String tableName, String[] keys, int[] values) {
		LogUtils.LOGD(TAG, "queryTable " + tableName + " <----");
		boolean res = false;
		if (tableName == null || keys == null || values == null || keys.length > values.length)
			return false;
		
		SQLiteDatabase db = getReadableDatabase();
		
//		LogUtils.LOGD(TAG, "queryTable " + tableName + " query <----");
		Cursor cursor = db.query(tableName, keys , KEY_VEHICLE + " = ?",
				new String[] { String.valueOf(LOCAL_VEHICLE_ID) }, null, null, null, null);
//		LogUtils.LOGD(TAG, "queryTable query cursor " + cursor + " ---->");
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
//			LogUtils.LOGD(TAG, "queryTable getCount " + cursor.getCount()
//					+ " getColumnCount " + cursor.getColumnCount()
//					+ " isAfterLast " + cursor.isAfterLast());
			for (int i=0; i<keys.length; i++) {
				try {
					values[i] = Integer.parseInt(cursor.getString(i));
				}
				catch(NumberFormatException e) {
					values[i] =  0;
				}
			}
			res = true;
		}
		
		if (cursor != null){
			cursor.close();
		}
		
		db.close();
		
		LogUtils.LOGD(TAG, "queryTable " + tableName + " ---->");
		return res;
	}
	
	
	private void deleleAllTables(SQLiteDatabase db) {
		Integer[] ids = ItemMgr.getAllFrameId();
		for (int i=0, cnt=ids.length; i<cnt; i++) {
			if (ids[i] == null)
				continue;
			db.execSQL("DROP TABLE IF EXISTS " + ItemMgr.getDesc(ids[i].intValue()));
		}
	}
}
