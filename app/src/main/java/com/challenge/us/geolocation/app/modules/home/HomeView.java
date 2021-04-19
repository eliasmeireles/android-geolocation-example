package com.challenge.us.geolocation.app.modules.home;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;
import com.challenge.us.geolocation.app.components.HomeTutorialComponent;
import com.challenge.us.geolocation.app.components.MapOptionsComponent;

public interface HomeView {

    AppCompatActivity getActivity();

    GoogleMapComponent googleMapComponent();

    MapOptionsComponent mapOptionsComponent();

    HomeTutorialComponent homeTutorialComponent();
}
