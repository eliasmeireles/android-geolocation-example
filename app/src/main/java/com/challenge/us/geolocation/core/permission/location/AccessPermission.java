package com.challenge.us.geolocation.core.permission.location;

import android.app.Activity;


public interface AccessPermission {
    int ACCESS_PERMISSION_FINE_LOCATION = 101;

    boolean accessLocation(Activity activity);

    void requestPermission(Activity activity);

    boolean deviceGPSEnable(Activity activity);
}
