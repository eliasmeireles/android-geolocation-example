package com.challenge.us.geolocation.app.modules.home;

import com.challenge.us.geolocation.data.model.MarkerData;

public interface HomeRouter {

    void bindWith(HomeView homeView);

    void openMapWith(MarkerData data);
}
