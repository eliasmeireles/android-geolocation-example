package com.challenge.us.geolocator.app;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class GeoLocatorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
