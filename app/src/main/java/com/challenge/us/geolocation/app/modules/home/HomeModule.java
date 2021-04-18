package com.challenge.us.geolocation.app.modules.home;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class HomeModule {

    @Provides
    public HomePresenter presenter(HomeRouter homeRouter) {
        return new HomePresenterImpl(homeRouter);
    }

    @Provides
    public HomeRouter router() {
        return new HomeRouterImpl();
    }
}
