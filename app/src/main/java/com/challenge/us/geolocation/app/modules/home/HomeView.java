package com.challenge.us.geolocation.app.modules.home;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;

public interface HomeView {

    AppCompatActivity getActivity();

    GoogleMapComponent getMapComponent();

}
