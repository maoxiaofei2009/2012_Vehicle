package com.zjdx.vehicle.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceUtils {
	private static final String LOG_TAG = SharedPreferenceUtils.class.getSimpleName();

	public static final String MAINTANENCE_BRAKE_MILE_RECORD = "BRAKE_MILE_RECORD";
	public static final String MAINTANENCE_BRAKE_TIMER_RECORD = "BRAKE_TIMER_RECORD";
	public static final String MAINTANENCE_CHECK_MILE_RECORD= "CHECK_MILE_RECORD";
	public static final String MAINTANENCE_CHECK_TIMER_RECORD= "CHECK_TIMER_RECORD";
	
	
	public static final String MAINTANENCE_BRAKE_MILE_DUTATION = "BRAKE_MILE_DUTATION";
	public static final String MAINTANENCE_BRAKE_TIMER_DUTATION = "BRAKE_TIMER_DUTATION";
	public static final String MAINTANENCE_CHECK_MILE_DUTATION = "CHECK_MILE_DUTATION";
	public static final String MAINTANENCE_CHECK_TIMER_DUTATION = "CHECK_TIMER_DUTATION";
	
	
	public static int getMaintanenceBrakeMileDuration(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getInt(MAINTANENCE_BRAKE_MILE_DUTATION, 25000);
	}
	
	public static int getMaintanenceBrakeTimerDuration(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getInt(MAINTANENCE_BRAKE_TIMER_DUTATION, 12);
	}
	
	public static int getMaintanenceCheckMileDuration(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getInt(MAINTANENCE_CHECK_MILE_DUTATION, 25000);
	}
	
	public static int getMaintanenceCheckTimerDuration(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getInt(MAINTANENCE_CHECK_TIMER_DUTATION, 24);
	}
	
	
	public static void setMaintanenceBrakeMileDuration(final Context context, int value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putInt(MAINTANENCE_BRAKE_MILE_DUTATION, value).commit();
	}
	
	public static void setMaintanenceBrakeTimerDuration(final Context context, int value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putInt(MAINTANENCE_BRAKE_TIMER_DUTATION, value).commit();
	}
	
	public static void setMaintanenceCheckMileDuration(final Context context, int value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putInt(MAINTANENCE_CHECK_MILE_DUTATION, value).commit();
	}
	
	public static void setMaintanenceCheckTimerDuration(final Context context, int value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putInt(MAINTANENCE_CHECK_TIMER_DUTATION, value).commit();
	}
	//======================================
	
	public static void setMaintanenceBrakeTimerRecord(final Context context, long value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putLong(MAINTANENCE_BRAKE_TIMER_RECORD, value).commit();
	}
	
	public static long getMaintanenceBrakeTimerRecord(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getLong(MAINTANENCE_BRAKE_TIMER_RECORD, -1);
	}
	
	public static void setMaintanenceCheckTimerRecord(final Context context, long value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putLong(MAINTANENCE_CHECK_TIMER_RECORD, value).commit();
	}
	
	public static long getMaintanenceCheckTimerRecord(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getLong(MAINTANENCE_CHECK_TIMER_RECORD, -1);
	}
	
	//======================================
	public static void setMaintanenceBrakeMileRecord(final Context context, int value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putInt(MAINTANENCE_BRAKE_MILE_RECORD, value).commit();
	}
	
	public static int getMaintanenceBrakeMileRecord(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getInt(MAINTANENCE_BRAKE_MILE_RECORD, -1);
	}
	
	public static void setMaintanenceCheckMileRecord(final Context context, int value){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putInt(MAINTANENCE_CHECK_MILE_RECORD, value).commit();
	}
	
	public static int getMaintanenceCheckMileRecord(final Context context){
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getInt(MAINTANENCE_CHECK_MILE_RECORD, -1);
	}
	
	//=======================================
	public static String getAppList(final Context context, String loadAppType) {
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.getString(loadAppType, null);
	}
	
	public static void savaAppList(final Context context, final String loadAppType,  final String app_list) {
		final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		preference.edit().putString(loadAppType, app_list).commit();
	}
}