package com.challenge.us.geolocation.app.modules.splash;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class SplashModule {

    @Provides
    SplashPresenter presenter(SplashRouter splashRouter) {
        return new SplashPresenterImpl(splashRouter);
    }

    @Provides
    SplashRouter router() {
        return new  SplashRouterImpl();
    }
}
