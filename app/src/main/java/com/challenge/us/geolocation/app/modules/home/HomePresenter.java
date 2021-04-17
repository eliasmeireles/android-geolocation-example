package com.challenge.us.geolocation.app.modules.home;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;

public interface HomePresenter extends GoogleMapComponent.MapListener {

    void bindWith(HomeView homeView);
}
