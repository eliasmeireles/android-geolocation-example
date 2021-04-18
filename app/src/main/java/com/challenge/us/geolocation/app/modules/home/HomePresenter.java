package com.challenge.us.geolocation.app.modules.home;

import androidx.annotation.NonNull;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;
import com.challenge.us.geolocation.app.components.MapOptionsComponent;

public interface HomePresenter extends GoogleMapComponent.MapListener, MapOptionsComponent.MapOptionDelegate {

    void bindWith(HomeView homeView);

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
