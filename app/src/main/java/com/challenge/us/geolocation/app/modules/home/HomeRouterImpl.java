package com.challenge.us.geolocation.app.modules.home;

public class HomeRouterImpl implements HomeRouter {

    private HomeView homeView;

    @Override
    public void bindWith(HomeView homeView) {
        this.homeView = homeView;
    }
}
