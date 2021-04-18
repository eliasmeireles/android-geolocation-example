package com.challenge.us.geolocation.app.modules.home;

import androidx.annotation.NonNull;

import com.challenge.us.geolocation.data.model.MarkerData;
import com.google.android.gms.maps.model.LatLng;

public class HomePresenterFake implements HomePresenter {
    @Override
    public void bindWith(HomeView homeView) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }


    @Override
    public void mapIsReady() {

    }

    @Override
    public void onMarkerClick(MarkerData markerData) {

    }

    @Override
    public void searByGeolocation(String value) {

    }

    @Override
    public void clipCurrentGeolocation() {

    }
}
