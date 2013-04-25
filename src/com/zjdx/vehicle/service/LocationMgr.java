package com.zjdx.vehicle.service;


import com.zjdx.vehicle.LogUtils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationMgr {
	private static final String TAG = "LocationMgr ";
	private static LocationMgr mInstance = null;	
	private LocationListener [] mLocationListeners = null;
	private LocationManager mLocationManager = null;
	
	private LocationMgr() {
		mLocationListeners = new LocationListener[] {
				new LocationListener(LocationManager.GPS_PROVIDER),
				new LocationListener(LocationManager.NETWORK_PROVIDER)
			};
	}
	
	public static synchronized LocationMgr Instance() {
		if (mInstance == null) {
			mInstance = new LocationMgr();
		}
		return mInstance;
	}
	
	public static synchronized Location getLocation() {
		return (mInstance != null)? mInstance.getCurrentLocation() : null;	
	}
	
	private Location getCurrentLocation() {
		for (int i = 0; i < mLocationListeners.length; i++) {
			Location loc = mLocationListeners[i].getLocation();
		    if (loc != null) {
		    	return new Location(loc);		    	
		    }
		}
		
		if(mLocationManager != null) {
			for (int i = 0; i < mLocationListeners.length; i++) {
				Location loc = mLocationManager.getLastKnownLocation(mLocationListeners[i].getProvider());
				if (loc != null) {
			    	return new Location(loc);		    	
			    }
			}
		}
	
		return null;
	}
	
	public synchronized void onCreate(Context context) {
		mLocationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if (mLocationManager != null) {
			try {
				mLocationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 
					60000, 
					500F, 
					mLocationListeners[1]);
			} catch (SecurityException ex) {
				LogUtils.LOGD(TAG,"request listen Network location failed");
			} catch (IllegalArgumentException ex) {
				LogUtils.LOGD(TAG,"provider does not exist " + ex.getMessage());
			}

			try {
				mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 
					60000, 
					500F, 
					mLocationListeners[0]);
			} catch (java.lang.SecurityException ex) {
				LogUtils.LOGD(TAG,"request Gps location failed");
			} catch (IllegalArgumentException ex) {
				LogUtils.LOGD(TAG, "provider does not exist " + ex.getMessage());
			}
		}
	}
	
	public synchronized void onDestroy() {
		if (mLocationManager != null) {
			for (int i = 0; i < mLocationListeners.length; i++) {
				try {
					mLocationManager.removeUpdates(mLocationListeners[i]);
				} catch (Exception ex) {
					// OK
				}
			}
		}
		mLocationManager = null;
	}
	
	private class LocationListener implements android.location.LocationListener {
		String mProvider = null;
		Location mLastLocation = null;
		boolean mValided = false;	
		
		public LocationListener(String provider) {	
			mProvider = provider;
			mLastLocation = new Location(provider);
		}
		
		@Override
		public synchronized void onLocationChanged(Location location) {
			mLastLocation.set(location);
			mValided = true;			
		}

		@Override
		public void onProviderDisabled(String provider) {
			mValided = false;
		}

		@Override
		public void onProviderEnabled(String provider) {
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}		
		
		public synchronized Location getLocation() {
			return mValided? mLastLocation : null;
		}
		
		public String getProvider() {
			return mProvider;
		}
	}
}
