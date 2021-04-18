package com.challenge.us.geolocation.app.modules.home;

import com.challenge.us.geolocation.core.permission.location.AccessPermission;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class HomeModule {

    @Provides
    public HomePresenter presenter(HomeRouter homeRouter, AccessPermission accessPermission) {
        return new HomePresenterImpl(homeRouter, accessPermission);
    }

    @Provides
    public HomeRouter router() {
        return new HomeRouterImpl();
    }
}
