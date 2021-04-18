package com.challenge.us.geolocation.app.modules.home;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;

public interface HomeView {

    AppCompatActivity getActivity();

    void setMapListener(GoogleMapComponent.MapListener listener);

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"})
    void setMyLocationEnabled(boolean enabled);
}
