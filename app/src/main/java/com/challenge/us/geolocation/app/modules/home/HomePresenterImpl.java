package com.challenge.us.geolocation.app.modules.home;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

public class HomePresenterImpl implements HomePresenter {

    private HomeRouter homeRouter;

    private HomeView homeView;

    @Inject
    public HomePresenterImpl(HomeRouter homeRouter) {
        this.homeRouter = homeRouter;
    }

    @Override
    public void bindWith(HomeView homeView) {
        this.homeView = homeView;
        homeRouter.bindWith(homeView);
        homeView.setMapListener(this);
    }

    @Override
    public void deviceLocation(LatLng latLng) {
        System.out.println(latLng.toString());
    }

    @Override
    public void mapIsReady() {

    }
}
