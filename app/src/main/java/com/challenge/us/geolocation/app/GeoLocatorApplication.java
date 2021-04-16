package com.challenge.us.geolocation.app;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class GeoLocatorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
