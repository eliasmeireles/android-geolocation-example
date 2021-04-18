package com.challenge.us.geolocation.core.permission.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class AccessPermissionImpl implements AccessPermission {

    public boolean accessLocation(Activity activity) {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public boolean deviceGPSEnable(Activity activity) {
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
