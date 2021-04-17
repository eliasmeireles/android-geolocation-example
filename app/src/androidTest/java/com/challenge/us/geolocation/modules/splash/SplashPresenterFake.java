package com.challenge.us.geolocation.modules.splash;

public class SplashPresenterFake implements SplashPresenter {

    SplashRouter splashRouter;

    public void setRouterMock(SplashRouter splashRouter) {
        this.splashRouter = splashRouter;
    }

    @Override
    public void bindWith(SplashView view) {

    }
}
