package org.apache.cordova.sipkita;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class Lokasi extends CordovaPlugin implements LocationListener {
	private static final String LOG_TAG = "Lokasi";
	private LocationManager locationManager;
	private String provider;

	public Lokasi() {}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("coordinate")) {
			coordinate(callbackContext);
			return true;
		} else if (action.equals("address")) {
			address(callbackContext);
			return true;
		} else if (action.equals("addressByCoordinate")) {
			double latitude = args.getDouble(0);
			double longitude = args.getDouble(1);
			addressByCoordinate(callbackContext, latitude, longitude);
			return true;
		}
		return false;
	}

	private void addressByCoordinate(CallbackContext callbackContext, double latitude, double longitude) {
		Geocoder geocoder = new Geocoder(this.cordova.getActivity());
		try {
			List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
			if (addresses != null) {
				Address returnedAddress = addresses.get(0);
				JSONObject json = new JSONObject();
				json.put("postalCode", returnedAddress.getPostalCode());
				JSONArray jArray = new JSONArray();
//				StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
				for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
					jArray.put(i, returnedAddress.getAddressLine(i));
//					strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
				}
//				longitudeField.setText(strReturnedAddress.toString());
				json.put("address", jArray);
				callbackContext.success(json);
			}
			else {
				callbackContext.error("No Address returned!");
			}
		} catch (Exception e) {
			String errMsg = e.getMessage();
			Log.e(LOG_TAG, errMsg);
			e.printStackTrace();
			callbackContext.error(errMsg);
		}
	}

	private void address(CallbackContext callbackContext) {}

	private void coordinate(CallbackContext callbackContext) {
		locationManager = (LocationManager) this.cordova.getActivity().getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!enabled) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			this.cordova.getActivity().startActivity(intent);
		}
		if (location != null) {
//			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
			Map<String, Double> map = new HashMap<String, Double>();
			map.put("latitude", location.getLatitude());
			map.put("longitude", location.getLongitude());
			JSONObject json = new JSONObject(map);
			callbackContext.success(json);
		} else {
			callbackContext.error("Location not available");
		}
	}

	@Override
	public void onLocationChanged(Location location) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onProviderDisabled(String provider) {}
}
/*
	ctx.getContext() replaced with cordova.getContext() 
	ctx.startActivity() replaced with cordova.getActivity().startActivity() 
	ctx.getSystemService() replaced with cordova.getActivity().getSystemService() 
	ctx.getAssets() replaced with cordova.getActivity().getAssets() 
	ctx.runOnUiThread() replaced with cordova.getActivity().runOnUiThread() 
	ctx.getApplicationContext() replaced with 
	cordova.getActivity().getApplicationContext() 
	ctx.getPackageManager() replaced with cordova.getActivity().getPackageManager() 
	ctx.getSharedPreferences() replaced with 
	cordova.getActivity().getSharedPreferences() 
	ctx.unregisterActivity() replaced with 
	cordova.getActivity().unregisterActivity() 
	ctx.getResources() replaced with cordova.getActivity().getResources() 
	import com.phonegap.api.* replaced with import org.apache.cordova.api.* 

*/
