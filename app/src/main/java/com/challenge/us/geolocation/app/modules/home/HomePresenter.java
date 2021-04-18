package com.challenge.us.geolocation.app.modules.home;

import androidx.annotation.NonNull;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;

public interface HomePresenter extends GoogleMapComponent.MapListener {

    void bindWith(HomeView homeView);

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
